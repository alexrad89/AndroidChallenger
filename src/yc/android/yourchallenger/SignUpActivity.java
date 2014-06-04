package yc.android.yourchallenger;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		//get rid of action bar
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		Button signUp = (Button) findViewById(R.id.insert_contact);
		
		final String john = "john";
		final String pass = "mavsman";
		
		final DBAdapter db = new DBAdapter(this);
		db.open();
		if(db.open() != null) //checks to make sure database is actually open
		{
			Toast.makeText(this, "database not open", Toast.LENGTH_LONG).show();
		}
	}

	
}
