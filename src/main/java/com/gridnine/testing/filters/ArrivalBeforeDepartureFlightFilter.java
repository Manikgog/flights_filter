package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A class for filtering out flights whose arrival time is earlier than the departure.
 */
public class ArrivalBeforeDepartureFlightFilter implements FlightFilter  {
    /**
     * A method for filtering out flights whose arrival time is earlier than the departure time.
     * @param flights - a list of flights to be filtered.
     * @return List<Flight> - a filtered list of Flight
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .collect(Collectors.toList());
    }
}
