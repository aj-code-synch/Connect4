package connect4;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Grid extends JPanel implements MouseListener {

	private Cell[][] gridUI = new Cell[6][7];

	public Grid(){
		addMouseListener(this);
		setLayout(new GridLayout(6, 7));
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				Cell tempCell = new Cell(i,j);
				tempCell.addMouseListener(this);
				gridUI[i][j] = tempCell;
				add(tempCell);


			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

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
