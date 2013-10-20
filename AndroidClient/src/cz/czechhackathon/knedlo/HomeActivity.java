package cz.czechhackathon.knedlo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;

import com.appspot.knedloreader.knedlo.Knedlo;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.nikola.despotoski.drawerlayoutedgetoggle.DrawerLayoutEdgeToggle;

/**
 * Default activity - encapsulates FeedFragment and has a topicDrawer
 * 
 * @author shmoula
 * 
 */
public class HomeActivity extends FragmentActivity {
	private FeedFragment feedFragment;
	private TopicDrawerFragment topicDrawerFragment;
	private DrawerLayout drawerLayout;

	private Knedlo knedloService;	// FukkenGogolEndpoint service
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);
		
		// service creation
		Knedlo.Builder builder = new Knedlo.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		knedloService = builder.build();

		feedFragment = new FeedFragment();
		feedFragment.setKnedloService(knedloService);

		setupTopicDrawer();
	}

	@Override
	protected void onResume() {
		super.onResume();

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.content_frame, feedFragment);
		transaction.commit();
	}

	/**
	 * Drawer initialization
	 */
	private void setupTopicDrawer() {
		drawerLayout = (DrawerLayout) findViewById(R.id.topic_drawer_layout);

		// fragment init
		topicDrawerFragment = new TopicDrawerFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.topic_drawer_frame, topicDrawerFragment);
		transaction.commit();

		// drawer shadow
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// drawer hamburger button
		DrawerLayoutEdgeToggle drawerToggle = new DrawerLayoutEdgeToggle(this,
				drawerLayout, R.drawable.drawer_burger,
				R.drawable.drawer_burger, Gravity.LEFT | Gravity.BOTTOM, true) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				feedFragment.setClickListenerEnabled(true);
			}

			public void onDrawerOpened(View view) {
				super.onDrawerOpened(view);
				feedFragment.setClickListenerEnabled(false);
			}

			public void onDrawerSlide(View view, float slideOffset) {
				super.onDrawerSlide(view, slideOffset);
				feedFragment.setClickListenerEnabled(true);
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);
	}
	
	public void closeDrawer() {
		drawerLayout.closeDrawer(Gravity.LEFT);
	}
}
