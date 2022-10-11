package com.example.paniermobil.monCash.paymentsTest;

import com.example.paniermobil.monCash.APIContext;
import com.example.paniermobil.monCash.http.Constants;
import com.example.paniermobil.monCash.payments.Payment;
import com.example.paniermobil.monCash.payments.PaymentCreator;

import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.logging.Logger;

public class PaymentCreatorTest {

    private static final Logger logger = Logger
            .getLogger(PaymentCreatorTest.class.getName());

    @Test
    public void create() throws Exception {
        PaymentCreator paymentCreator = new PaymentCreator();
        APIContext apiContext = new APIContext(CredentialTest.CLIENT_ID, CredentialTest.CLIENT_SECRET, Constants.SANDBOX);
        Payment payment = new Payment();
        payment.setOrderId(System.currentTimeMillis()+"");
        payment.setAmount(50);
        PaymentCreator creator = paymentCreator.execute(apiContext,PaymentCreator.class, payment);
        if(creator.getStatus() !=null && creator.getStatus().compareTo(HttpStatus.SC_ACCEPTED+"")==0){
            logger.info("redirect to the link below");
            logger.info(creator.redirectUri());
        }else if(creator.getStatus()==null){
            logger.warning("Error");
            logger.warning(creator.getError());
            logger.warning(creator.getError_description());
        }else{
            logger.warning("Error");
            logger.warning(creator.getStatus());
            logger.warning(creator.getError());
            logger.warning(creator.getMessage());
            logger.warning(creator.getPath());
        }

    }
}