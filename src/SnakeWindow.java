
import java.awt.event.KeyEvent;

public class SnakeWindow extends javax.swing.JFrame {

    public SnakeWindow() {
        initComponents();
        snakeWidget1.setFocusable(true);
        snakeWidget1.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        snakeWidget1 = new SnakeWidget();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        snakeWidget1.setMinimumSize(new java.awt.Dimension(50, 50));
        snakeWidget1.setPreferredSize(new java.awt.Dimension(800, 800));
        snakeWidget1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                snakeWidget1KeyPressed(evt);
            }
        });
        getContentPane().add(snakeWidget1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void snakeWidget1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_snakeWidget1KeyPressed
        SnakeGame game = snakeWidget1.getGame();
        SnakePlan plan = game.getPlan();
        if (!game.running) {
            snakeWidget1.gameOver();
            return;
        }
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                plan.setDirection(Directions.UP);
            }
            case KeyEvent.VK_DOWN -> {
                plan.setDirection(Directions.DOWN);
            }
            case KeyEvent.VK_LEFT -> {
                plan.setDirection(Directions.LEFT);
            }
            case KeyEvent.VK_RIGHT -> {
                plan.setDirection(Directions.RIGHT);
            }
            case KeyEvent.VK_W -> {
                plan.setDirection(Directions.UP);
            }
            case KeyEvent.VK_S -> {
                plan.setDirection(Directions.DOWN);
            }
            case KeyEvent.VK_A -> {
                plan.setDirection(Directions.LEFT);
            }
            case KeyEvent.VK_D -> {
                plan.setDirection(Directions.RIGHT);
            }
        }
    }//GEN-LAST:event_snakeWidget1KeyPressed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SnakeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SnakeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SnakeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SnakeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SnakeWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private SnakeWidget snakeWidget1;
    // End of variables declaration//GEN-END:variables
}
