package service;

import java.util.List;

import org.apache.kafka.common.Uuid;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import domainModels.ProductItems;
import domainModels.Sale;
import dto.ProductItemsDto;
import dto.SaleRequest;
import lombok.RequiredArgsConstructor;
import repos.SaleRepository;

@Service
@RequiredArgsConstructor
public class SaleService {
	
	private final SaleRepository saleRepository;
	private final WebClient webClient;
	
	SaleService(SaleRepository saleRepository, WebClient webClient) {
		this.saleRepository = saleRepository;
		this.webClient = webClient;
	} 
	
	public void placeSale(SaleRequest saleRequest) {
		Sale sale = new Sale();
		sale.setSalesNumber(Uuid.randomUuid().toString());
		
		List<ProductItems> productItems = SaleRequest.getProductDtoList()
				.stream()
				.map(ProductItemsDto -> mapToDto(ProductItemsDto))
				.toList();
		
		sale.setProductLineItems(productItems);
		
		
		//Call inventory service
		/*EntityModel<?> result = webClient.get()
				.uri("http://localhost:8082/products")
				.retrieve()
				.bodyToMono(EntityModel.class)
				.block();*/
		
				
		saleRepository.save(sale);
	}
	
	private ProductItems mapToDto(ProductItemsDto productItemsDto) {
		ProductItems productItems = new ProductItems();
		productItems.setName(productItems.getName());
		productItems.setPrice(productItems.getPrice());
		productItems.setComment(productItems.getComment());
		return productItems;
	}
}
