package org.bensonou.directint;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import org.bensonou.directint.util.*;

public class MainActivity extends Activity{
	
	Button btnSwitch;	
	Button btnRefresh;
	Button btnLoad;
	
	ListView listWifi;
	SimpleAdapter adapterListWifi;
	
	WifiUtility myWifi;
	
	private void initailVariable() {
		
	}
	
	private void initialComponent() {
		btnSwitch = (Button) findViewById(R.id.btnSwitch);
		btnRefresh = (Button) findViewById(R.id.btnRefresh);
		btnLoad = (Button) findViewById(R.id.btnLoad);
		
		
		
		myWifi = new WifiUtility(this);
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

class MainClickListener implements OnClickListener{

	@Override
	public void onClick(View v) {
		
	}
	
}

class MainItemListener implements OnItemClickListener{

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}
		
}