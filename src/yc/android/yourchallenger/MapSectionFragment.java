package yc.android.yourchallenger;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapSectionFragment extends Fragment implements
		LoaderManager.LoaderCallbacks<Cursor> {

	GoogleMap mMap = null;

	// private MapView mapView;
	// private MapController mc;

	private static final int LOCATION_DATA_LOADER2 = 0;

	// MarkerItemizedOverlay itemizedOverlay;

	public MapSectionFragment() {
	}

	/*
	 * public void addOverlayItem(int lat, int lon, String title, String
	 * snippet) { GeoPoint point = new GeoPoint(lat, lon); OverlayItem
	 * overlayItem = new OverlayItem(point, title, snippet);
	 * itemizedOverlay.addOverlayItem(overlayItem); }
	 */

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated
		// the map.

		if (mMap == null) {
			mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
	};

	@Override
	public void onResume() {
		super.onResume();

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getLoaderManager().initLoader(LOCATION_DATA_LOADER2, null, this);

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_map, container,
				false);

		// mapView = (MapView) rootView.findViewById(R.id.map);
		// mc = mapView.getController();

		/*
		 * mapView.setBuiltInZoomControls(true); Drawable makerDefault =
		 * this.getResources().getDrawable( android.R.drawable.star_on);
		 * itemizedOverlay = new MarkerItemizedOverlay(makerDefault);
		 */

		return rootView;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
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
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// TODO Auto-generated method stub

		setUpMapIfNeeded();

		mMap.clear();
		
		Log.d("LatLong", "Cleared Data");
		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {

			MarkerOptions markerOptions = new MarkerOptions();

			markerOptions.position(new LatLng(cursor.getDouble(2), cursor
					.getDouble(3)));
			markerOptions.title(cursor.getString(1));
			markerOptions.flat(true);
			markerOptions.snippet(cursor.getString(2) + ","
					+ cursor.getString(3));
			mMap.addMarker(markerOptions);
			
			

			Log.d("LatLong", cursor.getString(2) + "," + cursor.getString(3));

			cursor.moveToNext();
		}

		/*
		 * if (cursor != null) { cursor.moveToFirst();
		 * 
		 * while (!cursor.isAfterLast()) {
		 * 
		 * addOverlayItem(cursor.getInt(2), cursor.getInt(3),
		 * cursor.getString(1), cursor.getString(2) + "," +
		 * cursor.getString(3)); cursor.moveToNext(); }
		 * 
		 * mapView.getOverlays().add(itemizedOverlay);
		 * 
		 * // mc.setCenter(new GeoPoint(33580000, 73000000));
		 * mc.zoomToSpan(itemizedOverlay.getLatSpanE6(),
		 * itemizedOverlay.getLonSpanE6());
		 * 
		 * }
		 */

	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub

	}
}
