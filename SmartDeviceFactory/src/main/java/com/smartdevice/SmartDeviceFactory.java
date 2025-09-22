package com.smartdevice;

/**
 * Abstract Factory for creating smart home devices
 */
public abstract class SmartDeviceFactory {
    public abstract ProductBulb makeBulb();  // Create Smart Bulb
    public abstract ProductLock makeLock();  // Create Smart Lock
}
