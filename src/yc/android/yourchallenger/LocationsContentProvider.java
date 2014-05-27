package yc.android.yourchallenger;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class LocationsContentProvider extends ContentProvider {

	private SqlLiteHelper dbHelper;

	private static final int LOCATIONS = 1;

	private static final String AUTHORITY = "yc.android.yourchallenger.locationscontentprovider";

	private static final String BASE_PATH = "location";

	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + BASE_PATH);

	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/locations";

	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, LOCATIONS);

	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub

		SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
		int rowsDeleted = 0;

		rowsDeleted = sqlDB.delete(SqlLiteHelper.TABLE_LOC, selection,
				selectionArgs);

		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
		long id = 0;
		id = sqlDB.insert(SqlLiteHelper.TABLE_LOC, null, values);
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub

		dbHelper = new SqlLiteHelper(getContext());
		return false;
	}

	private void checkColumns(String[] projection) {
		String[] available = { SqlLiteHelper.COLUMN_ID,
				SqlLiteHelper.COLUMN_DEVICEID, SqlLiteHelper.COLUMN_LAT,
				SqlLiteHelper.COLUMN_LONG };
		if (projection != null) {
			HashSet<String> requestedColumns = new HashSet<String>(
					Arrays.asList(projection));
			HashSet<String> availableColumns = new HashSet<String>(
					Arrays.asList(available));
			// Check if all columns which are requested are available
			if (!availableColumns.containsAll(requestedColumns)) {
				throw new IllegalArgumentException(
						"Unknown columns in projection");
			}
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		// Using SQLiteQueryBuilder instead of query() method
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		// Check if the caller has requested a column which does not exists
		checkColumns(projection);

		// Set the table
		queryBuilder.setTables(SqlLiteHelper.TABLE_LOC);

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		// Make sure that potential listeners are getting notified
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub

		SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
		int rowsUpdated = 0;

		rowsUpdated = sqlDB.update(SqlLiteHelper.TABLE_LOC, values, selection,
				selectionArgs);

		getContext().getContentResolver().notifyChange(uri, null);
		return rowsUpdated;
	}

}
