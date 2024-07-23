import com.gridnine.testing.Filter;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.filters.ArrivalBeforeDepartureFlightFilter;
import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.filters.GroundTimeFlightFilter;
import com.gridnine.testing.filters.TimeDepartureFlightFilter;
import com.gridnine.testing.models.Flight;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class FilterTest {

    private Filter filter = new Filter();

    /**
     * Test for filterFlights method.
     */
    @Test
    public void filterFlights_Test() {
        List<Flight> flightList = FlightBuilder.createFlights();

        filter.addFilter(new ArrivalBeforeDepartureFlightFilter());
        filter.addFilter(new TimeDepartureFlightFilter());
        filter.addFilter(new GroundTimeFlightFilter(3));

        List<Flight> filteredFlights = filter.filterFlights(flightList);

        List<Flight> result = new ArrayList<>(flightList);
        List<Flight> expectedFlights = new ArrayList<>(flightList);
        for (FlightFilter filter : List.of(new ArrivalBeforeDepartureFlightFilter(),
                                            new TimeDepartureFlightFilter(),
                                            new GroundTimeFlightFilter(3))) {
            result = filter.filter(expectedFlights);
            expectedFlights = new ArrayList<>(result);
        }

        Assertions.assertThat(filteredFlights).hasSize(expectedFlights.size());
        Assertions.assertThat(filteredFlights).isEqualTo(expectedFlights);
    }


    /**
     * Positive test for addFilter method.
     */
    @Test
    public void addFilter_positiveTest() {
        filter.addFilter(new ArrivalBeforeDepartureFlightFilter());
        Assertions.assertThat(filter.getFilters()).hasSize(1);

        filter.addFilters(List.of(new TimeDepartureFlightFilter(), new GroundTimeFlightFilter()));
        Assertions.assertThat(filter.getFilters()).hasSize(3);
    }


    /**
     * Negative test for addFilter method.
     */
    @Test
    public void addFilter_negativeTest() {
        filter.addFilter(new ArrivalBeforeDepartureFlightFilter());
        filter.addFilter(new TimeDepartureFlightFilter());

        Assertions.assertThatThrownBy(() -> filter.addFilter(new ArrivalBeforeDepartureFlightFilter()));
    }


    /**
     * Positive test for addFilters method.
     */
    @Test
    public void addFilters_positiveTest() {
        filter.addFilters(List.of(new TimeDepartureFlightFilter(), new GroundTimeFlightFilter()));
        Assertions.assertThat(filter.getFilters()).hasSize(2);
    }


    /**
     * Negative test for addFilters method.
     */
    @Test
    public void addFilters_negativeTest() {
        filter.addFilters(List.of(new TimeDepartureFlightFilter(), new GroundTimeFlightFilter()));
        Assertions.assertThatThrownBy(() -> filter.addFilters(List.of(new TimeDepartureFlightFilter())));
    }

    /**
     * Test for clear method.
     */
    @Test
    public void clear_Test() {
        filter.addFilters(List.of(new TimeDepartureFlightFilter(), new GroundTimeFlightFilter()));
        filter.clear();
        Assertions.assertThat(filter.getFilters()).isEmpty();
    }
}
