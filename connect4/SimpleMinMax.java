package connect4;

import java.util.ArrayList;

public class SimpleMinMax {
	
private static Boolean noMoreMoves = false;
private int[][] grid = new int[6][7];

	public static Move minimax(int[][] gridCopy){
		ArrayList<Integer> xAndy = new ArrayList<Integer>();
		Move nextMove = null;
		int maxVal = 10000;
		int minVal =-10000;
		int x = 0;
		int y = 0;
        noMoreMoves = false;
		return maxMove(gridCopy, 3);

	}

	private static Move maxMove(int[][] gridCopy, int depth){
		int gameValue = 0;

		Move maxMove = null,
				tempMove = null,
				thisMove = null;

		if(depth==0 || noMoreMoves){
			return null;
		} else {
			for (int i = 0; i < 7; i++) {
				try {
					thisMove = new Move(gridCopy, i, Grid.getAI());
					tempMove = minMove(thisMove.newGrid, depth-1 );
					if (maxMove==null && tempMove != null) maxMove = tempMove;
					if(tempMove==null) tempMove = thisMove;
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

		return maxMove;

	}
	
	private static Move minMove(int[][] gridCopy, int depth){

		Move minMove = null,
				tempMove = null,
				thisMove = null;
		if(depth==0 || noMoreMoves){
			//			int moveValue = moveValue(gridCopy, Human);
			return null;
		} else {
			for (int i = 0; i < 7; i++) {
				try {
					thisMove = new Move(gridCopy, i, Grid.getHuman());
					tempMove = maxMove(thisMove.newGrid, depth-1 );
					if(minMove == null && tempMove != null) minMove = tempMove;
					if(tempMove==null) tempMove = thisMove;
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





		return minMove;


	}

	
}
