/**
 * 
 */
package com.musala.gatewaysapi.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.gatewaysapi.exception.GatewayNotFoundException;
import com.musala.gatewaysapi.model.Gateway;
import com.musala.gatewaysapi.repository.IGatewayRepository;

/**
 * @author Rafael Bello Lara
 *
 */
@RestController
@RequestMapping("/gateway")
public class GatewayController {

	@Autowired
	IGatewayRepository gatewayRepositoy;

	/**
	 * Create gateway
	 * @param gateway
	 * @return
	 */
	@PostMapping()
	public Gateway createGateway(@Valid @RequestBody Gateway gateway) {
		return gatewayRepositoy.save(gateway);
	}
	/**
	 * Get gateway by id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Gateway getById(@PathVariable UUID id) {
		Optional<Gateway>  g=gatewayRepositoy.findById(id);
		if(g.isPresent())
			return g.get();
		else
			throw new GatewayNotFoundException("Gateway not found with id " + id);
	}
	/**
	 * Get all gateway
	 * @return
	 */
	@GetMapping()
	public List<Gateway> getAll(){
		return gatewayRepositoy.findAll();
	}

	/**
	 * Update gateway by id
	 * @param id
	 * @param gatewayUpdated
	 * @return
	 */
	@PutMapping("/{id}")
	public Gateway updateGateway(@PathVariable UUID id, @Valid @RequestBody Gateway gatewayUpdated) {
		return gatewayRepositoy.findById(id)
				.map(gateway -> {
					gateway.setName(gatewayUpdated.getName());
					gateway.setAddress(gatewayUpdated.getAddress());
					return gatewayRepositoy.save(gateway);
				}).orElseThrow(() -> new GatewayNotFoundException("Gateway not found with id " + id));
	}
	/**
	 * Delete gateway by id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteGateway(@PathVariable UUID id) {
		return  gatewayRepositoy.findById(id)
				.map(gateway -> {
					gatewayRepositoy.delete(gateway);
					return new ResponseEntity<String>("Delete Successfully!", HttpStatus.OK);
				}).orElseThrow(() -> new GatewayNotFoundException("Gateway not found with id " + id));
	}
}
