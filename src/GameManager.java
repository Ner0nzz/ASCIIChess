import java.util.*;

public class GameManager {
	
	private Scanner console;
	
	private GameBoard board;
	private GameMessenger messenger;
	
	private int moveCount;
	private boolean activeGame;
	private boolean isWhiteMove;
	private boolean hasStartInput;
	private Piece startPiece;

	private String currentInput;
	private String previousInput;
	
	public GameManager() {
		console = new Scanner(System.in);
		
		this.board = new GameBoard();
		this.messenger = new GameMessenger();
		
		this.moveCount = 0;
		this.activeGame = false;
		this.isWhiteMove = true;
		this.hasStartInput = false;

		this.previousInput = "";
		this.currentInput = "start";
		
		displayWelcome();

		update(this.currentInput);
	}

	private void update(String input) {

		if (input.equals("/exit")) {
			this.previousInput = "/exit";
			System.out.println(this.messenger.getMessage(19));
		}
		else if (this.activeGame) {
			System.out.println(board.getBoardString());
			updateGame();
			if (board.checkBlackVictory()) {
				System.out.println(this.messenger.getMessage(29));
				System.out.println(this.messenger.getMessage(18));
				this.activeGame = false;
				this.currentInput = console.nextLine();
				this.previousInput = "";
				update(this.currentInput);
			}
			else if (board.checkWhiteVictory()) {
				System.out.println(this.messenger.getMessage(29));
				System.out.println(this.messenger.getMessage(17));
				this.activeGame = false;
				this.currentInput = console.nextLine();
				this.previousInput = "";
				update(this.currentInput);
			}
			
			else {
				update("");
			}
		}

		else if (input.equals("start")) {
			System.out.println(this.messenger.getMessage(2));
			this.previousInput = input;
			this.currentInput = this.console.nextLine();
			update(this.currentInput);
		}

		else if (input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
			if (this.previousInput.equals("/new")) {
				System.out.println(this.messenger.getMessage(4));
				System.out.println(this.messenger.getMessage(5));
				System.out.println(this.messenger.getMessage(6));
				System.out.println(this.messenger.getMessage(29));
				startNewGame();
				update("");
			}
			else {
				startNewGame();
				update("");
			}
		}

		else if (input.toLowerCase().equals("n") || input.toLowerCase().equals("no")) {
			if (this.previousInput.equals("/new")) {
				startNewGame();
				update("");
			}
			else {
				update("/exit");
			}
		}

		else if (input.equals("/new")) {
			System.out.println(this.messenger.getMessage(3));
			this.previousInput = this.currentInput;
			this.currentInput = this.console.nextLine();
			update(this.currentInput);
		}

		else { 
			if (!this.previousInput.equals("/exit")) {
				System.out.println(this.messenger.getMessage(23));
				this.currentInput = this.console.nextLine();
				update(this.currentInput);
			}
		}
	}
	
	private void displayWelcome() {
		System.out.println(this.messenger.getMessage(0));
		System.out.println(this.messenger.getMessage(1));
	}

