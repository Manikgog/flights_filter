import com.gridnine.testing.models.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.filters.GroundTimeFlightFilter;
import com.gridnine.testing.models.Segment;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroundTimeFlightFilterTest {

    private final FlightFilter filter = new GroundTimeFlightFilter();

    @Test
    public void filterTest() {
        List<Flight> flightList = FlightBuilder.createFlights();

        List<Flight> expectedFlights = filter.filter(flightList);

        List<Flight> actualFlights = new ArrayList<>();
        List<Flight> excludedFlights = new ArrayList<>();
        long groundTimeLimit = 2;
        for (Flight flight : flightList) {
            List<Segment> segments = flight.getSegments();
            long duration = 0;
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime depTime = segments.get(i + 1).getDepartureDate();
                LocalDateTime arrTime = segments.get(i).getArrivalDate();
                duration += Duration.between(arrTime, depTime).toMinutes();
                if(duration > (groundTimeLimit * 60L) || duration < 0L) {
                    break;  // if duration is more than groundTimeLimit hours, break the loop
                }
            }
            if (duration <= (groundTimeLimit * 60L) && duration >= 0L) {
                actualFlights.add(flight);
            }else{
                excludedFlights.add(flight);
            }
        }

        Assertions.assertThat(actualFlights).isEqualTo(expectedFlights);
        Assertions.assertThat(excludedFlights).isNotIn(actualFlights);
    }
}
