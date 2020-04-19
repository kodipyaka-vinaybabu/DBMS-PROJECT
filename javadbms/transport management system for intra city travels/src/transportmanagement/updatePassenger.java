package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class updatePassenger extends JFrame {
	private JPanel pnl1,pnl0,pnl2;
	private JTextField txtPid, txtAge, txtEmail,txtPname,txtMobnu;
	private JLabel lblPid,lblAge, lblEmail, lblPname,lblMobnu;
	private List list;
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
	updatePassenger(){
		txtPid=new JTextField(15);           txtAge=new JTextField(15);
	    txtEmail=new JTextField(15);         txtPname=new JTextField(15);
		txtMobnu=new JTextField(15);
		lblPid=new JLabel("PID");            lblAge=new JLabel("ÄGE"); 
		lblEmail=new JLabel("EMAIL");        lblPname=new JLabel("PASSENGER NAME");
		lblMobnu=new JLabel("MOBILE NUMBER");
		pnl0=new JPanel();   pnl1=new JPanel(); pnl2=new JPanel();
		list=new List(10);
	    txtAre2=new JTextArea(20, 100);
		btnUpdate=new JButton("MODIFY");
		setTitle("UPDATE PASSENGER");
		pnl0.setBackground(Color.YELLOW);
		pnl1.setBackground(Color.MAGENTA);
		pnl2.setBackground(Color.BLUE);
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
		ResultSet  rs = statement.executeQuery("SELECT PID FROM passenger");
		  while (rs.next()) 
		  {
			list.add(rs.getString("PID"));
		  }
		} 
		catch (SQLException e) { 
			System.out.println(e);
		}
		list.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				try {
					ResultSet rs = statement.executeQuery("SELECT * FROM passenger where pID ="+list.getSelectedItem());
					rs.next();
					txtPid.setText(rs.getString("PID"));
					txtAge.setText(rs.getString("AGE"));
					txtEmail.setText(rs.getString("EMAILID"));
					txtPname.setText(rs.getString("PNAME"));
					txtMobnu.setText(rs.getString("MOBNU"));
					
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
					int i = statement.executeUpdate("UPDATE passenger "
					+ "SET age=" + txtAge.getText() + ", "
					+ "emailid='" + txtEmail.getText() + "', "
					+ "pname ='"+ txtPname.getText() + "', "+
					"mobnu="+txtMobnu.getText()+"WHERE pid = "
					+ list.getSelectedItem());
					txtAre2.append("\nUpdated " + i + " rows successfully");
				} 
				catch (SQLException insertException) {
				 System.out.println(e);
				}
			}
		});
	    pnl0.add(list);
		pnl1.add(lblPid);            pnl1.add(txtPid);
		pnl1.add(lblAge);            pnl1.add(txtAge);
		pnl1.add(lblEmail);          pnl1.add(txtEmail);
		pnl1.add(lblPname);          pnl1.add(txtPname);
		pnl1.add(lblMobnu);          pnl1.add(txtMobnu);
		pnl2.add(btnUpdate);         pnl2.add(txtAre2);
		pnl1.setSize(600,500);       pnl2.setSize(600,500);
		add(pnl0);add(pnl1);add(pnl2);
		setSize(2000,1000);
		setLayout(new GridLayout(3, 1));
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
