package cl.ubiobio.serviciodesaludbio_bio;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private SplashActivity _this = this;
    private SharedPreferences sharedPre; //variable donde se almacenan las preferencias
    private SharedPreferences.Editor editorSP; //editor de preferencias (con editorSP.putInt() guardaremos el modo elegido)

    //inicializo valores de modo utiles para guardar las preferencias de pantalla
    private int NO_PREFERENCES = 0;
    private int MODO_JOVEN = 1;
    private int MODO_VIEJO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPre = getSharedPreferences(getString(R.string.sharedPreID), MODE_PRIVATE);
        editorSP = sharedPre.edit();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iniciar = null;
                if(sharedPre.getInt("MODO", NO_PREFERENCES) == NO_PREFERENCES){ //si es la primera vez que se abre la aplicacion entonces preguntara que modo desea elegir
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(_this);
                    alertBuilder.setMessage("se recomienda el \"Modo Mayor\" para adultos mayores")
                            .setTitle("Elija el modo de interfaz")
                            .setPositiveButton("Modo Joven", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editorSP.putInt("MODO",MODO_JOVEN); //si elige modo joven se guardara en las preferencias de usuario
                                    editorSP.commit();
                                    Intent iniciar = new Intent(_this, MainJovenActivity.class); //nos redireccionara a la pantalla del modo joven
                                    startActivity(iniciar);
                                    finish();
                                }
                            })
                            .setNegativeButton("Modo Mayor", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editorSP.putInt("MODO",MODO_VIEJO);//si elige modo viejo se guardara en las preferencias de usuario
                                    editorSP.commit();
                                    Intent iniciar = new Intent(_this, MainMayorActivity.class);//nos redireccionara a la pantalla del modo mayor
                                    startActivity(iniciar);
                                    finish();
                                }
                            });
                    AlertDialog dialog = alertBuilder.create();
                    dialog.show();
                }else{
                    if(sharedPre.getInt("MODO", NO_PREFERENCES) == MODO_JOVEN){ //si ya existen preferencias y esta es MODO_JOVEN, entonces al abrir la app no preguntara que modo elegir, si no que nos redireccionara inmediatamente a la pantalla de modo joven
                        iniciar = new Intent(_this, MainJovenActivity.class);
                    }else{ //Por el contrario, si la preferencia existente no es MODO_JOVEN, entonces sera MODO_VIEJO, por lo tanto al abrir la app no preguntara que modo elegir, si no que nos redireccionara inmediatamente a la pantalla de modo mayor
                        iniciar = new Intent(_this, MainMayorActivity.class);
                    }
                    startActivity(iniciar);
                    finish();
                }
            }
        }, 3000);//Milisegundos
    }
}