/**
 * 
 */
package com.musala.gatewaysapi.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.musala.gatewaysapi.model.Gateway;
import com.musala.gatewaysapi.model.Peripheral;


/**
 * @author Rafael Bello Lara
 *
 */
@Repository
public interface IPeripheralRepository extends JpaRepository<Peripheral, Long> {
	List<Peripheral> findByGateway(Gateway g);
}
