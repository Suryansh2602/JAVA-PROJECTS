package com.info.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PRODUCTMICROSERVICE")
public interface ProductMicroServiceClient {
	@GetMapping("/product/{name}")
	List<Object> getProductByCategory(@PathVariable String name);
}
