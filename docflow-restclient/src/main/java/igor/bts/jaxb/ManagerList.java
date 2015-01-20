package igor.bts.jaxb;


import java.util.List;
import igor.bts.jaxb.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Manager.class)
public class ManagerList {
	private List<Manager> managers = null;
	
	@XmlElement(name = "manager")
	public List<Manager> getManagers(){
		return managers;
	}
	
	public void setManagers(List<Manager> managers){
		this.managers = managers;
	}
}