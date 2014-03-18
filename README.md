SENG403
=======

#SENG 403 Project


##Getting started

###Setting up the Project

First download gradle from http://www.gradle.org/downloads and unzip it. Next clone the
github repository. Once the repository has been cloned, open a command prompt and navigate to
the cloned repository. Next we will run graddle which will download all dependencies and 
generate the eclipse project. run gradle by typing:
(note: use gradle.bat on windows)

'''
$/path/to/gradle/bin/gradle eclipse
'''

Now open eclipse and create a new java project changing the location to the folder that you
cloned the repo into (eg. SENG403). The project will now open in eclipse. We are using the 
standard maven folder structure where src/main/java is code, src/test/java is tests and 
src/main/resources are resources.

###Building the Database

In the eclipse package explorer navigate to the src/main/resources folder and you will see a
package called ScriptRunner. Inside this package there is a file called Runner.java, open it
and run it in eclipse. This script will create and build your derby database for you.
