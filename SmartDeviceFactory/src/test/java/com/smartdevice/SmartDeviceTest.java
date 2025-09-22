package com.smartdevice;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class SmartDeviceTest {

    private SmartDeviceFactory factoryA;
    private SmartDeviceFactory factoryB;

    private static final Path DATA_DIR  = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("device_usage_data.txt");

    @BeforeAll
    static void ensureDataFileOnce() throws IOException {
        if (!Files.exists(DATA_FILE)) {
            Files.createDirectories(DATA_DIR);
            String content = String.join(System.lineSeparator(),
                    "BULB_A_POWER:9.5",
                    "LOCK_A_BATTERY:4.2",
                    "BULB_B_POWER:11.8",
                    "LOCK_B_BATTERY:6.7"
            ) + System.lineSeparator();
            Files.writeString(DATA_FILE, content);
        }
    }

    @BeforeEach
    void setUp() {
        factoryA = new BrandAFactory();
        factoryB = new BrandBFactory();
    }

    // -------- Factory sanity --------
    @Test @DisplayName("1) Factory: brand-specific products")
    void factoryMethodPattern() {
        ProductBulb bulbA = factoryA.makeBulb();
        ProductBulb bulbB = factoryB.makeBulb();
        ProductLock lockA = factoryA.makeLock();
        ProductLock lockB = factoryB.makeLock();

        System.out.printf("Factory made: bulbA=%s, bulbB=%s, lockA=%s, lockB=%s%n",
                bulbA.getBrand(), bulbB.getBrand(), lockA.getBrand(), lockB.getBrand());

        assertEquals("Brand A", bulbA.getBrand());
        assertEquals("Brand B", bulbB.getBrand());
        assertEquals("Brand A", lockA.getBrand());
        assertEquals("Brand B", lockB.getBrand());
    }

    // -------- Bulb A --------
    @Test @DisplayName("2) Bulb A: creation & readings")
    void bulbA_creation_and_readings() {
        ProductBulb bulb = factoryA.makeBulb();
        System.out.printf("Bulb A created. brand=%s, power(OFF)=%s%n",
                bulb.getBrand(), bulb.getPowerUsage());
        assertEquals(0.0, bulb.getPowerUsage(), 1e-9);

        bulb.turnOn();
        System.out.printf("Bulb A turned ON. power=%s%n", bulb.getPowerUsage());
        assertTrue(bulb.getPowerUsage() > 0.0);

        bulb.turnOff();
        System.out.printf("Bulb A turned OFF. power=%s%n", bulb.getPowerUsage());
        assertEquals(0.0, bulb.getPowerUsage(), 1e-9);
    }

    // -------- Bulb B --------
    @Test @DisplayName("3) Bulb B: creation & readings")
    void bulbB_creation_and_readings() {
        ProductBulb bulb = factoryB.makeBulb();
        System.out.printf("Bulb B created. brand=%s, power(OFF)=%s%n",
                bulb.getBrand(), bulb.getPowerUsage());
        assertEquals(0.0, bulb.getPowerUsage(), 1e-9);

        bulb.turnOn();
        System.out.printf("Bulb B turned ON. power=%s%n", bulb.getPowerUsage());
        assertTrue(bulb.getPowerUsage() > 0.0);

        bulb.turnOff();
        System.out.printf("Bulb B turned OFF. power=%s%n", bulb.getPowerUsage());
        assertEquals(0.0, bulb.getPowerUsage(), 1e-9);
    }

    // Optional: show set/get while ON
    @Test @DisplayName("4) Bulb A: set/get power when ON")
    void bulbA_set_get_power() {
        ProductBulb bulb = factoryA.makeBulb();
        bulb.turnOn();
        double prev = bulb.getPowerUsage();
        System.out.printf("Bulb A initial power(ON)=%s%n", prev);

        bulb.setPowerUsage(15.0);
        System.out.printf("Bulb A power after set=%.2f%n", bulb.getPowerUsage());
        assertEquals(15.0, bulb.getPowerUsage(), 1e-9);

        bulb.setPowerUsage(prev); // restore
    }

    // -------- Lock A --------
    @Test @DisplayName("5) Lock A: creation & lock/unlock")
    void lockA_creation_and_toggle() {
        ProductLock lock = factoryA.makeLock();
        System.out.printf("Lock A created. brand=%s, battery=%s%n",
                lock.getBrand(), lock.getBatteryConsumption());
        assertTrue(lock.getBatteryConsumption() > 0.0);

        lock.unlock();
        System.out.println("Lock A unlocked.");

        lock.lock();
        System.out.println("Lock A locked.");
    }

    // -------- Lock B --------
    @Test @DisplayName("6) Lock B: creation & lock/unlock")
    void lockB_creation_and_toggle() {
        ProductLock lock = factoryB.makeLock();
        System.out.printf("Lock B created. brand=%s, battery=%s%n",
                lock.getBrand(), lock.getBatteryConsumption());
        assertTrue(lock.getBatteryConsumption() > 0.0);

        lock.unlock();
        System.out.println("Lock B unlocked.");

        lock.lock();
        System.out.println("Lock B locked.");
    }

    // Optional: set/get battery
    @Test @DisplayName("7) Lock B: set/get battery")
    void lockB_set_get_battery() {
        ProductLock lock = factoryB.makeLock();
        double prev = lock.getBatteryConsumption();
        System.out.printf("Lock B initial battery=%s%n", prev);

        lock.setBatteryConsumption(12.0);
        System.out.printf("Lock B battery after set=%.2f%n", lock.getBatteryConsumption());
        assertEquals(12.0, lock.getBatteryConsumption(), 1e-9);

        lock.setBatteryConsumption(prev); // restore
    }
}
