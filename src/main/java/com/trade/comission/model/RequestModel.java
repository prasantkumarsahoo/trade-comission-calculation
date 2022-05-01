package com.trade.comission.model;

import java.util.Date;

import javax.validation.constraints.*;

import lombok.Data;

@Data
public class RequestModel {
	
	@NotNull(message = "The timestamp is required.")
	private Date timestamp;

	@NotBlank(message = "A Valid Trade Security Type is required.")
	@NotNull
    private String securityType;

	@NotBlank(message = "A Valid Transaction Type is required.")
	@NotNull
    private String transactionType;

	@NotNull(message = "The quantity is required.")
    private Integer quantity;

	@NotNull(message = "The price is required")
    private Float price;

}