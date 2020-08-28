package problems.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement the class UndergroundSystem that supports three methods:
 *
 * 1. checkIn(int id, string stationName, int t)
 *
 * A customer with id card equal to id, gets in the station stationName at time t.
 * A customer can only be checked into one place at a time.
 * 2. checkOut(int id, string stationName, int t)
 *
 * A customer with id card equal to id, gets out from the station stationName at time t.
 * 3. getAverageTime(string startStation, string endStation)
 *
 * Returns the average time to travel between the startStation and the endStation.
 * The average time is computed from all the previous traveling from startStation to endStation that happened directly.
 * Call to getAverageTime is always valid.
 * You can assume all calls to checkIn and checkOut methods are consistent.
 * That is, if a customer gets in at time t1 at some station, then it gets out at time t2 with t2 > t1. All events happen in chronological order.
 */
public class UndergroundSystem {
    /**
     * When asked in real interview, discuss real-world issues related to this. like we wouldn't want to store this info in memory only
     * in case computer loses power, we need to store data in permanent medium like database
     * Also discuss scalability - in large cities like NY, where the metro system gets millions of trips per day. we need to calculate times
     * fast.
     */

    Map<Integer, PassengerCheckIn> passengerTrip;
    Map<String, RouteStatistic> tripMap;

    public UndergroundSystem() {
        passengerTrip = new HashMap<>();
        tripMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        passengerTrip.put(id, new PassengerCheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        PassengerCheckIn trip = passengerTrip.get(id);
        String route = trip.boardingStation + "_" + stationName;
        RouteStatistic routeStatistic = tripMap.getOrDefault(route, new RouteStatistic());
        routeStatistic.update(t - trip.boardingTime);
        tripMap.put(route, routeStatistic);
        passengerTrip.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        RouteStatistic trip = tripMap.get(startStation + "_" + endStation);
        return trip.totalTimeTaken / trip.numberOfCompletedTrips;
    }

    // * * * * * * * * * * * Wrapper classes * * * * * * * * * * * * * * * * *
    static class PassengerCheckIn {
        String boardingStation;
        double boardingTime;

        public PassengerCheckIn(String fromStation, double t) {
            boardingStation = fromStation;
            boardingTime = t;
        }
    }

    static class RouteStatistic {
        double numberOfCompletedTrips;
        double totalTimeTaken;

        public void update(double timeTakenInThisTrip) {
            totalTimeTaken += timeTakenInThisTrip;
            numberOfCompletedTrips++;
        }
    }
}
