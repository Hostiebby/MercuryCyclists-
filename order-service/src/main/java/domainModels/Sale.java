package domainModels;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

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

@Entity
@Table(name = "sales")
public class Sale {
	
	
	@Id	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salesId;	
	//private String SalesNumber;
	private Integer quantity;  
	private Date dataTime; 
	
	//@OneToMany(cascade = CascadeType.ALL)
	//private static List<ProductItems> productLineItems;
	
	Sale() {}
	
	Sale(Integer salesId, Integer quantity, Date dataTime){
		this.salesId = salesId;
		this.quantity = quantity;
		this.dataTime = dataTime;		
	}
	
	public Integer getSalesId() {
		return this.salesId;
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
	    		Objects.equals(this.quantity, sale.quantity) &&
	    		Objects.equals(this.dataTime,  sale.dataTime);
	    		
	  }

	@Override
	public int hashCode() {
		return Objects.hash(this.salesId, this.quantity, this.dataTime);
	}
	

}
