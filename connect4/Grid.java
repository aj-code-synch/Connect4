package connect4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Grid extends JPanel implements MouseListener {

	private Cell[][] gridUI = new Cell[6][7];
	private static int[][] gridTrack = new int[6][7];
	private static int player = 1;
	public static Boolean gameOver;
	public Boolean randPlayer;
	private static ArrayList<Cell> cellArray = new ArrayList<Cell>();

	public Grid(){
		//		setPreferredSize(new Dimension(600,700));;
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
		System.out.println("Grid Click");
		Cell clickedCell;
		Boolean filled = false;

		Point mousePoint;

		mousePoint = e.getPoint();
		System.out.println(mousePoint.x + "||" + mousePoint.y);
		clickedCell = (Cell)getComponentAt(mousePoint);

		Component clickedComp = getComponentAt(mousePoint);
		int cellIndex;
		cellIndex = Integer.parseInt(clickedComp.getName());

		//		Point mousePoint = MouseInfo.
//		int cellIndex;
		cellIndex = Integer.parseInt(clickedCell.getName());
		int cellX = cellIndex / 7;
		int cellY = cellIndex % 7;
		//		cellIndex = cellX * 7 + cellY;
		//		System.out.println(cellIndex);
		//		clickedCell = cellArray.get(cellIndex);
		System.out.println("Clicked on" + "[" + cellX + "][" + cellY+ "]");
		filled = clickedCell.fillTile(player);
		if(filled == true){
			gridTrack[clickedCell.row][clickedCell.column] = player;
			gameOver = checkGrid();
			if(player == 1){
				player = 2;
			}else{
				player = 1;
			}
		}




	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

		//		Cell enteredCell;
		//		
		//		Point mousePoint =  MouseInfo.getPointerInfo().getLocation();
		////		Point mousePoint = MouseInfo.
		//	     enteredCell = (Cell)this.getComponentAt(mousePoint);
		//	     enteredCell.mouseEntered(player);
		//	     repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//		Cell enteredCell;
		//		
		//		Point mousePoint =  MouseInfo.getPointerInfo().getLocation();
		////		Point mousePoint = MouseInfo.
		//	     enteredCell = (Cell)this.getComponentAt(mousePoint);
		//	     enteredCell.mouseEntered(player);
		////	     repaint();
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
						return win;
					}


				}


			}
		}


		return win;
	}
}
