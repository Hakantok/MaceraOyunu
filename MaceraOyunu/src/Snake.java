import java.util.Random;

public class Snake extends Obstacle {
	
	public Snake() {
		super("Yýlan", 4, 3+(int)(Math.random()*4), 12, 0);
	}	
}
