package igor.bts.jaxb;

import java.util.List;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(TpInternetList.class)
public class TpInternetList {
	private List<TpInternet> tpInternets;
	
	@XmlElement(name = "tpInternet")
	public List<TpInternet> getTpInternets(){
		return tpInternets;
	}
	
	public void setTpInternets(List<TpInternet> tpInternets){
		this.tpInternets = tpInternets;
	}
}
