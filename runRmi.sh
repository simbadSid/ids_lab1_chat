#!/bin/sh



currentDir=`pwd`
export CLASSPATH=$currentDir/bin:$currentDir/lib/ChatServerInterface.jar:$currentDir/lib/ChatServer.jar:$currentDir


rmiregistry &

