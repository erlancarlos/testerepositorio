package com.example.hbioss;

import android.view.GestureDetector;
import android.view.MotionEvent;

class GestureListener extends GestureDetector.SimpleOnGestureListener
{
	
    static String currentGestureDetected;
    
	  
   // Override s all the callback methods of GestureDetector.SimpleOnGestureListener
   @Override
   public boolean onSingleTapUp(MotionEvent ev) {
       currentGestureDetected= "simples toque";
    //ev.toString();
     return true;
   }
   @Override
   public void onShowPress(MotionEvent ev) {
       currentGestureDetected= "pressionado";
     //ev.toString()
   }
   @Override
   public void onLongPress(MotionEvent ev) {
       currentGestureDetected= "pressionado 2";
    //ev.toString()
   }
   @Override
   public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
       currentGestureDetected= "scroll";
   //e1.toString()+ "  "+e2.toString()
     return true;
   }
   @Override
   public boolean onDown(MotionEvent ev) {
       currentGestureDetected="para baixo";
     //ev.toString()
     return true;
   }
   @Override
   public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
 	  float sensitvity = 50;
 	  
 	  if((e1.getX() - e2.getX()) > sensitvity){
 		   //viewFlipper.setInAnimation(slide_in_right);
 		   //viewFlipper.setOutAnimation(slide_out_left);
 		   //viewFlipper.showPrevious();
 		   
 		  currentGestureDetected= "esquerda";	  
 	}else if((e2.getX() - e1.getX()) > sensitvity){
 		   //viewFlipper.setInAnimation(slide_in_left);
 		   //viewFlipper.setOutAnimation(slide_out_right);
 		   //viewFlipper.showNext();
 		   currentGestureDetected= "direita";
 	}    	  
 	  
       //e1.toString()+ " vs "+e2.toString()
     return true;
   }
}
