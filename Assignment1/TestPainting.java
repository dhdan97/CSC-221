import java.util.Scanner;

public class TestPainting {
	public static void main(String[] args) {
		
		//instance of Painting using the default constructor
		Painting defaultPainting = new Painting();
		
		defaultPainting.setArtistName("Mark Rothko");
		defaultPainting.setName("No. 6 (Violet, Green and Red)");
		defaultPainting.setPrice(186_000_000.0);
		defaultPainting.setYear(1951);
		
		//Using the Scanner class to prompt the user to enter values for painting
		Scanner input = new Scanner(System.in);
		
		System.out.printf("%s","Artist Name: ");
		String newArtistName = input.nextLine();
		
		System.out.printf("%s","Name: ");
		String newName = input.nextLine();
		
		System.out.printf("%s","Price: ");
		Double newPrice = input.nextDouble();
		
		System.out.printf("%s","Year: ");
		int newYear = input.nextInt();
		
		input.close();
		
		//instance of Painting using the non-default constructor
		Painting nonDefaultPainting = new Painting(newArtistName, newName, newPrice, newYear);
		
		//formatting and printing second instance
		System.out.printf(
				  "%24s%s%n"
				+ "%24s%s%n"
				+ "%24s%,.2f%n"
				+ "%24s%d%n"
				+ "%24s%d%n"
				+ "%24s%,.2f"
				+ "%s"
				+ "%,.2f%n", 
				"Artist Name: ", nonDefaultPainting.getArtistName(),
				"Name: ", nonDefaultPainting.getName(),
				"Price: ", nonDefaultPainting.getPrice(), 
				"Year: ", nonDefaultPainting.getYear(),
				"Age: ", nonDefaultPainting.getAge(),
				"Discounted Price Range: ", nonDefaultPainting.getMinimumDiscountPrice(), " - ",
				nonDefaultPainting.getMaximumDiscountPrice());
	}

}
