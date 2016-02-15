#!/bin/sh




currentDir=`pwd`
export CLASSPATH=$currentDir/bin:$currentDir/lib/ChatServerInterface.jar:$currentDir/lib/ChatServer.jar:$currentDir

echo "======================="
echo $currentDir
echo $CLASSPATH
echo "======================="

java -cp $CLASSPATH chatServer.ChatServer 



