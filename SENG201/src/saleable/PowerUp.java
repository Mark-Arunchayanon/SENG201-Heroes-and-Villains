package saleable;
import team.Hero;
import team.Team;

/**
 * Provides an interface for a PowerUp. These can be purchased at
 * the shop and then applied to a particular Hero in the
 * PowerUpDen
 * 
 * @author fer25
 * @author par116
 */
public abstract class PowerUp extends Saleable {

	/**
	 * Applies a PowerUp to the correct stat of either a Hero or a
	 * Team depending on the type of PowerUp 
	 * @param hero
	 */
	public abstract void applyBonus(Team team, Hero hero);
	
	/**
	 * Provides a descriptor of the PowerUp containing all the information
	 * relevant to the application of it
	 * @return The descriptor
	 */
	public abstract String getApplicationDescriptor();
	
	@Override
	public String getDescriptor() {
		if (sold) {
			return getApplicationDescriptor();
		} else {
			return getSaleDescriptor();
		}
	}
	
}
