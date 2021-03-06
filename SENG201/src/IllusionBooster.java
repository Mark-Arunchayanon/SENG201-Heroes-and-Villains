import java.util.Random;

/**
 * Defines a PowerUp object that increases a Hero's illusion stat
 * @author fer25
 *
 */
public class IllusionBooster implements Saleable, PowerUp {
	
	//Define the minimum and maximum illusion boost the power up will provide
	private static final int MIN_BOOST = 10;
	private static final int MAX_BOOST = 30;
	//Describes the multiplier to calculate price from performance
	private static final int PRICE_COEFFICIENT = 3;
	//Describes the random variance between calculated price and
	//price charged
	private static final int PRICE_VARIATION = 15;
	
	//Pre calulation of constants
	private static final int BOOST_COEFFICIENT = MAX_BOOST - MIN_BOOST;
	
	private Random r = new Random();
	
	private int price;
	private static int boost;
	private int temp_price;
	
	public IllusionBooster() {
		
		boost = r.nextInt(BOOST_COEFFICIENT) + MIN_BOOST;
		
		price = PRICE_COEFFICIENT * boost;
		
		price += r.nextInt(PRICE_VARIATION * 2) + price - PRICE_VARIATION;
		
	}

	@Override
	public void applyBonus(Team team, Hero hero) {
		
		hero.adjustPUIllusion(boost);
		
	}

	@Override
	public String getSaleDescriptor(int haggling) {
		
		temp_price = (int) Math.round(price * 100 / haggling);
		
		String s = "Illusion Booster\nA Power-Up that increases a Hero's Illusion Stat\n"
				+ "Boost: " + boost + "\nPrice: $" + temp_price; 
		
		return s;
		
	}

	@Override
	public int getPrice() {
		return temp_price;
	}

	@Override
	public String getApplicationDescriptor() {
		String s = "This Illusion Booster Power Up will add "
				+ boost + " Illusion Points to the Hero it is applied to.\n"
						+ "This will increace their chance of tricking a Villain in a game while you are in this City";
		return s;
	}

}
