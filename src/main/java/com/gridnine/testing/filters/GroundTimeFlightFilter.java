package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroundTimeFlightFilter implements FlightFilter{

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
                if(duration > 2) {
                    break;  // if duration is more than 2 hours, break the loop
                }
            }
            if (duration <= 2) {
                listLessTwoHours.add(flight);
            }
        }
        return listLessTwoHours;
    }
}
