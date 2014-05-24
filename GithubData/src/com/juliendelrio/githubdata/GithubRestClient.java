package com.juliendelrio.githubdata;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import com.juliendelrio.githubdata.data.SearchRequestResult;
import com.juliendelrio.githubdata.data.UserRepository;

public class GithubRestClient {
	private static final String HTTPS_API_GITHUB_COM = "https://api.github.com";

	public static IGithubRestClient create() {
		return new RestAdapter.Builder().setEndpoint(HTTPS_API_GITHUB_COM)
				.build().create(IGithubRestClient.class);
	}

	public interface IGithubRestClient {
		@GET("/" + RequestTypes.SEARCH + "/repositories")
		List<SearchRequestResult> listReposForSearch(
				@Query("q") String searchTerm);

		@GET("/" + RequestTypes.SEARCH + "/repositories")
		void listReposForSearch(@Query("q") String searchTerm,
				Callback<SearchRequestResult> cb);

		@GET("/" + RequestTypes.USERS + "/{user}/repos")
		List<UserRepository> listGetUserRepositoriesList(
				@Path("user") String user);

		@GET("/" + RequestTypes.USERS + "/{user}/repos")
		void listGetUserRepositoriesList(@Path("user") String user,
				Callback<List<UserRepository>> cb);
	}

	public final class RequestTypes {
		private RequestTypes() {
		}

		public static final String ISSUES = "issues";
		public static final String REPOS = "repos";
		public static final String USER = "user";
		public static final String USERS = "users";
		public static final String ORGS = "orgs";
		public static final String SEARCH = "search";

	}
}
