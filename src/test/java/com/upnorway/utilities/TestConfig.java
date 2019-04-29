package com.upnorway.utilities;
public class TestConfig{


	
	public static String server="smtp.gmail.com";
	public static String from = "arshad.lk@gmail.com";
	public static String password = "tingba";
	public static String[] to ={"arshadm@99x.lk"};
	public static String subject = "Up Norway Smoke Test Report";
	
	public static String messageBody ="TestMessage";
	public static String attachmentPath="c:\\screenshot\\2017_10_3_14_49_9.jpg";
	public static String attachmentName="error.jpg";
	
	
	
	//SQL DATABASE DETAILS	
	public static String driver="net.sourceforge.jtds.jdbc.Driver"; 
	public static String dbConnectionUrl="jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval"; 
	public static String dbUserName="sa"; 
	public static String dbPassword="$ql$!!1"; 
	
	
	//MYSQL DATABASE DETAILS
	public static String mysqldriver="com.mysql.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "selenium";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/acs";
	
	
	
	
	
	
	
	
	
}
