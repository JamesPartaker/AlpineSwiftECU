
#include "IOManager.h"


#define PIN_EGT_THERMO A0
#define PIN_THROTTLE A1
#define PIN_LOAD_SENSOR A2
//
#define PIN_FUEL_PUMP 9
#define PIN_FUEL_SOL 10
#define PIN_START_MOTOR 11
#define PIN_IGNITION 12

uint32_t engineSpeed;
uint16_t EGT;
uint8_t throttle;

uint8_t startupMotorVoltage;
uint8_t fuelPumpVoltage;
boolean isFuelSolenoidOpen;
boolean isIgnitionOn;

void setupIOManager(){
  
 // pinMode(, INPUT);
  
  pinMode(PIN_FUEL_PUMP, OUTPUT);
  analogWrite(FUEL_PUMP, 0);
  
  pinMode(PIN_FUEL_SOL, OUTPUT);
  analogWrite(FUEL_SOL, 0);
  
  pinMode(PIN_START_MOTOR, OUTPUT);
  analogWrite(START_MOTOR, 0);
  
  pinMode(PIN_IGNITION, OUTPUT);
  digitalWrite(IGNITION, LOW);
  
   
}

void updateSensorReadings(){
  
  uint16_t rawEGT = analogRead(PIN_EGT_THERMO);
  //convert
  
  uint16_t rawThrust = analogRead(PIN_LOAD_SENSOR);
  //convert
  
}

void setStartupMotorSpeed(uint8_t motorSpeed){
  analogWrite(PIN_START_MOTOR, motorSpeed);
}

uint8_t getStartupMotor(){
  return startupMotorVoltage;
}

void setFuelSolenoid(boolean openSolenoid){
  digitalWrite(PIN_FUEL_SOL, openSolenoid ? HIGH : LOW);
}

boolean getFuelSolenoid(){
  return isFuelSolenoidOpen;
}

void setFuelValvePos(uint8_t valvePos){
  analogWrite(PIN_FUEL_VALVE, valvePos);
}

uint8_t getFuelValve(){
  return fuelValveVoltage;
}

void setIgnition(boolean ignite){
  digitalWrite(PIN_IGNITION, ignite ? HIGH : LOW);
}

boolean getIgnition(){
  return isIgnitionOn;
}

uint32_t getEngineSpeed(){
  return engineSpeed;
}

uint16_t getEGT(){
   return EGT;
}

uint8_t getThrottle(){
  return throttle;
}

