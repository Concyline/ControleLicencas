package br.com.controle.entidades;


public class CheckBody {

    private String document;
    private String license;

    public CheckBody(Builder builder) {
        this.document = builder.document;
        this.license = builder.license;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public static class Builder{

        private String document;
        private String license;

        public Builder document(String document){
            this.document = document;
            return this;
        }

        public Builder license(String license){
            this.license = license;
            return this;
        }

        public CheckBody build(){
            return new CheckBody(this);
        }

    }
}
