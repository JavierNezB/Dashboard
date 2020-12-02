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
    private BarChart barChart;
    private String [] months = new  String[]{"Enero","Febrero","Marzo","Abril","Mayo"};
    private int [] sale = new int[]{25, 30, 38, 10, 15};
    private int [] colors = new int[]{Color.rgb(196,235,83), Color.rgb(83, 235, 173), Color.rgb(255,170,0), Color.rgb(83, 203, 235), Color.rgb(229, 106, 212)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas_men);

        barChart=(BarChart) findViewById(R.id.barchart);
        pieChart=(PieChart) findViewById(R.id.piechart);
        createChats();
    }
    private Chart getSameChart (Chart chart, String description, int text, int background, int animatey){
        chart.getDescription().setText(description);
        chart.getDescription().setTextColor(text);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animatey);

        return chart;
    }
    private void legend(Chart chart){
        Legend legend=chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry>entries=new ArrayList<>();
        for(int i = 0; i<months.length; i++){
            LegendEntry entry = new LegendEntry();
            entry.formColor=colors[i];
            entry.label=months[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }
    private ArrayList<BarEntry>getBarEntries(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i<sale.length; i++){
            entries.add(new BarEntry(i, sale[i]));
        }
        return entries;
    }
    private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i<sale.length; i++){
            entries.add(new PieEntry(sale[i]));
        }
        return entries;
    }
    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(months));
        //aqui se habilita las etiquetas en la parte inferior
        axis.setEnabled(true);
    }
    private void axisLeft(YAxis axis){
        axis.setSpaceTop(30);
        axis.setAxisMinimum(0);
        //cambiar gradualidad
        axis.setGranularity(15);
    }
    private void axisRight(YAxis axis){
        // poner numeros del lado derecho
        axis.setEnabled(false);
    }

    public void createChats(){
        barChart = (BarChart) getSameChart(barChart, "Series", Color.BLACK, Color.TRANSPARENT, 3000);
        barChart.setDrawGridBackground(false);// quitamos el fondo feo detras de la grafica
        barChart.setDrawBarShadow(false); // activamos la sobra restante de la grafica
        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());
        //Poner en la grafica de PIE los indicadores arriba
        barChart.getLegend().setEnabled(false);


        pieChart=(PieChart) getSameChart(pieChart, "Ventas", Color.BLACK, Color.TRANSPARENT, 3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        //No se mostraría la grafica
        //pieChart.setDrawHoleEnabled(false);
    }
    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        //Cambiar color de los numeros dentro de la grafica
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(10);
        return dataSet;
    }
    private BarData getBarData(){
        BarDataSet barDataSet=(BarDataSet)getData(new BarDataSet(getBarEntries(), ""));

        barDataSet.setBarShadowColor(Color.TRANSPARENT);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private PieData getPieData(){
        PieDataSet pieDataSet=(PieDataSet)getData(new PieDataSet(getPieEntries(), ""));

        pieDataSet.setSliceSpace(1);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }
}