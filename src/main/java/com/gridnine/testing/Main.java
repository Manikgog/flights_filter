package com.gridnine.testing;

import com.gridnine.testing.filters.ArrivalBeforeDepartureFlightFilter;
import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.filters.GroundTimeFlightFilter;
import com.gridnine.testing.filters.TimeDepartureFlightFilter;
import com.gridnine.testing.models.Flight;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();
        flights.forEach(System.out::println);

        Filter filter = new Filter();

        filter.addFilter(new ArrivalBeforeDepartureFlightFilter());


        List<Flight> filteredFlights = filter.filterFlights(flights);

        System.out.println("\nПолеты отфильтрованные от полетов, где дата и время прибытия раньше чем дата и время вылета:");

        filteredFlights.forEach(System.out::println);

        filter.clear();
        filter.addFilter(new TimeDepartureFlightFilter());

        System.out.println("\nПолеты отфильтрованные от полетов, где дата и время раньше текущих даты и времени:");

        List<Flight> currentDateAndTimeFlights = filter.filterFlights(flights);

        currentDateAndTimeFlights.forEach(System.out::println);

        System.out.println("\nПолеты отфильтрованные от полетов, где общее время нахождения на земле больше 2 часов:");

        filter.clear();
        filter.addFilter(new GroundTimeFlightFilter()); // 2 hours in minutes

        List<Flight> groundTimeFlightsMoreTwoHours = filter.filterFlights(flights);

        groundTimeFlightsMoreTwoHours.forEach(System.out::println);

        List<FlightFilter> filters = List.of(new ArrivalBeforeDepartureFlightFilter(),
                new TimeDepartureFlightFilter(),
                new GroundTimeFlightFilter());

        System.out.println("\nПрименение всех фильтров сразу.");

        filter.clear();
        filter.addFilters(filters);

        List<Flight> allFiltersFlights = filter.filterFlights(flights);

        allFiltersFlights.forEach(System.out::println);

    }


}