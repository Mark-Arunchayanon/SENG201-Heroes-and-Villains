package saleable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HealingItemTest {
	
	private static HealingItem item;
	
	private static  Random r = new Random();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		item = new HealingItem();
		
		item.setRandom(r);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetTitle() {

		assertTrue("Healing Potion".equals(item.getTitle()));
		
	}

	@Test
	void testGetDescriptor() {
		
		String template = "Healing Potion\nHeals a hero over a period of time\nHealth boost: "
				+ item.getHealing() + "\nHeal time: " + item.getTime() + "s" + "\nPrice: $" + item.getPrice();
		
		assertEquals(template, item.getDescriptor());
		
		//#####################################################
		//Test that description changes when sold
		
		item.sold();
		
		template = "Stats for this Healing Potion:\n"
				+ "Healing Amount:  " + item.getHealing() + 
				"\nHealing Time: " + item.getTime();
		
	}

	@Test
	void testSetHaggling() {
		
		int hero_haggling = 130;
		
		int price = item.getPrice();
		
		item.setHaggling(hero_haggling);
		
		assertEquals(price * 100 / hero_haggling, item.getPrice());
		
	}

}
