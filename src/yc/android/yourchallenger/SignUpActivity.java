package yc.android.yourchallenger;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		//get rid of action bar
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		final EditText mUserName = (EditText) findViewById(R.id.create_username);
		final EditText mPassword = (EditText) findViewById(R.id.create_password);
		Button register = (Button) findViewById(R.id.register);
		final DBAdapter db = new DBAdapter(this);
		
		register.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				String username = mUserName.getText().toString();
				String password = mPassword.getText().toString();
				db.open();
				
				db.insertContact(username, password);
				
				
				Intent myIntent = new Intent(SignUpActivity.this, LoginActivity.class);
				Toast.makeText(SignUpActivity.this, "you have succesfully created an account", Toast.LENGTH_LONG).show();
				startActivity(myIntent);				
			}
        });		
	}	
}


