package yc.android.yourchallenger;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		final EditText userName = (EditText) findViewById(R.id.user_name);
		final EditText password = (EditText) findViewById(R.id.password);
		
		final DBAdapter db = new DBAdapter(this);
		
		Button login = (Button) findViewById(R.id.login);
		
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db.open();				
				db.insertContact(userName.toString(), password.toString());
			}
		});
	}

	

}
