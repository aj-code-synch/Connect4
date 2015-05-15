package connect4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Grid extends JPanel implements MouseListener {

	private Cell[][] gridUI = new Cell[6][7];
	private static int[][] gridTrack = new int[6][7];
	private static int player = 1;
	private static int AI = 2;
	private static int Human = 1;
	private static Boolean gameOver = false;
	private static Boolean noMoreMoves = false;
	public Boolean randPlayer = false;
	private static ArrayList<Cell> cellArray = new ArrayList<Cell>();
	private PlayConnect UIInstance;

	public Grid(PlayConnect UIInstance_in){

		UIInstance = UIInstance_in;

		setLayout(new GridLayout(6, 7));
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				Cell tempCell = new Cell(i,j);
				tempCell.addMouseListener(this);
				gridUI[i][j] = tempCell;
				gridTrack[i][j] = 0;
				add(tempCell);

				int index = i*6 + j;
				cellArray.add(tempCell);

			}

		}
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!gameOver){
			//			System.out.println("-----Grid Click------");
			Cell clickedCell;
			clickedCell = (Cell)e.getSource();
			Boolean filled = false;
			filled = clickedCell.fillTile(player);
			if(filled == true){
				gridTrack[clickedCell.row][clickedCell.column] = player;
				gameOver = checkGrid();
				if(player == 1){
					player = 2;
				}else{
					player = 1;
				}


				if(randPlayer){
					randomMove();
				}
			}



		}


	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!gameOver){
			Cell enteredCell = (Cell)e.getSource();
			enteredCell.mouseEntered(player);
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!gameOver){
			Cell exitedCell = (Cell)e.getSource();
			exitedCell.mouseExited(player);	
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	private Boolean checkGrid() {
		Boolean win = false;

		int[][] gridCopy = new int[6][7];
		gridCopy = gridTrack;

		int counter= 0;

		//     Horizontal Check 		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if(gridCopy[i][j]==player){
					counter++;
				} else{
					counter = 0;
				}

				if(counter==4){
					win = true;
					UIInstance.printWin(player);
					return win;
				}

			}

		}

		//     Vertical Check 
		counter = 0;
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				if(gridCopy[i][j]==player){
					counter++;
				} else{
					counter = 0;
				}

				if(counter==4){
					win = true;
					UIInstance.printWin(player);
					return win;
				}

			}

		}



		////    Diag Check 1 (\)
		counter = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++) {

				for (int k = 0; k < 4; k++) {

					if(gridCopy[k+i][j+k]==player){
						counter++;
					} else{
						counter = 0;
					}


					if(counter==4){
						win = true;
						UIInstance.printWin(player);
						return win;
					}


				}


			}
		}


		////    Diag Check 2 (/)
		counter = 0;
		for (int j = 3; j < 7; j++) {
			for (int i = 0; i < 3; i++) {

				for (int k = 0; k < 4; k++) {

					if(gridCopy[k+i][j-k]==player){
						counter++;
					} else{
						counter = 0;
					}


					if(counter==4){
						win = true;
						UIInstance.printWin(player);
						return win;
					}


				}


			}
		}


		return win;
	}

	public int printWin(){
		if(gameOver){
			return player;

		} else {
			return 0;
		}


	}

	private void randomMove(){
		Random randClass = new Random();
		int column = 0;
		int row = 0;
		Cell randCell = null;
		Boolean filled = false;

		if(!gameOver){
			column = randClass.nextInt(7);
			for (int i = 0; i < 6; i++) {
				if(gridTrack[i][column] != 0){
					row = i-1;
					break;
				}
			}
			if(row==0){
				row = 5;
			}
			gridTrack[row][column] = player;
			System.out.println("Random Move" + row + "//" + column);
			randCell = gridUI[row][column];
			filled = randCell.fillTile(player);
			if(filled){
				gameOver = checkGrid();

				if(gameOver){
					System.out.println(player + " Wins");
				}else{
					if(player==1){
						player = 2;
					}else{
						player = 1;
					}   
				}

				repaint();

			}	
		}

	}

	public void resetGrid(){
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {

				gridUI[i][j].reset();
				gridTrack[i][j] = 0;
				gameOver = false;
				randPlayer = false;


			}
		}

		player = 1;
		repaint();

	}

	private Move minimax(int[][] gridCopy){
		ArrayList<Integer> xAndy = new ArrayList<Integer>();
		Move nextMove = null;
		int maxVal = 10000;
		int minVal =-10000;
		int x = 0;
		int y = 0;

		return maxMove(gridCopy, maxVal, minVal, 3);



	}

	private Move maxMove(int[][] gridCopy, int alpha, int beta, int depth){
		int gameValue = 0;

		Move maxMove = null,
				tempMove = null;

		if(depth==0 || noMoreMoves){

		} else {
			for (int i = 0; i < 7; i++) {
				try {
					tempMove = new Move(gridCopy, i, AI);
					minMove(tempMove.newGrid, alpha, beta, depth-1 );
				} catch (MoveException e) {
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

	private Move minMove(int[][] gridCopy, int alpha, int beta, int depth){

		Move minMove = null,
				tempMove;
		if(depth==0 || noMoreMoves){
//			int moveValue = moveValue(gridCopy, Human);
		} else {
			for (int i = 0; i < 7; i++) {
				try {
					tempMove = new Move(gridCopy, i, Human);
					maxMove(tempMove.newGrid, alpha, beta, depth-1 );
					if(minMove == null) minMove = tempMove;
					if(tempMove.moveValue(Human) >= minMove.moveValue(Human)){
						minMove = tempMove;
						alpha = minMove.moveValue(Human);
					}
				} catch (MoveException e) {
					// TODO: handle exception
					if(i==6){
						noMoreMoves = true;
						break;	
					}


				}


			}
			
			if(beta>alpha) return null;
		}





		return minMove;





	}


	public class Move{

		public int[][] newGrid = new int[6][7];
		public int moveX = 9,
				moveY;



		Move(int[][] CurrGrid, int y, int player) throws MoveException{

			newGrid = CurrGrid;
			int i = 0;
			for (i = 0; i < 6 ; i++) {
				if(CurrGrid[i][y] != 0){
					moveX = i-1;
					break;
				}
			}

			if(i<0){
				throw new MoveException("No More Moves");
			}

			if(moveX==9){
				moveX = 5;
			}

			moveY = y;
			newGrid[moveX][y] = player;


		}
		
		public int moveValue(int player_in){

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
					if(newGrid[i][j]==player){
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

						if(newGrid[i][j]==player){
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

						if(newGrid[i][j]==player){
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



			return currcount;

		}

	}

	public class MoveException extends Exception{
		String exception;
		public MoveException(){
			super();
			exception = "NoMoreMoves";
		}

		public MoveException(String exp){
			super(exp);
			this.exception = exp;
		}

		public String getException(){
			return this.exception;
		}
	}

	

}
