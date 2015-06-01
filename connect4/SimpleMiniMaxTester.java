package connect4;

import java.util.Arrays;

public class SimpleMiniMaxTester extends SimpleMiniMax {


	public static int[][] gridTrack;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//		gridTrack = new int[6][7];

		gridTrack = new int[][]{ 
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0},
		};

		SimpleMiniMax.searchdepth = 3;
		Move.lastX = 5;
		Move.lastY = 0;
		SimpleMiniMaxTester tester = new SimpleMiniMaxTester();
		Move maxMove = tester.minimax(gridTrack);
		System.out.println("MovePlayer: " + maxMove.movePlayer +  
				"|MoveX: " + maxMove.moveX + 
				"|MoveY: " + maxMove.moveY);


		maxMove.print();
	}




}
