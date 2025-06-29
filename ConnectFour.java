public class ConnectFour {
    private char[][] grid;
    private char currentSymbol;

    public ConnectFour() {
        grid = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = ' ';
            }
        }
        currentSymbol = 'X';
    }

    public boolean makeMove(int col) {
        if (col < 0 || col >= 7) {
            return false;
        }

        for (int row = 5; row >= 0; row--) {
            if (grid[row][col] == ' ') {
                grid[row][col] = currentSymbol;
                if (currentSymbol == 'X') {
                    currentSymbol = 'O';
                } else {
                    currentSymbol = 'X';
                }
                return true;
            }
        }

        return false; // columna llena
    }

    public char[][] getGrid() {
        return grid;
    }

    public char getCurrentSymbol() {
        return currentSymbol;
    }

    public void printBoard() {
        System.out.println(" 0 1 2 3 4 5 6");
        for (int i = 0; i < 6; i++) {
            System.out.print("|");
            for (int j = 0; j < 7; j++) {
                System.out.print(grid[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }

    public String isGameOver() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                char c = grid[row][col];
                if (c == ' ') continue;

                if (col <= 3) {
                    if (grid[row][col + 1] == c &&
                        grid[row][col + 2] == c &&
                        grid[row][col + 3] == c) {
                        return c + "_WIN";
                    }
                }

                if (row <= 2) {
                    if (grid[row + 1][col] == c &&
                        grid[row + 2][col] == c &&
                        grid[row + 3][col] == c) {
                        return c + "_WIN";
                    }
                }

                if (row <= 2 && col <= 3) {
                    if (grid[row + 1][col + 1] == c &&
                        grid[row + 2][col + 2] == c &&
                        grid[row + 3][col + 3] == c) {
                        return c + "_WIN";
                    }
                }

                if (row <= 2 && col >= 3) {
                    if (grid[row + 1][col - 1] == c &&
                        grid[row + 2][col - 2] == c &&
                        grid[row + 3][col - 3] == c) {
                        return c + "_WIN";
                    }
                }
            }
        }

        boolean full = true;
        for (int col = 0; col < 7; col++) {
            if (grid[0][col] == ' ') {
                full = false;
                break;
            }
        }

        if (full) {
            return "DRAW";
        }

        return "IN_PROGRESS";
    }
}
