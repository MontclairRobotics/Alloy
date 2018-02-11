# Auto

The auto package contains all of the classes that control autonomous control and movements. <br>
Auto control in alloy is based of of state machines and autonomous states, and all of the auto framework <br>
can be found in the auto package.

## Contents

1. State Machine - A state machine is an object that takes in a number of states and then runs them, states can be ran<br> 
in a linear fashion by default, but can have more fine tuned control by the user. State machines can also be used outside <br>
of auto modes to run states in teleop. State machines are also states themselves so a state machine can run another state  machine

2. State - A state is an interface that can be implemented by other classes so that they can be run in a State Machine. <br>
states have functionality defined for when they are started, running, and stopped. States must also define a condition for <br>
when they have finished running

3. Alloy Autonomus - This is the basic framework for alloy auto's. An alloy autonomous takes care of running the state machine<br>
that the auto is using so all the user has to do is create the state machine and define functionality.
You can read more about how to use an Alloy Autonomous [Here](https://github.com/GarrettBurroughs/Alloy/wiki/Creating-An-Auto-Mode)