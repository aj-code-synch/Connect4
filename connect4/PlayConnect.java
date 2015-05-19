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

public class PlayConnect{

	//private String[][] grid = new String[6][7];
	//private Cell[][] gridUI = new Cell[6][7];
	private JFrame mainFrame;
	private JPanel basePanel,
	buttonPanel,
	messagePanel;

	private Grid gridPanel;

	private JTextArea messArea;
	private Font messFont;

	private JButton randButton,
	                simpleMinMaxButton,
	                resetButton;
	private RandomMoves randButtonHandle;
	private ResetGrid resetHandle;
	private MiniMaxSimpleMove miniMaxHandle;


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PlayConnect game = new PlayConnect();

		game.startGame();

	}

	private void startGame(){

		mainFrame = new JFrame("Connect-4");
		mainFrame.setSize(800, 700);	

		basePanel = new JPanel();
		basePanel.setName("basePanel");
		mainFrame.add(basePanel);

		gridPanel = new Grid(this);
		gridPanel.setName("GridPanel");


		basePanel.add(gridPanel,BorderLayout.CENTER);

		messagePanel = new JPanel();
		messagePanel.setName("messagePanel");

		messArea = new JTextArea();
		messArea.setEditable(false);
		messFont = new Font(Font.SERIF, Font.BOLD, 20);
		messArea.setFont(messFont);
		messArea.setText("Game On !");

		messagePanel.add(messArea);
		basePanel.add(messagePanel,BorderLayout.PAGE_END);


		buttonPanel = new JPanel();
		buttonPanel.setName("button Panel");
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


		resetButton = new JButton("Reset Grid");
		resetHandle = new ResetGrid();
		resetButton.addActionListener(resetHandle);
		buttonPanel.add(resetButton);
		
		
		
		randButton = new JButton("Random Moves");
		buttonPanel.add(randButton);
		randButtonHandle = new RandomMoves();
		randButton.addActionListener(randButtonHandle);
		
		simpleMinMaxButton = new JButton("Simple MinMax");
		buttonPanel.add(simpleMinMaxButton);
		miniMaxHandle = new MiniMaxSimpleMove();
		simpleMinMaxButton.addActionListener(miniMaxHandle);
		
		
		basePanel.add(buttonPanel,BorderLayout.LINE_END);
		
		


		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}


	public class RandomMoves implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gridPanel.randPlayer = true;
			gridPanel.simpleMinimax = false;
			gridPanel.resetGrid();
			System.out.println("Random Player");

		}


	}
	
	public class ResetGrid implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			messArea.setForeground(Color.BLACK);
			messArea.setText("Game On !");
			gridPanel.resetGrid();

		}


	}

	public void printWin(int winner){

		if(winner != 0){

			String message = null;

			if(winner==1){
				message = "Blue Wins!!";
				messArea.setForeground(Color.BLUE);
			}else{
				message = "Red Wins!!";
				messArea.setForeground(Color.RED);
			}
			messArea.setText(message);

		}

	}


	public class MiniMaxSimpleMove implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gridPanel.randPlayer = false;
			gridPanel.simpleMinimax = true;
			gridPanel.resetGrid();
			System.out.println("SimpleMiniMax Player");

		}


	}


}
