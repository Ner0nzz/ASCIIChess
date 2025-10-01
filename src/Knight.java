import java.util.*;

public class Knight implements Piece {

    private int type;
    private String name;
	private String symbol;
	private int xPos;
	private int yPos;
	private boolean isWhite;
	private boolean hasMoved;
	
	private ArrayList<Integer> validMovesRaw;

    public Knight(int x, int y, boolean isWhite) {
        this.type = 1;
		this.name = "Knight";
		this.symbol = "N";
		this.isWhite = isWhite;
		this.xPos = x;
		this.yPos = y;
		
		validMovesRaw = new ArrayList<Integer>();
	}

    public void Update() {
		this.validMovesRaw.clear();
	}
    
    public ArrayList<Integer> getValidMoves(Piece[][] gameBoardState, int moveCount, int[][] gameBoardThreats) {
        return getValidMoves(gameBoardState, moveCount);
    }

    public ArrayList<Integer> getValidMoves(Piece[][] gameBoardState, int moveCount) {
		updateValidMoves(gameBoardState, moveCount);
		return this.validMovesRaw;
	}

    /**
     * Clears the ArrayList validMovesRaw and adds integers corresponding to all valid moves that this piece may take if it is its color's turn to move
     * @param gameBoardState A two-dimensional array containing the pieces in their respective positions on the chessboard
     * @param moveCount The number of moves that have passed since the start of the game. Is not used in this method and is included because of the Piece implementaiton.
     */
    private void updateValidMoves(Piece[][] gameBoardState, int moveCount) {
        this.validMovesRaw.clear();

        //Check up two
        if (this.yPos - 2 > -1) {
            if (this.xPos - 1 > -1) {
                if (gameBoardState[this.yPos - 2][this.xPos - 1] == null || this.isWhite != gameBoardState[this.yPos - 2][this.xPos - 1].getIsWhite()) {
                    validMovesRaw.add((this.yPos - 2) * 8 + this.xPos - 1);
                }
                validMovesRaw.add((this.yPos - 2) * 8 + this.xPos - 1);
            }
            if (this.xPos + 1 < 8) {
                if (gameBoardState[this.yPos - 2][this.xPos + 1] == null || this.isWhite != gameBoardState[this.yPos - 2][this.xPos + 1].getIsWhite()) {
                    validMovesRaw.add((this.yPos - 2) * 8 + this.xPos + 1);
                }
            }
        }

        //Check down two
        if (this.yPos + 2 < 8) {
            if (this.xPos - 1 > -1) {
                if (gameBoardState[this.yPos + 2][this.xPos - 1] == null || this.isWhite != gameBoardState[this.yPos + 2][this.xPos - 1].getIsWhite()) {
                    validMovesRaw.add((this.yPos + 2) * 8 + this.xPos - 1);
                }
            }
            if (this.xPos + 1 < 8) {
                if (gameBoardState[this.yPos + 2][this.xPos + 1] == null || this.isWhite != gameBoardState[this.yPos + 2][this.xPos + 1].getIsWhite()) {
                    validMovesRaw.add((this.yPos + 2) * 8 + this.xPos + 1);
                }
            }
        }

        //Check left two
        if (this.xPos - 2 > -1) {
            if (this.yPos - 1 > -1) {
                if (gameBoardState[this.yPos - 1][this.xPos - 2] == null || this.isWhite != gameBoardState[this.yPos - 1][this.xPos - 2].getIsWhite()) {
                    validMovesRaw.add((this.yPos - 1) * 8 + this.xPos - 2);
                }
            }
            if (this.yPos + 1 < 8) {
                if (gameBoardState[this.yPos + 1][this.xPos - 2] == null || this.isWhite != gameBoardState[this.yPos + 1][this.xPos - 2].getIsWhite()) {
                    validMovesRaw.add((this.yPos + 1) * 8 + this.xPos - 2);
                }
            }
        }

        //Check right two
        if (this.xPos + 2 < 8) {
            if (this.yPos - 1 > -1) {
                if (gameBoardState[this.yPos - 1][this.xPos + 2] == null || this.isWhite != gameBoardState[this.yPos - 1][this.xPos + 2].getIsWhite()) {
                    validMovesRaw.add((this.yPos - 1) * 8 + this.xPos + 2);
                }
            }
            if (this.yPos + 1 < 8) {
                if (gameBoardState[this.yPos + 1][this.xPos + 2] == null || this.isWhite != gameBoardState[this.yPos + 1][this.xPos + 2].getIsWhite()) {
                    validMovesRaw.add((this.yPos + 1) * 8 + this.xPos + 2);
                }
            }
        }
        
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
        return false;
    }

}
