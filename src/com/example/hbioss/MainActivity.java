package com.example.hbioss;

import static com.example.hbioss.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.example.hbioss.CommonUtilities.EXTRA_MESSAGE;
import static com.example.hbioss.CommonUtilities.SENDER_ID;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.BroadcastReceiver;

import com.example.hbioss.AddContactActivity;
import com.example.hbioss.AlertDialogManager;
import com.example.hbioss.ConnectionDetector;
import com.example.hbioss.MainActivity;
import com.example.hbioss.R;
import com.example.hbioss.ServerUtilities;
import com.example.hbioss.WakeLocker;
import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity {
	
	
    // label to display gcm messages
    TextView lblMessage;
     
    // Asyntask
    AsyncTask<Void, Void, Void> mRegisterTask;
     
    // Alert dialog manager
    AlertDialogManager alert = new AlertDialogManager();
     
    // Connection detector
    ConnectionDetector cd;
     
    public static String name;
    public static String email;
    
    EditText et;
    
    public void abrirActivityAddContact(View view){
    	// Checks whether Device is already registered on GCM
        if (GCMRegistrar.isRegisteredOnServer(this)) {
        	Intent i = new Intent(this, AddContactActivity.class);
			startActivity(i);
        }
        else{
        	Toast.makeText(getApplicationContext(), "The device is not registered yet", Toast.LENGTH_LONG).show();
        }
    }
    
    
    public void enviarMensaje(View view){
		
    	et = (EditText)findViewById(R.id.texto);
    	final String newMessage= et.getText().toString();
    	lblMessage.append("\n" +newMessage);
    	
    	et.setText("");
    	
    	if (GCMRegistrar.isRegisteredOnServer(this)) {
    	
	    	final Context context = this;
			 mRegisterTask = new AsyncTask<Void, Void, Void>() {
	
	            @Override
	            protected Void doInBackground(Void... params) {
	                ServerUtilities.sendMessageToContacts(context, MainActivity.name, MainActivity.name+": "+newMessage);
	                return null;
	            }
	
	            @Override
	            protected void onPostExecute(Void result) {
	                mRegisterTask = null;
	            }
	
	        };
	        mRegisterTask.execute(null, null, null);
	    	
    	}
        else{
    		Toast.makeText(getApplicationContext(), "You should send a non empty message.", Toast.LENGTH_LONG).show();
    		return;
    	}
    	
    	
	     
	}
    
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        et = (EditText)findViewById(R.id.texto);
        
        cd = new ConnectionDetector(getApplicationContext());
 
        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(MainActivity.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }
         
        // Getting name, email from intent
        Intent i = getIntent();
         
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");      
         
        // Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);
 
        // Make sure the manifest was properly set - comment out this line
        // while developing the app, then uncomment it when it's ready.
        GCMRegistrar.checkManifest(this);
 
        lblMessage = (TextView) findViewById(R.id.lblMessage);
         
        registerReceiver(mHandleMessageReceiver, new IntentFilter(DISPLAY_MESSAGE_ACTION));
         
        // Get GCM registration id
        final String regId = GCMRegistrar.getRegistrationId(this);
 
        // Check if regid already presents
        if (regId.equals("")) {
            // Registration is not present, register now with GCM           
            GCMRegistrar.register(this, SENDER_ID);
        } else {
            // Device is already registered on GCM
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                // Skips registration.              
                Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
            } else {
                // Try to register again, but not in the UI thread.
                // It's also necessary to cancel the thread onDestroy(),
                // hence the use of AsyncTask instead of a raw thread.
                final Context context = this;
                mRegisterTask = new AsyncTask<Void, Void, Void>() {
 
                    @Override
                    protected Void doInBackground(Void... params) {
                        // Register on our server
                        // On server creates a new user
                        ServerUtilities.register(context, name, email, regId);
                        return null;
                    }
 
                    @Override
                    protected void onPostExecute(Void result) {
                        mRegisterTask = null;
                    }
 
                };
                mRegisterTask.execute(null, null, null);
            }
        }
    }       
 
    /**
     * Receiving push messages
     * */
    private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
            if(newMessage==null)
            	return;
            boolean fromUser = !newMessage.contains("attempt");
            // Waking up mobile if it is sleeping
            WakeLocker.acquire(getApplicationContext());
             
            /**
             * Take appropriate action on this message
             * depending upon your app requirement
             * For now i am just displaying it on the screen
             * */
             
            // Showing received message
            if(fromUser){
            	lblMessage.append("\n" +newMessage);
            	Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
            }
            else
            	Toast.makeText(getApplicationContext(), newMessage, Toast.LENGTH_LONG).show();
             
          
            // Releasing wake lock
            WakeLocker.release();
        }
    };
     
    @Override
    protected void onDestroy() {
        if (mRegisterTask != null) {
            mRegisterTask.cancel(true);
        }
        try {
            unregisterReceiver(mHandleMessageReceiver);
            GCMRegistrar.onDestroy(this);
        } catch (Exception e) {
            Log.e("UnRegister Receiver Error", "> " + e.getMessage());
        }
        super.onDestroy();
    }
 
}