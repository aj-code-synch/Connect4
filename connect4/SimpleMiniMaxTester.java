package connect4;

import java.util.Arrays;

public class SimpleMiniMaxTester extends SimpleMiniMax {


	public static int[][] gridTrack;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//		gridTrack = new int[6][7];

//		gridTrack = new int[][]{ 
//				{0,0,0,0,0,0,0},
//				{0,0,0,0,0,0,2},
//				{0,0,0,0,0,0,1},
//				{0,0,1,0,0,0,2},
//				{0,0,1,0,0,2,2},
//				{1,1,1,2,0,1,2},
//		};
//
//		
//		Move.lastX = 3;
//		Move.lastY = 2;
		
		gridTrack = new int[][]{ 
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,2},
				{1,1,1,0,0,2,2},
		};

		
		Move.lastX = 5;
		Move.lastY = 2;
		
		
		SimpleMiniMax.moveCount = 0;
		
		SimpleMiniMax.searchdepth = 3;

		SimpleMiniMaxTester tester = new SimpleMiniMaxTester();
		Move maxMove = tester.minimax(gridTrack);
		System.out.println("MovePlayer: " + maxMove.movePlayer +  
				"|MoveX: " + maxMove.moveX + 
				"|MoveY: " + maxMove.moveY + 
				"|Board: " + SimpleMiniMax.moveCount);


		maxMove.print();
	}




}
