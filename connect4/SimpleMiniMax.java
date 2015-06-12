package connect4;

import java.util.ArrayList;

public class SimpleMiniMax {

	//	private Boolean noMoreMoves = false;
	public static int moveCount = 0;
	public static int searchdepth = 3;


	public Move minimax(int[][] gridCopy_minimax){
		Move nextMove = null;
		moveCount = 0;
		//		noMoreMoves = false;
		int[][] gridCopy = new int[6][7];
		for (int i = 0; i < gridCopy.length; i++) {
			for (int j = 0; j < gridCopy[0].length; j++) {
				gridCopy[i][j] = gridCopy_minimax[i][j];

			}
		}

		Move tempMove = new Move(gridCopy, Grid.getHuman());
		Move.lastMove = tempMove;
		return maxMove(tempMove, searchdepth);

	}

	private Move maxMove(Move move_in, int depth){
		int gameValue = 0;

		Move maxMove = null,
				tempMove = null,
				thisMove = null;

		int[][] gridCopy = new int[6][7];
		for (int i = 0; i < gridCopy.length; i++) {
			for (int j = 0; j < gridCopy[0].length; j++) {
				gridCopy[i][j] = move_in.newGrid[i][j];

			}
		}

		if(depth==0 || move_in.noMoreMoves || move_in.gameOver){
			move_in.moveValue(Grid.getAI());
			return move_in;
		} else {
			for (int i = 0; i < 7; i++) {
				try {
					thisMove = new Move(gridCopy, i, Grid.getAI());
					System.out.println(" ");
					for (int j = 0; j < 4 - depth; j++) {
						System.out.print(">>");
					}
					System.out.print("Current maxMove|" +
							"|Player :" + thisMove.movePlayer +  
							"|MoveX :" + thisMove.moveX + 
							"|MoveY :" + thisMove.moveY + 
							"|Move Value : " + thisMove.moveValue +
							"|Depth : " + depth);
					tempMove = minMove(thisMove, depth-1 );

					if (maxMove==null && tempMove != null) {
						maxMove = tempMove;
					}

					if(tempMove != null && tempMove.moveValue <= maxMove.moveValue ){
						thisMove.moveValue = tempMove.moveValue;
						maxMove = thisMove;
					}
				} catch (Exception e) {
					// TODO: handle exception
					if(i==6){
						move_in.noMoreMoves = true;
						tempMove = minMove(move_in, depth-1 );

						if (maxMove==null && tempMove != null) 
							{
							maxMove = tempMove;
							}

						if(tempMove != null && tempMove.moveValue <= maxMove.moveValue ){
							thisMove.moveValue = tempMove.moveValue;
							maxMove = thisMove;
						}
					}


				}


			}
		}

		System.out.println(" ");
		System.out.println("maxMove Return|" +
				"|Player :" + maxMove.movePlayer +  
				"|MoveX :" + maxMove.moveX + 
				"|MoveY :" + maxMove.moveY + 
				"|Move Value : " + maxMove.moveValue +
				"|Depth " + depth);
		return maxMove;

	}

	private Move minMove(Move move_in, int depth){

		Move minMove = null,
				tempMove = null,
				thisMove = null;

		int[][] gridCopy = new int[6][7];
		for (int i = 0; i < gridCopy.length; i++) {
			for (int j = 0; j < gridCopy[0].length; j++) {
				gridCopy[i][j] = move_in.newGrid[i][j];

			}
		}

		if(depth==0 || move_in.noMoreMoves || move_in.gameOver){
			move_in.moveValue(Grid.getHuman());
			return move_in;
		} else {
			for (int i = 0; i < 7; i++) {
				try {
					thisMove = new Move(gridCopy, i, Grid.getHuman());
					System.out.println(" ");
					for (int j = 0; j < 4 - depth; j++) {
						System.out.print(">>");
					}
					System.out.println("Current minMove|" +
							"|Player :" + thisMove.movePlayer +  
							"|MoveX :" + thisMove.moveX + 
							"|MoveY :" + thisMove.moveY + 
							"|Move Value : " + thisMove.moveValue +
							"|Depth : " + depth);
					tempMove = maxMove(thisMove, depth-1 );

					if(minMove == null && tempMove != null) {
						minMove = tempMove;
					}
					if(tempMove.moveValue >= minMove.moveValue){
						thisMove.moveValue = tempMove.moveValue;
						minMove = thisMove;
					}

				} catch (Exception e) {
					// TODO: handle exception
					if(i==6){
						move_in.noMoreMoves = true;
						tempMove = maxMove(move_in, depth-1 );

						if(minMove == null && tempMove != null) {
							minMove = tempMove;
						}
						if(tempMove.moveValue >= minMove.moveValue) {
							thisMove.moveValue = tempMove.moveValue;
							minMove = thisMove;
						}

					}


				}


			}


		}

		System.out.println(" ");
		System.out.println("minMove Return|" +
				"|Player :" + minMove.movePlayer +  
				"|MoveX :" + minMove.moveX + 
				"|MoveY :" + minMove.moveY + 
				"|Move Value : " + minMove.moveValue+
				"|Depth " + depth);

		return minMove;


	}


}