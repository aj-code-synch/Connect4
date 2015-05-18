package connect4;

//import connect4.Grid.MoveException;

public class Move {
	public int[][] newGrid = new int[6][7];
	public int moveX = 9,
			moveY,
			moveValue;




	public Move(int[][] CurrGrid, int y, int player_in) throws Exception{

		newGrid = CurrGrid;
		int i = 0;
		for (i = 0; i < 6 ; i++) {
			if(CurrGrid[i][y] != 0){
				moveX = i-1;
				break;
			}
		}

		if(i<0){
			throw new Exception("No More Moves");
		}

		if(moveX==9){
			moveX = 5;
		}

		moveY = y;
		newGrid[moveX][y] = player_in;

		this.moveValue(player_in);
	}

	private void moveValue(int player_in){

		int count = 0,
				tempcount = 0,
				currcount = 0;

		//		Horizontal Count 
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if(newGrid[i][j]==player_in){
					tempcount++;
				} else{
					if(currcount<tempcount){
						currcount = tempcount;
					}
					tempcount = 0;
				}


			}

		}

		//     Vertical Check 
		tempcount = 0;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				if(newGrid[i][j]==player_in){
					tempcount++;
				} else{
					if(currcount<tempcount){
						currcount = tempcount;
					}
					tempcount = 0;
				}

			}

		}



		////    Diag Check 1 (\)
		tempcount = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++) {

				for (int k = 0; k < 4; k++) {

					if(newGrid[i][j]==player_in){
						tempcount++;
					} else{
						if(currcount<tempcount){
							currcount = tempcount;
						}
						tempcount = 0;
					}


				}


			}
		}


		////    Diag Check 2 (/)
		tempcount = 0;
		for (int j = 3; j < 7; j++) {
			for (int i = 0; i < 3; i++) {

				for (int k = 0; k < 4; k++) {

					if(newGrid[i][j]==player_in){
						tempcount++;
					} else{
						if(currcount<tempcount){
							currcount = tempcount;
						}
						tempcount = 0;
					}


				}


			}
		}

		moveValue = currcount;

		

	}



	



}
