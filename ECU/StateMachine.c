
#include "StateMachine.h"

State states[] PROGMEM = {
  {notReadyOnEnter, notReadyOnLoop, notReadyOnMessage}
  {readyOnEnter, readyOnLoop, readyOnMessage}
  {startupOnEnter, startupOnLoop, startupOnMessage}
  //...
};


//GENERIC

void empty(){
  return;
}

StateType unchanged(void){
  return STATE_UNCHANGED; 
}

////////////////////////////////////
//NOT READY
////////////////////////////////////

void notReadyOnEnter(void){
  return;  
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
      if(cm->controlMessageType == MESSAGE_CONTROL_THROTTLE){
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

StateType readyOnMessage(MessageType mType, void* messageData){
  
  switch(mType){
  case MESSAGE_CONTROL:
    ControlMessage* cm = (ControlMessage*)messageData;
    if(cm->controlMessageType == MESSAGE_CONTROL_THROTTLE){
      //engine.throttle = cm->throttle;
    }else if(cm->controlMessageType == MESSAGE_CONTROL_STARTUP){
      //set the state to startup 
      
      //only if startup requirements are met 
      return STATE_STARTUP;
    }
    break;
  case MESSAGE_CONFIG_REQUEST:
    handleMessageConfigRequest((EngineConfigRequestMessage*)messageData);
    break;
  }
  
}

StateType readyOnLoop(void){
  return STATE_UNCHANGED;
}


////////////////////////////////////
//STARTUP
////////////////////////////////////

void startupOnEnter(void){
  startupState = JUST_STARTING;
  
  setStartupMotorSpeed(); //based on some calculation? TODO
}

StateType startupOnMessage(MessageType mType, void* messageData){
  
  switch(mType){
  case MESSAGE_CONTROL:
    ControlMessage* cm = (ControlMessage*)messageData;
    if(cm->controlMessageType == MESSAGE_CONTROL_EMERG_SHUTDOWN){
      return STATE_EMERGENCYSHUTDOWN; 
    }
    //throttle
  }
  
}

//have a way to re-try startup sequence when it fails to ignite
StateType startupOnLoop(void){
  
  switch(startupState){
  case JUST_STARTING:
    
    if(getEngineSpeed() > MIN_STARTUP_ENGINE_SPEED){
      startupState = INIT_FUEL_FLOW;
    }
    
    break;
    
  case INIT_FUEL_FLOW:
    //disengage startup motor
    setStartupMotor(0);
    setFuelSolenoid(true);
    startupState = IGNITION;
    break;
  
  case IGNITION:
    setFuelValve(); //how to specify value?
    setIgnition(true);
    break;
    
  case CHECK_FOR_IGNITION:
    if(EGT > ){ //perhaps check for an increase in the EGT
      
    }
    break;
  case TO_IDLE:
    
    if(){
      return STATE_RUNNING;
    }
    break;
  }
  
  
}


////////////////////////////////////
//RUNNING
////////////////////////////////////

StateType startupOnMessage(MessageType mType, void* messageData){
  
  switch(mType){
  case MESSAGE_CONTROL:
    ControlMessage* cm = (ControlMessage*)messageData;
    if(cm->controlMessageType == MESSAGE_CONTROL_EMERG_SHUTDOWN){
      return STATE_EMERGENCYSHUTDOWN; 
    }else if(cm->controlMessageType == MESSAGE_CONTROL_SHUTDOWN){
      return STATE_SHUTDOWN;
    }else if(cm->controlMessageType == MESSAGE_CONTROL_THROTTLE){
      //?
    }

  }
  
}

StateType runningOnLoop(void){
   
  //make sure that we don't overspeed
  
  //make sure that we don't flameout
  
  //make sure that we don't surge
  
  //set the amount of fuel entering the engine based on throttle
  
}

////////////////////////////////////
//SHUTDOWN
////////////////////////////////////

StateType shutdownOnLoop(void){
  //to idle
  // 
}

////////////////////////////////////
//EMERGENCY SHUTDOWN
////////////////////////////////////

StateType emergShutdownOnLoop(void){
  
}





