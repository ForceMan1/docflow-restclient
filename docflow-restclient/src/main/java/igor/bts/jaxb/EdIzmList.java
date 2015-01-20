package igor.bts.jaxb;



import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import igor.bts.jaxb.*;

@XmlRootElement
@XmlSeeAlso(EdIzmList.class)
public class EdIzmList {
	private List<EdIzm> edIzms;
	
	@XmlElement(name = "edizm")
	public List<EdIzm> getEdIzms(){
		return edIzms;
	}
	
	public void setEdIzms(List<EdIzm> edIzms){
		this.edIzms = edIzms;
	}

}
