package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class insertTypeOfVehicle extends JFrame {
	private JPanel pnl1,pnl2;
	private JTextField txtVid,txtModeOftrans,txtCost,txtAvailability,txtType;
	private JLabel lblVid,lblModeOftrans,lblCost,lblAvailability,lblType;
	private JTextArea txtAre;
	private JButton btnInsert;
	Connection connection;
	Statement statement;
	insertTypeOfVehicle(){
		txtVid=new JTextField(15);			txtModeOftrans=new JTextField(15);
		txtCost=new JTextField(15);         txtAvailability=new JTextField(15);
		txtType=new JTextField(15);
		lblVid=new JLabel("VID");           lblModeOftrans=new JLabel("MODE OF TRANSPORT");
		lblCost=new JLabel("COST");         lblAvailability=new JLabel("AVAILABILITY");
		lblType=new JLabel("TYPE OF VEHICLE");
		pnl1=new JPanel();                  pnl2=new JPanel();
		txtAre=new JTextArea(20,100);
		btnInsert=new JButton("SUBMIT");
		setTitle("INSERT TYPE OF VEHICLE");
		pnl1.setBackground(Color.ORANGE);
		pnl2.setBackground(Color.green);
		pnl1.setLayout(new GridLayout(5,2));
		pnl1.add(lblVid);            pnl1.add(txtVid);
		pnl1.add(lblModeOftrans);    pnl1.add(txtModeOftrans);
		pnl1.add(lblCost);           pnl1.add(txtCost);
		pnl1.add(lblAvailability);   pnl1.add(txtAvailability);
		pnl1.add(lblType);           pnl1.add(txtType);
		pnl2.add(btnInsert);
		pnl2.add(txtAre);
		pnl1.setSize(600,500);
		pnl2.setSize(600,500);
	    add(pnl1);add(pnl2);
		setSize(2000,1000);
		setLayout(new GridLayout(2, 1));
		setVisible(true);
		btnInsert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
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
				 		  
				  String query= "INSERT INTO typeofvehicle VALUES( "+ txtVid.getText() + ", "  + "'" +txtModeOftrans.getText() + "'," + txtCost.getText() + ","  + txtAvailability.getText()  + "," + "'" + txtType.getText() + "'"+ " )";
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
