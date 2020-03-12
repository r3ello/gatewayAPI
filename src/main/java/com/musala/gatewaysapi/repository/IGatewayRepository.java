/**
 * 
 */
package com.musala.gatewaysapi.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.musala.gatewaysapi.model.Gateway;

/**
 * @author Rafael Bello Lara
 *
 */
public interface IGatewayRepository extends JpaRepository<Gateway, UUID>{
	public Optional<Gateway> findByName(String name);

}
