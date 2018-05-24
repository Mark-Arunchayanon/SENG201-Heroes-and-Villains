package team;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Robot;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gui.MenuSystem;

class TeamTest {
	
	private static MenuSystem m = new MenuSystem();
	
	private static Team team1 = new Team(m);
	
	private static Hero[] heros1 = {new Hero(10, 11, 12, 13), new Hero(14, 15, 16, 16)};
	
	private static Hero[] heros2 = {new Hero(10, 11, 12, 13), new Hero(14, 15, 16, 16)};
	
	private static String[] hero_names = {"Hero 1", "Hero 2"};
	
	private static Team team2 = new Team(m, heros2, false);
	
	private static Team team3 = new Team(m, heros2, true);
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
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
	void testHeroNames() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHero() {
		fail("Not yet implemented");
	}

	@Test
	void testCheckHealth() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMap() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPUMap() {
		fail("Not yet implemented");
	}

	@Test
	void testResetPU() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTeamItems() {
		fail("Not yet implemented");
	}

	@Test
	void testAddTeamItem() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCash() {
		fail("Not yet implemented");
	}

	@Test
	void testAdjustCash() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHeros() {
		fail("Not yet implemented");
	}

	@Test
	void testAddHealOperation() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveHealOperation() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHealingOperations() {
		fail("Not yet implemented");
	}

	@Test
	void testGetStatus() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTeamSize() {
		fail("Not yet implemented");
	}

}
