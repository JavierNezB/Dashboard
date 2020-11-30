package javiermarcos.jc.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProductosActivity extends AppCompatActivity {

    ListView listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        listaProductos = (ListView) findViewById(R.id.listv1);

        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.arrayProdcutos,
                R.layout.row);

        listaProductos.setAdapter(adaptador);
    }
}