
public interface Location {

	/**
	 * Called to transfer the team to the location
	 * @param team The team of heros to transfer to the location
	 * @return Returns true if transfer successful. False otherwise
	 */
	public void travelTo(Team team);
	
}
