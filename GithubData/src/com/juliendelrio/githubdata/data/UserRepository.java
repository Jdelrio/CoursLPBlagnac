package com.juliendelrio.githubdata.data;


public class UserRepository {

	public int id;
	public String name;
	public String full_name;

	@Override
	public String toString() {
		return name;
	}
}
