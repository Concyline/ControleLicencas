package br.com.controle.entidades;

public class ResponseApi {

    private int code;
    private String message;
    private String body;
    private String url;

    public ResponseApi() {
    }

    public ResponseApi(int code, String message, String body, String url) {
        this.code = code;
        this.message = message;
        this.body = body;
        this.url = url;
    }

    public ResponseApi(int code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
