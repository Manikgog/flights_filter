package com.gridnine.testing;

import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.models.Flight;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private final List<FlightFilter> filters;

    public Filter(){
        this.filters = new ArrayList<>();
    }

    public Filter(List<FlightFilter> filters) {
        this.filters = filters;
    }

    public void addFilter(FlightFilter filter) {
        filters.add(filter);
    }

    public void addFilters(List<FlightFilter> filtersFlights) {
        filters.addAll(filtersFlights);
    }

    public void clear(){
        filters.clear();
    }

    public List<Flight> filterFlights(List<Flight> flights) {
        List<Flight> result = new ArrayList<>(flights);
        List<Flight> flightsAfterFilters = new ArrayList<>(flights);
        for (FlightFilter filter : filters) {
            result = filter.filter(flightsAfterFilters);
            flightsAfterFilters = new ArrayList<>(result);
        }
        return result;
    }
}
