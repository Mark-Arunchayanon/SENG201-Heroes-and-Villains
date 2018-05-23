import java.util.Random;

/**
 * Defines a PowerUp object that provides the team with a map
 * @author fer25
 * @author par116
 */
public class Map extends PowerUp {
	
	//Describes the standard price
	private static final int BASE_PRICE = 110;
	//Describes the random variance between calculated price and
	//price charged
	private static final int PRICE_VARIATION = 15;
	
	@Override
	public void setRandom(Random r) {
		
		price = r.nextInt(PRICE_VARIATION * 2) + BASE_PRICE - PRICE_VARIATION;
		
	}

	@Override
	public void applyBonus(Team team, Hero hero) {

		team.setPUMap();

	}

	@Override
	public String getApplicationDescriptor() {
		return "This Map Power Up will provide the Team a map of this city";
	}

	@Override
	public String getTitle() {
		return "Map";
	}

	@Override
	public String getSaleDescriptor() {
		
		String s = "A Map\nProvides the Team a map of this City"
				+ "\nPrice: $" + temp_price; 
		
		return s;
	}

}
