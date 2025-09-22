package com.smartdevice;

/**
 * Interface for Smart Bulb products
 */
public interface ProductBulb {
    void turnOn();
    void turnOff();
    void setPowerUsage(double watts);
    double getPowerUsage();
    String getBrand();
    void displayInfo();
}
