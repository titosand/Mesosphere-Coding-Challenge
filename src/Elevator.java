import java.util.LinkedList;
import java.util.List;

/**
 * This is a basic elevator class to be used by the Elevator Control System.
 * @author Cuauhtemoc
 */
public class Elevator {

	/**
	 * The various states that an elevator can be in
	 * TODO: add some kind of emergency/maintenance mode
	 */
	enum movementState {UP, DOWN, HALTED, STRAIGHTUP, STRAIGHTDOWN}
	
	/**
	 * ID of the elevator
	 */
	int id;
	
	/**
	 * Current floor of the elevator
	 */
	int floor;
	
	/**
	 * Pending pickup requests assigned to elevator
	 * (aka "Places where the elevator is on it's way to pick someone up")
	 */
	List<ElevatorRequest> pendingPickups;
	
	/**
	 * Pending dropoff requests assigned to elevator
	 * (aka "Places where the elevator is on it's way to drop someone off")
	 */
	List<Integer> destinations;
	
	/**
	 * Current state of the elevator
	 */
	movementState state;
	
	/**
	 * Creates a basic Elevator object
	 * @param id
	 * @param floor
	 * @param destinations
	 * @param state
	 */
	public Elevator(int id, int floor, List<Integer> destinations, 
			movementState state){
		this.id = id;
		this.floor = floor;
		this.destinations = destinations;
		this.state = state;
		this.pendingPickups = new LinkedList<ElevatorRequest>();
	}

	/**
	 * Return the ID of the elevator
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Return the floor of the elevator
	 * @return
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * Sets the floor of the elevator
	 * @param floor
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	/**
	 * Return all the pending destinations of the elevator
	 * @return
	 */
	public List<Integer> getDestinations() {
		return destinations;
	}
	
	/**
	 * Return all pending pickups of the elevator
	 * @return
	 */
	public List<ElevatorRequest> getPickups() {
		return pendingPickups;
	}
	
	/**
	 * Return the current state of the elevator
	 * @return
	 */
	public movementState getMovementState() {
		return state;
	}
	
	/**
	 * Set the current state of the elevator
	 * @param state
	 */
	public void setMovementState(movementState state) {
		this.state = state;
	}



}
