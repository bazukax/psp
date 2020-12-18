import org.junit.Assert;
import org.junit.jupiter.api.Test;

class BallTest {

    @Test
    void setYDirection() {
        Ball ball = new Ball(10,10,10,10);
        ball.setYDirection(1);
        Assert.assertEquals(ball.yVelocity,1);
    }

    @Test
    void setXDirection() {
        Ball ball = new Ball(10,10,10,10);
        ball.setXDirection(1);
        Assert.assertEquals(ball.xVelocity,1);
    }

    @Test
    void moveX() {
        Ball ball = new Ball(10,10,10,10);
        ball.xVelocity = 1;
        ball.move();
        Assert.assertEquals(ball.x,11);
    }
    @Test
    void moveY() {
        Ball ball = new Ball(10,10,10,10);
        ball.yVelocity = 1;
        ball.move();
        Assert.assertEquals(ball.y,11);
    }
}