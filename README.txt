Futoshiki: v1.0


Author: bdtw20


Instruction:

The goal of the game is to solve the puzzle by enter in the right numbers in each square. If you insert a number is not discrete in its row or column(if there another number with equal value) the game will indicate the error.


You can select what number you want to input by clicking on the toggle buttons on the left hand side. As well as this you can change to pencil mode by clicking on the pencil icon to mark numbers in a box and not have it checked if its correct.


The way the UI displays the grid is the use of Vbox in Hbox. The VBox will be filled with buttons which will take the data from the Futoshiki object and place them in the right place.


As well as the game itself, I added additional features which I thought would make the game more like a game. I firstly added sound. I made a music class which controls the sound being played in the game such as change volume and swap clips depending on what state of the game it is in. There is a option and setting the user can open during their game and in the main menu to change the volume of the music or mute it.


I implemented my own graphics. Equally with the music, I made a graphics class to be able to control what images should be used for what UI node. These graphics can be seen during when the user plays the game and the win and lose screen.


Lastly I implemented a save and load functionality so that the user can save their progress. There is a quick save to save a game under a predefined directory as well as a save as where the user can choose the name of the file and directory. The user can load back their game in the main menu to any previously saved games.