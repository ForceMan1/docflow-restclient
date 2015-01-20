package igor.bts.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(DocType.class)
public class DocTypeList {
	private List<DocType> docTypes = null;
	
	@XmlElement(name = "doctype")
	public List<DocType> getDocTypes(){
		return docTypes;
	}
	
	public void setDocTypes(List<DocType> docTypes){
		this.docTypes = docTypes;
	}
}
