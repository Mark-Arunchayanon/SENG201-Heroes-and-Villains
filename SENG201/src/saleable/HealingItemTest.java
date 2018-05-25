package saleable;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HealingItemTest {
	
	private static HealingItem item = new HealingItem();
	
	private static  Random r = new Random();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		item.setRandom(r);
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSetRandom() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTitle() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDescriptor() {
		
		String template = "Healing Potion\nHeals a hero over a period of time\nHealth boost: "
				+ heal + "\nHeal time: " + time + "s" + "\nPrice: $" + temp_price;
		
	}

	@Test
	void testGetHealingDescriptor() {
		fail("Not yet implemented");
	}

	@Test
	void testHeal() {
		fail("Not yet implemented");
	}

	@Test
	void testSetLabel() {
		fail("Not yet implemented");
	}

	@Test
	void testSetHealing() {
		fail("Not yet implemented");
	}

	@Test
	void testSetHaggling() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPrice() {
		fail("Not yet implemented");
	}

	@Test
	void testSold() {
		fail("Not yet implemented");
	}

}
