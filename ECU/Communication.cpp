
#include "Communication.h"

void setupCommunication(){
  Serial.begin(9600);
}

//perhaps add messageSize as a parameter
void writeMessage(OutputMessageType mType, void* messageData, uint8_t messageSize){
  Serial.write(messageSize);
  Serial.write(mType); //make sure uint8_t
  Serial.write((uint8_t*)messageData, messageSize);
  Serial.flush();
}

//Format:
//first byte: length as a uint8_t
//second byte: type
//'length' bytes: messageStruct

//should not overrun buffer on longer messages, throw error instead
void readMessage(){
  
  static char messageBuffer[64];
  static uint8_t messageLength;
  static uint8_t messageType;
  static boolean readingMessage = false;
  
  if(!readingMessage){
    if(Serial.available() >= 2){
      messageLength = Serial.read();
      messageType = Serial.read();
      readingMessage = true;
     }
  }else{
    if(Serial.available() >= messageLength){
      Serial.readBytes(messageBuffer, messageLength);
      //send message to ecu state machine
      ecuStateMachine.state->onMessage(messageType, messageBuffer);
      readingMessage = false;
    }
  }  
}


