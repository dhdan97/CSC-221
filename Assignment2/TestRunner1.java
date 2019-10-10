
public class TestRunner1 {
	public static void main(String[] args) {
		Runner1 Daniel = new Runner1();
		
		for(int i = 0; i < 50; i++) {
			Daniel.setPosition(1);
			Daniel.NewMove();
			System.out.println(Daniel.getPosition());
		}
	}

}
