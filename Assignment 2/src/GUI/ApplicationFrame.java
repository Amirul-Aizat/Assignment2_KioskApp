package GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import kioskapp.ordereditem.OrderedItem;
import kioskapp.ordertransaction.OrderTransaction;

import database.ItemProductDatabase;
import javax.swing.border.EtchedBorder;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.TextArea;

// not readable

/*
 *  Amirul Aizat 
 */

@SuppressWarnings("serial")
public class ApplicationFrame extends JFrame {

	private OrderTransaction orderTransaction = new OrderTransaction();
	private OrderedItem orderedItem ;
	private float totalPrice = 0;
	
	private ItemProductDatabase  itemProductDatabase = new ItemProductDatabase();
	private ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
	private JPanel contentPane;
	private JTextField txtCreditCardNumber;
	private float basePrice[] = {(float)8.10, (float)11.9, (float)30.2, (float)11.9, (float)9.4, (float)9.45, (float)10.4, (float)8.45, (float)13.2, (float)13.2, (float)13, (float)4.15, (float)4.15};
	private float subPrice[] = {(float)8.10, (float)11.9, (float)30.2, (float)11.9, (float)9.4, (float)9.45, (float)10.4, (float)8.45, (float)13.2, (float)13.2, (float)13, (float)4.15, (float)4.15};
	private int[] quantity = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	private boolean[] choosedItem = {false,false,false,false,false,false,false,false,false,false,false,false,false,};
	private int choose = 0;
	private JLabel lblNewLabel_8 = new JLabel("0.00");
	private String creditCardNumber = "";
	private TextArea textArea = new TextArea();
	
