package yc.android.yourchallenger;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
 
	private String [] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	
	 public void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_main);
		 
		 mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerList = (ListView) findViewById(R.id.left_drawer);

	        // Set the adapter for the list view
	        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
	                R.layout.nav_drawer, mNavigationDrawerItemTitles));
	        // Set the list's click listener
	        mDrawerList.setOnItemClickListener((OnItemClickListener) new DrawerItemClickListener());
	}
	 
	 private class DrawerItemClickListener implements ListView.OnItemClickListener {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectItem(position);			
			}			
		}
	 
	 private void selectItem(int position) {
		 Fragment fragment = new CreateFragment();
		 Bundle args = new Bundle();
		 args.putInt("key", position);
		 fragment.setArguments(args);
		 
		 FragmentManager fragmentManager = getFragmentManager();
		    fragmentManager.beginTransaction()
		                   .replace(R.id.content_frame, fragment)
		                   .commit();
		 // Highlight the selected item, update the title, and close the drawer
		    mDrawerList.setItemChecked(position, true);
		    setTitle(mNavigationDrawerItemTitles[position]);
		    mDrawerLayout.closeDrawer(mDrawerList);
	 }	 
	 
	 @Override
	 public void setTitle(CharSequence title){
		 mTitle = title;
		 getActionBar().setTitle(mTitle);
	 }
	
	
}

