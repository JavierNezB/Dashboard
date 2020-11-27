package javiermarcos.jc.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView card1, card2, card3, card4, card5, card6, card7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);
        card4 = (CardView) findViewById(R.id.c4);
        card5 = (CardView) findViewById(R.id.c5);
        card6 = (CardView) findViewById(R.id.c6);
        card7 = (CardView) findViewById(R.id.c7);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.c1 :
                i = new Intent(this, VentasMenActivity.class);
                startActivity(i);
                break;
            case R.id.c2 :
                i = new Intent(this, ventasAxoActivity.class);
                startActivity(i);
                break;
            case R.id.c3 :
                i = new Intent(this, ZonasActivity.class);
                startActivity(i);
                break;
            case R.id.c4 :
                i = new Intent(this, VentaZonasActivity.class);
                startActivity(i);
                break;
            case R.id.c5 :
                i = new Intent(this, ProductosActivity.class);
                startActivity(i);
                break;
            case R.id.c6 :
                i = new Intent(this, IndiceProductosActivity.class);
                startActivity(i);
                break;
            case R.id.c7 :
                i = new Intent(this, ProdVendZonaActivity.class);
                startActivity(i);
                break;
        }
            }
}