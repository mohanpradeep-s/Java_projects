package maze_runner;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter row and column for maze: ");
		int row = sc.nextInt();
		int column = sc.nextInt();
		System.out.println("Enter the row and column to place the Treasure: ");
		int t_row = sc.nextInt();
		int t_col = sc.nextInt();
		sc.close();
		Maze maze = new Maze(row,column);
		maze.intializeTreasure(t_row, t_col);
		int shortestPath = maze.shortestPath(4,1);
		System.out.println("The Shortest path is: "+shortestPath);
//		maze.printMaze();
	}

}
