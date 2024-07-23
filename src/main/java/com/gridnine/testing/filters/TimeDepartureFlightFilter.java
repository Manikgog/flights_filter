package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TimeDepartureFlightFilter implements FlightFilter {
    /**
     * A method for filtering out flights whose departure time is earlier than the current time.
     * @param flights - a list of flights to be filtered.
     * @return List<Flight> - a filtered list of Flight
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate()
                                .isAfter(LocalDateTime.now().truncatedTo(TimeUnit.MINUTES.toChronoUnit()))))
                .collect(Collectors.toList());
    }
}
