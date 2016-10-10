package lessons.catchthedrop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow extends JFrame {

    private static GameWindow gameWindow;
    private static Image background, drop, gameOver;
    private static long lastFrameTime;
    private static float dropLeft = 200;
    private static float dropTop = -100;
    private static float dropSpeed = 200;

    public static void main(String[] args) throws IOException {
        background = ImageIO.read(GameWindow.class.getResourceAsStream("img/background.png"));
        drop = ImageIO.read(GameWindow.class.getResourceAsStream("img/drop.png"));
        gameOver = ImageIO.read(GameWindow.class.getResourceAsStream("img/gameOver.png"));
        gameWindow = new GameWindow();
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200,100);
        gameWindow.setSize(906, 478);
        gameWindow.setResizable(false);
        lastFrameTime = System.nanoTime();
        GameField gameField = new GameField();
        gameWindow.add(gameField);
        gameWindow.setVisible(true);
    }


    private static void onRepaint (Graphics g ){
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime)*0.000000001f;

        lastFrameTime = currentTime;
        dropTop = dropTop+dropSpeed*deltaTime;

        g.drawImage(background, 0,0,null);

        g.drawImage(drop, (int)dropLeft,(int)dropTop,null);
       // g.drawImage(gameOver, 280,120,null);
    }
    private static class GameField extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            dropTop++;
            dropLeft+=4;
            onRepaint(g);
            repaint();
        }
    }
}
