package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class DeleteTypeOfVehicle extends JFrame {
	private JPanel pnl1,pnl0,pnl2;
	private JTextField txtVid,txtModeOfTrans, txtCost, txtAvailability, txtType;
	private JLabel lblVid,lblModeOfTrans,lblCost,lblAvailability, lblType;
	private JTextArea txtAre2;
	private List list;
	private JButton btnDelete;
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
	DeleteTypeOfVehicle(){
		txtVid=new JTextField(15);			txtModeOfTrans=new JTextField(15);
		txtCost=new JTextField(15); 		txtAvailability=new JTextField(15);
		txtType=new JTextField(15);
		lblVid=new JLabel("VID");			lblModeOfTrans=new JLabel("PID");
		lblCost=new JLabel("PAYMENT ID");	lblAvailability=new JLabel("DAY");
		lblType=new JLabel("NO OF PASSENGERS");
		pnl0=new JPanel();					pnl1=new JPanel();
		pnl2=new JPanel();
		list=new List(10);
		txtAre2=new JTextArea(20, 100);
		btnDelete=new JButton("DELETE VALUES");
		setTitle("DELETE TYPE OF VEHICLE");
		pnl0.setBackground(Color.blue);
		pnl1.setBackground(Color.pink);
		pnl2.setBackground(Color.yellow);
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
					ResultSet rs = statement.executeQuery("SELECT * FROM typeofvehicle where VID ="+list.getSelectedItem());
					while (rs.next()) 
					{
						if (rs.getString("VID").equals(list.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						txtVid.setText(rs.getString("vid"));
						txtModeOfTrans.setText(rs.getString("ModeOfTransport"));
						txtCost.setText(rs.getString("cost"));
						txtAvailability.setText(rs.getString("availability"));
						txtType.setText(rs.getString("type"));
					}
					
					
				} 
				catch (SQLException selectException) 	{
					System.out.println(e);
				}
			}
		});
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE from typeofvehicle where vid=" +list.getSelectedItem());
					txtAre2.append("\nDeleted " + i + " rows successfully");
				} 
				catch (SQLException insertException){
					txtAre2.append(insertException.getMessage());
					System.out.println(e);
				}
			}
		});
		pnl0.add(list);
		pnl1.add(lblVid);            pnl1.add(txtVid);
		pnl1.add(lblModeOfTrans);    pnl1.add(txtModeOfTrans);
		pnl1.add(lblCost);           pnl1.add(txtCost);
		pnl1.add(lblAvailability);   pnl1.add(txtAvailability);
		pnl1.add(lblType);           pnl1.add(txtType);
		pnl2.add(btnDelete);         pnl2.add(txtAre2);
	    pnl1.setSize(600,500);
		pnl2.setSize(600,500);
		add(pnl0);add(pnl1);add(pnl2);
		setSize(2000,1000);
		setLayout(new GridLayout(3, 1));
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
