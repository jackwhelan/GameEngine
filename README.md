# knightsriddle

I decided to do my own project idea: a game, because I wanted to do something that I had full creative control over.
I layed down what features were intended in my e-mail to you:

"Core Functionality:
The user explores; completes puzzles to unlock new areas and interacts with non player characters. Uses a time function in order to bottleneck powerful computers so the game is consistent across computers with different capabilities. 

Extras:
- In game currency to purchase in game items.
- Saving and loading game state.
- Health System for player and hostile entities.
- Varying item drop on entity death (e.g. common/rare items)"

In retrospect this was incredibly ambitious for the time frame, as this is still only the first major java project I've attempted.

I spent hours looking through the java graphics libraries attempting to understand them, aided by approximately 20 hours of online lectures I watched by Marcus Dubreil, mentioned once or twice in the comments of my code, for some of his code that I used. (http://www.marcusman.com)

Repo: http://www.github.com/jackwhelan/knightsriddle

Due to the initial ambitiousness of this project I will continue it after submission to fully complete it.

Even though the material substance of the game may seem minimal, you will see from my code how much work went behind every little detail, from storing buffered images in arrays of pixels, to rendering the array pixels to the screen consistently at 60 frames per second.

I have implemented a player character (no sprite, but graphics design was not my priority, so it is now a rectangle, temporarily.) The player can move, responsive to WASD or arrow keys. The left mouse button allows for placement of tiles.
The "map" that is rendered to the screen is customisable through a map.txt file. All of these features allow for scalability and easy changing of code without effecting too much existing code.

Effectively what is complete is a game engine. You can import 16 bit sprites and move them, place them, remove them etc with my existing code, as I have demonstrated by manipulating the spritesheet created by user "Sharm" on www.opengameart.org:
https://opengameart.org/content/16x16-town-remix

There may be something I forgot to mention but I'll be happy to explain during my demo.
