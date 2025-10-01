Two-player chess that can be played using the terminal.

A lot of the code was originally written back in June 2023 when I was still in high school. I decided to finish programming this in September 2025. I hope you can also see the change in quality. 

(　´∀｀)b

```
Do you want to view a tutorial? y/n
y
The chess board of ascii chess is arranged like so:
_________________________________
| R*|=N*| B*|=Q*| K*|=B*| N*|=R*| 8
|___|___|___|___|___|___|___|___|
|=P*| P*|=P*| P*|=P*| P*|=P*| P*| 7
|___|___|___|___|___|___|___|___|
|   |===|   |===|   |===|   |===| 6
|___|___|___|___|___|___|___|___|
|===|   |===|   |===|   |===|   | 5
|___|___|___|___|___|___|___|___|
|   |===|   |===|   |===|   |===| 4
|___|___|___|___|___|___|___|___|
|===|   |===|   |===|   |===|   | 3
|___|___|___|___|___|___|___|___|
| P |=P=| P |=P=| P |=P=| P |=P=| 2
|___|___|___|___|___|___|___|___|
|=R | N |=B=| Q |=K=| B |=N=| R | 1
|___|___|___|___|___|___|___|___|
  a   b   c   d   e   f   g   h

Squares that are black have an equals sign or "=" on them.
The game rules and board setup are the same as standard chess.
Pieces are represented by their respective first letter in their names (with the exception of the knight, whose symbol is "N").
Black pieces are distinguished with an asterisk in front of their symbol.
For example, a black pawn on a black square would look like this: "=P*"
To move a piece, first type in its position, press enter, then type in its ending position. The move/capture will then be played if the action is valid. 
For example, moving a pawn from e2 to e4 would require the player to type "e2", press enter, type "e4", and press enter again.
This concludes the tutorial.
================================================================
Current move: 1
It is now white's turn.
What is the position of the piece you want to move?
e2
What is the ending position of that piece?
e4
_________________________________
| R*|=N*| B*|=Q*| K*|=B*| N*|=R*| 8
|___|___|___|___|___|___|___|___|
|=P*| P*|=P*| P*|=P*| P*|=P*| P*| 7
|___|___|___|___|___|___|___|___|
|   |===|   |===|   |===|   |===| 6
|___|___|___|___|___|___|___|___|
|===|   |===|   |===|   |===|   | 5
|___|___|___|___|___|___|___|___|
|   |===|   |===| P |===|   |===| 4
|___|___|___|___|___|___|___|___|
|===|   |===|   |===|   |===|   | 3
|___|___|___|___|___|___|___|___|
| P |=P=| P |=P=|   |=P=| P |=P=| 2
|___|___|___|___|___|___|___|___|
|=R=| N |=B=| Q |=K=| B |=N=| R | 1
|___|___|___|___|___|___|___|___|
  a   b   c   d   e   f   g   h  
```
