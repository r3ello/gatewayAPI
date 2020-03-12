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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.musala.gatewaysapi.exception.GatewayNotFoundException;
import com.musala.gatewaysapi.exception.LimitOfPeripheralsExcededException;
import com.musala.gatewaysapi.exception.PeripheralNotFoundException;
import com.musala.gatewaysapi.model.Gateway;
import com.musala.gatewaysapi.model.Peripheral;
import com.musala.gatewaysapi.repository.IGatewayRepository;
import com.musala.gatewaysapi.repository.IPeripheralRepository;

@RestController
public class PeripheralController {

	@Autowired
	IGatewayRepository gatewayRepository;
	@Autowired
	IPeripheralRepository peripheralRepository;

	/**
	 * Create a peripheral in specific gateway
	 * @param gateway_id
	 * @param peripheral
	 * @return
	 */
	@PostMapping("/gateway/{gatewayId}/peripheral")
	public Peripheral createPeripheral(@PathVariable UUID gatewayId, @Valid @RequestBody Peripheral peripheral) {
		Optional <Gateway> g = gatewayRepository.findById(gatewayId);
		if(g.isPresent() ) {
			if(g.get().getPeripheral().size() < 10) {
				peripheral.setGateway(g.get());
				return peripheralRepository.save(peripheral);
			}
			else {
				throw new LimitOfPeripheralsExcededException("Only 10 peripheral by gateway are allowed" );
			}
		}
		else {
			throw new GatewayNotFoundException("Gateway not found with id " + gatewayId );
		}
	}

	/**
	 * Get all peripherals from specific gateway
	 * @param gateway_id
	 * @return
	 */
	@GetMapping("/gateway/{gatewayId}/peripheral")
	public List<Peripheral> getPeripherals(@PathVariable UUID gatewayId) {
		Optional <Gateway> g = gatewayRepository.findById(gatewayId);
		if(g.isPresent() ) {
			return peripheralRepository.findByGateway(g.get());
		}
		else {
			throw new GatewayNotFoundException("Gateway not found with id " + gatewayId );
		}
	}

	/**
	 * Get one specific peripheral by gatewayId and peripheralId
	 * @param gateway_id
	 * @param id
	 * @return
	 */
	@GetMapping("/gateway/{gatewayId}/peripheral/{id}")
	public Peripheral getPeripherals(@PathVariable UUID gatewayId,@PathVariable long id) {
		Optional <Gateway> g = gatewayRepository.findById(gatewayId);
		if(g.isPresent() ) {
			Optional<Peripheral>p = peripheralRepository.findById(id);
			if(p.isPresent())
				return p.get();
			else
				throw new PeripheralNotFoundException("Peripheral not found with id:"+id);
		}
		else {
			throw new GatewayNotFoundException("Gateway not found with id " + gatewayId  );
		}
	}

	/**
	 * Remove one specific peripheral from specific gateway and peripheralId
	 * @param gatewayId
	 * @param peripheralId
	 * @return
	 */
	@DeleteMapping("/gateway/{gatewayId}/peripheral/{peripheralId}")
	public ResponseEntity<String> deletePeripheral(@PathVariable UUID gatewayId,
			@PathVariable Long peripheralId) {
		Optional <Gateway> g = gatewayRepository.findById(gatewayId);
		if(g.isPresent() ) {
			return peripheralRepository.findById(peripheralId)
					.map(peripheral -> {
						peripheralRepository.delete(peripheral);
						return new ResponseEntity<String>("Deleted Successfully!", HttpStatus.OK);
					}).orElseThrow(() -> new PeripheralNotFoundException("Peripherical not found with id " + gatewayId ) );
		}else {
			throw new GatewayNotFoundException("Gateway not found with id " + gatewayId  );
		}
	}
}


