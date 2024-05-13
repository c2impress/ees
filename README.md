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

## How to run

run classes at:
```
ees/ees/src/test/java/io/github/agentsoz/ees/it
/SingleModeCarNoError.java
```
```
ees/ees/src/test/java/io/github/agentsoz/ees/it
/MultiModeEventBasedIncorrect.java
```
```
ees/ees/src/test/java/io/github/agentsoz/ees/it
/MultiModeVehicleNotFoundError.java
```


## Respective scenarios


Scenario 1
```
ees/ees/scenarios/SingleModeCar_NoError
```

Scenario 2
```
ees/ees/scenarios/MultiMode_EventBased_Incorrect
```

Scenario 3
```
ees/ees/scenarios/MultiMode_VehicleNotFound_Error
```
