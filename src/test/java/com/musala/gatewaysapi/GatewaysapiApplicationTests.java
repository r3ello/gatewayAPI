package com.musala.gatewaysapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.gatewaysapi.model.Gateway;
import com.musala.gatewaysapi.repository.IGatewayRepository;

@SpringBootTest
@AutoConfigureMockMvc
class GatewaysapiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private IGatewayRepository gatewayRepository;

	/*
	 * Method to test create and  save gateway 
	 * 
	 * */
	@Test
	public void registerGatewayTest() throws Exception
	{
		Gateway g= Gateway.builder().
				name("Gatw 1").
				address("12.32.21.52").build();
		mockMvc.perform(post("/gateway/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(g)))
		.andExpect(status().isOk());

		Optional<Gateway> gateway = gatewayRepository.findByName("Gatw 1");
		assertThat(gateway.get().getAddress()).isEqualTo("12.32.21.52");
	}

	/*
	 * Method to test address validation 
	 * when one gateway is created with incorrect address IP 
	 * BadRequest status is expected
	 * 
	 * */
	@Test
	public void registerGatewayWithIncorrectIPTest() throws Exception
	{
		Gateway g= Gateway.builder().
				name("Gatw 1").
				address("12.32.21.52k2").build();
		mockMvc.perform(post("/gateway/")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(g)))
		.andExpect(status().isBadRequest());


	}



}
