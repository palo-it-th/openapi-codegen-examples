package com.paloit.client.petstore.feign.api;

import com.paloit.client.petstore.feign.config.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="${store.name:store}", url="${client.petstore.base-path:/v3}", configuration = ClientConfiguration.class)
public interface StoreApiClient extends StoreApi {
}
