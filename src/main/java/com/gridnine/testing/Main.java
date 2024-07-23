package com.gridnine.testing;

import com.gridnine.testing.filters.ArrivalBeforeDepartureFlightFilter;
import com.gridnine.testing.filters.FlightFilter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();
        flights.forEach(System.out::println);

        List<Flight> filteredFlights = filterFlights(flights, new ArrivalBeforeDepartureFlightFilter());

        System.out.println("\nПолеты отфильтрованные от полетов, где дата и время прибытия раньше чем дата и время вылета:");

        filteredFlights.forEach(System.out::println);

        /*System.out.println("\nПолеты отфильтрованные от полетов, где дата и время раньше текущих даты и времени:");

        List<Flight> currentDateAndTimeFlights = filterFlights(flights, new TimeDepartureFlightFilter());

        currentDateAndTimeFlights.forEach(System.out::println);

        System.out.println("\nПолеты отфильтрованные от полетов, где общее время нахождения на земле больше 2 часов:");

        List<Flight> groundTimeFlightsMoreTwoHours = filterFlights(flights, new GroundTimeFlightFilter());

        groundTimeFlightsMoreTwoHours.forEach(System.out::println);

        List<FlightFilter> filters = List.of(new ArrivalBeforeDepartureFlightFilter(),
                new TimeDepartureFlightFilter(),
                new GroundTimeFlightFilter());

        System.out.println("Применение всех фильтров сразу.");

        List<Flight> allFiltersFlights = filterFlights(flights, filters);

        System.out.println(allFiltersFlights);*/

    }

    public static List<Flight> filterFlights(List<Flight> flights, FlightFilter filter) {
        return filter.filter(flights);
    }

    public static List<Flight> filterFlights(List<Flight> flights, List<FlightFilter> filters) {
        List<Flight> filtered = new ArrayList<>();
        for (FlightFilter filter : filters) {
            filtered = filter.filter(flights);
        }
        return filtered;
    }
}