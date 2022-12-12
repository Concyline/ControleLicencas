package br.com.controle.entidades;

public class Param {

    public static String POST = "POST";
    public static String GET = "GET";

    public String method;
    public String rout;
    public String content;
    public String token;
    public String authorization;

    public Param(Builder builder){
        this.method = builder.method;
        this.rout = builder.rout;
        this.content = builder.content;
        this.token = builder.token;
        this.authorization = builder.authorization;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRout() {
        return rout;
    }

    public void setRout(String rout) {
        this.rout = rout;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }


    public static class Builder{

        public String method;
        public String rout;
        public String content;
        public String token;
        public String authorization;

        public Builder method(String method){
            this.method = method;
            return this;
        }

        public Builder rout(String rout){
            this.rout = rout;
            return this;
        }

        public Builder content(String content){
            this.content = content;
            return this;
        }

        public Builder token(String token){
            this.token = token;
            return this;
        }

        public Builder authorization(String authorization){
            this.authorization = authorization;
            return this;
        }

        public Param build(){
            return new Param(this);
        }
    }

}
