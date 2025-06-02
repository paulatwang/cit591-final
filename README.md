# BattleShip

 This is a one-player game of Battleship, where the computer places the ships and the human player attempts to sink them. The game will take place on a 10x10 ocean with the following fleet:

| Ship type	| Ship length |	Quantity| 
| -- | -- | -- |
| Battleship	| 4	| 1 |
| Cruiser |	3	| 2 |
| Destroyer	| 2	| 3 |
| Submarine	| 1	| 4 |

The computer will place the ten ships in the ocean in such a way that no ships are immediately adjacent to each other horizontally, vertically, or diagonally. To start, the human player will not know where the ships are. The initial display of the ocean will show a 10x10 array of locations, all the same. The human player tries to hit the ships by providing a row and column number. The computer will respond with a single message: `hit`, if the coordinate corresponds to a tile containing a ship, or `miss` if the coordinate corresponds to an empty ocean tile.

A ship is “sunk” when every tile making up the ship has been hit. It takes four hits (in the four different tiles) to sink a battleship, but only one hit to sink a submarine. When a ship is hit but not sunk, the program does not provide any information about what kind of a ship was hit. However, when the last tile of a ship is hit and the ship sinks, the program will print out the message: You just sunk a <`SHIP_TYPE`>.

After each shot is taken, the computer will display the ocean with all information about all tiles attacked, including a marking for hits and for missses.

The object of the game is to sink the fleet with as few shots as possible; therefore, the best possible score is 20, and the worst possible score is 100.
 
<img width="606" alt="Screenshot 2025-06-02 at 06 56 05" src="https://github.com/user-attachments/assets/26ad7c05-2524-46f0-a1fd-0a2bb4d58a7c" />

# To Play

Run BattleshipGame.java
