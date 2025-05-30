
public enum Directions {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);
    
    public final int dx;
    public final int dy;

    Directions(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
