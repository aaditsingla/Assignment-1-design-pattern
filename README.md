Smart Device Factory – Design Patterns Assignment 


Overview
This project demonstrates the Abstract Factory and Factory Method design patterns in Java.  
It simulates smart home devices (Smart Bulb and Smart Lock) for two brands (Brand A and Brand B).

Abstract Factory Pattern: provides families of related products (bulbs and locks) without specifying concrete classes.  
Factory Method Pattern: each product reads its specific usage value (powerUsage for bulbs, batteryConsumption for locks) from an external data file after creation.

Repository Structure

data/  
Contains the external file device_usage_data.txt that simulates a database.  
Holds values like BULB_A_POWER, LOCK_B_BATTERY.  
These values are loaded into bulbs/locks when they are created.

src/main/java/com/smartdevice/  
Contains the main source code for the project:  
- SmartDeviceFactory.java: abstract factory interface  
- BrandAFactory.java, BrandBFactory.java: concrete factories for each brand  
- ProductBulb.java and ProductLock.java: product interfaces  
- ProductBulbA.java, ProductBulbB.java: implementations of bulbs for each brand  
- ProductLockA.java, ProductLockB.java: implementations of locks for each brand  
- Client.java: demo class that shows how a client can use the factories

src/test/java/com/smartdevice/  
Contains the test code:  
- SmartDeviceTest.java: JUnit 5 test suite that validates factory creation, bulb ON/OFF behavior, and lock unlock/lock behavior  
- Tests also print power and battery values to show they are loaded from the data file

pom.xml  
Maven build configuration with JUnit dependencies for running tests.

Tests
The SmartDeviceTest class contains the following test methods:

- factoryMethodPattern  
  Confirms that factories for Brand A and Brand B create different product families and the brands of bulbs and locks do not overlap.

- bulbA_creation_and_readings  
  Verifies that a Bulb from Brand A is created correctly, is OFF by default with power usage 0, and displays the expected values when turned ON and OFF.

- bulbB_creation_and_readings  
  Same as above but for a Bulb from Brand B. Ensures power usage loads from the data file when ON.

- bulbA_set_get_power  
  Tests that the power usage value for a Bulb from Brand A can be changed programmatically using setPowerUsage and that getPowerUsage returns the correct updated value. Works same way for bulbB.

- lockA_creation_and_toggle  
  Creates a Lock from Brand A, prints its battery consumption, and verifies that unlock and lock operations execute without error.

- lockB_creation_and_toggle  
  Same as above but for a Lock from Brand B.

- lockB_set_get_battery  
  Tests that the battery consumption for a Lock from Brand B can be changed using setBatteryConsumption and that getBatteryConsumption returns the updated value. Works the same way for LockA.

All tests were executed in IntelliJ IDEA using JUnit 5. Screen dumps of the successful test execution are included below to show that each test passed and to display the printed outputs of power usage and battery consumption values.

![Test1](https://github.com/user-attachments/assets/d0b69c2b-ddde-4677-bd50-169a6819c3a5)
![Test2](https://github.com/user-attachments/assets/374d3062-adb2-424e-aa92-d505d8344c35)
![Test3](https://github.com/user-attachments/assets/eac9b55c-9069-45d7-b114-67e6917a331e)
![Test4](https://github.com/user-attachments/assets/d44b8a09-4f76-400c-ba0d-c6589de366ef)
![Test5](https://github.com/user-attachments/assets/c019dff5-d102-4768-9e6b-d7f6e6b73fba)
![Test6](https://github.com/user-attachments/assets/b23f8b02-69cb-4829-a9d9-cb7a63e22243)
![Test7](https://github.com/user-attachments/assets/f0629d8a-c491-4949-a86d-64935cd54ae4)

