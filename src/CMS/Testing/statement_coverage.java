package CMS.Testing;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.*;
import org.junit.Test;
import CMS.BLL.core.*;
import CMS.BLL.courier.*;
import CMS.BLL.franchise.*;
import CMS.UIL.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class statement_coverage {
	@Test
	public void admintest() {
		//basic get set checker
		Admin ad=new Admin();
		ad.setAll("E000011", "ali", "akbar", "g7 lahore", "03159188008", "6110109802603", "1-03-1996", 50000.0);
		assertEquals(ad.getID(),"E000011");
		assertEquals(ad.getFName(),"ali");
		assertEquals(ad.getLName(),"akbar");
		assertEquals(ad.getAddress(),"g7 lahore");
		assertEquals(ad.getContactNumber(),"03159188008");
		assertEquals(ad.getCNIC(),"6110109802603");
		assertEquals(ad.getDate_Of_Birth(), "1-03-1996");
		assertEquals(ad.getBasicSalary(),(50000.0),0);
	}
	
	@Test
	public void managertest() {
		//basic get set checker
		Manager ad=new Manager();
		ad.setAll("M00011", "ali", "akbar", "g7 lahore", "03159188008", "6110109802603", "1-03-1996", 50000.0,"BS", "A000001");
		assertEquals(ad.getMID(),"M00011");
		assertEquals(ad.getFName(),"ali");
		assertEquals(ad.getLName(),"akbar");
		assertEquals(ad.getAddress(),"g7 lahore");
		assertEquals(ad.getContactNumber(),"03159188008");
		assertEquals(ad.getCNIC(),"6110109802603");
		assertEquals(ad.getDate_Of_Birth(), "1-03-1996");
		assertEquals(ad.getBasicSalary(),(50000.0),0);
		assertEquals(ad.getQualification(),"BS");
		assertEquals(ad.getFID(),"A000001");
	}
	
	@Test
	public void Fanchisetest() {
		//basic get set checker
		Franchise ad=new Franchise();
		ad.setAll("F00011", "ali", "akbar", "g7 lahore", "03159188008");
		assertEquals(ad.getFID(),"F00011");
		assertEquals(ad.getName(),"ali");
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		assertEquals(ad.toString(),"F00011,ali,akbar,g7 lahore,03159188008,"+date);
	}
	
	@Test
	public void Allowancetest() {
		//basic get set checker
		Allowance ad=new Allowance();
		ad.setAll("ali", "akbar", 500.0);
		ad.setAll("A001","ali", "akbar", 500.0);
		assertEquals(ad.getDescription(),"ali");
		assertEquals(ad.getType(),"akbar");
		assertEquals(ad.getAmount(),500.0,0);
	}
	
	@Test
	public void Employeetest() {
		//basic get set checker
		Employee ad=new Employee();
		ad.setAll("ali", "akbar","G7 lahore","03159188008","6110109802603","1996-02-1","Nuces", "Employee" , 50000);
		ad.setAll( "ali", "akbar","G7 lahore","03159188008","6110109802603","1996-02-1");
		ad.setAll("E00011", "ali", "akbar","G7 lahore","03159188008","6110109802603","1996-02-1","Nuces", "Employee" , 50000);
		assertEquals(ad.getEID(),"E00011");
		assertEquals(ad.getFName(),"ali");
		assertEquals(ad.getLName(),"akbar");
		assertEquals(ad.getAddress(),"G7 lahore");
		assertEquals(ad.getContactNumber(),"03159188008");
		assertEquals(ad.getCNIC(),"6110109802603");
		assertEquals(ad.getDate_Of_Birth(),"1996-02-1");
		assertEquals(ad.getQualification(),"Nuces");
		assertEquals(ad.getDesignation(),"Employee");
		assertEquals(ad.getBasicSalary(),50000,0);
	}
	
	@Test
	public void RegisteredCouriertest() {
		//basic get set checker
		RegisteredCourier ad=new RegisteredCourier();
		//
		ad.setAll("R00000000000003", "Shakeel", "Islamkot", "03323881213","Gift",  11.0,512.0,  "E000007", "C000005");
		ad.setAll( "Shakeel", "Islamkot", "03323881213",512.0,"Gift",  11.0,"pending",  "E000007", "C000005");
		ad.setAll("R00000000000003", "Shakeel", "Islamkot", "03323881213","2016-11-27",512.0,"Gift",  11.0,"pending",  "E000007", "C000005");
		assertEquals(ad.getCID(),"R00000000000003");
		assertEquals(ad.getRName(),"Shakeel");
		assertEquals(ad.getRAddress(),"Islamkot");
		assertEquals(ad.getRContactNumber(),"03323881213");
		assertEquals(ad.getPrice(),512.0,0);
		assertEquals(ad.getRegDate(),"2016-11-27");
		assertEquals(ad.getWeight(),11.0,0);
		assertEquals(ad.getType(),"Gift");
		assertEquals(ad.getStatus(),"pending");
		assertEquals(ad.getCustomerID(),"C000005");
		assertEquals(ad.getRegisterBy(),"E000007");
	}
	
	@Test
	public void UnregisteredCouriertest() {
		//basic get set checker
		UnRegisteredCourier ad=new UnRegisteredCourier();
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		//
		ad.setAll("Arslan", "Dadu", "03323881213", date, 512.0, "Gift", 12.0, "delivered", "E000003", "Ramesh", "Kumar", "Mirpurkhas", "03323881213", "3520288854061");
		ad.setAll( "U00000000000001", "Arslan", "Dadu", "03323881213","Ramesh", "Kumar", "Mirpurkhas", "03323881213", "3520288854061", 512.0, "Gift", 12.0, "E000003" );
		ad.setAll("U00000000000001", "Arslan", "Dadu", "03323881213", "2016-11-27", 512.0, "Gift", 12.0, "delivered", "E000003", "Ramesh", "Kumar", "Mirpurkhas", "03323881213", "3520288854061");
		assertEquals(ad.getCID(),"U00000000000001");
		assertEquals(ad.getRName(),"Arslan");
		assertEquals(ad.getRAddress(),"Dadu");
		assertEquals(ad.getRContactNumber(),"03323881213");
		assertEquals(ad.getRegDate(),"2016-11-27");
		assertEquals(ad.getPrice(),512.0,0);
		assertEquals(ad.getType(),"Gift");
		assertEquals(ad.getWeight(),12.0,0);
		assertEquals(ad.getStatus(),"delivered");
		assertEquals(ad.getRegisterBy(),"E000003");
		assertEquals(ad.getSender(),"Ramesh Kumar");
	}
	
	@Test
	public void Loantest() {
		//basic get set checker
		Loan ad=new Loan();
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		ad.setAll("L00000000000001",50000.0,"Testing");
		assertEquals(ad.getAmount(),50000.0,0);
		assertEquals(ad.getDescription(),"Testing");
		assertEquals(ad.getIssueDate(),date.toString());
		assertEquals(ad.getLID(),"L00000000000001");
		ad.setAmount(50000.0);
		ad.setDescription("Helloworld");
		ad.setIssueDate("2016-11-27");
		ad.setLID("L00000000000001");
		assertEquals(ad.getAmount(),50000.0,0);
		assertEquals(ad.getDescription(),"Helloworld");
		assertEquals(ad.getIssueDate(),"2016-11-27");
		assertEquals(ad.getLID(),"L00000000000001");
		ad.getNewID();
		assertEquals(ad.getLID(),"L000001");
		assertEquals(ad.insertValue(),"('L000001',50000.0,'2016-11-27','Helloworld')");
	}
	
	@Test
	public void Ratetest() {
		//basic get set checker
		Rate ad=new Rate();
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		ad.setAll("0001","Lahore",50000.0,"Testing");
		assertEquals(ad.getID(),"0001");
		assertEquals(ad.getPrice(),50000.0,0);
		assertEquals(ad.getZone(),"Lahore");
		assertEquals(ad.getDesc(),"Testing");
		ad.setFID("F0001");
		assertEquals(ad.getFID(),"F0001");
		assertEquals(ad.insertValue(),"('0001','Lahore',50000.0,'Testing','F0001')");
		//assertEquals(ad.generateID(),"0001");not testing as table not in db
	}
	
	@Test
	public void Customertest() {
		//basic get set checker
		Customer ad=new Customer();
		ad.setAll("Waseem", "Raze", "Larkana", "03323881213", "3520288854061", "FAST NUCES Islamabad");
		ad.initAll("C000001", "Waseem", "Raze", "Larkana", "03323881213", "3520288854061","FAST NUCES Islamabad");
		ad.setAll("C000001", "Waseem", "Raze", "Larkana", "03323881213", "3520288854061", "FAST NUCES Islamabad");
		assertEquals(ad.getCID(),"C000001");
		assertEquals(ad.getFName(),"Waseem");
		assertEquals(ad.getLName(),"Raze");
		assertEquals(ad.getAddress(),"Larkana");
		assertEquals(ad.getContactNumber(),"03323881213");
		assertEquals(ad.getCNIC(),"3520288854061");
		assertEquals(ad.getOrganization(),"FAST NUCES Islamabad");
		ad.setJoiningDate("1-03-1996");
		assertEquals(ad.getJoiningDate(),"1-03-1996");
		ad.setStatus(true);
		assertEquals(ad.getStatus(),true);
		assertEquals(ad.generateID(),"C000006");
		String res=ad.insertValue();
		assertEquals(res,"('C000001', 'Waseem', 'Raze', 'Larkana', '03323881213', '3520288854061', '1-03-1996', 'FAST NUCES Islamabad',1)");
		res=ad.toString();
		assertEquals(res,"C000001,Waseem', 'Raze', 'Larkana', '03323881213', '3520288854061', 'null,FAST NUCES Islamabad");
	}
	
	@Test
	public void Integratedtest() {
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());
		Admin aad=new Admin();
		aad.setAll("E000011", "ali", "akbar", "g7 lahore", "03159188008", "6110109802603", "1-03-1996", 50000.0);
		Manager mad=new Manager();
		mad.setAll("M00011", "ali", "akbar", "g7 lahore", "03159188008", "6110109802603", "1-03-1996", 50000.0,"BS", "A000001");
		Franchise fad=new Franchise();
		fad.setAll("F00011", "ali", "akbar", "g7 lahore", "03159188008");
		Allowance alad=new Allowance();
		alad.setAll("ali", "akbar", 500.0);
		Employee ead=new Employee();
		ead.setAll("E00011", "ali", "akbar","G7 lahore","03159188008","6110109802603","1996-02-1","Nuces", "Employee" , 50000);
		RegisteredCourier rad=new RegisteredCourier();
		rad.setAll("R00000000000003", "Shakeel", "Islamkot", "03323881213","2016-11-27",512.0,"Gift",  11.0,"pending",  "E000007", "C000005");
		UnRegisteredCourier uad=new UnRegisteredCourier();
		uad.setAll("U00000000000001", "Arslan", "Dadu", "03323881213", "2016-11-27", 512.0, "Gift", 12.0, "delivered", "E000003", "Ramesh", "Kumar", "Mirpurkhas", "03323881213", "3520288854061");
		Loan lad=new Loan();
		lad.setAll("L00000000000001",50000.0,"Testing");
		Rate rate=new Rate();
		rate.setAll("0001","Lahore",50000.0,"Testing");
		
		mad.setJoiningDate(date);
		aad.addManager(mad);
		System.err.println("My msg");
		System.err.println(aad.getManager("M000001")+"");
		//assertEquals(aad.getManager("M00011")+"",);
		aad.updateManager(mad);
		//assertEquals(aad.fireManager("M000001"),true);
		//assertEquals(aad.fireManager("M000001"),false);
		assertEquals(aad.getManagerList().size()>0,true);
		assertEquals(aad.getFranchiseList().size()>0,true);
		assertEquals(aad.getList("Manager", "MID").size()>0,true);
		assertEquals(aad.getAllocatedAllowancesList().size()>0, true);
		assertEquals(aad.retreieveFranchiseList().size()>0,true);
		assertEquals(aad.retrieveFranchiseList().size()>0,true);
		
		
	}
	
}