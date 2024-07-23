package com.gridnine.testing.filters;

import com.gridnine.testing.models.Flight;
import java.util.List;

public interface FlightFilter {
    List<Flight> filter(List<Flight> flights);
}
