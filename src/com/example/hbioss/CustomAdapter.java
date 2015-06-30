package com.example.hbioss;

import java.util.List;

import com.example.hbioss.meuscontatos.Type;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<meuscontatos>{
    /*
     * Used to instantiate layout XML file into its corresponding View objects
     */
    private final LayoutInflater inflater;
 
    /*
     * each list item layout ID
     */
    private final int resourceId;
 
    public CustomAdapter(Context context, int resource, List<meuscontatos> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.resourceId = resource;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        //get the person from position
        meuscontatos contatos = getItem(position);
 
        // get a new View no matter recycling or ViewHolder FIXME
        convertView = inflater.inflate(resourceId, parent, false);
 
        //get all object from view
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        TextView name = (TextView) convertView.findViewById(R.id.tv1);
        TextView ultimaMensagem = (TextView) convertView.findViewById(R.id.tv3);
        TextView vistoporultimo = (TextView) convertView.findViewById(R.id.vistoporultimo);
 
        //fill the view objects according values from person object
        name.setText(contatos.getName());
        ultimaMensagem.setText(contatos.getUltimaMensagem());
        
        //Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://abc.com/image.jpg").getContent());
 
        if(contatos.getFoto()=="bradpitt"){
          image.setImageResource(R.drawable.brad_pitt);
        }else if(contatos.getFoto()=="scarlet"){
          image.setImageResource(R.drawable.scarlet);
        }else{
          image.setImageResource(R.drawable.angelina_jolie);
        }
        /*
        if(contatos.getType() == Type.FEMALE){
            //female.setChecked(true);
            //image.setImageResource(R.drawable.female);
        }else{
            //male.setChecked(true);
            //image.setImageResource(R.drawable.male);
        }*/
 
        if(contatos.isClient()){
            //client.setChecked(true);
        }
 
        return convertView;
    }
}
