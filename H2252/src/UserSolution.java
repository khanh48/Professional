import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

public class UserSolution {

	public static int MAX_N;
	public static int M_INT;

	static HashMap<String, Piece> map;
	static HashMap<String, Piece> canPlace;
	static PriorityQueue<Piece> maxSame;
	
	void init(int N, int M, int mU[][], int mR[][], int mB[][], int mL[][]) {
		MAX_N = N + 2;
		M_INT = M;
		canPlace = new HashMap<>();
		map = new HashMap<>();
		int x, y;
		for(int i = 0; i < N; i++) {
			x = i + 1;
			y = 0;

			Piece piece = new Piece(mU[i][0], mU[i][1], mU[i][2], mU[i][3], M, x, y);
			canPlace.put(getPos(x, y), piece);
			map.put(getPos(x, y), piece);
		}
		for(int i = 0; i < N; i++) {
			x = N + 1;
			y = i + 1;

			Piece piece = new Piece(mR[i][0], mR[i][1], mR[i][2], mR[i][3], M, x, y);
			canPlace.put(getPos(x, y), piece);
			map.put(getPos(x, y), piece);
		}
		for(int i = 0; i < N; i++) {
			x = i + 1;
			y = N + 1;

			Piece piece = new Piece(mB[i][0], mB[i][1], mB[i][2], mB[i][3], M, x, y);
			canPlace.put(getPos(x, y), piece);
			map.put(getPos(x, y), piece);
		}
		for(int i = 0; i < N; i++) {
			x = 0;
			y = i + 1;

			Piece piece = new Piece(mL[i][0], mL[i][1], mL[i][2], mL[i][3], M, x, y);
			canPlace.put(getPos(x, y), piece);
			map.put(getPos(x, y), piece);
		}
	}
	
	void destroy() {
		
	}
	
	int put(int mPiece[]) {
		maxSame = new PriorityQueue<>(new Comparator<Piece>() {
			@Override
			public int compare(UserSolution.Piece o1, UserSolution.Piece o2) {
				return o2.same - o1.same;
			}
		});
		
		Piece piece = new Piece(mPiece[0], mPiece[1], mPiece[2], mPiece[3], M_INT, 0, 0);
		for(int w = 0; w < 4; w++) {
			for (Piece cur : canPlace.values()) {
				int[] tmp = cur.isSame(piece);
				if(tmp[2] == 1) {
					int x, y;
					x = tmp[0];
					y = tmp[1];
					piece.x = x;
					piece.y = y;
					canPlace.put(getPos(x, y), piece);
					canPlace.remove(getPos(cur.x, cur.y));
					map.put(getPos(x, y), piece);
					return x + y;
				}
			}
			piece.rotate();
		}
		return -1;
	}

	static String getPos(int x, int y) {
		return x + ":" + y;
	}
	
	class Piece implements Cloneable{
		public int top, right, bottom, left, M, x, y, same;
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		int[] num = {1110, 11110, 111110, 1111110, 11111110, 111111110};
		
		Piece(int top, int right, int botton, int left, int M, int x, int y) {
			this.top = top;
			this.right = right;
			this.bottom = flip(botton);
			this.left = flip(left);
			this.M = M;
			this.x = x;
			this.y = y;
			this.same = 0;
		}
		
		int[] isSame(Piece piece) {
			int[] result = {-1, -1, 0};
			for(int i = 0; i < 4; i++) {
				int tmp = check(piece, i);
				if(tmp != -1) {
					int xt, yt;
					xt = dx[tmp] + this.x;
					yt = dy[tmp] + this.y;
					if(xt <= 0 || yt <= 0 || xt >= MAX_N - 1 || yt >= MAX_N - 1 || map.containsKey(getPos(xt, yt)))
						continue;
					result[0] = xt;
					result[1] = yt;
					result[2] = 1;
					return result;
				}
			}
			return result;
		}
		
		int check(Piece piece, int dir) {
			int tmp = num[piece.M - 3];
			if(dir == 0) {
				if(tmp - this.bottom == piece.top)
					return 2;
			}
			else if(dir == 1) {
				if(tmp - this.left == piece.right)
					return 3;
			}
			else if(dir == 2) {
				if(tmp - this.top == piece.bottom)
					return 0;
			}
			else if(dir == 3) {
				if(tmp - this.right == piece.left)
					return 1;
			}
			return -1;
		}
		
		int count(Piece piece) {
			int total = 0;
			for(int i = 0; i < 4; i++) {
				
			}
			return total;
		}
		int flip(int input) {
		    int reversedNum = 0;
		    int input_long = input;

		    while (input_long != 0) {
		        reversedNum = reversedNum * 10 + input_long % 10;
		        input_long = input_long / 10;
		    }
		    
		    return reversedNum;
		}
		
		void rotate() {
			int t = top, r = right, b = bottom, l = left;
			
			this.top = flip(l);
			this.right = t;
			this.bottom = flip(r);
			this.left = b;
		}
		
		public Piece clone(){
			try {
				return (Piece)super.clone();
			} catch (CloneNotSupportedException e) {
				return null;
			}
		}
	}
	
	public static int getRandom(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}

void te(){

	PriorityQueue<Piece> test;
	test = new PriorityQueue<>((a,b) -> b.same - a.same);
	Piece piece = new Piece(1232, 1241, 4121, 3213, 4, 1, 2);
	piece.same = 5;
	test.add(piece);
	Piece tmp = null;
	tmp = piece.clone();
	tmp.same = 3;
	test.add(tmp);
	tmp = piece.clone();
	tmp.same = 9;
	test.add(tmp);
	for (Piece piec : test) {
	System.out.println(piec.same);
		
	}
}
	
	public static void main(String[] args){
		new UserSolution().te();
	}
}
