
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SnakeWidget extends JComponent implements GraphicsCallback {

    private static final Color BG = new Color(60, 60, 60);
    private static final Color GRID = Color.BLACK;
    private static final Color SNAKE = Color.GREEN;
    private static final Color APPLE = Color.RED;

    private int currentWidth = 20;
    private int currentHeight = 20;
    private int speed = 200;

    private SnakeGame game;
    private int xOffset;

    private int s;

    public SnakeWidget() {
        game = new SnakeGame(this, currentWidth, currentHeight, speed);
    }

    public SnakeGame getGame() {
        return game;
    }

    public void setGame(SnakeGame game) {
        this.game = game;
    }

    private void getScaling() {
        SnakePlan plan = game.getPlan();
        int width = this.getWidth() / plan.getWidth();
        int height = this.getHeight() / plan.getHeight();
        if (width > height) {
            xOffset = (this.getWidth() - height * plan.getWidth()) / 2;
        } else {
            xOffset = 0;
        }
        s = Math.min(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BG);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        getScaling();
        int width = game.getPlan().getWidth();
        int height = game.getPlan().getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                g.setColor(GRID);
                g.drawRect(x * s + xOffset, y * s, s, s);
            }
        }
        for (Point node : game.getPlan().getSnake()) {
            g.setColor(SNAKE);
            g.fillRect(node.x * s + xOffset, node.y * s, s, s);
        }
        Point apple = game.getPlan().getApple();
        g.setColor(APPLE);
        g.fillRect(apple.x * s + xOffset, apple.y * s, s, s);
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void gameOver() {
        Window owner = SwingUtilities.getWindowAncestor(this);
        JDialog popup = new JDialog(owner, "Set dimensions:");
        popup.setLayout(new GridBagLayout());
        popup.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints gbc = new GridBagConstraints();

        JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(currentWidth, 10, 1000, 1));
        JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(currentHeight, 10, 1000, 1));
        JSpinner spinner3 = new JSpinner(new SpinnerNumberModel((int) game.timeToMove, 1, 1000, 1));

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        popup.add(new JLabel("Width:"), gbc);
        gbc.gridx = 1;
        popup.add(spinner1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        popup.add(new JLabel("Height:"), gbc);
        gbc.gridx = 1;
        popup.add(spinner2, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        popup.add(new JLabel("Speed:"), gbc);
        gbc.gridx = 1;
        popup.add(spinner3, gbc);

        JButton submitButton = new JButton("Start Game");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        popup.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentWidth = (Integer) spinner1.getValue();
                currentHeight = (Integer) spinner2.getValue();
                speed = (Integer) spinner3.getValue();
                game = new SnakeGame(SnakeWidget.this, currentWidth, currentHeight, speed);
                SnakeWidget.this.repaint();
                getScaling();
                popup.dispose();
            }
        });

        popup.getRootPane().setDefaultButton(submitButton);
        SwingUtilities.invokeLater(() -> submitButton.requestFocusInWindow());

        popup.pack();
        popup.setLocationRelativeTo(this);
        popup.setVisible(true);

    }
}
