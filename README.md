# Alloy  [![Build Status](https://ci.montclairrobotics.org/buildStatus/icon?job=Alloy)](https://ci.montclairrobotics.org/job/Alloy/) [![CodeFactor](https://www.codefactor.io/repository/github/garrettburroughs/alloy/badge)](https://www.codefactor.io/repository/github/garrettburroughs/alloy) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/f3335dca15a8429ebc50528ca6330411)](https://www.codacy.com/project/garrett_2/Alloy/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=GarrettBurroughs/Alloy&amp;utm_campaign=Badge_Grade_Dashboard)

![AlloyLogo](http://gdurl.com/AFl8)

Status: **Beta**

Alloy is a robot framework designed specifically for the First Tech Challenge (FTC).

**Note:** Alloy is still under heavy development, and not ready or suggested for use in creating a robot

Developed by FTC147 and FRC555

The goal of Alloy is to provide a robot framework that allows anyone, including new programmers, to be able to implement
high functioning and quality code for their robots. In other terms, Alloy aims to prevent robot code from being a limiting
factor on the performance of the robot.


Alloy is a robot framework designed specifically for the First Tech Challenge (FTC), but is also designed
with expandibility in mind. FRC implementation is possible and will be coming in the future.

Alloy is based off the FRC robot framework [Sprocket](https://github.com/MontclairRobotics/Sprocket), but redesigned to abstract away the more complex ideas but still allow for advanced robot functionality.

Any information regarding Alloy, including how it works, project structure, as well as in-depth explanation on how to use all of the components
can be found in the project [Wiki](https://github.com/GarrettBurroughs/Alloy/wiki) as well as a [quick start guide](https://github.com/GarrettBurroughs/Alloy/wiki/Getting-Started).


## Formatting
Alloy uses the [Spotless](https://github.com/diffplug/spotless/tree/master/plugin-gradle) code formatter to ensure that 
all of the code follows a specific format. It is added as a build step for a gradle
build so this means that all code in alloy must be compliant to be merged. 
Spotless allows for automatic code formatting, just run:
```
cmd> ./gradlew spotlessApply
:spotlessApply
BUILD SUCCESSFUL

cmd> ./gradlew build
BUILD SUCCESSFUL
```

## Setting up the project for development

### Gradle
Alloy uses the gradle build system and the GradleRio FRC plugin to manage FRC
dependencies. There is a lot you can do with gradle if you are familiar with it, 
but here are the basic things you need to know in order to develop alloy



### Intellij IDEA

If using the Intellij IDEA editor, it is necessary to do a few things to set up the project.

1. **Create project files**

    This can be done using the gradle command
    ```
    cmd> ./gradlew IDEA
    ```

2. **Import the project**

    There are 2 ways to do this
   - import in Intellij
   - import using .ipr file
    
    If you are using the .ipr file make sure that Intellij IDEA is closed before you open the file
    To import the file, simply double click on the .ipr files and follow the configuration settings
    
    If importing intellij use `File > Open`, select the alloy build.gradle file and select `Import as Project`

3. **Setting up gradle tasks**

    *This step is not necessary, but makes working with gradle in Intellij much easier.*
    
    To add a Gradle Build task in intellij
    
    - Click + > Gradle.
    - Fill in Name: `Refresh Build`.
    - Fill in Gradle project by clicking on the folder icon and selecting `Alloy`.
    - Fill in Tasks: `build`.
    - Fill in optional Arguments: `--Refresh-Dependencies`.
    
    Here are a few recommended tasks you should set up
    
    Build -> `build`
    
    Refresh -> `build --Refresh-Dependencies`
    
    Format -> `spotlessApply`
    
    Check Format -> `spotlessCheck`
    
    Generate JAR -> `jar`
    
    Generate Javadoc -> `Javadoc`
    
    

[Gradle Intellij Plugin Help](https://docs.gradle.org/current/userguide/idea_plugin.html)

### Eclipse

1. **Create project files**

    This can be done using the gradle command
    ```
    cmd> ./gradlew eclipse
    ```
2. **Import Project**
    You can then import your project with `File > Import > Existing Projects into Workspace` in Eclipse.
      
[Gradle Eclipse Plugin Help](https://docs.gradle.org/current/userguide/eclipse_plugin.html)

## FAQ

### Q. What is the purpose of/how do I use ___?

A. Every class is heavily documented as well as wiki articles explaining the use of more complex/important parts of alloy

### Q. I tried to update ___ method using @Update, but it isn't working?

A. The way alloy is designed, is that everything is split up into components, and a method can only be updated if it is within a component.
you can read more about this topic [Here](https://github.com/GarrettBurroughs/Alloy/wiki/Alloy-Update-System)

### Q. What is a component/what is the difference between the components?
A. Different components have different purposes, for example, a control component would be used for an advanced control system,
whereas a motor component, or motor ocmponent group would be used to control a drivetrain or manipulator

### Q. What if I don't want to use components?

A. Things can be done in alloy without the use of components, but if you are designing code for a specific tool or component on the robot,
such as a shooter, drivetrain, or control system, it is highly recommended as components add extra functionality like toggleability,
easier debugging, and updateable methods. But again, all of this can be done without the use of components.

### Q. ___ isn't working.

A. If you have a problem first see our [Troubleshooting guide](https://github.com/GarrettBurroughs/Alloy/wiki/Troubleshooting-Guide). If that does not fix your problem you can file an [Issue](https://github.com/GarrettBurroughs/Alloy/issues/new)
using our issue template, please provide as much information as possible so we can fully understand the probelm and fix the issue.
