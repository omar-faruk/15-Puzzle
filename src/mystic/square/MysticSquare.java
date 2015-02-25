package mystic.square;

import java.awt.Point;
import java.util.Scanner;

/**
 *
 * @author omar
 */
public class MysticSquare {
	static Scanner sc = new Scanner(System.in);

	public static void main(String args[]) {
		BaseBoard newGame = new BaseBoard();
		while (true) {
			int ax,ay,bx,by;
			ax=sc.nextInt();
			ay=sc.nextInt();
			bx=sc.nextInt();
			by=sc.nextInt();
			//newGame.move(ax,ay,bx,by);
		}
	}
}
