package igor.bts.jaxb;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="service")
@XmlAccessorType(XmlAccessType.FIELD)
public class Service {
	@XmlTransient
	public static final String ALL_SERVICE = "ALL_SERVICE";
	private Integer id;
	private DocType type;
	private Boolean is_periodic;
	private EdIzm edIzm;
	private BigDecimal cost;
	private BigDecimal kol;
	private BigDecimal stoimost;
	private TpInternet tpInternet;
	private String info;
	private Set<Integer> ids_billing;
	private BZ bz;
	
	/**** Constructors   **********************/
	public Service(){}
	public Service(DocType type, Boolean is_periodic, EdIzm edIzm, BigDecimal cost, BigDecimal kol,
			BigDecimal stoimost, TpInternet tpInternet, Set<Integer> ids_billing, BZ bz){
		this.type = type;
		this.is_periodic = is_periodic;
		this.edIzm = edIzm;
		this.cost = cost;
		this.kol = kol;
		this.stoimost = stoimost;
		this.tpInternet = tpInternet;
		this.ids_billing = ids_billing;
		this.bz = bz;
	}
	
	/****** Setters & Getters ****************/
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
	public void setType(DocType docType) {
		this.type = docType;
	}
	
	@NotNull
	public Boolean getIs_periodic() {
		return is_periodic;
	}
	public void setIs_periodic(Boolean is_periodic) {
		this.is_periodic = is_periodic;
	}
	
	@NotNull
	public EdIzm getEdIzm() {
		return edIzm;
	}
	public void setEdIzm(EdIzm edIzm) {
		this.edIzm = edIzm;
	}
	
	@NotNull @Digits(integer = 14, fraction = 4)
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	@NotNull @Digits(integer = 14, fraction = 4)
	public BigDecimal getKol() {
		return kol;
	}
	public void setKol(BigDecimal kol) {
		this.kol = kol;
	}
	
	@Digits(integer = 14, fraction = 4)
	public BigDecimal getStoimost() {
		return stoimost;
	}
	public void setStoimost(BigDecimal stoimost) {
		this.stoimost = stoimost;
	}
	
	public TpInternet getTpInternet() {
		return tpInternet;
	}
	public void setTpInternet(TpInternet tpInternet) {
		this.tpInternet = tpInternet;
	}
	
	@Size(max = 128)
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public Set<Integer> getIds_billing() {
		return ids_billing;
	}
	public void setIds_billing(Set<Integer> ids_billing) {
		this.ids_billing = ids_billing;
	}
	
	public BZ getBz() {
		return bz;
	}
	public void setBz(BZ bz) {
		this.bz = bz;
	}
	
	/* Methods */
	@Override 
	public boolean equals(Object o){
		if(o instanceof Service){
			Service service = (Service)o;
			if(service.getBz() != bz || service.getEdIzm() != edIzm 
					|| service.getCost() != cost || service.getKol() != kol
					|| service.getStoimost() != stoimost)
				return false;
			return true;
		}else
			return false;
	}
}
