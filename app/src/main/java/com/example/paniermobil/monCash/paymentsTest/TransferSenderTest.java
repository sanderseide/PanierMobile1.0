package com.example.paniermobil.monCash.paymentsTest;

import com.example.paniermobil.monCash.APIContext;
import com.example.paniermobil.monCash.http.Constants;
import com.example.paniermobil.monCash.payments.TransferRequest;
import com.example.paniermobil.monCash.payments.TransferSender;

import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.logging.Logger;

public class TransferSenderTest {

    private static final Logger logger = Logger
            .getLogger(PaymentCaptureTest.class.getName());

    @Test
    public void send() throws Exception {
        APIContext apiContext = new APIContext(CredentialTest.CLIENT_ID, CredentialTest.CLIENT_SECRET, Constants.SANDBOX);
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setAmount(22);
        transferRequest.setReceiver("50938662809");
        transferRequest.setDesc("Test");
        TransferSender transferSender = TransferSender.build().execute(apiContext, TransferSender.class, transferRequest);
        if(transferSender.getStatus() !=null && transferSender.getStatus().compareTo(HttpStatus.SC_OK+"")==0){
            logger.info("Transaction");
            logger.info(transferSender.getTransfer().getMessage());
            logger.info("transactio_id="+transferSender.getTransfer().getTransaction_id());
            logger.info("Receiver="+transferSender.getTransfer().getReceiver());
            logger.info("amount="+transferSender.getTransfer().getAmount()+"");
        } else {
            logger.info(transferSender.getStatus());
            logger.info(transferSender.getError());
            logger.info(transferSender.getError_description());
            logger.info(transferSender.getMessage());
        }
    }
}
