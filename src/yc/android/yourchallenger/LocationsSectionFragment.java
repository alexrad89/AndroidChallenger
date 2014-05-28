package yc.android.yourchallenger;

import java.util.UUID;

import android.content.ContentValues;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LocationsSectionFragment extends Fragment implements
		LocationListener, GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener,
		LoaderManager.LoaderCallbacks<Cursor> {

	/*
	 * Constants for location update parameters
	 */

	private static int MAX_LOCATION_FETCH_COUNT = 10;

	private static final int LOCATION_DATA_LOADER = 0;

	// Milliseconds per second
	public static final int MILLISECONDS_PER_SECOND = 1000;

	// The update interval
	public static final int UPDATE_INTERVAL_IN_SECONDS = 5;

	// A fast interval ceiling
	public static final int FAST_CEILING_IN_SECONDS = 1;

	// Update interval in milliseconds
	public static final long UPDATE_INTERVAL_IN_MILLISECONDS = MILLISECONDS_PER_SECOND
			* UPDATE_INTERVAL_IN_SECONDS;

	// A fast ceiling of update intervals, used when the app is visible
	public static final long FAST_INTERVAL_CEILING_IN_MILLISECONDS = MILLISECONDS_PER_SECOND
			* FAST_CEILING_IN_SECONDS;

	String m_androidId;

	Uri CONTENT_URI_INSERT;

	LocationRequest mLocationRequest;
	LocationClient mLocationClient;

	SimpleCursorAdapter adapter;

	ListView listview;

	public String[] mFromColumns = { SqlLiteHelper.COLUMN_DEVICEID,
			SqlLiteHelper.COLUMN_LAT, SqlLiteHelper.COLUMN_LONG };
	public int[] mToFields = { R.id.title, R.id.desc };

	public LocationsSectionFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		m_androidId = Secure.getString(this.getActivity().getContentResolver(),
				Secure.ANDROID_ID);

		// Create a new global location parameters object
		mLocationRequest = LocationRequest.create();

		/*
		 * Set the update interval
		 */
		mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

		// Use high accuracy
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

		// Set the interval ceiling to one minute
		mLocationRequest
				.setFastestInterval(FAST_INTERVAL_CEILING_IN_MILLISECONDS);

		mLocationClient = new LocationClient(this.getActivity()
				.getApplicationContext(), this, this);

		getLoaderManager().initLoader(LOCATION_DATA_LOADER, null, this);

		adapter = new SimpleCursorAdapter(this.getActivity()
				.getApplicationContext(), R.layout.locations_list_view, null,
				mFromColumns, mToFields, 0);

		adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {

			@Override
			public boolean setViewValue(View view, Cursor cursor, int column) {
				// TODO Auto-generated method stub
				if (column == 2) {
					TextView textView = (TextView) view;
					textView.setText(cursor.getString(2) + ","
							+ cursor.getString(3));
					return true;
				}
				return false;
			}
		});

	}

	@Override
	public void onStop() {

		if (mLocationClient.isConnected())
			mLocationClient.disconnect();

		super.onStop();
	}

	@Override
	public void onStart() {

		super.onStart();

		/*
		 * Connect the client. Don't re-start any requests here; instead, wait
		 * for onResume()
		 */
		mLocationClient.connect();

	}

	@Override
	public void onResume() {
		super.onResume();
		if (mLocationClient.isConnected() && servicesConnected()) {
			mLocationClient.requestLocationUpdates(mLocationRequest, this);
			Log.d("LocationUpdate", "Started");
		}

		return;
	}

	// Normal case behavior follows

	@Override
	public void onPause() {
		super.onPause();
		if (mLocationClient.isConnected() && servicesConnected()) {
			mLocationClient.removeLocationUpdates(this);
			Log.d("LocationUpdate", "Stopped");
		}

		return;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_locations,
				container, false);

		listview = (ListView) rootView.findViewById(R.id.listView1);

		listview.setAdapter(adapter);

		return rootView;
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this.getActivity().getApplicationContext(),
				"Connection Failed", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this.getActivity().getApplicationContext(), "Connected",
				Toast.LENGTH_LONG).show();
		mLocationClient.requestLocationUpdates(mLocationRequest, this);

		/*
		 * if (servicesConnected()) { Location mLastLocation =
		 * mLocationClient.getLastLocation(); if (mLastLocation != null) {
		 * 
		 * ContentValues values = new ContentValues();
		 * values.put(SqlLiteHelper.COLUMN_DEVICEID, UUID.randomUUID()
		 * .toString()); values.put(SqlLiteHelper.COLUMN_LAT,
		 * String.valueOf(mLastLocation.getLatitude()));
		 * values.put(SqlLiteHelper.COLUMN_LONG,
		 * String.valueOf(mLastLocation.getLongitude()));
		 * 
		 * if (CONTENT_URI_INSERT == null) { // New todo CONTENT_URI_INSERT =
		 * this .getActivity() .getContentResolver()
		 * .insert(LocationsContentProvider.CONTENT_URI, values); } else { //
		 * Update todo this.getActivity().getContentResolver()
		 * .update(CONTENT_URI_INSERT, values, null, null); }
		 * 
		 * }
		 * 
		 * }
		 */
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		Toast.makeText(this.getActivity().getApplicationContext(),
				"Disconnected", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

		if ((--MAX_LOCATION_FETCH_COUNT) >= 0) {

			ContentValues values = new ContentValues();
			values.put(SqlLiteHelper.COLUMN_DEVICEID, UUID.randomUUID()
					.toString());
			values.put(SqlLiteHelper.COLUMN_LAT,
					String.valueOf(location.getLatitude()));
			values.put(SqlLiteHelper.COLUMN_LONG,
					String.valueOf(location.getLongitude()));

			// if (CONTENT_URI_INSERT == null) {
			// New todo
			CONTENT_URI_INSERT = this.getActivity().getContentResolver()
					.insert(LocationsContentProvider.CONTENT_URI, values);
			/*
			 * } else { // Update todo this.getActivity().getContentResolver()
			 * .update(CONTENT_URI_INSERT, values, null, null); }
			 */

		}
	}

	private boolean servicesConnected() {

		// Check that Google Play services is available
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this.getActivity());

		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {
			// In debug mode, log the status
			Log.d("yc.android.yourchallenger",
					getString(R.string.play_services_available));
			// Continue
			return true;
			// Google Play services was not available for some reason
		} else {
			// Display an error dialog
			Log.d("yc.android.yourchallenger",
					getString(R.string.play_services_not_available));
			return false;
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle) {
		// TODO Auto-generated method stub

		// Returns a new CursorLoader

		String[] allColumns = { SqlLiteHelper.COLUMN_ID,
				SqlLiteHelper.COLUMN_DEVICEID, SqlLiteHelper.COLUMN_LAT,
				SqlLiteHelper.COLUMN_LONG };

		return new CursorLoader(getActivity(), // Parent activity context
				LocationsContentProvider.CONTENT_URI, // Table to query
				allColumns, // Projection to return
				null, // No selection clause
				null, // No selection arguments
				null // Default sort order
		);

	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// TODO Auto-generated method stub

		adapter.swapCursor(data);

	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		adapter.swapCursor(null);
	}

}