	private void updateGame() {
		if (hasStartInput) {
			System.out.println(this.messenger.getMessage(10));
			this.currentInput = this.console.nextLine();
			if (this.currentInput.equals("/exit")) {
				this.previousInput = "/exit";
				this.activeGame = false;
				update("/exit");
			}
			else if (!checkValidInput(this.currentInput)) {
				System.out.println(this.messenger.getMessage(11));
				System.out.println(this.messenger.getMessage(25));
				updateGame();
			}
			else if (checkValidMove(this.startPiece, rawToCoords(inputToRaw(this.currentInput))[0], rawToCoords(inputToRaw(this.currentInput))[1])) {
				//Check en passant exception by checking if new position's raw value is negative and if it's a pawn
				if (this.startPiece.getPieceType() == 0 && isEnPassant(this.startPiece, rawToCoords(inputToRaw(this.currentInput))[0], rawToCoords(inputToRaw(this.currentInput))[1])) {
					if (this.startPiece.getIsWhite()) {
						board.removePiece(rawToCoords(inputToRaw(this.currentInput))[1], rawToCoords(inputToRaw(this.currentInput))[0] + 1);
					}
					else {
						board.removePiece(rawToCoords(inputToRaw(this.currentInput))[1], rawToCoords(inputToRaw(this.currentInput))[0] - 1);
					}
				}

				//Check castle exception by checking if new position's raw value is negative and if it's a king
				if (this.startPiece.getPieceType() == 5 && isCastle(this.startPiece, rawToCoords(inputToRaw(this.currentInput))[0], rawToCoords(inputToRaw(this.currentInput))[1])) {
					if (this.startPiece.getIsWhite()) {
						//Castle king side
						if (inputToRaw(this.currentInput) == 62) {
							board.movePiece(7, 7, 5, 7);
						}
						//Castle Queen side
						else {
							board.movePiece(0, 7, 3, 7);
						}
					}
					else {
						//Castle king side
						if (inputToRaw(this.currentInput) == 6) {
							board.movePiece(7, 0, 5, 0);
						}
						//Castle Queen side
						else {
							board.movePiece(0, 0, 3, 0);
						}
					}
				}

				board.movePiece(this.startPiece.getXPos(), this.startPiece.getYPos(), rawToCoords(inputToRaw(this.currentInput))[1], rawToCoords(inputToRaw(this.currentInput))[0]);
				
				//Promotion check
				this.startPiece = board.getPieceByPos(rawToCoords(inputToRaw(this.currentInput))[1], rawToCoords(inputToRaw(this.currentInput))[0]);
				if (isPromotion(this.startPiece, this.startPiece.getYPos())) {
					promotionHandler(this.startPiece.getYPos(), this.startPiece.getXPos());
				}

				this.isWhiteMove = !this.isWhiteMove;
				hasStartInput = false;
				//System.out.println("Move successful");
				if (this.isWhiteMove) {
					this.moveCount += 1;
				}
				board.updateThreats();
			}
			else {
				System.out.println(this.messenger.getMessage(11));
				System.out.println(this.messenger.getMessage(14));
				hasStartInput = false;
				updateGame();
			}
		}
		else {
			System.out.println(this.messenger.getMessage(28) + (moveCount + 1));
			if (this.isWhiteMove) {
				System.out.println(this.messenger.getMessage(7));
				if (board.checkForCheck(true)) {
					System.out.println(this.messenger.getMessage(12));
				}
			}
			else {
				System.out.println(this.messenger.getMessage(8));
				if (board.checkForCheck(false)) {
					System.out.println(this.messenger.getMessage(13));
				}
			}
			System.out.println(this.messenger.getMessage(9));
			this.currentInput = this.console.nextLine();
			if (this.currentInput.equals("/exit")) {
				this.previousInput = "/exit";
				this.activeGame = false;
				update("/exit");
			}
			else {
				if (!checkValidInput(this.currentInput)) {
					System.out.println(this.messenger.getMessage(11));
					System.out.println(this.messenger.getMessage(25));
				}
				else if (board.getBoard()[inputToCoords(this.currentInput)[0]][inputToCoords(this.currentInput)[1]] == null) {
					System.out.println(this.messenger.getMessage(11));
					System.out.println(this.messenger.getMessage(24));
				}
				else if (this.isWhiteMove && !board.getBoard()[inputToCoords(this.currentInput)[0]][inputToCoords(this.currentInput)[1]].getIsWhite()) {
					System.out.println(this.messenger.getMessage(11));
					System.out.println(this.messenger.getMessage(16));
				}
				else if (!this.isWhiteMove && board.getBoard()[inputToCoords(this.currentInput)[0]][inputToCoords(this.currentInput)[1]].getIsWhite()) {
					System.out.println(this.messenger.getMessage(11));
					System.out.println(this.messenger.getMessage(15));
				}
				else {
					this.hasStartInput = true;
					this.startPiece = board.getBoard()[inputToCoords(this.currentInput)[0]][inputToCoords(this.currentInput)[1]];
				}
				updateGame();
			}
		}
	}
	
	/*
	private void endGame(int winner) {
		if (winner == 0) {
			this.blackWinCount += 1;
		}
		else {
			this.whiteWinCount += 1;
		}
		this.activeGame = false;
	}
	*/

	private static int inputToRaw(String input) {
		return (7 - (input.charAt(1) - 48 - 1)) * 8 + input.charAt(0) - 97;
	}
	
	private static int[] rawToCoords(int raw) {
		int[] coords = new int[2];
		coords[0] = Math.abs(raw) / 8;
		coords[1] = Math.abs(raw) % 8;
		return coords;
	}

	private static int[] inputToCoords(String input) {
		return rawToCoords(inputToRaw(input));
	}

