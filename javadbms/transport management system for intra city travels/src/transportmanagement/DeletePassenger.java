package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class DeletePassenger extends JFrame {
	private JPanel pnl1,pnl0,pnl2;
	private JTextField txtPid,txtAge,txtEmail,txtPname,txtMobnu;
	private JLabel lblPid,lblAge,lblEmail, lblPname,lblMobnu;
    private List list;
	private JTextArea txtAre,txtAre2;
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
	
	DeletePassenger(){
		txtPid=new JTextField(15);      txtAge=new JTextField(15);
		txtEmail=new JTextField(15);    txtPname=new JTextField(15);
		txtMobnu=new JTextField(15);
		lblPid=new JLabel("PID");       lblAge=new JLabel("ÄGE");
		lblEmail=new JLabel("EMAIL");   lblPname=new JLabel("PASSENGER NAME");
		lblMobnu=new JLabel("MOBILE NUMBER");
		pnl0=new JPanel();  pnl1=new JPanel();  pnl2=new JPanel();
		list=new List(10);
		txtAre=new JTextArea(20,20);   txtAre2=new JTextArea(20, 100);
		btnDelete=new JButton("DELETE VALUES");
		setTitle("DELETE PASSENGER");
	    pnl0.setBackground(Color.CYAN);
		pnl1.setBackground(Color.ORANGE);
		pnl2.setBackground(Color.PINK);
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
					while (rs.next()) {
						if (rs.getString("PID").equals(list.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) {
						txtPid.setText(rs.getString("PID"));
						txtAge.setText(rs.getString("AGE"));
						txtEmail.setText(rs.getString("EMAILID"));
						txtPname.setText(rs.getString("PNAME"));
						txtMobnu.setText(rs.getString("MOBNU"));
					}
				} 
				catch (SQLException selectException) {
					System.out.println(e);
				}
			}
		});
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE from passenger where pid=" +list.getSelectedItem());
					txtAre2.append("\nDeleted " + i + " rows successfully");
					} 
				catch (SQLException insertException) {
					txtAre2.append(insertException.getMessage());
					
				}
			}
		});
	    pnl0.add(list);
		pnl1.add(lblPid);            pnl1.add(txtPid);
		pnl1.add(lblAge);            pnl1.add(txtAge);
		pnl1.add(lblEmail);          pnl1.add(txtEmail);
		pnl1.add(lblPname);          pnl1.add(txtPname);
		pnl1.add(lblMobnu);          pnl1.add(txtMobnu);
		pnl2.add(btnDelete); pnl2.add(txtAre2);
		pnl1.setSize(600,500);pnl2.setSize(600,500);
		add(pnl0);add(pnl1);add(pnl2);
		setSize(2000,1000);
		setLayout(new GridLayout(3, 1));
	    setVisible(true);
	}
}
