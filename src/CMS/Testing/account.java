package CMS.Testing;


import static org.junit.Assert.*;
import org.junit.Test;
import CMS.BLL.core.Account;

public class account {
	
	@Test
	public void accountstest1() {
		Account acc=new Account();
		acc.setAll("atif", "12345");
		assertEquals("ID check ",acc.getUserID(),"atif");
		assertEquals("Password check",acc.getPassword(),"12345");
		assertEquals(acc.insertValues(), "('atif','12345')");
		assertEquals(acc.toString(), "atif,12345");
	}
	@Test
	public void accountstest2() {
		//admin account test
		Account acc=new Account();
		String res=acc.validateAccount("A000002", "larkana")+"";
		assertEquals(res, "Nilesh,Kumar,Larkana,03471386120,3520288854061,1990-05-03");		
	}
	@Test
	public void accountstest3() {
		//manager account test
		Account acc=new Account();
		String res=acc.validateAccount("M000003", "M000003")+"";
		assertEquals(res, "Nilesh,Kumar,Larkana,03323881213,3520288854061,1985-09-24,M000003,BBA,2016-11-26,75000.0,F000003");
	}
	@Test
	public void accountstest4() {
		//employee account test
		Account acc=new Account();
		String res=acc.validateAccount("E000002", "E000002")+"";
		assertEquals(res, "E000002,Nilesh', 'Kumar', 'Larkana', '03323881213', '3520288854061', '1989-10-25,Delivery boy,MS(CS),2011-11-26,10000.0,F000001");
	}
	@Test
	public void accountstest5() {
		//Null and empty checks
		Account acc=new Account();
		//assertEquals(acc.validateAccount("'M000003'", "KarachiKings"), 1064);//sql fail
		//assertEquals( acc.validateAccount("E000018", "LAstNight"), null);//wrong employee
		//assertEquals( acc.validateAccount("A000014","BlackBlood"),null);//wrong admin
		//assertEquals( acc.validateAccount("M000012","HAppynewyear"), null);//wrong manager
	}
	
	
	
}
