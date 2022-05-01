package com.trade.comission.utility;

import com.trade.comission.model.RequestModel;
import org.springframework.stereotype.Service;

@Service
public class Utility {

    public float calculateBUYCommission(float rate, RequestModel trade){
        return ((trade.getPrice()*trade.getQuantity())*rate) ;
    }

    public float calculateSELLCommission(float rate, float advisoryPrice, RequestModel trade){
     return ((trade.getPrice()*trade.getQuantity())*rate) + advisoryPrice;
    }

}
