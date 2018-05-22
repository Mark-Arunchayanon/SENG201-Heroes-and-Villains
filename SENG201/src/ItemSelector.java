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
import java.awt.Panel;
import javax.swing.JScrollPane;

public class ItemSelector extends JPanel implements ActionListener {
	
	String select_string = "Select";
	String cancel_string = "Cancel";
	
	Selectable[] items;
	
	int selected = -2;
	
	ButtonGroup radio_buttons = new ButtonGroup();
	JTextPane txtpnDescriptor;
	
	public ItemSelector(String title, Selectable[] items) {
		
		this.items = items;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE, 1.0, Double.MIN_VALUE};
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
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		Panel panel = new Panel();
		scrollPane.setViewportView(panel);
		
		//Add all the radio buttons to the GUI. Select the first one
		for (int i = 0; i < items.length; i++) {
			
			System.out.println(i);
			
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
		gbc_txtpnDescriptor.gridy = 1;
		add(txtpnDescriptor, gbc_txtpnDescriptor);
		
		JButton btnSelect = new JButton(select_string);
		btnSelect.setActionCommand(select_string);
		btnSelect.addActionListener(this);
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelect.gridx = 0;
		gbc_btnSelect.gridy = 2;
		add(btnSelect, gbc_btnSelect);
		
		JButton btnCancel = new JButton(cancel_string);
		btnCancel.setActionCommand(cancel_string);
		btnSelect.addActionListener(this);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 2;
		add(btnCancel, gbc_btnCancel);
		
		System.out.println("Done");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == select_string) {
			selected = Integer.parseInt(radio_buttons.getSelection().getActionCommand());
		} else if (e.getActionCommand() == cancel_string) {
			selected = -1;
		} else {
			int currently_selected = Integer.parseInt(e.getActionCommand());
			txtpnDescriptor.setText(items[currently_selected].getDescriptor());
		}
		
	}
	
	public Object getSelectedObject() {
		
		while(selected == -2);
		
		if(selected == -1) {
			return null;
		} else {
			return items[selected];
		}
		
	}
	
	public int getSelectedIndex() {
		
		while(selected == -2);
		
		return selected;
		
	}

}
