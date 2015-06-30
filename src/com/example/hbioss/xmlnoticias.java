package com.example.hbioss;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class xmlnoticias extends ActionBarActivity {

	private SitesAdapter mAdapter;
	private ListView sitesList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("StackSites", "OnCreate()");
		setContentView(R.layout.xmlnoticias);

		//Get reference to our ListView
		sitesList = (ListView)findViewById(R.id.sitesList);
		
		//Set the click listener to launch the browser when a row is clicked.
		sitesList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos,long id) {
				String url = mAdapter.getItem(pos).getLink();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
				
			}
			
		});
		
		/*
		 * If network is available download the xml from the Internet.
		 * If not then try to use the local file from last time.
		 */
		if(isNetworkAvailable()){
			Log.i("StackSites", "starting download Task");
			SitesDownloadTask download = new SitesDownloadTask();
			download.execute();
		}else{
			mAdapter = new SitesAdapter(getApplicationContext(), -1, SitesXmlPullParser.getStackSitesFromFile(xmlnoticias.this));
			sitesList.setAdapter(mAdapter);
		}

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
	
	//Helper method to determine if Internet connection is available.
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	} 
	
	/*
	 * AsyncTask that will download the xml file for us and store it locally.
	 * After the download is done we'll parse the local file.
	 */
	private class SitesDownloadTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			//Download the file
			try {
				Downloader.DownloadFromUrl("http://vougansistemas.com.br/testexml.xml", openFileOutput("StackSites.xml", Context.MODE_PRIVATE));
				//Downloader.DownloadFromUrl("https://dl.dropboxusercontent.com/u/5724095/XmlParseExample/stacksites.xml", openFileOutput("StackSites.xml", Context.MODE_PRIVATE));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result){
			//setup our Adapter and set it to the ListView.
			mAdapter = new SitesAdapter(xmlnoticias.this, -1, SitesXmlPullParser.getStackSitesFromFile(xmlnoticias.this));
			sitesList.setAdapter(mAdapter);
			Log.i("StackSites", "adapter size = "+ mAdapter.getCount());
		}
	}
	

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Intent trocaTela = new Intent(xmlnoticias.this,configuracoes.class);	
        	xmlnoticias.this.startActivity(trocaTela);
        	xmlnoticias.this.finish();
		    
            return true;
        }else if (id == R.id.perfil_id) {
        	Intent trocaTela = new Intent(xmlnoticias.this,perfil.class);	
        	xmlnoticias.this.startActivity(trocaTela);
        	xmlnoticias.this.finish();
		    
		    return true;
        }else if(id == R.id.home){
        	Intent trocaTela = new Intent(xmlnoticias.this,perfil.class);	
        	xmlnoticias.this.startActivity(trocaTela);
		    
        	this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    } 	

}
