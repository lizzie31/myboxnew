package controllers;


import java.awt.CardLayout;
import java.awt.Container;

import view.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;

import client.myboxapp;
import Model.Envelope;
import Model.User;
import Model.file;


public class userMainMenuController extends AbstractTransfer{
	
	protected userMainMenuGUI CurrGui=null;
	private logInCon prevController;
	private User userDetails;


	private Envelope en;
	

	public userMainMenuController(userMainMenuGUI menu, logInCon lastCon,User user) {
		this.CurrGui=menu;
		prevController=lastCon;
		userDetails=user;
		CurrGui.addcreatenewfile(new ButtoncreatenewfileListener());
		CurrGui.addcreatenewfolder(new ButtoncreatenewfolderListener());
		CurrGui.addleaveEntergruop(new ButtonAddleaveAGrouprListener());
		CurrGui.addshowgruops(new ButtonshowgrouprListener());
		CurrGui.addLogOut(new LogOutListener());
		// TODO Auto-generated constructor stub
	}

	private class ButtonshowgrouprListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonshowgroupPressed();
		}
		
	}
	private void buttonshowgroupPressed() {
		
		en=new Envelope(userDetails,"show user interest groups");
		sendToServer(en);
		myboxapp.clien.setCurrObj(this);

		
	}
	 class LogOutListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UpdateDB(); //update the user to status 0 = logout
				CurrGui.dispose();
				prevController.getLoginG().ClearText();
				prevController.getLoginG().setVisible(true);
				
			}
	 }
	 
	 class ListSelectionListener implements javax.swing.event.ListSelectionListener{

			public void valueChanged(ListSelectionEvent e) {
				int flag = 0;
				 String s=(String)CurrGui.getlist().getSelectedValue();
				 System.out.println(""+s);
				 //CurrGui.showMenu();
				
			}
			 
		 }



	private class ButtoncreatenewfileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncreatenewfilePressed();
		}
		
	}
	
	private void buttoncreatenewfilePressed() {
		CurrGui.close();
		createNewFileGUI CNFG=new createNewFileGUI ();
		new createNewFileController(CNFG,this);
		CNFG.setVisible(true);
	}
	private class ButtoncreatenewfolderListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttoncreatenewfolderPressed();
		}
		
	}
	
	private void buttoncreatenewfolderPressed() {
		CurrGui.close();
		createNewFolderGUI CNFOG=new createNewFolderGUI ();
		new createNewFolderController(CNFOG,this);
	    CNFOG.setVisible(true);
	}
	public userMainMenuGUI getusermainmenu() {
		return CurrGui;
	}
	
	private class ButtonAddleaveAGrouprListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buttonAddleaveAGroupPressed();
		}
		
	}
	
	private void buttonAddleaveAGroupPressed() {
		CurrGui.close();
		EnterOrLeaveGroupGUI ALG=new EnterOrLeaveGroupGUI ();
		new EnterOrLeaveGroupController(ALG,this);
		ALG.setVisible(true);
	}
	
	public void UpdateDB(){
		//update the status of  current  user to 0 - logoff
		en=new Envelope(userDetails,"log out status");
		 sendToServer(en);
		 userDetails.setStatus(0);
	}
	public User getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}
	
	public void handleDBResult(Object message) {
	userDetails = (User)message;
	CurrGui.close();
	this.setUserDetails((User)userDetails);
	groupListGUI SG=new groupListGUI (userDetails);
	new GroupsListController(SG,this);
	SG.setVisible(true);
	}
	
}
