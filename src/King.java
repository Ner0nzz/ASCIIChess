import java.util.ArrayList;

public class King implements Piece {

    private int type;
    private String name;
	private String symbol;
	private int xPos;
	private int yPos;
	private boolean isWhite;
	private boolean hasMoved;
	
	private ArrayList<Integer> validMovesRaw;

    public King(int x, int y, boolean isWhite, boolean hasMoved) {
        this.type = 5;
		this.name = "King";
		this.symbol = "K";
		this.isWhite = isWhite;
		this.xPos = x;
		this.yPos = y;
        this.hasMoved = hasMoved;
		
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
        if (this.xPos != 0) {
            if (gameBoardState[this.yPos][this.xPos - 1] == null || gameBoardState[this.yPos][this.xPos - 1].getIsWhite() != this.isWhite) {
                  validMovesRaw.add(this.yPos * 8 + this.xPos - 1);
            }
            if (this.yPos  != 0) {
                if (gameBoardState[this.yPos - 1][this.xPos - 1] == null || gameBoardState[this.yPos - 1][this.xPos - 1].getIsWhite() != this.isWhite) {
                    validMovesRaw.add((this.yPos - 1) * 8 + this.xPos - 1);
                }
                if (gameBoardState[this.yPos - 1][this.xPos] == null || gameBoardState[this.yPos - 1][this.xPos].getIsWhite() != this.isWhite) {
                    validMovesRaw.add((this.yPos - 1) * 8 + this.xPos);
                }
            }
         }
        if (this.yPos != 0) {
            if (gameBoardState[this.yPos - 1][this.xPos] == null || gameBoardState[this.yPos - 1][this.xPos].getIsWhite() != this.isWhite) {
                validMovesRaw.add((this.yPos - 1) * 8 + this.xPos);
            }
            if (this.xPos != 7) {
                if (gameBoardState[this.yPos][this.xPos + 1] == null || gameBoardState[this.yPos][this.xPos + 1].getIsWhite() != this.isWhite) {
                    validMovesRaw.add(this.yPos * 8 + this.xPos + 1);
                }
                if (gameBoardState[this.yPos - 1][this.xPos + 1] == null || gameBoardState[this.yPos - 1][this.xPos + 1].getIsWhite() != this.isWhite) {
                    validMovesRaw.add((this.yPos - 1) * 8 + this.xPos + 1);
                }
             }
         }
         if (this.xPos != 7) {
            if (gameBoardState[this.yPos][this.xPos + 1] == null || gameBoardState[this.yPos][this.xPos + 1].getIsWhite() != this.isWhite) {
                validMovesRaw.add(this.yPos * 8 + this.xPos + 1);
            }
            if (this.yPos != 7) {
                if (gameBoardState[this.yPos + 1][this.xPos + 1] == null || gameBoardState[this.yPos + 1][this.xPos + 1].getIsWhite() != this.isWhite) {
                    validMovesRaw.add((this.yPos + 1) * 8 + this.xPos + 1);
                }
                if (gameBoardState[this.yPos + 1][this.xPos] == null || gameBoardState[this.yPos + 1][this.xPos].getIsWhite() != this.isWhite) {
                    validMovesRaw.add((this.yPos + 1) * 8 + this.xPos);
                }
            }
         }
         if (this.yPos != 7) {
            if (gameBoardState[this.yPos + 1][this.xPos] == null || gameBoardState[this.yPos + 1][this.xPos].getIsWhite() != this.isWhite) {
                validMovesRaw.add((this.yPos + 1) * 8 + this.xPos);
            }
            if (this.xPos != 0) {
                if (gameBoardState[this.yPos + 1][this.xPos - 1] == null || gameBoardState[this.yPos + 1][this.xPos - 1].getIsWhite() != this.isWhite) {
                    validMovesRaw.add((this.yPos + 1) * 8 + this.xPos - 1);
                }
                if (gameBoardState[this.yPos][this.xPos - 1] == null || gameBoardState[this.yPos][this.xPos - 1].getIsWhite() != this.isWhite) {
                    validMovesRaw.add(this.yPos * 8 + this.xPos - 1);
                }
            }
         }
    }

    public ArrayList<Integer> getValidMoves(Piece[][] gameBoardState, int moveCount) {
		updateValidMoves(gameBoardState, moveCount);
		return this.validMovesRaw;
	}

