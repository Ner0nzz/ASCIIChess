import java.util.ArrayList;

/*
 * 
 * String[] messages
 * 0 - welcome 1
 * 1 - welcome 2
 * 2 - welcome 3
 * 3 - ask tutorial
 * 4 - tutorial 1
 * 5 - tutorial 2
 * 6 - tutorial 3
 * 7 - turn white
 * 8 - turn black
 * 9 - ask position 1
 * 10 - ask position 2
 * 11 - move error
 * 12 - error white check
 * 13 - error black check
 * 14 - error invalid move
 * 15 - error white turn
 * 16 - error black turn
 * 17 - white win
 * 18 - black win
 * 19 - program end 1
 * 20 - fill 1
 * 21 - fill 2
 * 22 - fill 3
 * 23 - invalid input
 * 24 - error no piece
 * 25 - not a valid position
 * 26 - pawn promotion
 * 27 - promotion options
 * 28 - current move number
 * 29 - message border
 * 
 */

public class GameMessenger {
	
	private ArrayList<String> messages;
	
	public GameMessenger() {
		this.messages = new ArrayList<String>();
		compileMessages();
	}
	
	private void compileMessages() {
		this.messages.add("Welcome to ascii chess, programmed by Richard G in June 2023 and updated in September 2025.");
		this.messages.add("Ascii chess is played entirely using the console. It normally requires two players but one amnesic player also works.");
		this.messages.add("To start a new game, type \"/new\" into the console.\nTo end the program at any time, type \"/exit\"");
		this.messages.add("Do you want to view a tutorial? y/n");
		this.messages.add("The chess board of ascii chess is arranged like so:");
		this.messages.add("_________________________________\n" + //
				"| R*|=N*| B*|=Q*| K*|=B*| N*|=R*| 8\n" + //
 				"|___|___|___|___|___|___|___|___|\n" + //
  				"|=P*| P*|=P*| P*|=P*| P*|=P*| P*| 7\n" + //
  				"|___|___|___|___|___|___|___|___|\n" + //
  				"|   |===|   |===|   |===|   |===| 6\n" + //
  				"|___|___|___|___|___|___|___|___|\n" + //
  				"|===|   |===|   |===|   |===|   | 5\n" + //
  				"|___|___|___|___|___|___|___|___|\n" + //
  				"|   |===|   |===|   |===|   |===| 4\n" + //
  				"|___|___|___|___|___|___|___|___|\n" + //
 				"|===|   |===|   |===|   |===|   | 3\n" + //
  				"|___|___|___|___|___|___|___|___|\n" + //
 				"| P |=P=| P |=P=| P |=P=| P |=P=| 2\n" + //
 				"|___|___|___|___|___|___|___|___|\n" + //
 				"|=R | N |=B=| Q |=K=| B |=N=| R | 1\n" + //
 				"|___|___|___|___|___|___|___|___|\n" + //
				"  a   b   c   d   e   f   g   h\n");
		this.messages.add("Squares that are black have an equals sign or \"=\" on them.\n" + //
				"The game rules and board setup are the same as standard chess.\n" + //
				"Pieces are represented by their first letter (with the exception of the knight, whose symbol is \"N\").\n" + //
				"Black pieces are distinguished with an asterisk in front of their symbol.\n" + //
				"For example, a black pawn on a black square would look like this: \"=P*\"\n" + //
				"To move a piece, first type in its position, press enter, then type in its ending position. The move/capture will then be played if the action is valid. \n" + //
				"For example, moving a pawn from e2 to e4 would require the player to type \"e2\", press enter, type \"e4\", and press enter again.\n" + //
				"This concludes the tutorial.");
		this.messages.add("It is now white's turn.");
		this.messages.add("It is now black's turn.");
		this.messages.add("What is the position of the piece you want to move?");
		this.messages.add("What is the ending position of that piece?");
		this.messages.add("!Error moving piece: ");
		this.messages.add("White's king is in check! Defend it or it may be taken next turn.");
		this.messages.add("Black's king is in check! Defend it or it may be taken next turn.");
		this.messages.add("That is not a valid move!");
		this.messages.add("It is not white's turn to move!");
		this.messages.add("It is not black's turn to move!");
		this.messages.add("White has won. Play again?");
		this.messages.add("Black has won. Play again?");
		this.messages.add("Thank you for playing ascii chess! *program end*");
		this.messages.add("");
		this.messages.add("");
		this.messages.add("");
		this.messages.add("!Invalid input, please reenter input!");
		this.messages.add("No piece in that spot!");
		this.messages.add("That is not a valid position!");
		this.messages.add("Please enter what you want to promote your pawn into.");
		this.messages.add("Promotion options: [Knight, Bishop, Rook, Queen]");
		this.messages.add("Current move: ");
		this.messages.add("================================================================");
	}
	
	public String getMessage(int index) {
		return this.messages.get(index);
	}

}


