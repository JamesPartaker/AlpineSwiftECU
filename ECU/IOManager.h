
#ifndef IO_MANAGER_H
#define IO_MANAGER_H

#include "Arduino.h"

void setupIOManager();

void setStartupMotorSpeed(uint8_t motorSpeed);

void setFuelSolenoid(boolean openSolenoid);

void setFuelValvePos(uint8_t valvePos);

void setIgnition(boolean ignite);

uint32_t getEngineSpeed();

uint16_t getEGT();

uint8_t getThrottle();

#endif

