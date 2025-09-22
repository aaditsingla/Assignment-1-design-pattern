package com.smartdevice;

/**
 * Client class that uses the Abstract Factory pattern
 */
public class Client {
    private SmartDeviceFactory factory;

    public Client(SmartDeviceFactory factory) {
        this.factory = factory;
    }

    public void demonstrateSmartBulb() {
        ProductBulb bulb = factory.makeBulb();
        bulb.displayInfo();
        bulb.turnOn();
        System.out.println("Power consumption: " + bulb.getPowerUsage() + " watts\n");
    }

    public void demonstrateSmartLock() {
        ProductLock lock = factory.makeLock();
        lock.displayInfo();
        lock.unlock();
        System.out.println("Battery consumption: " + lock.getBatteryConsumption() + " mAh\n");
    }

    public static void main(String[] args) {
        System.out.println("Smart Device Factory Pattern Demo");
        System.out.println("==================================\n");

        // Test Brand A Bulb
        System.out.println("Testing Brand A Smart Bulb:");
        System.out.println("---------------------------");
        Client clientA = new Client(new BrandAFactory());
        clientA.demonstrateSmartBulb();

        // Test Brand B Lock
        System.out.println("Testing Brand B Smart Lock:");
        System.out.println("---------------------------");
        Client clientB = new Client(new BrandBFactory());
        clientB.demonstrateSmartLock();

        // Additional demonstration - Brand A Lock and Brand B Bulb
        System.out.println("Additional Tests:");
        System.out.println("-----------------");

        System.out.println("Brand A Smart Lock:");
        clientA.demonstrateSmartLock();

        System.out.println("Brand B Smart Bulb:");
        clientB.demonstrateSmartBulb();
    }
}
