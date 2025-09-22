package com.smartdevice;

/**
 * Concrete Factory for Brand B devices
 */
public class BrandBFactory extends SmartDeviceFactory {

    @Override
    public ProductBulb makeBulb() {
        System.out.println("Creating Brand B Smart Bulb...");
        return new ProductBulbB();
    }

    @Override
    public ProductLock makeLock() {
        System.out.println("Creating Brand B Smart Lock...");
        return new ProductLockB();
    }
}
