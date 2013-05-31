
#ifndef STATE_MACHINE_H
#define STATE_MACHINE_H

#include <avr/pgmspace.h>

//what about faults and errors?
//what about communications - like configuration, thrust?
//do we need to keep state across looping state calls?

/*
enum ConfigMessageType { READ, SET, FLASH };

enum MessageType { MESSAGE_CONFIG,  };
typedef struct{
  ConfigMessageType configMessageType;
  EngineConfig engineConfig;
} ConfigMessage;
*/




#define STATE_UNCHANGED ((StateType)-1)
//StateType and states MUST BE IN SYNC 
typedef enum { 
  STATE_NOTREADY, 
  STATE_READY, 
  STATE_STARTUP, 
  STATE_RUNNING, 
  STATE_SHUTDOWN, 
  STATE_EMERGENCYSHUTDOWN
} StateType;

StateType notReadyOnEnter(void);
StateType notReadyOnLoop(void);
//StateType notReadyOnMessage(MessageType mType, void* messageData);

//Relies on forward declaration of StateType
typedef struct{
  StateType (*onEnter)(void);
  StateType (*onLoop)(void);
  //StateType (*onMessage)(MessageType mType, void* messageData);
} State;


extern State states[] PROGMEM;

typedef struct{
  State* currentState;
} StateMachine;


/*
//States
0 NotAvailable
1 State_NotReady 
2 State_Ready
3 State_Startup
4 State_Running
5 State_Shutdown
6 State_EmergencyShutdown

//??
//events
1 Event_Startup
2 Event_Shutdown
3 Event_EmergencyShutdown
4 Event_Fault //? Go to x depending on fault

//transitions
1 = 0 0 0
2 = 3 0 0
3 = 0 ? 6 
4 = 0 5 6
5 = 0 0 6
6 = 0 0 0
*/

#endif

