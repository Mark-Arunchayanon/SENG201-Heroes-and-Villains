/**
 * Provides an interface to allow the product to be purchased
 * in the game shop
 * 
 * @author fer25
 *
 */
public interface Saleable extends Selectable {

	/**
	 * Allows the purchasing Hero's haggling stat to be passed to the
	 * Saleable object before it is passed to an ItemSelector
	 * @param haggling the haggling stat of the purchasing Hero
	 */
	public void setHaggling(int haggling);
	
	/**
	 * Gets the sale price that the shop will remove from the
	 * teams purse
	 * @return The price
	 */
	public int getPrice();
	
}
