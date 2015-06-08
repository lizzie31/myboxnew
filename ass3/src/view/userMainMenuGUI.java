package view;
import java.awt.AWTEvent;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.MenuItem;
import java.awt.PopupMenu;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Container;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Model.User;
import Model.file;
import controllers.*;

import java.awt.Color;

import javax.swing.UIManager;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.AbstractListModel;
public class userMainMenuGUI extends JFrame {

	private JPanel MainMenu;
	private JTextField textField;
	private JButton btnCreateNewFile=null;
	private JButton btnShowgroups=null;
	private JButton btnCreateNewFolder=null;
	private JButton btnAddleaveAGroup=null;
	private PopupMenu p = new PopupMenu();
	private JButton open=null;
	private JButton btnLogOut=null;
	//private ArrayList<String> files=new ArrayList<String>();
	private String filePath = "C:/Users/����/Desktop/n/nofar33.txt";
	private Desktop desktop;
	private ArrayList<file> userfiles=null;
	private int arraysize;
	private String[] values;
	JList list=null;
	User user;
	
	public userMainMenuGUI(User user) {
		this.user=user;
		this.setSize(500, 500);
		initialize();
		this.setVisible(true);
		

	}
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.CYAN);
		setBounds(100, 100, 450, 300);
		this.setSize(500,400);
		this.setTitle("main menu");;
		this.setContentPane(getMainMenu());
		userfiles=user.getFilesInDB();
        arraysize=user.getFilesInDB().size();
        values = new String[arraysize];
        for(int i=0;i<userfiles.size();i++)
		{
			values[i]=userfiles.get(i).getFileName();
		}
		desktop= Desktop.getDesktop();
		btnCreateNewFile = new JButton("create new file");
		btnCreateNewFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreateNewFile.setBounds(307, 216, 132, 23);    
		btnCreateNewFile.setBackground(UIManager.getColor("SplitPane.background"));
		MainMenu.add(btnCreateNewFile);
		
		btnLogOut = new JButton("log out");
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOut.setBackground(UIManager.getColor("SplitPane.background"));
		btnLogOut.setBounds(307, 284, 89, 23);
		MainMenu.add(btnLogOut);
		
		btnShowgroups = new JButton("show Groups");
		btnShowgroups.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnShowgroups.setBackground(UIManager.getColor("SplitPane.background"));
		btnShowgroups.setBounds(307, 143, 138, 25);
		MainMenu.add(btnShowgroups);
		
		btnCreateNewFolder = new JButton("create new folder");
		btnCreateNewFolder.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCreateNewFolder.setBackground(UIManager.getColor("SplitPane.background"));
		btnCreateNewFolder.setBounds(307, 180, 138, 25);
		MainMenu.add(btnCreateNewFolder);
		
		open = new JButton("open");
		open.setBounds(115, 283, 138, 25);
		MainMenu.add(open);
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				if (source==open)
					try {
						desktop.open(new File(filePath));
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
			}
		});
		
		btnAddleaveAGroup = new JButton("add/leave a group");
		btnAddleaveAGroup.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddleaveAGroup.setBackground(UIManager.getColor("SplitPane.background"));
		btnAddleaveAGroup.setBounds(307, 250, 146, 23);
		MainMenu.add(btnAddleaveAGroup);
		
		textField = new JTextField();
		textField.setBounds(198, 46, 146, 20);
		MainMenu.add(textField);
		textField.setColumns(10);
		
		JLabel lblSearch = new JLabel("search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearch.setBounds(105, 46, 104, 17);
		MainMenu.add(lblSearch);
		 
		

	}
	
	private JPanel getMainMenu(){
		
		
		
		if(MainMenu==null)
		{MainMenu=new JPanel();
        MainMenu.setBackground(new Color(135, 206, 235));
		MainMenu.setLayout(null);
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(54, 140, 168, 132);
		MainMenu.add(list);
		
				
		//final JFileChooser fc = new JFileChooser();
		//int returnVal = fc.showOpenDialog(getComponent(0));
		//MainMenu.add(fc, MainMenu);
		}
		return MainMenu;
		
	}
	
	
	public void addcreatenewfile(ActionListener l) {
		btnCreateNewFile.addActionListener(l);
	}
	public void addcreatenewfolder(ActionListener l) {
		btnCreateNewFolder.addActionListener(l);
	}
	public void addleaveEntergruop(ActionListener l) {
		btnAddleaveAGroup.addActionListener(l);
	}
	public void addshowgruops(ActionListener l) {
		btnShowgroups.addActionListener(l);
	}
	public void addLogOut(ActionListener l) {
		btnLogOut.addActionListener(l);	
	}
	
	public void addlistClickedListener(ListSelectionListener l) {
		list.addListSelectionListener(l);	
	}
	public void close() {
		this.setVisible(false);
		dispose();
	}
	public void menu(){
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		p.add(new MenuItem("read"));
		p.add(new MenuItem("rename"));
		p.add(new MenuItem("update"));
		p.add(new MenuItem("set prmmission"));
		p.add(new MenuItem("add to DB"));
		MainMenu.add(p);
	}
	
	/*public void processMouseEvent(MouseEvent e){
		if(e.getID() == MouseEvent.MOUSE_RELEASED & e.isPopupTrigger())
			p.show(MainMenu, e.getX(),e.getY());
	}
	*/
	
	public JList getlist()
	{
		return this.list;
	}
}
	
	
	

