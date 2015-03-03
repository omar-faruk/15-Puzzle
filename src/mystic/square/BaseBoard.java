package mystic.square;

import java.awt.Point;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class BaseBoard extends JFrame {

	static final long serialVersionUID = 1L;
	private JLabel[][] board = new JLabel[10][10];
	private JLabel winningLabel;
	private int[][][] grid = new int[50][10][10];
	public boolean isClicked = false;
	int setCount = 0, totalSet = 0;
	JFrame frame = new JFrame();

	public BaseBoard() {
		super("BaseBoard");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(540, 550);
		final ImageIcon winIcon = new ImageIcon("res/win.jpg");
		winningLabel = new JLabel(winIcon);

		try {
			listAll();
		} catch (Exception e) {
		}

		initializeBoard();
		setVisible(true);

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point location = new Point();
				location.x = e.getX();
				location.y = e.getY();
				checkValidityAndMoveTo(location);
			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent k) {
				if (k.getKeyCode() == 34) {
					setCount++;
					System.out.println(setCount);
					// write a function to set the board to a new shuffle
				}
			}
		});
	}

	private void initializeBoard() {
		int i, j;
		ImageIcon[] imageFile = new ImageIcon[16];

		for (i = 0; i < 16; i++) {
			String iconFile = "res/" + Integer.toString(i + 1) + ".jpg";
			imageFile[i] = new ImageIcon();
			imageFile[i] = new ImageIcon(iconFile);

		}

		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				int index = grid[setCount][j + 1][i + 1];
				board[j + 1][i + 1] = new JLabel(imageFile[index - 1]);
				// grid[j + 1][i + 1] = 1 + n++;
				System.out.print(grid[setCount][j + 1][i + 1] + " ");
				board[j + 1][i + 1].setBounds(j * 128, 128 * i, 128, 128);
				board[j + 1][i + 1].setVisible(true);
				// board[j][i].setText(Integer.toString(grid[i][j]));
				add(board[j + 1][i + 1]);
			}
			System.out.println("");
		}
	}

	public void move(int ax, int ay, int bx, int by) {
		int temp;
		temp = grid[setCount][ax][ay];
		grid[setCount][ax][ay] = grid[setCount][bx][by];
		grid[setCount][bx][by] = temp;

		ImageIcon temp1 = new ImageIcon();
		ImageIcon temp2 = new ImageIcon();
		temp1 = (ImageIcon) (board[ax][ay].getIcon());
		temp2 = (ImageIcon) (board[bx][by].getIcon());
		board[ax][ay].setIcon(temp2);
		board[bx][by].setIcon(temp1);

	}

	private void checkValidityAndMoveTo(Point location) {

		Point local = getGridIndex(location);

		if (grid[setCount][local.x - 1][local.y] == 16) {
			move(local.x, local.y, local.x - 1, local.y);
		}

		else if (grid[setCount][local.x + 1][local.y] == 16) {
			move(local.x, local.y, local.x + 1, local.y);
		}

		else if (grid[setCount][local.x][local.y - 1] == 16) {
			move(local.x, local.y, local.x, local.y - 1);
		}

		else if (grid[setCount][local.x][local.y + 1] == 16) {
			move(local.x, local.y, local.x, local.y + 1);
		}

	}

	private Point getGridIndex(Point location) {
		Point gridPoint = new Point();
		gridPoint.x = (int) (location.x / 128) + 1;

		gridPoint.y = (int) (location.y / 135) + 1;

		return gridPoint;
	}

	public void listAll() throws IOException {
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader("res/input.txt")));

			while (s.hasNext()) {
				for (totalSet = 0; totalSet < 5; totalSet++) {
					for (int i = 1; i <= 4; i++) {
						for (int j = 1; j <= 4; j++) {
							if (s.hasNext()) {
								grid[totalSet][j][i] = s.nextInt();
							}
						}
					}
				}
				break;
			}
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
