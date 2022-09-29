
import java.util.List;
import java.util.stream.Stream;

public final class StreamTestClass {

    private StreamTestClass() {
    }

    public static void main(String[] args) {

        Stream<Flight> flight = CSVReading.processInputFile("FlightData.csv");
//        Stream<Flight> flight = null;
        Stream<Flight> copy = CSVReading.processInputFile("FlightData.csv");
//        Stream<Flight> copy = null;
        Stream<Flight> copy1 = CSVReading.processInputFile("FlightData.csv");
        List<Flight> f = flight.toList();

        List<String> loc = DataScience.getAllDestinationsNotInSchengenAreaAsList(copy1);
        loc.forEach(value -> System.out.println("Value from list: " + value));

        Map<Airline, Long> map1 = DataScience.getFlightCountPerAirline(f);
        map1.forEach((key, value) -> System.out.println(key + " : " + value));

        Map<AircraftType, Long> map3 = DataScience.getFlightCountPerAircraftType(f);
        map3.forEach((key, value) -> System.out.println(key + " : " + value));

        List<String> los = DataScience.getDestinationsWithinGateRange(copy, "G05", "G27");
        los.forEach(value -> System.out.println("Value from list: " + value));

        Map<String, Integer> map = DataScience.getAvgNumberOfPassengersPerDestination(f);
        map.forEach((key, value) -> System.out.println(key + " : " + value));

        Map<String, Set<Flight>> map2 = DataScience.getAllFlightsFromGate(f);
        map2.forEach((key, value) -> System.out.println(key + " : " + value));
    }

}
