import java.util.*;

public class SnakeLadderGame {

    // Inner class: Board
    class Board {
        private final Map<Integer, Integer> snakes = new HashMap<>();
        private final Map<Integer, Integer> ladders = new HashMap<>();

        Board() {
            // Ladders
            ladders.put(4, 14);
            ladders.put(9, 31);
            ladders.put(20, 38);
            ladders.put(28, 84);
            ladders.put(40, 59);

            // Snakes
            snakes.put(17, 7);
            snakes.put(62, 19);
            snakes.put(54, 34);
            snakes.put(87, 24);
            snakes.put(98, 79);
        }

        int checkPosition(int pos) {
            if (ladders.containsKey(pos)) {
                System.out.println("  Ladder! Go up to " + ladders.get(pos));
                return ladders.get(pos);
            }
            if (snakes.containsKey(pos)) {
                System.out.println("  Snake! Slide down to " + snakes.get(pos));
                return snakes.get(pos);
            }
            return pos;
        }
    }

    // Inner class: Player
    class Player {
        String name;
        int position = 0;

        Player(String name) {
            this.name = name;
        }

        boolean hasWon() {
            return position == 100;
        }
    }

    // Main gameplay
    public void startGame() {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter number of players: ");
            int n = Integer.parseInt(sc.nextLine());

            if (n <= 0) throw new Exception("Invalid number of players.");

            List<Player> players = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                System.out.print("Enter Player " + i + " name: ");
                players.add(new Player(sc.nextLine()));
            }

            Board board = new Board();
            Random random = new Random();

            System.out.println("\n--- Snake & Ladder Game Started ---");

            boolean gameOver = false;

            while (!gameOver) {
                for (Player p : players) {
                    System.out.println("\n" + p.name + " press Enter to roll dice...");
                    sc.nextLine();

                    int dice = random.nextInt(6) + 1;
                    System.out.println("  Rolled: " + dice);

                    int nextPos = p.position + dice;

                    if (nextPos <= 100) {
                        p.position = board.checkPosition(nextPos);
                    }

                    System.out.println("  Position: " + p.position);

                    if (p.hasWon()) {
                        System.out.println("\n*** " + p.name + " WINS! ***");
                        gameOver = true;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Main class entry
    public static void main(String[] args) {
        new SnakeLadderGame().startGame();
    }
}
