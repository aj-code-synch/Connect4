package connect4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	resetButton,
	printGrid,
	checkGrid;


	private RandomMoves randButtonHandle;
	private ResetGrid resetHandle;
	private MiniMaxSimpleMove miniMaxHandle;
	private PrintGrid printGridHandle;
	private CheckGrid checkGridHandle;


	public ArrayList<JTextField> fieldArray = new ArrayList<JTextField>();

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


		printGrid = new JButton("Print Grid");
		buttonPanel.add(printGrid);
		printGridHandle = new PrintGrid();
		printGrid.addActionListener(printGridHandle);



		checkGrid = new JButton("Check Grid");
		buttonPanel.add(checkGrid);
		checkGridHandle = new CheckGrid();
		checkGrid.addActionListener(checkGridHandle);

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


	public class PrintGrid implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 7; j++) {
					System.out.print(Grid.gridPrint[i][j] + ",");
				}
				System.out.println("");
			}

			System.out.println("};");
			//			Move.lastX = 5;
			//			Move.lastY = 2;
			//			SimpleMiniMax.moveCount = 0;

			System.out.println("Move.lastX = " + Cell.lastX + ";");
			System.out.println("Move.lastY = " + Cell.lastY + ";");
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

	public class CheckGrid implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {


			JFrame CheckFrame = new JFrame("Check");
			JPanel mainP = new JPanel();
			mainP.setName("MainP");
			
			JPanel gridP = new JPanel();
			gridP.setName("gridP");
			gridP.setLayout(new GridLayout(6,7));
			gridP.setPreferredSize(new Dimension(300, 360));

			JPanel buttonP = new JPanel();
			buttonP.setName("buttonP");
			buttonP.setPreferredSize(new Dimension(50,50));



			CheckFrame.add(mainP);


			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 7; j++) {
					JTextField inputfld= new JTextField(5);
					fieldArray.add(inputfld);
					gridP.add(inputfld);

				}

			}

//			mainP.add(gridP);
//			mainP.add(buttonP);

			
			mainP.add(gridP,BorderLayout.CENTER);
			mainP.add(buttonP,BorderLayout.PAGE_END);
			
			
			CheckFrame.add(mainP);

			JButton go = new JButton("Go");
			buttonP.add(go);


			go.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					int gridCopy[][] = new int[6][7];
					for (int i = 0; i < 6; i++) {
						for (int j = 0; j < 7; j++) {


							gridCopy[i][j] = Integer.parseInt(fieldArray.get(i*6 + j).getText());
						}

					}


					gridPanel.manualCheckGrid(2, gridCopy);

				}
			});

			CheckFrame.setVisible(true);
			CheckFrame.setSize(600,700);
			CheckFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		}


	}




}




