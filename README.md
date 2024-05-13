# Emergency Evacuation Simulator

### Setup

Initialise and update the git submodules as below. This only has to be done once, when you first clone this repository.

```
git submodule update --init --recursive
```

### How to build

```
./mvnw package
```


### Scenario files 


# Scenario 1
```
ees/ees/scenarios/SingleModeCar_NoError
```
Respective run class at:
```
ees/ees/src/test/java/io/github/agentsoz/ees/it
/SingleModeCarNoError.java
```

# Scenario 2
```
ees/ees/scenarios/MultiMode_EventBased_Incorrect
```
Respective run class at:
```
ees/ees/src/test/java/io/github/agentsoz/ees/it
/MultiModeEventBasedIncorrect.java
```

# Scenario 3
```
ees/ees/scenarios/MultiMode_VehicleNotFound_Error
```
Respective run class at:
```
ees/ees/src/test/java/io/github/agentsoz/ees/it
/MultiModeVehicleNotFoundError.java
```


## Testing Objectives and Approach

### Objective
The primary objective of these tests is to enhance the existing BDI-MATSim integration to not only support additional customized modes but also facilitate multi-modal evacuation scenarios. This includes enabling agents to use different modes of transportation, such as walking or driving, to reach safety.

### Approach
A new mode named `sOne` has been defined, complete with associated routing (`sOnefree` and `sOneGlobal`) and actions (`walkto1`). Below is an outline of the scenarios and their expected outcomes:

#### Scenario 1: SingleModeCarNoError
- **Description:** Two agents use the car mode for both pre-evacuation activities and evacuation.
- **Expected Outcome:** The results are as expected without any errors, demonstrating that the car mode functions correctly for evacuation.

#### Scenario 2: MultiModeEventBasedIncorrect
- **Description:** Initially, a dummy activity utilizing car mode is added, followed by the use of the newly defined `sOne` mode for evacuation. However, the event file inaccurately reports the use of `sOne` mode, not aligning with the predefined maximum speed for each mode.
- **Issues:** This scenario highlights discrepancies in mode speed handling and reporting.

#### Scenario 3: MultiModeVehicleNotFoundError
- **Description:** If the dummy car-based activity from Scenario 2 is omitted, an error occurs stating, "Could not find requested vehicle 0 in simulation for agent EvacAgent{personId=0, state=LEG, vehicleId=0_sOne} with id 0."
- **Issues:** This test indicates issues with vehicle assignment and recognition in scenarios lacking prior car mode activities.

### Summary
These scenarios help validate the flexibility and reliability of the proposed multi-modal evacuation capabilities in the BDI-MATSim framework, identifying current limitations and areas for further enhancement.




