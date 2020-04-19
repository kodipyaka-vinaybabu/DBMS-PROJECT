package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class UpdateChecks  extends JFrame{
	private JPanel pnl1,pnl0,pnl2;
	private JTextField txtPid, txtVid,txtDay;
	private JLabel lblPid,lblVid,lblDay,lblPid1,lblVid1;
	private List list,l2;
	private JTextArea txtAre,txtAre2;
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
	
	UpdateChecks(){
		
		txtPid=new JTextField(15);txtVid=new JTextField(15);
		txtDay=new JTextField(15);
        lblPid=new JLabel("PID");lblVid=new JLabel("VID");
        lblPid1=new JLabel("PID");lblVid1=new JLabel("VID");
		lblDay=new JLabel("DAY");
		pnl0=new JPanel();pnl1=new JPanel();
		pnl2=new JPanel();
	    list=new List(10);l2=new List(10);
		txtAre=new JTextArea(20,20);txtAre2=new JTextArea(20, 100);
		btnUpdate=new JButton("MODIFY");
		pnl1.setLayout(new GridLayout(3,2));
		pnl0.setLayout(new GridLayout(1,4));
		pnl0.add(lblPid1);pnl0.add(list);
		pnl0.add(lblVid1);pnl0.add(l2);
		setTitle("UPDATE CHECKS");
        pnl0.setBackground(Color.pink);
        pnl1.setBackground(Color.gray);
        pnl2.setBackground(Color.cyan);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (Exception e){
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		connectToDB();
		try{
		ResultSet  rs = statement.executeQuery("SELECT PID FROM checks ");
		  while (rs.next()) 
		  {
			list.add(rs.getString("PID"));
		  }
		} 
		catch (SQLException e) { 
	  	System.out.println(e);
		}
		try{
			ResultSet  rs = statement.executeQuery("SELECT VID FROM typeofvehicle ");
			  while (rs.next()) 
			  {
				l2.add(rs.getString("VID"));
			  }
			} 
			catch (SQLException e) { 
		  	System.out.println(e);
			}

		list.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				try {
					ResultSet rs = statement.executeQuery("SELECT * FROM checks where PID ="+list.getSelectedItem());
					rs.next();
					txtPid.setText(rs.getString("PID"));
					txtVid.setText(rs.getString("VID"));
					txtDay.setText(rs.getString("DAY"));
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
					int i = statement.executeUpdate("UPDATE CHECKS  "
					+ "SET VID=" + txtVid.getText() + ", "
					+ "day='" + txtDay.getText() + "' "+ "WHERE pid = "
					+ list.getSelectedItem());
					txtAre2.append("\nUpdated " + i + " rows successfully");
				} 
				catch (SQLException insertException) {
				System.out.println(e);
				}
			}
		});
		//pnl0.add(list);
		pnl1.add(lblPid);            pnl1.add(txtPid);
		pnl1.add(lblVid);            pnl1.add(txtVid);
		pnl1.add(lblDay);          pnl1.add(txtDay);
		pnl2.add(btnUpdate);     pnl2.add(txtAre2);
		pnl1.setSize(600,500);   pnl2.setSize(600,500);
	    add(pnl0);   add(pnl1);   add(pnl2);
		setSize(2000,1000);
		setLayout(new GridLayout(3, 1));
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

}
