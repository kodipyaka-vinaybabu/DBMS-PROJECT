package transportmanagement;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class abc extends JFrame{
	private JMenuBar mBar;
	private JMenu mnuPassenger,mnuChecks,mnuReserves,mnuTypeofVehicle,mnuPayment;
	private JMenuItem mnuItemInsert,mnuItemInsert1,mnuItemInsert2,mnuItemInsert3,mnuItemInsert4;
	private JMenuItem mnuItemUpdate,mnuItemUpdate1,mnuItemUpdate2,mnuItemUpdate3,mnuItemUpdate4;
	private JMenuItem mnuItemDelete,mnuItemDelete1,mnuItemDelete2,mnuItemDelete3,mnuItemDelete4;
	abc(){
	mBar=new JMenuBar();
	mnuPassenger=new JMenu("passenger");
	mnuPassenger.setOpaque(true);
	mnuPassenger.setBackground(Color.blue);
	
	mnuChecks=new JMenu("checks"); mnuChecks.setOpaque(true);
	mnuChecks.setBackground(Color.green);
	
	mnuReserves=new JMenu("reserves"); mnuReserves.setOpaque(true);
	mnuReserves.setBackground(Color.MAGENTA);
	
	mnuTypeofVehicle=new JMenu("typeOfvehicle"); mnuTypeofVehicle.setOpaque(true);
	mnuTypeofVehicle.setBackground(Color.ORANGE);
	
	mnuPayment=new JMenu("payment"); mnuPayment.setOpaque(true);
	mnuPayment.setBackground(Color.PINK);
	
	mnuItemInsert=new JMenuItem("insert passenger");
	mnuItemInsert.setBackground(Color.yellow);
	mnuItemUpdate=new JMenuItem("update passenger");
	mnuItemUpdate.setBackground(Color.yellow);
	mnuItemDelete=new JMenuItem("delete passenger");
	mnuItemDelete.setBackground(Color.yellow);
	
	mnuItemInsert1=new JMenuItem("insert checks");
	mnuItemInsert1.setBackground(Color.orange);
	mnuItemUpdate1=new JMenuItem("update checks");
	mnuItemUpdate1.setBackground(Color.orange);
	mnuItemDelete1=new JMenuItem("delete checks");
	mnuItemDelete1.setBackground(Color.orange);
	
	mnuItemInsert2=new JMenuItem("insert reserves");
	mnuItemInsert2.setBackground(Color.pink);
	mnuItemUpdate2=new JMenuItem("update reserves");
	mnuItemUpdate2.setBackground(Color.pink);
    mnuItemDelete2=new JMenuItem("delete reserves");
    mnuItemDelete2.setBackground(Color.pink);
	
	mnuItemInsert3=new JMenuItem("insert typeofvehicle");
	mnuItemInsert3.setBackground(Color.green);
	mnuItemUpdate3=new JMenuItem("update typeofvehicle");
	mnuItemUpdate3.setBackground(Color.green);
	mnuItemDelete3=new JMenuItem("delete typeofvehicle");
	mnuItemDelete3.setBackground(Color.green);
	
	mnuItemInsert4=new JMenuItem("insert payment");
	mnuItemInsert4.setBackground(Color.MAGENTA);
	mnuItemUpdate4=new JMenuItem("update payment");
	mnuItemUpdate4.setBackground(Color.MAGENTA);
	mnuItemDelete4=new JMenuItem("delete payment");
	mnuItemDelete4.setBackground(Color.MAGENTA);
	
	mnuPassenger.add(mnuItemInsert);
	mnuPassenger.add(mnuItemUpdate);
	mnuPassenger.add(mnuItemDelete);
	
	mnuChecks.add(mnuItemInsert1);
	mnuChecks.add(mnuItemUpdate1);
	mnuChecks.add(mnuItemDelete1);
	
	mnuReserves.add(mnuItemInsert2);
	mnuReserves.add(mnuItemUpdate2);
	mnuReserves.add(mnuItemDelete2);
	
	mnuTypeofVehicle.add(mnuItemInsert3);
	mnuTypeofVehicle.add(mnuItemUpdate3);
	mnuTypeofVehicle.add(mnuItemDelete3);
	
	mnuPayment.add(mnuItemInsert4);
	mnuPayment.add(mnuItemUpdate4);
	mnuPayment.add(mnuItemDelete4);
	
	mBar.add(mnuPassenger);
	mBar.add(mnuChecks);
	mBar.add(mnuReserves);
	mBar.add(mnuTypeofVehicle);
	mBar.add(mnuPayment);
	setJMenuBar(mBar);
	setSize(2000,1000);
	setLayout(new GridBagLayout());
	JLabel lbl=new JLabel("TRANSPORT MANAGEMENT SYSTEM FOR INTRA CITY TRAVELS");	
	lbl.setFont(lbl.getFont().deriveFont(40.0f));
	add(lbl);
	getContentPane().setBackground(Color.cyan);
	setTitle("TRANSPORT MANAGEMENT SYSTEM FOR INTRA CITY TRAVELS");
	setVisible(true);
	mnuItemInsert.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			new Passenger();
			}
	});
	mnuItemUpdate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			new updatePassenger();
		}
	});
	mnuItemDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new DeletePassenger();
		}
	});
	mnuItemInsert1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			new insertchecks();
		}
	});
	mnuItemUpdate1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			new UpdateChecks();
		}
	});
	mnuItemDelete1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			new DeleteChecks();
		}
	});
	mnuItemInsert2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			new insertReserves();
		}
	});
	mnuItemUpdate2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			new updateReserves();
		}
	});
	mnuItemDelete2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			new DeleteReserves();
			
		}
	});
	mnuItemInsert3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			new insertTypeOfVehicle();
		}
	});
	mnuItemUpdate3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new updateTypeOfVehicle();
			
		}
	});
	mnuItemDelete3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new DeleteTypeOfVehicle();
		}
	});
	mnuItemInsert4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			new insertPayment();
		}
	});
	mnuItemUpdate4.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent ae) {
			new updatePayment();
		}
		
	});
	mnuItemDelete4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new DeletePayment();
			
		}
	});
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
public class FirstFrameGUI {
	public static void main(String[] args){
		new abc();
	}
}
