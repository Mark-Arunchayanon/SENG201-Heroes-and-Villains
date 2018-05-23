import java.util.Random;

/**
 * Defines a PowerUp object that increases a Hero's haggling stat
 * @author fer25
 * @author par116
 */
public class HagglingBooster extends PowerUp {
	
	//Define the minimum and maximum illusion boost the power up will provide
	private static final int MIN_BOOST = 10;
	private static final int MAX_BOOST = 30;
	//Describes the multiplier to calculate price from performance
	private static final int PRICE_COEFFICIENT = 2;
	//Describes the random variance between calculated price and
	//price charged
	private static final int PRICE_VARIATION = 15;
	
	//Pre calculation of constants
	private static final int BOOST_COEFFICIENT = MAX_BOOST - MIN_BOOST;
	
	private int boost;
	
	@Override
	public void setRandom(Random r) {		
		boost = r.nextInt(BOOST_COEFFICIENT) + MIN_BOOST;
		
		price = PRICE_COEFFICIENT * boost;		
		price += r.nextInt(PRICE_VARIATION * 2) + price - PRICE_VARIATION;
		
	}

	@Override
	public void applyBonus(Team team, Hero hero) {
		// Apply boost
		hero.adjustPUHaggling(boost);

	}

	@Override
	public String getApplicationDescriptor() {
		String s = "This Haggling Booster Power Up will add "
				+ boost + " Haggling Points to the Hero it is applied to.\n"
				+ "This will reduce the price they will pay at the shop while you are in this City";
		return s;
	}

	@Override
	public String getTitle() {
		return "Haggling Booster";
	}

	@Override
	public String getDescriptor() {
		
		String s = "Haggling Booster\nA Power-Up that increases a Hero's Haggling Stat\n"
				+ "Boost: " + boost + "\nPrice: $" + temp_price; 
		
		return s;
	}

	@Override
	public String getSaleDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

}
