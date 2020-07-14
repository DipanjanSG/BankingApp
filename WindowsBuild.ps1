
$filePath = Read-Host "Where do you want to deploy the .war file? Enter path, else hit ENTER if you want in target directory --->"
if ($filePath.length -eq 0)
    {
        $filePath = split-path -parent $MyInvocation.MyCommand.Definition
         $filePath+= "\target"
    }
mvn clean install "-DbuildDirectory=$filePath"