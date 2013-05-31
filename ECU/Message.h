#ifndef MESSAGE_H
#define MESSAGE_H

#include "EngineConfig.h"

typedef enum { MESSAGE_CONFIG_READ, MESSAGE_CONFIG_SET, MESSAGE_CONFIG_FLASH } ConfigMessageType;

typedef enum { MESSAGE_CONTROL_STARTUP, MESSAGE_CONTROL_SHUTDOWN, MESSAGE_CONTROL_EMERG_SHUTDOWN, MESSAGE_CONTROL_THRUST } ControlMessageType;

typedef enum { MESSAGE_CONFIG, MESSAGE_CONTROL, MESSAGE_LOG, MESSAGE_FAULT } MessageType;

typedef struct{
  ConfigMessageType configMessageType;
  EngineConfig engineConfig;
} ConfigMessage;

typedef struct{
  ControlMessageType controlMessageType;
  
} ControlMessage;

typedef struct{
   //stuff
} LogMessage;

typedef struct{
  //stuff
} FaultMessage;


#endif

