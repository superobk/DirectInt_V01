package org.bensonou.directint;

import android.app.Activity;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.util.*;

import org.bensonou.directint.util.*;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener{
	
	public final static String TAG = "DirectInt";
	
	boolean returnFlag = false;
	String[] ssidList = null;
	//String[] testList = {"Piggy sister", "Zhu mei", "Zhu qinqin"};
	
	WifiUtility myWifi;
	
	Button btnSwitch;	
	Button btnRefresh;
	Button btnLoad;
	
	ListView listWifi;
	ArrayAdapter<String> adapterListWifi;

	private void initialComponent() {
		btnSwitch = (Button) findViewById(R.id.btnSwitch);
		btnRefresh = (Button) findViewById(R.id.btnRefresh);
		btnLoad = (Button) findViewById(R.id.btnLoad);
		listWifi = (ListView) findViewById(R.id.listWifi);
		
		btnSwitch.setOnClickListener(this);
		btnRefresh.setOnClickListener(this);
		btnLoad.setOnClickListener(this);
		listWifi.setOnItemClickListener(this);
		
		//adapterListWifi = new ArrayAdapter<String>(this, R.layout.listviewitem_main, testList);
		//listWifi.setAdapter(adapterListWifi);
		
		myWifi = new WifiUtility(this);
	}
	
	private void initailVariable() {
		
	}
	
	private void settingListview(){
		ssidList = myWifi.getWifiListString();
		adapterListWifi = new ArrayAdapter<String>(this, R.layout.listviewitem_main, ssidList);
		listWifi.setAdapter(adapterListWifi);
	}
	
	private void clearListview(){
		String[] temp = new String[] {};
		adapterListWifi = new ArrayAdapter<String>(this, R.layout.listviewitem_main, temp);
		listWifi.setAdapter(adapterListWifi);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initailVariable();
		initialComponent();
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		switch (myWifi.checkState()){
		case WifiManager.WIFI_STATE_ENABLED:
			Utility.displayToast(this, "Wifi enabled..");
			myWifi.startScan();
			this.settingListview();
			btnSwitch.setText("Close Wifi");
			break;
		case WifiManager.WIFI_STATE_DISABLED:
			Utility.displayToast(this, "Wifi disabled..");
			this.clearListview();
			btnSwitch.setText("Open Wifi");
			break;
		default:
			break;
		}
			
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSwitch:
			Log.i(MainActivity.TAG, "Switch has been click.");
			if(myWifi.checkState() == WifiManager.WIFI_STATE_ENABLED){
				myWifi.closeWifi();
				this.clearListview();
				btnSwitch.setText("Open Wifi");
			}
			else if (myWifi.checkState() == WifiManager.WIFI_STATE_DISABLED){
				myWifi.openWifi();
				myWifi.startScan();
				this.settingListview();
				btnSwitch.setText("Close Wifi");
			}
			else
				Utility.displayToast(this, "Please wait for Wifi status..");
			break;
		case R.id.btnRefresh:
			Log.i(MainActivity.TAG, "Refresh has been click.");
			if(myWifi.checkState() == WifiManager.WIFI_STATE_ENABLED){
				myWifi.startScan();
				this.settingListview();
			}
			else
				Utility.displayToast(this, "Please open Wifi in advance..");
			break;
		case R.id.btnLoad:
			Log.i(MainActivity.TAG, "Load has been click.");
			if(myWifi.checkState() == WifiManager.WIFI_STATE_ENABLED){
				Intent intent = new Intent (this, BrowserActivity.class);
				startActivity(intent);
			}
			else
				Utility.displayToast(this, "Please open Wifi in advance..");
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//Utility.displayToast(this, "Item on clicked " + position + " " + id);
		Utility.displayToast(this, myWifi.getWifiList().get(position).SSID + "\n" + myWifi.getWifiList().get(position).capabilities);
		myWifi.connectConfiguration(position);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}