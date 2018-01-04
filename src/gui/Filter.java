package gui;

import java.io.Serializable;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Filter implements Serializable {
	private JRadioButton rdbtnAnd = new JRadioButton("and");
	private JRadioButton rdbtnOr = new JRadioButton("or");
	private JRadioButton rdbtnNot = new JRadioButton("not");
	private JCheckBox chckbxDate = new JCheckBox("Date");
	private JCheckBox chckbxRadius = new JCheckBox("Radius");
	private JCheckBox chckbxID = new JCheckBox("ID");
	private JTextField textFieldBefore = new JTextField();
	private JTextField textFieldAfter = new JTextField();
	private JTextField TextFieldID = new JTextField();
	private JTextField textFieldRadius = new JTextField();
	private JTextField textFieldLat = new JTextField();
	private JTextField textFieldLon = new JTextField();	

	/**
	 * @return the textFieldBefore
	 */
	public JTextField getTextFieldBefore() {
		return textFieldBefore;
	}
	/**
	 * @param textFieldBefore the textFieldBefore to set
	 */
	public void setTextFieldBefore(JTextField textFieldBefore) {
		this.textFieldBefore = textFieldBefore;
	}
	/**
	 * @return the textFieldAfter
	 */
	public JTextField getTextFieldAfter() {
		return textFieldAfter;
	}
	/**
	 * @param textFieldAfter the textFieldAfter to set
	 */
	public void setTextFieldAfter(JTextField textFieldAfter) {
		this.textFieldAfter = textFieldAfter;
	}
	/**
	 * @return the TextFieldID
	 */
	public JTextField getTextFieldID() {
		return TextFieldID;
	}
	/**
	 * @param TextFieldID the TextFieldID to set
	 */
	public void setTextFieldID(JTextField TextFieldID) {
		this.TextFieldID = TextFieldID;
	}
	/**
	 * @return the textFieldRadius
	 */
	public JTextField getTextFieldRadius() {
		return textFieldRadius;
	}
	/**
	 * @param textFieldRadius the textFieldRadius to set
	 */
	public void setTextFieldRadius(JTextField textFieldRadius) {
		this.textFieldRadius = textFieldRadius;
	}
	/**
	 * @return the textFieldLat
	 */
	public JTextField getTextFieldLat() {
		return textFieldLat;
	}
	/**
	 * @param textFieldLat the textFieldLat to set
	 */
	public void setTextFieldLat(JTextField textFieldLat) {
		this.textFieldLat = textFieldLat;
	}
	/**
	 * @return the textFieldLon
	 */
	public JTextField getTextFieldLon() {
		return textFieldLon;
	}
	/**
	 * @param textFieldLon the textFieldLon to set
	 */
	public void setTextFieldLon(JTextField textFieldLon) {
		this.textFieldLon = textFieldLon;
	}
	/**
	 * @return the rdbtnAnd
	 */
	public JRadioButton getRdbtnAnd() {
		return rdbtnAnd;
	}
	/**
	 * @param rdbtnAnd the rdbtnAnd to set
	 */
	public void setRdbtnAnd(JRadioButton rdbtnAnd) {
		this.rdbtnAnd = rdbtnAnd;
	}
	/**
	 * @return the rdbtnOr
	 */
	public JRadioButton getRdbtnOr() {
		return rdbtnOr;
	}
	/**
	 * @param rdbtnOr the rdbtnOr to set
	 */
	public void setRdbtnOr(JRadioButton rdbtnOr) {
		this.rdbtnOr = rdbtnOr;
	}
	/**
	 * @return the rdbtnNot
	 */
	public JRadioButton getRdbtnNot() {
		return rdbtnNot;
	}
	/**
	 * @param rdbtnNot the rdbtnNot to set
	 */
	public void setRdbtnNot(JRadioButton rdbtnNot) {
		this.rdbtnNot = rdbtnNot;
	}
	/**
	 * @return the chckbxDate
	 */
	public JCheckBox getChckbxDate() {
		return chckbxDate;
	}
	/**
	 * @param chckbxDate the chckbxDate to set
	 */
	public void setChckbxDate(JCheckBox chckbxDate) {
		this.chckbxDate = chckbxDate;
	}
	/**
	 * @return the chckbxRadius
	 */
	public JCheckBox getChckbxRadius() {
		return chckbxRadius;
	}
	/**
	 * @param chckbxRadius the chckbxRadius to set
	 */
	public void setChckbxRadius(JCheckBox chckbxRadius) {
		this.chckbxRadius = chckbxRadius;
	}
	/**
	 * @return the chckbxID
	 */
	public JCheckBox getchckbxID() {
		return chckbxID;
	}
	/**
	 * @param chckbxID the chckbxID to set
	 */
	public void setchckbxID(JCheckBox chckbxID) {
		this.chckbxID = chckbxID;
	}
	/**
	 * @return the lblDateBefore
	 */


}
