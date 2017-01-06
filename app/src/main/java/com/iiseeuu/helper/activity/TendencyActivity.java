package com.iiseeuu.helper.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.iiseeuu.helper.R;
import com.iiseeuu.helper.base.BaseActivity;
import com.iiseeuu.helper.entity.DataItem;
import com.iiseeuu.helper.http.BaseRespFunc;
import com.iiseeuu.helper.http.BaseSubscriber;
import com.iiseeuu.helper.http.HttpModule;
import com.iiseeuu.helper.utils.RxUtils;
import com.iiseeuu.helper.utils.SpHelper;
import com.iiseeuu.helper.utils.TimeUtils;
import com.iiseeuu.helper.widget.OnRefreshListener;
import com.iiseeuu.helper.widget.loader.LoadingAndRetryManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;

public class TendencyActivity extends BaseActivity implements OnRefreshListener {

    @Bind(R.id.toolBar) Toolbar toolBar;
    @Bind(R.id.layout) LinearLayout layout;
    @Bind(R.id.lineChart) LineChart lineChart;
    private String key;
    private String name;
    private static final int QUERY_DAY_COUNT = 7;
    private static final int DEF_MAX_VALUE = 20;
    private LoadingAndRetryManager layoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tendency;
    }

    public static void start(Context context, String key, String name) {
        Intent intent = new Intent(context, TendencyActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    public void parseIntent(Intent intent) {
        key = intent.getStringExtra("key");
        name = intent.getStringExtra("name");
    }

    @Override
    public void initView() {
        layoutManager = bindRefreshView(layout, this);
        toolBar.setTitle(name);
        toolBar.setNavigationIcon(R.drawable.back_icon);
        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(view -> finish());
        initLineChart();
    }

    private void initLineChart() {
        lineChart.setDrawGridBackground(false);
        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawBorders(false);

        lineChart.getAxisLeft().setEnabled(true);
        lineChart.getAxisLeft().setDrawAxisLine(true);
        lineChart.getAxisLeft().setDrawGridLines(true);

        lineChart.getAxisRight().setEnabled(false);

        lineChart.getXAxis().setDrawAxisLine(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart.setTouchEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);

        lineChart.setPinchZoom(false);
        lineChart.getAxisLeft().setAxisMaximum(DEF_MAX_VALUE);
        lineChart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return tempList.get((int) value) + "日";
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });

        Legend l = lineChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
    }

    @Override
    public void initData() {
        String accessToken = SpHelper.getAccessToken();
        String endDate = String.valueOf(System.currentTimeMillis() / 1000);
        String startDate = getQueryStartTime(QUERY_DAY_COUNT);
        HttpModule.newHttpApi().getTendencyData(accessToken, key, startDate, endDate).compose(RxUtils.rxSchedulerHelper())
                .flatMap(new BaseRespFunc<>()).subscribe(new BaseSubscriber<List<DataItem>>() {
            @Override
            public void onNext(List<DataItem> dataItems) {
                lineChart.setData(generateDataLine(dataItems));
                lineChart.invalidate();
                layoutManager.showContent();
            }
        });
    }

    private List<String> tempList = new ArrayList<>();

    private LineData generateDataLine(List<DataItem> data) {
        tempList.clear();
        ArrayList<Entry> e1 = new ArrayList<>();
        int tempMax = 0;
        for (int i = 0; i < data.size(); i++) {
            DataItem item = data.get(i);
            tempList.add(TimeUtils.dateToDay(item.getDate()));
            e1.add(new Entry(i, Float.parseFloat(item.getNumber())));
            if (Integer.parseInt(item.getNumber()) > tempMax) {
                tempMax = Integer.parseInt(item.getNumber());
            }
        }
        if (tempMax > DEF_MAX_VALUE) {
            lineChart.getAxisLeft().resetAxisMaximum();
        }
        LineDataSet d1 = new LineDataSet(e1, "");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        return new LineData(sets);
    }

    public String getQueryStartTime(int count) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        int timestamp = (count - 1) * 24 * 60 * 60;
        return String.valueOf((cal.getTimeInMillis() / 1000) - timestamp);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRetry() {
        initData();
    }
}
