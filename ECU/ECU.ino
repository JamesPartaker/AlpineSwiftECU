
extern "C"{
  #include "StateMachine.h"
  #include "EngineConfig.h"
  #include "IOManager.h"
}
#include "Communication.h"


EngineConfig engineConfig;
StateMachine ecuStateMachine;
StateType nextState;
extern State states[];

void setup() {
  setupIOManager();
  setupCommunication();
  updateSensorReadings();
}

void loop() {
 
  nextState = ecuStateMachine.currentState->onLoop();
  if(nextState != STATE_UNCHANGED){
    ecuStateMachine.currentState = &states[nextState];
    ecuStateMachine.currentState->onEnter();
  }//make it possible to change the state from onEnter?
  
  updateSensorReadings();
  readMessage();
}
