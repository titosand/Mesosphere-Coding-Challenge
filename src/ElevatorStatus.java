import java.util.List;

/**
 * This is a basic class to represent the status of an elevator in the Elevator
 * Control System.
 * @author Cuauhtemoc
 */
public class ElevatorStatus {

	/**
	 * The ID of the elevator
	 */
	int id;
	
	/**
	 * The current floor of the elevator 
	 */
	int floor;
	

	/**
	 * The current destinations of the elevator 
	 */
	List<Integer> destinations;
	

	/**
	 * The current state of the elevator 
	 */
	Elevator.movementState state;


	/**
	 * Creates an ElevatorStatus object for a given Elevator.
	 * @param e
	 */
	public ElevatorStatus(Elevator e) {
		id = e.getId();
		floor = e.getFloor();
		destinations = e.getDestinations();
		state = e.getMovementState();
	}
	

}
