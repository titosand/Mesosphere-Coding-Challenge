import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Basic testing framework for the Elevator Control System. Original plan 
 * was to test thoroughly and do real observed/expected comparisons, 
 * but do to time constraints I am just printing the status of the elevators under
 * various conditions and checking it by eye.
 * 
 * TODO: Make more thorough tests, implement actual comparisons between
 * observed/expected values using Assert.
 * @author Cuauhtemoc
 *
 */
public class ElevatorControlSystemImplTest {

	ElevatorControlSystemImpl test;
	
	
	/**
	 * This test files one request from the first floor to the fourth.
	 */
	@Test
	public void firstToFourth() {
		System.out.println("First to Fourth Test:");
		test = new ElevatorControlSystemImpl(4, 10);
		test.pickup(1, 1, 4);
		printStatus(test);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
	}
	
	/**
	 * This test calls a request starting from the 3rd floor to the 1st floor.
	 */
	@Test
	public void fourthtoFirst() {
		System.out.println("Fourth to First Test:");
		test = new ElevatorControlSystemImpl(4, 10);
		test.pickup(4, -1, 1);
		printStatus(test);
		for(int i = 0; i < 8; i++){
		test.step();
		printStatus(test);
		}
	}
	
	/**
	 * This test calls a request to the top floor, then picks someone else up 
	 * along the way who is also headed up.
	 */
	@Test
	public void pickupOnTheWayUp() {
		System.out.println("Pickup On The Way Up Test:");
		test = new ElevatorControlSystemImpl(4, 10);
		test.pickup(1, 1, 4);
		printStatus(test);
		test.step();
		test.pickup(2, 1, 3);
		printStatus(test);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
		}
	
	/**
	 * This test calls a request to the bottom floor, then picks someone else up 
	 * along the way who is also headed down.
	 */
	@Test
	public void pickupOnTheWayDown() {
		System.out.println("Pickup On The Way Down Test:");
		test = new ElevatorControlSystemImpl(4, 10);
		test.pickup(4, -1, 1);
		printStatus(test);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
		test.pickup(3, -1, 2);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
		test.step();
		printStatus(test);
		}
	
	/**
	 * Helper method to print out the status of an ECS
	 * @param ecs
	 */
	private void printStatus(ElevatorControlSystem ecs){
	System.out.println("------------------");
	for(ElevatorStatus s : ecs.status()){
		System.out.println("Id: " + s.id + " Floor: " + s.floor 
				+ " Direction: " + s.state.toString());
		
		for(int i : s.destinations)
			System.out.println("Elevator "+ s.id + " is headed to floor " + i);
	}
}
	
	
}
