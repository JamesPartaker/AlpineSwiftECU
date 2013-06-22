
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
  //move
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
    if(millis() < timeLimit){
      if(EGT > EGT_TO_DETECT_IGNITION){ //perhaps check for an increase in the EGT
        
      }
    }else{
      if(timesRetried <= timesToRetry){
        //go back to retry
        ++timesRetried;
        startupState = RETRY_IGNITION;
      }else{
       // shut 'er down
       //purge explosion chambers from extra fuel
      }
    }
    break;
  case TO_IDLE:
    
    if(){
      return STATE_RUNNING;
    }
    break;
  }
  
  return STATE_UNCHANGED;
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
  if(engineSpeed > MAX_ENGINE_SPEED_RPM){
    //turn down the throttle 
  }
  
  //make sure that we don't hit max temp
  if(EGT > MAX_RUNNING_EGT){
    //turn down the throttle?
  }
  
  //make sure that we don't flameout
  
  //make sure that we don't surge
  
  //set the amount of fuel entering the engine based on throttle
  
}

////////////////////////////////////
//SHUTDOWN
////////////////////////////////////

StateType shutdownOnLoop(void){
  
  switch(shutdownState){
  case TO_IDLE:
    //use fuel ramper to set engine to idle
    break; 
  case REACHING_IDLE:
    //if we're at our idle engine speed and temps
    break;
  case DELAY:
    // just wait for a few seconds
    if(millis() > delayUntil){
      shutdownState = SHUTOFF;
    }
    break;
  case SHUTOFF:
    //turn off the fuel pump, and solenoid
    setFuelPump(false);
    setFuelSolenoid(false);
    break;
  case REACHING_:
    if(engineSpeed < ){
      shutdownState = INCREASE_COOLING; 
    }
    break; 
  case INCREASE_COOLING:
    //turn on the startup motor - up to 7k
    setStartupMotorToRPM(7000);
    
    if(engineSpeed > 7000){
      shutdownState = DECREASE_COOLING; 
    }
    
    break;
  case DECREASE_COOLING:
    setStartupMotorToRPM(1000);
    
    if(engineSpeed < 1000){
      ++numRepeats
      if(getEGT() < EGT_SHUTDOWN_COOLDOWN_TO  || numRepeats > MAX_TIMES_TO_REPEAT){
        setStartupMotor(false);
        shutdownState = TO_READY;
      }else{
        shutdownState = INCREASE_COOLING;
      }
    }
    
    //turn off the startup motor - down to 1k
    //if the engine is cool enough stop, otherwise keep going, max at 6
    break;
  case TO_READY:
    if(engineSpeed < 10){
      return STATE_READY;
    }
    break;
  }
  
  return STATE_UNCHANGED;
}

////////////////////////////////////
//EMERGENCY SHUTDOWN
////////////////////////////////////

StateType emergShutdownOnLoop(void){
  
}





