package com.juliendelrio.tp3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.juliendelrio.githubdata.GithubApi;
import com.juliendelrio.githubdata.data.UserRepository;
import com.juliendelrio.tp3.R;
import com.squareup.picasso.Picasso;

/**
 * A fragment representing a single Repo detail screen. This fragment is
 * either contained in a {@link RepoListActivity} in two-pane mode (on
 * tablets) or a {@link RepoDetailActivity} on handsets.
 */
public class RepoDetailFragment extends Fragment {
	private final SimpleDateFormat dateFormatRead = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	private final SimpleDateFormat dateFormatWrite = new SimpleDateFormat(
			"dd-MM-yyyy HH:mm");
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";
	private static final String TAG = RepoDetailFragment.class
			.getSimpleName();

	/**
	 * The dummy content this fragment is presenting.
	 */
	private UserRepository mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public RepoDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = GithubApi
					.getInstance()
					.getLastRepositoriesList()
					.get(Integer
							.parseInt(getArguments().getString(ARG_ITEM_ID)));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_statut_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.statut_detail))
					.setText(mItem.name);
			((TextView) rootView.findViewById(R.id.description))
					.setText(mItem.description);
			((TextView) rootView.findViewById(R.id.owner))
					.setText(mItem.owner.login);
			try {
				Date date = dateFormatRead.parse(mItem.created_at);
				((TextView) rootView.findViewById(R.id.created))
						.setText(dateFormatWrite.format(date));
			} catch (ParseException e) {
				Log.e(TAG, "Error parsing date", e);
			}

			ImageView imageView = ((ImageView) rootView
					.findViewById(R.id.imageView_owner));
			Picasso.with(inflater.getContext()).load(mItem.owner.avatar_url

			).into(imageView);
			((Button) rootView.findViewById(R.id.seeOnWebsite))
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setData(Uri.parse(mItem.html_url));
							getActivity().startActivity(intent);

						}
					});
			;
		}

		return rootView;
	}
}
