package domainModels;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(name = "sales")

public class Sale {
	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salesId;		
	private Integer quantity; 	
	private Integer productId;
	
	@Temporal(TemporalType.TIMESTAMP)
	//@Column(nullable = false)
	private Date dataTime;
	
	@PrePersist
	private void onCreate() {
		dataTime = new Date();
	}	
	
	Sale() {}
	
	public Sale(Integer productId, Integer quantity){

		//this.salesId = salesId;
		this.productId = productId;
		this.quantity = quantity;	
		
	}
		

	public Integer getSalesId() {
		return this.salesId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getProductId() {
		return this.productId;
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
	
	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
	    if (!(o instanceof Sale))
	    	return false;
	    Sale sale = (Sale) o;
	    return Objects.equals(this.salesId, sale.salesId) &&
	    		Objects.equals(this.productId, sale.productId) &&
	    		Objects.equals(this.quantity, sale.quantity) &&
	    		Objects.equals(this.dataTime,  sale.dataTime) &&
	    		Objects.equals(this.productId,  sale.productId);
	    		
	  }

	@Override
	public int hashCode() {

		return Objects.hash(this.salesId, this.quantity, this.dataTime, this.productId);		

	}
	

}
