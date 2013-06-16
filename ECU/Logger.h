#ifndef LOGGER_H
#define LOGGER_H

#include "IOManager.h"

void doLog(){
  
  LogMessage log;    
  log.bitfield = getIgnition() ? IGNITION_BIT : 0;
  log.bitfield |= getFuelSolenoid() ? FUEL_SOLENOID_BIT : 0;
  log.engineSpeed = getEngineSpeed(); //convert
  log.EGT = getEGT(); //convert
  log.throttle = getThrottle();
  log.startupMotor = getStartupMotor();
  log.fuelValve = getFuelValve();

  sendLogMessage(log);
}

#endif
