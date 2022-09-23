package domainModels;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import domainModels.ProductItems;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long salesId;	
	private String SalesNumber;
	private Integer quantity;  
	private Date dataTime; 
	@OneToMany(cascade = CascadeType.ALL)
	private static List<ProductItems> productLineItems;
	
	
	public String getSalesNumber() {
		return SalesNumber;
	}
	public void setSalesNumber(String salesNumber) {
		SalesNumber = salesNumber;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Date getDataTime() {
		return dataTime;
	}
	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}
	
	public List<ProductItems> getProductLineItems() {
		return productLineItems;
	}
	public void setProductLineItems(List<ProductItems> productLineItems) {
		this.productLineItems = productLineItems;
	}
	

}
