import java.util.Scanner;
abstract class ChessPiece {
    String type;
    boolean isWhite; 

    public ChessPiece(String type, boolean isWhite) {
        this.type = type;
        this.isWhite = isWhite;
    }

    abstract boolean isValidMove(int x1, int y1, int x2, int y2);

    abstract boolean canBeCaptured(int x1, int y1, int x2, int y2);

    abstract boolean canBePromoted(int x1, int y1, int x2, int y2);
}

class Pawn extends ChessPiece {
    public Pawn(boolean isWhite) {
        super("pawn", isWhite);
    }

    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        if (isWhite && y2 == y1 - 1 && x2 == x1) {
            return true;
        } else if (!isWhite && y2 == y1 + 1 && x2 == x1) {
            return true;
        }

        if ((isWhite && y2 == y1 - 1 && Math.abs(x2 - x1) == 1)
                || (!isWhite && y2 == y1 + 1 && Math.abs(x2 - x1) == 1)) {
            return true;
        }

        if ((isWhite && y1 == 2 && y2 == 4 && x2 == x1)
                || (!isWhite && y1 == 7 && y2 == 5 && x2 == x1)) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBeCaptured(int x1, int y1, int x2, int y2) {
        if ((isWhite && y2 == y1 - 1 && Math.abs(x2 - x1) == 1)
                || (!isWhite && y2 == y1 + 1 && Math.abs(x2 - x1) == 1)) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBePromoted(int x1, int y1, int x2, int y2) {
        if ((isWhite && y2 == 0) || (!isWhite && y2 == 7)) {
            return true;
        }

        return false;
    }
}

class Knight extends ChessPiece {
    public Knight(boolean isWhite) {
        super("knight", isWhite);
    }

    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        if (Math.abs(x2 - x1) == 2 && Math.abs(y2 - y1) == 1) {
            return true;
        } else if (Math.abs(x2 - x1) == 1 && Math.abs(y2 - y1) == 2) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBeCaptured(int x1, int y1, int x2, int y2) {
        if (isValidMove(x1, y1, x2, y2)) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBePromoted(int x1, int y1, int x2, int y2) {
        return false;
    }
}

class Bishop extends ChessPiece {
    public Bishop(boolean isWhite) {
        super("bishop", isWhite);
    }

    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        if (dx == dy) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBeCaptured(int x1, int y1, int x2, int y2) {
        if (isValidMove(x1, y1, x2, y2)) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBePromoted(int x1, int y1, int x2, int y2) {
        return false;
    }
}

class Rook extends ChessPiece {
    public Rook(boolean isWhite) {
        super("rook", isWhite);
    }

    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            return true;
        } else if (y1 == y2) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBeCaptured(int x1, int y1, int x2, int y2) {
        if (isValidMove(x1, y1, x2, y2)) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBePromoted(int x1, int y1, int x2, int y2) {
        return false;
    }
}

class Queen extends ChessPiece {
    public Queen(boolean isWhite) {
        super("queen", isWhite);
    }

    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        if (dx == dy || x1 == x2 || y1 == y2) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBeCaptured(int x1, int y1, int x2, int y2) {
        if (isValidMove(x1, y1, x2, y2)) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBePromoted(int x1, int y1, int x2, int y2) {
        return false;
    }
}

class King extends ChessPiece {
    public King(boolean isWhite) {
        super("king", isWhite);
    }

    @Override
    boolean isValidMove(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        if (dx <= 1 && dy <= 1) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBeCaptured(int x1, int y1, int x2, int y2) {
        if (isValidMove(x1, y1, x2, y2)) {
            return true;
        }

        return false;
    }

    @Override
    boolean canBePromoted(int x1, int y1, int x2, int y2) {
        return false;
    }
}

class Board {
    ChessPiece[][] squares;

    public Board() {
        squares = new ChessPiece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = null;
            }
        }

        for (int i = 0; i < 8; i++) {
            squares[1][i] = new Pawn(true);
            squares[6][i] = new Pawn(false);
        }

        squares[0][0] = new Rook(true);
        squares[0][7] = new Rook(true);
        squares[7][0] = new Rook(false);
        squares[7][7] = new Rook(false);
        squares[0][1] = new Knight(true);
        squares[0][6] = new Knight(true);
        squares[7][1] = new Knight(false);
        squares[7][6] = new Knight(false);
        squares[0][2] = new Bishop(true);
        squares[0][5] = new Bishop(true);
        squares[7][2] = new Bishop(false);
        squares[7][5] = new Bishop(false);
        squares[0][3] = new Queen(true);
        squares[7][4] = new Queen(false);
        squares[7][3] = new King(false);
        squares[0][4] = new King(true);
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j] != null) {
                    System.out.printf("%8s",squares[i][j].type + " ");
                } else {
                    System.out.printf("%8s","_ ");
                }
            }
            System.out.println();
        }
    }

    public boolean isSquareOccupied(int x, int y) {
        if (squares[y][x] != null) {
            return true;
        }

        return false;
    }

    public ChessPiece getPiece(int x, int y) {
        return squares[y][x];
    }

    public void movePiece(int x1, int y1, int x2, int y2) {
        squares[y2][x2] = squares[y1][x1];
        squares[y1][x1] = null;
    }
}

class Player {
    String name;
    boolean isWhite;

    public Player(String name, boolean isWhite) {
        this.name = name;
        this.isWhite = isWhite;
    }
}

class Game {
    Board board;
    Player whitePlayer;
    Player blackPlayer;
    boolean gameOver;

    public Game() {
        board = new Board();
        whitePlayer = new Player("White Player", true);
        blackPlayer = new Player("Black Player", false);
        gameOver = false;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            System.out.println("Current board state:");
            board.displayBoard();

            System.out.println("Enter the starting position (row and column) of the piece you want to move (e.g., 'e4'):");
            String startInput = scanner.next();
            int startX = startInput.charAt(0) - 'a';
            int startY = Character.getNumericValue(startInput.charAt(1)) - 1;

            System.out.println("Enter the destination position (row and column) of the piece you want to move (e.g., 'e5'):");
            String endInput = scanner.next();
            int endX = endInput.charAt(0) - 'a';
            int endY = Character.getNumericValue(endInput.charAt(1)) - 1;

            if (board.isSquareOccupied(startX, startY)) {
                ChessPiece piece = board.getPiece(startX, startY);

                if (piece.isWhite == whitePlayer.isWhite) {
                    if (piece.isValidMove(startX, startY, endX, endY)) {
                        board.movePiece(startX, startY, endX, endY);
                    } else {
                        System.out.println("Illegal move. Try again.");
                    }
                } else {
                    System.out.println("It's not your turn. Try again.");
                }
            } else {
                System.out.println("No piece at the starting position. Try again.");
            }
        }
    }
}

public class Prac68 {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}