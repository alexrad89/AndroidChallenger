package yc.android.yourchallenger;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		final EditText mUserName = (EditText) findViewById(R.id.user_name);
		final EditText mPassword = (EditText) findViewById(R.id.password);
		
		final DBAdapter db = new DBAdapter(this);
		
		final Button login = (Button) findViewById(R.id.login);
		TextView signup = (TextView) findViewById(R.id.signup);
		
		login.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View view) 
            {
                final String Username = mUserName.getText().toString();
                final String Password=  mPassword.getText().toString();

                //try{  
                    if(Username.length() > 0 && Password.length() >0)  
                    {  
                        DBAdapter dbUser = new DBAdapter(LoginActivity.this);  
                        dbUser.open();  
  
                        if(dbUser.Login(Username, Password))  
                        {  
                            Toast.makeText(LoginActivity.this,"Successfully Logged In", Toast.LENGTH_LONG).show(); 
                            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(myIntent);
                        }else{  
                            Toast.makeText(LoginActivity.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();  
                        }  
                        dbUser.close();  
                    }  
  
                //}catch(Exception e)  
               // {  
                   // Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();  
                //}  
                db.close();


            }
        });
		signup.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				Intent myIntent = new Intent(LoginActivity.this, SignUpActivity.class);
				startActivity(myIntent);
				
			}
		});
	}

	

}
