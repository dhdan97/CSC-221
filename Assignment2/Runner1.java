import java.security.SecureRandom;
public class Runner1 {
	private int position;
	
	//default constructor
	public Runner1() {
		position = 1;
	}

	//Determines runner's next move
	public void NewMove() {
		
		//create SecureRandom object
		SecureRandom random = new SecureRandom();
		
		//generate random integer from 1 to 10
		int randomMoveNumber = random.nextInt(10)+1;
		
		//determine move based on random integer;I did this instead of switch statements 1 to 10
		//jump = 50%, slip = 30%, walk = 20%
		String randomMove = new String();
		if(randomMoveNumber >=1 && randomMoveNumber <=5)
			randomMove = "jump";
		else if(randomMoveNumber >=6 && randomMoveNumber <=8)
			randomMove = "slip";
		else if(randomMoveNumber >=9 && randomMoveNumber <=10)
			randomMove = "walk";
		
		//jump = 3 squares forward, slip = 6 squares backward, walk = 1 square forward
		switch(randomMove) {
		case "jump":
			position += 3;
			break;
		case "slip":
			if(position <=6)//no negative positions
				position = 1;
			else position -= 6;
			break;
		case "walk":
			position += 1;
		}
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {//probably don't use this outside of testing
		this.position = position;
	}
}
