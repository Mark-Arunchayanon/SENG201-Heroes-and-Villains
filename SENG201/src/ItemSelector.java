import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import java.awt.Panel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class ItemSelector extends JPanel implements ActionListener {
	
	private String select_string = "Select";
	private String cancel_string = "Cancel";
	
	private Selectable[] items;
	
	private int selected = -2;
	
	private Object synchronizer = new Object();
	
	ButtonGroup radio_buttons = new ButtonGroup();
	JTextPane txtpnDescriptor;
	JTextPane txtpnDescription;
	
	public ItemSelector(String title, Selectable[] items) {
		this(title, "", items);
	}
	
	public ItemSelector(String title, String description, Selectable[] items) {
		
		this.items = items;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, Double.MIN_VALUE, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setEditable(false);
		txtpnTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnTitle.setText(title);
		GridBagConstraints gbc_txtpnTitle = new GridBagConstraints();
		gbc_txtpnTitle.gridwidth = 2;
		gbc_txtpnTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnTitle.fill = GridBagConstraints.BOTH;
		gbc_txtpnTitle.gridx = 0;
		gbc_txtpnTitle.gridy = 0;
		add(txtpnTitle, gbc_txtpnTitle);
		
		txtpnDescription = new JTextPane();
		txtpnDescription.setText(description);
		GridBagConstraints gbc_txtpnDescription = new GridBagConstraints();
		gbc_txtpnDescription.gridwidth = 2;
		gbc_txtpnDescription.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnDescription.fill = GridBagConstraints.BOTH;
		gbc_txtpnDescription.gridx = 0;
		gbc_txtpnDescription.gridy = 1;
		add(txtpnDescription, gbc_txtpnDescription);
		
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
			
			JRadioButton rdbtn = new JRadioButton(items[i].getTitle());
			if(i == 0) {
				rdbtn.setSelected(true);
			}
			rdbtn.setActionCommand(Integer.toString(i));
			rdbtn.addActionListener(this);
			panel.add(rdbtn);
			
			radio_buttons.add(rdbtn);
			
		}
		
		txtpnDescriptor = new JTextPane();
		txtpnDescriptor.setText(items[0].getDescriptor());
		GridBagConstraints gbc_txtpnDescriptor = new GridBagConstraints();
		gbc_txtpnDescriptor.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnDescriptor.fill = GridBagConstraints.BOTH;
		gbc_txtpnDescriptor.gridx = 1;
		gbc_txtpnDescriptor.gridy = 2;
		add(txtpnDescriptor, gbc_txtpnDescriptor);
		
		JButton btnSelect = new JButton(select_string);
		btnSelect.setActionCommand(select_string);
		btnSelect.addActionListener(this);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect.gridx = 0;
		gbc_btnSelect.gridy = 3;
		add(btnSelect, gbc_btnSelect);
		
		JButton btnCancel = new JButton(cancel_string);
		btnCancel.setActionCommand(cancel_string);
		btnCancel.addActionListener(this);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 3;
		add(btnCancel, gbc_btnCancel);
		
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
				txtpnDescriptor.setText(items[currently_selected].getDescriptor());
			}
			
		}
			
	}
	
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
	
	public void descriptionTextSet(String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				txtpnDescription.setText(text);
			}
		});
	}

}
