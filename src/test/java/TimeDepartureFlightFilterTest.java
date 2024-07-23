import com.gridnine.testing.models.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.filters.TimeDepartureFlightFilter;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeDepartureFlightFilterTest {

    private final FlightFilter filter = new TimeDepartureFlightFilter();

    @Test
    public void filterTest() {
        List<Flight> flightList = FlightBuilder.createFlights();

        List<Flight> filteredFlights = filter.filter(flightList);

        List<Flight> expectedFlights = flightList.stream()
                .filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate()
                                .isAfter(LocalDateTime.now().truncatedTo(TimeUnit.MINUTES.toChronoUnit()))))
                .toList();

        List<Flight> excludedFlights = flightList.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate()
                                .isBefore(LocalDateTime.now().truncatedTo(TimeUnit.MINUTES.toChronoUnit()))))
                .toList();

        Assertions.assertThat(filteredFlights).isEqualTo(expectedFlights);
        Assertions.assertThat(excludedFlights).isNotIn(filteredFlights);
    }
}
