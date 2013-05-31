
#ifndef IO_MANAGER_H
#define IO_MANAGER_H

void setupIOManager();

void setStartupMotor(boolean enabled);

void setStartupMotorSpeed(uint8_t motorSpeed);

void setFuelSolenoid(boolean isOpen);

void setFuelValvePos(uint8_t valvePos);

void setIgnition(boolean ignite);

uint32_t readEngineSpeed();

uint16_t readEGT();

uint8_t readThrottle();

#endif

