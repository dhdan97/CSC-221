import java.security.SecureRandom;
public class Runner2 {
	private int position;
	
	//default constructor
	public Runner2() {
		position = 1;
	}
	
	//determines runner's next move
	public void NewMove() {
		
		//create SecureRandom object
		SecureRandom random = new SecureRandom();
		
		//generate random integer from 1 to 10
		int randomMoveNumber = random.nextInt(10)+1;
		
		//determine move based on random integer;I did this instead of switch statements 1 to 10
		//walk = 40%, jump = 20%, small slip = 20%, sleep = 10%, slip = 10%
		String randomMove = new String();
		if(randomMoveNumber >=1 && randomMoveNumber <=4)
			randomMove = "walk";
		else if(randomMoveNumber >=5 && randomMoveNumber <=6)
			randomMove = "jump";
		else if(randomMoveNumber >=7 && randomMoveNumber <=8)
			randomMove = "small slip";
		else if(randomMoveNumber == 9)
			randomMove = "sleep";
		else if(randomMoveNumber == 10)
			randomMove = "slip";
		
		//walk = 1 square forward, jump = 5 squares forward, small slip = 1 square backward,
		//sleep = no movement, slip = 10 squares backwards;
		switch(randomMove) {
		case "walk":
			position += 1;
			break;
		case "jump":
			position += 5;
			break;
		case "small slip":
			if(position <= 1)//no negative positions
				position = 1;
			else position -= 1;
			break;
		case "sleep":
			break;
		case "slip":
			if(position <= 10)//no negative positions
				position = 1;
			else position -= 10;
			break;
			
		}
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {//probably don't use this outside of testing
		this.position = position;
	}
}
