import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

/**
 * A location that allows HealingItems to be applied to a Hero
 * 
 * @author fer25
 *
 */
public class Hospital extends JPanel implements Location{

	MenuSystem m;	
	Team team;
	
	JPanel left_panel;
	
	public Hospital(MenuSystem m) {
		this.m = m;
	}

	@Override
	public void travelTo(Team team, boolean last_city) {
		
		this.team = team;
		
		//Get all the items the Team owns
		ArrayList<Saleable> all_items = team.getTeamItems();
		
		//Create an array and fill it with all the HealingItems the Team own
		ArrayList<HealingItem> items = new ArrayList<HealingItem>();
		for (Saleable item: all_items) {			
			if (item instanceof HealingItem) {				
				items.add((HealingItem) item);				
			}			
		}
		
		//Allow healing items to be applied while the Team has some in their inventory
		while (!items.isEmpty()) {
			
			Hero selected_hero = selectHero(team);
			
			//User want to return to Home Base
			if (selected_hero == null) {
				return;
			}
			
			String title = "Welcome to the Hospital";
			String description = "What healing item would you like to use?";
			
			Selectable[] items_array = new Selectable[1];
			items_array = items.toArray(items_array);
			ItemSelector selector = new ItemSelector(title, description, items_array);
			left_panel = selector;
			displayHospital();
			
			HealingItem selected_item = (HealingItem) selector.getSelectedObject();
			
			if (selected_item == null); //Do nothing, Application cancelled
			else {
				applyItem(selected_hero, selected_item, all_items, items);
			}		
		}
		String title = "You should get Health Insurance";
		String body = "You do not have any healing items to use.";
		
		InformationPanel info = new InformationPanel(title, body);
		left_panel = info;
		displayHospital();
		info.blockTillOK();
		
	}
	
	private void displayHospital() {
		
		removeAll();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.4};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = left_panel;
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		
		Panel panel = new Panel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{Double.MIN_VALUE, 1.0};
		panel.setLayout(gbl_panel);
		
		JTextPane txtpnCurrentHealingOperations = new JTextPane();
		txtpnCurrentHealingOperations.setText("Current Healing Operations.\nTime Till Completion");
		txtpnCurrentHealingOperations.setEditable(false);
		GridBagConstraints gbc_txtpnCurrentHealingOperations = new GridBagConstraints();
		gbc_txtpnCurrentHealingOperations.insets = new Insets(0, 0, 0, 5);
		gbc_txtpnCurrentHealingOperations.fill = GridBagConstraints.BOTH;
		gbc_txtpnCurrentHealingOperations.gridx = 0;
		gbc_txtpnCurrentHealingOperations.gridy = 0;
		panel.add(txtpnCurrentHealingOperations, gbc_txtpnCurrentHealingOperations);
		
		Panel timer_panel = new Panel();
		GridBagConstraints gbc_timer_panel = new GridBagConstraints();
		gbc_timer_panel.fill = GridBagConstraints.BOTH;
		gbc_timer_panel.gridx = 0;
		gbc_timer_panel.gridy = 1;
		panel.add(timer_panel, gbc_timer_panel);
		timer_panel.setLayout(new BoxLayout(timer_panel, BoxLayout.Y_AXIS));
		
		for (HealingItem item : team.getHealingOperations()) {
			JLabel TimerLabel = new JLabel();
			TimerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			timer_panel.add(TimerLabel);
			item.setLabel(TimerLabel);
			
			JSeparator separator = new JSeparator();
			timer_panel.add(separator);
		}
		
		m.updatePanel(this);
		
	}
	
	/**
	 * Applies selected_item to selected_hero and removes selected_item from
	 * all_items and items
	 * @param selected_hero The Hero to have the HealingItem applied to
	 * @param selected_item The HealingItem to apply to the Hero
	 * @param all_items The Team's inventory
	 * @param items The Hospital's inventory of the Team's HealingItems
	 */
	private void applyItem(Hero selected_hero, HealingItem selected_item,
			ArrayList<Saleable> all_items, ArrayList<HealingItem> items) {

		selected_item.heal(selected_hero, team);
		//Remove the item from the Team's inventory
		all_items.remove((Saleable) selected_item);
		items.remove(selected_item);
		team.addHealOperation(selected_item);
		//Update display to show new timer
		displayHospital();
		
	}
	
	private Hero selectHero(Team team) {
		String title = "Welcome to the Hospital";
		String description = "Which Hero would you like to heal?";
		
		Selectable[] heros = new Selectable[1];
		heros = team.getHeros().toArray(heros);
		ItemSelector selector = new ItemSelector(title, description, heros);
		left_panel = selector;
		displayHospital();
		
		Hero selected_hero = (Hero) selector.getSelectedObject();
		
		return selected_hero;
	}

	@Override
	public String getType() {
		
		return "Hospital";
		
	}	
}
