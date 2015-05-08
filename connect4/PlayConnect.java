package connect4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlayConnect implements MouseListener {

//private String[][] grid = new String[6][7];
private Cell[][] gridUI = new Cell[6][7];
private JFrame mainFrame;
private JPanel basePanel,
               buttonPanel,
               messagePanel;

private Grid gridPanel;

//private JLayeredPane gridPanel;
private JTextArea messArea;
private Font messFont;

private JButton randButton;
private RandomMoves randButtonHandle;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PlayConnect game = new PlayConnect();
	   
		game.startGame();

	}
	
	private void startGame(){
		
		mainFrame = new JFrame("Connect-4");
        mainFrame.setSize(800, 700);		
		
        basePanel = new JPanel();
//        basePanel.setLayout(new BorderLayout());
		mainFrame.add(basePanel);
		
		gridPanel = new Grid();
	
		
		
		basePanel.add(gridPanel,BorderLayout.CENTER);
		
		messagePanel = new JPanel();
		messagePanel.addMouseListener(this);
		
		messArea = new JTextArea();
		messArea.setEditable(false);
		messFont = new Font(Font.SERIF, Font.BOLD, 20);
		messArea.setFont(messFont);
		messArea.setText("Game On !");
		
		messagePanel.add(messArea);
		basePanel.add(messagePanel,BorderLayout.PAGE_END);
		
		
		buttonPanel = new JPanel();
		basePanel.addMouseListener(this);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		
		
		randButton = new JButton("Random Moves");
		buttonPanel.add(randButton);
		randButtonHandle = new RandomMoves();
		randButton.addActionListener(randButtonHandle);
		basePanel.add(buttonPanel,BorderLayout.LINE_END);
		
		
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

//	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("MainClass Click");
		if(gridPanel.gameOver){
			
			String message = null;
			
			if(gridUI[0][0].player==1){
				message = "Blue Wins!!";
				messArea.setForeground(Color.BLUE);
			}else{
				message = "Red Wins!!";
				messArea.setForeground(Color.RED);
			}
			messArea.setText(message);

		}
		
	}

//	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//
//	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//
//	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//
//	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public class RandomMoves implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gridUI[0][0].randPlayer = true;
			System.out.println("Random Player");
			
		}
		
		
	}
}
