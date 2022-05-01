package com.trade.comission.controller;

import com.trade.comission.model.RequestModel;
import com.trade.comission.model.ResponseModel;
import com.trade.comission.service.TradeComissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Validated
public class TradeComissionController {
	
	@Autowired
	TradeComissionService service;
	
	 @PostMapping("/trade/commission-rate")
	  public ResponseEntity<ResponseModel> commissionRate(@Valid @RequestBody RequestModel trade) {
	    return new ResponseEntity<>(service.findCommisionRate(trade), HttpStatus.OK);
	  }
	  @GetMapping("/welcome")
	  public ResponseEntity<String> welcome() {
		  return new ResponseEntity<>("Welcome to APP", HttpStatus.OK);
	  }
}
