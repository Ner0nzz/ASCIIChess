import java.util.*;

public class Bishop implements Piece{

    private int type;
	private String name;
	private String symbol;
	private int xPos;
	private int yPos;
	private boolean isWhite;
	private boolean hasMoved;

    private ArrayList<Integer> validMovesRaw;

    public Bishop(int x, int y, boolean isWhite) {
		this.type = 2;
		this.name = "Bishop";
		this.symbol = "B";
		this.isWhite = isWhite;
		this.xPos = x;
		this.yPos = y;
		
		validMovesRaw = new ArrayList<Integer>();
	}

    public void Update() {
		this.validMovesRaw.clear();
	}

    /**
     * Clears the ArrayList validMovesRaw and adds integers corresponding to all valid moves that this piece may take if it is its color's turn to move
     * @param gameBoardState A two-dimensional array containing the pieces in their respective positions on the chessboard
     * @param moveCount The number of moves that have passed since the start of the game. Is not used in this method and is included because of the Piece implementaiton.
     */
    private void updateValidMoves(Piece[][] gameBoardState, int moveCount) {
        this.validMovesRaw.clear();
        checkValidMovesUpRight(gameBoardState);
        checkValidMovesUpLeft(gameBoardState);
        checkValidMovesDownRight(gameBoardState);
        checkValidMovesDownLeft(gameBoardState);

    }

    /**
     * Updates validMovesRaw with valid moves that are directly to the top right of this piece in the same diagonal
     * @param gameBoardState A two-dimensional array containing the pieces in their respective positions on the chessboard
     */
    private void checkValidMovesUpRight(Piece[][] gameBoardState) {
        boolean hasReachedEnd = false;
        int currentDistance = 1;
        while (!hasReachedEnd) {
            if (this.yPos - currentDistance < 0 || this.xPos + currentDistance > 7) {
                hasReachedEnd = true;
            }
            else if (gameBoardState[this.yPos - currentDistance][this.xPos + currentDistance] != null) {
                if (this.isWhite != gameBoardState[this.yPos - currentDistance][this.xPos + currentDistance].getIsWhite()) {
                    validMovesRaw.add((this.yPos - currentDistance) * 8 + this.xPos + currentDistance);
                }
                hasReachedEnd = true;
            }
            else {
                validMovesRaw.add((this.yPos - currentDistance) * 8 + this.xPos + currentDistance);
                currentDistance += 1;
            }
        }
    }

    /**
     * Updates validMovesRaw with valid moves that are directly to the top left of this piece in the same diagonal
     * @param gameBoardState A two-dimensional array containing the pieces in their respective positions on the chessboard
     */
    private void checkValidMovesUpLeft(Piece[][] gameBoardState) {
        boolean hasReachedEnd = false;
        int currentDistance = 1;
        while (!hasReachedEnd) {
            if (this.yPos - currentDistance < 0 || this.xPos - currentDistance < 0) {
                hasReachedEnd = true;
            }
            else if (gameBoardState[this.yPos - currentDistance][this.xPos - currentDistance] != null) {
                if (this.isWhite != gameBoardState[this.yPos - currentDistance][this.xPos - currentDistance].getIsWhite()) {
                    validMovesRaw.add((this.yPos - currentDistance) * 8 + this.xPos - currentDistance);
                }
                hasReachedEnd = true;
            }
            else {
                validMovesRaw.add((this.yPos - currentDistance) * 8 + this.xPos - currentDistance);
                currentDistance += 1;
            }
        }
    }

    /**
     * Updates validMovesRaw with valid moves that are directly to the bottom right of this piece in the same diagonal
     * @param gameBoardState A two-dimensional array containing the pieces in their respective positions on the chessboard
     */
    private void checkValidMovesDownRight(Piece[][] gameBoardState) {
        boolean hasReachedEnd = false;
        int currentDistance = 1;
        while (!hasReachedEnd) {
            if (this.yPos + currentDistance > 7 || this.xPos + currentDistance > 7) {
                hasReachedEnd = true;
            }
            else if (gameBoardState[this.yPos + currentDistance][this.xPos + currentDistance] != null) {
                if (this.isWhite != gameBoardState[this.yPos + currentDistance][this.xPos + currentDistance].getIsWhite()) {
                    validMovesRaw.add((this.yPos + currentDistance) * 8 + this.xPos + currentDistance);
                }
                hasReachedEnd = true;
            }
            else {
                validMovesRaw.add((this.yPos + currentDistance) * 8 + this.xPos + currentDistance);
                currentDistance += 1;
            }
        }
    }

    /**
     * Updates validMovesRaw with valid moves that are directly to the bottom left of this piece in the same diagonal
     * @param gameBoardState A two-dimensional array containing the pieces in their respective positions on the chessboard
     */
    private void checkValidMovesDownLeft(Piece[][] gameBoardState) {
        boolean hasReachedEnd = false;
        int currentDistance = 1;
        while (!hasReachedEnd) {
            if (this.yPos + currentDistance > 7 || this.xPos - currentDistance < 0) {
                hasReachedEnd = true;
            }
            else if (gameBoardState[this.yPos + currentDistance][this.xPos - currentDistance] != null) {
                if (this.isWhite != gameBoardState[this.yPos + currentDistance][this.xPos - currentDistance].getIsWhite()) {
                    validMovesRaw.add((this.yPos + currentDistance) * 8 + this.xPos - currentDistance);
                }
                hasReachedEnd = true;
            }
            else {
                validMovesRaw.add((this.yPos + currentDistance) * 8 + this.xPos - currentDistance);
                currentDistance += 1;
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
        return false;
    }

}
