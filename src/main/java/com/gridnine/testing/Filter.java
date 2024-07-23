package com.gridnine.testing;

import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.models.Flight;
import java.util.ArrayList;
import java.util.List;

/**
 * Flight filtering class
 */
public class Filter {
    private final List<FlightFilter> filters;

    public Filter(){
        this.filters = new ArrayList<>();
    }

    /**
     * A constructor for creating a filter with a given list of filters
     * @param filters - list of filters to init list of filters
     */
    public Filter(List<FlightFilter> filters) {
        this.filters = filters;
    }

    /**
     * A method for adding a single filter
     * @param filter - filter to add to the list of filters
     */
    public void addFilter(FlightFilter filter) {
        filters.add(filter);
    }

    /**
     * A method for adding multiple filters at once
     * @param filtersFlights - list of filters to add
     */
    public void addFilters(List<FlightFilter> filtersFlights) {
        filters.addAll(filtersFlights);
    }

    /**
     * A method for clearing all filters
     */
    public void clear(){
        filters.clear();
    }

    /**
     * A method for filtering flights
     * @param flights - list of flights to filter
     * @return filtered list of flights
     */
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
