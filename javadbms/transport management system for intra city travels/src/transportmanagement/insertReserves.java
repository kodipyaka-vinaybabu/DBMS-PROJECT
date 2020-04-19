package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class insertReserves extends JFrame{
	private JPanel pnl0,pnl1,pnl2;
	private JTextField txtDay,txtNoofpass;
	private JLabel lblPid,lblVid,lblPayid,lblDay,lblNoofpass;
	private JTextArea txtAre;
	private List l1,l2,l3;
	private JButton btnInsert;
	Connection connection;
	Statement statement;
	insertReserves(){
		pnl0=new JPanel();
		pnl1=new JPanel();      pnl2=new JPanel();
		pnl0.setLayout(new GridLayout(1,6));
		pnl1.setLayout(new GridLayout(2,2));
		//pnl2.setLayout(new GridLayout(1,2));
		//txtPid=new JTextField(15);	         txtVid=new JTextField(15);
		/*txtPayid=new JTextField(15);*/         txtDay=new JTextField(15);
		txtNoofpass=new JTextField(15);
		
		lblPid=new JLabel("PID:");			lblVid=new JLabel("VID:");
		lblPayid=new JLabel("PAYMENT ID:");  lblDay=new JLabel("DAY");
		lblNoofpass=new JLabel("NO OF PASSENGERS");
		
		txtAre=new JTextArea(20,100);
		l1=new List(10);l2=new List(10);l3=new List(10);
		btnInsert=new JButton("SUBMIT");
		pnl0.add(lblPid);pnl0.add(l1);pnl0.add(lblVid);pnl0.add(l2);
		pnl0.add(lblPayid);pnl0.add(l3);
		
		    
		pnl1.add(lblDay);            pnl1.add(txtDay);
		pnl1.add(lblNoofpass);          pnl1.add(txtNoofpass);
		pnl2.add(btnInsert);
		pnl2.add(txtAre);
		pnl0.setSize(600,500);
		pnl1.setSize(600,500);pnl2.setSize(600,500);
		add(pnl0);add(pnl1);add(pnl2);
		setTitle("INSERT RESERVES");
		setSize(2000,1000);
		setLayout(new GridLayout(3, 1));
		setVisible(true);
		pnl0.setBackground(Color.yellow);
		pnl1.setBackground(Color.cyan);
		pnl2.setBackground(Color.BLUE);
		try {
			
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception e) {
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		try {
		  connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","dbmsass","dbms");
		  statement = connection.createStatement();

		} 
		catch (SQLException connectException) {
             System.exit(1);
		}
		try{
			ResultSet  rs = statement.executeQuery("SELECT PID FROM passenger ");
			  while (rs.next()) 
			  {
				l1.add(rs.getString("PID"));
			  }
			} 
			catch (SQLException e) { 
		  	System.out.println(e);
			}
		try{
			ResultSet  rs = statement.executeQuery("SELECT VID FROM typeOfVehicle ");
			  while (rs.next()) 
			  {
				l2.add(rs.getString("VID"));
			  }
			} 
			catch (SQLException e) { 
		  	System.out.println(e);
			}
		try{
			ResultSet  rs = statement.executeQuery("SELECT PAYID FROM payment ");
			  while (rs.next()) 
			  {
				l3.add(rs.getString("PAYID"));
			  }
			} 
			catch (SQLException e) { 
		  	System.out.println(e);
			}
		btnInsert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
				try {		  
				 String query= "INSERT INTO reserves VALUES(" + l1.getSelectedItem() + ", "  + l2.getSelectedItem() + "," + l3.getSelectedItem() + "," + "'" + txtDay.getText()  + "'," + txtNoofpass.getText() + " )";
				  int i = statement.executeUpdate(query);
				  txtAre.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) {
				  System.out.println(insertException);
				}
			}
        });
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
