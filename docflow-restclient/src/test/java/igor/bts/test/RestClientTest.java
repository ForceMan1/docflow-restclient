package igor.bts.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import igor.bts.jaxb.BZ;
import igor.bts.jaxb.BZList;
import igor.bts.jaxb.Bank;
import igor.bts.jaxb.BankList;
import igor.bts.jaxb.Client;
import igor.bts.jaxb.ClientList;
import igor.bts.jaxb.DocType;
import igor.bts.jaxb.DocTypeList;
import igor.bts.jaxb.Dogovor;
import igor.bts.jaxb.DogovorList;
import igor.bts.jaxb.EdIzm;
import igor.bts.jaxb.EdIzmList;
import igor.bts.jaxb.Manager;
import igor.bts.jaxb.ManagerList;
import igor.bts.jaxb.Podpisant;
import igor.bts.jaxb.Service;
import igor.bts.jaxb.TpInternet;
import igor.bts.jaxb.TpInternetList;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;



public class RestClientTest {
	private static javax.ws.rs.client.Client client;
	private static final String urlBank = "http://127.0.0.1:8080/docflow/rs/admin/bank"; 
	private static final String urlManager = "http://127.0.0.1:8080/docflow/rs/admin/manager";
	private static final String urlDocType = "http://127.0.0.1:8080/docflow/rs/admin/doctype";
	private static final String urlEdIzm = "http://127.0.0.1:8080/docflow/rs/admin/edizm";
	private static final String urlTpInternet = "http://127.0.0.1:8080/docflow/rs/admin/tp_internet";
	private static final String urlClient = "http://127.0.0.1:8080/docflow/rs/client";
	private static final String urlDogovor = "http://127.0.0.1:8080/docflow/rs/dogovor";
	private static final String urlBZ = "http://127.0.0.1:8080/docflow/rs/bz";
	private static final String urlService = "http://127.0.0.1:8080/docflow/rs/service";
	
