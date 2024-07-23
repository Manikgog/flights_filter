import com.gridnine.testing.models.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.filters.ArrivalBeforeDepartureFlightFilter;
import com.gridnine.testing.filters.FlightFilter;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import java.util.List;

public class ArrivalBeforeDepartureFlightFilterTest {

    private final FlightFilter filter = new ArrivalBeforeDepartureFlightFilter();


    @Test
    public void filterTest() {
        List<Flight> flightList = FlightBuilder.createFlights();

        List<Flight> filteredFlights = filter.filter(flightList);

        Assertions.assertThat(filteredFlights).hasSize(5);
        Assertions.assertThat(flightList.get(3)).isNotIn(filteredFlights);
    }
}
