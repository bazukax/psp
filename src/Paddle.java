import java.awt.*;
import java.awt.event.*;


class Paddle extends Rectangle {

    private int id;
    private int yVelocity;
    private int speed = 15;
    private boolean aiBot;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id, boolean bot) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
        this.aiBot = bot;
    }

    void keyPresed(KeyEvent e) {
        if (aiBot) return;
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                    move(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                    move(0);
                }
                break;

        }
    }

    void keyReleased(KeyEvent e) {
        if (aiBot) return;
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move(0);
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move(0);
                }
                break;

        }
    }

    private void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    void move(int ballPosY) {
        if (aiBot) {
            int n = ballPosY - y;
            if (Math.abs(n) < 20) {
                return;
            }

            if (ballPosY > y) {
                setYDirection(speed);
            }
            if (ballPosY < y) {
                setYDirection(-speed);
            }

        }
        y = y + yVelocity;

    }

    void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);
    }
}
