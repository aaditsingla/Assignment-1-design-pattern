package com.smartdevice;

/**
 * Concrete Factory for Brand A devices
 */
public class BrandAFactory extends SmartDeviceFactory {

    @Override
    public ProductBulb makeBulb() {
        System.out.println("Creating Brand A Smart Bulb...");
        return new ProductBulbA();
    }

    @Override
    public ProductLock makeLock() {
        System.out.println("Creating Brand A Smart Lock...");
        return new ProductLockA();
    }
}
