package connect4;

//import connect4.Grid.MoveException;

public class Move {
	public int[][] newGrid = new int[6][7];
	public int moveX = 9,
			moveY,
			moveValue,
			movePlayer,
			moveAICount,
			moveHumanCount;
	public Boolean noMoreMoves = false;
	public Boolean gameOver = false;
	private final int[] moveValues = {0,1,10,100,1000};


	public static Move lastMove;
	public static int lastX,
	lastY;


	public Move(int[][] CurrGrid, int y, int player_in) throws Exception{
		moveX = 9;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				newGrid[i][j] = CurrGrid[i][j];

			}

		}



		int i = 0;
		for (i = 0; i < 6 ; i++) {
			if(CurrGrid[i][y] != 0){
				moveX = i-1;
				break;
			}
		}

		if(i<0){
			System.out.println(">>>>>>>>>>>>>>>>>>>>>No more moves<<<<<<<<<<<<<<<");
			throw new Exception("No More Moves");
		}

		if(moveX==9){
			moveX = 5;
		}

		moveY = y;
		newGrid[moveX][y] = player_in;

		this.moveValue(player_in);
		++SimpleMiniMax.moveCount;

		movePlayer = player_in;

		//				System.out.println("Generating Move|" +SimpleMiniMax.moveCount + 
		//						"|Player :" +player_in + 
		//						"|MoveX :" + moveX + 
		//						"|MoveY :" + moveY + 
		//						"|Move Value : " + moveValue);
	}

	public Move(int[][] gridCopy, int player_in){
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				newGrid[i][j] = gridCopy[i][j];

			}

		}

		this.moveValue(player_in);
		this.moveX = lastX;
		this.moveY = lastY;
		this.movePlayer = player_in;

	}

	public void moveValue(int player_in){


		int count = 0, 
				tempcount = 0,
				currcount = 0,
				humanCount = 0,
				tempHuman = 0,
				AICount = 0,
				tempAI = 0,
				score = 0;

		int AI4Count = 0,
				AI3Count = 0,
				AI2Count = 0,
				H4Count = 0,
				H3Count = 0,
				H2Count = 0;

		moveValue = 0;
		gameOver = false;
		//		Horizontal Count 
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {

				if(newGrid[i][j]==Grid.getAI()){
					tempAI++;
					if(humanCount<=tempHuman) {
						humanCount = tempHuman;
						tempHuman = 0;
					}
				} else if(newGrid[i][j]==Grid.getHuman()){
					tempHuman++;
					if(AICount<=tempAI){
						AICount = tempAI;
						tempAI = 0;
					}

				}

				else if(newGrid[i][j]==0){
					if(AICount<=tempAI){
						AICount = tempAI;
						tempAI = 0;
					}

					if(humanCount<=tempHuman) {
						humanCount = tempHuman;
						tempHuman = 0;
					}

				}


			}

			if(AICount<=tempAI){
				AICount = tempAI;

			}

			if(humanCount<=tempHuman) {
				humanCount = tempHuman;

			}

			if(humanCount<=tempHuman) {
				humanCount = tempHuman;
			}

			if(AICount<=tempAI){
				AICount = tempAI;
			}

			if(AICount>3) AI4Count ++;
			if(AICount==3)AI3Count++;
			if(AICount==2)AI2Count++;

			if(humanCount>3) H4Count ++;
			if(humanCount==3)H3Count++;
			if(humanCount==2)H2Count++;


			tempAI = tempHuman = 0;
			AICount = humanCount = 0;

		}





		//     Vertical Check 
		tempAI = tempHuman =  0;
		AICount = humanCount = 0;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				if(newGrid[i][j]==Grid.getAI()){
					tempAI++;
					if(humanCount<=tempHuman) {
						humanCount = tempHuman;
						tempHuman = 0;
					}
				} else if(newGrid[i][j]==Grid.getHuman()){
					tempHuman++;
					if(AICount<=tempAI){
						AICount = tempAI;
						tempAI = 0;
					}

				}
				else if(newGrid[i][j]==0){
					if(AICount<=tempAI){
						AICount = tempAI;
						tempAI = 0;
					}

					if(humanCount<=tempHuman) {
						humanCount = tempHuman;
						tempHuman = 0;
					}

				}


			}

			if(AICount<=tempAI){
				AICount = tempAI;

			}

			if(humanCount<=tempHuman) {
				humanCount = tempHuman;

			}
			if(AICount>3) AI4Count ++;
			if(AICount==3)AI3Count++;
			if(AICount==2)AI2Count++;

			if(humanCount>3) H4Count ++;
			if(humanCount==3)H3Count++;
			if(humanCount==2)H2Count++;


			tempAI = tempHuman = 0;
			AICount = humanCount = 0;

		}

		////    Diag Check 1 (\)
		tempAI = tempHuman =  0;
		AICount = humanCount = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++) {

				for (int k = 0; k < 4; k++) {

					if(newGrid[k+i][j+k]==Grid.getAI()){
						tempAI++;
						if(humanCount<=tempHuman) {
							humanCount = tempHuman;
							tempHuman = 0;
						}
					} else if(newGrid[k+i][j+k]==Grid.getHuman()){
						tempHuman++;
						if(AICount<=tempAI){
							AICount = tempAI;
							tempAI = 0;
						}

					} 
					else if(newGrid[k+i][j+k]==0){
						if(AICount<=tempAI){
							AICount = tempAI;
							tempAI = 0;
						}

						if(humanCount<=tempHuman) {
							humanCount = tempHuman;
							tempHuman = 0;
						}

					}


				}

				if(AICount<=tempAI){
					AICount = tempAI;

				}

				if(humanCount<=tempHuman) {
					humanCount = tempHuman;

				}
				if(AICount>3) AI4Count ++;
				if(AICount==3)AI3Count++;
				if(AICount==2)AI2Count++;

				if(humanCount>3) H4Count ++;
				if(humanCount==3)H3Count++;
				if(humanCount==2)H2Count++;


				tempAI = tempHuman = 0;
				AICount = humanCount = 0;

			}


		}
		////    Diag Check 2 (/)
		tempAI = tempHuman =  0;
	    AICount = humanCount = 0;
		for (int j = 3; j < 7; j++) {
			for (int i = 0; i < 3; i++) {

				for (int k = 0; k < 4; k++) {

					if(newGrid[k+i][j-k]==Grid.getAI()){
						tempAI++;
						if(humanCount<=tempHuman) {
							humanCount = tempHuman;
							tempHuman = 0;
						}
					} else if(newGrid[k+i][j-k]==Grid.getHuman()){
						tempHuman++;
						if(AICount<=tempAI){
							AICount = tempAI;
							tempAI = 0;
						}

					} 
					else if(newGrid[k+i][j-k]==0){
						if(AICount<=tempAI){
							AICount = tempAI;
							tempAI = 0;
						}

						if(humanCount<=tempHuman) {
							humanCount = tempHuman;
							tempHuman = 0;
						}

					}

				}

				if(AICount<=tempAI){
					AICount = tempAI;

				}

				if(humanCount<=tempHuman) {
					humanCount = tempHuman;

				}

				if(AICount>3) AI4Count ++;
				if(AICount==3)AI3Count++;
				if(AICount==2)AI2Count++;

				if(humanCount>3) H4Count ++;
				if(humanCount==3)H3Count++;
				if(humanCount==2)H2Count++;


				tempAI = tempHuman = 0;
				AICount = humanCount = 0;


			}

		}

		//		if(H4Count==0){
		//			moveValue = AI4Count*moveValues[3] + AI3Count*moveValues[2] + AI2Count*moveValues[1];
		//		}else {
		//			moveValue = -100000;
		//		}


		int[] AI = {AI2Count, AI3Count, AI4Count};
		int[] Human = {H2Count, H3Count, H4Count};


		moveValue = power4Heuristic(AI, Human);

		if(H4Count>0 || AI4Count>0){
			gameOver = true;
		}
		
		if(gameOver){
			
			if (player_in == Grid.getAI()) 
				moveValue = 100000; 
			else moveValue = -100000;
		}
			
	}

	public void print(){
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {

				System.out.print(newGrid[i][j] + "\t");

			}
			System.out.println("\n");

		}
	}

	public int power4Heuristic(int[] AI, int[] H){
		int value = 0;

		//		Math.pow(arg0, arg1)

		for (int i = 0; i < AI.length; i++) {
			value += AI[i] * ( (int)Math.pow(4,(double)(i+2)));
		}


		for (int i = 0; i < H.length; i++) {
			value -= 1 * H[i]*((int)Math.pow(4,(double)(i+2)));
		}

		
		

		return value;
	}

}
