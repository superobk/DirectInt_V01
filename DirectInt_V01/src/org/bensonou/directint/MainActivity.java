package org.bensonou.directint;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.util.*;

import org.bensonou.directint.util.*;

public class MainActivity extends Activity{
	
	public final static String TAG = "DirectInt";
	
	String[] testList = {"Piggy sister", "Zhu mei", "Zhu qinqin"};
	
	Button btnSwitch;	
	Button btnRefresh;
	Button btnLoad;
	
	ListView listWifi;
	ArrayAdapter<String> adapterListWifi;
	
	WifiUtility myWifi;

	private void initialComponent() {
		btnSwitch = (Button) findViewById(R.id.btnSwitch);
		btnRefresh = (Button) findViewById(R.id.btnRefresh);
		btnLoad = (Button) findViewById(R.id.btnLoad);
		listWifi = (ListView) findViewById(R.id.listWifi);
		
		MainBtnListener btnListener = new MainBtnListener();
		MainListListener listListener = new MainListListener();
		
		btnSwitch.setOnClickListener(btnListener);
		btnRefresh.setOnClickListener(btnListener);
		btnLoad.setOnClickListener(btnListener);
		listWifi.setOnItemClickListener(listListener);
		
		adapterListWifi = new ArrayAdapter<>(this, R.layout.listviewitem_main, testList);
		listWifi.setAdapter(adapterListWifi);
		
		myWifi = new WifiUtility(this);
	}
	
	private void initailVariable() {
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initailVariable();
		initialComponent();
		
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

class MainBtnListener implements OnClickListener{

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSwitch:
			Log.i(MainActivity.TAG, "Switch has been click.");
			System.out.println("Switch has been click.");
			break;
		case R.id.btnRefresh:
			Log.i(MainActivity.TAG, "Refresh has been click.");
			System.out.println("Refresh has been click.");
			break;
		case R.id.btnLoad:
			Log.i(MainActivity.TAG, "Load has been click.");
			System.out.println("Load has been click.");
			break;
		default:
			break;
		}
	}
	
}

class MainListListener implements OnItemClickListener{

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}
		
}