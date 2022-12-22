package fenetre;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import systemInfo.SystemInfos;

public class Frame {

	JTable table;
	JScrollPane scrollPane;
	JFrame frame;
	DefaultTableModel model;
	SystemInfos sys;
	Vector<Object> row;

	public Frame() throws Exception{
		initialize();
		((DefaultTableModel) table.getModel()).removeRow(0);
		frame.revalidate();
		frame.setVisible(true);
	}

	private void initialize() throws Exception{
		row = new Vector<>();
		frame = new JFrame();
		frame.setBounds(100, 100, 1340, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1306, 718);
		panel.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel();
		sys = new SystemInfos();
		Vector<Object> column = sys.getAttributs();
		model.setColumnIdentifiers(column);
		table.setModel(model);
		updateModel(getRow());
		scrollPane.setViewportView(table);
	}
	public void updateModel(Vector<Object> row){
		getModel().addRow(row);
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	public Vector<Object> getRow() {
		return row;
	}

	public void setRow(Vector<Object> row) {
		this.row = row;
	}
}
