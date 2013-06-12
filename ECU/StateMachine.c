
#include "StateMachine.h"

State states[] PROGMEM = {
  {notReadyOnEnter, notReadyOnLoop, notReadyOnMessage}
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


StateType notReadyOnMessage(MessageType mType, void* messageData){
  //fix all of this
  switch(mType){
    case  MESSAGE_CONTROL:
      //set throttle?
      ControlMessage* cm = (ControlMessage*)messageData;
      if(MESSAGE_CONTROL_THROTTLE){
        //engine.throttle = cm->throttle;
      }
      
      break;
    case MESSAGE_CONFIG_REQUEST:
      EngineConfigRequestMessage* ecrm = (EngineConfigRequestMessage*)messageData;
      if(ecrm->configMessageType == MESSAGE_CONFIG_SET){
      
        if(!setEngineConfig(engineConfig, ecrm->engineConfig)){
          //return message stating that there is a bad engine config version
          //writeMessage(MESSAGE_STATUS, "ERBADECV");
        }
        
      }else if(ecrm->configMessageType == MESSAGE_CONFIG_FLASH){
        
        if(setEngineConfig(engineConfig, ecrm->engineConfig)){
          flashEngineConfig(ecrm->engineConfig);
        }else{
           //return message stating that there is a bad engine config version
           //writeMessage(MESSAGE_STATUS, );
        }
       
      }else if(ecrm->configMessageType == MESSAGE_CONFIG_READ){
        //TODO: hackey -- change
        EngineConfig engineConfig;
        EngineConfigResponseMessage ecresm;
        loadEngineConfig(&engineConfig);
        ecresm->engineConfig = engineConfig;
        writeMessage(MESSAGE_CONFIG_RESPONSE, &ecresm);
      }else{
        //error 
        
      }
      break;
  }
  
  return STATE_UNCHANGED;
}


////////////////////////////////////
//READY
////////////////////////////////////

//...

