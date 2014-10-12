package org.bensonou.directint.util;

import java.util.*;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

public class WifiUtility {

	private WifiManager mWifiManager;
	private WifiInfo mWifiInfo;
	private List<ScanResult> mWifiList;
	
	// List of Wifi Configuration
	private List<WifiConfiguration> mWifiConfiguration;
	
	WifiLock mWifiLock;

	public WifiUtility(Context context) {
		// Achieve the wifi object
		mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		// Achieve the object information
		mWifiInfo = mWifiManager.getConnectionInfo();
	}


	public void openWifi() {
		if (!mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(true);
		}
	}


	public void closeWifi() {
		if (mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(false);
		}
	}


	public int checkState() {
		return mWifiManager.getWifiState();
	}

	// Acquire a Wifi lock
	public void acquireWifiLock() {
		mWifiLock.acquire();
	}

	public void releaseWifiLock() {
		//
		if (mWifiLock.isHeld()) {
			mWifiLock.acquire();
		}
	}

	public void createWifiLock() {
		mWifiLock = mWifiManager.createWifiLock("Test");
	}

	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfiguration;
	}
	
	public void connectConfiguration(int index) {
		
		if (index > mWifiConfiguration.size()) {
			return;
		}
		// Enable the selected network
		mWifiManager.enableNetwork(mWifiConfiguration.get(index).networkId,
				true);
	}

	public void startScan() {
		mWifiManager.startScan();
		mWifiList = mWifiManager.getScanResults();
		mWifiConfiguration = mWifiManager.getConfiguredNetworks();
	}

	public List<ScanResult> getWifiList() {
		return mWifiList;
	}
	
	public String[] getWifiListString() {
		String[] wifiString = new String[mWifiList.size()];
		for (int i = 0; i < mWifiList.size(); i++){
			wifiString[i] = mWifiList.get(i).SSID;
		}
		return wifiString;
	}

	public StringBuilder lookUpScan() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < mWifiList.size(); i++) {
			stringBuilder
					.append("Index_" + new Integer(i + 1).toString() + ":");
			// Change the information to a String to display
			stringBuilder.append((mWifiList.get(i)).toString());
			stringBuilder.append("/n");
		}
		return stringBuilder;
	}

	public String getMacAddress() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
	}

	public String getBSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
	}

	public int getIPAddress() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
	}

	public int getNetworkId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	public String getWifiInfo() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}

	public void addNetwork(WifiConfiguration wcg) {
		int wcgID = mWifiManager.addNetwork(wcg);
		mWifiManager.enableNetwork(wcgID, true);
	}

	public void disconnectWifi(int netId) {
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
	}
	
}
