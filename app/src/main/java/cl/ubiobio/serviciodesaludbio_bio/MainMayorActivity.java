package cl.ubiobio.serviciodesaludbio_bio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMayorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cambiarj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mayor);
        cambiarj = findViewById(R.id.cambiar_a_joven);
        cambiarj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent cambiarj = new Intent(MainMayorActivity.this, MainJovenActivity.class);
        startActivity(cambiarj);
        finish();
    }
}
