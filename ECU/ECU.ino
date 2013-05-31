
#include "StateMachine.h"
extern "C"{
#include "IOManager.h"
}

StateMachine ecuStateMachine;
StateType nextState;

void setup() {
  setupIOManager();
}

void loop() {
 
  nextState = ecuStateMachine.currentState->onLoop();
  if(nextState != STATE_UNCHANGED){
    ecuStateMachine.currentState = &states[nextState];
    ecuStateMachine.currentState->onEnter();
  }
   
}
