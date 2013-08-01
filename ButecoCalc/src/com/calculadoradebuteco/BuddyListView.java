package com.calculadoradebuteco;

public class BuddyListView {

	private String Buddy;

	public BuddyListView() {
		this("");
	}

	public BuddyListView(String buddy) {
		super();
		Buddy = buddy;
	}

	public String getBuddy() {
		return Buddy;
	}

	public void setBuddy(String buddy) {
		Buddy = buddy;
	}

}
