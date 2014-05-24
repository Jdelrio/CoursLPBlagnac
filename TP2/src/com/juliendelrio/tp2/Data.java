package com.juliendelrio.tp2;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.util.Log;

import com.juliendelrio.githubdata.GithubRestClient;
import com.juliendelrio.githubdata.GithubRestClient.IGithubRestClient;
import com.juliendelrio.githubdata.data.UserRepository;

public class Data {
	private static String TAG = Data.class.getSimpleName();
	private static Data singleton;
	private ArrayList<UserRepository> lastRepositoriesList;
	private IGithubRestClient githubRestClient;

	public static Data getInstance() {
		if (singleton == null) {
			singleton = new Data();
		}
		return singleton;
	}

	private Data() {
		lastRepositoriesList = new ArrayList<UserRepository>();
		githubRestClient = GithubRestClient.create();
	}

	public ArrayList<UserRepository> getLastRepositoriesList() {
		return lastRepositoriesList;
	}

	public void updateLastRepositoriesList(
			final UpdateLastRepositoriesListListener listener) {
		githubRestClient.listGetUserRepositoriesList("JulienDelRio",
				new Callback<List<UserRepository>>() {

					@Override
					public void success(List<UserRepository> result,
							Response response) {
						updateLastRepositoriesList(result);
						listener.onSucceeded();
					}

					@Override
					public void failure(RetrofitError e) {
						Log.d(TAG, "Load list search error", e);
						listener.onFailed(e);
					}
				});
	}

	public void updateLastRepositoriesList(
			List<UserRepository> list) {
		lastRepositoriesList.clear();
		lastRepositoriesList.addAll(list);
	}

	public interface UpdateLastRepositoriesListListener {
		public void onSucceeded();

		public void onFailed(Throwable error);
	}

}
