package br.com.controle.entidades;

public class Param {

    public String method;
    public String rout;
    public String content;
    public String token;
    public String authorization;

    public Param(String method, String rout, String content, String authorization) {
        this.method = method;
        this.rout = rout;
        this.content = content;
        this.token = null;
        this.authorization = authorization;
    }

    public Param(String method, String rout, String authorization) {
        this.method = method;
        this.rout = rout;
        this.content = null;
        this.token = null;
        this.authorization = authorization;
    }

    public Param(String method, String rout) {
        this.method = method;
        this.rout = rout;
        this.content = null;
        this.token = null;
        this.authorization = null;
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
}
