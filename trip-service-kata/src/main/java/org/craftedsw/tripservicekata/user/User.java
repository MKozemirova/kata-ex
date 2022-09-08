package org.craftedsw.tripservicekata.user;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.trip.Trip;

public class User {

	private final List<Trip> trips = new ArrayList<>();
	private final List<User> friends = new ArrayList<>();

	public void addFriend(User user) {
		friends.add(user);
	}

	public List<User> getFriends() {
		return friends;
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}
	
	public List<Trip> trips() {
		return trips;
	}

}
