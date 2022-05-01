package com.trade.comission.service;

import com.trade.comission.model.*;
import com.trade.comission.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TradeComissionService {
	@Autowired
	Utility utility;

	@Value("${trade.commission.sell.sto.advisory.price}")
	float stoSellAdvisoryPrice;
	@Value("${trade.commission.sell.fx.advisory.price}")
	float fxSellAdvisoryPrice;

	@Value("${trade.commission.sell.fx.commission.price}")
	float fxSellCommissionRate;
	@Value("${trade.commission.buy.fx.commission.price}")
	float fxBuyCommissionRate;

	@Value("${trade.commission.sell.sto.commission.price}")
	float stoSellCommissionRate;

	@Value("${trade.commission.buy.sto.commission.price}")
	float stoBuyCommissionRate;


	@Value("${trade.commission.sell.bon.commission.price}")
	float bonSellCommissionRate;

	@Value("${trade.commission.buy.bon.commission.price}")
	float bonBuyCommissionRate;

	public ResponseModel findCommisionRate(RequestModel trade) {
		float rate = 0f;
		float advisoryPrice = 0f;
		switch (TradeSecurityEnum.valueOf(trade.getSecurityType())) {
			case STO:
				if (TransactionTypeEnum.BUY.toString().equals(trade.getTransactionType()))
					rate = utility.calculateBUYCommission(stoBuyCommissionRate, trade);
				else if (TransactionTypeEnum.SELL.toString().equals(trade.getTransactionType())) {
					if (trade.getPrice()* trade.getQuantity() > 10000)
						advisoryPrice = stoSellAdvisoryPrice;
					rate = utility.calculateSELLCommission(stoSellCommissionRate, advisoryPrice, trade);
				} else
					new RuntimeException("Trade Security Type is not supported");
				break;
			case BON:
				if (TransactionTypeEnum.BUY.toString().equals(trade.getTransactionType()))
					rate = utility.calculateBUYCommission(bonBuyCommissionRate, trade);
				else if (TransactionTypeEnum.SELL.toString().equals(trade.getTransactionType())) {
					rate = utility.calculateSELLCommission(bonSellCommissionRate, advisoryPrice, trade);
				} else
					new RuntimeException("Trade Security Type is not supported");
				break;
			case FX:
				if (TransactionTypeEnum.BUY.toString().equals(trade.getTransactionType()))
					rate=utility.calculateBUYCommission(fxBuyCommissionRate,trade);
				else if (TransactionTypeEnum.SELL.toString().equals(trade.getTransactionType())) {
					if(trade.getPrice()*trade.getQuantity()>10000)
						advisoryPrice=fxSellAdvisoryPrice;
					rate = utility.calculateSELLCommission(fxSellCommissionRate,advisoryPrice,trade);
				}
				else
					new RuntimeException("Trade Security Type is not supported");
				break;
			default:
				new RuntimeException("Trade Security Type is not supported");
		}
	return new ResponseModel(rate);
	}


}
