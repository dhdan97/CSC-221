
public class Race {
	
	//main file to use Race Class
	public static void main(String[] args) {
		Race race1 = new Race();
		
		race1.startRace();
	}
	
	
	//race class contains runner classes
	Runner1 runner1;
	Runner2 runner2;
	static int time;
	
	//default constructor
	public Race() {
		runner1 = new Runner1();
		runner2 = new Runner2();
		time = 0;
	}
	
	//overloading toString() method
	public String toString(String race) {
		return "Race simulation class";
	}
	
	//method that begins the race and prints who won
	public void startRace() {
		System.out.println("On Your Mark, Get Set, Go");
		
		//show start of race
		printRace();
		time++;
		
		//while either runner hasn't reached/passed the finish line yet
		while(runner1.getPosition() < 100 && runner2.getPosition() < 100) {
			runner1.NewMove();
			runner2.NewMove();
			printRace();
			time++;
		}
		
		//checks for tie, else print who won
		if((runner1.getPosition() >= 100 && runner2.getPosition() >=100)) {
			System.out.printf("%s%n%s", "IT'S A TIE","Time Elapsed = " + time + " seconds");
		}
		else if(runner1.getPosition() >= 100 && runner2.getPosition() < 100)
			System.out.printf("%s%n%s", "Runner1 wins","Time Elapsed = " + time + " seconds");
		else System.out.printf("%s%n%s", "Runner2 wins","Time Elapsed = " + time + " seconds");
	}

	public void printRace() {
		
		System.out.printf("%s%s%n", "Time: ", time);
		
		//if runners are at the same position, prints B(for "Both") on that position
		//takes the runner that is winning, prints R1 based on that runner's position,
		//then prints the second runner's position based of the difference in position's
		if(runner1.getPosition() == runner2.getPosition()) {
			if(runner1.getPosition() <= 100) {
			System.out.printf("%" + runner1.getPosition() +"s%n", "B");
			}
		}
		else {
			if(runner1.getPosition() > runner2.getPosition()) {
				if(runner2.getPosition() <= 100)//don't print if out of bounds
					System.out.printf("%" + runner2.getPosition() + "s", "R2");
				if(runner1.getPosition() <= 100)//don't print if out of bounds
					System.out.printf("%" + (runner1.getPosition() - runner2.getPosition()) + "s","R1");
				System.out.println();
			}
			else {
				if(runner1.getPosition() <= 100)//don't print if out of bounds
					System.out.printf("%" + runner1.getPosition() + "s","R1");
				if(runner2.getPosition() <= 100)//don't print if out of bounds
					System.out.printf("%" + (runner2.getPosition() - runner1.getPosition()) + "s","R2");
				System.out.println();
				
			}
		}
		
		//prints "track"
		for(int i = 0; i < 100; i++)
			System.out.print("-");
		System.out.println();
	}
}
