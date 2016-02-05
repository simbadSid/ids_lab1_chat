#!/bin/sh

rm -r -f classes/*
rm -r -f lib/*
mkdir classes/chatServer
mkdir classes/chatClient

currentDir=`pwd`





javac -d classes -classpath .:classes/chatServer src/chatServer/ChatServerInterface.java
jar cvf lib/ChatServerInterface.jar classes/chatServer/ChatServerInterface.class

javac -d classes -classpath .:classes/chatServer/* src/chatServer/ChatServerImpl.java
jar cvf lib/ChatServerImpl.jar classes/chatServer/ChatServerImpl.class

javac -d classes -cp .:classes/chatServer:lib/ChatServerInterface.jar:lib/ChatServerImpl.jar $currentDir/src/chatServer/ChatServer.java

javac -d classes -cp .:classes:lib/ChatServerInterface.jar $currentDir/src/ChatClient.java






