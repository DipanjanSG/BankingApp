#!/bin/bash
echo "Where do you want to deploy the .war file? Enter path, else hit ENTER if you want in target directory --->";
read filePath
if [ ${#filePath} -eq 0 ];
then
filePath="$(pwd)/target"
fi
echo "YOUR .war FILE WILL BE SENT TO $filePath"
mvn clean install "-DbuildDirectory=$filePath"