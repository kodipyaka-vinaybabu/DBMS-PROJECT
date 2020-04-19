package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class updateReserves extends JFrame{
	private JPanel pnl1,pnl0,pnl2;
	private JTextField txtVid, txtPid ,txtPayid, txtDay,txtNo;
	private JLabel lblVid,lblVid1,lblPid1,lblPid,lblPayid1,lblPayid,lblDay,lblNo;
	private List list,l1,l2;
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
	updateReserves(){
		txtVid=new JTextField(15);     txtPid=new JTextField(15);
		txtPayid=new JTextField(15);   txtDay=new JTextField(15);
		txtNo=new JTextField(15);
		lblVid=new JLabel("VID"); 
		lblVid1=new JLabel("VID"); lblPid=new JLabel("PID");
		lblPid1=new JLabel("PID");
		lblPayid=new JLabel("PAYMENT ID");
		lblPayid1=new JLabel("PAYMENT ID");lblDay=new JLabel("DAY");
		lblNo=new JLabel("NO OF PASSENGERS");
		pnl0=new JPanel(); pnl1=new JPanel();
		pnl2=new JPanel();
		list=new List(10);
		l1=new List(10);
		l2=new List(10);
		txtAre=new JTextArea(20,20);  txtAre2=new JTextArea(20, 100);
		btnUpdate=new JButton("MODIFY");
		setTitle("UPDATE RESERVES");
		  pnl0.setLayout(new GridLayout(1,6));
		pnl0.add(lblPid1);
		pnl0.add(list);pnl0.add(lblVid1); pnl0.add(l1); pnl0.add(lblPayid1);
		pnl0.add(l2);
		 
      
		//pnl0.setLayout(new GridLayout(6,1));
		pnl0.setBackground(Color.CYAN);
		pnl1.setBackground(Color.gray);
		pnl2.setBackground(Color.orange);
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
		ResultSet  rs = statement.executeQuery("SELECT PID FROM RESERVES ");
		  while (rs.next()) 
		  {
			list.add(rs.getString("PID"));
		  }
		} 
		
		catch (SQLException e) { 
		  System.out.println(e);
		}
		try {
			ResultSet  rs = statement.executeQuery("SELECT VID FROM typeofvehicle ");
			  while (rs.next()) 
			  {
				l1.add(rs.getString("VID"));
			  }
			} 
			
			catch (SQLException e) { 
			  System.out.println(e);
			}
		try {
			ResultSet  rs = statement.executeQuery("SELECT PAYID FROM PAYMENT ");
			  while (rs.next()) 
			  {
				l2.add(rs.getString("PAYID"));
			  }
			} 
			
			catch (SQLException e) { 
			  System.out.println(e);
			}
		list.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				try {
					ResultSet rs = statement.executeQuery("SELECT * FROM reserves where PID ="+list.getSelectedItem());
					rs.next();
					txtPid.setText(rs.getString("pid"));
					txtVid.setText(rs.getString("VID"));
				    txtPayid.setText(rs.getString("payid"));
					txtDay.setText(rs.getString("day"));
					txtNo.setText(rs.getString("Noofpassengers"));
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
					int i = statement.executeUpdate("UPDATE RESERVES  "
					+ "SET vid=" + txtVid.getText() + ", "
					+ "payid=" + txtPayid.getText() + ", "
					+ "day='"+ txtDay.getText() + "', "
					+ "NoOfPassengers="+txtNo.getText()+"WHERE Pid = "
					+ list.getSelectedItem());
					txtAre2.append("\nUpdated " + i + " rows successfully");
				} 
				catch (SQLException insertException) {
					System.out.println(e);
				}
			}
		});
		
		
		pnl1.add(lblPid);            pnl1.add(txtPid);
		pnl1.add(lblVid);            pnl1.add(txtVid);
		pnl1.add(lblPayid);          pnl1.add(txtPayid);
		pnl1.add(lblDay);            pnl1.add(txtDay);
		pnl1.add(lblNo);             pnl1.add(txtNo);
		pnl2.add(btnUpdate);
		pnl2.add(txtAre2);
		pnl1.setSize(600,500);
		pnl2.setSize(600,500);
		add(pnl0);add(pnl1);
		add(pnl2);
		setSize(2000,1000);
		setLayout(new GridLayout(3, 1));
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
