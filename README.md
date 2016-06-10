asdfasfdsdsafdsfadldsafsdafd
dafasdfadfdafasdfads
# Mesosphere-Coding-Challenge
Implementation of an Elevator Control System

###Problem Specifications
Build a basic Elevator Control System 
 
###Overall Design
Language: Java 

The overall design of the ElevatorControlSystemImpl class implements an interface similar to the one specified in the original problem, though slightly modified. To address the issue of an elevator having multiple people/destinations, I used a list of Integers to hold all the destinations an elevator has. As an elevator moves, it checks its list of destinations to see if it has arrived at any of them. Additionally, I modified the update() method in the ECS interface and elected to divide it into the updateDestination() and updateFloor() methods, since I found while trying to implement the original update() that there were times I wanted to change the destination but not the floor, and vice-versa.

Additionally, I created an Elevator class to represent the elevators within the system, as well as two additional classes to represent elevator statuses and requests.

In general, computational time complexity was not on my mind when implementing this (i.e. I didn't think twice about using a for loop to check all requests/elevators) since the problem specifed that the total number of elevators will never be greater than 16. My main focus was maximizing the time efficiency of the elevators, in other words picking up the most amount of people as fast/efficient as possible. Additionally, in the real-world the number of elevators(N) would also be low - even for large buildings.

###Scheduling Algorithm
My scheduling algorithm is based on the way the elevators in my dorm (a high-rise with 4 elevators for ~500 people) work. Empty elevators are assigned on a first-come-first-serve basis, unless there is an elevator headed in your direction. In which case, the latter is a more ideal elevator (since it is already coming towards you) and will be assigned your pickup request. Additionally, if there are multiple elevators headed towards you, the closest one will be assigned. Elevators without any people are reassigned an empty state until there is a new pickup request for them to handle. This way of scheduling naturally does some load balancing, and in a long-enough running system should engage all the elevators equally. 

###Testing/To Do's
Due to time constraints, my testing framework uses print-statements and prints out the status of an ECS during various situations. This is not ideal, since we would want to use Assert to check that the ECS is operating as it should and properly use the JUnit testing framework. Printing was faster/easier for me at the time, since I could eye-check how the ECS was performing. So, the big To-Do is to test much more thoroughly and modify my tests to use Assert and properly check values.

Moreover, implementing some type of maintenance state that ignores all requests for an elevator would have been nice, since that happens in the real world. An emergency state in which all elevators are deactivated would have been cool, too.

Additionally, there are numerous improvements that can be made to the scheduling algorithm. For instance, if all the elevators are idle for a given time, they should all return to the lobby. Or perhaps, half should return to the lobby and half should return to the middle floor (you can think of this as the ECS running at 3AM and where you would like the elevators to be at 8am when class starts). 

Lastly, I did not do much accounting for bad inputs at the time. One can make the argument that there wouldn't be bad inputs since all these calls would be given by the buttons pressed in the real world (which are all valid), but I would have liked to have been able to go through and include null/bad case tests.



