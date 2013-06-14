#ifndef MESSAGE_H
#define MESSAGE_H

#include "EngineConfig.h"

typedef enum { MESSAGE_CONTROL, MESSAGE_CONFIG_REQUEST } InputMessageType; 

typedef enum { MESSAGE_STATUS, MESSAGE_CONFIG_RESPONSE, MESSAGE_LOG } OutputMessageType;

typedef enum { MESSAGE_CONTROL_STARTUP, MESSAGE_CONTROL_SHUTDOWN, MESSAGE_CONTROL_EMERG_SHUTDOWN, MESSAGE_CONTROL_THROTTLE } ControlMessageType;

typedef enum { MESSAGE_CONFIG_SET, MESSAGE_CONFIG_FLASH, MESSAGE_CONFIG_READ } ConfigMessageType;

typedef enum { 
  MESSAGE_STATUS_EBADENGCONFVER, 
  MESSAGE_STATUS_SNOREADY, 
  MESSAGE_STATUS_SREADY, 
  MESSAGE_STATUS_SSTARTUP, 
  MESSAGE_STATUS_SRUNNING, 
  MESSAGE_STATUS_SSHUDOWN, 
  MESSAGE_STATUS_SEMERGYS
} StatusMessage; //TODO: change name

char* statusCodes[] PROGMEM = {
  "ECONFVER",
  "SNOREADY",
  "SREADY",
  "SSTARTUP",
  "SRUNNING",
  "SSHUDOWN",
  "SEMERGYS",
}; 

//INPUT
typedef struct{
  ControlMessageType controlMessageType;
  uint8_t throttle; //valid only when ControlMessageType = THROTTLE
} ControlMessage;

typedef struct{
  ConfigMessageType configMessageType;
  EngineConfig engineConfig;
} EngineConfigRequestMessage;

//OUTPUT
typedef struct{
  byte statusCode[8];
} StatusMessage;

typedef struct{
  EngineConfig engineConfig;
} EngineConfigResponseMessage;

typedef struct{
   uint8_t engineSpeed;
   uint8_t EGT;
} LogMessage;

#endif

