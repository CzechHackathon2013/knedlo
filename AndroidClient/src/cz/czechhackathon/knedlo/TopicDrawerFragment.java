package cz.czechhackathon.knedlo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import cz.czechhackathon.knedlo.SectionActivity.SectionIdentificator;
import cz.czechhackathon.knedlo.adapter.BadgeAdapter;

/**
 * Drawer with topic buttons and feed filtering
 * Opens inside HomeActivity from the left
 * @author shmoula
 *
 */
public class TopicDrawerFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_drawer_topic, container, false);
		
		GridView badgeGrid = (GridView) view.findViewById(R.id.badge_grid);
		badgeGrid.setAdapter(new BadgeAdapter(getActivity().getApplicationContext()));
		
		// section banners click listeners
		// TODO: should be generated dynamicaly (maybe inside ListView)
		ImageView btnMovieZone = (ImageView) view.findViewById(R.id.button_section_movie_zone);
		btnMovieZone.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goToSectionView(SectionIdentificator.SECTION_MZ);
			}
		});
		
		ImageView btnSuper = (ImageView) view.findViewById(R.id.button_section_super);
		btnSuper.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goToSectionView(SectionIdentificator.SECTION_SUPER);
			}
		});
		
		ImageView btnRespekt = (ImageView) view.findViewById(R.id.button_section_respekt);
		btnRespekt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				goToSectionView(SectionIdentificator.SECTION_RESPEKT);
			}
		});
		
		return view;
	}

	/**
	 * Opens section feed activity for desired section
	 */
	protected void goToSectionView(SectionIdentificator sectionMz) {
		Intent intent = new Intent(getActivity().getBaseContext(), SectionActivity.class);
		intent.putExtra(SectionActivity.EXTRA_SECTION, sectionMz);
		startActivity(intent);
	}
}