    /**
     * Calls updateValidMoves and adds castling moves to validMovesRaw if they are valid
     * @param gameBoardState A two-dimensional array containing the pieces in their respective positions on the chessboard
     * @param moveCount The number of moves that have passed since the start of the game. Is not used in this method and is included because of the Piece implementaiton.
     * @param gameBoardThreats A two-dimensional array with integer values corresponding to what squares are threatened by each color
     */
    public ArrayList<Integer> getValidMoves(Piece[][] gameBoardState, int moveCount, int[][] gameBoardThreats) {
        updateValidMoves(gameBoardState, moveCount);
        //Castle check white and if it's in check and if it has moved
        if (this.yPos == 7 && this.xPos == 4 && gameBoardThreats[this.yPos][this.xPos] < 2 && !this.hasMoved) {
            //Check if rook on h1 is still there
            if (gameBoardState[7][7] != null) {
                if (gameBoardState[7][7].getPieceType() == 3 && !gameBoardState[7][7].getHasMoved()) {
                    //Check if spaces between king and rook are empty
                    if (gameBoardState[this.yPos][this.xPos + 1] == null && gameBoardState[this.yPos][this.xPos + 2] == null) {
                        //Check if spaces between the king and rook are threatened by enemy pieces
                        if (gameBoardThreats[this.yPos][this.xPos + 1] < 2 && gameBoardThreats[this.yPos][this.xPos + 2] < 2) {
                            //Negative sign so it's clear that this is a castle move
                            validMovesRaw.add(0 - (this.yPos * 8 + this.xPos + 2));
                        }
                    }
                }
            }
            //Check if rook on a1 is still there
            if (gameBoardState[7][0] != null) {
                if (gameBoardState[7][0].getPieceType() == 3 && !gameBoardState[7][0].getHasMoved()) {
                    //Check if spaces between king and rook are empty
                    if (gameBoardState[this.yPos][this.xPos - 1] == null && gameBoardState[this.yPos][this.xPos - 2] == null && gameBoardState[this.yPos][this.xPos - 3] == null) {
                        //Check if spaces between the king and rook are threatened by enemy pieces
                        if (gameBoardThreats[this.yPos][this.xPos - 1] < 2 && gameBoardThreats[this.yPos][this.xPos - 2] < 2 && gameBoardThreats[this.yPos][this.xPos - 3] < 2) {
                            //Negative sign so it's clear that this is a castle move
                            validMovesRaw.add(0 - (this.yPos * 8 + this.xPos - 2));
                        }
                    }
                }
            }
        }

        //Castle check black and if it's in check and if it has moved
        if (this.yPos == 0 && this.xPos == 4 && gameBoardThreats[this.yPos][this.xPos] < 2 && !this.hasMoved) {
            //Check if rook on h8 is still there
            if (gameBoardState[0][7] != null) {
                if (gameBoardState[0][7].getPieceType() == 3 && !gameBoardState[0][7].getHasMoved()) {
                    //Check if spaces between king and rook are empty
                    if (gameBoardState[this.yPos][this.xPos + 1] == null && gameBoardState[this.yPos][this.xPos + 2] == null) {
                        //Check if spaces between the king and rook are threatened by enemy pieces
                        if (gameBoardThreats[this.yPos][this.xPos + 1] < 2 && gameBoardThreats[this.yPos][this.xPos + 2] < 2) {
                            //Negative sign so it's clear that this is a castle move
                            validMovesRaw.add(0 - (this.yPos * 8 + this.xPos + 2));
                        }
                    }
                }
            }
            //Check if rook on a8 is still there
            if (gameBoardState[0][0] != null) {
                if (gameBoardState[0][0].getPieceType() == 3 && !gameBoardState[0][0].getHasMoved()) {
                    //Check if spaces between king and rook are empty
                    if (gameBoardState[this.yPos][this.xPos - 1] == null && gameBoardState[this.yPos][this.xPos - 2] == null && gameBoardState[this.yPos][this.xPos - 3] == null) {
                        //Check if spaces between the king and rook are threatened by enemy pieces
                        if (gameBoardThreats[this.yPos][this.xPos - 1] != 3 && gameBoardThreats[this.yPos][this.xPos - 2] != 3 && gameBoardThreats[this.yPos][this.xPos - 3] != 3) {
                            if (gameBoardThreats[this.yPos][this.xPos - 1] != 1 && gameBoardThreats[this.yPos][this.xPos - 2] != 1 && gameBoardThreats[this.yPos][this.xPos - 3] != 1) {
                                //Negative sign so it's clear that this is a castle move
                                validMovesRaw.add(0 - (this.yPos * 8 + this.xPos - 2));
                            }
                        }
                    }
                }
            }
        }

        return this.validMovesRaw;
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
