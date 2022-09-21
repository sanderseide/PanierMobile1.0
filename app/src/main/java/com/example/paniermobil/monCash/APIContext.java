package com.example.paniermobil.monCash;

import com.example.paniermobil.monCash.credential.OAuthTokenCredential;
import com.example.paniermobil.monCash.exception.MonCashRestException;
import com.example.paniermobil.monCash.http.Constants;
import com.example.paniermobil.monCash.http.HttpMethod;

public class APIContext {

        private OAuthTokenCredential credential;

        private String mode;

        public APIContext(String clientID, String clientSecret, String mode) {
            this.credential = new OAuthTokenCredential(clientID, clientSecret);
            this.mode = mode;
        }

        public OAuthTokenCredential getCredential() {
            return credential;
        }

        public void setCredential(OAuthTokenCredential credential) {
            this.credential = credential;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getAccessToken() {
            String url = "";
            if(this.getMode().compareTo(Constants.SANDBOX)==0){
                url = Constants.REST_SANDBOX_ENDPOINT+Constants.OAUTH_TOKEN_URI;
            }else if(this.getMode().compareTo(Constants.LIVE)==0){
                url = Constants.REST_LIVE_ENDPOINT+Constants.OAUTH_TOKEN_URI;
            }else{
                throw new IllegalArgumentException("Mode must be "+Constants.SANDBOX+" or "+Constants.LIVE);
            }
            this.credential.addConfiguration(Constants.URL_KEY, url);
            this.credential.addConfiguration(Constants.METHOD_KEY, HttpMethod.POST.name());
            try {

                return this.credential.getAuthorizationOauth();
            }catch (MonCashRestException ex){
                return null;
            }

        }


}
