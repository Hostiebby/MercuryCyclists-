package InventoryDomainModels;

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
@Table(name = "products")
public class Product {
	@Column(unique=true)
	private @Id Integer productId;
	private String name;
	private Double price;
	private String comment;
	private Integer stockOnHand;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Part> parts;
	
	Product() {}
	
	public Product(Integer productId, String name, Double price, String comment, Integer stockOnHand){
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.comment = comment;
		this.stockOnHand = stockOnHand;
	}
	
	public void setStockOnHand(Integer stockOnHand) {
		this.stockOnHand = stockOnHand;
	}
	
	public Integer getStockOnHand() {
		return this.stockOnHand;
	}
	
	public Set<Part> displayParts() {
		return this.parts;
	}
	
	public void pushPart(Part part) {
		this.parts.add(part);
	}
	
	public Integer getProductId() {
		return this.productId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Double getPrice() {
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
	
	public void setPrice(Double price) {
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
	    		Objects.equals(this.stockOnHand, product.stockOnHand);
	  }

	@Override
	public int hashCode() {
		return Objects.hash(this.productId, this.name, this.price, this.comment, this.stockOnHand);
	}
}