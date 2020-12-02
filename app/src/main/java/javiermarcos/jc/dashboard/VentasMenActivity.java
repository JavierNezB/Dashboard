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

public class VentasMenActivity extends AppCompatActivity {

    private PieChart pieChart;
    private BarChart barChart1;
    private BarChart barChart2;

    //datos del eje horizontal eje X
    private String [] months1 = new  String[]{"Ene","Feb","Mar","Abr","May", "Jun", "Jul","Ago","Sep","Oct","Nov","Dici"};
    //datos del eje vertical eje Y
    private int [] sale1 = new int[]{3750000, 4100000, 3450000, 2800000, 4150000, 4500000, 3850000, 5200000, 4550000, 3900000, 3250000, 6600000};
    //Colores de nuestra gafica
    private int [] colors1 = new int[]{Color.rgb(67, 221,242), Color.rgb(67, 221,242), Color.rgb(67, 221,242), Color.rgb(67, 221,242),
            Color.rgb(67, 221,242), Color.rgb(67, 221,242), Color.rgb(67, 221,242), Color.rgb(67, 221,242),Color.rgb(67, 221,242),
            Color.rgb(67, 221,242),Color.rgb(67, 221,242),Color.rgb(67, 221,242)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_men);

        //Buscamos nuestras graficas en las vistas
        barChart1=(BarChart) findViewById(R.id.barchart1);
        barChart2=(BarChart) findViewById(R.id.barchart02);
        //pieChart=(PieChart) findViewById(R.id.piechart);
        createChats();
        createChats2();
    }

    //Personalizar la grafica datos generales
    private Chart getSameChart (Chart chart, String description, int text, int background, int animatey){
        //descripción de la grafica
        chart.getDescription().setText(description);
        //color de la descreipción
        chart.getDescription().setTextColor(text);
        //tamaño de la descripción
        chart.getDescription().setTextSize(15);
        //color de fondo
        chart.setBackgroundColor(background);
        //animación de la grafica
        chart.animateY(animatey);

        legend(chart);

        return chart;
    }
    //leyenda de la grafica
    private void legend(Chart chart){
        //personalizar la leyenda
        Legend legend=chart.getLegend();
        //personalizar los cuadros de colores
        legend.setForm(Legend.LegendForm.CIRCLE);
        //se coloca centrado para la parte de abajo
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        //rellenar los datos de la grafica
        ArrayList<LegendEntry>entries=new ArrayList<>();
        for(int i = 0; i<months1.length; i++){
            //variable para agregar los datos de la leyenda
            LegendEntry entry = new LegendEntry();
            entry.formColor=colors1[i];
            entry.label=months1[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }
    //datos dentro de nuestra gafica de barras
    private ArrayList<BarEntry>getBarEntries(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i<sale1.length; i++){
            entries.add(new BarEntry(i, sale1[i]));
        }
        return entries;
    }
    //datos dentro de nuestra gafica de pastel
    /*private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i<sale.length; i++){
            entries.add(new PieEntry(sale[i]));
        }
        return entries;
    }*/
    //agregar cada uno de los valores, seria la barra
    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(months1));
        //aqui se habilita las etiquetas en la parte inferior
        axis.setEnabled(true);
    }
    private void axisLeft(YAxis axis){
        //espacio de arriba
        axis.setSpaceTop(30);
        //valor minimo eje Y
        axis.setAxisMinimum(0);
        //cambiar gradualidad
        axis.setGranularity(1500000);
    }
    private void axisRight(YAxis axis){
        // poner numeros del lado derecho
        axis.setEnabled(false);
    }
    //creación de la grafica de barras
    public void createChats(){
        barChart1 = (BarChart) getSameChart(barChart1, "Ventas 2019 Alvaro Obregon", Color.BLACK, Color.TRANSPARENT, 3000);
        //carqacteristicas de la grafica:
        barChart1.setDrawGridBackground(false);// quitamos el fondo feo detras de la grafica
        barChart1.setDrawBarShadow(false); // activamos la sobra restante de la grafica
        barChart1.setData(getBarData());
        barChart1.invalidate();
        axisX(barChart1.getXAxis());
        axisLeft(barChart1.getAxisLeft());
        axisRight(barChart1.getAxisRight());
        //Poner en la grafica de PIE los indicadores arriba
        barChart1.getLegend().setEnabled(false);

        //creación de la grafica de pie
        /*
        pieChart=(PieChart) getSameChart(pieChart, "Ventas", Color.BLACK, Color.TRANSPARENT, 3000);
        //circulo de en medio
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        //No se mostraría la grafica
        //pieChart.setDrawHoleEnabled(false);
        */
    }

    //agregar los datos dentro de la grafica
    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors1);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet.setValueTextColor(Color.BLACK);
        //tamaño del texto
        dataSet.setValueTextSize(10);
        return dataSet;
    }
    //personalizar el contenido la grafica de BARRAS
    private BarData getBarData(){
        BarDataSet barDataSet=(BarDataSet)getData(new BarDataSet(getBarEntries(), ""));

        barDataSet.setBarShadowColor(Color.TRANSPARENT);
        BarData barData = new BarData(barDataSet);
        //ancho de las barras
        barData.setBarWidth(0.45f);
        return barData;
    }
    //personalizar el contenido la grafica de PIE
    /*
    private PieData getPieData(){
        PieDataSet pieDataSet=(PieDataSet)getData(new PieDataSet(getPieEntries(), ""));

        pieDataSet.setSliceSpace(1);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }
     */

    //***********************************
    //************SEGUNDA GRAFICA********
    //***********************************

    //datos del eje horizontal eje X
    //private String [] months1 = new  String[]{"Ene","Feb","Mar","Abr","May", "Jun", "Jul","Ago","Sep","Oct","Nov","Dici"};
    //datos del eje vertical eje Y
    private int [] sale2 = new int[]{3750000, 4100000, 3450000, 2800000, 4150000, 4500000, 3850000, 5200000, 4550000, 3900000, 3250000, 6600000};
    //Colores de nuestra gafica
    //private int [] colors1 = new int[]{Color.rgb(67, 221,242), Color.rgb(67, 221,242), Color.rgb(67, 221,242), Color.rgb(67, 221,242),
    //       Color.rgb(67, 221,242), Color.rgb(67, 221,242), Color.rgb(67, 221,242), Color.rgb(67, 221,242),Color.rgb(67, 221,242),
    //        Color.rgb(67, 221,242),Color.rgb(67, 221,242),Color.rgb(67, 221,242)};


    //Personalizar la grafica datos generales
    private Chart getSameChart2 (Chart chart2, String description, int text, int background, int animatey2){
        //descripción de la grafica
        chart2.getDescription().setText(description);
        //color de la descreipción
        chart2.getDescription().setTextColor(text);
        //tamaño de la descripción
        chart2.getDescription().setTextSize(15);
        //color de fondo
        chart2.setBackgroundColor(background);
        //animación de la grafica
        chart2.animateY(animatey2);

        legend2(chart2);

        return chart2;
    }
    //leyenda de la grafica
    private void legend2 (Chart chart2){
        //personalizar la leyenda
        Legend legend2 =chart2.getLegend();
        //personalizar los cuadros de colores
        legend2.setForm(Legend.LegendForm.CIRCLE);
        //se coloca centrado para la parte de abajo
        legend2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        //rellenar los datos de la grafica
        ArrayList<LegendEntry>entries2=new ArrayList<>();
        for(int i = 0; i<months1.length; i++){
            //variable para agregar los datos de la leyenda
            LegendEntry entry2 = new LegendEntry();
            entry2.formColor=colors1[i];
            entry2.label=months1[i];
            entries2.add(entry2);
        }
        legend2.setCustom(entries2);
    }
    //datos dentro de nuestra gafica de barras
    private ArrayList<BarEntry>getBarEntries2(){
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        for (int i = 0; i<sale2.length; i++){
            entries2.add(new BarEntry(i, sale2[i]));
        }
        return entries2;
    }
    //agregar cada uno de los valores, seria la barra
    private void axisX2(XAxis axis2){
        axis2.setGranularityEnabled(true);
        axis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis2.setValueFormatter(new IndexAxisValueFormatter(months1));
        //aqui se habilita las etiquetas en la parte inferior
        axis2.setEnabled(true);
    }
    private void axisLeft2(YAxis axis2){
        //espacio de arriba
        axis2.setSpaceTop(30);
        //valor minimo eje Y
        axis2.setAxisMinimum(0);
        //cambiar gradualidad
        axis2.setGranularity(1500000);
    }
    private void axisRight2(YAxis axis2){
        // poner numeros del lado derecho
        axis2.setEnabled(false);
    }
    //creación de la grafica de barras
    public void createChats2(){
        barChart2 = (BarChart) getSameChart2(barChart2, "Ventas 2020 Alvaro Obregon", Color.BLACK, Color.TRANSPARENT, 3000);
        //carqacteristicas de la grafica:
        barChart2.setDrawGridBackground(false);// quitamos el fondo feo detras de la grafica
        barChart2.setDrawBarShadow(false); // activamos la sobra restante de la grafica
        barChart2.setData(getBarData2());
        barChart2.invalidate();
        axisX2(barChart2.getXAxis());
        axisLeft2(barChart2.getAxisLeft());
        axisRight2(barChart2.getAxisRight());
        //Poner en la grafica de PIE los indicadores arriba
        barChart2.getLegend().setEnabled(false);
    }

    //agregar los datos dentro de la grafica
    private DataSet getData2(DataSet dataSet2){
        dataSet2.setColors(colors1);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet2.setValueTextColor(Color.BLACK);
        //tamaño del texto
        dataSet2.setValueTextSize(10);
        return dataSet2;
    }
    //personalizar el contenido la grafica de BARRAS
    private BarData getBarData2(){
        BarDataSet barDataSet2 =(BarDataSet)getData2(new BarDataSet(getBarEntries2(), ""));

        barDataSet2.setBarShadowColor(Color.TRANSPARENT);
        BarData barData2 = new BarData(barDataSet2);
        //ancho de las barras
        barData2.setBarWidth(0.45f);
        return barData2;
    }
}