	/**
	 * Checks if a move input by the user into the terminal is a valid position on a chess board.
	 * @param input The string input by the user
	 * @returns True if the string input is a valid position on a chess board. False if the input is- 
	 * -not two characters long, the first character isn't a letter, the second character isn't a-
	 * -number, or the input is not a valid position on a chessboard.
	 */
	public boolean checkValidInput(String input) {
		if (input.length() == 2) {
			if (input.charAt(0) > 96 && input.charAt(0) < 105) {
				if (input.charAt(1) > 48 && input.charAt(1) < 57) {
					if (checkValidPos(inputToCoords(input)[0], inputToCoords(input)[1])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Checks if two numbers are less than 8 and are greater than -1
	 * @param yPos The first number
	 * @param xPos The second number
	 * @return True or false if the value of both numbers are between 8 and -1.
	 */
	public boolean checkValidPos(int yPos, int xPos) {
		if (yPos < 8 && yPos > -1) {
			if (xPos < 8 && xPos > -1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns whether a move made by a piece is within the game rules.
	 * @param piece The piece making the move
	 * @param targetRow The row of the ending position of the move made by the piece on the game board
	 * @param targetCol The column of the ending position of the move made by the piece on the game board
	 */
	public boolean checkValidMove(Piece piece, int targetRow, int targetCol) {
		for (int i = 0; i < piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).size(); i++) {
			if (rawToCoords(piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).get(i))[1] == targetCol && rawToCoords(piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).get(i))[0] == targetRow) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns whether a move made by a piece is defined as "En Passant"
	 * @param piece The selected piece
	 * @param targetRow The row of the ending position of the move made by the piece on the game board
	 * @param targetCol The column of the ending position of the move made by the piece on the game board
	 * @requires piece.getPieceType() == 0; (piece is a pawn)
	 * @return True if targetRow and targetCol are within the subset of valid moves piece can make, and the raw value of that ending position is negative.
	 */
	public boolean isEnPassant(Piece piece, int targetRow, int targetCol) {
		for (int i = 0; i < piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).size(); i++) {
			if (rawToCoords(piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).get(i))[1] == targetCol && rawToCoords(piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).get(i))[0] == targetRow) {
				if (piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).get(i) < 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns whether a move made by a piece is defined as "Castle"
	 * @param piece The selected piece
	 * @param targetRow The row of the ending position of the move made by the piece on the game board
	 * @param targetCol The column of the ending position of the move made by the piece on the game board
	 * @requires piece.getPieceType() == 5; (piece is a king)
	 * @return True if targetRow and targetCol are within the subset of valid moves piece can make, and the raw value of that ending position is negative.
	 */
	public boolean isCastle(Piece piece, int targetRow, int targetCol) {
		for (int i = 0; i < piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).size(); i++) {
			if (rawToCoords(piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).get(i))[1] == targetCol && rawToCoords(piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).get(i))[0] == targetRow) {
				if (piece.getValidMoves(board.getBoard(), this.moveCount, board.getBoardThreats()).get(i) < 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns whether a move made by a piece is defined as "Promotion"
	 * @param piece The selected piece
	 * @param yPos The row of the ending position of the move made by the piece on the game board
	 * @return True if piece is a pawn and the piece is at the corresponding promotion row of its color
	 */
	public boolean isPromotion(Piece piece, int yPos) {
		if (piece.getPieceType() == 0) {
			if (piece.getIsWhite()) {
				if (yPos == 0) {
					return true;
				}
		}
			else {
				if (yPos == 7) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Updates the position on the chessboard specified by two numbers corresponding to the row and column
	 * @param yPos The target row
	 * @param xPos the target column
	 */
	public void promotionHandler(int yPos, int xPos) {
		System.out.println(this.messenger.getMessage(26));
		System.out.println(this.messenger.getMessage(27));

		this.currentInput = (this.console.nextLine()).toLowerCase();

		int selection = isValidPromotionInput(this.currentInput);

		if (selection != -1) {
			if (selection == 1) {
				board.spawnPiece(new Knight(xPos, yPos, board.getPieceByPos(xPos, yPos).getIsWhite()));
			}
			else if (selection == 2) {
				board.spawnPiece(new Bishop(xPos, yPos, board.getPieceByPos(xPos, yPos).getIsWhite()));
			}
			else if (selection == 3) {
				board.spawnPiece(new Rook(xPos, yPos, board.getPieceByPos(xPos, yPos).getIsWhite(), true));
			}
			else {
				board.spawnPiece(new Queen(xPos, yPos, board.getPieceByPos(xPos, yPos).getIsWhite()));
			}
		}
		else {
			System.out.println(this.messenger.getMessage(23));
			promotionHandler(yPos, xPos);
		}
	}

	/**
	 * Verifies if a string input by the user is a valid promotion and returns the translation of that value to promotionHandler
	 * @param input The promotion string input by the user
	 * @return -1 if input is invalid, otherwise a number between 1 and 4
	 */
	public int isValidPromotionInput(String input) {
		String[] validInputs = {"knight", "bishop", "rook", "queen"};
		char[] validInputsLetter = {'k', 'b', 'r', 'q'};

		for (int i = 0; i < validInputs.length; i++) {
			if (input.length() == 1) {
				if (input.charAt(0) == validInputsLetter[i]) {
					return i + 1;
				}
			}
			if (input.equals(validInputs[i])) {
				return i + 1;
			}
		}
		return -1;

	}
	
	public void startNewGame() {
		board.resetGameBoard();
		this.moveCount = 0;
		this.activeGame = true;
		this.isWhiteMove = true;
	}
	
	public void forceEndGame() {
		this.activeGame = false;
	}
	
}

