package cz.czechhackathon.knedlo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Drawer with bonus item - viewed on the right when details activity is displayed
 * @author shmoula
 *
 */
public class BonusDrawerFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_drawer_bonus, container, false);
		
		return view;
	}
}
