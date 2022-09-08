package org.craftedsw.tripservicekata.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @Mock private User loggedUser;
    @Mock private UserSession userSession;
    @Mock private TripDAO tripDAO;

    private TripService service;

    @BeforeEach
    void setup() {
        service = new TripService(userSession, tripDAO);
    }

    @Test
    void throwErrorWhenUserIsNotLogged() {
        User user = new User();

        assertThrows(
                UserNotLoggedInException.class,
                () -> service.getTripsByUser(user));
    }

    @Test
    void returnNoTripsIfUserIsNotFriendOfLoggedUser() {
        User user = new User();
        user.addFriend(new User());
        when(userSession.getLoggedUser()).thenReturn(loggedUser);

        assertTrue(service.getTripsByUser(user).isEmpty());
    }

    @Test
    void returnTripsIfUserHasFriends() {
        User user = new User();
        user.addFriend(new User());
        user.addFriend(loggedUser);
        Trip trip = new Trip();

        when(userSession.getLoggedUser()).thenReturn(loggedUser);
        when(tripDAO.findTripsByUser(user)).thenReturn(List.of(trip));

        assertEquals(1, service.getTripsByUser(user).size());
        assertEquals(trip, service.getTripsByUser(user).get(0));
    }


}
