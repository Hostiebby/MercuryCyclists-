package dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
public class SaleRequest {
	private static List<ProductItemsDto> productDtoList;

	public static List<ProductItemsDto> getProductDtoList() {
		return productDtoList;
	}

	public void setProductDtoList(List<ProductItemsDto> productDtoList) {
		this.productDtoList = productDtoList;
	}
}
