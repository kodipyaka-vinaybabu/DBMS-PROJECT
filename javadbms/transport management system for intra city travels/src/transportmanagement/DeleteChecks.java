package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class DeleteChecks extends JFrame {
	private JPanel pnl1,pnl0,pnl2;
	private JTextField txtPid,txtVid,txtDay;
	private JLabel lblPid, lblVid, lblDay;
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
	DeleteChecks(){
		txtPid=new JTextField(15);            txtVid=new JTextField(15);
		txtDay=new JTextField(15);
        lblPid=new JLabel("PID");             lblVid=new JLabel("VID");
		lblDay=new JLabel("DAY");
		pnl0=new JPanel();pnl1=new JPanel();
		pnl2=new JPanel();
		list=new List(10);
		txtAre=new JTextArea(20,20);       txtAre2=new JTextArea(20, 100);
		btnDelete=new JButton("DELETE VALUES");
		setTitle("DELETE CHECKS");
		pnl0.setBackground(Color.BLUE);
		pnl1.setBackground(Color.gray);
		pnl2.setBackground(Color.yellow);
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
		ResultSet  rs = statement.executeQuery("SELECT PID FROM CHECKS");
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
					ResultSet rs = statement.executeQuery("SELECT * FROM CHECKS where pID ="+list.getSelectedItem());
					while (rs.next()) 
					{
						if (rs.getString("PID").equals(list.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) {
						txtPid.setText(rs.getString("PID"));
						txtVid.setText(rs.getString("vid"));
						txtDay.setText(rs.getString("day"));
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
					int i = statement.executeUpdate("DELETE from checks where pid=" +list.getSelectedItem());
					txtAre2.append("\nDeleted " + i + " rows successfully");
					
				} 
				catch (SQLException insertException) {
					txtAre2.append(insertException.getMessage());
				}
			}
		});
		pnl0.add(list);
		pnl1.add(lblPid);            pnl1.add(txtPid);
		pnl1.add(lblVid);            pnl1.add(txtVid);
		pnl1.add(lblDay);          pnl1.add(txtDay);
		pnl2.add(btnDelete);
		pnl2.add(txtAre2);
		pnl1.setSize(600,500);    pnl2.setSize(600,500);
		add(pnl0);  add(pnl1);add(pnl2);
		setSize(2000,1000);
		setLayout(new GridLayout(3, 1));
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
