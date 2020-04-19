package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Passenger extends JFrame{
	private JPanel pnl1, pnl2,pnl4;
	private JTextField txtPid,txtAge,txtEmail,txtPname,txtMobnu;
	private JLabel lblPid, lblAge, lblEmail, lblPname, lblMobnu;
	private JTextArea txtAre;
	private JButton btnInsert;
	Connection connection;
	Statement statement;
	Passenger(){
		txtPid=new JTextField(15);       txtAge=new JTextField(15);
		txtEmail=new JTextField(15);     txtPname=new JTextField(15);
		txtMobnu=new JTextField(15);
		lblPid=new JLabel("PID");        lblAge=new JLabel("ÄGE");
		lblEmail=new JLabel("EMAIL");    lblPname=new JLabel("PASSENGER NAME");
		lblMobnu=new JLabel("MOBILE NUMBER");
		pnl1=new JPanel();               pnl2=new JPanel(); pnl4=new JPanel();
		btnInsert=new JButton("SUBMIT");
		txtAre=new JTextArea(20,100);
		pnl1.setLayout(new GridLayout(5,2));
		pnl1.add(lblPid);            pnl1.add(txtPid);
		pnl1.add(lblAge);            pnl1.add(txtAge);
		pnl1.add(lblEmail);          pnl1.add(txtEmail);
		pnl1.add(lblPname);          pnl1.add(txtPname);
		pnl1.add(lblMobnu);          pnl1.add(txtMobnu);
		pnl2.add(btnInsert); pnl2.add(txtAre);
		pnl1.setSize(600,500);pnl2.setSize(600,500);
		pnl4.setSize(600,500);
		setTitle("INSERT PASSENGER");
		pnl1.setBackground(Color.MAGENTA);
		pnl2.setBackground(Color.CYAN);
		add(pnl1);add(pnl2);
		setSize(2000,1000);setLayout(new GridLayout(2, 1));
		setVisible(true);
		btnInsert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try {
					
				    Class.forName("oracle.jdbc.driver.OracleDriver");
				}
				catch (Exception e) 
				{
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
				try {
				 		  
				  String query= "INSERT INTO passenger VALUES(" + txtPid.getText() + ", "  + txtAge.getText() + "," +"'" + txtEmail.getText() + "'," + "'" + txtPname.getText()  + "'," + txtMobnu.getText() + " )";
				  int i = statement.executeUpdate(query);
				  txtAre.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) {
				 // Dialog d=new Dialog(Passenger, "PLEASE ENTER CORRECT DATATYPES");
					JOptionPane.showMessageDialog(pnl4,"INSERTION CANCELLED \nPLEASE ENTER CORRECT DATATYPES");
				}
			}
		});
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
