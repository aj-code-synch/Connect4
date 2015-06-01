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
	public Boolean simpleMinimax = false;
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

				if(!gameOver){
					if(randPlayer){
						randomMove();
					}

					if(simpleMinimax){
						simpleMiniMaxMove(clickedCell);
					}
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

			System.out.println("Random Move" + row + "//" + column);
			randCell = gridUI[row][column];
			filled = randCell.fillTile(player);
			if(filled){
				gridTrack[row][column] = player;
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



	public static int getAI(){
		return AI;
	}

	public static int getHuman(){
		return Human;
	}

	private void simpleMiniMaxMove(Cell clickedCell){

		
		
		Move.lastX = clickedCell.row;
		Move.lastY = clickedCell.column;
		SimpleMiniMax minimax = new SimpleMiniMax();
		SimpleMiniMax.searchdepth = 3;
		Move thisMove = minimax.minimax(gridTrack);
		System.out.println("MinMax Cell --" + thisMove.moveX + "||" + thisMove.moveY);
		Cell minimaxCell = gridUI[thisMove.moveX][thisMove.moveY];

		Boolean filled = minimaxCell.fillTile(player);
		if(filled){
			gridTrack[thisMove.moveX][thisMove.moveY] = player;
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
