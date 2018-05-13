import java.util.Random;

/**
 * Defines a PowerUp object that increases a Hero's haggling stat
 * @author fer25
 *
 */
public class HagglingBooster implements Saleable, PowerUp {
	
	//Define the minimum and maximum illusion boost the power up will provide
	private static final double MIN_BOOST = 0.1;
	private static final double MAX_BOOST = 0.3;
	//Describes the multiplier to calculate price from performance
	private static final double PRICE_COEFFICIENT = 200;
	//Describes the random variance between calculated price and
	//price charged
	private static final int PRICE_VARIATION = 15;
	
	//Pre calulation of constants
	private static final double BOOST_COEFFICIENT = MAX_BOOST - MIN_BOOST;
	
	private int price;
	private static double boost;
	
	public HagglingBooster() {
		
		Random r = new Random();
		
		boost = BOOST_COEFFICIENT * r.nextFloat() + MIN_BOOST;
		
		price = (int) (PRICE_COEFFICIENT * boost);
		
		price += r.nextInt(PRICE_VARIATION * 2) + price - PRICE_VARIATION;
		
	}

	@Override
	public void applyBonus(Team team, Hero hero) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSaleDescriptor() {
		
		String s = "Haggling Booster\nA Power-Up that increases a Hero's Haggling Stat\n"
				+ "Boost: " + boost + "\nPrice: $" + price; 
		
		return s;
		
	}

	@Override
	public int getPrice() {
		return price;
	}

}
