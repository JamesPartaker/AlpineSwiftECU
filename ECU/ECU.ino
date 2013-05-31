
#include "StateMachine.h"
#include "IOManager.h"

StateMachine ecuStatemachine;
StateType nextState;

void setup() {
  setupIOManager();
}

void loop() {
 
  nextState = ecuStateMachine.currentState.onLoop();
  if(nextState != STATE_UNCHANGED){
    ecuStateMachine.currentState = states[nextState];
    ecuStateMachine.currentState.onEnter();
  }
   
}
