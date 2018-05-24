package team;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeroStatSelectorTest {
	
	HeroStatSelector[] hss_list = {new Dandy(), new Explorer(),
			new Illusionist(), new Physician(), new Stickler(), 
			new Strongman()};
	
	int[] cash_list = {130, 80, 90, 100, 90, 100};
	int[] health_list = {90, 90, 90, 80, 100, 130};
	int[] healing_list = {100, 100, 90, 130, 90, 90};
	int[] illusion_list = {90, 100, 130, 90, 90, 90};
	int[] haggling_list = {90, 100, 100, 100, 130, 90};
	boolean[] map_list = {false, true, false, false, false, false};

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
	void testCreateHero() {
		
		for (int i = 0; i < hss_list.length; i++) {
			
			//Check values haven't been adjusted
			
			assertEquals(cash_list[i], hss_list[i].getCash());			
			assertEquals(health_list[i], hss_list[i].getTotalHealth());			
			assertEquals(healing_list[i], hss_list[i].getHealing());			
			assertEquals(illusion_list[i], hss_list[i].getIllusion());			
			assertEquals(haggling_list[i], hss_list[i].getHaggling());			
			assertEquals(map_list[i], hss_list[i].getMap());
			
			//#####################################################
			//Check Hero is created correctly
			
			Hero hero = hss_list[i].createHero();
			
			assertEquals(health_list[i], hero.getHealth());			
			assertEquals(healing_list[i], hero.getHealing());			
			assertEquals(illusion_list[i], hero.getIllusion());			
			assertEquals(haggling_list[i], hero.getHaggling());
			
		}
		
	}

}
