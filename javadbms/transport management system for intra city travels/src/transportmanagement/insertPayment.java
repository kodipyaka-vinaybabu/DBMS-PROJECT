package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class insertPayment extends JFrame {
	private JPanel pnl1,pnl2;
	private JTextField txtPayid,txtStatus,txtBill;
	private JLabel lblPayid,lblStatus,lblBill;
	private JTextArea txtAre;
	private JButton btnInsert;
	Connection connection;
	Statement statement;
	insertPayment(){
		txtPayid=new JTextField(15);			txtStatus=new JTextField(15);
		txtBill=new JTextField(15);
		lblPayid=new JLabel("PAYMENT ID");		lblStatus=new JLabel("STATUS");
		lblBill=new JLabel("BILL");
		pnl1=new JPanel();						pnl2=new JPanel();
		txtAre=new JTextArea(20,100);		btnInsert=new JButton("SUBMIT");
		pnl1.setLayout(new GridLayout(3,2));
		pnl1.add(lblPayid);          pnl1.add(txtPayid);
		pnl1.add(lblStatus);         pnl1.add(txtStatus);
		pnl1.add(lblBill);           pnl1.add(txtBill);
		pnl2.add(btnInsert); pnl2.add(txtAre);
	    pnl1.setSize(400,300); pnl2.setSize(600,500);
	    add(pnl1);add(pnl2);
	    setSize(2000,1000);
		setTitle("INSERT PAYMENT");
		pnl1.setBackground(Color.CYAN);
		pnl2.setBackground(Color.gray);
		setLayout(new GridLayout(2, 1));
		setVisible(true);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
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
				try {
				 		  
				  String query= "INSERT INTO payment VALUES(" + txtPayid.getText() + ", "  + "'" +  txtStatus.getText() + "',"  + txtBill.getText()  + " )";
				  int i = statement.executeUpdate(query);
				  txtAre.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException){
				  System.out.println(insertException);
				}
			}
      });
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
