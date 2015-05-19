package connect4;

import java.util.ArrayList;

public class SimpleMiniMax {
	
private static Boolean noMoreMoves = false;


	public Move minimax(int[][] gridCopy_minimax){
		Move nextMove = null;
        noMoreMoves = false;
        int[][] gridCopy = gridCopy_minimax;
		return maxMove(gridCopy, 1);

	}

	private Move maxMove(int[][] gridCopy_max, int depth){
		int gameValue = 0;

		Move maxMove = null,
				tempMove = null,
				thisMove = null;

		if(depth==0 || noMoreMoves){
			return null;
		} else {
			for (int i = 0; i < 7; i++) {
				try {
					thisMove = new Move(gridCopy_max, i, Grid.getAI());
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

		return maxMove;

	}
	
	private Move minMove(int[][] gridCopy_min, int depth){

		Move minMove = null,
				tempMove = null,
				thisMove = null;
		if(depth==0 || noMoreMoves){
			//			int moveValue = moveValue(gridCopy, Human);
			return null;
		} else {
			for (int i = 0; i < 7; i++) {
				try {
					thisMove = new Move(gridCopy_min, i, Grid.getHuman());
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


		return minMove;


	}

	
}
