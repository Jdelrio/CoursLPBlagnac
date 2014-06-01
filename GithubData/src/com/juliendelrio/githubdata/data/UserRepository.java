package com.juliendelrio.githubdata.data;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

	public int id;
	public String name;
	public String full_name;
	public Owner owner;
	public String html_url;
	public String description;
	public boolean fork;
	public String url;
	public String forks_url;
	public String keys_url;
	public String collaborators_url;
	public String created_at;
	public String updated_at;
	public String pushed_at;
	public String homepage;
	public int size;
	public List<RepoBranch> branches = new ArrayList<RepoBranch>();

	@Override
	public String toString() {
		return name;
	}

	public class Owner {
		public String login;
		public int id;
		public String avatar_url;
		public String gravatar_id;
		public String url;
		public String html_url;
		public String followers_url;
		public String following_url;
		public String gists_url;
		public String starred_url;
		public String subscriptions_url;
		public String organizations_url;
		public String repos_url;
		public String events_url;
		public String received_events_url;
		public String type;
		public boolean site_admin;
	}
}
