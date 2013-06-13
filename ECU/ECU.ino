
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


void setEcuState(StateType nextState){
  
  if(nextState != STATE_UNCHANGED){
    ecuStateMachine.currentState = &states[nextState];
    ecuStateMachine.currentState->onEnter();
    //send a status message that we have set a new state
  }
  
}

void setup() {
  //disable anything that can interrupt us during setup...?
  setupIOManager();
  setupCommunication();
  updateSensorReadings();
  

  if(loadEngineConfig(&engineConfig)){
    setState(STATE_READY);
  }else{
    sendStatusMessage(MESSAGE_STATUS_EBADENGCONFVER);
    setState(STATE_NOTREADY);
  }

}

void loop() {
 
  nextState = ecuStateMachine.currentState->onLoop();
  setState(nextState);
  
  updateSensorReadings();
  readMessage();
}
