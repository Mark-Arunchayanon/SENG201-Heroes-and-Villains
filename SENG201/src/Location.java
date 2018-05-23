/**
 * Provides an interface for locations that can be traveled
 * to from the home base in the centre of the City
 * 
 * @author fer25
 * @author par116
 */
public interface Location {

	/**
	 * Called to transfer the team to the location
	 * @param team The team of heros to transfer to the location
	 * @param last_city Should be true if this is the last city in
	 * the game, False otherwise.
	 */
	public void travelTo(Team team, boolean last_city);
	
	/**
	 * Returns the type of the location
	 * @return The type of the location
	 */
	public String getType();
	
}
