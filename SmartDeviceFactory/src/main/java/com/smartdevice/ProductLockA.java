package com.smartdevice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Concrete implementation of Smart Lock for Brand A
 */
public class ProductLockA implements ProductLock {
    private double batteryConsumption;
    private boolean isLocked;
    private final String brand = "Brand A";

    public ProductLockA() {
        this.isLocked = true; // Default to locked
        this.batteryConsumption = 0.0;
        loadBatteryConsumption(); // Factory Method pattern - load usage after creation
    }

    /**
     * Factory Method to load battery consumption from data file
     */
    private void loadBatteryConsumption() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/device_usage_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("LOCK_A_BATTERY:")) {
                    this.batteryConsumption = Double.parseDouble(line.split(":")[1].trim());
                    break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading battery consumption data for Brand A Lock: " + e.getMessage());
            this.batteryConsumption = 5.0; // Default value
        }
    }

    @Override
    public void lock() {
        this.isLocked = true;
        System.out.println(brand + " Smart Lock LOCKED");
    }

    @Override
    public void unlock() {
        this.isLocked = false;
        System.out.println(brand + " Smart Lock UNLOCKED");
    }

    @Override
    public void setBatteryConsumption(double mAh) {
        this.batteryConsumption = mAh;
    }

    @Override
    public double getBatteryConsumption() {
        return this.batteryConsumption;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + brand + " Smart Lock Info ===");
        System.out.println("Status: " + (isLocked ? "LOCKED" : "UNLOCKED"));
        System.out.println("Battery Consumption: " + batteryConsumption + " mAh");
        System.out.println("Brand: " + brand);
        System.out.println();
    }
}
