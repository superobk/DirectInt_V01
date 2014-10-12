package org.bensonou.directint.util;

import android.content.Context;
import android.widget.Toast;

public class Utility {
	
	public static void displayToast(Context context, String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	
}
