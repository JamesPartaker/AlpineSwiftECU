#ifndef MESSAGE_H
#define MESSAGE_H

#include "EngineConfig.h"

typedef enum { MESSAGE_CONFIG_READ, MESSAGE_CONFIG_SET, MESSAGE_CONFIG_FLASH } ConfigMessageType;

typedef enum { MESSAGE_CONTROL_STARTUP, MESSAGE_CONTROL_SHUTDOWN, MESSAGE_CONTROL_EMERG_SHUTDOWN, MESSAGE_CONTROL_THROTTLE } ControlMessageType;

typedef enum { MESSAGE_CONFIG, MESSAGE_CONTROL, MESSAGE_LOG, MESSAGE_FAULT } MessageType;


//INPUT
typedef struct{
  ConfigMessageType configMessageType;
  EngineConfig engineConfig;
} EngineConfigMessage;

typedef struct{
  ControlMessageType controlMessageType;
  uint8_t throttle; //valid only when ControlMessageType = THROTTLE
} ControlMessage;

//OUTPUT
typedef struct{
  EngineConfig engineConfig;
} EngineConfigResponseMessage;

typedef struct{
   uint8_t engineSpeed;
   uint8_t EGT;
} LogMessage;

typedef struct{
  byte statusCode[8];
} StatusMessage;


#endif

