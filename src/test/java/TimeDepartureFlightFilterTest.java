import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.filters.TimeDepartureFlightFilter;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import java.util.List;

public class TimeDepartureFlightFilterTest {

    private final FlightFilter filter = new TimeDepartureFlightFilter();

    @Test
    public void filterTest() {
        List<Flight> flightList = FlightBuilder.createFlights();

        List<Flight> filteredFlights = filter.filter(flightList);

        Assertions.assertThat(filteredFlights).hasSize(5);
        Assertions.assertThat(flightList.get(2)).isNotIn(filteredFlights);
    }
}
