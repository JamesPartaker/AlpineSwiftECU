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
import java.awt.TextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import javax.swing.JButton;
import java.awt.FlowLayout;


@SuppressWarnings("serial")
public class AlpineSwiftInterface extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public AlpineSwiftInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[]{0.20, 0.8};
		gbl_contentPane.rowWeights = new double[]{1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		String[] columnNames =  {"Name", "Value", "Units", "Internal"};
		Object[][] data = { {"Max Engine Speed", "120000", "RPM", "24"}, {"Max temp @ pre-startup", "88", "�C", "88"} };
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table = new JTable(data, columnNames);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.append("[12:34:31] Error: ECU Config Version Incompatible");
		scrollPane_1.setViewportView(textArea);
		
		
		
		
		//panel.add(table);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0};
		gbl_panel_1.rowWeights = new double[]{0.5, 1.0};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLUE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWeights = new double[]{0.3, 0.7};
		gbl_panel_2.rowWeights = new double[]{1.0};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBackground(Color.GREEN);
		GridBagConstraints gbc_panel_18 = new GridBagConstraints();
		gbc_panel_18.fill = GridBagConstraints.BOTH;
		gbc_panel_18.gridx = 0;
		gbc_panel_18.gridy = 0;
		panel_2.add(panel_18, gbc_panel_18);
		
		JButton btnNewButton = new JButton("Startup");
		btnNewButton.setPreferredSize(new Dimension(200,50));
		
		JButton btnShutdown = new JButton("Shutdown");
		btnShutdown.setPreferredSize(new Dimension(200,50));
		
		JButton btnEmergencyShutdown = new JButton("Emergency Shutdown");
		btnEmergencyShutdown.setPreferredSize(new Dimension(200,50));
		//btnEmergencyShutdown.setBackground(new Color(200,30,30));
		//btnEmergencyShutdown.setOpaque(true);
		//btnEmergencyShutdown.setBorder(null);
		
		panel_18.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_18.add(btnNewButton);
		panel_18.add(btnShutdown);
		panel_18.add(btnEmergencyShutdown);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBackground(Color.CYAN);
		GridBagConstraints gbc_panel_19 = new GridBagConstraints();
		gbc_panel_19.fill = GridBagConstraints.BOTH;
		gbc_panel_19.gridx = 1;
		gbc_panel_19.gridy = 0;
		panel_2.add(panel_19, gbc_panel_19);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel_1.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.RED);
		panel_3.add(panel_4);
		panel_4.setLayout(new GridLayout(5, 0, 0, 0));
		
		//JPanel panel_6 = new JPanel();
		//panel_6.setBackground(Color.LIGHT_GRAY);
		//panel_4.add(panel_6);
		
		//CHARTS!
		/*
		LineChart lineChart = new LineChart();
		ChartPanel chartPanel1 = lineChart.getChartPanel();
		panel_4.add(chartPanel1);
		*/
		
		DynamicLineChart dynamicLineChart = new DynamicLineChart();
		dynamicLineChart.start();
		ChartPanel chartPanel2 = dynamicLineChart.getChartPanel();
		panel_4.add(chartPanel2);
		
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.GREEN);
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		panel_5.add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.RED);
		panel_5.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.ORANGE);
		panel_5.add(panel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.GREEN);
		panel_5.add(panel_12);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.CYAN);
		panel_5.add(panel_15);
		
		
	}

}
