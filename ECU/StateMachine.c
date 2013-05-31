
#include "StateMachine.h"

State states[] PROGMEM = {
  //{notReadyOnEnter, notReadyOnLoop, notReadyOnMessage}
   {notReadyOnEnter, notReadyOnLoop }
  //...
};

////////////////////////////////////
//NOT READY
////////////////////////////////////
StateType notReadyOnEnter(void){
  return STATE_UNCHANGED;  
}

StateType notReadyOnLoop(void){
  return STATE_UNCHANGED;
}

/*
StateType notReadyOnMessage(MessageType mType, void* messageData){
  return STATE_UNCHANGED;
}
*/

////////////////////////////////////
//READY
////////////////////////////////////

//...

