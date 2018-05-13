/**
 * Provides an interface to allow the product to be purchased
 * in the game shop
 * @author fer25
 *
 */
public interface Saleable {

	/**
	 * Gets the descriptor that can be used in the store. Includes
	 * name and price
	 * @return The descriptor
	 */
	public String getSaleDescriptor();
	
	/**
	 * Gets the sale price that the shop will remove from the
	 * teams purse
	 * @return The price
	 */
	public int getPrice();
	
}
