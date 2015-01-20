package igor.bts.jaxb;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="bank")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Bank implements Serializable {
	@XmlTransient
	public static final String ALL_BANK = "ALL_BANK";
	private Integer id;
	private String name;
	private String bik;
	private String okpo;
	
	/***** Constructors ***************/
	public Bank(){}
	public Bank(String name, String bik, String okpo){
		this.name = name;
		this.bik = bik;
		this.okpo = okpo;
	}
	
	/****** Setters & Getters ***************/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotNull @Size(max=255)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@NotNull @Pattern(regexp = "\\d{9}")
	public String getBik() {
		return bik;
	}
	public void setBik(String bik) {
		this.bik = bik;
	}
	
	@Pattern(regexp="\\d{10}") 
	public String getOkpo() {
		return okpo;
	}
	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if(o instanceof Bank){
			Bank b = (Bank)o;
			if(!b.getBik().equals(bik))
				return false;
			return true;
		}else
			return false;
		
	}
	 
	 
}
