#!/bin/sh




currentDir=`pwd`
export CLASSPATH=$currentDir/classes/ChatClient.class:$currentDir/classes/ChatServer.class:$currentDir/classes/ChatServerImpl.class:$currentDir/classes/ChatServerInterface.class:$currentDir/lib/ChatServerInterface.jar:$currentDir/lib/ChatServer.jar:$currentDir

echo "======================="
echo $currentDir
echo $CLASSPATH
echo "======================="

java ChatServer



