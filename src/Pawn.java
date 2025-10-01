import java.util.ArrayList;

public class Pawn implements Piece {
	
	private int type;
	private String name;
	private String symbol;
	private int xPos;
	private int yPos;
	private boolean isWhite;
	private boolean hasMoved;
	private int enPassantTurn;
	
	private ArrayList<Integer> validMovesRaw;
	
	public Pawn(int x, int y, boolean isWhite) {
		this.type = 0;
		this.name = "Pawn";
		this.symbol = "P";
		this.isWhite = isWhite;
		this.xPos = x;
		this.yPos = y;
		
		validMovesRaw = new ArrayList<Integer>();
	}
	
	public void Update() {
		this.validMovesRaw.clear();
	}
	
	private void updateValidMoves(Piece[][] gameBoardState, int moveCount) {
		this.validMovesRaw.clear();
		//White moves upwards
		if (this.isWhite) {
			if (gameBoardState[this.yPos - 1][this.xPos] == null) {
				validMovesRaw.add((this.yPos - 1) * 8 + this.xPos);
			}
			if (this.xPos != 0 && gameBoardState[this.yPos - 1][this.xPos - 1] != null && !gameBoardState[this.yPos - 1][this.xPos - 1].getIsWhite()) {
				validMovesRaw.add((this.yPos - 1) * 8 + this.xPos - 1);
			}
			if (this.xPos != 7 && gameBoardState[this.yPos - 1][this.xPos + 1] != null && !gameBoardState[this.yPos - 1][this.xPos + 1].getIsWhite()) {
				validMovesRaw.add((this.yPos - 1) * 8 + this.xPos + 1);
			}


			//en passant left
			if (this.xPos != 0 && gameBoardState[this.yPos][this.xPos - 1] != null && !gameBoardState[this.yPos][this.xPos - 1].getIsWhite() && gameBoardState[this.yPos][this.xPos - 1].getEnPassant(moveCount)) {
				if (gameBoardState[this.yPos - 1][this.xPos - 1] == null || !gameBoardState[this.yPos - 1][this.xPos - 1].getIsWhite()) {
					//if en passant is negative, store move as negative
					validMovesRaw.add(0 - ((this.yPos - 1) * 8 + this.xPos - 1));
				}
			}
			//en passant right
			if (this.xPos != 7 && gameBoardState[this.yPos][this.xPos + 1] != null && !gameBoardState[this.yPos][this.xPos + 1].getIsWhite() && gameBoardState[this.yPos][this.xPos + 1].getEnPassant(moveCount)) {
				if (gameBoardState[this.yPos - 1][this.xPos + 1] == null || !gameBoardState[this.yPos - 1][this.xPos + 1].getIsWhite()) {
					validMovesRaw.add(0 - ((this.yPos - 1) * 8 + this.xPos + 1));
				}
			}

			if (!hasMoved) {
				if (gameBoardState[this.yPos - 2][this.xPos] == null && gameBoardState[this.yPos - 1][this.xPos] == null) {
					validMovesRaw.add((this.yPos - 2) * 8 + this.xPos);
					this.enPassantTurn = moveCount;
				}
			}
		}
		
		//Black moves downwards
		else {
			if (gameBoardState[this.yPos + 1][this.xPos] == null) {
				validMovesRaw.add((this.yPos + 1) * 8 + this.xPos);
			}
			if (this.xPos != 0 && gameBoardState[this.yPos + 1][this.xPos - 1] != null && gameBoardState[this.yPos + 1][this.xPos - 1].getIsWhite()) {
				validMovesRaw.add((this.yPos + 1) * 8 + this.xPos - 1);
			}
			if (this.xPos != 7 && gameBoardState[this.yPos + 1][this.xPos + 1] != null && gameBoardState[this.yPos + 1][this.xPos + 1].getIsWhite()) {
				validMovesRaw.add((this.yPos + 1) * 8 + this.xPos + 1);
			}

			//en passant left
			if (this.xPos != 0) {
				if (gameBoardState[this.yPos][this.xPos - 1] != null) {
					if (gameBoardState[this.yPos][this.xPos - 1].getIsWhite()) {
						if (gameBoardState[this.yPos][this.xPos - 1].getEnPassant(moveCount)) {
							if (gameBoardState[this.yPos + 1][this.xPos - 1] == null || gameBoardState[this.yPos + 1][this.xPos - 1].getIsWhite()) {
								validMovesRaw.add(0 - ((this.yPos + 1) * 8 + this.xPos - 1));
							}
						}
					}
				}
			}
			//en passant right
			if (this.xPos != 7 && gameBoardState[this.yPos][this.xPos + 1] != null && gameBoardState[this.yPos][this.xPos + 1].getIsWhite() && gameBoardState[this.yPos][this.xPos + 1].getEnPassant(moveCount)) {
				if (gameBoardState[this.yPos + 1][this.xPos + 1] == null || gameBoardState[this.yPos + 1][this.xPos + 1].getIsWhite()) {
					validMovesRaw.add(0 - ((this.yPos + 1) * 8 + this.xPos + 1));
				}
			}

			//check for if the pawn has moved forward two spaces yet
			if (!hasMoved) {
				if (gameBoardState[this.yPos + 2][this.xPos] == null) {
					validMovesRaw.add((this.yPos + 2) * 8 + this.xPos);
					this.enPassantTurn = moveCount;
				}
			}
		}
	}
	
	public ArrayList<Integer> getValidMoves(Piece[][] gameBoardState, int moveCount) {
		updateValidMoves(gameBoardState, moveCount);
		return this.validMovesRaw;
	}

	public ArrayList<Integer> getValidMoves(Piece[][] gameBoardState, int moveCount, int[][] gameBoardThreats) {
        return getValidMoves(gameBoardState, moveCount);
    }

	public int getPieceType() {
		return this.type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public boolean getIsWhite() {
		return this.isWhite;
	}
	
	public int getXPos() {
		return this.xPos;
	}
	
	public int getYPos() {
		return this.yPos;
	}

	public boolean getHasMoved() {
        return this.hasMoved;
    }

	public void changeXPos(int x) {
		this.xPos = x;
	}

	public void changeYPos(int y) {
		this.yPos = y;
	}

	public void changeHasMoved() {
		this.hasMoved = true;
	}

	public boolean getEnPassant(int moveCount) {
		if (this.isWhite) {
			if (moveCount - this.enPassantTurn == 0) {
				return true;
			}
		}
		else {
			if (moveCount - this.enPassantTurn <= 1) {
				return true;
			}
		}
		return false;
	}
	
}

