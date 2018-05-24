package gui;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import saleable.Selectable;

import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

/**
 * Provides a GUI interface for selecting items that extends selectable
 * 
 * @author fer25
 * @author par116
 */
public class ItemSelector extends JPanel implements ActionListener {
	// Define string for buttons
	private String select_string = "Select";
	private String cancel_string = "Cancel";
	// Create a list for selectable items
	private Selectable[] items;
	
	private int selected = -2;
	
	private Object synchronizer = new Object();
	
	ButtonGroup radio_buttons = new ButtonGroup();
	JTextPane Descriptor;
	JLabel Description;
	
	public ItemSelector(String title, Selectable[] items, boolean cancelable) {
		this(title, "", items, cancelable);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public ItemSelector(String title, String description, Selectable[] items, boolean cancelable) {
		
		this.items = items;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, Double.MIN_VALUE, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel TitleLabel = new JLabel();
		TitleLabel.setFocusable(false);
		TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TitleLabel.setText(title);
		GridBagConstraints gbc_txtlblTitle = new GridBagConstraints();
		gbc_txtlblTitle.gridwidth = 2;
		gbc_txtlblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txtlblTitle.fill = GridBagConstraints.BOTH;
		gbc_txtlblTitle.gridx = 0;
		gbc_txtlblTitle.gridy = 0;
		add(TitleLabel, gbc_txtlblTitle);
		
		Description = new JLabel();
		Description.setFocusable(false);
		Description.setText(description);
		GridBagConstraints gbc_txtpnDescription = new GridBagConstraints();
		Description.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc_txtpnDescription.gridwidth = 2;
		gbc_txtpnDescription.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnDescription.fill = GridBagConstraints.BOTH;
		gbc_txtpnDescription.gridx = 0;
		gbc_txtpnDescription.gridy = 1;
		add(Description, gbc_txtpnDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		Panel panel = new Panel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//Add all the radio buttons to the GUI. Select the first one
		for (int i = 0; i < items.length; i++) {
			
			JRadioButton radioButtons = new JRadioButton(items[i].getTitle());
			radioButtons.setFont(new Font("Tahoma", Font.PLAIN, 18));
			if(i == 0) {
				radioButtons.setSelected(true);
			}
			radioButtons.setActionCommand(Integer.toString(i));
			radioButtons.addActionListener(this);
			panel.add(radioButtons);
			
			radio_buttons.add(radioButtons);
			
		}
		
		Descriptor = new JTextPane();
		Descriptor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Descriptor.setEditable(false);
		Descriptor.setText(items[0].getDescriptor());
		GridBagConstraints gbc_txtpnDescriptor = new GridBagConstraints();
		gbc_txtpnDescriptor.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnDescriptor.fill = GridBagConstraints.BOTH;
		gbc_txtpnDescriptor.gridx = 1;
		gbc_txtpnDescriptor.gridy = 2;
		add(Descriptor, gbc_txtpnDescriptor);
		
		JButton SelectButton = new JButton(select_string);
		SelectButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SelectButton.setActionCommand(select_string);
		SelectButton.addActionListener(this);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		if(!cancelable) gbc_btnSelect.gridwidth = 2;
		gbc_btnSelect.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect.gridx = 0;
		gbc_btnSelect.gridy = 3;
		add(SelectButton, gbc_btnSelect);
		
		if(cancelable) {
			JButton CancelButton = new JButton(cancel_string);
			CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
			CancelButton.setActionCommand(cancel_string);
			CancelButton.addActionListener(this);
			GridBagConstraints gbc_btnCancel = new GridBagConstraints();
			gbc_btnCancel.gridx = 1;
			gbc_btnCancel.gridy = 3;
			add(CancelButton, gbc_btnCancel);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		synchronized(synchronizer) {			
			String command = e.getActionCommand();			
			if (command.equals(select_string)) {
				selected = Integer.parseInt(radio_buttons.getSelection().getActionCommand());
				synchronizer.notify();
			} else if (command.equals(cancel_string)) {
				selected = -1;
				synchronizer.notify();
			} else {
				int currently_selected = Integer.parseInt(e.getActionCommand());
				Descriptor.setText(items[currently_selected].getDescriptor());
			}
			
		}
			
	}
	
	/**
	 * Waits for an item to be selected and returns the selected item
	 * 
	 * @return Selectable item
	 */
	public Selectable getSelectedObject() {
		
		synchronized(synchronizer) 	{		
			try {
				synchronizer.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		if(selected == -1) {
			return null;
		} else {
			return items[selected];
		}
		
	}
	
	/**
	 * Waits for item to be selected and returns the item index from a list
	 * 
	 * @return Index of the selected item
	 */
	public int getSelectedIndex() {
		
		synchronized(synchronizer) 	{		
			try {
				synchronizer.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return selected;
		
	}
	/**
	 * Sets the instruction line to the text parameter
	 * 
	 * @param text
	 */
	public void descriptionTextSet(String text) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Description.setText(text);
			}
		});
	}

}
