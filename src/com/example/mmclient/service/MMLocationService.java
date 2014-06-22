package com.example.mmclient.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class MMLocationService extends Service implements LocationListener {
	private final Context mContext;

	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

	private Location location; // location
	private double latitude = 0.0; // latitude
	private double longitude = 0.0; // longitude

	protected LocationManager locationManager;

	public MMLocationService(Context context) {
		this.mContext = context;
		locationManager = (LocationManager) mContext
				.getSystemService(LOCATION_SERVICE);
	}

	public void scanLocation() {
		update(LocationManager.NETWORK_PROVIDER);
		update(LocationManager.GPS_PROVIDER);
		update(LocationManager.PASSIVE_PROVIDER);
	}

	public void update(String provider) {
		if (locationManager.isProviderEnabled(provider)) {
			if (locationManager != null) {
				locationManager.requestLocationUpdates(provider,
						MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES,
						this);
				setLocation(locationManager.getLastKnownLocation(provider));
				if (getLocation() != null) {
					setLatitude(getLocation().getLatitude());
					setLongitude(getLocation().getLongitude());
				}
			}
		}
	}

	public Location getLocation() {

		return location;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
