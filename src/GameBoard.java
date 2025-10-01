/*
 * _________________________________
 * | R*| N*| B*| Q*| K*| B*| N*| R*|
 * |___|___|___|___|___|___|___|___|
 * |   |   |   |   |   |   |   |   |
 * |___|___|___|___|___|___|___|___|
 * |   |   |   |   |   |   |   |   |
 * |___|___|___|___|___|___|___|___|
 * |   |   |   |   |   |   |   |   |
 * |___|___|___|___|___|___|___|___|
 * |   |   |   |   |   |   |   |   |
 * |___|___|___|___|___|___|___|___|
 * |   |   |   |   |   |   |   |   |
 * |___|___|___|___|___|___|___|___|
 * |   |   |   |   |   |   |   |   |
 * |___|___|___|___|___|___|___|___|
 * | R | N | B | Q | K | B | N | R |
 * |___|___|___|___|___|___|___|___|
 * 
 *     a   b   c   d   e   f   g   h
 *   _________________________________
 *   | R*|=N*| B*|=Q*| K*|=B*| N*|=R*|
 * 8 |___|___|___|___|___|___|___|___| 8
 *   |===|   |===|   |===|   |===|   |
 * 7 |___|___|___|___|___|___|___|___| 7
 *   |   |===|   |===|   |===|   |===|
 * 6 |___|___|___|___|___|___|___|___| 6
 *   |===|   |===|   |===|   |===|   |
 * 5 |___|___|___|___|___|___|___|___| 5
 *   |   |===|   |===|   |===|   |===|
 * 4 |___|___|___|___|___|___|___|___| 4
 *   |===|   |===|   |===|   |===|   |
 * 3 |___|___|___|___|___|___|___|___| 3
 *   |   |===|   |===|   |===|   |===|
 * 2 |___|___|___|___|___|___|___|___| 2
 *   |=R | N |=B=| Q |=K=| B |=N=| R |
 * 1 |___|___|___|___|___|___|___|___| 1
 *     a   b   c   d   e   f   g   h
 * 
 */

 import java.util.*;

public class GameBoard {
	
	private String stringBoard;
	private Piece[][] gameBoardState;

	/*
	 * 0 - no threats
	 * 1 - only threat from white
	 * 2 - only threat from black
	 * 3 - threatened by both colors
	 */
	private int[][] gameBoardThreats;
	
	public GameBoard() {
		this.gameBoardState = new Piece[8][8];
		this.gameBoardThreats = new int[8][8];
		resetGameBoard();
	}
	
