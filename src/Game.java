import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    int size;
    long score;
    Piece[][] board;
    Random random = new Random();
    boolean isOver = false;

    public Game(int size) {
        this.size = size;
        board = new Piece[size][size];
    }

    public static Piece giveAPiece() {
//        return new NumPiece();
        return new ShengxiaoPiece();
    }

    public static void main(String[] args) {
        Game game = new Game(4);
        game.play();
        game.print();

    }

    public void mergeRow(Piece[] pieces) {

        compressRow(pieces);
        for (int i = 0; i < pieces.length - 1; i++) {
            if (!pieces[i].isBlank() && pieces[i].equals(pieces[i + 1])) {
                score += pieces[i].expand();
                pieces[i + 1].setBlank();
                compressRow(pieces);
            }
        }
    }

    public void compressRow(Piece[] pieces) {
        ArrayList<Piece> temp = new ArrayList<>();
        for (int i = 0; i < pieces.length; i++) {
            if (!pieces[i].isBlank()) {
                temp.add(pieces[i]);
            }
        }

        for (int i = 0; i < pieces.length; i++) {
            if (i < temp.size()) {
                pieces[i] = temp.get(i);
            } else {
                pieces[i] = giveAPiece();
            }
        }
    }

    /**
     * 开始游戏
     */
    public void play() {
        score = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = giveAPiece();
            }
        }

        randomOnePiece();
        randomOnePiece();
        Scanner scanner = new Scanner(System.in);

        while (!isOver) {
            print();
            String key = scanner.next();
            while (!("a".equals(key)) && !("d".equals(key)) && !("w".equals(key)) && !("s".equals(key))) {
                System.out.println("输入不合法");
                key = scanner.next();
            }
            System.out.println(key);
            directionMerge(key);
            randomOnePiece();
            System.out.println("当前得分为：" + score);
        }
    }

    /**
     * 判断棋盘已满的情况下是否能合并
     *
     * @return 能合并返回true，否则返回false
     */
    public boolean canMerge() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (board[i][j].equals(board[i][j + 1]) || board[i][j].equals(board[i + 1][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 响应用户输入
     *
     * @param key 用户输入的字符串
     */
    public void directionMerge(String key) {

        if ("a".equals(key)) {
            for (int i = 0; i < size; i++) {
                mergeRow(board[i]);
            }
        }

        if ("d".equals(key)) {
            for (int i = 0; i < size; i++) {
                Piece[] nPieces = new Piece[size];
                for (int j = 0; j < size; j++) {
                    nPieces[size - j - 1] = board[i][j];
                }

                mergeRow(nPieces);

                for (int j = 0; j < size; j++) {
                    board[i][j] = nPieces[size - j - 1];
                }
            }
        }

        if ("w".equals(key)) {
            for (int i = 0; i < size; i++) {
                Piece[] nPieces = new Piece[size];
                for (int j = 0; j < size; j++) {
                    nPieces[j] = board[j][i];
                }
                mergeRow(nPieces);

                for (int j = 0; j < size; j++) {
                    board[j][i] = nPieces[j];
                }
            }
        }

        if ("s".equals(key)) {
            for (int i = 0; i < size; i++) {
                Piece[] nPieces = new Piece[size];
                for (int j = 0; j < size; j++) {
                    nPieces[size - j - 1] = board[j][i];
                }
                mergeRow(nPieces);

                for (int j = 0; j < size; j++) {
                    board[j][i] = nPieces[size - j - 1];
                }
            }
        }
    }

    /**
     * 若棋盘有空位则随机在一个空位生成一个新的棋子
     * 若棋盘没有空位，且当前没有合并可能，则游戏结束
     */
    public void randomOnePiece() {

        ArrayList<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].isBlank()) {
                    pieces.add(board[i][j]);
                }
            }
        }

        if (pieces.size() == 0) {
            if (!canMerge()) {
                this.over();
            }
        } else {
            int i = random.nextInt(pieces.size());
            Piece piece = pieces.get(i);
            piece.init();

            int tmp = random.nextInt(100) + 1;

            if (tmp > 85) {
                piece.expand();
            }

            if (tmp > 95) {
                piece.expand();
            }

            if (tmp > 98) {
                piece.expand();
            }
        }
    }

    /**
     * 游戏结束
     */
    public void over() {
        System.out.println("游戏结束");
        isOver = true;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
