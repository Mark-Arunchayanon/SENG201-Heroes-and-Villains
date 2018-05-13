import java.util.Random;

/**
 * Defines a PowerUp object that increases a Hero's illusion stat
 * @author fer25
 *
 */
public class illusionBooster implements Saleable, PowerUp {
	
	//Define the minimum and maximum illusion boost the power up will provide
	private static final double MIN_BOOST = 0.2;
	private static final double MAX_BOOST = 0.5;
	//Describes the multiplier to calculate price from performance
	private static final double PRICE_COEFFICIENT = 300;
	//Describes the random variance between calculated price and
	//price charged
	private static final int PRICE_VARIATION = 15;
	
	//Pre calulation of constants
	private static final double BOOST_COEFFICIENT = MAX_BOOST - MIN_BOOST;
	
	private int price;
	private static double boost;
	
	public illusionBooster() {
		
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
		
		String s = "Illusion Booster\nA Power-Up that increases a Hero's Illusion Stat\n"
				+ "Boost: " + boost + "\nPrice: $" + price; 
		
		return s;
		
	}

	@Override
	public int getPrice() {
		return price;
	}

}
