 package domainModels;
 
 import java.util.List;
 import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
 import javax.persistence.Column;
 import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;
 import javax.persistence.OneToMany;
 import javax.persistence.PrePersist;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;

import domainModels.Sale;
 
 @Entity
 @Table(name = "stores")
 
public class Store {
	 @Column(unique=true)
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private @Id Integer storeId;
	 private String address;
	 private String manager;
	 
	 @OneToMany(mappedBy = "store", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 private Set<Sale> sales;
	 
	 Store() {}
	 
	 Store(String address, String manager) {
		 this.address = address;
		 this.manager = manager;
	 }
	 
	 public void setStoreId(Integer storeId) {
		 this.storeId = storeId;
	 }
	 public Integer getStoreId() {
		 return this.storeId;
	 }
	 
	 public void setAddress(String address) {
		 this.address = address;
	 }
	 public String getAddress() {
		 return this.address;
	 }
	 
	 public void setManager(String manager) {
		 this.manager = manager;
	 }
	 public String getManager() {
		 return this.manager;
	 }
	 
	 @Override
	 public boolean equals(Object o) {

		 if (this == o)
			 return true;
		 if (!(o instanceof Store))
			 return false;
		 Store store = (Store) o;
		 return Objects.equals(this.storeId, store.storeId) &&
		    	Objects.equals(this.address, store.address) &&
		    	Objects.equals(this.manager, store.manager);	
		  }

		@Override
		public int hashCode() {
			return Objects.hash(this.storeId, this.address, this.manager);		
		}
 }