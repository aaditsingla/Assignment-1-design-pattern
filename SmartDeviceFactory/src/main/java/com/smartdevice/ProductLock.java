package com.smartdevice;

/**
 * Interface for Smart Lock products
 */
public interface ProductLock {
    void lock();
    void unlock();
    void setBatteryConsumption(double mAh);
    double getBatteryConsumption();
    String getBrand();
    void displayInfo();
}
