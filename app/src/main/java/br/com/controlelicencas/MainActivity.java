package br.com.controlelicencas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.controle.util.GsonBuilder;
import br.com.controle.util.OnTaskCompleted;
import br.com.controle.RequisicaoLicencasApi;
import br.com.controle.entidades.CheckBody;
import br.com.controle.entidades.Param;
import br.com.controle.entidades.ResponseApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            CheckBody checkBody = new CheckBody.Builder()
                    .document("10654550000188")
                    .license(Util.getImei(getBaseContext()))
                    .build();

            Param param = new Param.Builder()
                    .method(Param.POST)
                    .rout("licenses/check")
                    .content(GsonBuilder.toJson(checkBody))
                    .build();

         /*   EmailBody emailBody = new EmailBody.Builder()
                    .to("concyline@hotmail")
                    .license("123456789__123456")
                    .client("IMPERIAL FERRAMENTAS LTDA")
                    .document("10.654.550/0001-88")
                    .subject("Ativação de Licença - Siad Android")
                    .seller("Jallim Habei")
                    .sellerId("VD-0102")
                    .build();

            Param param = new Param.Builder()
                    .method(Param.POST)
                    .rout("email")
                    .content(GsonBuilder.toJson(emailBody))
                    .build();*/

            new RequisicaoLicencasApi("teste", this, new OnTaskCompleted() {
                @Override
                public void onTaskCompleted(ResponseApi retorno) {
                    System.out.println(retorno.getBody());
                }
            }).execute(param);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}