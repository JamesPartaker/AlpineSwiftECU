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

boolean setEngineConfig(EngineConfig* dstConfig, EngineConfig* srcConfig){
  
  if(srcConfig->ECVersion == ENGINE_CONFIG_VER){  
    *dstConfig = *srcConfig;
  }else{
    return false; 
  }
  
  return true;
}

boolean loadEngineConfig(EngineConfig* engineConfig){
 
  uint8_t ECVer = eeprom_read_byte(ENGINE_CONFIG_ADDR);
  if(ECVer == ENGINE_CONFIG_VER){
    eeprom_read_block(engineConfig, ENGINE_CONFIG_ADDR, sizeof(EngineConfig));
    return true;
  }else{
    return false;
  }
}

