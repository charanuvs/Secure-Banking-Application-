Secure Online Banking
=======

## Softwares
 1. Java - **jdk1.8.0_60**
 2. Maven -  **Maven 3.3.3**
 3. Apache Tomcat - **apache-tomcat-7.0.64**

## Steps to Integrate with eclipse
 1. Import the `"SecureBankingApp"` folder as a maven project into your eclipse.
 2. Make sure that your Eclipse points to the jdk version mentioned above to build.
 3. To run right click on the project and "Run on server". Configure the server that matches the version above.
 4. Or you can build the project with the command `"mvn clean install"` and then deploy the war file on the server.

The log file goes into `/log` directory. Make sure that you have read and write permissions.