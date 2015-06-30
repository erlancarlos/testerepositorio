package com.example.hbioss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.example.hbioss.GestureListener;
import com.example.hbioss.R.string;
import com.example.hbioss.contatos;
import com.example.hbioss.meuscontatos.Type;
import com.example.hbioss.noticias;
import com.example.hbioss.mensagens;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat.AdapterContextMenuInfo;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class contatos extends ActionBarActivity {

	private String[] lstEstados;
	private static final int EDITAR = 1;
	private static final int APAGAR = 2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contatos);        
     
        List personList = retornaListaContatos();
        ArrayAdapter ad = new CustomAdapter(this, R.layout.itemcontato, personList);
        ListView lv = (ListView) findViewById(R.id.contatosList);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setAdapter(ad);
        registerForContextMenu(lv);
        
        //Button botaoOpcoes = (Button) findViewById(R.id.botaoopcoes);
        
        //botaoOpcoes.setOn   
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menumeuscontatos, menu);
    	/*
      menu.add(Menu.NONE,EDITAR,100,"Editar");
      menu.add(Menu.NONE,APAGAR,101,"Apagar");*/
    }
    
    private List retornaListaContatos(){
        List p = new ArrayList();
        p.add(new meuscontatos(1,"Erlan","Casamento da Mara este FDS :)","2hs ago", true,"bradpitt"));
        p.add(new meuscontatos(2,"Kleyton","Casamento da Mara este FDS :)","1h ago",false,"bradpitt"));
        p.add(new meuscontatos(3,"Jonathan","Casamento da Mara este FDS :)","30min ago",false,"bradpitt"));
        p.add(new meuscontatos(4,"Gerson","Casamento da Mara este FDS :)","3hs ago",true,"bradpitt"));
        p.add(new meuscontatos(5,"Victor","Casamento da Mara este FDS :)","2h ago",true,"bradpitt"));
        p.add(new meuscontatos(6,"Tiago","Casamento da Mara este FDS :)","1h ago",true,"bradpitt"));
        p.add(new meuscontatos(7,"Angelina Jolie","Casamento da Mara este FDS :)","2h ago",false,"angelina_jolie"));
        p.add(new meuscontatos(8,"Scarlet Johansson","Casamento da Mara este FDS :)","2h ago",true,"scarlet"));
 
        return p;
 
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
        	Intent trocaTela = new Intent(contatos.this,configuracoes.class);	
        	contatos.this.startActivity(trocaTela);
		    contatos.this.finish();
		    
            return true;
        }else if (id == R.id.perfil_id) {
        	Intent trocaTela = new Intent(contatos.this,perfil.class);	
        	contatos.this.startActivity(trocaTela);
		    contatos.this.finish();
		    
		    return true;
        }else if(id == R.id.home){
        	Intent trocaTela = new Intent(contatos.this,perfil.class);	
		    contatos.this.startActivity(trocaTela);
		    
        	this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }    
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {  
       if(item.getTitle()=="Action 1"){
    	  //function1(item.getItemId());
       }else if(item.getTitle()=="Action 2"){
    	  //function2(item.getItemId());
       }else {
    	  return false;
       }  
       return true;  
    }
}
