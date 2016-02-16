#!/usr/bin/env bash

mkdir -p bin lib
rm -rf bin/*
rm -rf lib/*

# Compile classes.
javac -d bin -classpath src src/*/*.java

# Pack interfaces.
jar cvf lib/ChatServerInterface.jar bin/chatServer/ChatServerInterface.class
jar cvf lib/ChatClientInterface.jar bin/chatClient/ChatClientInterface.class
jar cvf lib/ChatServerImpl.jar bin/chatServer/ChatServerImpl.class
jar cvf lib/ChatClientImpl.jar bin/chatClient/ChatClientImpl.class

# Compile the main classes for server and client.
javac -d bin -classpath src:lib/*.jar src/chatServer/ChatServer.java
javac -d bin -classpath src:lib/*.jar src/chatClient/ChatClient.java
