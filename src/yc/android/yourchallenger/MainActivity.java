package yc.android.yourchallenger;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
 
	private String [] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;	
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
	        
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                mDrawerLayout,         /* DrawerLayout object */
	                R.drawable.ic_launcher,  /* nav drawer icon to replace 'Up' caret */
	                R.string.drawer_open,  /* "open drawer" description */
	                R.string.drawer_close  /* "close drawer" description */
	                ) {

	            /** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	                super.onDrawerClosed(view);
	                getActionBar().setTitle(mTitle);
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                super.onDrawerOpened(drawerView);
	                getActionBar().setTitle(mDrawerTitle);
	            }
	        };

	        // Set the drawer toggle as the DrawerListener
	        mDrawerLayout.setDrawerListener(mDrawerToggle);

	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        getActionBar().setHomeButtonEnabled(true);	       
	}
	 
	 @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        mDrawerToggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Pass the event to ActionBarDrawerToggle, if it returns
	        // true, then it has handled the app icon touch event
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }
	        // Handle your other action bar items...

	        return super.onOptionsItemSelected(item);
	    }
	 
	 private class DrawerItemClickListener implements ListView.OnItemClickListener {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectItem(position);			
			}			
		}
	 
	 private void selectItem(int position) {
		 Fragment fragment = null;
		 
		 switch (position){
		 case 0: fragment = new CreateFragment();
		 break;
		 
		 case 1: fragment = new ReadFragment();
		 break;
		 
		 case 2: fragment = new HelpFragment();
		 break;
		 
		 default:
			 break;
		 }
		/* Bundle args = new Bundle();
		 args.putInt("key", position);
		 fragment.setArguments(args);*/
		if(fragment != null){ 
			 FragmentManager fragmentManager = getFragmentManager();
			    fragmentManager.beginTransaction()
			                   .replace(R.id.content_frame, fragment)
			                   .commit();
			 // Highlight the selected item, update the title, and close the drawer
			    mDrawerList.setItemChecked(position, true);
			    setTitle(mNavigationDrawerItemTitles[position]);
			    mDrawerLayout.closeDrawer(mDrawerList);
		}
	 }	 
	 
	 @Override
	 public void setTitle(CharSequence title){
		 mTitle = title;
		 getActionBar().setTitle(mTitle);
	 }
	
	
}

