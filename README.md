<dl>
	<img src="https://github.com/Chioxin/JetsProject/blob/master/Airport.jpg?raw=true" alt="An Airport" ALIGN="right" width="450"/>
</dl>

## Jets Application

Created an application that tracks the parking spots it an Air Field. A simple menu interface allows users to list the Fleet, send all jets flying, find the fastest jet, longest range jet, load all cargo jets, dog fight all combat ready jets, and add a jet to the Fleet.

This is Week 2 [Skill Distillery](http://skilldistillery.com) weekend homework.

###Technologies/Topics
* Java
* Object-oriented design
 * Polymorphism
 * Inheritance
 * Abstraction
 * Encapsulation
* UML
* Github
* Eclipse

###UML
<dl>
	<img src="https://github.com/Chioxin/JetsProject/blob/master/UMLJets.png?raw=true" ALIGN="right" alt="UML Diagram" width="400">
</dl>

This is the UML we were given to follow. I did create a MenuInterface Class to handle inputs and displays for the user. A CombatEngine Class was also added to handle the mini game found in the program.

###Stretch
* I built a graphical display for the Air Field that will grab information about the jet in that spot and it will display its name beside it. There is a jet graphic for each type of jet. This particular stretch goal had to check instances of Jet to display the correct graphic, as well as reduce a jet name down to 5 characters to display properly in the graphical display.
* Created a combat engine class that allows user to take a get out and play a mini game with it. If they lose, the jet is removed from the Air Field.