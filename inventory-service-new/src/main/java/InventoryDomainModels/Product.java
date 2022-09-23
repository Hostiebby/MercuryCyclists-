package InventoryDomainModels;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	private @Id Integer productId;
	private String name;
	private Float price;
	private String comment;
	private Integer partId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "part_partId", nullable = false)
	private Part part;
	
	Product() {}
	
	Product(Integer productId, String name, Float price, String comment, Integer partId){
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.comment = comment;
		this.partId = partId;
	}
	
	public void setPartId(Integer partId) {
		this.partId = partId;
	}
	
	public Integer getPartId() {
		return this.partId;
	}
	
	public void setPart(Part part) {
		this.part = part;
	}
	
	public Part getPart() {
		return this.part;
	}
	
	public Integer getProductId() {
		return this.productId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Float getPrice() {
		return this.price;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
	    if (!(o instanceof Product))
	    	return false;
	    Product product = (Product) o;
	    return Objects.equals(this.productId, product.productId) && 
	    		Objects.equals(this.name, product.name) &&
	    		Objects.equals(this.price,  product.price) &&
	    		Objects.equals(this.comment, product.comment) &&
	    		Objects.equals(this.part, product.part);
	  }

	@Override
	public int hashCode() {
		return Objects.hash(this.productId, this.name, this.price, this.comment, this.part);
	}
}