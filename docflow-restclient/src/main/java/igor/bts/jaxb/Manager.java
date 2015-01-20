package igor.bts.jaxb;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="manager")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Manager implements Serializable{
	@XmlTransient
	public static final String ALL_MANAGER = "ALL_MANAGER";
	private Integer id;
	private String fullname;
	private String smallname;
	
	/********** Constructors ********/
	public Manager(){}
	public Manager(String fullname, String smallname){
		this.fullname = fullname;
		this.smallname = smallname;
	}
	
	/********* Setters & Getters *********/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull @Size(max = 150)
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@NotNull @Size(max = 100)
	public String getSmallname() {
		return smallname;
	}
	public void setSmallname(String smallname) {
		this.smallname = smallname;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if(o instanceof Manager){
			Manager m = (Manager)o;
			if(m.getFullname() != fullname)
				return false;
			return true;
		}else
			return false;
	}
}
