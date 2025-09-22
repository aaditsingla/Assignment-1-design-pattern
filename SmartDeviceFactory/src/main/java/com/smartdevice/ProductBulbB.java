package com.smartdevice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Concrete implementation of Smart Bulb for Brand B
 */
public class ProductBulbB implements ProductBulb {
    private double powerUsage;
    private boolean isOn;
    private final String brand = "Brand B";

    public ProductBulbB() {
        this.isOn = false;
        this.powerUsage = 0.0;
        loadPowerUsage(); // Factory Method pattern - load usage after creation
    }

    /**
     * Factory Method to load power usage from data file
     */
    private void loadPowerUsage() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/device_usage_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("BULB_B_POWER:")) {
                    this.powerUsage = Double.parseDouble(line.split(":")[1].trim());
                    break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading power usage data for Brand B Bulb: " + e.getMessage());
            this.powerUsage = 12.0; // Default value
        }
    }

    @Override
    public void turnOn() {
        this.isOn = true;
        System.out.println(brand + " Smart Bulb turned ON");
    }

    @Override
    public void turnOff() {
        this.isOn = false;
        System.out.println(brand + " Smart Bulb turned OFF");
    }

    @Override
    public void setPowerUsage(double watts) {
        this.powerUsage = watts;
    }

    @Override
    public double getPowerUsage() {
        return isOn ? this.powerUsage : 0.0;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + brand + " Smart Bulb Info ===");
        System.out.println("Status: " + (isOn ? "ON" : "OFF"));
        System.out.println("Power Usage: " + getPowerUsage() + " watts");
        System.out.println("Brand: " + brand);
        System.out.println();
    }
}
