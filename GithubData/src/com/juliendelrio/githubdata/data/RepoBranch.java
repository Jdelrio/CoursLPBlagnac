package com.juliendelrio.githubdata.data;

public class RepoBranch {
	public String name;
	public Commit commit;

	public class Commit {
		public String sha;
		public String url;
	}
}
