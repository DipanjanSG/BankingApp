#Project Name
  Banking App
> An app to perform basic Banking operations (Create account, Money transfer, Get bank statement, Credit card transactions)


##Contributors
- Dipanjan Sengupta  <dipanjan.sen.gupta1993@gmail.com>

##License and Copyright
- This code is owned by Dipanjan Sengupta  <dipanjan.sen.gupta1993@gmail.com>

##How to use in "Windows OS"
> Clone the repository and open powershell in that location and run the command 
.\WindowsBuild.ps1 to run the file "WindowsBuild.ps1".

> It will ask for the location of the javaCertification.war file to be generated.
Give a location like ----> C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps
and hit the enter key.

> Start your tomcat server , open google chrome and 
type in ---> http://localhost:8080/javaCertification/
The login screen will appear

##How to use in "Linux OS"
> Clone the repository and open powershell in that location and run the command
- sudo su
- chmod +x LinuxBuild.sh

> Now run the file "LinuxBuild.sh"
- .\LinuxBuild.sh

> It will ask for the location of the javaCertification.war file to be generated.
Give a location like ----> /opt/tomcat/webapps/    

"OR" 

> just hit the enter key for the generating it in the present working directory.
> Start your tomcat server 
> Go to the /opt/tomcat/bin/  and execute ./startup.sh
> Open google chrome and 
type in ---> http://localhost:8080/javaCertification/
>The login screen will appear