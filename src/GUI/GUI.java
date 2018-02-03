package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import filters.*;
import project.*;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	File folder;
	String path = "C:\\Users\\Rachel\\Downloads\\study\\OR";
	int numberOfCheckedButtons = 0;
	private JFrame frame;
	static Link L1 = new Link();
	static Link L2 = new Link();
	static Link L3 = new Link();
	JRadioButton TimeRadioButton = new JRadioButton("");
	JRadioButton DeviceRadioButton = new JRadioButton("");
	JRadioButton LocationRadioButton = new JRadioButton("");
	JButton SaveFilterButton = new JButton("Save Filter");
	JButton StartFiltrationButton = new JButton("Start Filtration");
	private TimeFilter time;
	private IdFilter id;
	private LocationFilter loc;
	private Filter save = null;
	private Filter open = null;
	private String TimeGetMin;
	private String TimeGetMax;
	private String DeviceGetInput;
	private String LocationGetMinLat;
	private String LocationGetMaxLat;
	private String LocationGetMinLon;
	private String LocationGetMaxLon;
	private String LocationGetMinAlt;
	private String LocationGetMaxAlt;
	private String Algo1GetMac;
	private int Algo1getnum;
	private String Algo2GetMac1;
	private String Algo2GetMac2;
	private String Algo2GetMac3;
	private String Algo2GetSignal1;
	private String Algo2GetSignal2;
	private String Algo2GetSignal3;
	private JTextField TimeMINtxt;
	private JTextField TimeMAXtxt;
	private JTextField Devicetxt;
	private JTextField LatMINtxt;
	private JTextField LatMAXtxt;
	private JTextField LonMINtxt;
	private JTextField LonMAXtxt;
	private JTextField AltMINtxt;
	private JTextField AltMAXtxt;
	private JTextField Algo1txt;
	private JTextField NumOfMacstxt;
	private JTextField AlgoLattxt;
	private JTextField AlgoLontxt;
	private JTextField AlgoAlttxt;
	private JTextField Algo2Sampletxt;
	private JTextField Mac1txt;
	private JTextField Mac2txt;
	private JTextField Mac3txt;
	private JTextField Signal1;
	private JTextField Signal2;
	private JTextField Signal3;
	static JButton ClearButton = new JButton("Clear Data");
	static JTextArea textArea = new JTextArea();
	static JButton SaveCSV = new JButton("Save ---> CSV");
	static JButton SaveKMLButton = new JButton("Save ---> KML");
	static JButton EnterSQLButton = new JButton("Enter SQL");

	
	/**
	 * Launch the application.
	 */


	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */

	/*
	private boolean CheckAlgo1(){
		boolean flag = false;
		if(NumOfMacstxt.getText().equals("") || AlgoAlttxt.getText().equals(""))
			JOptionPane.showMessageDialog(null, "Invalid Input");

		}

	 */
	public void copy(Link l1 ,Link l2 ){
		for(int i=0; i<l1.DataBase.Lines.size(); i++)
			l2.temp.Lines.add(new WifiScan(l1.DataBase.Lines.get(i)));
		l2.DataBase = l2.temp;
		l2.temp = new ReaderForCsv();
	}

	private void setEnabled(){
		if(TimeRadioButton.isSelected() && DeviceRadioButton.isSelected())
			LocationRadioButton.setEnabled(false);
		if(TimeRadioButton.isSelected() && LocationRadioButton.isSelected())
			DeviceRadioButton.setEnabled(false);
		if(DeviceRadioButton.isSelected() && LocationRadioButton.isSelected())
			TimeRadioButton.setEnabled(false);
	}


	private void setDisabled(){
		if(TimeRadioButton.isSelected()){
			DeviceRadioButton.setEnabled(true);
			LocationRadioButton.setEnabled(true);

		}
		if(DeviceRadioButton.isSelected()){
			TimeRadioButton.setEnabled(true);
			LocationRadioButton.setEnabled(true);

		}

		if(LocationRadioButton.isSelected())
			TimeRadioButton.setEnabled(true);
		DeviceRadioButton.setEnabled(true);
	}

	private void CheckboxSelected(){
		System.out.println(numberOfCheckedButtons);
		if(numberOfCheckedButtons==2)
			setEnabled();
	}

	private void CheckboxUnSelected(){
		System.out.println(numberOfCheckedButtons);
		if(numberOfCheckedButtons==1)
			setDisabled();

	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(UIManager.getColor("Button.background"));
		frame.setBounds(0, 454, 1015, 961);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); 
		
		
		
		
		SaveCSV.setBackground(new Color(211, 211, 211));
		ClearButton.setBackground(new Color(211, 211, 211));
		JButton AddDirButton = new JButton("Add Dir");
		AddDirButton.setBackground(new Color(211, 211, 211));
		JButton CSVButton = new JButton("Add CSV");
		CSVButton.setBackground(new Color(211, 211, 211));
		SaveKMLButton.setBackground(new Color(211, 211, 211));
		JButton TimeMinEnterButton = new JButton("Enter MIN");
		TimeMinEnterButton.setBackground(new Color(211, 211, 211));
		JButton TimeMaxEnterButton = new JButton("Enter MAX");
		TimeMaxEnterButton.setBackground(new Color(211, 211, 211));
		JCheckBox TimeNOTCheckBox = new JCheckBox("NOT");
		JButton DeviceEnterButton = new JButton("Enter");
		DeviceEnterButton.setBackground(new Color(211, 211, 211));
		JCheckBox DeviceNOTCheckBox = new JCheckBox("NOT");
		JButton LatEnterButton = new JButton("Enter");
		LatEnterButton.setBackground(new Color(211, 211, 211));
		JButton LonEnterButton = new JButton("Enter");
		LonEnterButton.setBackground(new Color(211, 211, 211));
		JButton AltEnterButton = new JButton("Enter");
		AltEnterButton.setBackground(new Color(211, 211, 211));
		JCheckBox LocationNOTCheckBox = new JCheckBox("NOT");
		JCheckBox AndCheckBox = new JCheckBox("AND");
		JCheckBox ORCheckBox = new JCheckBox("OR");
		JButton OpenFilterButton = new JButton("Open Filter");
		JButton CancelFilterButton = new JButton("Cancel Filter");
		JButton MacAddressButton = new JButton("Enter");
		MacAddressButton.setBackground(new Color(211, 211, 211));
		JButton Algo2EnterButton = new JButton("Enter");
		Algo2EnterButton.setBackground(new Color(211, 211, 211));
		JButton Algo2SampleEnterButton = new JButton("Enter");
		Algo2SampleEnterButton.setBackground(new Color(211, 211, 211));



		// Save CSV Button
		SaveCSV.setEnabled(false);
		SaveCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				L1.SaveCSV("FinalCSV");
				JOptionPane.showMessageDialog(null, "Operation Done !");
			}
		});
		SaveCSV.setBounds(12, 427, 128, 44);
		frame.getContentPane().add(SaveCSV);



		//Clear Button

		ClearButton.setEnabled(false);
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L1 = new Link();
				JOptionPane.showMessageDialog(null, "Operation Done !!!");
				ClearButton.setEnabled(false);
				AddDirButton.setEnabled(true);
				SaveCSV.setEnabled(false);
				EnterSQLButton.setEnabled(true);
				textArea.setText(null);

			}
		});
		ClearButton.setBounds(12, 370, 128, 44);
		frame.getContentPane().add(ClearButton);


		// Add directory 
		AddDirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Open dir = new Open();
				File wiggleDir = dir.PickFolder();
				if(wiggleDir == null)
					JOptionPane.showMessageDialog(null, "You Didn't choose a Directory ");
				else{
					L1.ReadDir(wiggleDir);
					if(!L1.DataBase.Lines.isEmpty()){
						ClearButton.setEnabled(true);
						SaveCSV.setEnabled(true);					
						SaveKMLButton.setEnabled(true);
						textArea.append("Path: " + wiggleDir.getPath() + "\n");
						textArea.append(" Data Size : "+ L1.DataBase.Lines.size() + "\n");
						textArea.append(" # of Mac : "+ MacCount(L1.DataBase.Lines) + "\n");

					}
					AddDirButton.setEnabled(false);
				}		   

			}

			

		});

		AddDirButton.setBounds(12, 256, 128, 44);
		frame.getContentPane().add(AddDirButton);



		//Add CSV Button
		CSVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Open file = new Open();
				File csv = file.PickCSVFile();
				if(csv == null)
					JOptionPane.showMessageDialog(null, "You Didn't choose a File ");
				else{
					if(L1.DataBase.Lines.size()==0)
						L1.ReadCSV(csv,false);
					else
						L1.ReadCSV(csv,true);
					SaveCSV.setEnabled(true);					
					ClearButton.setEnabled(true);
					textArea.setText(null);
					textArea.append(" Data Size : "+ L1.DataBase.Lines.size() + "\n");
					textArea.append(" # of Mac : "+ MacCount(L1.DataBase.Lines));
				}

			}
		});
		CSVButton.setBounds(12, 313, 128, 44);
		frame.getContentPane().add(CSVButton);
		@SuppressWarnings("unused")
		Border border = BorderFactory.createLineBorder(Color.BLACK);

		// SaveKML Button

		SaveKMLButton.setEnabled(false);
		SaveKMLButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				L1.SaveKML();
				JOptionPane.showMessageDialog(null, "Operation Done !");

			}
		});
		SaveKMLButton.setBounds(12, 484, 128, 44);
		frame.getContentPane().add(SaveKMLButton);



		//TimeRadioButton
		TimeRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TimeRadioButton.isSelected()==true){
					TimeMINtxt.setEnabled(true);
					TimeMAXtxt.setEnabled(true);
					TimeNOTCheckBox.setEnabled(true);
					numberOfCheckedButtons++;
					CheckboxSelected();
				}
				else if(TimeRadioButton.isSelected()==false){
					TimeMINtxt.setEnabled(false);
					TimeMAXtxt.setEnabled(false);
					TimeMaxEnterButton.setEnabled(false);
					TimeMinEnterButton.setEnabled(false);
					TimeMINtxt.setText("hh:mm:ss");
					TimeMAXtxt.setText("hh:mm:ss");
					TimeNOTCheckBox.setEnabled(false);
					TimeNOTCheckBox.setSelected(false);
					numberOfCheckedButtons--;
					CheckboxUnSelected();

				}


			}
		});
		TimeRadioButton.setBackground(new Color(255, 255, 255));
		TimeRadioButton.setBounds(232, 256, 25, 25);
		frame.getContentPane().add(TimeRadioButton);

		// DeviceRadioButton

		DeviceRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DeviceRadioButton.isSelected()==true){
					Devicetxt.setEnabled(true);
					DeviceNOTCheckBox.setEnabled(true);
					numberOfCheckedButtons++;
					CheckboxSelected();
				}
				else if(DeviceRadioButton.isSelected()==false){
					Devicetxt.setEnabled(false);
					Devicetxt.setText("Name");
					DeviceEnterButton.setEnabled(false);
					DeviceNOTCheckBox.setEnabled(false);
					DeviceNOTCheckBox.setSelected(false);
					numberOfCheckedButtons--;
					CheckboxUnSelected();
				}

			}

		});
		DeviceRadioButton.setBackground(Color.WHITE);
		DeviceRadioButton.setBounds(244, 402, 25, 25);
		frame.getContentPane().add(DeviceRadioButton);




		//LocationRadioButton
		LocationRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LocationRadioButton.isSelected()==true){
					LatMINtxt.setEnabled(true);
					LatMAXtxt.setEnabled(true);
					LonMINtxt.setEnabled(true);
					LonMAXtxt.setEnabled(true);
					AltMINtxt.setEnabled(true);
					AltMAXtxt.setEnabled(true);
					LocationNOTCheckBox.setEnabled(true);
					numberOfCheckedButtons++;
					CheckboxSelected();
				}
				else{
					LatMINtxt.setEnabled(false);
					LatMAXtxt.setEnabled(false);
					LonMINtxt.setEnabled(false);
					LonMAXtxt.setEnabled(false);
					AltMINtxt.setEnabled(false);
					AltMAXtxt.setEnabled(false);
					LatMINtxt.setText("XX.XXX");
					LatMAXtxt.setText("YY.YYY");
					LonMINtxt.setText("XX.XXX");
					LonMAXtxt.setText("YY.YYY");
					AltMINtxt.setText("XX.XXX");
					AltMAXtxt.setText("YY.YYY");
					LatEnterButton.setEnabled(false);
					LonEnterButton.setEnabled(false);
					AltEnterButton.setEnabled(false);
					LocationNOTCheckBox.setEnabled(false);
					LocationNOTCheckBox.setSelected(false);
					numberOfCheckedButtons--;
					CheckboxUnSelected();
				}
			}
		});
		LocationRadioButton.setBackground(Color.WHITE);
		LocationRadioButton.setBounds(257, 515, 25, 25);
		frame.getContentPane().add(LocationRadioButton);


		// TimeMinEnterButton

		TimeMinEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeGetMin = TimeMINtxt.getText();

			}
		});
		TimeMinEnterButton.setEnabled(false);
		TimeMinEnterButton.setBounds(343, 289, 103, 25);
		frame.getContentPane().add(TimeMinEnterButton);

		// TimeMaxEnterButton
		TimeMaxEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeGetMax = TimeMAXtxt.getText();
				time = new TimeFilter(TimeGetMin,TimeGetMax);


			}
		});
		TimeMaxEnterButton.setEnabled(false);
		TimeMaxEnterButton.setBounds(343, 323, 103, 25);
		frame.getContentPane().add(TimeMaxEnterButton);


		//TimeNOTCheckBOX

		TimeNOTCheckBox.setBounds(232, 356, 59, 25);
		TimeNOTCheckBox.setEnabled(false);
		frame.getContentPane().add(TimeNOTCheckBox);

		//DeviceEnterButton

		DeviceEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeviceGetInput = Devicetxt.getText();
				id = new IdFilter(DeviceGetInput);
			}
		});
		DeviceEnterButton.setEnabled(false);
		DeviceEnterButton.setBounds(343, 437, 103, 25);
		frame.getContentPane().add(DeviceEnterButton);

		//DeviceNOTCheckBox
		DeviceNOTCheckBox.setEnabled(false);
		DeviceNOTCheckBox.setBounds(232, 470, 59, 25);
		frame.getContentPane().add(DeviceNOTCheckBox);


		//LatEnterButton
		LatEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationGetMinLat = LatMINtxt.getText();
				LocationGetMaxLat = LatMAXtxt.getText(); 
			}
		});
		LatEnterButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LatEnterButton.setEnabled(false);
		LatEnterButton.setBounds(232, 615, 65, 25);
		frame.getContentPane().add(LatEnterButton);

		//LonEnterButton
		LonEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationGetMinLon = LonMINtxt.getText();
				LocationGetMaxLon = LonMAXtxt.getText();
			}
		});
		LonEnterButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LonEnterButton.setEnabled(false);
		LonEnterButton.setBounds(361, 615, 65, 25);
		frame.getContentPane().add(LonEnterButton);

		//AltEnterButton
		AltEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocationGetMinAlt = AltMINtxt.getText();
				LocationGetMaxAlt = AltMAXtxt.getText();
				
				Double LonMAX = Double.parseDouble(LocationGetMaxLon);
				Double LonMIN = Double.parseDouble(LocationGetMinLon);
				Double LatMAX = Double.parseDouble(LocationGetMaxLat);
				Double LatMIN = Double.parseDouble(LocationGetMinLat);
				Double AltMAX = Double.parseDouble(LocationGetMaxAlt);
				Double AltMIN = Double.parseDouble(LocationGetMinAlt);
				
				Point3D p = new Point3D(LonMIN+((LonMAX-LonMIN)/2), LatMIN+((LatMAX-LatMIN)/2), AltMIN+((AltMAX-AltMIN)/2));
				loc = new LocationFilter(p.latitude, p.longtitude);
			}
		});
		AltEnterButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		AltEnterButton.setEnabled(false);
		AltEnterButton.setBounds(489, 615, 65, 25);
		frame.getContentPane().add(AltEnterButton);

		//LocationNOTCheckBox
		LocationNOTCheckBox.setEnabled(false);
		LocationNOTCheckBox.setBounds(232, 649, 59, 25);
		frame.getContentPane().add(LocationNOTCheckBox);
		StartFiltrationButton.setBackground(new Color(211, 211, 211));


		//StartFiltrationButton
		StartFiltrationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy(L1,L2);
				SaveFilterButton.setEnabled(true);
				boolean flag = true;
				Writer w = new Writer(); 
				Not_Filter not1;
				Not_Filter not2;
				And_Filter and = null;
				Or_Filter or = null;
				@SuppressWarnings("unused")
				Filter save = null;
				
				if(open != null){
					w.SortbyFilter(L2.DataBase.Lines, open, "FilteredCSV");
					System.out.println("saveeeee");
					open = null;
					flag = false;
				}

				if(AndCheckBox.isSelected() && flag){ // and selected 
					if(TimeRadioButton.isSelected() && DeviceRadioButton.isSelected()){ // if time & device selected
						if(TimeNOTCheckBox.isSelected() && DeviceNOTCheckBox.isSelected()){ // not selected both
							not1 = new Not_Filter(time);
							not2 = new Not_Filter(id);
							and = new And_Filter(not1, not2);
							w.SortbyFilter(L2.DataBase.Lines, and, "FilteredCSV");
							textArea.append("Filter: (!(Time(" +TimeGetMin+"<Data<"+TimeGetMax+"))&&(!(Device("+DeviceGetInput+"))))"+"\n") ;
						}
						if(TimeNOTCheckBox.isSelected() && !DeviceNOTCheckBox.isSelected()){ 
							not1 = new Not_Filter(time);
							and = new And_Filter(not1, id);
							w.SortbyFilter(L2.DataBase.Lines, and, "FilteredCSV");
							textArea.append("Filter: (!(Time(" +TimeGetMin+"<Data<"+TimeGetMax+"))&&(Device("+DeviceGetInput+")))"+"\n") ;

						}

						if(!TimeNOTCheckBox.isSelected() && DeviceNOTCheckBox.isSelected()){
							not1 = new Not_Filter(id);
							and = new And_Filter(not1, time);
							w.SortbyFilter(L2.DataBase.Lines, and, "FilteredCSV");
							textArea.append("Filter: (Time(" +TimeGetMin+"<Data<"+TimeGetMax+")&&(!(Device("+DeviceGetInput+"))))"+"\n") ;

						}

					}

					if(TimeRadioButton.isSelected() && LocationRadioButton.isSelected()){
						if(TimeNOTCheckBox.isSelected() && DeviceNOTCheckBox.isSelected()){ // not selected both
							not1 = new Not_Filter(time);
							not2 = new Not_Filter(loc);
							and = new And_Filter(not1, not2);
							w.SortbyFilter(L2.DataBase.Lines, and, "FilteredCSV");
							textArea.append("Filter: (!(Time(" +TimeGetMin+"<Data<"+TimeGetMax+")) && (!(Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+"))))"+"\n") ;

						}
						if(TimeNOTCheckBox.isSelected() && !LocationNOTCheckBox.isSelected()){ 
							not1 = new Not_Filter(time);
							and = new And_Filter(not1, loc);
							w.SortbyFilter(L2.DataBase.Lines, and, "FilteredCSV");
							textArea.append("Filter: (!(Time(" +TimeGetMin+"<Data<"+TimeGetMax+") && (Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+")))"+"\n") ;

						}

						if(!TimeNOTCheckBox.isSelected() && LocationNOTCheckBox.isSelected()){
							not1 = new Not_Filter(loc);
							and = new And_Filter(not1, time);
							w.SortbyFilter(L2.DataBase.Lines, and, "FilteredCSV");
							textArea.append("Filter: (Time(" +TimeGetMin+"<Data<"+TimeGetMax+") && (!(Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+"))))"+"\n") ;

						}
					}
					if(DeviceRadioButton.isSelected() && LocationRadioButton.isSelected()){
						if(DeviceNOTCheckBox.isSelected() && LocationNOTCheckBox.isSelected()){ // not selected both
							not1 = new Not_Filter(id);
							not2 = new Not_Filter(loc);
							and = new And_Filter(not1, not2);
							w.SortbyFilter(L2.DataBase.Lines, and, "FilteredCSV");
							textArea.append("Filter: (!(Device("+DeviceGetInput+")) && (!(Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+"))))"+"\n") ;

						}
						if(DeviceNOTCheckBox.isSelected() && !LocationNOTCheckBox.isSelected()){ 
							not1 = new Not_Filter(id);
							and = new And_Filter(not1, loc);
							w.SortbyFilter(L2.DataBase.Lines, and, "FilteredCSV");
							textArea.append("Filter: (!(Device("+DeviceGetInput+")) && (Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+")))"+"\n") ;

						}

						if(!DeviceNOTCheckBox.isSelected() && LocationNOTCheckBox.isSelected()){
							not1 = new Not_Filter(loc);
							and = new And_Filter(not1, id);
							w.SortbyFilter(L2.DataBase.Lines, and, "FilteredCSV");
							textArea.append("Filter: (Device("+DeviceGetInput+") && (!(Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+"))))"+"\n") ;

						}

					}

					save = and;


				}


				if(ORCheckBox.isSelected() && flag){ // or selected
					if(TimeRadioButton.isSelected() && DeviceRadioButton.isSelected()){ 
						if(TimeNOTCheckBox.isSelected() && DeviceNOTCheckBox.isSelected()){ // not selected both
							not1 = new Not_Filter(time);
							not2 = new Not_Filter(id);
							or = new Or_Filter(not1, not2);
							w.SortbyFilter(L2.DataBase.Lines, or, "FilteredCSV");
							textArea.append("Filter: (!(Time(" +TimeGetMin+"<Data<"+TimeGetMax+")) || (!(Device("+DeviceGetInput+"))))"+"\n") ;

						}
						if(TimeNOTCheckBox.isSelected() && !DeviceNOTCheckBox.isSelected()){ 
							not1 = new Not_Filter(time);
							or = new Or_Filter(not1, id);
							w.SortbyFilter(L2.DataBase.Lines, or, "FilteredCSV");
							textArea.append("Filter: (!(Time(" +TimeGetMin+"<Data<"+TimeGetMax+")) || (Device("+DeviceGetInput+")))"+"\n") ;

						}

						if(!TimeNOTCheckBox.isSelected() && DeviceNOTCheckBox.isSelected()){
							not1 = new Not_Filter(id);
							or = new Or_Filter(not1, time);
							w.SortbyFilter(L2.DataBase.Lines, or, "FilteredCSV");
							textArea.append("Filter: (Time(" +TimeGetMin+"<Data<"+TimeGetMax+") || (!(Device("+DeviceGetInput+"))))"+"\n") ;

						}

					}
					if(TimeRadioButton.isSelected() && LocationRadioButton.isSelected()){
						if(TimeNOTCheckBox.isSelected() && DeviceNOTCheckBox.isSelected()){ // not selected both
							not1 = new Not_Filter(time);
							not2 = new Not_Filter(loc);
							or = new Or_Filter(not1, not2);
							w.SortbyFilter(L2.DataBase.Lines, or, "FilteredCSV");
							textArea.append("Filter: (!(Time(" +TimeGetMin+"<Data<"+TimeGetMax+")) || (!(Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+"))))"+"\n") ;

						}
						if(TimeNOTCheckBox.isSelected() && !LocationNOTCheckBox.isSelected()){ 
							not1 = new Not_Filter(time);
							or = new Or_Filter(not1, loc);
							w.SortbyFilter(L2.DataBase.Lines, or, "FilteredCSV");
							textArea.append("Filter: (!(Time(" +TimeGetMin+"<Data<"+TimeGetMax+") || (Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+")))"+"\n") ;

						}

						if(!TimeNOTCheckBox.isSelected() && LocationNOTCheckBox.isSelected()){
							not1 = new Not_Filter(loc);
							or = new Or_Filter(not1, time);
							w.SortbyFilter(L2.DataBase.Lines, or, "FilteredCSV");
							textArea.append("Filter: (Time(" +TimeGetMin+"<Data<"+TimeGetMax+") || (!(Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+"))))"+"\n") ;

						}
					}

					if(DeviceRadioButton.isSelected() && LocationRadioButton.isSelected()){
						if(DeviceNOTCheckBox.isSelected() && LocationNOTCheckBox.isSelected()){ // not selected both
							not1 = new Not_Filter(id);
							not2 = new Not_Filter(loc);
							or = new Or_Filter(not1, not2);
							w.SortbyFilter(L2.DataBase.Lines, or, "FilteredCSV");
							textArea.append("Filter: (!(Device("+DeviceGetInput+")) || (!(Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+"))))"+"\n") ;

						}
						if(DeviceNOTCheckBox.isSelected() && !LocationNOTCheckBox.isSelected()){ 
							not1 = new Not_Filter(id);
							or = new Or_Filter(not1, loc);
							w.SortbyFilter(L2.DataBase.Lines, or, "FilteredCSV");
							textArea.append("Filter: (!(Device("+DeviceGetInput+")) || (Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+")))"+"\n") ;

						}

						if(!DeviceNOTCheckBox.isSelected() && LocationNOTCheckBox.isSelected()){
							not1 = new Not_Filter(loc);
							or = new Or_Filter(not1, id);
							w.SortbyFilter(L2.DataBase.Lines, or, "FilteredCSV");
							textArea.append("Filter: (Device("+DeviceGetInput+") || (!(Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+"))))"+"\n") ;

						}

					}

					save = or;

				}

				if(TimeRadioButton.isSelected() && !LocationRadioButton.isSelected() && !DeviceRadioButton.isSelected() && flag){
					if(TimeNOTCheckBox.isSelected()){
						not1 = new Not_Filter(time);
						w.SortbyFilter(L2.DataBase.Lines, not1, "FilteredCSV");
						save = not1;
						textArea.append("Filter: (!(Time(" +TimeGetMin+"<Data<"+TimeGetMax+")))"+"\n");
					}

					else{
						w.SortbyFilter(L2.DataBase.Lines, time, "FilteredCSV");
						save = time;
						textArea.append("Filter: (Time(" +TimeGetMin+"<Data<"+TimeGetMax+"))"+"\n");
					}
				}

				if(DeviceRadioButton.isSelected() && !TimeRadioButton.isSelected() && !LocationRadioButton.isSelected() && flag){
					if(DeviceNOTCheckBox.isSelected()){
						not1 = new Not_Filter(id);
						w.SortbyFilter(L2.DataBase.Lines, not1, "FilteredCSV");
						save = not1;
						textArea.append("Filter: (!(Device("+DeviceGetInput+")))"+"\n") ;

					}
					else{
						w.SortbyFilter(L2.DataBase.Lines, id, "FilteredCSV");
						save = id;
						textArea.append("Filter: (Device("+DeviceGetInput+"))"+"\n") ;

					}
				}
				if(LocationRadioButton.isSelected() && !TimeRadioButton.isSelected() && !DeviceRadioButton.isSelected() && flag){
					if(LocationNOTCheckBox.isSelected()){
						not1 = new Not_Filter(loc);
						w.SortbyFilter(L2.DataBase.Lines, not1, "FilteredCSV");
						save = not1;
						textArea.append("Filter: (!(Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+")))"+"\n") ;

					}

					else{
						w.SortbyFilter(L2.DataBase.Lines, loc, "FilteredCSV");
					save = loc;
					textArea.append("Filter: (Location("+LocationGetMinLat+"<Data<"+LocationGetMaxLat+"&"+LocationGetMinLon+"<data<"+LocationGetMaxLon+"&"+LocationGetMinAlt+"<data<"+LocationGetMaxAlt+"))"+"\n") ;
					}
				}

				JOptionPane.showMessageDialog(null, "Operation Done !!!");
				L2 = new Link();
				try {
					WatchSrc folderWatch = new WatchSrc(path);
					Thread watchThread = new Thread(folderWatch);
					watchThread.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}


		});
		StartFiltrationButton.setBounds(191, 737, 115, 44);
		frame.getContentPane().add(StartFiltrationButton);


		//SaveFilterButton
		SaveFilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReadSaveFilter rd = new ReadSaveFilter();
				rd.SaveFilter(save);
				JOptionPane.showMessageDialog(null, "Operation Done !!!");				
			}
		});

		SaveFilterButton.setEnabled(false);
		SaveFilterButton.setBounds(318, 737, 115, 44);
		SaveFilterButton.setBackground(new Color(211, 211, 211));
		frame.getContentPane().add(SaveFilterButton);


		//OpenFilterButton
		OpenFilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReadSaveFilter rd = new ReadSaveFilter();
				open = rd.ReadFilter();
				JOptionPane.showMessageDialog(null, "Operation Done !!!");
			}
		});
		OpenFilterButton.setBackground(new Color(211, 211, 211));
		OpenFilterButton.setBounds(318, 789, 115, 44);
		frame.getContentPane().add(OpenFilterButton);


		//CancelFilterButton
		CancelFilterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L2.DataBase = new ReaderForCsv();;
				TimeRadioButton.setSelected(false);
				TimeMINtxt.setEnabled(false);
				TimeMAXtxt.setEnabled(false);
				TimeMaxEnterButton.setEnabled(false);
				TimeMinEnterButton.setEnabled(false);
				TimeMINtxt.setText("hh:mm:ss");
				TimeMAXtxt.setText("hh:mm:ss");
				TimeNOTCheckBox.setEnabled(false);
				TimeNOTCheckBox.setSelected(false);
				//
				DeviceRadioButton.setSelected(false);
				Devicetxt.setEnabled(false);
				Devicetxt.setText("Name");
				DeviceEnterButton.setEnabled(false);
				DeviceNOTCheckBox.setEnabled(false);
				DeviceNOTCheckBox.setSelected(false);
				//
				LocationRadioButton.setSelected(false);
				LatMINtxt.setEnabled(false);
				LatMAXtxt.setEnabled(false);
				LonMINtxt.setEnabled(false);
				LonMAXtxt.setEnabled(false);
				AltMINtxt.setEnabled(false);
				AltMAXtxt.setEnabled(false);
				LatMINtxt.setText("XX.XXX");
				LatMAXtxt.setText("YY.YYY");
				LonMINtxt.setText("XX.XXX");
				LonMAXtxt.setText("YY.YYY");
				AltMINtxt.setText("XX.XXX");
				AltMAXtxt.setText("YY.YYY");
			}
		});

		CancelFilterButton.setBackground(new Color(211, 211, 211));
		CancelFilterButton.setBounds(191, 789, 115, 44);
		frame.getContentPane().add(CancelFilterButton);


		//AndCheckBox
		AndCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AndCheckBox.isSelected())
					ORCheckBox.setEnabled(false);
				else
					ORCheckBox.setEnabled(true);
			}
		});
		AndCheckBox.setBounds(191, 703, 59, 25);
		frame.getContentPane().add(AndCheckBox);


		//ORCheckBox
		ORCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ORCheckBox.isSelected())
					AndCheckBox.setEnabled(false);
				else
					AndCheckBox.setEnabled(true);
			}
		});
		ORCheckBox.setBounds(247, 703, 59, 25);
		frame.getContentPane().add(ORCheckBox);


		//MacAddresButton
		MacAddressButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AlgoAlttxt.getText().equals("MAC Addredd") || NumOfMacstxt.getText().equals("Num. of Macs"))
					JOptionPane.showMessageDialog(null, "Invald Input !");
				else{
					copy(L1, L3);
					Algo1GetMac = Algo1txt.getText();
					Algo1getnum = Integer.parseInt(NumOfMacstxt.getText());
					String[] str = L3.Algo1(Algo1GetMac, Algo1getnum);
					AlgoLattxt.setText(str[0]);
					AlgoLontxt.setText(str[1]);
					AlgoAlttxt.setText(str[2]);
					L3 = new Link();
				}
			}
		});

		MacAddressButton.setEnabled(true);
		MacAddressButton.setBounds(851, 310, 103, 30);
		frame.getContentPane().add(MacAddressButton);


		//Algo2EnterButton
		Algo2EnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy(L1, L3);
				String[] str = {"","","","","",""};
				Algo2GetMac1 = Mac1txt.getText();
				Algo2GetMac2 = Mac2txt.getText();
				Algo2GetMac3 = Mac3txt.getText();
				Algo2GetSignal1 = Signal1.getText();
				Algo2GetSignal2 = Signal2.getText();
				Algo2GetSignal3 = Signal3.getText();
				str[0] = Algo2GetMac1;
				str[1] = Algo2GetSignal1;
				str[2] = Algo2GetMac2;
				str[3] = Algo2GetSignal2;
				str[4] = Algo2GetMac3;
				str[5] = Algo2GetSignal3;
				String[] str2 = L3.Algo2Pairs(str);
				AlgoLattxt.setText(str2[0]);
				AlgoLontxt.setText(str2[1]);
				AlgoAlttxt.setText(str2[2]);
				L3 = new Link();
			}
		});
		Algo2EnterButton.setEnabled(true);
		Algo2EnterButton.setBounds(851, 563, 103, 30);
		frame.getContentPane().add(Algo2EnterButton);

		//Algo2SampleEnterButton
		Algo2SampleEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copy(L1, L3);
				String[] str = Algo2Sampletxt.getText().split(",");
				str = L3.Algo2Sample(str);
				AlgoLattxt.setText(str[0]);
				AlgoLontxt.setText(str[1]);
				AlgoAlttxt.setText(str[2]);
				L3 = new Link();
			}
		});

		
		Algo2SampleEnterButton.setEnabled(true);
		Algo2SampleEnterButton.setBounds(715, 414, 103, 31);
		frame.getContentPane().add(Algo2SampleEnterButton);

		//EnterSQLButton
		EnterSQLButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login s = new Login();
				s.setVisible(true);
				
			}
		});
		EnterSQLButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		EnterSQLButton.setBackground(new Color(211, 211, 211));
		EnterSQLButton.setBounds(12, 541, 128, 44);
		frame.getContentPane().add(EnterSQLButton);

		
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(255, 255, 255));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(170, 189, 9, 686);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 0));
		separator_1.setForeground(new Color(255, 255, 255));
		separator_1.setBounds(182, 231, 385, 2);
		frame.getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.WHITE);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(579, 189, 9, 686);
		frame.getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 0, 0));
		separator_3.setForeground(new Color(255, 255, 255));
		separator_3.setBounds(12, 231, 146, 2);
		frame.getContentPane().add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setForeground(Color.WHITE);
		separator_4.setBackground(Color.BLACK);
		separator_4.setBounds(306, 552, 9, 88);
		frame.getContentPane().add(separator_4);

		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilters.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFilters.setBounds(317, 177, 91, 64);
		frame.getContentPane().add(lblFilters);

		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTime.setBounds(191, 245, 91, 44);
		frame.getContentPane().add(lblTime);

		JLabel lblDevice = new JLabel("Device");
		lblDevice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDevice.setBounds(191, 390, 59, 44);
		frame.getContentPane().add(lblDevice);

		JLabel lblStart = new JLabel("Start");
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblStart.setBounds(29, 194, 80, 30);
		frame.getContentPane().add(lblStart);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLocation.setBounds(191, 504, 71, 44);
		frame.getContentPane().add(lblLocation);


		TimeMINtxt = new JTextField();
		TimeMINtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(!(TimeMINtxt.getText().isEmpty()))
					TimeMinEnterButton.setEnabled(true);
				else
					TimeMinEnterButton.setEnabled(false);

			}
		});
		TimeMINtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(TimeRadioButton.isSelected())
					TimeMINtxt.setText("");


			}
		});
		TimeMINtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TimeMINtxt.setEnabled(false);
		TimeMINtxt.setText("hh:mm:ss");
		TimeMINtxt.setBounds(232, 288, 91, 25);
		frame.getContentPane().add(TimeMINtxt);
		TimeMINtxt.setColumns(10);



		TimeMAXtxt = new JTextField();
		TimeMAXtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(TimeMAXtxt.getText().isEmpty()))
					TimeMaxEnterButton.setEnabled(true);
				else
					TimeMaxEnterButton.setEnabled(false);
			}
		});
		TimeMAXtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(TimeRadioButton.isSelected())
					TimeMAXtxt.setText("");
			}
		});
		TimeMAXtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TimeMAXtxt.setEnabled(false);
		TimeMAXtxt.setText("hh:mm:ss");
		TimeMAXtxt.setBounds(232, 322, 91, 25);
		frame.getContentPane().add(TimeMAXtxt);
		TimeMAXtxt.setColumns(10);

		Devicetxt = new JTextField();
		Devicetxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(Devicetxt.getText().isEmpty()))
					DeviceEnterButton.setEnabled(true);
				else
					DeviceEnterButton.setEnabled(false);
			}
		});
		Devicetxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(DeviceRadioButton.isSelected())
					Devicetxt.setText("");
			}
		});
		Devicetxt.setText("Name");
		Devicetxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Devicetxt.setEnabled(false);
		Devicetxt.setColumns(10);
		Devicetxt.setBounds(232, 436, 91, 25);
		frame.getContentPane().add(Devicetxt);



		LatMINtxt = new JTextField();
		LatMINtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(LocationRadioButton.isSelected())
					LatMINtxt.setText("");
			}
		});
		LatMINtxt.setText("XX.XXX");
		LatMINtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LatMINtxt.setEnabled(false);
		LatMINtxt.setColumns(10);
		LatMINtxt.setBounds(232, 552, 65, 25);
		frame.getContentPane().add(LatMINtxt);

		JLabel lblLat = new JLabel("Lat :");
		lblLat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLat.setBounds(191, 556, 39, 16);
		frame.getContentPane().add(lblLat);

		LatMAXtxt = new JTextField();
		LatMAXtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(LatMAXtxt.getText().isEmpty()))
					LatEnterButton.setEnabled(true);
				else
					LatEnterButton.setEnabled(false);
			}
		});
		LatMAXtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(LocationRadioButton.isSelected())
					LatMAXtxt.setText("");
			}
		});
		LatMAXtxt.setText("YY.YYY");
		LatMAXtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LatMAXtxt.setEnabled(false);
		LatMAXtxt.setColumns(10);
		LatMAXtxt.setBounds(232, 584, 65, 25);
		frame.getContentPane().add(LatMAXtxt);

		JLabel lblLon = new JLabel("Lon :");
		lblLon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLon.setBounds(317, 556, 39, 16);
		frame.getContentPane().add(lblLon);

		LonMINtxt = new JTextField();
		LonMINtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(LocationRadioButton.isSelected())
					LonMINtxt.setText("");
			}
		});
		LonMINtxt.setText("XX.XXX");
		LonMINtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LonMINtxt.setEnabled(false);
		LonMINtxt.setColumns(10);
		LonMINtxt.setBounds(361, 552, 65, 25);
		frame.getContentPane().add(LonMINtxt);

		LonMAXtxt = new JTextField();
		LonMAXtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(LonMAXtxt.getText().isEmpty()))
					LonEnterButton.setEnabled(true);
				else
					LonEnterButton.setEnabled(false);
			}
		});
		LonMAXtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(LocationRadioButton.isSelected())
					LonMAXtxt.setText("");
			}
		});
		LonMAXtxt.setText("YY.YYY");
		LonMAXtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LonMAXtxt.setEnabled(false);
		LonMAXtxt.setColumns(10);
		LonMAXtxt.setBounds(361, 584, 65, 25);
		frame.getContentPane().add(LonMAXtxt);

		AltMINtxt = new JTextField();
		AltMINtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(LocationRadioButton.isSelected())
					AltMINtxt.setText("");
			}
		});
		AltMINtxt.setText("XX.XXX");
		AltMINtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AltMINtxt.setEnabled(false);
		AltMINtxt.setColumns(10);
		AltMINtxt.setBounds(489, 552, 65, 25);
		frame.getContentPane().add(AltMINtxt);

		AltMAXtxt = new JTextField();
		AltMAXtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!(AltMAXtxt.getText().isEmpty()))
					AltEnterButton.setEnabled(true);
				else
					AltEnterButton.setEnabled(false);
			}
		});
		AltMAXtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(LocationRadioButton.isSelected())
					AltMAXtxt.setText("");
			}
		});
		AltMAXtxt.setText("YY.YYY");
		AltMAXtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AltMAXtxt.setEnabled(false);
		AltMAXtxt.setColumns(10);
		AltMAXtxt.setBounds(489, 584, 65, 25);
		frame.getContentPane().add(AltMAXtxt);


		Algo1txt = new JTextField();
		Algo1txt.setHorizontalAlignment(SwingConstants.CENTER);
		Algo1txt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Algo1txt.setText("");
			}
		});

		Algo1txt.setText("MAC Address");
		Algo1txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Algo1txt.setEnabled(true);
		Algo1txt.setColumns(10);
		Algo1txt.setBounds(600, 310, 131, 30);
		frame.getContentPane().add(Algo1txt);


		AlgoLattxt = new JTextField();
		AlgoLattxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AlgoLattxt.setBounds(794, 694, 181, 41);
		frame.getContentPane().add(AlgoLattxt);
		AlgoLattxt.setColumns(10);

		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setForeground(Color.WHITE);
		separator_5.setBackground(Color.BLACK);
		separator_5.setBounds(437, 552, 9, 88);
		frame.getContentPane().add(separator_5);

		JLabel lblAlt = new JLabel("Alt :");
		lblAlt.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAlt.setBounds(448, 557, 39, 16);
		frame.getContentPane().add(lblAlt);

		JLabel lblAlgorithms = new JLabel("Algorithms");
		lblAlgorithms.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlgorithms.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAlgorithms.setBounds(714, 177, 115, 64);
		frame.getContentPane().add(lblAlgorithms);

		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(new Color(0, 0, 0));
		separator_6.setBounds(592, 231, 393, 2);
		frame.getContentPane().add(separator_6);

		JLabel lblAlgorithm = new JLabel("Algorithm 1");
		lblAlgorithm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAlgorithm.setBounds(600, 256, 96, 44);
		frame.getContentPane().add(lblAlgorithm);

		JLabel lblCalculatedPoint = new JLabel("Calculated Point :");
		lblCalculatedPoint.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCalculatedPoint.setBounds(600, 692, 131, 44);
		frame.getContentPane().add(lblCalculatedPoint);

		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.BLACK);
		separator_7.setBounds(600, 356, 385, 13);
		frame.getContentPane().add(separator_7);

		NumOfMacstxt = new JTextField();
		NumOfMacstxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NumOfMacstxt.setText("");
			}
		});
		NumOfMacstxt.setText("Num. of Macs");
		NumOfMacstxt.setBounds(748, 310, 91, 30);
		frame.getContentPane().add(NumOfMacstxt);
		NumOfMacstxt.setColumns(10);

		JLabel label = new JLabel("Alt :");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(743, 822, 39, 16);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("Lat :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(743, 706, 39, 16);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("Lon :");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(743, 765, 39, 16);
		frame.getContentPane().add(label_2);

		AlgoLontxt = new JTextField();
		AlgoLontxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AlgoLontxt.setColumns(10);
		AlgoLontxt.setBounds(794, 753, 181, 41);
		frame.getContentPane().add(AlgoLontxt);

		AlgoAlttxt = new JTextField();
		AlgoAlttxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		AlgoAlttxt.setColumns(10);
		AlgoAlttxt.setBounds(794, 810, 181, 41);
		frame.getContentPane().add(AlgoAlttxt);

		JLabel lblAlgorithm_1 = new JLabel("Algorithm 2");
		lblAlgorithm_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAlgorithm_1.setBounds(600, 369, 96, 44);
		frame.getContentPane().add(lblAlgorithm_1);

		Algo2Sampletxt = new JTextField();
		Algo2Sampletxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Algo2Sampletxt.setText("");
			}
		});
		Algo2Sampletxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Algo2Sampletxt.setText("Enter Sample");
		Algo2Sampletxt.setBounds(600, 415, 103, 30);
		frame.getContentPane().add(Algo2Sampletxt);
		Algo2Sampletxt.setColumns(10);

		JSeparator separator_8 = new JSeparator();
		separator_8.setForeground(Color.BLACK);
		separator_8.setBounds(600, 470, 385, 13);
		frame.getContentPane().add(separator_8);

		JLabel label_3 = new JLabel("Algorithm 2");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(600, 474, 96, 44);
		frame.getContentPane().add(label_3);

		Mac1txt = new JTextField();
		Mac1txt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Mac1txt.setText("");
			}
		});
		Mac1txt.setHorizontalAlignment(SwingConstants.CENTER);
		Mac1txt.setText("MAC1");
		Mac1txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Mac1txt.setEnabled(true);
		Mac1txt.setColumns(10);
		Mac1txt.setBounds(600, 518, 131, 30);
		frame.getContentPane().add(Mac1txt);

		Mac2txt = new JTextField();
		Mac2txt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Mac2txt.setText("");
			}
		});
		Mac2txt.setText("MAC2");
		Mac2txt.setHorizontalAlignment(SwingConstants.CENTER);
		Mac2txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Mac2txt.setEnabled(true);
		Mac2txt.setColumns(10);
		Mac2txt.setBounds(600, 563, 131, 30);
		frame.getContentPane().add(Mac2txt);

		Mac3txt = new JTextField();
		Mac3txt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Mac3txt.setText("");
			}
		});
		Mac3txt.setText("MAC3");
		Mac3txt.setHorizontalAlignment(SwingConstants.CENTER);
		Mac3txt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Mac3txt.setEnabled(true);
		Mac3txt.setColumns(10);
		Mac3txt.setBounds(600, 606, 131, 30);
		frame.getContentPane().add(Mac3txt);

		Signal1 = new JTextField();
		Signal1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Signal1.setText("");
			}
		});
		Signal1.setHorizontalAlignment(SwingConstants.CENTER);
		Signal1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Signal1.setText("Signal1");
		Signal1.setBounds(748, 518, 71, 30);
		frame.getContentPane().add(Signal1);
		Signal1.setColumns(10);

		Signal2 = new JTextField();
		Signal2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Signal2.setText("");
			}
		});
		Signal2.setHorizontalAlignment(SwingConstants.CENTER);
		Signal2.setText("Signal2");
		Signal2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Signal2.setColumns(10);
		Signal2.setBounds(747, 563, 71, 30);
		frame.getContentPane().add(Signal2);

		Signal3 = new JTextField();
		Signal3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Signal3.setText("");
			}
		});
		Signal3.setHorizontalAlignment(SwingConstants.CENTER);
		Signal3.setText("Signal3");
		Signal3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Signal3.setColumns(10);
		Signal3.setBounds(748, 606, 71, 30);
		frame.getContentPane().add(Signal3);

		TitledBorder titled = new TitledBorder("LOG");

		textArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textArea.setEditable(false);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		textArea.setBounds(30, 51, 938, 117);
		textArea.setBorder(titled);

		JScrollPane scroll = new JScrollPane (textArea);
		scroll.setBounds(12, 11, 973, 169);
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		frame.getContentPane().add(scroll);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");

		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmExit);
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

	}
	
	/**
	 * The function gets an ArrayList of WifiScan and counts the number of macs
	 * 
	 * @param lines
	 * @return
	 */
	 static int MacCount(ArrayList<WifiScan> lines) {
		int count=0;
		for (int i = 0; i < lines.size(); i++) {
			count+=lines.get(i).numOfScans;
		}
		return count;
	}
}
