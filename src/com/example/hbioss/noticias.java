package com.example.hbioss;

import com.example.hbioss.GestureListener;
import com.example.hbioss.MainActivity;
import com.example.hbioss.noticias;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;


public class noticias extends ActionBarActivity {

	private GestureDetector mGestureDetector;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticias);
        
        mGestureDetector = new GestureDetector(this, new GestureListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00944b"));     
        actionBar.setBackgroundDrawable(colorDrawable);
               
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Intent trocaTela = new Intent(noticias.this,configuracoes.class);	
        	noticias.this.startActivity(trocaTela);
        	noticias.this.finish();
		    
            return true;
        }else if (id == R.id.perfil_id) {
        	Intent trocaTela = new Intent(noticias.this,perfil.class);	
        	noticias.this.startActivity(trocaTela);
		    noticias.this.finish();
		    
		    return true;
        }else if(id == R.id.home){
        	Intent trocaTela = new Intent(noticias.this,perfil.class);	
		    noticias.this.startActivity(trocaTela);
		    
        	this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //method onTouchEvent of GestureDetector class Analyzes the given motion event 
        //and if applicable triggers the appropriate callbacks on the GestureDetector.OnGestureListener supplied.
        //Returns true if the GestureDetector.OnGestureListener consumed the event, else false.
        
        boolean eventConsumed=mGestureDetector.onTouchEvent(event);
            if (eventConsumed)
            {
            	if(GestureListener.currentGestureDetected=="esquerda"){
                  //Toast.makeText(this,GestureListener.currentGestureDetected,Toast.LENGTH_LONG).show();
                                    		
            	  // TODO Auto-generated method stub
          	      Intent trocaTela = new Intent(noticias.this,MainActivity.class);	
          	      noticias.this.startActivity(trocaTela);
          	      noticias.this.finish();
          			               
                  return true;
            	}else{
            	  return false;
            	}
            }
            else
                return false;
    }        
}
