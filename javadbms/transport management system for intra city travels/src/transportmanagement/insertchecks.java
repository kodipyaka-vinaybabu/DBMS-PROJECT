package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class insertchecks extends JFrame{
	private JPanel pnl0,pnl1,pnl2;
	private JTextField /* txtPid,txtVid, */txtDay;
	private JLabel lblPid,lblVid , lblDay;
	private JTextArea txtAre;
	private JButton btnInsert;
	private List l1,l2;
	Connection connection;
	Statement statement;
	insertchecks(){
		//txtPid=new JTextField(15);  txtVid=new JTextField(15);
		txtDay=new JTextField(15);
	    lblPid=new JLabel("PID:");   lblVid=new JLabel("VID:");
		lblDay=new JLabel("DAY");
		txtAre=new JTextArea(20,100);
		pnl0=new JPanel();
		pnl1=new JPanel();          pnl2=new JPanel();
	    btnInsert=new JButton("SUBMIT");
		pnl1.setLayout(new GridLayout(1,2));
		pnl0.setLayout(new GridLayout(1,4));
		l1=new List(10);l2=new List(10);
		pnl0.add(lblPid);
		pnl0.add(l1);
		pnl0.add(lblVid);
		pnl0.add(l2);
		//pnl1.add(lblPid);            pnl1.add(txtPid);
		//pnl1.add(lblVid);            pnl1.add(txtVid);
		pnl1.add(lblDay);            pnl1.add(txtDay);
		pnl2.add(btnInsert);         pnl2.add(txtAre);
		pnl1.setSize(20,100);       pnl2.setSize(20,100);
		pnl2.setBackground(Color.pink);   pnl1.setBackground(Color.CYAN);
		add(pnl0);add(pnl1); add(pnl2);
		setTitle("INSERT CHECKS");
		setSize(2000,1000);
		setLayout(new GridLayout(3, 1));
		setVisible(true);
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
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				 try { String query= "INSERT INTO checks VALUES(" + l1.getSelectedItem() + ", " +
				  l2.getSelectedItem() + "," +"'" + txtDay.getText() + "'" + " )"; 
				  int i =statement.executeUpdate(query);
				  txtAre.append("\nInserted " + i +
				  " rows successfully"); }
            catch (SQLException insertException) {
				  System.out.println(insertException); }
				 
			}
       });
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
