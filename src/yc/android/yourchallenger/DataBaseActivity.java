package yc.android.yourchallenger;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class DataBaseActivity extends Activity{
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String john = "john";
		String pass = "mavsman";
		DBAdapter db = new DBAdapter(this);
		
		db.open();
		Toast toast = Toast.makeText(this,"database opened", Toast.LENGTH_SHORT);
		toast.show();
		long id = db.insertContact(john, pass);
		Toast inserted = Toast.makeText(this, "Contact inserted", Toast.LENGTH_SHORT);
		inserted.show();
	}
	
	
	
};
