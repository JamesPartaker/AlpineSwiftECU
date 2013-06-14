
#ifndef STATE_MACHINE_H
#define STATE_MACHINE_H

#include <avr/pgmspace.h>
#include "Message.h"

//what about faults and errors?
//what about communications - like configuration, thrust?
//do we need to keep state across looping state calls?

#define STATE_UNCHANGED //TODO: what value? -1?
//StateType and states MUST BE IN SYNC 
typedef enum { 
  STATE_NOTREADY, 
  STATE_READY, 
  STATE_STARTUP, 
  STATE_RUNNING, 
  STATE_SHUTDOWN, 
  STATE_EMERGENCYSHUTDOWN
} StateType;

void notReadyOnEnter(void);
StateType notReadyOnLoop(void);
StateType notReadyOnMessage(MessageType mType, void* messageData);

//Relies on forward declaration of StateType
typedef struct{
  void (*onEnter)(void);
  StateType (*onLoop)(void);
  StateType (*onMessage)(InputMessageType mType, void* messageData);
} State;


extern State states[] PROGMEM;

typedef struct{
  State* currentState;
} StateMachine;


#endif

