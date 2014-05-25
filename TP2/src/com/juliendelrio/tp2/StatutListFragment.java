package com.juliendelrio.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juliendelrio.githubdata.GithubApi;
import com.juliendelrio.githubdata.GithubApi.UpdateLastRepositoriesListListener;
import com.juliendelrio.githubdata.data.SearchRequestResult;
import com.juliendelrio.githubdata.data.UserRepository;

/**
 * A list fragment representing a list of Statuts. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link StatutDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class StatutListFragment extends Fragment {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks mCallbacks = sDummyCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	private ListView listView;

	private EditText editTextSearch;

	private ImageButton imageButtonSearch;

	private ProgressBar progressBarSearch;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public StatutListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO: replace with a real list adapter.
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_statut_list, container,
				false);
		listView = (ListView) view.findViewById(android.R.id.list);
		listView.setAdapter(new ArrayAdapter<UserRepository>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, GithubApi.getInstance()
						.getLastRepositoriesList()));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCallbacks.onItemSelected(Integer.toString(position));
			}
		});
		editTextSearch = (EditText) view.findViewById(R.id.et_search);
		imageButtonSearch = (ImageButton) view
				.findViewById(R.id.ib_search);
		imageButtonSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateList();
			}
		});
		progressBarSearch = (ProgressBar) view.findViewById(R.id.pb_search);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private ListView getListView() {
		return listView;
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}

	@Override
	public void onResume() {
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		updateList();
		super.onResume();
	}

	private void updateList() {
		progressBarSearch.setVisibility(View.VISIBLE);
		String searchText = editTextSearch.getText().toString();
		searchText.trim();
		if ("".equals(searchText)){
			searchText = "JulienDelRio";
		}
		GithubApi.getInstance().updateLastRepositoriesList(searchText,
				new UpdateLastRepositoriesListListener() {

					@Override
					public void onSucceeded() {
						((ArrayAdapter<SearchRequestResult.Repository>) getListView()
								.getAdapter()).notifyDataSetChanged();
						progressBarSearch.setVisibility(View.GONE);
					}

					@Override
					public void onFailed(Throwable error) {
						Toast.makeText(getActivity(), "Get list error",
								Toast.LENGTH_SHORT).show();
						progressBarSearch.setVisibility(View.GONE);
					}
				});
	}
}
