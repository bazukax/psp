
import java.awt.*;
import java.util.*;

class Ball extends Rectangle {
    private Random random;
    int xVelocity;
    int yVelocity;
    private int initialSpeed = 2;
    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;

        }
        setXDirection(randomXDirection * initialSpeed);
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;

        }
        setYDirection(randomYDirection* initialSpeed);
    }


    void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    void move() {
        x += xVelocity;
        y += yVelocity;
    }

    void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, height, width);
    }
}
