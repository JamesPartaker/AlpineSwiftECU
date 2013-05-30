#ifndef ENGINE_CONFIG_H
#define ENGINE_CONFIG_H

#include <stdint.h>

#define ENGINE_CONFIG_VER 1

typedef struct{
  uint8_t ECVersion; //must be the first byte
  //uint16_t maxEngineSpeed;
  //...
  
} EngineConfig;

#endif

