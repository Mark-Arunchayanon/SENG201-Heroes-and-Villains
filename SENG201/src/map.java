import java.util.Random;

/**
 * Defines a PowerUp object that provides the team with a map
 * @author fer25
 *
 */
public class map implements Saleable, PowerUp {
	
	//Describes the standard price
	private static final int BASE_PRICE = 110;
	//Describes the random variance between calculated price and
	//price charged
	private static final int PRICE_VARIATION = 15;
	
	private int price;
	
	public map() {
		
		Random r = new Random();
		
		price = r.nextInt(PRICE_VARIATION * 2) + price - PRICE_VARIATION;
		
	}

	@Override
	public void applyBonus(Team team, Hero hero) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSaleDescriptor() {
		
		String s = "A Map\nProvides the Team a map of this City"
				+ "\nPrice: $" + price; 
		
		return s;
		
	}

	@Override
	public int getPrice() {
		return price;
	}

}