package javiermarcos.jc.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class ProdVendZonaActivity extends AppCompatActivity {

    private PieChart pieChart;
    private BarChart barChart1;

    //datos del eje horizontal eje X
    private String[] months1 = new String[]{"Alvaro Obregon", "Azcapotzalco", "Benito Juarez", "Coyoacan"};
    //datos del eje vertical eje Y
    private int[] sale1 = new int[]{99550000 , 116400000, 101600000, 109200000};
    //Colores de nuestra gafica
    private int[] colors1 = new int[]{Color.rgb(232, 245, 102),
            Color.rgb(178, 245, 102), Color.rgb(102, 245, 184),
            Color.rgb(102, 193, 245)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_vend_zona);

        //Buscamos nuestras graficas en las vistas
        pieChart = (PieChart) findViewById(R.id.piechart);
        createChats();
    }

    //Personalizar la grafica datos generales
    private Chart getSameChart(Chart chart, String description, int text, int background, int animatey) {
        //descripción de la grafica
        chart.getDescription().setText(description);
        //color de la descreipción
        chart.getDescription().setTextColor(text);
        //tamaño de la descripción
        chart.getDescription().setTextSize(20);
        //color de fondo
        chart.setBackgroundColor(background);
        //animación de la grafica
        chart.animateY(animatey);

        legend(chart);

        return chart;
    }

    //leyenda de la grafica
    private void legend(Chart chart) {
        //personalizar la leyenda
        Legend legend = chart.getLegend();
        //personalizar los cuadros de colores
        legend.setForm(Legend.LegendForm.CIRCLE);
        //se coloca centrado para la parte de abajo
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        //rellenar los datos de la grafica
        ArrayList<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < months1.length; i++) {
            //variable para agregar los datos de la leyenda
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors1[i];
            entry.label = months1[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }

    //datos dentro de nuestra gafica de pastel
    private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i<sale1.length; i++){
            entries.add(new PieEntry(sale1[i]));
        }
        return entries;
    }
    //agregar cada uno de los valores, seria la barra
    private void axisX(XAxis axis) {
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(months1));
        //aqui se habilita las etiquetas en la parte inferior
        axis.setEnabled(true);
    }

    private void axisLeft(YAxis axis) {
        //espacio de arriba
        axis.setSpaceTop(30);
        //valor minimo eje Y
        axis.setAxisMinimum(0);
        //cambiar gradualidad
        axis.setGranularity(1500000);
    }

    private void axisRight(YAxis axis) {
        // poner numeros del lado derecho
        axis.setEnabled(false);
    }

    //creación de la grafica de barras
    public void createChats() {
        //creación de la grafica de pie
        pieChart=(PieChart) getSameChart(pieChart, "Ventas totales por zonas", Color.BLACK, Color.TRANSPARENT, 3000);
        //circulo de en medio
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        //No se mostraría la grafica
        //pieChart.setDrawHoleEnabled(false);

    }

    //agregar los datos dentro de la grafica
    private DataSet getData(DataSet dataSet) {
        dataSet.setColors(colors1);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet.setValueTextColor(Color.BLACK);
        //tamaño del texto
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    //personalizar el contenido la grafica de PIE
    private PieData getPieData() {
        PieDataSet pieDataSet = (PieDataSet) getData(new PieDataSet(getPieEntries(), ""));

        pieDataSet.setSliceSpace(1);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }
}