	@SuppressWarnings("deprecation")
	public ApplicationFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 696, 453);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setEnabledAt(1, true);
				tabbedPane.setEnabledAt(0, false);
				tabbedPane.setSelectedIndex(1);
			}
		});
		JLabel lblNewLabel_9 = new JLabel("0.00");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBackground(Color.RED);
		tabbedPane.addTab("WELCOME", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Touch Here To Continue");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(1, true);
				tabbedPane.setEnabledAt(0, false);
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnNewButton.setBounds(207, 385, 266, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setEnabledAt(1, true);
				tabbedPane.setEnabledAt(0, false);
				tabbedPane.setSelectedIndex(1);
			}
		});
		lblNewLabel.setBounds(207, 54, 266, 321);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/mcd-fries.png")));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("WELCOME TO MCDuCK");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(207, 10, 299, 42);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		tabbedPane.addTab("DINE", null, panel_1, null);
		panel_1.setBackground(Color.RED);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				orderTransaction.setOrderMode("Eat In");
				tabbedPane.setEnabledAt(2, true);
				tabbedPane.setEnabledAt(1, false);
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnNewButton_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnNewButton_1.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/eat-in.png")));
		btnNewButton_1.setBounds(71, 99, 250, 250);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				choose = 1;
				orderTransaction.setOrderMode("Take Away");
				tabbedPane.setEnabledAt(2, true);
				tabbedPane.setEnabledAt(1, false);
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnNewButton_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnNewButton_1_1.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/take-away.png")));
		btnNewButton_1_1.setBounds(363, 99, 250, 250);
		panel_1.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("CHOOSE");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblNewLabel_2.setBounds(277, 35, 140, 42);
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_2.setBackground(Color.RED);
		tabbedPane.addTab("MENU", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.YELLOW);
		scrollPane.setBounds(24, 32, 617, 374);
		panel_2.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		panel_3.setBackground(Color.YELLOW);
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(new MigLayout("", "[193px][193px][193px][193px][193px][193px][]", "[169px][]"));
		
		JPanel panel_5 = new JPanel();
		panel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mc Chicken", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_5, "cell 0 0,grow");
		panel_5.setLayout(new MigLayout("", "[193px]", "[169px]"));
		
		JButton mcChicken= new JButton("");
		mcChicken.setForeground(Color.BLACK);
		mcChicken.setBackground(Color.WHITE);
		panel_5.add(mcChicken, "cell 0 0,alignx center,aligny center");
		
		mcChicken.setBorder(new LineBorder(Color.BLACK, 3, true));
		mcChicken.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/mc chicken.png")));
		
		JPanel panel_9 = new JPanel();
		panel_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_9.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ayam McD 2pcs", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_9, "cell 1 0,grow");
		panel_9.setLayout(new MigLayout("", "[193px]", "[169px]"));
		
		JButton ayamMcd2pc= new JButton("");
		ayamMcd2pc.setForeground(Color.BLACK);
		
		
		panel_9.add(ayamMcd2pc, "cell 0 0,alignx center,aligny center");
		ayamMcd2pc.setBorder(new LineBorder(Color.BLACK, 3, true));
		ayamMcd2pc.setBackground(Color.WHITE);
		ayamMcd2pc.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/ayam mcd 2pc.png")));
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ayam McD 5pcs", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_10, "cell 2 0,grow");
		panel_10.setLayout(new MigLayout("", "[193px]", "[169px]"));
		
		JButton ayamMcd5pc = new JButton("");
		ayamMcd5pc.setForeground(Color.BLACK);
		
		panel_10.add(ayamMcd5pc, "cell 0 0,alignx center,aligny center");
		ayamMcd5pc.setBorder(new LineBorder(Color.BLACK, 3, true));
		ayamMcd5pc.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/ayam mcd 5pc.png")));
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Spicy Chickend McDeluxe", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_14, "cell 3 0,grow");
		panel_14.setLayout(new MigLayout("", "[193px]", "[169px]"));
		
		JButton spicyChicDeluxe = new JButton("");
		spicyChicDeluxe.setForeground(Color.BLACK);
	
		panel_14.add(spicyChicDeluxe, "cell 0 0,alignx center,aligny center");
		spicyChicDeluxe.setBorder(new LineBorder(Color.BLACK, 3, true));
		spicyChicDeluxe.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/spicy chic deluxe.png")));
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mc Nugget 6pcs", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_16, "cell 4 0,grow");
		panel_16.setLayout(new MigLayout("", "[193px]", "[169px]"));
		
		JButton nugget = new JButton("");
		nugget.setForeground(Color.BLACK);
		
		panel_16.add(nugget, "cell 0 0,alignx center,aligny center");
		nugget.setBorder(new LineBorder(Color.BLACK, 3, true));
		nugget.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/nugget.jfif.png")));
		
		JPanel panel_18 = new JPanel();
		panel_18.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Double Cheeseburger", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_18, "cell 5 0,grow");
		panel_18.setLayout(new MigLayout("", "[193px]", "[169px]"));
		
		JButton doubleCheeseBurger = new JButton("");
		doubleCheeseBurger.setForeground(Color.BLACK);
		
		panel_18.add(doubleCheeseBurger, "cell 0 0,alignx center,aligny center");
		doubleCheeseBurger.setBorder(new LineBorder(Color.BLACK, 3, true));
		doubleCheeseBurger.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/product-double-cheeseburger.png")));
		
		JPanel panel_20 = new JPanel();
		panel_20.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Big Mac", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_20, "cell 6 0,grow");
		panel_20.setLayout(new MigLayout("", "[]", "[169px]"));
		
		JButton bigMac = new JButton("");
		bigMac.setForeground(Color.BLACK);
		
		panel_20.add(bigMac, "cell 0 0,alignx center,aligny center");
		bigMac.setBorder(new LineBorder(Color.BLACK, 3, true));
		bigMac.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/product-big-mac.png")));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Filet O Fish", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_11, "cell 0 1,grow");
		panel_11.setLayout(new MigLayout("", "[193px]", "[]"));
		
		JButton filetOFish= new JButton("");
		filetOFish.setForeground(Color.BLACK);
		
		panel_11.add(filetOFish, "cell 0 0,alignx center,aligny center");
		filetOFish.setBorder(new LineBorder(Color.BLACK, 3, true));
		filetOFish.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/filet-o-fish.png")));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mc Chicken Meal", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_12, "cell 1 1,grow");
		panel_12.setLayout(new MigLayout("", "[193px]", "[]"));
		
		JButton mcChickenMeal = new JButton("");
		mcChickenMeal.setForeground(Color.BLACK);
		
		panel_12.add(mcChickenMeal, "cell 0 0,alignx center,aligny center");
		mcChickenMeal.setBorder(new LineBorder(Color.BLACK, 3, true));
		mcChickenMeal.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/mc chcken meal.png")));
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mc Nugget 6pcs", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_13, "cell 2 1,grow");
		panel_13.setLayout(new MigLayout("", "[193px]", "[]"));
		
		JButton chickenMcnuggets = new JButton("");
		chickenMcnuggets.setForeground(Color.BLACK);
		
		panel_13.add(chickenMcnuggets, "cell 0 0,alignx center,growy");
		chickenMcnuggets.setBorder(new LineBorder(Color.BLACK, 3, true));
		chickenMcnuggets.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/mcd-meals-chicken-mcnuggets.png")));
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Filet O Fish Meal", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_15, "cell 3 1,grow");
		panel_15.setLayout(new MigLayout("", "[193px]", "[]"));
		
		JButton fofMeal = new JButton("");
		fofMeal.setForeground(Color.BLACK);
		
		panel_15.add(fofMeal, "cell 0 0,alignx center,aligny center");
		fofMeal.setBorder(new LineBorder(Color.BLACK, 3, true));
		fofMeal.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/fof meal.png")));
		
		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Strawberry Sundae", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_17, "cell 4 1,grow");
		panel_17.setLayout(new MigLayout("", "[193px]", "[]"));
		
		JButton strawberrySundae = new JButton("");
		strawberrySundae.setForeground(Color.BLACK);
		
		panel_17.add(strawberrySundae, "cell 0 0,alignx center,aligny center");
		strawberrySundae.setBorder(new LineBorder(Color.BLACK, 3, true));
		strawberrySundae.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/product-strawberry-sundae.png")));
		
		JPanel panel_19 = new JPanel();
		panel_19.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chocolate Sundae", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_19, "cell 5 1,grow");
		panel_19.setLayout(new MigLayout("", "[193px]", "[]"));
		
		JButton hotFudgeSundae = new JButton("");
		hotFudgeSundae.setForeground(Color.BLACK);
		
		panel_19.add(hotFudgeSundae, "cell 0 0,alignx center,aligny center");
		hotFudgeSundae.setBorder(new LineBorder(Color.BLACK, 3, true));
		hotFudgeSundae.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/product-hot-fudge-sundae.png")));
		
		JLabel lblNewLabel_3 = new JLabel("MENU");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(302, 10, 65, 13);
		panel_2.add(lblNewLabel_3);
		
		JButton btnNewButton_16 = new JButton("Cancel");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose = -1;
				try {
					release();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_16.setBounds(24, 10, 85, 21);
		panel_2.add(btnNewButton_16);
		
		JButton btnNewButton_16_1 = new JButton("Next");
		btnNewButton_16_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean alreadyChoosedAtleastOne = false;
				 for(boolean b : choosedItem) {
					 if(b) {
						 alreadyChoosedAtleastOne = b || alreadyChoosedAtleastOne;
					 }
				 }
					 
				if(alreadyChoosedAtleastOne) {
					updateTotalPrice();
					tabbedPane.setEnabledAt(3, true);
					tabbedPane.setEnabledAt(2, false);
					tabbedPane.setSelectedIndex(3);
					choose = 1;
					try {
						release();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Please choose some food", "Opps!", JOptionPane.INFORMATION_MESSAGE);
					
				}
					 
				 
			}
		});
		btnNewButton_16_1.setBounds(556, 9, 85, 21);
		panel_2.add(btnNewButton_16_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_6.setBackground(Color.RED);
		tabbedPane.addTab("CART", null, panel_6, null);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 621, 285);
		panel_6.add(scrollPane_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_7.setBackground(Color.YELLOW);
		panel_7.setPreferredSize(new Dimension (500,1800));
		scrollPane_1.setViewportView(panel_7);
		panel_7.setLayout(new GridLayout(13, 0, 0, 0));
		
		JPanel ap = new JPanel();
		ap.setBackground(Color.ORANGE);
		ap.setBounds(10, 32, 623, 160);
		ap.setLayout(null);
		
		JLabel al = new JLabel("New label");
		al.setBounds(1, 0, 155, 160);
		al.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/filet-o-fish.png")));
		ap.add(al);
		
		JLabel al2 = new JLabel("Filet O Fish");
		al2.setFont(new Font("Tahoma", Font.BOLD, 15));
		al2.setBounds(181, 15, 120, 130);
		ap.add(al2);
		
		JSpinner as = new JSpinner();
		as.enableInputMethods(false);
		as.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as.setBounds(327, 34, 103, 93);
		ap.add(as);
		
		JLabel al3 = new JLabel("RM 8.45");
		as.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[7] = Integer.parseInt(as.getValue().toString());
				subPrice[7] = quantity[7]*basePrice[7];
				al3.setText(String.format("RM %2.2f",basePrice[7] * Double.parseDouble(as.getValue().toString())));
				updateTotalPrice();
			}
		});
		al3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al3.setBounds(466, 15, 130, 130);
		ap.add(al3);
		
		
		
		JPanel ap2 = new JPanel();
		ap2.setBackground(Color.YELLOW);
		ap2.setBounds(10, 32, 623, 160);
		ap2.setLayout(null);
		
		JLabel al4 = new JLabel("New label");
		al4.setBounds(1, 0, 155, 160);
		al4.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/ayam mcd 2pc.png")));
		ap2.add(al4);
		
		JLabel al5 = new JLabel("Ayam Mcd 2pcs");
		al5.setFont(new Font("Tahoma", Font.BOLD, 15));
		al5.setBounds(180, 13, 137, 130);
		ap2.add(al5);
		
		JSpinner as2 = new JSpinner();
		
		as2.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as2.setBounds(327, 34, 103, 93);
		ap2.add(as2);
		
		JLabel al6 = new JLabel("RM 11.90");
		as2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[1] = Integer.parseInt(as2.getValue().toString());
				subPrice[1] = quantity[1]*basePrice[1];
				al6.setText(String.format("RM %2.2f",basePrice[1] * Double.parseDouble(as2.getValue().toString())));
				updateTotalPrice();
			}
		});
		al6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al6.setBounds(466, 15, 130, 130);
		ap2.add(al6);
		
		
		
		JPanel ap3 = new JPanel();
		ap3.setBackground(Color.ORANGE);
		ap3.setBounds(10, 32, 623, 160);
		ap3.setLayout(null);
		
		JLabel al7 = new JLabel("New label");
		al7.setBounds(1, 0, 155, 160);
		al7.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/ayam mcd 5pc.png")));
		ap3.add(al7);
		
		JLabel al8 = new JLabel("Ayam Mcd 5pcs");
		al8.setFont(new Font("Tahoma", Font.BOLD, 15));
		al8.setBounds(180, 13, 137, 130);
		ap3.add(al8);
		
		JSpinner as3 = new JSpinner();
		
		as3.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as3.setBounds(327, 34, 103, 93);
		ap3.add(as3);
		
		JLabel al9 = new JLabel("RM 30.20");
		as3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[2] = Integer.parseInt(as3.getValue().toString());
				subPrice[2] = quantity[2]*basePrice[2];
				al9.setText(String.format("RM %2.2f",basePrice[2] * Double.parseDouble(as3.getValue().toString())));
				updateTotalPrice();
			}
		});
		al9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al9.setBounds(466, 15, 130, 130);
		ap3.add(al9);
		
		
		
		JPanel ap4 = new JPanel();
		ap4.setBackground(Color.YELLOW);
		ap4.setBounds(10, 32, 623, 160);
		ap4.setLayout(null);
		
		JLabel al10 = new JLabel("New label");
		al10.setBounds(1, 0, 155, 160);
		al10.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/fof meal.png")));
		ap4.add(al10);
		
		JLabel al11 = new JLabel("FOF Meal");
		al11.setFont(new Font("Tahoma", Font.BOLD, 15));
		al11.setBounds(180, 13, 137, 130);
		ap4.add(al11);
		
		JSpinner as4 = new JSpinner();
		
		as4.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as4.setBounds(327, 34, 103, 93);
		ap4.add(as4);
		
		JLabel al12 = new JLabel("RM 13.00");
		as4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[10] = Integer.parseInt(as4.getValue().toString());
				subPrice[10] = quantity[10]*basePrice[10];
				al12.setText(String.format("RM %2.2f",basePrice[10] * Double.parseDouble(as4.getValue().toString())));
				updateTotalPrice();
			}
		});
		al12.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al12.setBounds(466, 15, 130, 130);
		ap4.add(al12);
		
		
		
		JPanel ap5 = new JPanel();
		ap5.setBackground(Color.ORANGE);
		ap5.setBounds(10, 32, 623, 160);
		ap5.setLayout(null);
		
		JLabel al13 = new JLabel("New label");
		al13.setBounds(1, 0, 155, 160);
		al13.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/mc chcken meal.png")));
		ap5.add(al13);
		
		JLabel al14 = new JLabel("McChicken Meal");
		al14.setFont(new Font("Tahoma", Font.BOLD, 15));
		al14.setBounds(180, 13, 137, 130);
		ap5.add(al14);
		
		JSpinner as5 = new JSpinner();
	
		as5.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as5.setBounds(327, 34, 103, 93);
		ap5.add(as5);
		
		JLabel al15 = new JLabel("RM 13.20");
		as5.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[8] = Integer.parseInt(as5.getValue().toString());
				subPrice[8] = quantity[8]*basePrice[8];
				al15.setText(String.format("RM %2.2f",basePrice[8] * Double.parseDouble(as5.getValue().toString())));
				updateTotalPrice();
			}
		});
		al15.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al15.setBounds(466, 15, 130, 130);
		ap5.add(al15);
		
		
		
		JPanel ap6 = new JPanel();
		ap6.setBackground(Color.YELLOW);
		ap6.setBounds(10, 32, 623, 160);
		ap6.setLayout(null);
		
		JLabel al16 = new JLabel("New label");
		al16.setBounds(1, 0, 155, 160);
		al16.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/mc chicken.png")));
		ap6.add(al16);
		
		JLabel al17 = new JLabel("McChicken");
		al17.setFont(new Font("Tahoma", Font.BOLD, 15));
		al17.setBounds(180, 13, 137, 130);
		ap6.add(al17);
		
		JSpinner as6 = new JSpinner();
		
		as6.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as6.setBounds(327, 34, 103, 93);
		ap6.add(as6);
		
		JLabel al18 = new JLabel("RM 8.10");
		as6.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[0] = Integer.parseInt(as6.getValue().toString());
				subPrice[0] = quantity[0]*basePrice[0];
				al18.setText(String.format("RM %2.2f",basePrice[0] * Double.parseDouble(as6.getValue().toString())));
				updateTotalPrice();
			}
		});
		al18.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al18.setBounds(466, 15, 130, 130);
		ap6.add(al18);
		
		
		
		JPanel ap7 = new JPanel();
		ap7.setBackground(Color.ORANGE);
		ap7.setBounds(10, 32, 623, 160);
		ap7.setLayout(null);
		
		JLabel al19 = new JLabel("New label");
		al19.setBounds(1, 0, 155, 160);
		al19.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/mcd-meals-chicken-mcnuggets.png")));
		ap7.add(al19);
		
		JLabel al20 = new JLabel("Nugget Meal");
		al20.setFont(new Font("Tahoma", Font.BOLD, 15));
		al20.setBounds(180, 13, 137, 130);
		ap7.add(al20);
		
		JSpinner as7 = new JSpinner();
		
		as7.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as7.setBounds(327, 34, 103, 93);
		ap7.add(as7);
		
		JLabel al21 = new JLabel("RM 13.2");
		as7.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[9] = Integer.parseInt(as7.getValue().toString());
				subPrice[9] = quantity[9]*basePrice[9];
				al21.setText(String.format("RM %2.2f",basePrice[9] * Double.parseDouble(as7.getValue().toString())));
				updateTotalPrice();
			}
		});
		al21.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al21.setBounds(466, 15, 130, 130);
		ap7.add(al21);
		
		
		
		JPanel ap8 = new JPanel();
		ap8.setBackground(Color.YELLOW);
		ap8.setBounds(10, 32, 623, 160);
		ap8.setLayout(null);
		
		JLabel al22 = new JLabel("New label");
		al22.setBounds(1, 0, 155, 160);
		al22.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/nugget.jfif.png")));
		ap8.add(al22);
		
		JLabel al23 = new JLabel("Nugget 6pcs");
		al23.setFont(new Font("Tahoma", Font.BOLD, 15));
		al23.setBounds(180, 13, 137, 130);
		ap8.add(al23);
		
		JSpinner as8 = new JSpinner();
		
		as8.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as8.setBounds(327, 34, 103, 93);
		ap8.add(as8);
		
		JLabel al24 = new JLabel("RM 9.4");
		as8.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[4] = Integer.parseInt(as8.getValue().toString());
				subPrice[4] = quantity[4]*basePrice[4];
				al24.setText(String.format("RM %2.2f",basePrice[4] * Double.parseDouble(as8.getValue().toString())));
				updateTotalPrice();
			}
		});
		al24.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al24.setBounds(466, 15, 130, 130);
		ap8.add(al24);
		
		
		
		JPanel ap9 = new JPanel();
		ap9.setBackground(Color.ORANGE);
		ap9.setBounds(10, 32, 623, 160);
		ap9.setLayout(null);
		
		JLabel al25 = new JLabel("New label");
		al25.setBounds(1, 0, 155, 160);
		al25.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/product-big-mac.png")));
		ap9.add(al25);
		
		JLabel al26 = new JLabel("Big Mac");
		al26.setFont(new Font("Tahoma", Font.BOLD, 15));
		al26.setBounds(180, 13, 137, 130);
		ap9.add(al26);
		
		JSpinner as9 = new JSpinner();
		
		as9.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as9.setBounds(327, 34, 103, 93);
		ap9.add(as9);
		
		JLabel al27 = new JLabel("RM 10.40");
		as9.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[6] = Integer.parseInt(as9.getValue().toString());
				subPrice[6] = quantity[6]*basePrice[6];
				al27.setText(String.format("RM %2.2f",basePrice[6] * Double.parseDouble(as9.getValue().toString())));
				updateTotalPrice();
			}
		});
		al27.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al27.setBounds(466, 15, 130, 130);
		ap9.add(al27);
		
		
		
		JPanel ap10 = new JPanel();
		ap10.setBackground(Color.YELLOW);
		ap10.setBounds(10, 32, 623, 160);
		ap10.setLayout(null);
		
		JLabel al28 = new JLabel("New label");
		al28.setBounds(1, 0, 155, 160);
		al28.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/product-double-cheeseburger.png")));
		ap10.add(al28);
		
		JLabel al29 = new JLabel("Doube Cheese");
		al29.setFont(new Font("Tahoma", Font.BOLD, 15));
		al29.setBounds(180, 13, 137, 130);
		ap10.add(al29);
		
		JSpinner as10 = new JSpinner();
		
		as10.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as10.setBounds(327, 34, 103, 93);
		ap10.add(as10);
		
		JLabel al30 = new JLabel("RM 9.45");
		as10.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[5] = Integer.parseInt(as10.getValue().toString());
				subPrice[5] = quantity[5]*basePrice[5];
				al30.setText(String.format("RM %2.2f",basePrice[5] * Double.parseDouble(as10.getValue().toString())));
				updateTotalPrice();
			}
		});
		al30.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al30.setBounds(466, 15, 130, 130);
		ap10.add(al30);
		
		
		
		JPanel ap11 = new JPanel();
		ap11.setBackground(Color.ORANGE);
		ap11.setBounds(10, 32, 623, 160);
		ap11.setLayout(null);
		
		JLabel al31 = new JLabel("New label");
		al31.setBounds(1, 0, 155, 160);
		al31.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/product-hot-fudge-sundae.png")));
		ap11.add(al31);
		
		JLabel al32 = new JLabel("Chocolate Sundae");
		al32.setFont(new Font("Tahoma", Font.BOLD, 15));
		al32.setBounds(180, 13, 137, 130);
		ap11.add(al32);
		
		JSpinner as11 = new JSpinner();
		
		as11.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as11.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as11.setBounds(327, 34, 103, 93);
		ap11.add(as11);
		
		JLabel al33 = new JLabel("RM 4.15");
		as11.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[12] = Integer.parseInt(as11.getValue().toString());
				subPrice[12] = quantity[12]*basePrice[12];
				al33.setText(String.format("RM %2.2f",basePrice[12] * Double.parseDouble(as11.getValue().toString())));
				updateTotalPrice();
			}
		});
		al33.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al33.setBounds(466, 15, 130, 130);
		ap11.add(al33);
		
		
		
		JPanel ap12 = new JPanel();
		ap12.setBackground(Color.YELLOW);
		ap12.setBounds(10, 32, 623, 160);
		ap12.setLayout(null);
		
		JLabel al34 = new JLabel("New label");
		al34.setBounds(1, 0, 155, 160);
		al34.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/product-strawberry-sundae.png")));
		ap12.add(al34);
		
		JLabel al35 = new JLabel("Strawberry Sundae");
		al35.setFont(new Font("Tahoma", Font.BOLD, 15));
		al35.setBounds(180, 13, 137, 130);
		ap12.add(al35);
		
		JSpinner as12 = new JSpinner();
		
		as12.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as12.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as12.setBounds(327, 34, 103, 93);
		ap12.add(as12);
		
		JLabel al36 = new JLabel("RM 4.15");
		as12.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[11] = Integer.parseInt(as12.getValue().toString());
				subPrice[11] = quantity[11]*basePrice[11];
				al36.setText(String.format("RM %2.2f",basePrice[11] * Double.parseDouble(as12.getValue().toString())));
				updateTotalPrice();
			}
		});
		al36.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al36.setBounds(466, 15, 130, 130);
		ap12.add(al36);
		
		
		
		JPanel ap13 = new JPanel();
		ap13.setBackground(Color.ORANGE);
		ap13.setBounds(10, 32, 623, 160);
		ap13.setLayout(null);
		
		JLabel al37 = new JLabel("New label");
		al37.setBounds(1, 0, 155, 160);
		al37.setIcon(new ImageIcon(ApplicationFrame.class.getResource("/images/spicy chic deluxe.png")));
		ap13.add(al37);
		
		JLabel al38 = new JLabel("Spicy McDeluxe");
		al38.setFont(new Font("Tahoma", Font.BOLD, 15));
		al38.setBounds(180, 13, 137, 130);
		ap13.add(al38);
		
		JSpinner as13 = new JSpinner();
		
		as13.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		as13.setFont(new Font("Tahoma", Font.PLAIN, 20));
		as13.setBounds(327, 34, 103, 93);
		ap13.add(as13);
		
		JLabel al39 = new JLabel("RM 11.90");
		as13.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity[3] = Integer.parseInt(as13.getValue().toString());
				subPrice[3] = quantity[3]*basePrice[3];
				al39.setText(String.format("RM %2.2f",basePrice[3] * Double.parseDouble(as13.getValue().toString())));
				updateTotalPrice();
			}
		});
		al39.setFont(new Font("Tahoma", Font.PLAIN, 20));
		al39.setBounds(466, 15, 130, 130);
		ap13.add(al39);
		
		
		
		
		JButton btnNewButton_6 = new JButton("Process Payment");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setOrderedItems();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				setTextArea();
		
				tabbedPane.setEnabledAt(4, true);
				tabbedPane.setEnabledAt(3, false);
				tabbedPane.setSelectedIndex(4);
				lblNewLabel_9.setText(String.format("%2.2f",totalPrice));
				try {
					release();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				orderTransaction.setAmountCharged(totalPrice);
				choose = 1;
			}
		});
		btnNewButton_6.setBackground(Color.WHITE);
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_6.setBounds(458, 355, 193, 37);
		panel_6.add(btnNewButton_6);
		
		JButton btnNewButton_6_1 = new JButton("Cancel Order");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose = -1;
				try {
					release();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_6_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_6_1.setBackground(new Color(255, 255, 255));
		btnNewButton_6_1.setBounds(255, 355, 193, 37);
		panel_6.add(btnNewButton_6_1);
		
		JLabel lblNewLabel_4 = new JLabel("Total:  RM");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(380, 306, 102, 25);
		panel_6.add(lblNewLabel_4);
		
		JPanel panel_21 = new JPanel();
		panel_21.setBounds(486, 306, 145, 25);
		panel_6.add(panel_21);
		panel_21.setLayout(new BorderLayout(0, 0));
		
		
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_21.add(lblNewLabel_8);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_4.setBackground(Color.RED);
		tabbedPane.addTab("PAYMENT", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Total Price:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5.setBounds(494, 25, 137, 31);
		panel_4.add(lblNewLabel_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(455, 54, 192, 43);
		panel_4.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 37));
		panel_8.add(lblNewLabel_9, BorderLayout.CENTER);
		
		JLabel lblNewLabel_6 = new JLabel("PAYMENT");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(505, 173, 115, 36);
		panel_4.add(lblNewLabel_6);
		
		txtCreditCardNumber = new JTextField();
		txtCreditCardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtCreditCardNumber.setBorder(new LineBorder(Color.BLACK, 2));
		txtCreditCardNumber.setBounds(453, 239, 212, 31);
		panel_4.add(txtCreditCardNumber);
		txtCreditCardNumber.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Credit Card Number");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_7.setBounds(505, 216, 115, 13);
		panel_4.add(lblNewLabel_7);
		
		JButton btnNewButton_15 = new JButton("Proceed Payment");
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!(txtCreditCardNumber.getText().isBlank()) && isNumeric(txtCreditCardNumber.getText()) && txtCreditCardNumber.getText().length() >12) {
				orderTransaction.setLast4Digits(Integer.parseInt(txtCreditCardNumber.getText().substring(txtCreditCardNumber.getText().length() - 4)));
				creditCardNumber = txtCreditCardNumber.getText();
				choose = 1;
				try {
					release();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
				else {
					JOptionPane.showMessageDialog(null, "Please insert your credit card number and make sure it is string and valid length", "Opps!", JOptionPane.INFORMATION_MESSAGE);
				}
				}
			
		});
		btnNewButton_15.setBounds(455, 286, 210, 21);
		panel_4.add(btnNewButton_15);
		
		JButton btnNewButton_15_1 = new JButton("Cancel Order");
		btnNewButton_15_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					release();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				choose = -1;
			}
		});
		btnNewButton_15_1.setBounds(455, 317, 210, 21);
		panel_4.add(btnNewButton_15_1);
		
		JLabel lblNewLabel_10 = new JLabel("RM");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblNewLabel_10.setBounds(403, 36, 81, 78);
		panel_4.add(lblNewLabel_10);
		
		
		textArea.setBounds(10, 10, 387, 405);
		panel_4.add(textArea);
		
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.setEnabledAt(3, false);
		tabbedPane.setEnabledAt(4, false);
		
		mcChicken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) mcChicken.getBorder()).getLineColor() == Color.BLACK) {
					mcChicken.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap6);
					choosedItem[0] = true;
				}
				else {
					mcChicken.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap6);
					choosedItem[0] = false;
				}
				updateTotalPrice();
				
			}
		});
		
		
		ayamMcd2pc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) ayamMcd2pc.getBorder()).getLineColor() == Color.BLACK) {
					ayamMcd2pc.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap2);
					choosedItem[1] = true;
				}
				else {
					ayamMcd2pc.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap2);
					choosedItem[1] = false;
				}
				updateTotalPrice();
			}
		});
		
		
		ayamMcd5pc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) ayamMcd5pc.getBorder()).getLineColor() == Color.BLACK) {
					ayamMcd5pc.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap3);
					choosedItem[2] = true;
				}
				else {
					ayamMcd5pc.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap3);
					choosedItem[2] = false;
				}
				updateTotalPrice();
			}
		});
		
		spicyChicDeluxe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) spicyChicDeluxe.getBorder()).getLineColor() == Color.BLACK) {
					spicyChicDeluxe.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap13);
					choosedItem[3] = true;
				}
				else {
					spicyChicDeluxe.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap13);
					choosedItem[3] = false;
				}
				updateTotalPrice();
			}
		});
		
		nugget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) nugget.getBorder()).getLineColor() == Color.BLACK) {
					nugget.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap8);
					choosedItem[4] = true;
				}
				else {
					nugget.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap8);
					choosedItem[4] = false;
				}
				updateTotalPrice();
			}
		});
		
		doubleCheeseBurger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) doubleCheeseBurger.getBorder()).getLineColor() == Color.BLACK) {
					doubleCheeseBurger.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap10);
					choosedItem[5] = true;
				}
				else {
					doubleCheeseBurger.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap10);
					choosedItem[5] = false;
				}
				updateTotalPrice();
			}
		});
		
		bigMac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) bigMac.getBorder()).getLineColor() == Color.BLACK) {
					bigMac.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap9);
					choosedItem[6] = true;
				}
				else {
					bigMac.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap9);
					choosedItem[6] = false;
				}
				updateTotalPrice();
			}
		});
		
		filetOFish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) filetOFish.getBorder()).getLineColor() == Color.BLACK) {
					filetOFish.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap);
					choosedItem[7] = true;
				}
				else {
					filetOFish.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap);
					choosedItem[7] = false;
				}
				updateTotalPrice();
			}
		});
		
		mcChickenMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) mcChickenMeal.getBorder()).getLineColor() == Color.BLACK) {
					mcChickenMeal.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap5);
					choosedItem[8] = true;
				}
				else {
					mcChickenMeal.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap5);
					choosedItem[8] = false;
				}
				updateTotalPrice();
			}
		});
		
		chickenMcnuggets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) chickenMcnuggets.getBorder()).getLineColor() == Color.BLACK) {
					chickenMcnuggets.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap7);
					choosedItem[9] = true;
				}
				else {
					chickenMcnuggets.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap7);
					choosedItem[9] = false;
				}
				updateTotalPrice();
			}
		});
		
		fofMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) fofMeal.getBorder()).getLineColor() == Color.BLACK) {
					fofMeal.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap4);
					choosedItem[10] = true;
				}
				else {
					fofMeal.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap4);
					choosedItem[10] = false;
				}
				updateTotalPrice();
			}
		});
		
		strawberrySundae.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((LineBorder) strawberrySundae.getBorder()).getLineColor() == Color.BLACK) {
					strawberrySundae.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap12);
					choosedItem[11] = true;
				}
				else {
					strawberrySundae.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap12);
					choosedItem[11] = false;
				}
				updateTotalPrice();
			}
		});
		
		hotFudgeSundae.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (((LineBorder) hotFudgeSundae.getBorder()).getLineColor() == Color.BLACK) {
					hotFudgeSundae.setBorder(new LineBorder(Color.BLUE, 3, true));
					panel_7.add(ap11);
					choosedItem[12] = true;
					
				}
				else {
					hotFudgeSundae.setBorder(new LineBorder(Color.BLACK, 3, true));
					panel_7.remove(ap11);
					choosedItem[12] = false;
				}
				updateTotalPrice();
			}
		});
		
		
		setLocationRelativeTo(null);
		
	}
	public void updateTotalPrice() {
		float total = 0;
		for(int i = 0 ;i<choosedItem.length;i++) {
			if(choosedItem[i]) {
				total += subPrice[i];
			}
		}
		totalPrice = total;
		lblNewLabel_8.setText(String.format("%2.2f", total));
	}
	
	public void waitForInput() throws InterruptedException {
		synchronized(this) {
			wait();
		}
	}

	public void release() throws InterruptedException {
		synchronized(this) {
			notifyAll();
		}
	}
	
	public ArrayList<OrderedItem> getOrderedItems() throws SQLException, InterruptedException {
		
		return orderedItems;
	}
	
	public OrderTransaction getOrderTransaction() {
		return orderTransaction;
	}
	
	public void setOrderedItems() throws SQLException {
		for(int i = 0 ;i<choosedItem.length;i++) {
			if(choosedItem[i]) {
				orderedItem = new OrderedItem();
				orderedItem.setItemProduct(itemProductDatabase.getItemProduct(i+1));
				orderedItem.setQuantity(quantity[i]);
				orderedItem.setSubTotalAmount(subPrice[i]);
				orderedItems.add(orderedItem);
				
			}
		}
	}
	
	public int getChoose() throws InterruptedException {
		waitForInput();
		return choose;
	}
	
	public void setTextArea() {
		String itemText = "";
		for(OrderedItem orderedItem: orderedItems) {
			itemText += orderedItem.getItemProduct().getName()+"\nQuantity: "+orderedItem.getQuantity()+"\nTotal Price: RM"+String.format ("%4.2f",orderedItem.getSubTotalAmount())+"\n\n";
		}
		textArea.setText("Your Order :\n\n"+itemText);
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	public String getCreditCardNumber() throws InterruptedException {
		
		return creditCardNumber;
	}
	
	

}
