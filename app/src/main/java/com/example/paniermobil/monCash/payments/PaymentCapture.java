package com.example.paniermobil.monCash.payments;

import com.example.paniermobil.monCash.APIContext;
import com.example.paniermobil.monCash.consomer.Resource;
import com.example.paniermobil.monCash.exception.MonCashRestException;
import com.example.paniermobil.monCash.http.Constants;
import com.example.paniermobil.monCash.http.HttpMethod;


public class PaymentCapture extends Resource{

    private Transaction payment;

    public Transaction getPayment() {
        return payment;
    }

    public void setPayment(Transaction payment) {
        this.payment = payment;
    }

    @Override
    public Resource addConfiguration(String key, String value) {
        return super.addConfiguration(key, value);
    }

    @Override
    public <T> T execute(APIContext apiContext, Class<T> tClass, Object object) throws MonCashRestException {
        if(!(object instanceof TransactionId) && !(object instanceof OrderId)){
            throw new IllegalArgumentException("Object must be a transaction or order instance");
        }
        String uri;
        if(object instanceof TransactionId){
            uri = Constants.PAYMENT_TRANSACTION_URI;
        }else{
            uri = Constants.PAYMENT_ORDER_URI;
        }
        String url = "";
        if(apiContext.getMode().compareTo(Constants.SANDBOX)==0){
            url = Constants.REST_SANDBOX_ENDPOINT+uri;
        }else if(apiContext.getMode().compareTo(Constants.LIVE)==0){
            url = Constants.REST_LIVE_ENDPOINT+uri;
        }else{
            throw new IllegalArgumentException("Mode must be "+Constants.SANDBOX+" or "+Constants.LIVE);
        }
        this.addConfiguration(Constants.URL_KEY, url);
        this.addConfiguration(Constants.METHOD_KEY, HttpMethod.POST.name());
        return super.execute(apiContext, tClass, object);
    }
}
