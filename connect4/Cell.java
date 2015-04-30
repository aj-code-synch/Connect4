package connect4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Cell extends JPanel implements  MouseListener{

	private String status; 
	private Color curColor;
	private Boolean occupied;
	private Boolean gameOver;
	private static int player;
	private Color player1 = Color.BLUE;
	private Color player2 = Color.RED;
	private static int[][] gridTrack = new int[6][7];
	private int row,column;
	public Cell(int row_in, int column_in){
		setPreferredSize(new Dimension(80,80));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		setBackground(Color.GRAY);
		player = 0;
		curColor = Color.WHITE;
		//		addMouseMotionListener(this);
		//		addFocusListener(this);
		addMouseListener(this);
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


	public void mouseEntered(MouseEvent arg0){
		if(!occupied){
			if(player==1){
				curColor = player1;	
			} else{
				curColor = player2;
			}

			repaint();	
		}

	}

	public void mouseExited(MouseEvent arg0){
		if(!occupied){
			curColor = Color.WHITE;
			repaint();
		}

	}

	public void markOccupied(){
		occupied = true;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!occupied){

			if(row == 5 || (row < 5 && gridTrack[row+1][column]!=0)){
				occupied = true;
				gridTrack[row][column] = player;

				gameOver = checkGrid();

				if(gameOver){

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


	private Boolean checkGrid(){
		Boolean win = false;
		return win;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
