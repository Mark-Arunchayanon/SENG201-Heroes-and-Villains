import java.util.Random;

/**
 * Defines a PowerUp object that provides the team with a map
 * @author fer25
 *
 */
public class Map implements Saleable, PowerUp {
	
	//Describes the standard price
	private static final int BASE_PRICE = 110;
	//Describes the random variance between calculated price and
	//price charged
	private static final int PRICE_VARIATION = 15;
	
	private Random r = new Random();
	
	private int price;
	private int temp_price;
	
	public Map() {
		
		price = r.nextInt(PRICE_VARIATION * 2) + BASE_PRICE - PRICE_VARIATION;
		
	}

	@Override
	public void applyBonus(Team team, Hero hero) {

		team.setPUMap();

	}

	@Override
	public String getSaleDescriptor(int haggling) {
		
		temp_price = (int) Math.round(price * 100 / haggling);
		
		String s = "A Map\nProvides the Team a map of this City"
				+ "\nPrice: $" + temp_price; 
		
		return s;
		
	}

	@Override
	public int getPrice() {
		return temp_price;
	}

	@Override
	public String getApplicationDescriptor() {
		return "This Map Power Up will provide the Team a map of this city";
	}

}
