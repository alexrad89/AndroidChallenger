package yc.android.yourchallenger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	static final String KEY_ROWID = "id";
	static final String KEY_USER = "user";
	static final String KEY_PASSWORD = "password";
	static final String TAG = "DBAdapter";
	
	static final String DATABASE_NAME = "YCDB";
	static final String KEY_NAME = "users";
	static final int DATABASE_VERSION = 1;
	
	static final String DATABASE_CREATE = 
			"create table contact (" + KEY_USER + " String primary key, "+ KEY_PASSWORD + "String);";
	
	final Context context;
	
	DatabaseHelper DBHelper;
	SQLiteDatabase db;
	
	public DBAdapter (Context ctx)
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + 
					newVersion + ", which will " +
					"destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS contacts");
			onCreate(db);			
		}
	}
		
		//opens db database
		public DBAdapter open() throws SQLException
		{
			db = DBHelper.getWritableDatabase();
			return null;			
		}
		
		//closes the database
		public void close()
		{
			DBHelper.close();
		}
		
		//insert contacts
		public long insertContact(String user, String password )
		{
			ContentValues initialValues = new ContentValues();
			initialValues.put(KEY_NAME, user);
			initialValues.put(KEY_PASSWORD, password);
			return db.insert(DATABASE_NAME, null, initialValues);
		}
		
		//deletes contact
		/*public boolean deleteContact(long rowId)
		{
			return db.delete(DATABASE_NAME, KEY_ROWID + "+" + rowId, null) > 0;
		}
		
		//retrieve contacts
		public Cursor getAllContacts()
		{
			return db.query(DATABASE_NAME, new String[]{KEY_ROWID, KEY_NAME,
					KEY_PASSWORD}, null, null, null, null, null);
		}
		
		//retrieve specified contact
		public Cursor getContact(long rowId) throws SQLException
		{
			Cursor mCursor =
					db.query(true, DATABASE_NAME, new String[] {KEY_ROWID,
					KEY_NAME, KEY_PASSWORD}, KEY_ROWID + "=" + rowId, null,
					null, null, null, null);
			if(mCursor!= null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		public Boolean updatContact(long rowId, String user, String password)
		{
			ContentValues args = new ContentValues();
			args.put(KEY_USER, user);
			args.put(KEY_PASSWORD, password);
			return db.update(DATABASE_NAME, args, KEY_ROWID + "=" + rowId, null) > 0;
		}*/
	}
	

	


	
