
import java.util.Locale;

public class AirlineData {

    private final Airline airline;
    private final AircraftType aircraftType;
    private final int numberOfPassenger;
    private final int fuel;

    public AirlineData(String airline, String aircraft, int passenger, int fuel) {
        this.airline = Airline.valueOf(airline.toUpperCase(Locale.ROOT));
        this.aircraftType = AircraftType.valueOf(aircraft);
        this.numberOfPassenger = passenger;
        this.fuel = fuel;
    }

    public Airline getAirline() {
        return airline;
    }

    public AircraftType getAircraft() {
        return aircraftType;
    }

    public int getNumberOfPassenger() {
        return numberOfPassenger;
    }

    public int getFuel() {
        return fuel;
    }

    @Override
    public String toString() {
        return "{"
                + "airline=" + airline
                + ", aircraft=" + aircraftType
                + ", numberOfPassenger=" + numberOfPassenger
                + ", fuel=" + fuel
                + '}';
    }
}
