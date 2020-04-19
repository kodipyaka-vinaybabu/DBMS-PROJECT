package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class updatePayment extends JFrame{
	private JPanel pnl1,pnl0,pnl2;
	private JTextField txtPayid,txtStatus,txtBill;
	private JLabel lblPayid,lblStatus,lblBill;
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
		catch (SQLException connectException){
		  System.exit(1);
		}
    }
	updatePayment(){
		txtPayid=new JTextField(15);	txtStatus=new JTextField(15);
		txtBill=new JTextField(15);
		lblPayid=new JLabel("PayID");	lblStatus=new JLabel("status");
		lblBill=new JLabel("BILL");
		pnl0=new JPanel();	pnl1=new JPanel();
		pnl2=new JPanel();
		list=new List(10);
		txtAre2=new JTextArea(20, 100);
		btnUpdate=new JButton("MODIFY");
		setTitle("UPDATE PAYMENT");
		pnl0.setBackground(Color.orange);
		pnl1.setBackground(Color.gray);
		pnl2.setBackground(Color.pink);
		pnl1.setLayout(new GridLayout(3,2));
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (Exception e) {
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		connectToDB();
		try {
		ResultSet  rs = statement.executeQuery("SELECT PayID FROM payment ");
		  while (rs.next()) 
		  {
			list.add(rs.getString("PayID"));
		  }
		} 
		catch (SQLException e) { 
			System.out.println(e);
		}
		
		list.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				try {
					ResultSet rs = statement.executeQuery("SELECT * FROM payment where PayID ="+list.getSelectedItem());
					rs.next();
					txtPayid.setText(rs.getString("PayID"));
					txtStatus.setText(rs.getString("status"));
					txtBill.setText(rs.getString("bill"));	
					
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
					int i = statement.executeUpdate("UPDATE payment  "
					+ "SET status='" + txtStatus.getText() + "', "
					+ "bill=" + txtBill.getText() + "WHERE payid = "
					+ list.getSelectedItem());
					txtAre2.append("\nUpdated " + i + " rows successfully");
				} 
				catch (SQLException insertException) {
					System.out.println(e);
				}
			}
		});
        pnl0.add(list);
		pnl1.add(lblPayid);            pnl1.add(txtPayid);
		pnl1.add(lblStatus);            pnl1.add(txtStatus);
		pnl1.add(lblBill);          pnl1.add(txtBill);
		pnl2.add(btnUpdate);pnl2.add(txtAre2);
		pnl1.setSize(600,500);
		pnl2.setSize(600,500);
		add(pnl0);add(pnl1);add(pnl2);
		setSize(2000,1000);
		setLayout(new GridLayout(3, 1));
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
