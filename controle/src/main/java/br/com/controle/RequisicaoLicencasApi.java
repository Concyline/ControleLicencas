package br.com.controle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import br.com.controle.entidades.LoginLicenca;
import br.com.controle.entidades.Param;
import br.com.controle.entidades.ResponseApi;
import br.com.controle.task.Task;
import br.com.controle.util.GsonBuilder;
import br.com.controle.util.OnTaskCompleted;
import br.com.controle.util.TimestampUtils;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RequisicaoLicencasApi extends Task<Object, Object, ResponseApi> {

    public static final int SEM_INTERNET = 418;
    public static final int FALHA_URL = 419;
    public static final int EXCEPTION = 420;

    private AlertDialog.Builder alert;
    private Activity activity;
    private String msnAlert;
    private OnTaskCompleted listener;
    private ProgressDialog progressDialog;
    private Param param;

    private String UrlFakeSiac;

    public RequisicaoLicencasApi(String msnAlert, Activity ctx, OnTaskCompleted listener) {
        this.msnAlert = msnAlert;
        this.activity = ctx;
        alert = new AlertDialog.Builder(activity);
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        if (msnAlert != null && !msnAlert.isEmpty()) {
            progressDialog = ProgressDialog.show(activity, "Aguarde", msnAlert, false, false);
        }
    }

    @Override
    protected ResponseApi doInBackground(Object... params) {

        try {
            UrlFakeSiac = "https://api.gruposiac.com.br/v1/";

            if (params != null && params.length > 0) {
                param = (Param) params[0];
            }

            if (param == null) {
                return new ResponseApi(
                        EXCEPTION, "ERRO: Falha na passagem de parametros, revise o objeto Param passado no execute!", param.getRout()
                );
            }

            param.setAuthorization("BEARER");

            if (UrlFakeSiac == null) {
                return new ResponseApi(
                        FALHA_URL, "ERRO: Estão faltando os dados de conexão nas configurações", param.getRout()
                );
            }

            if (!verificaConexaoInternet(activity)) {
                return new ResponseApi(
                        SEM_INTERNET, "ERRO: Sem conexão com a internet!", param.getRout()
                );
            }

            ResponseApi responseApi = getToken();

            if (responseApi.getCode() != 200) {
                return responseApi;
            }

            param.setToken(responseApi.getBody());

            return call(param);


        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseApi(
                    EXCEPTION, "ERRO: " + e.getMessage(), param.getRout()
            );
        }
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        Log.d("onProgressUpdate", "" + values);
    }

    @Override
    protected void onPostExecute(ResponseApi s) {
        listener.onTaskCompleted(s);

        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    private ResponseApi call(Param param) throws Exception {

        String URL;
        String credential;
        RequestBody body;

        if (param.getContent() != null) {
            body = RequestBody.create(param.getContent(), MediaType.parse("application/json"));
        } else {
            body = RequestBody.create(MediaType.parse("text/plain"), "");
        }

        URL = UrlFakeSiac + param.getRout();

        if (param.getAuthorization().equals("BASIC")) {
            credential = Credentials.basic("SIAC", "$2a$12$XtsNQ1SorHKRY5JMws9Mru.E.jRLZfkYak7eIbVRZoupv0zZqito2");
        } else {
            credential = "Bearer " + param.getToken();
        }

        OkHttpClient client = getUnsafeOkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .method(param.getMethod(), body)
                .addHeader("X-API-TIMESTAMP", TimestampUtils.getISOStringForCurrentDate())
                .addHeader("Authorization", credential)
                .build();

        Response response = client.newCall(request).execute();

        if (response == null) {
            return new ResponseApi(
                    400, "Erro: não completada a requisição para o servidor!", URL
            );
        }

        return getResponseApi(response);
    }

    private ResponseApi getResponseApi(Response response) throws Exception {

        ResponseApi responseApi;
        String url = response.request().url().toString();

        responseApi = new ResponseApi(
                response.code(),
                response.message(),
                response.peekBody(response.body().contentLength()).string(),
                url
        );

        return responseApi;
    }

    private ResponseApi getToken() throws Exception {

        Param param = new Param.Builder()
                .method(Param.POST)
                .rout("siac/auth/login")
                .authorization("BASIC")
                .build();

        ResponseApi responseApi = call(param);

        if (responseApi.getCode() < 200 || responseApi.getCode() > 299) {
            return responseApi;
        }

        LoginLicenca loginLicenca = GsonBuilder.fromJson(responseApi.getBody(), LoginLicenca.class);

        if (loginLicenca == null) {
            return new ResponseApi(
                    400, "ERRO: Não foi possivel fazer a conversão para um objeto Login", param.getRout()
            );
        }

        return new ResponseApi(200, "OK", loginLicenca.getAccessToken(), "siac/auth/login");

    }

    public okhttp3.OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void
                        checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void
                        checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[]
                        getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            okhttp3.OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verificaConexaoInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable()
                && connectivityManager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
