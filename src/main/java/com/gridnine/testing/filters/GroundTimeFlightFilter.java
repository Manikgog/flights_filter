package com.gridnine.testing.filters;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for filtering out flights that have a total time on the ground of more than groundTimeLimit.
 */
public class GroundTimeFlightFilter implements FlightFilter{
    // The limit of the total time spent on earth
    private final int groundTimeLimit;

    public GroundTimeFlightFilter() {
        this.groundTimeLimit = 2;
    }  // default limit is 2 hours for ground time (in hours)  // constructor for customizable limit  // constructor for default limit 2 hours for ground time (in hours)  // constructor for customizable limit 2 hours for ground time (in hours)  // constructor for default limit 2 hours for ground time (in hours)  // constructor for customizable limit 2 hours for ground time (in

    public GroundTimeFlightFilter(int groundTimeLimit) {
        this.groundTimeLimit = groundTimeLimit;
    }

    /**
     * A method for filtering out flights that have a total time on the ground of more than 2 hours.
     * @param flights - a list of flights to be filtered.
     * @return List<Flight> - a filtered list of Flight
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> listLessTwoHours = new ArrayList<>();
        for (Flight flight : flights) {
            List<Segment> segments = flight.getSegments();
            long duration = 0;
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime depTime = segments.get(i + 1).getDepartureDate();
                LocalDateTime arrTime = segments.get(i).getArrivalDate();
                duration += Duration.between(arrTime, depTime).toHours();
                if(duration > groundTimeLimit) {
                    break;  // if duration is more than groundTimeLimit hours, break the loop
                }
            }
            if (duration <= groundTimeLimit) {
                listLessTwoHours.add(flight);
            }
        }
        return listLessTwoHours;
    }
}
