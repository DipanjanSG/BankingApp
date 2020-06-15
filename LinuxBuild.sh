#!/bin/bash
echo "Where do you want to deploy the .war file? Enter path, else hit ENTER if you want in target directory--->";
read filePath
echo "YOUR .war FILE WILL BE SENT TO $filePath"
#if [ ${#filePath} -eq 0] && filePath="$(pwd)/target";
mvn clean install "-DbuildDirectory=$filePath"