
/**
 * This is a basic class to represent pickup requests sent to the Elevator
 * Control System.
 * @author Cuauhtemoc
 */
public class ElevatorRequest {

	/**
	 * ID of the elevator
	 */
	
	
	int floor;
	
	/**
	 * The final destination of the request(i.e. "Where is the person headed?")
	 */
	int goal;
	
	/**
	 * The direction that the request is in
	 */
	Elevator.movementState state;


	/**
	 * Creates a basic pickup request, given the starting floor, destination 
	 * floor, and the direction of the request.
	 * @param floor
	 * @param direction
	 * @param goal
	 */
	ElevatorRequest(int floor, int direction, int goal) {
		this.floor = floor;
		this.goal = goal;
		if (direction < 0)
			state = Elevator.movementState.DOWN;
		else if (direction > 0)
			state = Elevator.movementState.UP;
	}

	/**
	 * Return the floor in which the request originated from
	 * @return
	 */
	int getFloor() {
		return floor;
	}

	/**
	 * Return the destination floor of the request
	 * @return
	 */
	int getGoal() {
		return goal;
	}

	/**
	 * Return the direction of the request, relative to where it starts
	 * @return
	 */
	Elevator.movementState getDirection() {
		return state;
	}

}
