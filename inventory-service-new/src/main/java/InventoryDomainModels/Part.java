package InventoryDomainModels;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parts")
public class Part {
	
	private @Id Integer partId;
	private String name;
	private String description;
	private String companyName;
	private Integer productId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "product_productId", nullable = false)
	private Product product;
	
	Part() {}
	
	Part(Integer partId, String name, String description, String companyName, Integer productId){
		this.partId = partId;
		this.name = name;
		this.description = description;		
		this.companyName = companyName;
		this.productId = productId;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyName() {
		return this.companyName;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getProductId() {
		return this.productId;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getpartId() {
		return this.partId;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPartId(Integer partId) {
		this.partId = partId;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}	
	
	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
	    if (!(o instanceof Part))
	    	return false;
	    Part part = (Part) o;
	    return Objects.equals(this.partId, part.partId) && 
	    		Objects.equals(this.name, part.name) &&
	    		Objects.equals(this.description,  part.description) &&
	    		Objects.equals(this.companyName, part.companyName);
	  }

	@Override
	public int hashCode() {
		return Objects.hash(this.partId, this.name, this.description, this.companyName);
	}
}