package GUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import Web.GetTNBike;

public class BikeContent implements ActionListener{
	
	Container contentArea;
	JPanel panel = new JPanel();
	JScrollPane scroll; //捲動顯示
	JPanel mainPanel;
	Object[][] data;
	JTable table;
	JButton back = new JButton("返回");
	
	public BikeContent(Container contentArea,JPanel mainPanel) {
		this.contentArea = contentArea;
		this.mainPanel = mainPanel;
		
		GetTNBike bike = new GetTNBike();
		
		try {
			data = bike.getBike();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String[] columns = {"站名","可借用","可停放"};
		table = new JTable(data,columns);
		scroll = new JScrollPane(table);
		
		table.setRowHeight(30);
		back.addActionListener(this);
		panel.add(back);
		
		contentArea.add(panel);
		contentArea.add(scroll);
		contentArea.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == back) {
			contentArea.remove(scroll);
			contentArea.remove(panel);
			
			contentArea.revalidate();
			contentArea.repaint();
			
			contentArea.add(mainPanel);
			contentArea.repaint();
		}
	}
	
}
