package com.juliendelrio.githubdata.data;

import java.util.ArrayList;

public class SearchRequestResult {
	public int total_count;
	public boolean incomplete_results;
	public ArrayList<Repository> items;

	public class Repository {
		public long id;
		public String name;
		public String full_name;
		public String html_url;
		public String description;
		public String url;
		
		@Override
		public String toString() {
			return name;
		}
	}
}
