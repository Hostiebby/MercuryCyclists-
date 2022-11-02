package domainModels;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import domainModels.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name  = "sales")
public class Sale {
	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salesId;		
	private Integer quantity; 	
	private Integer productId;
	private Integer storeId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "store_storeId", nullable = false)
	private Store store;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTime;
	
	@PrePersist
	private void onCreate() {
		dataTime = new Date();
	}	
	
	Sale() {}
	
<<<<<<< HEAD
	Sale(Integer salesId, Integer productId, Integer quantity, Integer storeId){
=======
	public Sale(Integer productId, Integer quantity){
>>>>>>> branch 'main' of https://github.com/Hostiebby/MercuryCyclists-.git

		//this.salesId = salesId;
		this.productId = productId;
		this.quantity = quantity;	
		this.storeId = storeId;
	}
	
	public void setStore(Store store) {
		this.store = store;
	}
	public Store getStore() {
		return this.store;
	}
	
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getStoreId() {
		return this.storeId;
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