#include <Event.h>
#include <Timer.h>

extern "C"{
  #include "StateMachine.h"
  #include "EngineConfig.h"
  #include "IOManager.h"
}
#include "Communication.h"

extern State states[];

EngineConfig engineConfig;
StateMachine ecuStateMachine;

Timer timer;

//maybe move
void setEcuState(StateType nextState){
  
  if(nextState != STATE_UNCHANGED){
    ecuStateMachine.currentState = &states[nextState];
    ecuStateMachine.currentState->onEnter();
  }
  
}

void setup() {
  //disable anything that can interrupt us during setup...?
  setupIOManager();
  setupCommunication();
  updateSensorReadings();
  timer.every(1000, doLog);
  
  if(loadEngineConfig(&engineConfig)){
    setEcuState(STATE_READY);
    sendStatusMessage(MESSAGE_STATUS_SREADY); //do this automatically
  }else{
    sendStatusMessage(MESSAGE_STATUS_EBADENGCONFVER);
    setEcuState(STATE_NOTREADY);
    sendStatusMessage(MESSAGE_STATUS_SNOREADY);
  }

}

void loop() {
  
  StateType nextState = ecuStateMachine.currentState->onLoop();
  setEcuState(nextState);
  
  timer.update();
  updateSensorReadings();
  readMessage();
}
