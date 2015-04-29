package connect4;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlayConnect {

private String[][] grid = new String[6][7];
private Cell[][] gridUI = new Cell[6][7];
private JFrame mainFrame;
private JPanel basePanel,
               gridPanel,
               buttonPanel;

private JButton swtichButton;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PlayConnect game = new PlayConnect();
		game.startGame();

	}
	
	private void startGame(){
		
		mainFrame = new JFrame("Connect-4");
        mainFrame.setSize(800, 700);		
		
        basePanel = new JPanel();
		mainFrame.add(basePanel);
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(6, 7));
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				Cell tempCell = new Cell();
				gridUI[i][j] = tempCell;
				gridPanel.add(tempCell);
				
			}
			
		}
		
		
		
		basePanel.add(gridPanel);
		
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		
		swtichButton = new JButton("Swtich");
		buttonPanel.add(swtichButton);
		
		
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

}
