package cz.czechhackathon.knedlo.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cz.czechhackathon.knedlo.R;
import cz.czechhackathon.knedlo.domain.FeedItem;

/**
 * Adapter which holds FeedItems and its references to surounding view
 * @author shmoula
 *
 */
public class FeedAdapter extends ArrayAdapter<FeedItem> {
	private final Activity context;
	private List<FeedItem> items;
	private int itemHeight;
	
	public FeedAdapter(Activity context, List<FeedItem> items) {
		super(context, R.layout.rowlayout_feed_item, items);

		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflator = context.getLayoutInflater();
		final View view = inflator.inflate(R.layout.rowlayout_feed_item, null);

		// observer for getting feed item height
		view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				itemHeight = view.getHeight();
			}
		});

		// new item creation and keeping reference to surrounding view (for future content refresh)
		FeedItem item = items.get(position);
		item.setBoundView(view);

		repaintComponents(item);

		return view;
	}
	
	

	/**
	 * Repaints component of desired view - fills text and show/hide appropriate
	 * edge icon
	 * 
	 * @param item
	 * @param edgeButton
	 */
	private void repaintComponents(FeedItem item) {
		View view = item.getBoundView();

		((TextView)view.findViewById(R.id.feed_item_title)).setText(item.getTitle());
		
		if(item.getImageUrl() == null)
			view.findViewById(R.id.feed_item_thumbnail).setVisibility(View.GONE);
	}

	/**
	 * Returns vertical position of list item on position
	 * @param position	Index of item in list
	 * @return
	 */
	public int getVerticalItemLocationOnScreen(int position) {
		FeedItem item = items.get(position);

		int[] location = new int[2];
		item.getBoundView().getLocationOnScreen(location);

		return location[1];
	}

	/**
	 * Returns height of list item
	 * @return
	 */
	public int getItemHeight() {
		return itemHeight;
	}

}
