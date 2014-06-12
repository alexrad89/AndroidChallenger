package yc.android.yourchallenger;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {
 
	private String [] mNavigationDrawerItemTitles;
		 private DrawerLayout mDrawerLayout;
		 private ListView mDrawerList;
		 
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		 // TODO Auto-generated method stub
		 super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_main);
		  
		  mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
		  mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		  mDrawerList = (ListView) findViewById(R.id.left_drawer);
		  
		  ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[3];
		  
		  drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_launcher, "Create");
		  drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_launcher, "Read");
		  drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_launcher, "Help");
		  
		  DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
		  mDrawerList.setAdapter(adapter);
 }
	 
	 public class DrawerItemClickListener implements ListView.OnItemClickListener {
			
			@Override 
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				selectItem(position);
			}
		}

		private void selectItem(int position){
			
			Fragment fragment = null;
			
			switch (position) {
			case 0:
				fragment = new CreateFragment();
				break;
			case 1:
				fragment = new ReadFragment();
				break;
			case 2:
				fragment = new HelpFragment();
				break;
			default:
				break;
			}
			
			if(fragment != null){
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
				
				mDrawerList.setItemChecked(position, true);
				mDrawerList.setSelection(position);
				getActionBar().setTitle(mNavigationDrawerItemTitles[position]);
				mDrawerLayout.closeDrawer(mDrawerList);
			}
			else{
				Log.e("MainActivity", "Error in creating fragment");
			}
		}

}