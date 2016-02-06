import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of ElevatorControlSystem interface
 * TODO: Account for bad inputs, current implementation assumes no nefarious
 * activity.
 * @author Cuauhtemoc
 *
 */
public class ElevatorControlSystemImpl implements ElevatorControlSystem {

	/**
	 * List of all elevators in the system
	 */
	ArrayList<Elevator> elevators;
	
	/**
	 * Currently unassigned elevator requests
	 */
	LinkedList<ElevatorRequest> requests; 
	
	/**
	 * Total number of floors in system
	 */
	int floors;
	
	/**
	 * Universal time counter
	 */
	int time;
	
	/**
	 * Constructs a basic elevator control system, given the desired number
	 * of elevators and floors in the building.
	 * @param numElevators
	 * @param numFloors
	 */
	public ElevatorControlSystemImpl(int numElevators, int numFloors) {
		elevators = new ArrayList<Elevator>(numElevators);
		requests = new LinkedList<ElevatorRequest>();
		floors = numFloors;
		time = 0;
		
		//Initialize all elevators to a "resting position" on the first floor
		for(int i = 0; i < numElevators; i++){
			elevators.add(i, new Elevator(i+1, 1, new LinkedList<Integer>(), 
					Elevator.movementState.HALTED));
		}
		
	}
	
	/**
	 * Return a list containing the status of each elevator in the system.
	 * @return
	 */
	@Override
	public List<ElevatorStatus> status() {
		ArrayList<ElevatorStatus> result = new ArrayList<ElevatorStatus>();
		for(Elevator e : elevators){
			result.add(new ElevatorStatus(e));
		}
		
		return result;
	}

	/**
	 * Add a destination to a specified elevator
	 */
	@Override
	public void updateDestination(int id, int destination) {
		elevators.get(id - 1).getDestinations().add(destination);
	}
	
	/**
	 * Update the floor that the elevator is on.
	 */
	@Override
	public void updateFloor(int id, int currentFloor) {
		elevators.get(id - 1).setFloor(currentFloor);
	}

	/**
	 * Add a pickup request to the system. This implementation takes in 
	 * the floor the request is being made, its direction, and the goal 
	 * destination.
	 */
	@Override
	public void pickup(int floor, int direction, int goal) {
		requests.add(new ElevatorRequest(floor, direction, goal));

	}

	/**
	 * Runs one time-step in the simulation of the ECS. Processes any requests
	 * that are processable in this time-step, an updates the position/state of 
	 * all the elevators in the system.
	 */
	@Override
	public void step() {

		//Initialize list for requests that can't be processed
		LinkedList<ElevatorRequest> waiting = new LinkedList<ElevatorRequest>();
		
		//Check each request to determine if it can be processed
		for (ElevatorRequest r : requests) {

			ElevatorRequest current = requests.pop();
			Elevator temp = null;

			//Find ideal elevator to process request
			for (Elevator e : elevators) {

				if (current.getDirection() == e.getMovementState()) {

					if (e.getMovementState() == Elevator.movementState.DOWN 
							&& current.getFloor() <= e.getFloor()) {
						if (temp == null)
							temp = e;
						else if (e.getFloor() < temp.getFloor())
							temp = e;
					}

					if (e.getMovementState() == Elevator.movementState.UP 
							&& current.getFloor() >= e.getFloor()) {
						if (temp == null)
							temp = e;
						else if (e.getFloor() > temp.getFloor())
							temp = e;
					}
				}
			}
			
			//If no ideal elevator exists, try to find an empty elevator
			if(temp == null){
				for (Elevator e : elevators){
					if(e.getMovementState() == Elevator.movementState.HALTED){
						temp = e;
						if(e.getFloor() == current.getFloor()){
							e.setMovementState(current.getDirection());
						}
						if(e.getFloor() < current.getFloor()){
							e.setMovementState(Elevator.movementState.STRAIGHTUP);
						}
						if(e.getFloor() > current.getFloor()){
							e.setMovementState(Elevator.movementState.STRAIGHTDOWN);
						}
						break;
					}
				}
			}

			//Did we find an elevator?
			if (temp != null)
				temp.getPickups().add(current);
			
			//Next time!
			else waiting.add(current);
	}
		
		requests.addAll(waiting);
		
		//Update the positions of all the elevators
		updateElevators();
		time++;
	}

	/**
	 * This helper method updates the positions of all the elevators, taking 
	 * into account their current state/direction.
	 */
	private void updateElevators() {

		for (Elevator e : elevators) {

			if (e.getMovementState() != Elevator.movementState.STRAIGHTDOWN
					&& e.getMovementState() != Elevator.movementState.STRAIGHTUP) {

				// Pick up new people
				for (ElevatorRequest r : e.getPickups()) {
					if (r.getFloor() == e.getFloor()) {
						updateDestination(e.getId(), r.getGoal());
						e.getPickups().remove(r);
					}
				}

				// Drop people off
				for(int i : e.getDestinations()){
					if (i == e.getFloor()) {
						e.getDestinations().removeAll(Collections.singleton(i));
					}
				}

				/*
				 * Update floor, if there are no people in an elevator, put it
				 * in a halted state
				 */
				if (e.getDestinations().isEmpty())
					e.setMovementState(Elevator.movementState.HALTED);

				/* Update floor, move it down a floor */
				if (e.getMovementState() == Elevator.movementState.DOWN)
					if (e.getFloor() > 1)
						updateFloor(e.getId(), e.getFloor() - 1);

				/* Update floor, move it up a floor */
				if (e.getMovementState() == Elevator.movementState.UP)
					if (e.getFloor() < floors)
						updateFloor(e.getId(), e.getFloor() + 1);
			}

			// Handle case where an empty elevator is picking up a request
			else {

				/* Empty elevators "on a mission" will only have 1 request under
				this implementation*/
				ElevatorRequest r = e.getPickups().get(0);
				//Destination floor found
				if (e.getFloor() == e.getPickups().get(0).getFloor()) {
					e.setMovementState(r.getDirection());
				}

				//Keep moving towards destination floor
				else if (e.getMovementState() == Elevator.movementState.STRAIGHTDOWN)
					if (e.getFloor() > 1)
						updateFloor(e.getId(), e.getFloor() - 1);

					if (e.getMovementState() == Elevator.movementState.STRAIGHTUP)
						if (e.getFloor() < floors)
							updateFloor(e.getId(), e.getFloor() + 1);
			}

		}
		
	}


}
