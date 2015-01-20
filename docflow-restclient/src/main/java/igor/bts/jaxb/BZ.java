package igor.bts.jaxb;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="bz")
@XmlAccessorType(XmlAccessType.FIELD)
public class BZ {
	@XmlTransient
	public static final String ALL_BZ = "ALL_BZ";
	private Integer id;
	private DocType type;
	private String number;  
	private Date date;
	private Date date_start;	
	private Date date_stop;
	private String speed_access;
	private String phone_list;
	private Boolean unlimit = true;
	private String address;
	private String info;
	private Boolean is_original = false;
	private Date original_date;
	private Dogovor dogovor;
	private List<Service> services;
	
	/***** Constructors ***********************/
	public BZ(){}
	public BZ(DocType type, String number, Date date, Date date_start, Date date_stop,
			String speed_access, String phone_list, Boolean unlimit, String address, String info,
			Boolean is_original, Date original_date, Dogovor dogovor, List<Service> services){
		this.type = type;
		this.number = number;
		this.date = date;
		this.date_start = date_start;
		this.date_stop = date_stop;
		this.speed_access = speed_access;
		this.phone_list = phone_list;
		this.unlimit = unlimit;
		this.address = address;
		this.info = info;
		this.is_original = is_original;
		this.original_date = original_date;
		this.dogovor = dogovor;
		this.services = services;
	}
	
	/****** Getters & Setters **********************/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull
	public DocType getType() {
		return type;
	}
	public void setType(DocType type) {
		this.type = type;
	}
	
	@NotNull @Size(max =10)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@NotNull
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate_start() {
		return date_start;
	}
	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}
	
	public Date getDate_stop() {
		return date_stop;
	}
	public void setDate_stop(Date date_stop) {
		this.date_stop = date_stop;
	}
	
	@Size(max = 30)
	public String getSpeed_access() {
		return speed_access;
	}
	public void setSpeed_access(String speed_access) {
		this.speed_access = speed_access;
	}
	
	@Size(max = 128)
	public String getPhone_list() {
		return phone_list;
	}
	public void setPhone_list(String phone_list) {
		this.phone_list = phone_list;
	}
	
	@NotNull
	public Boolean getUnlimit() {
		return unlimit;
	}
	public void setUnlimit(Boolean unlimit) {
		this.unlimit = unlimit;
	}
	
	@Size(max = 150)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Size(max = 255)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@NotNull
	public Boolean getIs_original() {
		return is_original;
	}
	public void setIs_original(Boolean is_original) {
		this.is_original = is_original;
	}
	
	public Date getOriginal_date() {
		return original_date;
	}
	public void setOriginal_date(Date original_date) {
		this.original_date = original_date;
	}
	
	public Dogovor getDogovor() {
		return dogovor;
	}
	public void setDogovor(Dogovor dogovor) {
		this.dogovor = dogovor;
	}
	
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	/* Methods */
	@Override
	public boolean equals(Object o){
		if( o instanceof BZ ){
			BZ bz = (BZ)o;
			if(bz.getDogovor() != dogovor || bz.getNumber() != number || bz.getType() != type) 
					return false;
			return true;
				
		}else
			return false;
	}
}
