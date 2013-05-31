#ifndef COMMUNICATION_H
#define COMMUNICATION_H

#include "Arduino.h"
#include "Message.h"

void setupCommunication();

void writeMessage(MessageType mType, void* messageData);

void readMessage();

#endif
