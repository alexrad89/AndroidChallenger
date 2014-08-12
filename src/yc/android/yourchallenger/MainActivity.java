package yc.android.yourchallenger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
 
	private String [] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	
	//TINDER VARIABLES
	int windowwidth;
	int screenCenter;
	int x_cord, y_cord;
	int Likes = 0;
	RelativeLayout parentView;
	float alphaValue = 0;
	private Context m_context;

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	 public void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_main);
		 
		 //NAV DRAWER BEGIN
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
	       
	     //TINDER-LIKE BEGIN
			 //
				setContentView(R.layout.mainlayout);
				m_context = MainActivity.this;

				parentView = (RelativeLayout) findViewById(R.id.layoutview);
				windowwidth = getWindowManager().getDefaultDisplay().getWidth();
				screenCenter = windowwidth / 2;
				int[] myImageList = new int[] { R.drawable.cats, R.drawable.baby1,
						R.drawable.sachin, R.drawable.cats, R.drawable.puppy };

				for (int i = 0; i < 5; i++) {
					LayoutInflater inflate = (LayoutInflater) m_context
							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

					final View m_view = inflate.inflate(R.layout.custom_layout, null);
					ImageView m_image = (ImageView) m_view.findViewById(R.id.sp_image);
					LinearLayout m_topLayout = (LinearLayout) m_view
							.findViewById(R.id.sp_color);
					LinearLayout m_bottomLayout = (LinearLayout) m_view
							.findViewById(R.id.sp_linh);
					// final RelativeLayout myRelView = new RelativeLayout(this);
					m_view.setLayoutParams(new LayoutParams((windowwidth - 80), 450));
					m_view.setX(40);
					m_view.setY(40);
					m_view.setTag(i);
					m_image.setBackgroundResource(myImageList[i]);

					if (i == 0) {
						m_view.setRotation(-1);
					} else if (i == 1) {
						m_view.setRotation(-5);

					} else if (i == 2) {
						m_view.setRotation(3);

					} else if (i == 3) {
						m_view.setRotation(7);

					} else if (i == 4) {
						m_view.setRotation(-2);

					} else if (i == 5) {
						m_view.setRotation(5);

					}

					// ADD dynamically like button on image.
					final Button imageLike = new Button(m_context);
					imageLike.setLayoutParams(new LayoutParams(100, 50));
					imageLike.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.like));
					imageLike.setX(20);
					imageLike.setY(-250);
					imageLike.setAlpha(alphaValue);
					m_topLayout.addView(imageLike);

					// ADD dynamically dislike button on image.
					final Button imagePass = new Button(m_context);
					imagePass.setLayoutParams(new LayoutParams(100, 50));
					imagePass.setBackgroundDrawable(getResources().getDrawable(
							R.drawable.dislike));

					imagePass.setX(260);
					imagePass.setY(-300);
					imagePass.setAlpha(alphaValue);
					m_topLayout.addView(imagePass);

					// Click listener on the bottom layout to open the details of the
					// image.
					//m_bottomLayout.setOnClickListener(new OnClickListener() {

						//@Override
						//public void onClick(View v) {
							//startActivity(new Intent(m_context, DetailsActivity.class));

						//}
					//});

					// Touch listener on the image layout to swipe image right or left.
					m_topLayout.setOnTouchListener(new OnTouchListener() {

						@Override
						public boolean onTouch(View v, MotionEvent event) {
							x_cord = (int) event.getRawX();
							y_cord = (int) event.getRawY();

							m_view.setX(x_cord - screenCenter + 40);
							m_view.setY(y_cord - 150);
							switch (event.getAction()) {
							case MotionEvent.ACTION_DOWN:
								break;
							case MotionEvent.ACTION_MOVE:
								x_cord = (int) event.getRawX();
								y_cord = (int) event.getRawY();
								m_view.setX(x_cord - screenCenter + 40);
								m_view.setY(y_cord - 150);
								if (x_cord >= screenCenter) {
									m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
									if (x_cord > (screenCenter + (screenCenter / 2))) {
										imageLike.setAlpha(1);
										if (x_cord > (windowwidth - (screenCenter / 4))) {
											Likes = 2;
										} else {
											Likes = 0;
										}
									} else {
										Likes = 0;
										imageLike.setAlpha(0);
									}
									imagePass.setAlpha(0);
								} else {
									// rotate
									m_view.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
									if (x_cord < (screenCenter / 2)) {
										imagePass.setAlpha(1);
										if (x_cord < screenCenter / 4) {
											Likes = 1;
										} else {
											Likes = 0;
										}
									} else {
										Likes = 0;
										imagePass.setAlpha(0);
									}
									imageLike.setAlpha(0);
								}

								break;
							case MotionEvent.ACTION_UP:
								x_cord = (int) event.getRawX();
								y_cord = (int) event.getRawY();

								Log.e("X Point", "" + x_cord + " , Y " + y_cord);
								imagePass.setAlpha(0);
								imageLike.setAlpha(0);

								if (Likes == 0) {
									// Log.e("Event Status", "Nothing");
									m_view.setX(40);
									m_view.setY(40);
									m_view.setRotation(0);
								} else if (Likes == 1) {
									// Log.e("Event Status", "Passed");
									parentView.removeView(m_view);
								} else if (Likes == 2) {

									// Log.e("Event Status", "Liked");
									parentView.removeView(m_view);
								}
								break;
							default:
								break;
							}
							return true;
						}
					});

					parentView.addView(m_view);

				}
			 //TINDER-LIKE END
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
		 Bundle args = new Bundle();
		 args.putInt("key", position);
		 fragment.setArguments(args);
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

