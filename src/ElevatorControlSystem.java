import java.util.List;

/**
 * A basic interface for an elevator control system, based on the original 
 * interface specified in the coding challenge. Some modifications made as 
 * as deemed necessary.
 * @author Cuauhtemoc
 */
public interface ElevatorControlSystem {

	public List<ElevatorStatus> status();
	
	public void updateDestination(int id, int destination);

	public void updateFloor(int id, int currentFloor);
	
	public void pickup(int floor, int direction, int goal);
	
	public void step();
	
	
}
