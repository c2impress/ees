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
The primary objective of these tests is to extend the existing BDI-MATSim integration to not only support additional customized modes but also facilitate multi-modal evacuation scenarios. This includes enabling agents to use different modes of transportation, such as walking or driving, to reach safety.

### Approach
A new mode named `sOne` has been defined, complete with associated routing (`sOnefree` and `sOneGlobal`) and actions (`walkto1`). Below is an outline of the scenarios and their expected outcomes:

#### Scenario 1: SingleModeCarNoError
- **Description:** Two agents use the car mode for both pre-evacuation activities and evacuation.
- **Expected Outcome:** The results are as expected without any errors, demonstrating that the car mode functions correctly for evacuation.

#### Scenario 2: MultiModeEventBasedIncorrect
- **Description:** Initially, , followed by the use of the newly defined `sOne` mode for evacuation. This setup run without error. However, while the event file indicates that the agent uses the sOne mode for evacuation, the actual movement speed do not conform to the specified limits for this mod, and the evacuation is in fact done based on car mode.

Initially, [an activity utilizing car leg mode](https://github.com/c2impress/ees/blob/00df55347e30522ade7a35a0caf975c8ccc908e0/ees/scenarios/MultiMode_EventBased_Incorrect/Egaleo_Population_epsg2100.xml#L35C1-L36C25) is added. This is followed by using the newly defined sOne mode for evacuation. Despite the setup functioning without errors and the event file indicating sOne mode usage for evacuation, the actual evacuation speed and behavior correspond to the car mode.


#### Scenario 3: MultiModeVehicleNotFoundError
- **Description:** If the activity using the car leg mode from Scenario 2 is omitted, an error occurs stating, "Could not find requested vehicle 0 in simulation for agent EvacAgent{personId=0, state=LEG, vehicleId=0_sOne} with id 0."
- **Issues:** While MATSim by default utilizes IDs like vehicleId=0_sOne for added modes (i.e., sOne), BDI expects vehicle IDs used for the car leg mode (i.e., vehicleId=0).





