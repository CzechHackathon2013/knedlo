package cz.czechhackathon.knedlo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.nikola.despotoski.drawerlayoutedgetoggle.DrawerLayoutEdgeToggle;

import cz.czechhackathon.knedlo.domain.FeedItem;

/**
 * Activity with article content; also prepares "bonusDrawer"
 * @author shmoula
 *
 */
public class DetailActivity extends FragmentActivity {
	public static final String EXTRA_ITEM = "item";
	
	private BonusDrawerFragment bonusDrawerFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_detail);
		
		Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
        	FeedItem item = (FeedItem) bundle.getSerializable(EXTRA_ITEM);
        	((TextView) findViewById(R.id.title)).setText(item.getTitle());
        	((TextView) findViewById(R.id.perex)).setText(item.getPerex());
        	((TextView) findViewById(R.id.text)).setText(item.getText());
        }
        
        // TODO: get that back
        // setupBonusDrawer();
	}

	/**
	 * Prepares drawer
	 * It's a litle bit special here - it hides handler when sliding and when drawer is opened
	 */
	private void setupBonusDrawer() {
		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.bonus_drawer_layout);

		// fragment init
		// TODO: drawer temporarily removed due to "zomg deadline comming" problems
		/**
		bonusDrawerFragment = new BonusDrawerFragment();
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.bonus_drawer_frame, bonusDrawerFragment);
		transaction.commit();
		**/

		// drawer shadow
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.END);

		// drawer hamburger button
		DrawerLayoutEdgeToggle drawerToggle = new DrawerLayoutEdgeToggle(this,
				drawerLayout, R.drawable.drawe_eye,
				R.drawable.drawe_eye, Gravity.RIGHT | Gravity.CENTER_VERTICAL, true) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				resetHandlePosition();
			}

			public void onDrawerOpened(View view) {
				super.onDrawerOpened(view);
			}

			public void onDrawerSlide(View view, float slideOffset) {
				super.onDrawerSlide(view, slideOffset);
				hideHandle();
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);
	}
}
