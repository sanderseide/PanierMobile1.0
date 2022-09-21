package com.example.paniermobil.monCash.payments;

import com.example.paniermobil.monCash.APIContext;
import com.example.paniermobil.monCash.consomer.Resource;
import com.example.paniermobil.monCash.exception.MonCashRestException;
import com.example.paniermobil.monCash.http.Constants;
import com.example.paniermobil.monCash.http.HttpMethod;


public class TransferSender extends Resource {

    private TransferResponse transfer;

    public TransferResponse getTransfer() {
        return transfer;
    }

    public void setTransfer(TransferResponse transfer) {
        this.transfer = transfer;
    }

    public static TransferSender build(){
        return new TransferSender();
    }

    @Override
    public Resource addConfiguration(String key, String value) {
        return super.addConfiguration(key, value);
    }

    @Override
    public <T> T execute(APIContext apiContext, Class<T> tClass, Object object) throws MonCashRestException {
        if(!(object instanceof TransferRequest)){
            throw new IllegalArgumentException("Object must be a TransferRequest instance");
        }
        String uri = Constants.TRANSFER_URI;
        String url = "";
        if(apiContext.getMode().compareTo(Constants.SANDBOX)==0){
            url = Constants.REST_SANDBOX_ENDPOINT+uri;
        }else if(apiContext.getMode().compareTo(Constants.LIVE)==0){
            url = Constants.REST_LIVE_ENDPOINT+uri;
        }else{
            throw new IllegalArgumentException("Mode must be "+ Constants.SANDBOX+" or "+ Constants.LIVE);
        }
        this.addConfiguration(Constants.URL_KEY, url);
        this.addConfiguration(Constants.METHOD_KEY, HttpMethod.POST.name());
        return super.execute(apiContext, tClass, object);
    }

}
