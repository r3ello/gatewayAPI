/**
 * 
 */
package com.musala.gatewaysapi.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Rafael Bello Lara
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "peripheral")
public class Peripheral implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String vendor;

	@Enumerated(EnumType.STRING)
	private StatusEnum status;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "gateway_id")
	@JsonIgnore
	private Gateway gateway;

	@Column(name = "date_created" )
	@CreationTimestamp
	private Timestamp dateCreate;
}
