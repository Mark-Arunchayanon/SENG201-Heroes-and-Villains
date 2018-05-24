package team;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeroTest {
	
	private static int Health = 100;
	private static int	Illusion = 110;
	private static int	Healing = 120;
	private static int Haggling = 130;
	
	private static Hero hero; 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		hero = new Hero(Health, Illusion, Healing, Haggling);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAdjustHealth() {
		
		assertEquals(Health, hero.getHealth());
		
		//#####################################################
		//Check that health cannot exceed original value
		
		hero.adjustHealth(1);
		
		assertEquals(Health, hero.getHealth());
		
		//#####################################################
		//Check that output responds to an adjustment of the health
		
		int adj_health1 = -13;
		
		hero.adjustHealth(adj_health1);
		
		assertEquals(Health + adj_health1, hero.getHealth());
		
		//#####################################################
		//Health adjustments should sum
		
		int adj_health2 = 6;
		
		hero.adjustHealth(adj_health2);
		
		assertEquals(Health + adj_health1 + adj_health2, hero.getHealth());
		
		//#####################################################
		//Health should be lower bounded by zero
		
		int adj_health3 = -100;
		
		hero.adjustHealth(adj_health3);
		
		assertEquals(0, hero.getHealth());
		
		//#####################################################
		//Health should not be added to a dead Hero
		
		hero.adjustHealth(1);
		
		assertEquals(0, hero.getHealth());
		
	}

	@Test
	void testGetHealth() {
		assertEquals(Health, hero.getHealth());
	}
	
	@Test
	void testGetStats() {
		
		//Test that getStats matches the desired template
		
		String template1 = "Health: 100\n" + 
				"Illusion Skill: 110\n" + 
				"Healing Skill: 120\n" + 
				"Haggling Skill: 130";
		
		assertEquals(template1, hero.getStats());
		
		//#####################################################
		//Have a bit of a play with the adjust functions
		//and check that the returned value responds to this
		
		hero.adjustHealth(5);
		hero.adjustPUHaggling(6);
		hero.adjustPUIllusion(7);
		
		//Note that Health cannot exceed the original value;
		String template2 = "Health: 100\n" + 
				"Illusion Skill: 117\n" + 
				"Healing Skill: 120\n" + 
				"Haggling Skill: 136";
		
		assertEquals(template2, hero.getStats());
		
		//#####################################################
		
		hero.adjustHealth(-5);
		//Power up adjustments should stack
		hero.adjustPUHaggling(6);
		hero.adjustPUIllusion(7);
		
		String template3 = "Health: 95\n" + 
				"Illusion Skill: 124\n" + 
				"Healing Skill: 120\n" + 
				"Haggling Skill: 142";
		
		assertEquals(template3, hero.getStats());
		
		//#####################################################
		//Use the power up reset function
		//and check that the returned value responds to this
		
		hero.resetPU();
		hero.adjustHealth(5);
		
		assertEquals(template1, hero.getStats());
		
	}

	@Test
	void testSetName() {
		
		//Check the name is originally null as the name can only be changed under this condition
		
		assertNull(hero.getName());
		
		//#####################################################
		
		String name1 = "Fred";
		
		hero.setName(name1);
		
		assertEquals(name1, hero.getName());
		
		//#####################################################
		//Name cannot be changed if it has already been set
		
		String name2 = "John";
		
		hero.setName(name2);
		
		assertEquals(name1, hero.getName());
		
	}

	@Test
	void testGetIllusion() {

		assertEquals(Illusion, hero.getIllusion());
		
		//#####################################################
		//Check that the returned value responds to a Power Up
		
		int adj_illusion1 = 6;
		
		hero.adjustPUIllusion(adj_illusion1);
		
		assertEquals(Illusion + adj_illusion1, hero.getIllusion());
		
		//#####################################################
		//Power Up adjustments should sum
		
		int adj_illusion2 = 3;
		
		hero.adjustPUIllusion(adj_illusion2);
		
		assertEquals(Illusion + adj_illusion1 + adj_illusion2, hero.getIllusion());
		
		//#####################################################
		//Check output respond to a Power Up reset
		
		hero.resetPU();
		
		assertEquals(Illusion, hero.getIllusion());
		
	}

	@Test
	void testGetHealing() {
		
		assertEquals(Healing, hero.getHealing());
		
	}

	@Test
	void testGetHaggling() {
		
		assertEquals(Haggling, hero.getHaggling());
		
		//#####################################################
		//Check that the returned value responds to a Power Up
		
		int adj_haggling1 = 6;
		
		hero.adjustPUHaggling(adj_haggling1);
		
		assertEquals(Haggling + adj_haggling1, hero.getHaggling());
		
		//#####################################################
		//Power Up adjustments should sum
		
		int adj_haggling2 = 3;
		
		hero.adjustPUHaggling(adj_haggling2);
		
		assertEquals(Haggling + adj_haggling1 + adj_haggling2, hero.getHaggling());
		
		//#####################################################
		//Check output respond to a Power Up reset
		
		hero.resetPU();
		
		assertEquals(Haggling, hero.getHaggling());
		
	}
	
//These functions have been thoroughly tested by the previous tests
//	@Test
//	void testAdjustPUIllusion() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAdjustPUHaggling() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testResetPU() {
//		fail("Not yet implemented");
//	}

	@Test
	void testGetTitle() {

		//Cannot change name if this test fails
		//(Checking my test rather than my function)
		
		assertNull(hero.getName());
		
		String name = "John";
		
		hero.setName(name);
		
		//getTitle should return the Hero's name
		assertEquals(hero.getName(), hero.getTitle());
		assertEquals(name, hero.getTitle());
		
	}

	@Test
	void testGetDescriptor() {
		
		//getDescriptor should return the Hero's stats
		assertEquals(hero.getStats(), hero.getDescriptor());
		
	}

}
