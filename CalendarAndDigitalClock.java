package EX;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class CalendarAndDigitalClock extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JLabel lblMonth, lblYear, lbltest;
	static JButton btnPrev, btnNext;
	static JTable tblCalendar;
	static JComboBox<String> cmbYear;
	static JFrame frmMain;
	static Container pane;
	static DefaultTableModel mtblCalendar; 
	static JScrollPane stblCalendar; 
	static JPanel pnlCalendar;
	static int realYear, realMonth, realDay, currentYear, currentMonth;

	public CalendarAndDigitalClock() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch (Exception e) {
	            System.out.println("Error occurred.");
	        }
	        
		
			//Define the frame
			frmMain = new JFrame ("Calendar with Digital Clock");
			frmMain.setSize(350, 500);

			//Define the pane
			pane = frmMain.getContentPane();
			pane.setLayout(null);
			frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			//Create controls for the calendar
			lblMonth = new JLabel ("January");
			lblYear = new JLabel ("Change year:");
			cmbYear = new JComboBox<String>();
			btnPrev = new JButton ("<");
			btnNext = new JButton (">");
			mtblCalendar = new DefaultTableModel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int rowIndex, int mColIndex) {
	                return false;
	            }
	        };
			tblCalendar = new JTable(mtblCalendar);
			stblCalendar = new JScrollPane(tblCalendar);
			pnlCalendar = new JPanel(null);
			pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));

			//Register action listeners
			btnPrev.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	                if (currentMonth == 0) {
	                    currentMonth = 11;
	                    currentYear -= 1;
	                } else {
	                    currentMonth -= 1;
	                }
	                refreshCalendar(currentMonth, currentYear);
	            }
	        });
			
			btnNext.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (currentMonth == 11) {
	                    currentMonth = 0;
	                    currentYear += 1;
	                } else {
	                    currentMonth += 1;
	                }
	                refreshCalendar(currentMonth, currentYear);
	            }
	        });
			
			cmbYear.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	                if (cmbYear.getSelectedItem() != null) {
	                    String b = cmbYear.getSelectedItem().toString();
	                    currentYear = Integer.parseInt(b);
	                    refreshCalendar(currentMonth, currentYear);
	                }
	            }
	        });

			//Add controls to pane
			pane.add(pnlCalendar);
			pnlCalendar.add(lblMonth);
			pnlCalendar.add(lblYear);
			pnlCalendar.add(cmbYear);
			pnlCalendar.add(btnPrev);
			pnlCalendar.add(btnNext);
			pnlCalendar.add(stblCalendar);

			//Set bounds
			pnlCalendar.setBounds(0, 0, 320, 335);
			lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
			lblYear.setBounds(10, 305, 80, 20);
			cmbYear.setBounds(230, 305, 80, 20);
			btnPrev.setBounds(10, 25, 50, 25);
			btnNext.setBounds(260, 25, 50, 25);
			stblCalendar.setBounds(10, 50, 300, 250);

			//Make the frame unresizable
			frmMain.setResizable(false);

			//Read the real day, month and year
			GregorianCalendar cal = new GregorianCalendar(); //Create calendar
			realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
			realMonth = cal.get(GregorianCalendar.MONTH); //Get month
			realYear = cal.get(GregorianCalendar.YEAR); //Get year
			currentMonth = realMonth; //Match month and year
			currentYear = realYear;

			//Create an array of days
			String[] headers = {"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"}; //All headers
			for (int i=0; i<7; i++){
				mtblCalendar.addColumn(headers[i]);
			}

			//Set background
			tblCalendar.getParent().setBackground(tblCalendar.getBackground()); 

			//Make the calendar header unchangable
			tblCalendar.getTableHeader().setResizingAllowed(false);
			tblCalendar.getTableHeader().setReorderingAllowed(false);

			//Set the cell selection
			tblCalendar.setColumnSelectionAllowed(true);
			tblCalendar.setRowSelectionAllowed(true);
			tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			//Set the row and column count
			tblCalendar.setRowHeight(38);
			mtblCalendar.setColumnCount(7);
			mtblCalendar.setRowCount(6);

			//Add the year values in the list
			for (int i=realYear-100; i<=realYear+100; i++){
				cmbYear.addItem(String.valueOf(i));
			}

			//Refresh the calendar
			refreshCalendar (realMonth, realYear); //Refresh calendar
			ClockLabel timeLable = new ClockLabel("time");
			timeLable.setBounds(0, 360, 360,50);
			frmMain.add(timeLable);
			frmMain.setVisible(true);
		}
	
	public static void refreshCalendar(int month, int year){
        //Create array of months
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month
        
        //Enable or disable the buttons
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= realYear-10){
        	btnPrev.setEnabled(false);
        } //Too early
        if (month == 11 && year >= realYear+100){
        	btnNext.setEnabled(false);
        } //Too late
        lblMonth.setText(months[month]); //Refresh the month label (at the top)
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
        
        //Clear table
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtblCalendar.setValueAt(null, i, j);
            }
        }
        
        //Read the first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        //Display the calendar
        for (int i=1; i<=nod; i++){
            int row = (i+som-2)/7;
            int column  =  (i+som-2)%7;
            mtblCalendar.setValueAt(String.valueOf(i), row, column);
        }
        
        //Add renderers
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
                super.getTableCellRendererComponent(table, value, selected, focused, row, column);
                if (column == 6) {
                    setBackground(new Color(255, 0, 0));
                } else {
                    setBackground(new Color(255, 255, 255));
                }
                if (value != null) {
                    if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear) {
                        setBackground(new Color(83, 230, 29));
                    }
                }
                setBorder(null);
                setForeground(Color.black);
                return this;
            }
        });
    }
}

class ClockLabel extends JLabel implements ActionListener {
    String type;
    SimpleDateFormat sdf;

    public ClockLabel(String type) {
        this.type = type;
        setForeground(Color.cyan);
        if ("date".equals(type)) {
            sdf = new SimpleDateFormat("  MMMM dd yyyy");
            setFont(new Font("sans-serif", Font.PLAIN, 12));
            setHorizontalAlignment(SwingConstants.LEFT);
        } else if ("time".equals(type)) {
            sdf = new SimpleDateFormat("hh:mm:ss a");
            setFont(new Font("sans-serif", Font.PLAIN, 40));
            setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            sdf = new SimpleDateFormat();
        }
        javax.swing.Timer t = new javax.swing.Timer(1000, this);
        t.start();
    }

    public void actionPerformed(ActionEvent ae) {
        Date d = new Date();
        setText(sdf.format(d));
    }
}