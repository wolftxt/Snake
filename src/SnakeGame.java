
import java.awt.Point;
import java.util.LinkedList;

public class SnakeGame {

    public long timeToMove;

    private SnakePlan plan;
    GraphicsCallback callback;

    public boolean running;

    public SnakeGame(GraphicsCallback callback) {
        this(callback, 20, 20, 200);
    }

    public SnakeGame(GraphicsCallback callback, int width, int height, long speed) {
        plan = new SnakePlan(width, height);
        this.callback = callback;
        timeToMove = speed;
        initializeGame();
        gameCycle();
    }

    private void initializeGame() {
        plan.setSnake(new LinkedList());
        plan.addHead(new Point(plan.getWidth() / 2, plan.getHeight() / 2));
        plan.setApple(new Point());
        plan.newApple();
    }

    private void gameCycle() {
        running = true;
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (running) {
                    try {
                        Thread.sleep(timeToMove);
                        if (moveSnake()) {
                            running = false;
                        }
                        callback.update();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                callback.gameOver();
            }
        };
        thread.start();
    }

    /**
     * Moves the snake and generates a new apple if eaten.
     *
     * @return true if you lost the game
     */
    private boolean moveSnake() {
        Point head = (Point) plan.getHead().clone();
        Directions d = plan.getDirection();
        if (d == null) {
            return false;
        }
        head.x += d.dx;
        head.y += d.dy;
        if (plan.isSnakeAt(head) || plan.isOutOfBounds(head)) {
            return true;
        }
        plan.addHead(head);
        if (head.equals(plan.getApple())) {
            plan.newApple();
        } else {
            plan.removeTail();
        }
        return false;
    }

    public SnakePlan getPlan() {
        return plan;
    }
}
