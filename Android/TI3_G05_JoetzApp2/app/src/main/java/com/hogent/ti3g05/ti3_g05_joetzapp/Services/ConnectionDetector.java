package com.hogent.ti3g05.ti3_g05_joetzapp.Services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

//Deze klasse zal de aanwezigheid van het internet controleren op het toestel
public class ConnectionDetector {
	
	private Context _context;
    private Boolean connected;

	public ConnectionDetector(Context context){
		this._context = context;
	}
    //Deze methode zal controleren of het toestel met het internet verbonden is
	public boolean isConnectingToInternet(){
		ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        connected= false;

		  if (connectivity != null) 
		  {
			  NetworkInfo[] info = connectivity.getAllNetworkInfo();
			  if (info != null) 
				  for (int i = 0; i < info.length; i++) 
					  if (info[i].getState() == NetworkInfo.State.CONNECTED)
					  {
						   return true;
					  }

		  }
        return connected;

	}
}
