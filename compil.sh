#!/bin/sh

rm -r -f bin/*
rm -r -f lib/*
#mkdir lib




currentDir=`pwd`




javac -d bin -classpath .:bin src/*/*
#javac -d bin -classpath .:bin src/chatServer/*
#javac -d bin -classpath .:bin src/chatClient/*
#javac -d bin -classpath .:bin src/gui/*

# compile the Interfaces

javac -d bin -classpath .:bin src/chatServer/ChatServerInterface.java
jar cvf lib/ChatServerInterface.jar bin/chatServer/ChatServerInterface.class

javac -d bin -classpath .:bin src/chatClient/ChatClientInterface.java
jar cvf lib/ChatClientInterface.jar bin/chatClient/ChatClientInterface.class

# compile the bin
#javac -d bin -classpath .:bin src/chatServer/*

# Generate the corresponding jar
jar cvf lib/ChatServerImpl.jar bin/chatServer/ChatServerImpl.class
jar cvf lib/ChatClientImpl.jar bin/chatClient/ChatClientImpl.class

# compile the main classe for the server
javac -d bin -cp lib/ChatServerInterface.jar:lib/ChatServerImpl.jar:bin $currentDir/src/chatServer/ChatServer.java
javac -d bin -cp lib/ChatClientInterface.jar:lib/ChatClientImpl.jar:bin $currentDir/src/chatClient/ChatClient.java







