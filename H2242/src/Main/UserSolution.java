package Main;

import java.util.ArrayDeque;
import java.util.TreeSet;

public class UserSolution {
	static int dR[] = {0, 0, 1, -1};
	static int dC[] = {1, -1, 0, 0};
	TreeSet<Cell>[] mRows = new TreeSet[701];
	TreeSet<Cell>[] mCols = new TreeSet[10001];
	int map[][] = new int[701][10001];
	int R, C, ans, visit;
	public void init(int R, int C, int M, int mStructureR[], int mStructureC[]) {
		this.R = R;
		this.C = C;
		for (int i = 0; i < R; i++)
			mRows[i] = new TreeSet<>((o1, o2) -> o1.col - o2.col);
		for (int i = 0; i < C; i++)
			mCols[i] = new TreeSet<>((o1, o2) -> o1.row - o2.row);
		for (int i = 0; i < M; i++) {
			Cell cell = new Cell(mStructureR[i], mStructureC[i], 0);
			mRows[mStructureR[i]].add(cell);
			mCols[mStructureC[i]].add(cell);
		}
	}
	
	public int minDamage(int rowStart, int colStart, int rowEnd, int colEnd) {
		Cell start = new Cell(rowStart, colStart, 0);
		Cell end = new Cell(rowEnd, colEnd, 0);
		if(start.checkStop(end)) {
			return 0;
		}
		mRows[rowEnd].add(end);
		mCols[colEnd].add(end);
		
		++visit;
		ArrayDeque<Cell> queue = new ArrayDeque<>();
		queue.add(start);
		ans = -1;
		while(!queue.isEmpty() && ans == -1) {
			Cell cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				Cell next = cur.next(i);
				if(next != null) {
					if(next.checkStop(end)) {
						ans = cur.counter;
						break;
					}
					int r = next.row + dR[i], c = next.col + dC[i];
					
					if(map[r][c] != visit) {
						map[r][c] = visit;
						queue.add(new Cell(r, c, cur.counter + 1));
					}
				}
			}
		}
		
		mRows[rowEnd].remove(end);
		mCols[colEnd].remove(end);
		
		return ans;
	}
	
 	class Cell{
		int row, col, counter;
		
		public Cell(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.counter = count;
		}
		
		Cell next(int way) {
			if(way == 0) return mRows[row].lower(this);
			if(way == 1) return mRows[row].higher(this);
			if(way == 2) return mCols[col].lower(this);
			return mCols[col].higher(this);
		}
		
		boolean checkStop(Cell cell) {
			return this.row == cell.row && this.col == cell.col;
		}
		
	}
}
