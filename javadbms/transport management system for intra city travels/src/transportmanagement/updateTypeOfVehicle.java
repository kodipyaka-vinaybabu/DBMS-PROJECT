package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class updateTypeOfVehicle extends JFrame{
	private JPanel pnl1,pnl0,pnl2;
	private JTextField txtVid,txtModeOfTrans,txtCost,txtAvailability,txtType;
	private JLabel lblVid,lblModeOfTrans,lblCost,lblAvailability,lblType;
	private JTextArea txtAre2;
	private List list;
	private JButton btnUpdate;
	Connection connection;
	Statement statement;
	public void connectToDB() {
		try {
		  connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","dbmsass","dbms");
		  statement = connection.createStatement();

		} 
		catch (SQLException connectException) {
		  System.exit(1);
		}
    }
	updateTypeOfVehicle() {
		txtVid=new JTextField(15);    txtModeOfTrans=new JTextField(15);
		txtCost=new JTextField(15);   txtAvailability=new JTextField(15);
		txtType=new JTextField(15);
		lblVid=new JLabel("VID");     lblModeOfTrans=new JLabel("MODE OF TRANSPORT");
		lblCost=new JLabel("COST");   lblAvailability=new JLabel("AVAILABILITY");
		lblType=new JLabel("TYPE");
		pnl0=new JPanel();   pnl1=new JPanel();
		pnl2=new JPanel();
		list=new List(10);
		txtAre2=new JTextArea(20, 100);
		btnUpdate=new JButton("MODIFY");
		setTitle("UPDATE TYPE OF VEHICLE");
		pnl0.setBackground(Color.orange);
		pnl1.setBackground(Color.cyan);
		pnl2.setBackground(Color.pink);
		pnl1.setLayout(new GridLayout(5,2));
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (Exception e) {
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		connectToDB();
		try {
		ResultSet  rs = statement.executeQuery("SELECT vid FROM typeOfVehicle ");
		  while (rs.next()) 
		  {
			list.add(rs.getString("VID"));
		  }
		} 
		catch (SQLException e) { 
			System.out.println(e);
		}
		list.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				try {
					ResultSet rs = statement.executeQuery("SELECT * FROM typeOfVehicle where VID ="+list.getSelectedItem());
					rs.next();
					txtVid.setText(rs.getString("vid"));
					txtModeOfTrans.setText(rs.getString("ModeOfTransport"));
				    txtCost.setText(rs.getString("cost"));
					txtAvailability.setText(rs.getString("availability"));
					txtType.setText(rs.getString("type"));
					
				} 
				catch (SQLException selectException) {
					System.out.println(e);
				}
			}
		});
	    btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE typeOfVehicle "
					+ "SET ModeOfTransport='" + txtModeOfTrans.getText() + "', "
					+ "cost=" + txtCost.getText() + ", "
					+ "availability="+ txtAvailability.getText() + ", "
					+ "type='"+txtType.getText()+"'WHERE vid = "
					+ list.getSelectedItem());
					txtAre2.append("\nUpdated " + i + " rows successfully");
					//list.removeAll();
					
				} 
				catch (SQLException insertException) {
					System.out.println(e);
				}
			}
		});
		pnl0.add(list);
		pnl1.add(lblVid);            pnl1.add(txtVid);
		pnl1.add(lblModeOfTrans);            pnl1.add(txtModeOfTrans);
		pnl1.add(lblCost);          pnl1.add(txtCost);
		pnl1.add(lblAvailability);            pnl1.add(txtAvailability);
		pnl1.add(lblType);             pnl1.add(txtType);
		pnl2.add(btnUpdate);
		pnl2.add(txtAre2);
		pnl1.setSize(600,500);
		pnl2.setSize(600,500);
		add(pnl0);
		add(pnl1);
		add(pnl2);
		setSize(2000,1000);
		setLayout(new GridLayout(3, 1));
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