	private void updateGameBoardString() {
		this.stringBoard = "_________________________________\n";
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				this.stringBoard += "|";
				if (this.gameBoardState[col][row] != null) {
					this.stringBoard += getPieceSeq(this.gameBoardState[col][row], col, row);
				}
				else {
					if (col % 2 == 0) {
						if (row % 2 == 0) {
							this.stringBoard += "   ";
						}
						else {
							this.stringBoard += "===";
						}
					}
					else {
						if (row % 2 == 0) {
							this.stringBoard += "===";
						}
						else {
							this.stringBoard += "   ";
						}
					}
				}
			}
			this.stringBoard += "| " + (8 - col) + "\n|___|___|___|___|___|___|___|___|\n";
		}
		this.stringBoard += "  a   b   c   d   e   f   g   h  \n";
	}
	
	private String getPieceSeq(Piece piece, int col, int row) {
		if (piece.getIsWhite()) {
			if (col % 2 == 0) {
				if (row % 2 == 0) {
					return " " + piece.getSymbol() + " ";
				}
				else {
					return "=" + piece.getSymbol() + "=";
				}
			}
			else {
				if (row % 2 == 0) {
					return "=" + piece.getSymbol() + "=";
				}
				else {
					return " " + piece.getSymbol() + " ";
				}
			}
		}
		else {
			if (col % 2 == 0) {
				if (row % 2 == 0) {
					return " " + piece.getSymbol() + "*";
				}
				else {
					return "=" + piece.getSymbol() + "*";
				}
			}
			else {
				if (row % 2 == 0) {
					return "=" + piece.getSymbol() + "*";
				}
				else {
					return " " + piece.getSymbol() + "*";
				}
			}
		}
	}
	
	public void resetGameBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.gameBoardState[i][j] = null;
			}
		}
		
		//Spawn Pawns
		for (int spawnPawnB = 0; spawnPawnB < 8; spawnPawnB++) {
			spawnPiece(new Pawn(spawnPawnB, 1, false));
		}
		for (int spawnPawnW = 0; spawnPawnW < 8; spawnPawnW++) {
			spawnPiece(new Pawn(spawnPawnW, 6, true));
		}

		//Spawn Knights
		spawnPiece(new Knight (1, 7, true));
		spawnPiece(new Knight (6, 7, true));
		spawnPiece(new Knight (1, 0, false));
		spawnPiece(new Knight (6, 0, false));

		//Spawn Bishops
		spawnPiece(new Bishop (2, 7, true));
		spawnPiece(new Bishop (5, 7, true));
		spawnPiece(new Bishop (2, 0, false));
		spawnPiece(new Bishop (5, 0, false));

		//Spawn Rooks
		spawnPiece(new Rook (0, 7, true, false));
		spawnPiece(new Rook (7, 7, true, false));
		spawnPiece(new Rook (7, 0, false, false));
		spawnPiece(new Rook (0, 0, false, false));

		//Spawn Queens
		spawnPiece(new Queen (3, 7, true));
		spawnPiece(new Queen (3, 0, false));
		
		//Spawn Kings
		spawnPiece(new King (4, 7, true, false));
		spawnPiece(new King (4, 0, false, false));
		updateThreats();
	}

	/**
	 * Updates all items in the two-dimensional array gameBoardThreats to contain the value 0
	 */
	public void resetThreats() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.gameBoardThreats[i][j] = 0;
			}
		}
	}
	
	public String getBoardString() {
		updateGameBoardString();
		return this.stringBoard;
	}
	
	public Piece[][] getBoard() {
		return this.gameBoardState;
	}

	/**
	 * Returns the two-dimensional array gameBoardThreats
	 */
	public int[][] getBoardThreats() {
		updateThreats();
		return this.gameBoardThreats;
	}
	
	public Piece getPieceByPos(int col, int row) {
		return this.gameBoardState[row][col];
	}
	
	public void removePiece(int col, int row) {
		//System.out.println("Removing piece at " + row + col);
		this.gameBoardState[row][col] = null;
	}
	
	public void movePiece(int col, int row, int newCol, int newRow) {
		removePiece(newCol, newRow);
		this.gameBoardState[newRow][newCol] = this.gameBoardState[row][col];
		this.gameBoardState[newRow][newCol].changeXPos(newCol);
		this.gameBoardState[newRow][newCol].changeYPos(newRow);
		this.gameBoardState[newRow][newCol].changeHasMoved();
		this.gameBoardState[row][col] = null;
	}
	
	public void spawnPiece(Piece piece) {
		removePiece(piece.getXPos(), piece.getYPos());
		this.gameBoardState[piece.getYPos()][piece.getXPos()] = piece;
	}

	/**
	 * Updates the two-dimensional array gameBoardThreats by calling updateThreatsByPiece for each piece in gameBoardState
	 */
	public void updateThreats() {
		resetThreats();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.gameBoardState[i][j] != null) {
					updateThreatsByPiece(this.gameBoardState[i][j]);
				}
			}
		}
	}
	/**
	 * Updates the two-dimensional array gameBoardThreats based on what squares the input piece threatens
	 * @param piece The input piece
	 */

	public void updateThreatsByPiece(Piece piece) {
		//Pawn check because taking behavior
		if (piece.getPieceType() == 0) {
			if (piece.getIsWhite()) {
				if (piece.getYPos() > 0) {
					if (piece.getXPos() > 0) {
						if (this.gameBoardThreats[piece.getYPos() - 1][piece.getXPos() - 1] == 2) {
							this.gameBoardThreats[piece.getYPos() - 1][piece.getXPos() - 1] = 3;
						}
						else if (this.gameBoardThreats[piece.getYPos() - 1][piece.getXPos() - 1] != 3) {
							this.gameBoardThreats[piece.getYPos() - 1][piece.getXPos() - 1] = 1;
						}
						else {
						}
					}
					if (piece.getXPos() < 7) {
						if (this.gameBoardThreats[piece.getYPos() - 1][piece.getXPos() + 1] == 2) {
							this.gameBoardThreats[piece.getYPos() - 1][piece.getXPos() + 1] = 3;
						}
						else if (this.gameBoardThreats[piece.getYPos() - 1][piece.getXPos() + 1] != 3) {
							this.gameBoardThreats[piece.getYPos() - 1][piece.getXPos() + 1] = 1;
						}
						else {
						}
					}
				}
				else {
				}
			}
			else {
				if (piece.getYPos() < 7) {
					if (piece.getXPos() > 0) {
						if (this.gameBoardThreats[piece.getYPos() + 1][piece.getXPos() - 1] == 1) {
							this.gameBoardThreats[piece.getYPos() + 1][piece.getXPos() - 1] = 3;
						}
						else if (this.gameBoardThreats[piece.getYPos() + 1][piece.getXPos() - 1] != 3) {
							this.gameBoardThreats[piece.getYPos() + 1][piece.getXPos() - 1] = 1;
						}
						else {
						}
					}
					if (piece.getXPos() < 7) {
						if (this.gameBoardThreats[piece.getYPos() + 1][piece.getXPos() + 1] == 1) {
							this.gameBoardThreats[piece.getYPos() + 1][piece.getXPos() + 1] = 3;
						}
						else if (this.gameBoardThreats[piece.getYPos() + 1][piece.getXPos() + 1] != 3) {
							this.gameBoardThreats[piece.getYPos() + 1][piece.getXPos() + 1] = 2;
						}
						else {
						}
					}
				}
				else {
				}
			}
		}
		//Every other piece
		else {
			ArrayList<Integer> validMoves = piece.getValidMoves(this.gameBoardState, 0, this.gameBoardThreats);
			if (piece.getIsWhite()) {
				for (int i = 0; i < validMoves.size(); i++) {
					int y = rawToCoords(validMoves.get(i))[0];
					int x = rawToCoords(validMoves.get(i))[1];
					if (this.gameBoardThreats[y][x] == 2) {
						this.gameBoardThreats[y][x] = 3;
					}
					else if (this.gameBoardThreats[y][x] != 3) {
						this.gameBoardThreats[y][x] = 1;
					}
					else {
					}
				}
			}
			else {
				for (int i = 0; i < validMoves.size(); i++) {
					int y = rawToCoords(validMoves.get(i))[0];
					int x = rawToCoords(validMoves.get(i))[1];
					if (this.gameBoardThreats[y][x] == 1) {
						this.gameBoardThreats[y][x] = 3;
					}
					else if (this.gameBoardThreats[y][x] != 3) {
						this.gameBoardThreats[y][x] = 2;
					}
					else {
					}
				}
			}
		}
	}

	/**
	 * Returns whether or not a king of the input color is in Check
	 * @param isWhite The color of the king to search for
	 * @return True or false depending on if the king of the input color is in Check. Returns false if no king is found in gameBoardState.
	 */
	public boolean checkForCheck(boolean isWhite) {
		if (isWhite) {
			for (int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					if (this.gameBoardState[i][j] != null && this.gameBoardState[i][j].getPieceType() == 5 && this.gameBoardState[i][j].getIsWhite()) {
						if (this.gameBoardThreats[i][j] == 3 || this.gameBoardThreats[i][j] == 2) {
							return true;
						}
						else {
							return false;
						}
					}
				}
			}
		}
		else {
			for (int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					if (this.gameBoardState[i][j] != null && this.gameBoardState[i][j].getPieceType() == 5 && !this.gameBoardState[i][j].getIsWhite()) {
						if (this.gameBoardThreats[i][j] == 3 || this.gameBoardThreats[i][j] == 1) {
							return true;
						}
						else {
							return false;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean checkWhiteVictory() {
		for (int i = 0; i < this.gameBoardState.length; i++) {
			for (int j = 0; j < this.gameBoardState[i].length; j++) {
				if (this.gameBoardState[i][j] != null) {
					if (this.gameBoardState[i][j].getName().equals("King") && !this.gameBoardState[i][j].getIsWhite()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean checkBlackVictory() {
		for (int i = 0; i < this.gameBoardState.length; i++) {
			for (int j = 0; j < this.gameBoardState[i].length; j++) {
				if (this.gameBoardState[i][j] != null) {
					if (this.gameBoardState[i][j].getName().equals("King") && this.gameBoardState[i][j].getIsWhite()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static int[] rawToCoords(int raw) {
		int[] coords = new int[2];
		coords[0] = Math.abs(raw) / 8;
		coords[1] = Math.abs(raw) % 8;
		//System.out.println("Raw: " + raw);
		//System.out.println("Target Coords: " + coords[1] + ", " + coords[0]);
		return coords;
	}
	
}
