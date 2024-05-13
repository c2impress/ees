# Emergency Evacuation Simulator

## Setup

Initialise and update the git submodules as below. This only has to be done once, when you first clone this repository.

```
git submodule update --init --recursive
```

## How to build

```
./mvnw package
```


## Scenario files 


Scenario 1
```
ees/ees/scenarios/SingleModeCar_NoError
```
Respective run class at:
```
ees/ees/src/test/java/io/github/agentsoz/ees/it
/SingleModeCarNoError.java
```

Scenario 2
```
ees/ees/scenarios/MultiMode_EventBased_Incorrect
```
Respective run class at:
```
ees/ees/src/test/java/io/github/agentsoz/ees/it
/MultiModeEventBasedIncorrect.java
```

Scenario 3
```
ees/ees/scenarios/MultiMode_VehicleNotFound_Error
```
Respective run class at:
```
ees/ees/src/test/java/io/github/agentsoz/ees/it
/MultiModeVehicleNotFoundError.java
```
