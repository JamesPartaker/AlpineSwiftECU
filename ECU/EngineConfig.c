#include "Arduino.h"
#include <avr/eeprom.h>
#include "EngineConfig.h"

#define ENGINE_CONFIG_ADDR (void*)0

boolean flashEngineConfig(EngineConfig* engineConfig){
  
  if(engineConfig->ECVersion == ENGINE_CONFIG_VER){  
    eeprom_write_block(engineConfig, ENGINE_CONFIG_ADDR, sizeof(EngineConfig));
  }else{
    return false;
  }
  
  return true;
}

//shouldn't modify engineConfig on fail
boolean loadEngineConfig(EngineConfig* engineConfig){
 
  eeprom_read_block(engineConfig, ENGINE_CONFIG_ADDR, sizeof(EngineConfig));
  if(engineConfig->ECVersion == ENGINE_CONFIG_VER){
    return true;
  }else{
    return false;
  }
}

