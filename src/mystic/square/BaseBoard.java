package mystic.square;

import java.awt.Point;
import java.awt.event.*;
import javax.swing.*;

public class BaseBoard extends JFrame {

	static final long serialVersionUID = 1L;
	private JLabel[][] board = new JLabel[10][10];
	private JLabel winningLabel;
	private int[][] grid = new int[50][50];
	public  boolean isClicked=false;
	JPanel frame = new JPanel();

	public BaseBoard() {
		super("BaseBoard");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(540, 550);
		initializeBoard();
		setVisible(true);

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point location = new Point();
				location.x = e.getX();
				location.y = e.getY();
				checkValidityAndMove(location);
			}
		});
	}

	private void initializeBoard() {
		int i, j, n = 0;
		ImageIcon[] imageFile = new ImageIcon[16];
		ImageIcon winIcon = new ImageIcon("icons//win.jpg");
		winningLabel = new JLabel(winIcon);

		for (i = 0; i < 16; i++) {
			String nth = Integer.toString(i + 1);
			imageFile[i] = new ImageIcon("icons//" + nth + ".jpg");

		}
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				board[j+1][i+1] = new JLabel(imageFile[n]);
				grid[j+1][i+1] = 1+n++;
				System.out.print(grid[j+1][i+1]+" ");
				board[j+1][i+1].setBounds(j * 128, 128 * i, 128, 128);
				board[j+1][i+1].setVisible(true);
				//board[j][i].setText(Integer.toString(grid[i][j]));
				add(board[j+1][i+1]);
			}
			System.out.println("");
		}
	}

	public void move(int ax, int ay, int bx, int by) {
		int temp;
		temp=grid[ax][ay];
		grid[ax][ay]=grid[bx][by];
		grid[bx][by]=temp;
		
		ImageIcon temp1 = new ImageIcon();
		ImageIcon temp2 = new ImageIcon();
		temp1 = (ImageIcon) (board[ax][ay].getIcon());
		temp2 = (ImageIcon) (board[bx][by].getIcon());
		board[ax][ay].setIcon(temp2);
		board[bx][by].setIcon(temp1);
		
	}

	private void checkValidityAndMove(Point location) {

		Point local = getGridIndex(location);
		System.out.println(grid[local.x][local.y]);
		
		
		if (grid[local.x - 1][local.y] == 16) {
			move(local.x, local.y, local.x - 1, local.y);
		}
		
		else if (grid[local.x + 1][local.y] == 16) {
			move(local.x, local.y, local.x + 1, local.y);
		} 
		
		else if (grid[local.x][local.y - 1] == 16) {
			move(local.x, local.y, local.x, local.y - 1);
		} 
		
		else if (grid[local.x][local.y + 1] == 16) {
			move(local.x, local.y, local.x, local.y+1);
		}

	}

	private Point getGridIndex(Point location) {
		Point gridPoint = new Point();
		gridPoint.x = (int) (location.x / 128)+1;

		gridPoint.y = (int) (location.y / 135)+1;

		return gridPoint;
	}
}
