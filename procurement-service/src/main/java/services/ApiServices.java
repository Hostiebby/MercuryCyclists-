package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServices {
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final String apiLink = "http://localhost:8081";
	public Part getPart (Integer partId) {
		System.out.println(partId);
		Part x = restTemplate.getForObject(apiLink + "/parts/" + partId, Part.class, partId);		
		return x;
	}

}
