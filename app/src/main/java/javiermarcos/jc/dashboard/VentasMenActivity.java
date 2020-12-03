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

    private BarChart barChart1;
    private BarChart barChart2;
    private BarChart barChart3;
    private BarChart barChart4;
    private BarChart barChart5;
    private BarChart barChart6;
    private BarChart barChart7;
    private BarChart barChart8;

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
        barChart3=(BarChart) findViewById(R.id.barchart3);
        barChart4=(BarChart) findViewById(R.id.barchart4);
        barChart5=(BarChart) findViewById(R.id.barchart5);
        barChart6=(BarChart) findViewById(R.id.barchart6);
        barChart7=(BarChart) findViewById(R.id.barchart7);
        barChart8=(BarChart) findViewById(R.id.barchart8);
        createChats();
        createChats2();
        createChats3();
        createChats4();
        createChats5();
        createChats6();
        createChats7();
        createChats8();
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

    //***********************************
    //************SEGUNDA GRAFICA********
    //***********************************

    private int [] sale2 = new int[]{3450000, 2800000, 4150000, 4500000, 3850000, 4150000, 4500000, 3850000, 5200000, 4550000, 3900000, 4550000};

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



    //****************************
    //****************************
    //*********AZCAPOTZALCO*******
    //****************************
    //****************************

    private int [] sale3 = new int[]{3450000, 2800000, 4150000, 4500000, 4850000, 5300000, 5750000, 6200000, 6650000, 7100000, 7550000, 8000000};
    //Colores de nuestra gafica
    private int [] colors2 = new int[]{Color.rgb(147, 242,67), Color.rgb(147, 242,67), Color.rgb(147, 242,67), Color.rgb(147, 242,67),
            Color.rgb(147, 242,67), Color.rgb(147, 242,67), Color.rgb(147, 242,67), Color.rgb(147, 242,67),Color.rgb(147, 242,67),
            Color.rgb(147, 242,67),Color.rgb(147, 242,67),Color.rgb(147, 242,67)};

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
        barChart3 = (BarChart) getSameChart3(barChart3, "Ventas 2019 Azcapotzalco", Color.BLACK, Color.TRANSPARENT, 3000);
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
        dataSet3.setValueTextColor(Color.BLACK);
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

    private int [] sale4 = new int[]{3450000, 2800000, 4150000, 4500000, 4850000, 5300000, 3450000, 2800000, 4150000, 4500000, 4850000, 5300000};

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
            entry4.formColor=colors2[i];
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
        barChart4 = (BarChart) getSameChart4(barChart4, "Ventas 2020 Azcapotzalco", Color.BLACK, Color.TRANSPARENT, 3000);
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
        dataSet4.setColors(colors2);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet4.setValueTextColor(Color.BLACK);
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


    //****************************
    //****************************
    //*********Benito Juarez*******
    //****************************
    //****************************

    private int [] sale5 = new int[]{3450000, 2800000, 4150000, 4500000, 3850000, 5200000, 4550000, 5200000, 4550000, 3900000, 3250000, 6600000};
    //Colores de nuestra gafica
    private int [] colors3 = new int[]{Color.rgb(239, 254,54), Color.rgb(239, 254,54), Color.rgb(239, 254,54), Color.rgb(239, 254,54),
            Color.rgb(239, 254,54), Color.rgb(239, 254,54), Color.rgb(239, 254,54), Color.rgb(239, 254,54),Color.rgb(239, 254,54),
            Color.rgb(239, 254,54),Color.rgb(239, 254,54),Color.rgb(239, 254,54)};

    //Personalizar la grafica datos generales
    private Chart getSameChart5 (Chart chart5, String description, int text, int background, int animatey){
        //descripción de la grafica
        chart5.getDescription().setText(description);
        //color de la descreipción
        chart5.getDescription().setTextColor(text);
        //tamaño de la descripción
        chart5.getDescription().setTextSize(15);
        //color de fondo
        chart5.setBackgroundColor(background);
        //animación de la grafica
        chart5.animateY(animatey);

        legend5(chart5);

        return chart5;
    }
    //leyenda de la grafica
    private void legend5(Chart chart5){
        //personalizar la leyenda
        Legend legend5=chart5.getLegend();
        //personalizar los cuadros de colores
        legend5.setForm(Legend.LegendForm.CIRCLE);
        //se coloca centrado para la parte de abajo
        legend5.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        //rellenar los datos de la grafica
        ArrayList<LegendEntry>entries=new ArrayList<>();
        for(int i = 0; i<months1.length; i++){
            //variable para agregar los datos de la leyenda
            LegendEntry entry = new LegendEntry();
            entry.formColor=colors3[i];
            entry.label=months1[i];
            entries.add(entry);
        }
        legend5.setCustom(entries);
    }
    //datos dentro de nuestra gafica de barras
    private ArrayList<BarEntry>getBarEntries5(){
        ArrayList<BarEntry> entries5 = new ArrayList<>();
        for (int i = 0; i<sale5.length; i++){
            entries5.add(new BarEntry(i, sale5[i]));
        }
        return entries5;
    }
    //agregar cada uno de los valores, seria la barra
    private void axisX5(XAxis axis5){
        axis5.setGranularityEnabled(true);
        axis5.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis5.setValueFormatter(new IndexAxisValueFormatter(months1));
        //aqui se habilita las etiquetas en la parte inferior
        axis5.setEnabled(true);
    }
    private void axisLeft5(YAxis axis5){
        //espacio de arriba
        axis5.setSpaceTop(30);
        //valor minimo eje Y
        axis5.setAxisMinimum(0);
        //cambiar gradualidad
        axis5.setGranularity(1500000);
    }
    private void axisRight5(YAxis axis5){
        // poner numeros del lado derecho
        axis5.setEnabled(false);
    }
    //creación de la grafica de barras
    public void createChats5(){
        barChart5 = (BarChart) getSameChart5(barChart5, "Ventas 2019 Benito Juarez", Color.BLACK, Color.TRANSPARENT, 3000);
        //carqacteristicas de la grafica:
        barChart5.setDrawGridBackground(false);// quitamos el fondo feo detras de la grafica
        barChart5.setDrawBarShadow(false); // activamos la sobra restante de la grafica
        barChart5.setData(getBarData5());
        barChart5.invalidate();
        axisX5(barChart5.getXAxis());
        axisLeft5(barChart5.getAxisLeft());
        axisRight5(barChart5.getAxisRight());
        //Poner en la grafica de PIE los indicadores arriba
        barChart5.getLegend().setEnabled(false);
    }

    //agregar los datos dentro de la grafica
    private DataSet getData5(DataSet dataSet5){
        dataSet5.setColors(colors3);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet5.setValueTextColor(Color.BLACK);
        //tamaño del texto
        dataSet5.setValueTextSize(10);
        return dataSet5;
    }
    //personalizar el contenido la grafica de BARRAS
    private BarData getBarData5(){
        BarDataSet barDataSet5=(BarDataSet)getData5(new BarDataSet(getBarEntries5(), ""));

        barDataSet5.setBarShadowColor(Color.TRANSPARENT);
        BarData barData5 = new BarData(barDataSet5);
        //ancho de las barras
        barData5.setBarWidth(0.45f);
        return barData5;
    }

    //***********************************
    //************sexta GRAFICA********
    //***********************************

    private int [] sale6 = new int[]{3450000, 2800000, 3450000, 2800000, 4150000, 4500000, 4850000, 5300000, 4550000, 3900000, 3250000, 6600000};

    //Personalizar la grafica datos generales
    private Chart getSameChart6 (Chart chart6, String description, int text, int background, int animatey2){
        //descripción de la grafica
        chart6.getDescription().setText(description);
        //color de la descreipción
        chart6.getDescription().setTextColor(text);
        //tamaño de la descripción
        chart6.getDescription().setTextSize(15);
        //color de fondo
        chart6.setBackgroundColor(background);
        //animación de la grafica
        chart6.animateY(animatey2);

        legend6(chart6);

        return chart6;
    }
    //leyenda de la grafica
    private void legend6 (Chart chart6){
        //personalizar la leyenda
        Legend legend6 =chart6.getLegend();
        //personalizar los cuadros de colores
        legend6.setForm(Legend.LegendForm.CIRCLE);
        //se coloca centrado para la parte de abajo
        legend6.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        //rellenar los datos de la grafica
        ArrayList<LegendEntry>entries6=new ArrayList<>();
        for(int i = 0; i<months1.length; i++){
            //variable para agregar los datos de la leyenda
            LegendEntry entry6 = new LegendEntry();
            entry6.formColor=colors3[i];
            entry6.label=months1[i];
            entries6.add(entry6);
        }
        legend6.setCustom(entries6);
    }
    //datos dentro de nuestra gafica de barras
    private ArrayList<BarEntry>getBarEntries6(){
        ArrayList<BarEntry> entries6 = new ArrayList<>();
        for (int i = 0; i<sale6.length; i++){
            entries6.add(new BarEntry(i, sale6[i]));
        }
        return entries6;
    }
    //agregar cada uno de los valores, seria la barra
    private void axisX6(XAxis axis6){
        axis6.setGranularityEnabled(true);
        axis6.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis6.setValueFormatter(new IndexAxisValueFormatter(months1));
        //aqui se habilita las etiquetas en la parte inferior
        axis6.setEnabled(true);
    }
    private void axisLeft6(YAxis axis6){
        //espacio de arriba
        axis6.setSpaceTop(30);
        //valor minimo eje Y
        axis6.setAxisMinimum(0);
        //cambiar gradualidad
        axis6.setGranularity(1500000);
    }
    private void axisRight6(YAxis axis6){
        // poner numeros del lado derecho
        axis6.setEnabled(false);
    }
    //creación de la grafica de barras
    public void createChats6(){
        barChart6 = (BarChart) getSameChart6(barChart6, "Ventas 2020 Benito Juarez", Color.BLACK, Color.TRANSPARENT, 3000);
        //carqacteristicas de la grafica:
        barChart6.setDrawGridBackground(false);// quitamos el fondo feo detras de la grafica
        barChart6.setDrawBarShadow(false); // activamos la sobra restante de la grafica
        barChart6.setData(getBarData6());
        barChart6.invalidate();
        axisX6(barChart6.getXAxis());
        axisLeft6(barChart6.getAxisLeft());
        axisRight6(barChart6.getAxisRight());
        //Poner en la grafica de PIE los indicadores arriba
        barChart6.getLegend().setEnabled(false);
    }

    //agregar los datos dentro de la grafica
    private DataSet getData6(DataSet dataSet6){
        dataSet6.setColors(colors3);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet6.setValueTextColor(Color.BLACK);
        //tamaño del texto
        dataSet6.setValueTextSize(10);
        return dataSet6;
    }
    //personalizar el contenido la grafica de BARRAS
    private BarData getBarData6(){
        BarDataSet barDataSet6 =(BarDataSet)getData6(new BarDataSet(getBarEntries6(), ""));

        barDataSet6.setBarShadowColor(Color.TRANSPARENT);
        BarData barData6 = new BarData(barDataSet6);
        //ancho de las barras
        barData6.setBarWidth(0.45f);
        return barData6;
    }

    //****************************
    //****************************
    //*********COYOACAN***********
    //****************************
    //****************************

    private int [] sale7 = new int[]{3850000, 5200000, 4550000, 5200000, 4550000, 3900000, 3250000, 4150000, 4500000, 4850000, 5300000, 5750000};
    //Colores de nuestra gafica
    private int [] colors4 = new int[]{Color.rgb(255, 98,74), Color.rgb(255, 98,74), Color.rgb(255, 98,74), Color.rgb(255, 98,74),
            Color.rgb(255, 98,74), Color.rgb(255, 98,74), Color.rgb(255, 98,74), Color.rgb(255, 98,74),Color.rgb(255, 98,74),
            Color.rgb(255, 98,74),Color.rgb(255, 98,74),Color.rgb(255, 98,74)};

    //Personalizar la grafica datos generales
    private Chart getSameChart7 (Chart chart7, String description, int text, int background, int animatey){
        //descripción de la grafica
        chart7.getDescription().setText(description);
        //color de la descreipción
        chart7.getDescription().setTextColor(text);
        //tamaño de la descripción
        chart7.getDescription().setTextSize(15);
        //color de fondo
        chart7.setBackgroundColor(background);
        //animación de la grafica
        chart7.animateY(animatey);

        legend7(chart7);

        return chart7;
    }
    //leyenda de la grafica
    private void legend7(Chart chart7){
        //personalizar la leyenda
        Legend legend7=chart7.getLegend();
        //personalizar los cuadros de colores
        legend7.setForm(Legend.LegendForm.CIRCLE);
        //se coloca centrado para la parte de abajo
        legend7.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        //rellenar los datos de la grafica
        ArrayList<LegendEntry>entries=new ArrayList<>();
        for(int i = 0; i<months1.length; i++){
            //variable para agregar los datos de la leyenda
            LegendEntry entry = new LegendEntry();
            entry.formColor=colors4[i];
            entry.label=months1[i];
            entries.add(entry);
        }
        legend7.setCustom(entries);
    }
    //datos dentro de nuestra gafica de barras
    private ArrayList<BarEntry>getBarEntries7(){
        ArrayList<BarEntry> entries7 = new ArrayList<>();
        for (int i = 0; i<sale7.length; i++){
            entries7.add(new BarEntry(i, sale7[i]));
        }
        return entries7;
    }
    //agregar cada uno de los valores, seria la barra
    private void axisX7(XAxis axis7){
        axis7.setGranularityEnabled(true);
        axis7.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis7.setValueFormatter(new IndexAxisValueFormatter(months1));
        //aqui se habilita las etiquetas en la parte inferior
        axis7.setEnabled(true);
    }
    private void axisLeft7(YAxis axis7){
        //espacio de arriba
        axis7.setSpaceTop(30);
        //valor minimo eje Y
        axis7.setAxisMinimum(0);
        //cambiar gradualidad
        axis7.setGranularity(1500000);
    }
    private void axisRight7(YAxis axis7){
        // poner numeros del lado derecho
        axis7.setEnabled(false);
    }
    //creación de la grafica de barras
    public void createChats7(){
        barChart7 = (BarChart) getSameChart7(barChart7, "Ventas 2019 Coyoacan", Color.BLACK, Color.TRANSPARENT, 3000);
        //carqacteristicas de la grafica:0
        barChart7.setDrawGridBackground(false);//0 quitamos el fondo feo detras de la grafica
        barChart7.setDrawBarShadow(false); // activamos la sobra restante de la grafica
        barChart7.setData(getBarData7());
        barChart7.invalidate();
        axisX7(barChart7.getXAxis());
        axisLeft7(barChart7.getAxisLeft());
        axisRight7(barChart7.getAxisRight());
        //Poner en la grafica de PIE los indicadores arriba
        barChart7.getLegend().setEnabled(false);
    }

    //agregar los datos dentro de la grafica
    private DataSet getData7(DataSet dataSet7){
        dataSet7.setColors(colors4);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet7.setValueTextColor(Color.BLACK);
        //tamaño del texto
        dataSet7.setValueTextSize(10);
        return dataSet7;
    }
    //personalizar el contenido la grafica de BARRAS
    private BarData getBarData7(){
        BarDataSet barDataSet7=(BarDataSet)getData7(new BarDataSet(getBarEntries7(), ""));

        barDataSet7.setBarShadowColor(Color.TRANSPARENT);
        BarData barData7 = new BarData(barDataSet7);
        //ancho de las barras
        barData7.setBarWidth(0.45f);
        return barData7;
    }

    //***********************************
    //************Octaba GRAFICA********
    //***********************************

    private int [] sale8 = new int[]{3850000, 5200000, 4550000, 5200000, 4550000, 3450000, 2800000, 4150000, 4500000, 4850000, 5300000, 5750000};

    //Personalizar la grafica datos generales
    private Chart getSameChart8 (Chart chart8, String description, int text, int background, int animatey2){
        //descripción de la grafica
        chart8.getDescription().setText(description);
        //color de la descreipción
        chart8.getDescription().setTextColor(text);
        //tamaño de la descripción
        chart8.getDescription().setTextSize(15);
        //color de fondo
        chart8.setBackgroundColor(background);
        //animación de la grafica
        chart8.animateY(animatey2);

        legend8(chart8);

        return chart8;
    }
    //leyenda de la grafica
    private void legend8 (Chart chart8){
        //personalizar la leyenda
        Legend legend8 =chart8.getLegend();
        //personalizar los cuadros de colores
        legend8.setForm(Legend.LegendForm.CIRCLE);
        //se coloca centrado para la parte de abajo
        legend8.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        //rellenar los datos de la grafica
        ArrayList<LegendEntry>entries8=new ArrayList<>();
        for(int i = 0; i<months1.length; i++){
            //variable para agregar los datos de la leyenda
            LegendEntry entry8 = new LegendEntry();
            entry8.formColor=colors4[i];
            entry8.label=months1[i];
            entries8.add(entry8);
        }
        legend8.setCustom(entries8);
    }
    //datos dentro de nuestra gafica de barras
    private ArrayList<BarEntry>getBarEntries8(){
        ArrayList<BarEntry> entries8 = new ArrayList<>();
        for (int i = 0; i<sale8.length; i++){
            entries8.add(new BarEntry(i, sale8[i]));
        }
        return entries8;
    }
    //agregar cada uno de los valores, seria la barra
    private void axisX8(XAxis axis8){
        axis8.setGranularityEnabled(true);
        axis8.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis8.setValueFormatter(new IndexAxisValueFormatter(months1));
        //aqui se habilita las etiquetas en la parte inferior
        axis8.setEnabled(true);
    }
    private void axisLeft8(YAxis axis8){
        //espacio de arriba
        axis8.setSpaceTop(30);
        //valor minimo eje Y
        axis8.setAxisMinimum(0);
        //cambiar gradualidad
        axis8.setGranularity(1500000);
    }
    private void axisRight8(YAxis axis8){
        // poner numeros del lado derecho
        axis8.setEnabled(false);
    }
    //creación de la grafica de barras
    public void createChats8(){
        barChart8 = (BarChart) getSameChart8(barChart8, "Ventas 2020 Coyoacan", Color.BLACK, Color.TRANSPARENT, 3000);
        //carqacteristicas de la grafica:
        barChart8.setDrawGridBackground(false);// quitamos el fondo feo detras de la grafica
        barChart8.setDrawBarShadow(false); // activamos la sobra restante de la grafica
        barChart8.setData(getBarData8());
        barChart8.invalidate();
        axisX8(barChart8.getXAxis());
        axisLeft8(barChart8.getAxisLeft());
        axisRight8(barChart8.getAxisRight());
        //Poner en la grafica de PIE los indicadores arriba
        barChart8.getLegend().setEnabled(false);
    }

    //agregar los datos dentro de la grafica
    private DataSet getData8(DataSet dataSet8){
        dataSet8.setColors(colors4);
        //Cambiar color de los numeros dentro de la grafica las etiquetas
        dataSet8.setValueTextColor(Color.BLACK);
        //tamaño del texto
        dataSet8.setValueTextSize(10);
        return dataSet8;
    }
    //personalizar el contenido la grafica de BARRAS
    private BarData getBarData8(){
        BarDataSet barDataSet8 =(BarDataSet)getData8(new BarDataSet(getBarEntries8(), ""));

        barDataSet8.setBarShadowColor(Color.TRANSPARENT);
        BarData barData8 = new BarData(barDataSet8);
        //ancho de las barras
        barData8.setBarWidth(0.45f);
        return barData8;
    }
}