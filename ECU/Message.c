

void sendStatusMessage(StatusMessage toSend){
  writeMessage(MESSAGE_STATUS, statusCodes[toSend], sizeof(StatusMessage));
}

void sendLogMessage(LogMessage* toSend){
  writeMessage(MESSAGE_LOG, toSend, sizeof(LogMessage));
}

void sendEngineConfigResponseMessage(EngineConfigResponseMessage* toSend){
  writeMessage(MESSAGE_CONFIG_RESPONSE, toSend, sizeof(EngineConfigResponseMessage));
}

