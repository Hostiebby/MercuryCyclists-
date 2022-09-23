package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import domainModels.ProductItems;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class ProductItemsDto {
	private Long productId;
	private String name;
	private Float price;
	private String comment;
	
	
}
