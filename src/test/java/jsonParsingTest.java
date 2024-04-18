import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import model.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class jsonParsingTest {

    private final ClassLoader cl = jsonParsingTest.class.getClassLoader();
    private final Gson gson = new Gson();

    String setBrand = "Tesla";
    String setModel = "Model S Performance";
    int setYear = 2022;
    String setBodyType = "Sedan";
    String setDriveType = "AWD";
    String setEngineType = "Electric";
    String setEnginePower = "778 hp";
    String setEngineTorque = "840 Nm";
    String setAcceleration0100 = "2.3 seconds";
    String setAccelerationTopSpeed = "322 km/h";
    String setBatteryCapacity = "100 kWh";
    String setBatteryRange = "up to 652 km";
    int setSeats = 5;
    List<String> setInterior = new ArrayList<>();
    boolean setSuspesionAdaptiveAirSuspension = true;
    String setPrice = "starting from $99,990";


    @Test
    public void teslaParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Tesla.json");
             Reader reader = new InputStreamReader(is)) {
            Car object = gson.fromJson(reader, Car.class);
            setInterior.add("Autopilot");
            setInterior.add("Full Self-Driving Capability");
            Assertions.assertEquals(setBrand, object.brand);
            Assertions.assertEquals(setModel, object.model);
            Assertions.assertEquals(setYear, object.year);
            Assertions.assertEquals(setBodyType, object.bodyType);
            Assertions.assertEquals(setDriveType, object.driveType);
            Assertions.assertEquals(setEngineType, object.engine.type);
            Assertions.assertEquals(setEnginePower, object.engine.power);
            Assertions.assertEquals(setEngineTorque, object.engine.torque);
            Assertions.assertEquals(setAcceleration0100, object.acceleration.zeroToHundred);
            Assertions.assertEquals(setAccelerationTopSpeed, object.acceleration.topSpeed);
            Assertions.assertEquals(setBatteryCapacity, object.battery.capacity);
            Assertions.assertEquals(setBatteryRange, object.battery.range);
            Assertions.assertEquals(setSeats, object.interior.seats);
            Assertions.assertEquals(setInterior, object.interior.driverAssist);
            Assertions.assertEquals(setSuspesionAdaptiveAirSuspension, object.suspension.adaptiveAirSuspension);
            Assertions.assertEquals(setPrice, object.price);
        }
    }
}