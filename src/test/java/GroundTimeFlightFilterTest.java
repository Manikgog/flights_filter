import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.filters.GroundTimeFlightFilter;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class GroundTimeFlightFilterTest {

    private final FlightFilter filter = new GroundTimeFlightFilter();

    @Test
    public void filterTest() {
        List<Flight> flightList = FlightBuilder.createFlights();

        List<Flight> filteredFlights = filter.filter(flightList);

        Assertions.assertThat(filteredFlights).hasSize(4);
        Assertions.assertThat(List.of(flightList.get(4), flightList.get(5))).isNotIn(filteredFlights);
    }
}
