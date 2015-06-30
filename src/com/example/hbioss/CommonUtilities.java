package com.example.hbioss;

import android.content.Context;
import android.content.Intent;
 
public final class CommonUtilities {
     
    // give your server registration url here
    //static final String SERVER_URL = "http://10.0.2.2/gcm_server_php/register.php"; 
    static final String SERVER_URL = "http://cursoa4.com.br/gcm_server_php/register.php";
    static final String SERVER_URL_ADD_CONTACT = "http://cursoa4.com.br/gcm_server_php/add_contacts.php";
    static final String SERVER_URL_SEND_MESSAGE_TO_CONTACT = "http://cursoa4.com.br/gcm_server_php/send_to_contacts.php";
	
	
    // Google project id
    static final String SENDER_ID = "563172146400"; 
 
    /**
     * Tag used on log messages.
     */
    static final String TAG = "Android GCM";
 
    static final String DISPLAY_MESSAGE_ACTION = "com.example.hbioss.DISPLAY_MESSAGE";
 
    static final String EXTRA_MESSAGE = "message";
 
    /**
     * Notifies UI to display a message.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service, that means call BroadcastReceiver mHandleMessageReceiver
     * in MainActivity.
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
