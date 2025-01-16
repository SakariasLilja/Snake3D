# Snake3D - JavaFX Application
> Author: Sakarias Lilja

## Table of contents:
1. Purpose of application
1. Idea of application
1. Rendering
1. Data Storage/Manipulation

## 1. Purpose of application
This application was made using Java utilising the JavaFX library for producing in interactable UI, Gradle for managing dependencies and running the application, and Gson to handle I/O operations. The font used for the text is Doto by Óliver Lalan, available at Google Fonts. Outside these dependencies, the project is entirely written by the author.

This project is made to showcase expertise in Java, CRUD processes, UML design, software testing, software quality management, and to showcase the benefit of utilising TDD in programming projects.

For a more in-depth explanation of the application outside this README, inspect "Snake3D - Plan Documentation" in the documentation folder located in this repository.

## 2. Idea of application
Snake3D is a 3D adaptation of the popular game snake. This version adopts similar principles, such as eating apples to grow and collisions causing the game to end. The differences lie in the other aspects. 

The most obvious difference is the additional dimension used in rendering. This itself changes the experience, enabling the user to move in two additioal axes. 

The game has been written to be very customizable, allowing for in the future the implementation of custom in-game colors and custom world dimensions.

Due to this potential of large worlds and long-lasting games, the option to save the progress and resume at another time has been implemented. More regarding this process is discussed in point 4: Data Storage/Manipulation.

## 3. Rendering
The game has a custom built renderer. The look of the game is easily customizable, by changing values of some of the constant variables, such as the field of view (FOV). 

The renderer camera works on the principles of perspective rendering, negative z -culling, and will also in the future include backface culling. These all improve the performance of the rendering. 

More in-depth information regarding the rendering process and algorithms can be found in the documentation file.

## 4. Data Storage/Manipulation
To store the data produced by the game, the game utilises Gson to read and write JSON files. Gson was chosen as the JSON-manipulator, as it's quite simple. Performance is not the best with Gson, but its simplicity far exceeds the costs.

All of the data files are non-encrypted and stored in an accessible place, as to allow for modification by the user outside of the game. This access does however allow for the files to be incorrectly formated. For this reason when the game detects a worngly formated file, it will save its contents into a backup file. This file can then be accessed by the user to recover their previous data. There will however only exist one backup file, so data can still be lost if not immeadiately recovered.