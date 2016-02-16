#!/bin/sh




currentDir=`pwd`
export CLASSPATH=$currentDir/bin:$currentDir/lib/ChatServerInterface.jar:$currentDir/lib/ChatServer.jar:$currentDir/classes

echo "======================="
echo $currentDir
echo $CLASSPATH
echo "======================="

java chatClient.ChatClient $*

