package connect4;

import java.util.ArrayList;

public class SimpleMiniMax {

	private static Boolean noMoreMoves = false;
	public static int moveCount = 0;
	public static int searchdepth = 3;


	public Move minimax(int[][] gridCopy_minimax){
		Move nextMove = null;
		moveCount = 0;
		noMoreMoves = false;
		int[][] gridCopy = new int[6][7];
		for (int i = 0; i < gridCopy.length; i++) {
			for (int j = 0; j < gridCopy[0].length; j++) {
				gridCopy[i][j] = gridCopy_minimax[i][j];

			}
		}
		return maxMove(gridCopy, searchdepth);

	}

	private Move maxMove(int[][] gridCopy_max, int depth){
		int gameValue = 0;

		Move maxMove = null,
				tempMove = null,
				thisMove = null;

		int[][] gridCopy = new int[6][7];
		for (int i = 0; i < gridCopy.length; i++) {
			for (int j = 0; j < gridCopy[0].length; j++) {
				gridCopy[i][j] = gridCopy_max[i][j];

			}
		}

		if(depth==0 || noMoreMoves){
			return null;
		} else {
			for (int i = 0; i < 7; i++) {
				try {
					thisMove = new Move(gridCopy, i, Grid.getAI());
					tempMove = minMove(thisMove.newGrid, depth-1 );
					if(tempMove==null) tempMove = thisMove;

					if (maxMove==null && tempMove != null) maxMove = tempMove;

					if(tempMove != null && tempMove.moveValue >= maxMove.moveValue ){
						maxMove = tempMove;
					}
				} catch (Exception e) {
					// TODO: handle exception
					if(i==6){
						noMoreMoves = true;
						break;	
					}


				}


			}
		}


		System.out.println("maxMove Return|" +
				"|Player :" + maxMove.movePlayer +  
				"|MoveX :" + maxMove.moveX + 
				"|MoveY :" + maxMove.moveY + 
				"|Move Value : " + maxMove.moveValue);
		return maxMove;

	}

	private Move minMove(int[][] gridCopy_min, int depth){

		Move minMove = null,
				tempMove = null,
				thisMove = null;

		int[][] gridCopy = new int[6][7];
		for (int i = 0; i < gridCopy.length; i++) {
			for (int j = 0; j < gridCopy[0].length; j++) {
				gridCopy[i][j] = gridCopy_min[i][j];

			}
		}

		if(depth==0 || noMoreMoves){
			//			int moveValue = moveValue(gridCopy, Human);
			return null;
		} else {
			for (int i = 0; i < 7; i++) {
				try {
					thisMove = new Move(gridCopy, i, Grid.getHuman());
					tempMove = maxMove(thisMove.newGrid, depth-1 );
					if(tempMove==null) tempMove = thisMove;
					if(minMove == null && tempMove != null) minMove = tempMove;
					if(tempMove.moveValue <= minMove.moveValue) minMove = tempMove;

				} catch (Exception e) {
					// TODO: handle exception
					if(i==6){
						noMoreMoves = true;
						break;	
					}


				}


			}


		}

		System.out.println("maxMove Return|" +
				"|Player :" + minMove.movePlayer +  
				"|MoveX :" + minMove.moveX + 
				"|MoveY :" + minMove.moveY + 
				"|Move Value : " + minMove.moveValue);

		return minMove;


	}


}
