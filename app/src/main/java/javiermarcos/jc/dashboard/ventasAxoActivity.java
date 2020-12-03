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

public class ventasAxoActivity extends AppCompatActivity {

    private BarChart barChart1;
    private BarChart barChart2;
    private BarChart barChart3;
    private BarChart barChart4;

    //datos del eje horizontal eje X
    private String [] months1 = new  String[]{"2019","2020"};
    //datos del eje vertical eje Y
    private int [] sale1 = new int[]{50100000, 49450000};
    //Colores de nuestra gafica
    private int [] colors1 = new int[]{Color.rgb(103, 243,224), Color.rgb(103, 222,243)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_axo);


        //Buscamos nuestras graficas en las vistas
        barChart1=(BarChart) findViewById(R.id.barchart1);
        barChart2=(BarChart) findViewById(R.id.barchart02);
        barChart3=(BarChart) findViewById(R.id.barchart3);
        barChart4=(BarChart) findViewById(R.id.barchart4);
        createChats();
        createChats2();
        createChats3();
        createChats4();
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
        barChart1 = (BarChart) getSameChart(barChart1, "Ventas por año Alvaro Obregon", Color.WHITE, Color.TRANSPARENT, 3000);
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
    }

    //agregar los datos dentro de la grafica
    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors1);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet.setValueTextColor(Color.WHITE);
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

    //***********************************
    //************SEGUNDA GRAFICA********
    //***********************************

    private int [] sale2 = new int[]{66300000, 50100000};
    private int [] colors1_1 = new int[]{Color.rgb(103, 156,243), Color.rgb(103, 109,243)};

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
            entry2.formColor=colors1_1[i];
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
        barChart2 = (BarChart) getSameChart2(barChart2, "Ventas por año Azcapotzalco", Color.WHITE, Color.TRANSPARENT, 3000);
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
        dataSet2.setColors(colors1_1);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet2.setValueTextColor(Color.WHITE);
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



    //****************************
    //****************************
    //*********AZCAPOTZALCO*******
    //****************************
    //****************************

    private int [] sale3 = new int[]{52000000, 49600000};
    //Colores de nuestra gafica
    private int [] colors2 = new int[]{Color.rgb(179, 103,243), Color.rgb(226, 103,243)};

    //Personalizar la grafica datos generales
    private Chart getSameChart3 (Chart chart3, String description, int text, int background, int animatey){
        //descripción de la grafica
        chart3.getDescription().setText(description);
        //color de la descreipción
        chart3.getDescription().setTextColor(text);
        //tamaño de la descripción
        chart3.getDescription().setTextSize(15);
        //color de fondo
        chart3.setBackgroundColor(background);
        //animación de la grafica
        chart3.animateY(animatey);

        legend3(chart3);

        return chart3;
    }
    //leyenda de la grafica
    private void legend3(Chart chart3){
        //personalizar la leyenda
        Legend legend3=chart3.getLegend();
        //personalizar los cuadros de colores
        legend3.setForm(Legend.LegendForm.CIRCLE);
        //se coloca centrado para la parte de abajo
        legend3.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        //rellenar los datos de la grafica
        ArrayList<LegendEntry>entries=new ArrayList<>();
        for(int i = 0; i<months1.length; i++){
            //variable para agregar los datos de la leyenda
            LegendEntry entry = new LegendEntry();
            entry.formColor=colors2[i];
            entry.label=months1[i];
            entries.add(entry);
        }
        legend3.setCustom(entries);
    }
    //datos dentro de nuestra gafica de barras
    private ArrayList<BarEntry>getBarEntries3(){
        ArrayList<BarEntry> entries3 = new ArrayList<>();
        for (int i = 0; i<sale3.length; i++){
            entries3.add(new BarEntry(i, sale3[i]));
        }
        return entries3;
    }
    //agregar cada uno de los valores, seria la barra
    private void axisX3(XAxis axis3){
        axis3.setGranularityEnabled(true);
        axis3.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis3.setValueFormatter(new IndexAxisValueFormatter(months1));
        //aqui se habilita las etiquetas en la parte inferior
        axis3.setEnabled(true);
    }
    private void axisLeft3(YAxis axis3){
        //espacio de arriba
        axis3.setSpaceTop(30);
        //valor minimo eje Y
        axis3.setAxisMinimum(0);
        //cambiar gradualidad
        axis3.setGranularity(1500000);
    }
    private void axisRight3(YAxis axis3){
        // poner numeros del lado derecho
        axis3.setEnabled(false);
    }
    //creación de la grafica de barras
    public void createChats3(){
        barChart3 = (BarChart) getSameChart3(barChart3, "Ventas por año Benito Juarez", Color.WHITE, Color.TRANSPARENT, 3000);
        //carqacteristicas de la grafica:
        barChart3.setDrawGridBackground(false);// quitamos el fondo feo detras de la grafica
        barChart3.setDrawBarShadow(false); // activamos la sobra restante de la grafica
        barChart3.setData(getBarData3());
        barChart3.invalidate();
        axisX3(barChart3.getXAxis());
        axisLeft3(barChart3.getAxisLeft());
        axisRight3(barChart3.getAxisRight());
        //Poner en la grafica de PIE los indicadores arriba
        barChart3.getLegend().setEnabled(false);
    }

    //agregar los datos dentro de la grafica
    private DataSet getData3(DataSet dataSet3){
        dataSet3.setColors(colors2);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet3.setValueTextColor(Color.WHITE);
        //tamaño del texto
        dataSet3.setValueTextSize(10);
        return dataSet3;
    }
    //personalizar el contenido la grafica de BARRAS
    private BarData getBarData3(){
        BarDataSet barDataSet3=(BarDataSet)getData3(new BarDataSet(getBarEntries3(), ""));

        barDataSet3.setBarShadowColor(Color.TRANSPARENT);
        BarData barData3 = new BarData(barDataSet3);
        //ancho de las barras
        barData3.setBarWidth(0.45f);
        return barData3;
    }

    //***********************************
    //************cuarta GRAFICA********
    //***********************************

    private int [] sale4 = new int[]{55050000, 54154000};
    private int [] colors2_2 = new int[]{Color.rgb(243, 103,173), Color.rgb(243, 103,128)};

    //Personalizar la grafica datos generales
    private Chart getSameChart4 (Chart chart4, String description, int text, int background, int animatey2){
        //descripción de la grafica
        chart4.getDescription().setText(description);
        //color de la descreipción
        chart4.getDescription().setTextColor(text);
        //tamaño de la descripción
        chart4.getDescription().setTextSize(15);
        //color de fondo
        chart4.setBackgroundColor(background);
        //animación de la grafica
        chart4.animateY(animatey2);

        legend4(chart4);

        return chart4;
    }
    //leyenda de la grafica
    private void legend4 (Chart chart4){
        //personalizar la leyenda
        Legend legend4 =chart4.getLegend();
        //personalizar los cuadros de colores
        legend4.setForm(Legend.LegendForm.CIRCLE);
        //se coloca centrado para la parte de abajo
        legend4.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        //rellenar los datos de la grafica
        ArrayList<LegendEntry>entries4=new ArrayList<>();
        for(int i = 0; i<months1.length; i++){
            //variable para agregar los datos de la leyenda
            LegendEntry entry4 = new LegendEntry();
            entry4.formColor=colors2_2[i];
            entry4.label=months1[i];
            entries4.add(entry4);
        }
        legend4.setCustom(entries4);
    }
    //datos dentro de nuestra gafica de barras
    private ArrayList<BarEntry>getBarEntries4(){
        ArrayList<BarEntry> entries4 = new ArrayList<>();
        for (int i = 0; i<sale4.length; i++){
            entries4.add(new BarEntry(i, sale4[i]));
        }
        return entries4;
    }
    //agregar cada uno de los valores, seria la barra
    private void axisX4(XAxis axis4){
        axis4.setGranularityEnabled(true);
        axis4.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis4.setValueFormatter(new IndexAxisValueFormatter(months1));
        //aqui se habilita las etiquetas en la parte inferior
        axis4.setEnabled(true);
    }
    private void axisLeft4(YAxis axis4){
        //espacio de arriba
        axis4.setSpaceTop(30);
        //valor minimo eje Y
        axis4.setAxisMinimum(0);
        //cambiar gradualidad
        axis4.setGranularity(1500000);
    }
    private void axisRight4(YAxis axis4){
        // poner numeros del lado derecho
        axis4.setEnabled(false);
    }
    //creación de la grafica de barras
    public void createChats4(){
        barChart4 = (BarChart) getSameChart4(barChart4, "Ventas por año Coyoacan", Color.WHITE, Color.TRANSPARENT, 3000);
        //carqacteristicas de la grafica:
        barChart4.setDrawGridBackground(false);// quitamos el fondo feo detras de la grafica
        barChart4.setDrawBarShadow(false); // activamos la sobra restante de la grafica
        barChart4.setData(getBarData4());
        barChart4.invalidate();
        axisX4(barChart4.getXAxis());
        axisLeft4(barChart4.getAxisLeft());
        axisRight4(barChart4.getAxisRight());
        //Poner en la grafica de PIE los indicadores arriba
        barChart4.getLegend().setEnabled(false);
    }

    //agregar los datos dentro de la grafica
    private DataSet getData4(DataSet dataSet4){
        dataSet4.setColors(colors2_2);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet4.setValueTextColor(Color.WHITE);
        //tamaño del texto
        dataSet4.setValueTextSize(10);
        return dataSet4;
    }
    //personalizar el contenido la grafica de BARRAS
    private BarData getBarData4(){
        BarDataSet barDataSet4 =(BarDataSet)getData4(new BarDataSet(getBarEntries4(), ""));

        barDataSet4.setBarShadowColor(Color.TRANSPARENT);
        BarData barData4 = new BarData(barDataSet4);
        //ancho de las barras
        barData4.setBarWidth(0.45f);
        return barData4;
    }
}