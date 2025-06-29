import java.util.Scanner;

public class Game {
    private String status; // IN_PROGRESS, VICTORY, DRAW
    private String winnerPlayerName;
    private String playerNameA;
    private String playerNameB;
    private ConnectFour connectFour;

    public Game(String playerNameA, String playerNameB) {
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        this.status = "IN_PROGRESS";
        this.winnerPlayerName = "";
        this.connectFour = new ConnectFour();
    }

    public String play() {
        Scanner scanner = new Scanner(System.in);
        char symbol;
        boolean validMove;

        while (status.equals("IN_PROGRESS")) {
            connectFour.printBoard();
            symbol = connectFour.getCurrentSymbol();

            if (symbol == 'X') {
                System.out.println(playerNameA + " (X), elige columna (0-6): ");
            } else {
                System.out.println(playerNameB + " (O), elige columna (0-6): ");
            }

            int col = scanner.nextInt();
            validMove = connectFour.makeMove(col);

            if (!validMove) {
                System.out.println("Movimiento inválido. Intenta otra columna.");
                continue;
            }

            String gameState = connectFour.isGameOver();

            if (gameState.equals("X_WIN")) {
                status = "VICTORY";
                winnerPlayerName = playerNameA;
                System.out.println("¡Ganó " + playerNameA + " (X)!");
            } else if (gameState.equals("O_WIN")) {
                status = "VICTORY";
                winnerPlayerName = playerNameB;
                System.out.println("¡Ganó " + playerNameB + " (O)!");
            } else if (gameState.equals("DRAW")) {
                status = "DRAW";
                winnerPlayerName = "";
                System.out.println("¡Empate!");
            }
        }

        connectFour.printBoard();
        return winnerPlayerName;
    }

    public String getStatus() {
        return status;
    }

    public String getWinnerPlayerName() {
        return winnerPlayerName;
    }
}
