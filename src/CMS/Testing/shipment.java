package CMS.Testing;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import CMS.BLL.courier.Shipment;

public class shipment {
	@Test
	public void shipmenttest1() {
		Shipment ship=new Shipment();
		ship.setSID("000000000000001");
		ship.setCID("R00000000000002");
		ship.setDeliveredBy("123");
		ship.setVehicleNumber("123");
		ship.setDeliveredOn(new Date(0));
		assertEquals(ship.getSID(),"000000000000001");		
		assertEquals(ship.getCID(),"R00000000000002");
		assertEquals(ship.getDeliveredBy(),"123");
		assertEquals(ship.getVehicleNumber(),"123");
		assertEquals(ship.getDeliveredOn(),new Date(0));
	}
}
