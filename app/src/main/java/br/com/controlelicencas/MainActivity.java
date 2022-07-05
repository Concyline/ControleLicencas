package br.com.controlelicencas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.controle.OnTaskCompleted;
import br.com.controle.RequisicaoLicencasApi;
import br.com.controle.entidades.CheckBody;
import br.com.controle.entidades.ResponseApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RequisicaoLicencasApi("teste", this, new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(ResponseApi retorno) {
                System.out.println(retorno.getBody());
            }
        }).execute(new CheckBody("10654550000188",  Util.getImei(getBaseContext())));
    }
}