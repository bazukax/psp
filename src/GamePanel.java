import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    private static final int GAME_WIDTH = 1000;
    private static final int GAME_HEIGHT = (int) (GAME_WIDTH * 0.5555);
    private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    private static int BALL_DIAMETER = 20;
    private static int PADDLE_WIDTH = 25;
    private static int PADDLE_HEIGHT = 100;
    private boolean aiBot;

    private Thread gameThread;
    private Image image;
    private Graphics graphics;
    private Random random;
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Score score;

    GamePanel(Boolean bot) {
        this.aiBot = bot;
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }

    private void newBall() {
        random = new Random();

        ball = new Ball(GAME_WIDTH / 2 - BALL_DIAMETER / 2, random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    private void newPaddles() {

       paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1, aiBot);
       paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2, false);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    private void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);

        score.draw(g);
    }
    private void RandomShapes()
    {
        random = new Random();
        int randomHeight = random.nextInt(175) + 25;
        int randomDiameter = random.nextInt(30) + 10;
        BALL_DIAMETER = randomDiameter;
        PADDLE_HEIGHT = randomHeight;

        ball.reshape(GAME_WIDTH / 2 - BALL_DIAMETER / 2, random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
        paddle1.reshape(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle2.reshape(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);

    }

    private void move() {
        if (aiBot) {
            paddle1.move(ball.y);
            paddle2.move(0);
            ball.move();
        } else {
            paddle1.move(0);
            paddle2.move(0);
            ball.move();
        }
    }

    private void changeVelocity() {

        ball.xVelocity = Math.abs(ball.xVelocity);
        ball.xVelocity++;
        if (ball.yVelocity > 0) {
            ball.yVelocity++;
        } else {
            ball.yVelocity--;
        }

    }

    private void checkPaddleCollision() {
        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }
    }

    private void checkBallBoundsCollision() {
        if (ball.y <= 0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
    }

    private void addScore() {
        if (ball.x <= 0) {
            score.player2++;
            newPaddles();
            newBall();
            RandomShapes();
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            RandomShapes();
        }
    }
    private void checkBallCollisionWithPaddles()
    {
        //Ball bounces of paddles
        if (ball.intersects(paddle1)) {
            changeVelocity();
            ball.setXDirection((ball.xVelocity));
            ball.setYDirection((ball.yVelocity));
        }
        if (ball.intersects(paddle2)) {
            changeVelocity();
            ball.setXDirection((-ball.xVelocity));
            ball.setYDirection((ball.yVelocity));
        }
    }
    private void checkCollision() {
        checkBallCollisionWithPaddles();
        checkBallBoundsCollision();
        checkPaddleCollision();
        addScore();
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            paddle1.keyPresed(e);
            paddle2.keyPresed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
