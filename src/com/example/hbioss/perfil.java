package com.example.hbioss;

import com.example.hbioss.GestureListener;
import com.example.hbioss.perfil;
import com.example.hbioss.noticias;
import com.example.hbioss.mensagens;

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
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ViewFlipper;


public class perfil extends ActionBarActivity {

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meuperfil);        
     
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
        	Intent trocaTela = new Intent(perfil.this,configuracoes.class);	
		    perfil.this.startActivity(trocaTela);
		    perfil.this.finish();
		    
            return true;
        }else if (id == R.id.perfil_id) {
		    return true;
        }else if(id == R.id.home){
        	Intent trocaTela = new Intent(perfil.this,perfil.class);	
		    perfil.this.startActivity(trocaTela);
		    
        	this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
