package saleable;
import java.util.Random;

import gui.Selectable;

/**
 * Provides a superclass to allow the product to be purchased
 * in the game shop
 * 
 * @author fer25
 * @author par116
 */
public abstract class Saleable implements Selectable {

	//Stores the base price of the Saleable
	int price;
	//Stores the Haggling stat adjusted value of Saleable
	int temp_price;
	//Tells the Saleable whether or not it has been sold
	boolean sold = false;

	/**
	 * Allows the purchasing Hero's haggling stat to be passed to the
	 * Saleable object before it is passed to an ItemSelector
	 * @param haggling the haggling stat of the purchasing Hero
	 */
	public void setHaggling(int haggling) {
		temp_price = (int) Math.round(price * 100 / haggling);
	}
	
	/**
	 * Gets the sale price that the shop will remove from the
	 * teams purse
	 * @return The price
	 */
	public int getPrice() {
		return temp_price;
	}
	
	/**
	 * Provides a psuedo random number generator to the object.
	 * This means that the same generator can be used for all object creations
	 * as if multiple new Random object are created in quick succession they
	 * all tend to produce the same first few numbers
	 * @param r The Random object to be used by the Saleable
	 */
	public abstract void setRandom(Random r);
	
	/**
	 * Gets the descriptor that can be used in the store. Includes
	 * name and price
	 * @return The descriptor
	 */
	public abstract String getSaleDescriptor();
	
	/**
	 * Allows the Saleable object to know if it has been purchased
	 */
	public void sold() {
		sold = true;
	}

	@Override
	public abstract String getTitle();

	@Override
	public abstract String getDescriptor();
}
