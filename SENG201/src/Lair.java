import java.util.Random;

public class Lair implements Location {
	
	private String[] taunts = {"He He He He", "Fe Fi Fo Fum, I advise you to run",
			"Go away", "I really cannot be bothered dealing with you right now",
			"Your time is over", "Pasta Lavista baby"};
	
	Random r = new Random();
	
	private String villain_taunt = taunts[r.nextInt(taunts.length)];

	@Override
	public void travelTo(Team team) {
		
				
		
	}

}
