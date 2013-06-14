#ifndef COMMUNICATION_H
#define COMMUNICATION_H

#include "Arduino.h"
#include "Message.h"

void setupCommunication();

void writeMessage(OutputMessageType mType, void* messageData, int messageSize);

void readMessage();

#endif
