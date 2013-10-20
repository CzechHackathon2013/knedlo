package cz.czechhackathon.knedlo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import cz.czechhackathon.knedlo.R;

/**
 * Adapter which holds user badges
 * @author shmoula
 *
 */
public class BadgeAdapter extends BaseAdapter {
	private Context context;

	public BadgeAdapter(Context context) {
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(context);

		imageView.setLayoutParams(new GridView.LayoutParams(92, 92));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setPadding(8, 8, 8, 8);

		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}

	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private Integer[] mThumbIds = {
			R.drawable.badge, R.drawable.badge,
			R.drawable.badge, R.drawable.badge,
			R.drawable.badge, R.drawable.badge,
			R.drawable.badge, R.drawable.badge};
}
