package br.com.controle.entidades;

public class EmailBody {

    private String  to;
    private String  license;
    private String  client;
    private String  document;
    private String  subject;
    private String  seller;
    private String  sellerId;

    public EmailBody(Builder builder){
        this.to = builder.to;
        this.license = builder.license;
        this.client = builder.client;
        this.document = builder.document;
        this.subject = builder.subject;
        this.seller = builder.seller;
        this.sellerId = builder.sellerId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public static class Builder{

        private String  to;
        private String  license;
        private String  client;
        private String  document;
        private String  subject;
        private String  seller;
        private String  sellerId;

        public Builder to(String to){
            this.to = to;
            return this;
        }

        public Builder license(String license){
            this.license = license;
            return this;
        }

        public Builder client(String client){
            this.client = client;
            return this;
        }

        public Builder document(String document){
            this.document = document;
            return this;
        }

        public Builder subject(String subject){
            this.subject = subject;
            return this;
        }

        public Builder seller(String seller){
            this.seller = seller;
            return this;
        }

        public Builder sellerId(String sellerId){
            this.sellerId = sellerId;
            return this;
        }

        public EmailBody build(){
            return new EmailBody(this);
        }

    }
}
