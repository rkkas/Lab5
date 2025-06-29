import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scoreboard scoreboard = new Scoreboard();

        System.out.println("=== CONECTA 4 ===");

        System.out.print("Ingrese nombre del Jugador A: ");
        String playerA = scanner.nextLine();
        System.out.print("Ingrese nombre del Jugador B: ");
        String playerB = scanner.nextLine();

        scoreboard.registerPlayer(playerA);
        scoreboard.registerPlayer(playerB);

        Game game = new Game(playerA, playerB);
        String winner = game.play();

        if (game.getStatus().equals("DRAW")) {
            scoreboard.addGameResult(playerA, playerB, true);
        } else {
            String loser = winner.equals(playerA) ? playerB : playerA;
            scoreboard.addGameResult(winner, loser, false);
        }

        System.out.println("\n=== RESULTADOS ACTUALES ===");
        Player player1 = scoreboard.winSuccessor(-1)[0]; // Mostrar alguien con al menos 1 victoria
        Player[] allPlayers = scoreboard.winRange(0, 100);

        for (int i = 0; i < allPlayers.length; i++) {
            Player p = allPlayers[i];
            System.out.println(p.toString() + " | WinRate: " + (p.winRate() * 100) + "%");
        }
    }
}
