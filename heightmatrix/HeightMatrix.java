package heightmatrix;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HeightMatrix {

	public static void main(String[] args) {
		String input = new String(
			"4 " +
			"1 1 2 0 " +
			"1 6 4 1 " +
			"1 5 1 1 " +
			"1 0 3 4 "
				);
		
		String input2 = new String(
				"6 " +
				"1 1 2 0 1 5 " +
				"1 6 4 6 4 8 " +
				"1 3 4 9 4 8 " +
				"1 5 8 1 3 0 " +
				"1 3 1 1 3 0 " +
				"1 0 3 4 7 1 "
					);
		
		Scanner in = new Scanner(new ByteArrayInputStream(input2.getBytes()));
		int n = in.nextInt();
		Node[][] m = new Node[n][n];
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				m[i][j] = new Node(in.nextInt(), i, j);
			}
		}
		
		System.out.println("Input matrix");
		print(m);
		
		// mark leak on all cells on east border
		for (int r=0; r<n; r++) {
			m[r][n-1].setLeakEast(true);
		}
		// mark leak on all cells on south border
		for (int c=0; c<n; c++) {
			m[n-1][c].setLeakSouth(true);
		}
		
		System.out.println("\nInitialized");
		debugPrint(m);
		
		// to north
		for (int r=n-1; r>=0; r--) {
			for (int c=n-1; c>=0; c--) {
				for (Node node : getL(m, r, c)) {
					if (node.isLeak()) {
						System.out.println(String.format("\nProcessing node[%d][%d]", node.r, node.c));
						for (Node adj : getAdjacent(m, node.r, node.c)) {
							if (adj.value >= node.value) {
								if (node.leakEast) adj.setLeakEast(true);
								if (node.leakSouth) adj.setLeakSouth(true);
							}
						}
						debugPrint(m);
					}
				}
			}
		}
		
		System.out.println("");
		debugPrint(m);

		System.out.println("");
		printAns(m);

	}
	
	/**
	 * Get a list of L shape nodes in the matrix.
	 * 
	 * a b c d
	 * e f g h
	 * i j k l
	 * m n o p
	 * 
	 * if r=3, c=3
	 * it returns
	 *       d
	 *       h
	 *       l
	 * m n o p
	 * 
	 * 
	 * @param m
	 * @param r
	 * @param c
	 * @return
	 */
	public static List<Node> getL(Node[][]m, int r, int c) {
		List<Node> list = new ArrayList<>();
		list.add(m[r][c]);
		if (r>=1) {
			for (int i=r-1; i>=0; i--) {
				list.add(m[i][c]);
			}
		}
		if (c>=1) {
			for (int i=c-1; i>=0; i--) {
				list.add(m[r][i]);
			}
		}
		return list;
	}
	
	/**
	 * Return a list of adjacent nodes
	 * @param m
	 * @param r
	 * @param c
	 * @return
	 */
	public static List<Node> getAdjacent(Node[][]m, int r, int c) {
		int n = m.length;
		List<Node> list = new ArrayList<>();
		if (r+1<n) list.add(m[r+1][c]);
		if (r-1>=0) list.add(m[r-1][c]);
		if (c+1<n) list.add(m[r][c+1]);
		if (c-1>=0) list.add(m[r][c-1]);
		return list;
	}
	
	public static void debugPrint(Node[][] m) {
		for (int i=0; i<m.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j=0; j<m[0].length; j++) {
				sb.append(m[i][j].toString4Debug()).append(" ");
			}
			System.out.println(sb.toString());
		}
	}
	
	public static void print(Node[][] m) {
		for (int i=0; i<m.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j=0; j<m[0].length; j++) {
				sb.append(m[i][j].value).append(" ");
			}
			System.out.println(sb.toString());
		}
	}
	
	public static void printAns(Node[][] m) {
		print(m);
		System.out.println("");

		for (int i=0; i<m.length; i++) {
			for (int j=0; j<m[0].length; j++) {
				Node node = m[i][j];
				if (node.isLeakEast() && node.isLeakSouth()) {
					System.out.println(String.format("m[%d][%d]=%d ", i, j, node.value));
				}
			}
		}
	}
	
	public static class Node {
		int value;
		boolean leakEast = false;
		boolean leakSouth = false;
		int r;
		int c;
		
		public Node(int value, int r, int c) {
			this.value = value;
			this.r = r;
			this.c = c;
		}
		
		public void setLeakEast(boolean b) {
			this.leakEast = b;
		}
		
		public void setLeakSouth(boolean b) {
			this.leakSouth = b;
		}
		
		public boolean isLeakEast() {
			return this.leakEast;
		}
		
		public boolean isLeakSouth() {
			return this.leakSouth;
		}
		
		public boolean isLeak() {
			return this.leakEast || this.leakSouth;
		}
		
		public String toString() {
			return value + "";
		}
		
		public String toString4Debug() {
			return value + "(" + (leakEast? ">":0) + ":" + (leakSouth? "V":0) + ")";
		}
	}
}
