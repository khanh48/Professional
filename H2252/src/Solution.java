import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    private static Scanner sc;
    private static UserSolution usersolution = new UserSolution();

    static final int MAX_N = 1000;

    static final int CMD_INIT = 100;
    static final int CMD_DESTROY = 200;
    static final int CMD_PUT = 300;

    static int[][] PieceU = new int[MAX_N + 2][4];
    static int[][] PieceR = new int[MAX_N + 2][4];
    static int[][] PieceD = new int[MAX_N + 2][4];
    static int[][] PieceL = new int[MAX_N + 2][4];


    private static int run() throws IOException {
        int isOK = 0;

        int mN, mM;

        int mOption;
        int num;

        int N = sc.nextInt();
        int cmd, result, check;

        int[] mPiece = new int[4];

        for (int c = 0; c < N; ++c) {

            cmd =  sc.nextInt();
            switch (cmd) {
            case CMD_INIT:
                mN = sc.nextInt();
                mM = sc.nextInt();
                for (int i = 0; i < mN; i++) {
                    PieceU[i][0] = sc.nextInt();
                    PieceU[i][1] = sc.nextInt();
                    PieceU[i][2] = sc.nextInt();
                    PieceU[i][3] = sc.nextInt();
                }
                for (int i = 0; i < mN; i++) {
                    PieceR[i][0] = sc.nextInt();
                    PieceR[i][1] = sc.nextInt();
                    PieceR[i][2] = sc.nextInt();
                    PieceR[i][3] = sc.nextInt();
                }
                for (int i = 0; i < mN; i++) {
                    PieceD[i][0] = sc.nextInt();
                    PieceD[i][1] = sc.nextInt();
                    PieceD[i][2] = sc.nextInt();
                    PieceD[i][3] = sc.nextInt();
                }
                for (int i = 0; i < mN; i++) {
                    PieceL[i][0] = sc.nextInt();
                    PieceL[i][1] = sc.nextInt();
                    PieceL[i][2] = sc.nextInt();
                    PieceL[i][3] = sc.nextInt();
                }
                usersolution.init(mN, mM, PieceU, PieceR, PieceD, PieceL);
                isOK = 1;
                break;

            case CMD_PUT:
                mPiece[0] = sc.nextInt();
                mPiece[1] = sc.nextInt();
                mPiece[2] = sc.nextInt();
                mPiece[3] = sc.nextInt();
                result = usersolution.put(mPiece);
                check = sc.nextInt();
                if (result != check)
                    isOK = 0;
                break;
            default:
                isOK = 0;
                break;
            }
        }
        usersolution.destroy();
        return isOK;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;
        System.setIn(new java.io.FileInputStream("sample_input.txt"));
        sc = new Scanner(System.in);
        T = sc.nextInt();
        MARK = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            if (run() == 1)
                System.out.println("#" + tc + " " + MARK);
            else
                System.out.println("#" + tc + " 0");
        }
        sc.close();
    }
}