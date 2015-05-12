package connect4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Cell extends JPanel{

	private String status; 
	private Color curColor;
	private Boolean occupied;
	public static Boolean gameOver = false;
	public static int player;
	private Color player1 = Color.BLUE;
	private Color player2 = Color.RED;
	private static int[][] gridTrack = new int[6][7];
	public int row,column;
	public static int cellSize = 80;
	public Cell(int row_in, int column_in){
		setPreferredSize(new Dimension(cellSize,cellSize));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		setBackground(Color.GRAY);
		player = 0;
		this.setName(Integer.toString(row_in*6+column_in));
		curColor = Color.WHITE;
		//		addMouseListener(this);
		occupied = false;
		player = 1;
		row = row_in;
		column = column_in;
		gridTrack[row][column] = 0;
	}

	protected void paintComponent(Graphics g) {
		g.setColor(curColor);
		super.paintComponent(g);
		g.fillOval(5, 5, 70, 70);

	}


	//	public void mouseEntered(MouseEvent arg0){
	//
	//		if(!gameOver && !occupied){
	//			if(player==1){
	//				curColor = player1;	
	//			} else{
	//				curColor = player2;
	//			}
	//
	//			repaint();	
	//		}
	//
	//
	//
	//	}
	//
	//	public void mouseExited(MouseEvent arg0){
	//		if(!gameOver && !occupied){
	//			curColor = Color.WHITE;
	//			repaint();
	//		}
	//
	//	}

	public void markOccupied(){
		occupied = true;
	}



	//	public void randMove(){
	//		Random randClass = new Random();
	//		int column = 0;
	//		int row = 0;
	//		column = randClass.nextInt(7);
	//		System.out.println("Random Column" + column);
	//		for (int i = 0; i < 6; i++) {
	//			if(gridTrack[i][column] != 0){
	//				row = i-1;
	//				break;
	//			}
	//			if(i==5){
	//				row = 5;
	//			}
	//			gridTrack[row][column] = player;
	//			System.out.println("Random Move" + row + "//" + column);
	//			curColor = player2;
	//			gameOver = checkGrid();
	//
	//			if(gameOver){
	//				System.out.println(player + " Wins");
	//			}else{
	//				if(player==1){
	//					player = 2;
	//				}else{
	//					player = 1;
	//				}   
	//			}
	//
	//			repaint();
	//
	//
	//		}
	//
	//
	//	}

	public Boolean fillTile(int player_in){
		//		System.out.println("Cell Click");
		Boolean filled = false;
		if(!occupied){

			if(row == 5 || (row < 5 && gridTrack[row+1][column]!=0)){
				occupied = true;
				gridTrack[row][column] = player_in;
				filled = true;
				if(player_in==1){
					curColor = player1;	
				} else{
					curColor = player2;
				}
				repaint();
			}



		}
		return filled;
	}


	public void mouseEntered(int player_in){

		if(!occupied){
			if(player_in==1){
				curColor = player1;	
			} else{
				curColor = player2;
			}

			repaint();	
		}



	}

	public void mouseExited(int player_in){
		if(!occupied){
			curColor = Color.WHITE;
			repaint();
		}

	}


}
