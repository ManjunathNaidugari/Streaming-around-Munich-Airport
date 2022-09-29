
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DataScience {

    private DataScience() {
    }

    public static String outputFlightInformation(Stream<Flight> stream, String flightNumber) {

        return stream.filter(flight -> flight.getFlightNumber().equals(flightNumber))
                .map(Flight::toString)
                .findAny()
                .orElse("The flight with FlightNumber " + flightNumber + " does not exist");

    }

    public static int getFuelDemandOfAirline(Stream<Flight> stream, Airline airline) {

        return stream.filter(flight -> flight.getAirlineData().getAirline().compareTo(airline) == 0)
                .mapToInt(flight1 -> flight1.getAirlineData().getFuel())
                .sum();

    }

    public static double getAverageDurationOfAllFlights(Stream<Flight> stream) {

        return stream.mapToDouble(Flight::getDuration).average().orElse(0);
    }

    public static Map<String, LocalDateTime> getCalculatedArrivalTime(Stream<Flight> stream) {

        return stream.collect(Collectors.toMap(Flight::getFlightNumber, flight -> flight.getDate().plusMinutes(flight.getDuration())));
    }

    public static long getCountOfSchengenFlights(Stream<Flight> stream) {

        return stream.filter(Flight::isSchengen).count();
    }

    public static double getAverageNumberOfPassengersPerNonSchengenFlight(Stream<Flight> stream) {
        return stream.filter(flight -> !flight.isSchengen()).mapToDouble(flight -> flight.getAirlineData().getNumberOfPassenger()).average().orElse(0);
    }

    public static List<String> getAllDestinationsNotInSchengenAreaAsList(Stream<Flight> stream) {
        return stream.filter(flight -> !flight.isSchengen()).map(Flight::getDestination).distinct().sorted().toList();

    }

    public static Map<Airline, Long> getFlightCountPerAirline(List<Flight> list) {
        Map<Airline, Long> result = new HashMap<>();
        List<Airline> airlineList = Arrays.stream(Airline.values()).toList();
        airlineList.forEach(airline -> result.put(airline,
                list.stream().filter(flight -> flight.getAirlineData().getAirline() == airline).distinct().count()
        ));
        return result;
    }


    public static Map<AircraftType, Long> getFlightCountPerAircraftType(List<Flight> list) {

        Map<AircraftType, Long> result = new HashMap<>();
        List<AircraftType> aircraftTypeList = Arrays.stream(AircraftType.values()).toList();
        aircraftTypeList.forEach(aircraft -> result.put(aircraft,
                list.stream().filter(flight -> flight.getAirlineData().getAircraft() == aircraft).distinct().count()
        ));
        return result;
    }

    public static List<String> getDestinationsWithinGateRange(Stream<Flight> stream, String gateFrom, String gateTo) {
        return stream.filter(flight -> flight != null && flight.getDepartureGate().compareTo(gateFrom) >= 0 && flight.getDepartureGate().compareTo(gateTo) <= 0)
                .map(Flight::getDestination)
                .distinct().sorted().toList();

    }

    public static Map<String, Integer> getAvgNumberOfPassengersPerDestination(List<Flight> list) {

        Map<String, Integer> result = new HashMap<>();

        list.forEach(flight -> {
            if (!result.containsKey(flight.getDestination())) {
                result.put(flight.getDestination(), (int) Math.floor(list.stream()
                        .filter(flight1 -> flight1.getDestination().equals(flight.getDestination()))
                        .mapToInt(c -> c.getAirlineData().getNumberOfPassenger())
                        .average()
                        .orElse(0)));
            }
        });
        return result;
    }

    public static Map<String, Set<Flight>> getAllFlightsFromGate(List<Flight> list) {

        Map<String, Set<Flight>> result = new HashMap<>();
        List<String> departuregate = list.stream().map(Flight::getDepartureGate).sorted().distinct().toList();
        departuregate.forEach(depGate -> result.put(depGate,
                list.stream().filter(flight -> flight.getDepartureGate().equals(depGate)).collect(Collectors.toSet())));

        return result;
    }

}
