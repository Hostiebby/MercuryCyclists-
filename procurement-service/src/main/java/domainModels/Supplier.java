package domainModels;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Supplier {
	@Column(unique=true)
	private @Id String companyName;
	private String base;
	
	@OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Contact> contacts;
	
	//@OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//private Set<Part> parts;
	
	Supplier() {}
	
	Supplier(String companyName, String base){
		this.companyName = companyName;
		this.base = base;
//		this.contacts = Collections.<Contact>emptySet();
	}
	
	public Set<Contact> displayContacts() {
		return this.contacts;
	}
	
	public void pushContact(Contact contact) {
		this.contacts.add(contact);
	}
	
	/*public Set<Part> displayParts() {
		return this.parts;
	}*/
	
	/*public void pushPart(Part part) {
		this.parts.add(part);
	}*/
	
	public String getCompanyName() {
		return this.companyName;
	}
	
	public String getBase() {
		return this.base;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public void setBase(String base) {
		this.base = base;
	}
	
	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
	    if (!(o instanceof Supplier))
	    	return false;
	    Supplier supplier = (Supplier) o;
	    return Objects.equals(this.companyName, supplier.companyName) && 
	    		Objects.equals(this.base, supplier.base);
	  }

	@Override
	public int hashCode() {
		return Objects.hash(this.companyName, this.base);
	}
}