/**
 * Provides an interface for a PowerUp. These can be purchased at
 * the shop and then applied to a particular Hero in the
 * PowerUpDen
 * @author fer25
 *
 */
public interface PowerUp {

	/**
	 * Applies a PowerUp to the correct stat of either a Hero or a
	 * Team depending on the type of PowerUp 
	 * @param hero
	 */
	public void applyBonus(Team team, Hero hero);
	
}
