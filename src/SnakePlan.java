
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

public class SnakePlan {

    private Point apple;
    private LinkedList<Point> snake = new LinkedList();
    private Directions direction;
    private int width;
    private int height;

    public SnakePlan(int width, int height) {
        if (width < 10 || height < 10) {
            throw new RuntimeException("Plan must be at least 10x10.");
        }
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isOutOfBounds(Point p){
        return p.x < 0 || p.x >= width || p.y < 0 || p.y >= height;
    }
    
    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        Point head = (Point) snake.getLast().clone();
        head.x += direction.dx;
        head.y += direction.dy;
        Point neck = null;
        if (snake.size() > 1) {
            neck = snake.get(snake.size() - 2);
        }
        if (head.equals(neck)) {
            return;
        }
        this.direction = direction;
    }

    public void setApple(Point apple) {
        this.apple = apple;
    }

    public Point getApple() {
        return apple;
    }

    public void newApple() {
        Random r = new Random();
        do {
            apple.x = r.nextInt(width);
            apple.y = r.nextInt(height);
        } while (isSnakeAt(apple));
    }

    public boolean isSnakeAt(Point p) {
        for (Point point : snake) {
            if (point.equals(p)) {
                return true;
            }
        }
        return false;
    }

    public void setSnake(LinkedList<Point> snake) {
        this.snake = snake;
    }

    public void removeTail() {
        snake.poll();
    }

    public void addHead(Point head) {
        snake.add(head);
    }

    public Point getHead() {
        return snake.getLast();
    }
    public Point getTail(){
        return snake.getFirst();
    }

    public LinkedList<Point> getSnake() {
        return snake;
    }

}
