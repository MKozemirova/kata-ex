package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;


public class TripService {

	private final UserSession userSession;
	private final TripDAO tripDAO;

	public TripService(UserSession userSession, TripDAO tripDAO) {
		this.userSession = userSession;
		this.tripDAO = tripDAO;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = userSession.getLoggedUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}

		if (user.getFriends().contains(loggedUser)) {
			return tripDAO.findTripsByUser(user);
		}
		return new ArrayList<>();
	}
	
}