	@BeforeClass
	public static void createClient(){
		client = ClientBuilder.newClient();
	}
	//@Test
	public void testBank(){
		/* *********** Bank ********** */
		/* Create */
		Bank bank1 = new Bank("TestBank1", "111111111", null);
		Bank bank2 = new Bank("TestBank2", "222222222", null);
		Bank bank3 = new Bank("TestBank3", "333333333", null);
		Response resp = client.target(urlBank)
							.request(MediaType.APPLICATION_XML).post(Entity.entity(bank1, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		String urlBank1 = (String) resp.getMetadata().getFirst("Location");
		assertTrue(urlBank1 != null);
				
		resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(bank2,  MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(bank3,  MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
				
		/* Get list */
		resp = client.target("http://127.0.0.1:8080/docflow/rs/admin/bank")
				.request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		BankList listBanks = resp.readEntity(BankList.class);
		
		/* Get bank1 */
		resp = client.target(urlBank1).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		Bank b1 = resp.readEntity(Bank.class);
		assertTrue(b1 != null);
		assertTrue(b1.equals(bank1));
		
		/* Update b1 */
		b1.setName("NewName");
		resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(b1, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 200);
		b1 = resp.readEntity(Bank.class);
		assertTrue(b1.getName().equals("NewName"));

		/* Get list */
		resp = client.target("http://127.0.0.1:8080/docflow/rs/admin/bank")
				.request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		listBanks = resp.readEntity(BankList.class);
		
		
		/* Delete all entities */
		for(Bank b: listBanks.getBanks()){
			System.out.println(b.getName() + "" + b.getId());
		
			WebTarget target = client.target(urlBank).path(b.getId().toString());
			resp = target.request(MediaType.APPLICATION_XML).delete();
			assertEquals(resp.getStatus(), 204);
			// Check for correct deleting
			resp = client.target(urlBank).path(b.getId().toString())
					.request(MediaType.APPLICATION_XML).get();
			assertEquals(resp.getStatus(), 404);
			assertTrue(b != null);
		}
	}	
	
	//@Test
	public void testDocType(){
		DocType docType1 = new DocType("name1", "info", 0, (short)0);
		DocType docType2 = new DocType("name2", "info", 0, (short)0);
		DocType docType3 = new DocType("name3", "info", 0, (short)0);
		
		Response resp = client.target(urlDocType).request(MediaType.APPLICATION_XML_TYPE)
				.post(Entity.entity(docType1, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		String urlDocType1 = (String)resp.getMetadata().getFirst("Location");
		resp = client.target(urlDocType1).request(MediaType.APPLICATION_XML)
				.get();
		assertEquals(resp.getStatus(), 200);
		
		resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(docType2, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(docType3, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		DocType docType = resp.readEntity(DocType.class);
		
		// Update
		docType1.setName("newName");
		resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(docType1, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 200);
		docType1 = resp.readEntity(DocType.class);
		assertEquals(docType1.getName(), "newName");
		
		// Get list DocTypes
		resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
				.get();
		assertEquals(resp.getStatus(), 200);
		DocTypeList dts = resp.readEntity(DocTypeList.class); 
		assertTrue(dts != null);
				
		for(DocType dt : dts.getDocTypes()){
			System.out.println(dt.getName() + " " + dt.getId());
			resp = client.target(urlDocType).path(dt.getId().toString()).request().delete();
			assertEquals(resp.getStatus(), 204);
			resp = client.target(urlDocType).path(dt.getId().toString()).request().get();
			assertEquals(resp.getStatus(), 404);
		}
		
		
		
	}
	
	//@Test
	public void testEdIzm(){
		EdIzm edIzm1 = new EdIzm("name1", "info1");
		EdIzm edIzm2 = new EdIzm("name2", "info2");
		EdIzm edIzm3 = new EdIzm("name3", "info3");
		
		Response resp = client.target(urlEdIzm).request()
			.post(Entity.entity(edIzm1, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		String urlEdIzm1 = (String)resp.getMetadata().getFirst("Location");
		resp = client.target(urlEdIzm1).request(MediaType.APPLICATION_XML)
				.get();
		assertEquals(resp.getStatus(), 200);
		edIzm1 = resp.readEntity(EdIzm.class);
		
		resp = client.target(urlEdIzm).request()
				.post(Entity.entity(edIzm2, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		resp = client.target(urlEdIzm).request()
				.post(Entity.entity(edIzm3, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		//Update
		resp = client.target(urlEdIzm).path(edIzm1.getId().toString())
				.request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		edIzm1 = resp.readEntity(EdIzm.class);
		edIzm1.setName("New edIzm name");
		resp = client.target(urlEdIzm).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(edIzm1, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 200);
		edIzm1 = resp.readEntity(EdIzm.class);
		assertTrue(edIzm1.getName().equals("New edIzm name"));
		
		//Get list
		resp = client.target(urlEdIzm).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		EdIzmList list = resp.readEntity(EdIzmList.class);
		assertTrue(list != null);
		
		for(EdIzm ei : list.getEdIzms()){
			resp = client.target(urlEdIzm).path(ei.getId().toString())
					.request().delete();
			assertEquals(resp.getStatus(), 204);
			resp = client.target(urlEdIzm).path(ei.getId().toString()).request().get();
			assertEquals(resp.getStatus(), 404);
		}
	}
	
	//@Test
	public void testManager(){
		Manager manager1 = new Manager("fname1", "sname1");
		Manager manager2 = new Manager("fname2", "sname2");
		Manager manager3 = new Manager("fname3", "sname3");
		
		Response resp = client.target(urlManager).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(manager1, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		String urlManager1 = (String)resp.getMetadata().getFirst("Location");
		resp = client.target(urlManager1).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		manager1 = resp.readEntity(Manager.class);
		
		resp = client.target(urlManager).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(manager2, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		resp = client.target(urlManager).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(manager3, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		//Update
		manager1.setFullname("New fullname");
		resp = client.target(urlManager).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(manager1, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 200);
		manager1 = resp.readEntity(Manager.class);
		assertTrue(manager1.getFullname().equals("New fullname"));
		
		// Get list
		resp = client.target(urlManager).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		ManagerList list = resp.readEntity(ManagerList.class);
		assertTrue(list != null && list.getManagers() != null);
		for(Manager m : list.getManagers()){
			resp = client.target(urlManager).path(m.getId().toString())
					.request().delete();
			assertEquals(resp.getStatus(), 204);
			resp = client.target(urlManager).path(m.getId().toString())
					.request().get();
			assertEquals(resp.getStatus(), 404);
		}
	}
	
	//@Test
	public void testTpInternet(){
		TpInternet t1 = new TpInternet(new BigDecimal(0), new BigDecimal(0),
				(byte)0, new BigDecimal(0), true, new BigDecimal(0));
		TpInternet t2 = new TpInternet(new BigDecimal(0), new BigDecimal(0),
				(byte)0, new BigDecimal(0), true, new BigDecimal(0));
		TpInternet t3 = new TpInternet(new BigDecimal(0), new BigDecimal(0),
				(byte)0, new BigDecimal(0), true, new BigDecimal(0));
		
		Response resp = client.target(urlTpInternet).request()
				.post(Entity.entity(t1, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		String urlTpInternet1 = (String)resp.getMetadata().getFirst("Location");
		resp = client.target(urlTpInternet1).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		t1 = resp.readEntity(TpInternet.class);
		assertTrue(t1 != null && t1.getId() != null);
		
		//Update
		t1.setEd_izm_incl((byte)2);
		resp = client.target(urlTpInternet).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(t1, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 200);
		t1 = resp.readEntity(TpInternet.class);
		assertTrue(t1.getEd_izm_incl() == (byte)2);
		
		//Get list
		resp = client.target(urlTpInternet).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		TpInternetList list = resp.readEntity(TpInternetList.class); 
		
		//Delete
		for(TpInternet t : list.getTpInternets()){
			resp = client.target(urlTpInternet).path(t.getId().toString())
					.request().delete();
			assertEquals(resp.getStatus(), 204);
			resp = client.target(urlTpInternet).path(t.getId().toString())
					.request().get();
			assertEquals(resp.getStatus(), 404);
		}
	}
	
	@Test
	public void testClient(){
		Manager manager = new Manager("test", "test");
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		Podpisant podpisant = new Podpisant("fio", "dolzhnost", "", "", null, "");
		
		//Create manager
		Response resp = client.target(urlManager).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(manager, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		String url = (String)resp.getMetadata().getFirst("Location");
		resp = client.target(url).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		manager = resp.readEntity(Manager.class);
		resp = client.target(urlManager).path(manager.getId().toString()).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		manager = resp.readEntity(Manager.class);
		assertTrue(manager.getId() != null);
		assertTrue(manager != null && manager.getId() != null);
		//Create Bank
		resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(bank, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		url = (String)resp.getMetadata().getFirst("Location");
		resp = client.target(url).request(MediaType.APPLICATION_XML).get();
		bank = resp.readEntity(Bank.class);
		assertTrue(bank!= null && bank.getId() != null);
		resp = client.target(urlBank).path(bank.getId().toString())
				.request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		bank = resp.readEntity(Bank.class);
		assertTrue(bank.getId() != null);
		//Create Client
		Client cl = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		//Client cl = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
		//		true, "1234567890", "1234567890", "okpo", null, "2299499", 
		//		false, "", "", null, null, null, null, null);
		resp = client.target(urlClient).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(cl, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		//Update client
		cl.setFullname("Жуков");
		resp = client.target(urlClient).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(cl, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 200);
		cl = resp.readEntity(Client.class);
		assertTrue(cl.getFullname().equals("Жуков"));
		
		// Get List
		resp = client.target(urlClient).request(MediaType.APPLICATION_XML)
				.get();
		assertEquals(resp.getStatus(), 200);
		ClientList list = resp.readEntity(ClientList.class);
		assertEquals(list.getClients().size(), 1);

		// Delete clients
		for(Client c : list.getClients()){
			resp = client.target(urlClient).path(c.getId().toString()).request().delete();
			assertEquals(resp.getStatus(), 204);
			resp = client.target(urlClient).path(c.getId().toString()).request().get();
			assertEquals(resp.getStatus(), 404);
		}
		
		//Delete Alls
		resp = client.target(urlManager).path(manager.getId().toString()).request().delete();
		resp = client.target(urlBank).path(bank.getId().toString()).request().delete();
	}
	
	//@Test
	public void testDogovor(){
		Manager manager = new Manager("test", "test");
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		Podpisant podpisant = new Podpisant("fio", "dolzhnost", "", "", null, "");
				
		//Create manager
		Response resp = client.target(urlManager).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(manager, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		manager = resp.readEntity(Manager.class);
		//Create Bank
		resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(bank, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		bank = resp.readEntity(Bank.class);
		//Create Client
		Client cl = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		resp = client.target(urlClient).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(cl, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		//Create DocType
		DocType docType = new DocType("name", "info", 0, (short)0);
		resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(docType, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		docType = resp.readEntity(DocType.class);
		// Create dogovor
		Dogovor dogovor = new Dogovor(docType, "13/011", new Date(), new Date(), "info", false, null, 
				cl, null);
		resp = client.target(urlDogovor).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(dogovor, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		assertTrue(dogovor != null && dogovor.getId() != null );
		
		// Update dogovor
		dogovor.setNumber("15//02");
		resp = client.target(urlDogovor).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(dogovor, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 200);
		dogovor = resp.readEntity(Dogovor.class);
		assertTrue(dogovor.getNumber().equals("15//02"));
		
		// Get List Dogovors
		resp =  client.target(urlDogovor).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		DogovorList list = resp.readEntity(DogovorList.class);
		
		//Delete Dogovors
		for(Dogovor d : list.getDogovors()){
			resp = client.target(urlDogovor).path(d.getId().toString()).request().delete();
			assertEquals(resp.getStatus(), 204);
			resp = client.target(urlDogovor).path(d.getId().toString()).request().get();
			assertEquals(resp.getStatus(), 404);
					
		}
		
		//Delete all 
		resp = client.target(urlClient).path(cl.getId().toString()).request().delete();
		resp = client.target(urlBank).path(bank.getId().toString()).request().delete();
		resp = client.target(urlManager).path(manager.getId().toString()).request().delete();
		resp = client.target(urlDocType).path(docType.getId().toString()).request().delete();
	}
	
	//@Test
	public void testBZ(){
		Manager manager = new Manager("test", "test");
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		Podpisant podpisant = new Podpisant("fio", "dolzhnost", "", "", null, "");
				
		//Create manager
		Response resp = client.target(urlManager).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(manager, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		manager = resp.readEntity(Manager.class);
		//Create Bank
		resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(bank, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		bank = resp.readEntity(Bank.class);
		//Create Client
		Client cl = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		resp = client.target(urlClient).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(cl, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		//Create DocType
		DocType docType = new DocType("name", "info", 0, (short)0);
		resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(docType, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		docType = resp.readEntity(DocType.class);
		// Create dogovor
		Dogovor dogovor = new Dogovor(docType, "13/011", new Date(), new Date(), "info", false, null, 
				cl, null);
		resp = client.target(urlDogovor).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(dogovor, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		assertTrue(dogovor != null && dogovor.getId() != null );
		
		//Create BZ
		BZ bz = new BZ(docType, "1", new Date(), new Date(), new  Date(), "1024 Kbits/s",
				"2299499", true, "ул.Ленина, 28", "comment", false, null, dogovor, null );
		resp = client.target(urlBZ).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(bz, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		//Update BZ
		bz.setNumber("002");
		resp = client.target(urlBZ).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(bz, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 200);
		bz = resp.readEntity(BZ.class);
		
		//Get list
		resp = client.target(urlBZ).request(MediaType.APPLICATION_XML).get();
		assertEquals(resp.getStatus(), 200);
		BZList list = resp.readEntity(BZList.class);
		
		//Delete BZs
		for( BZ b : list.getBzs() ){
			resp = client.target(urlBZ).path(b.getId().toString()).request().delete();
			assertEquals(resp.getStatus(), 204);
			resp = client.target(urlBZ).path(b.getId().toString()).request().get();
			assertEquals(resp.getStatus(), 404);
		}
		
		//Delete all
		resp = client.target(urlClient).path(cl.getId().toString()).request().delete();
		resp = client.target(urlBank).path(bank.getId().toString()).request().delete();
		resp = client.target(urlManager).path(manager.getId().toString()).request().delete();
		resp = client.target(urlDocType).path(docType.getId().toString()).request().delete();
		resp = client.target(urlDogovor).path(dogovor.getId().toString()).request().delete();
	}
	
	//@Test
	public void testService(){
		Manager manager = new Manager("test", "test");
		Bank bank = new Bank("TestBank", "111111111", "2222222222");
		Podpisant podpisant = new Podpisant("fio", "dolzhnost", "", "", null, "");
				
		//Create manager
		Response resp = client.target(urlManager).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(manager, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		manager = resp.readEntity(Manager.class);
		//Create Bank
		resp = client.target(urlBank).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(bank, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		bank = resp.readEntity(Bank.class);
		//Create Client
		Client cl = new Client("Конин, ИП", "ИП Конин", "450022, г.Уфа", "450000, ул.Ленина, 28",
				true, "1234567890", "1234567890", "okpo", podpisant, "2299499", 
				false, "", "", manager, bank, null, null, null);
		resp = client.target(urlClient).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(cl, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		//Create DocType
		DocType docType = new DocType("name", "info", 0, (short)0);
		resp = client.target(urlDocType).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(docType, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		docType = resp.readEntity(DocType.class);
		// Create dogovor
		Dogovor dogovor = new Dogovor(docType, "13/011", new Date(), new Date(), "info", false, null, 
				cl, null);
		resp = client.target(urlDogovor).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(dogovor, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		assertTrue(dogovor != null && dogovor.getId() != null );
		
		//Create BZ
		BZ bz = new BZ(docType, "1", new Date(), new Date(), new  Date(), "1024 Kbits/s",
				"2299499", true, "ул.Ленина, 28", "comment", false, null, dogovor, null );
		resp = client.target(urlBZ).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(bz, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		//Create TpInternet
		TpInternet tpInternet = new TpInternet(new BigDecimal(0), new BigDecimal(0),
				(byte)0, new BigDecimal(0), true, new BigDecimal(0));
		resp = client.target(urlTpInternet).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(tpInternet, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		tpInternet = resp.readEntity(TpInternet.class);
		
		//Create EdIzm
		EdIzm edIzm = new EdIzm("name", "info");
		resp = client.target(urlEdIzm).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(edIzm, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		edIzm = resp.readEntity(EdIzm.class);
		//Create Service
		Service service = new Service(docType, true, edIzm, new BigDecimal(2000),
				new BigDecimal(2), new BigDecimal(4000), tpInternet, new HashSet(), bz);
		resp = client.target(urlService).request(MediaType.APPLICATION_XML)
				.post(Entity.entity(service, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 201);
		
		// Update
		service.setInfo("ggggg");
		resp = client.target(urlService).request(MediaType.APPLICATION_XML)
				.put(Entity.entity(service, MediaType.APPLICATION_XML));
		assertEquals(resp.getStatus(), 200);
		service = resp.readEntity(Service.class);
		assertTrue(service.getInfo().equals("ggggg"));
	}
	
	
}
