package com.iiseeuu.helper.widget;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.iiseeuu.helper.entity.DataItem;

import java.util.List;

/**
 * Author: 30453
 * Date: 2016/12/29 11:01
 */
public class LineChartManager {

    private String lineName;

    public LineChartManager(Context context, LineChart lineChart, List<DataItem> itemList) {
//        initDataStyle(context, lineChart);
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        for (int z = 0; z < 10; z++) {
//            ArrayList<Entry> values = new ArrayList<>();
//            values.add(new Entry(z, z+1));
//            LineDataSet d = new LineDataSet(values, "DataSet " + (z + 1));
//            d.setLineWidth(2.5f);
//            d.setCircleRadius(4f);
//
//            dataSets.add(d);
//        }
//        //设置折线的样式
//        LineDataSet dataSet = new LineDataSet(yValue, lineName);
//        dataSet.setColor(Color.RED);
//        dataSet.setCircleColor(Color.RED);
//        dataSet.setDrawValues(false);
//
//
//        //设置动画效果
//        lineChart.animateY(2000, Easing.EasingOption.Linear);
//        lineChart.animateX(2000, Easing.EasingOption.Linear);
//        lineChart.invalidate();
    }

    private void initDataStyle(Context context, LineChart mLineChart) {
        //设置图表是否支持触控操作
        mLineChart.setTouchEnabled(true);
        mLineChart.setScaleEnabled(false);
        //设置点击折线点时，显示其数值
        //        MyMakerView mv = new MyMakerView(context, R.layout.item_mark_layout);
        //        mLineChart.setMarkerView(mv);
        //设置折线的描述的样式（默认在图表的左下角）
        Legend title = mLineChart.getLegend();
        title.setForm(Legend.LegendForm.LINE);
        //设置x轴的样式
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(Color.parseColor("#66CDAA"));
        xAxis.setAxisLineWidth(5);
        xAxis.setDrawGridLines(false);
        //设置是否显示x轴
        xAxis.setEnabled(true);

        //设置左边y轴的样式
        YAxis yAxisLeft = mLineChart.getAxisLeft();
        yAxisLeft.setAxisLineColor(Color.parseColor("#66CDAA"));
        yAxisLeft.setAxisLineWidth(5);
        yAxisLeft.setDrawGridLines(false);

        //设置右边y轴的样式
        YAxis yAxisRight = mLineChart.getAxisRight();
        yAxisRight.setEnabled(false);
    }

    /**
     * @param name
     * @Description:设置折线的名称
     */
    public void setLineName(String name) {
        lineName = name;
    }
}
