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

		return MaxMove(gridCopy, maxVal, minVal, 3);
		


	}

	private Move MaxMove(int[][] gridCopy, int alpha, int beta, int depth){
		int gameValue = 0;
		
       Move maxMove = null,
    		tempMove = null;
       
		if(depth==3){

		} else {
			for (int i = 0; i < 7; i++) {
				tempMove = new Move(gridCopy, i, AI);
				
			}
		}



		return maxMove;



	}

	private Move MinMove(int[][] gridCopy){
		int gameValue = 0;
		Move minMove = null;
		return minMove;



	}


	public class Move{

		public int[][] newGrid = new int[6][7];
		public int moveX = 9,
				   moveY;



		Move(int[][] CurrGrid, int y, int player) {

			newGrid = CurrGrid;
			for (int i = 0; i < 6 ; i++) {
				if(CurrGrid[i][y] != 0){
					moveX = i-1;
					break;
				}
			}

			if(moveX==9){
				moveX = 5;
			}

			moveY = y;
			newGrid[moveX][y] = player;


		}

	}



}
