package domainModels;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {
	
	private @Id String name;
	private String phone;
	private String email;
	private String position;
	private String companyName;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "supplier_companyName", nullable = false)
	private Supplier supplier;
	
	Contact() {}
	
	Contact(String name, String phone, String email, String position, String companyName){
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.position = position;
		this.companyName = companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyName() {
		return this.companyName;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public Supplier getSupplier() {
		return this.supplier;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
	    if (!(o instanceof Contact))
	    	return false;
	    Contact contact = (Contact) o;
	    return Objects.equals(this.name, contact.name) && 
	    		Objects.equals(this.phone, contact.phone) &&
	    		Objects.equals(this.email,  contact.email) &&
	    		Objects.equals(this.position, contact.position) &&
	    		Objects.equals(this.supplier, contact.supplier);
	  }

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.phone, this.email, this.position, this.supplier);
	}
}