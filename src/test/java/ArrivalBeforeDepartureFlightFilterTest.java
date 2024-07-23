import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.filters.ArrivalBeforeDepartureFlightFilter;
import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.models.Flight;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import java.util.List;

public class ArrivalBeforeDepartureFlightFilterTest {

    private final FlightFilter filter = new ArrivalBeforeDepartureFlightFilter();


    @Test
    public void filterTest() {
        List<Flight> flightList = FlightBuilder.createFlights();

        List<Flight> filteredFlights = filter.filter(flightList);

        List<Flight> expectedFlights = flightList.stream().filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(segment.getArrivalDate())))
                .toList();

        Assertions.assertThat(filteredFlights).hasSize(expectedFlights.size());
        Assertions.assertThat(filteredFlights).isEqualTo(expectedFlights);
    }
}
