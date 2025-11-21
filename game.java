import java.util.*;

// ---------------- BOARD CLASS ----------------
class Board {
    private final Map<Integer, Integer> snakes = new HashMap<>();
    private final Map<Integer, Integer> ladders = new HashMap<>();

    public Board() {
        // Ladders
        ladders.put(3, 22);
        ladders.put(5, 8);
        ladders.put(11, 26);
        ladders.put(20, 29);

        // Snakes
        snakes.put(27, 1);
        snakes.put(21, 9);
        snakes.put(17, 4);
        snakes.put(19, 7);
    }

    public int checkPosition(int position) {
        if (ladders.containsKey(position)) {
            System.out.println("  Ladder! Climb to " + ladders.get(position));
            return ladders.get(position);
        }
        if (snakes.containsKey(position)) {
            System.out.println("  Snake! Go down to " + snakes.get(position));
            return snakes.get(position);
        }
        return position;
    }
}

// ---------------- PLAYER CLASS ----------------
class Player {
    private final String name;
    private int position = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public int getPosition() { return position; }

    public void move(int steps) {
        position += steps;
        if (position > 30) position = 30;   // Finish point
    }

    public void setPosition(int pos) {
        position = pos;
    }
}

// ---------------- GAME CLASS ----------------
class SnakeLadderGame {
    private final List<Player> players;
    private final Board board;
    private final Random random = new Random();

    public SnakeLadderGame(List<Player> players) {
        this.players = players;
        this.board = new Board();
    }

    public void start() {
        System.out.println("======== SNAKE & LADDER GAME STARTED ========");

        boolean gameOver = false;

        while (!gameOver) {
            for (Player player : players) {
                System.out.println("\n" + player.getName() + "'s turn");
                System.out.println("Press ENTER to roll the dice...");
                new Scanner(System.in).nextLine();

                int dice = random.nextInt(6) + 1;
                System.out.println("Dice Rolled: " + dice);

                player.move(dice);
                System.out.println("  Moved to: " + player.getPosition());

                int updatedPosition = board.checkPosition(player.getPosition());
                player.setPosition(updatedPosition);

                System.out.println("  Final Position: " + player.getPosition());

                if (player.getPosition() == 30) {
                    System.out.println("\n******** " + player.getName() + " WINS THE GAME! ********");
                    gameOver = true;
                    break;
                }
            }
        }

        System.out.println("=============================================");
    }
}

// ---------------- MAIN CLASS ----------------
public class MultiPlayerSnakeLadder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        try {
            System.out.print("Enter number of players: ");
            int count = sc.nextInt();
            sc.nextLine(); // clear buffer

            if (count < 2)
                throw new Exception("At least 2 players required!");

            for (int i = 1; i <= count; i++) {
                System.out.print("Enter name for Player " + i + ": ");
                String name = sc.nextLine();
                players.add(new Player(name));
            }

            SnakeLadderGame game = new SnakeLadderGame(players);
            game.start();

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter numbers only.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Game Ended.");
        }
    }
}
