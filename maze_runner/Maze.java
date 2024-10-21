package maze_runner;

import java.util.LinkedList;
import java.util.Queue;

public class Maze {
	char[][] maze;
	
	Maze(int row,int column){
		this.maze = new char[row][column];
		intialize();
	}
	
	private void intialize() {
		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze[0].length;j++) {
				maze[i][j] = '0';
			}
		}
	}
	
	public void printMaze() {
		System.out.println();
		for(int i=0;i<maze.length;i++) {
			for(int j=0;j<maze[0].length;j++) {
				System.out.print(maze[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public boolean intializeTreasure(int row,int column) {
		row--;
		column--;
		if(row >= maze.length && column >= maze[0].length) {
			return false;
		}
		maze[row][column] = 'T';
		return true;
	}
	
	private static class Node { // Inner class represents the a node in the maze for pathFinding
		int row,column,steps;
//		Node previous;
		
		Node(int row,int column,int steps,Node previous){
			this.row = row;
			this.column = column;
			this.steps = steps;
//			this.previous = previous;
		}
	}
	
	public int shortestPath(int row, int column) {
		row--;
		column--;
		if(row>=maze.length || column>=maze[0].length) return -1;
		if(maze[row][column] == 'T') return 0;
		maze[row][column] = 'A';
		
		int rowLength = maze.length;
		int colLength = maze[0].length;
		
		boolean[][] visited = new boolean[rowLength][colLength];
		int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(row,column,0,null));
		visited[row][column] = true;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			for(int[] direction : directions) {
				int nextRow = current.row + direction[0];
				int nextColumn = current.row + direction[1];
				
				if(nextRow >=0 && nextRow < rowLength && nextColumn >=0 && nextColumn < colLength && !visited[nextRow][nextColumn]) {
					if(maze[nextRow][nextColumn]=='T') return current.steps+1;
					Node nextNode = new Node(nextRow, nextColumn, current.steps+1, current);
					queue.add(nextNode);
					visited[nextRow][nextColumn] = true;
				}
			}
		}
		return -1;
	}
}
