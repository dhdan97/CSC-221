import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Painting {
	//A POJO class containing a painting's information and basic operations
	
	//private member variables
	private String artistName;
	private String name;
	private Double price;
	private int year;
	
	//default constructor
	public Painting() {
		this.artistName = "-";
		this.name = "-";
		this.price = 0.0;
		this.year = 0;
	}
	
	//non-default constructor
	public Painting(String artistName, String name, Double price, int year) {
		this.artistName = artistName;
		this.name = name;
		this.price = price;
		this.year = year;
	}
	
	//get methods
	public String getArtistName() {
		return artistName;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public int getYear() {
		return year;
	}
	
	//set methods
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	//returns price discounted by 15%
	public Double getMinimumDiscountPrice() {
		return 	(this.price - (this.price * .15));
	}
	
	//returns price discounted by 10%
	public Double getMaximumDiscountPrice() {
		return (this.price - (this.price * .10));
	}
	
	//returns the age of the painting
	public int getAge() {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int currYear = localDate.getYear();
		
		return (currYear - this.year);
		
	}
	
	
	

}
