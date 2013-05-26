import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class AlpineSwiftInterface extends JFrame {

	private JPanel contentPane;

	public AlpineSwiftInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.ORANGE);
		panel.setBackground(Color.RED);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JTree tree_1 = new JTree();
		JScrollPane scrollPane_2 = new JScrollPane(tree_1);
		panel.add(scrollPane_2);
		
		JTree tree = new JTree();
		JScrollPane scrollPane_1 = new JScrollPane(tree);
		panel.add(scrollPane_1);
		
		String[] columnNames =  {"Name", "Value", "Units", "Internal"};
		Object[][] data = { {"Max Engine Speed", "120000", "RPM", "24"}, {"Max temp @ pre-startup", "88", "¼C", "88"} };
		
		JTable table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);

		JTextPane textPane = new JTextPane();
		panel.add(textPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		contentPane.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{635, 0};
		//gbl_panel_1.rowHeights = new int[] {222, 74, 74, 74, 74, 74, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{3.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		panel_1.setLayout(gbl_panel_1);
				
				JPanel panel_3 = new JPanel();
				panel_3.setBackground(Color.CYAN);
				GridBagConstraints gbc_panel_3 = new GridBagConstraints();
				gbc_panel_3.fill = GridBagConstraints.BOTH;
				gbc_panel_3.insets = new Insets(0, 0, 5, 0);
				gbc_panel_3.gridx = 0;
				gbc_panel_3.gridy = 0;
				panel_1.add(panel_3, gbc_panel_3);
		
				JPanel panel_4 = new JPanel();
				panel_4.setBackground(Color.GREEN);
				GridBagConstraints gbc_panel_4 = new GridBagConstraints();
				gbc_panel_4.fill = GridBagConstraints.BOTH;
				gbc_panel_4.insets = new Insets(0, 0, 5, 0);
				gbc_panel_4.gridx = 0;
				gbc_panel_4.gridy = 1;
				panel_1.add(panel_4, gbc_panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.ORANGE);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 2;
		panel_1.add(panel_5, gbc_panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.MAGENTA);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 3;
		panel_1.add(panel_6, gbc_panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.PINK);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 4;
		panel_1.add(panel_7, gbc_panel_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 5;
		panel_1.add(panel_2, gbc_panel_2);
		
		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 6;
		panel_1.add(panel_12, gbc_panel_12);
	}

}
