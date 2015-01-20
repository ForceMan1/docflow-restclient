package igor.bts.jaxb;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="ed_izm")
@XmlAccessorType(XmlAccessType.FIELD)
public class EdIzm implements Serializable{
	@XmlTransient
	public static final String ALL_ED_IZM = "ALL_ED_IZM";
	private Integer id;
	private String name;
	private String info;
	
	/***** Constructors ***************/
	public EdIzm(){}
	public EdIzm(String name, String info){
		this.name = name;
		this.info = info;
	}
	
	/****** Getters & Setters **********/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull @Size(max = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Size(max = 128)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if(o instanceof EdIzm){
			EdIzm edizm = (EdIzm)o;
			if(edizm.getName() != name)
				return false;
			return true;
		}else
			return false;
	}
}
