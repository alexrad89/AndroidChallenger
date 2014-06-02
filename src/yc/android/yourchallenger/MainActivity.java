package yc.android.yourchallenger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
	@Override 
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button insert = (Button) findViewById(R.id.insert_contact);
		
		final String john = "john";
		final String pass = "mavsman";
		
		final DBAdapter db = new DBAdapter(this);
		db.open();
		if(db.open() != null)
		{
			Toast.makeText(this, "database not open", Toast.LENGTH_LONG).show();
		}
		
		insert.setOnClickListener(new View.OnClickListener() 
		{		
			@Override
			public void onClick(View arg0)
			{
				db.insertContact(john, pass);
				
			}
			
		});	
	}
};
