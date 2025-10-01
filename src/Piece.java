import java.util.ArrayList;

public interface Piece {
	
	public void Update();

	/*
	 * Type=
	 * 0: Pawn
	 * 1: Knight
	 * 2: Bishop
	 * 3: Rook
	 * 4: Queen
	 * 5: King
	 */
	public int getPieceType();
	public String getName();
	public String getSymbol();
	public int getXPos();
	public int getYPos();
	public boolean getIsWhite();
	public boolean getEnPassant(int moveCount);
	public boolean getHasMoved();
	public void changeXPos(int x);
	public void changeYPos(int y);
	public void changeHasMoved();
	
	public ArrayList<Integer> getValidMoves(Piece[][] gameBoardState, int moveCount);

	//For Castling
	public ArrayList<Integer> getValidMoves(Piece[][] gameBoardState, int moveCount, int[][] gameBoardThreats);
	
}
