import java.util.ArrayList;
import java.util.List;

public class Snake {

    private Integer length;
    private List<Integer> snakeXLength;
    private List<Integer> snakeYLength;
    private Direction direction;

    public Snake() {
        length = 0;
        snakeXLength = new ArrayList<Integer>();
        snakeXLength.add(50);
        snakeYLength = new ArrayList<Integer>();
        snakeYLength.add(100);
        direction = Direction.RIGHT;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<Integer> getSnakeXLength() {
        return snakeXLength;
    }


    public List<Integer> getSnakeYLength() {
        return snakeYLength;
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
