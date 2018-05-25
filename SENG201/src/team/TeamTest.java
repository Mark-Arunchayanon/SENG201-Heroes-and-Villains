package team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gui.MenuSystem;
import saleable.HagglingBooster;
import saleable.HealingItem;
import saleable.IllusionBooster;
import saleable.Map;
import saleable.Saleable;

class TeamTest {
	
	private static MenuSystem m = new MenuSystem();	
	private static int[] hero_hagglings = {12, 16};
	private static Hero[] heros = {new Hero(10, 11, 12, hero_hagglings[0]),
			new Hero(14, 15, 14, hero_hagglings[1])};	
	private static String[] hero_names = {"Hero 1", "Hero 2"};	
	private static String team_name = "Team";
	
	private static Team team1;
	private static Team team2;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		for(int i = 0; i < heros.length; i++) {
			
			heros[i].setName(hero_names[i]);
			
		}
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		team1 = new Team(m, team_name, heros, false);
		team2 = new Team(m, team_name, heros, true);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHeroNames() {		
		
		Set<String> names = new HashSet<String>();
		
		for(String name : hero_names) names.add(name);
		
		assertEquals(names, team1.heroNames());
		
	}

	@Test
	void testGetHero() {
		
		for (int i = 0; i < heros.length; i++) {
			assertEquals(heros[i], team1.getHero(i));
		}
		
	}

	@Test
	void testCheckHealth() {
		
		//#####################################################
		//Confirm this truth. Test not valid otherwise
		
		ArrayList<Hero> herosAL = new ArrayList<Hero>();
		
		for(Hero hero : heros) herosAL.add(hero);
		
		assertTrue(herosAL.equals(team1.getHeros()));
		
		//#####################################################
		//Check the correct output with healthy heros
		
		assertTrue(team1.checkHealth());
		
		//#####################################################
		//Check the correct output when removing a hero from the list
		
		int index = 0;
		
		int heros_init_length = heros.length;
		
		for (int i = 0; i < heros_init_length - 1; i++) {
			
			herosAL.get(index).adjustHealth(-(herosAL.get(index).getHealth() + 1));
			
			herosAL.remove(index);
			
			assertTrue(team1.checkHealth());
			
			assertTrue(herosAL.equals(team1.getHeros()));
			
		}
		
		//#####################################################
		//Check the correct output on making an empty list
		
		herosAL.get(index).adjustHealth(-(herosAL.get(index).getHealth() + 1));
		
		herosAL.remove(index);
		
		assertFalse(team1.checkHealth());
		
		assertTrue(herosAL.equals(team1.getHeros()));
		
		//#####################################################
		//Check the correct output on an already empty list
		
		assertFalse(team1.checkHealth());
		
	}

	@Test
	void testGetMap() {

		//Check the function works for both states of Map
		
		assertFalse(team1.getMap());		
		assertTrue(team2.getMap());	
		
		//#####################################################
		//Check the function works for a power up map
		
		team1.setPUMap();
		
		assertTrue(team1.getMap());
		
		//#####################################################
		//Check the function works after resetting the PU map
		
		team1.resetPU();
		
		assertFalse(team1.getMap());
		
	}

	@Test
	void testResetPU() {
		
		//Apply a power up to each of the heros
		
		Random r = new Random();
		
		for (int i = 0; i < heros.length; i++) {
			
			HagglingBooster hb = new HagglingBooster();					
			hb.setRandom(r);						
			hb.applyBonus(team1, heros[i]);
	
			//Check successful application
			assertFalse(hero_hagglings[i] == team1.getHero(i).getHaggling());
			
		}
		
		//Apply a map power up
		
		Map map = new Map();
		map.setRandom(r);
		map.applyBonus(team1, heros[0]); //Hero index doesnt matter, Map applied to team
		//Check sucessfull application
		assertTrue(team1.getMap());
		
		team1.resetPU();
		
		//#####################################################
		//Check the heros PUs have been released
		
		for(int i = 0; i < heros.length; i++) {
			
			assertEquals(hero_hagglings[i], heros[i].getHaggling());
			
		}
		
		//#####################################################
		//Check the teams map pu has been released
		
		assertFalse(team1.getMap());
		
	}

	@Test
	void testGetTeamItems() {
		
		//Add Saleables to team1
		
		@SuppressWarnings("rawtypes")
		final Class[] SALEABLES = {HagglingBooster.class,
				IllusionBooster.class, Map.class, HealingItem.class};
		
		Random r = new Random();
		ArrayList<Saleable> items = new ArrayList<Saleable>();
		
		for(int i = 0; i < 50; i++) {
			
			try {
				Saleable item = (Saleable) SALEABLES[r.nextInt(SALEABLES.length)].newInstance();
				
				team1.addTeamItem(item);
				items.add(item);
			} catch (InstantiationException | IllegalAccessException e) {
				//Shouldn't except
				e.printStackTrace();
			}
			
		}
		
		//#####################################################
		//Check that the local AL and the class stored AL are the same
		
		assertTrue(items.equals(team1.getTeamItems()));
		
	}

	@Test
	void testAdjustCash() {
		
		//Test initial truth
		
		assertEquals(10000, team1.getCash());
		
		//#####################################################
		
		int adj_cash1 = 5;
		
		assertTrue(team1.adjustCash(adj_cash1));
		
		assertEquals(10000 + adj_cash1, team1.getCash());
		
		//#####################################################
		//Check that cash cannot become overdrawn
		
		int current_cash = team1.getCash();
		
		assertFalse(team1.adjustCash(-(team1.getCash() + 1)));
		
		assertEquals(current_cash, team1.getCash());
		
		assertTrue(team1.adjustCash(-team1.getCash()));
		
		assertEquals(0, team1.getCash());
		
	}

	@Test
	void testGetHeros() {
		
		//Create an AL to compare
		ArrayList<Hero> herosAL = new ArrayList<Hero>();
		
		for(Hero hero : heros) herosAL.add(hero);
		
		assertTrue(herosAL.equals(team1.getHeros()));
		
	}

	@Test
	void testRemoveHealOperation() {
		
		//Add some healing items and then remove some to check that
		//the remove function is working

		ArrayList<HealingItem> heals = new ArrayList<HealingItem>();
		ArrayList<HealingItem> heals_to_remove = new ArrayList<HealingItem>();
		
		for(int i = 0; i < 10; i++) {
			HealingItem item = new HealingItem();
			
			heals.add(item);
			team1.addHealOperation(item);
			if (i % 3 == 0) heals_to_remove.add(item);
		}
		
		for (HealingItem item : heals_to_remove) {
			
			heals.remove(item);
			team1.removeHealOperation(item);
			
		}
		
		assertTrue(heals.equals(team1.getHealingOperations()));
	}

	@Test
	void testGetHealingOperations() {
		
		ArrayList<HealingItem> heals = new ArrayList<HealingItem>();
		
		for(int i = 0; i < 10; i++) {
			HealingItem item = new HealingItem();
			
			heals.add(item);
			team1.addHealOperation(item);
		}
		
		assertTrue(heals.equals(team1.getHealingOperations()));
	}

	@Test
	void testGetTeamSize() {
		
		assertEquals(heros.length, team1.getTeamSize());
		
		//#####################################################
		//Check that team_sizze does not change when a hero is removed
		
		int index = 0;
		
		heros[0].adjustHealth(-(heros[index].getHealth() + 1));
		
		team1.checkHealth();
		
		assertEquals(heros.length, team1.getTeamSize());
		
	}

}
