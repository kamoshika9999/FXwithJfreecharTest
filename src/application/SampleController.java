package application;

import java.awt.BasicStroke;
import java.awt.Stroke;
import java.net.URL;
import java.util.ResourceBundle;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.fx.interaction.ChartMouseEventFX;
import org.jfree.chart.fx.interaction.ChartMouseListenerFX;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane chartPane;

    @FXML
    void initialize() {
        assert chartPane != null : "fx:id=\"chartPane\" was not injected: check your FXML file 'Sample.fxml'.";
        chartFact();

    }
    private void chartFact() {
    	XYSeriesCollection dataset_P2;
		dataset_P2 = getChartData_P2();
		JFreeChart chart_P2 = createInitChart("P2","(mm)","n",dataset_P2 ,1.8,2.2);
		ChartViewer chV = new ChartViewer(chart_P2);
        chV.addChartMouseListener( new ChartMouseListenerFX() {
				@Override
				public void chartMouseClicked(ChartMouseEventFX e) {
					XYPlot xyplot = e.getChart().getXYPlot();
					double value = xyplot.getRangeCrosshairValue();
				}

				@Override
				public void chartMouseMoved(ChartMouseEventFX e) {
				}
        	}
        );
        this.chartPane.setCenter(chV);
    }
    /**
     * グラフの雛形作成
     * @param title
     * @param valueAxisLabel
     * @param categoryAxisLabel
     * @return
     */
    private JFreeChart createInitChart(String title,String valueAxisLabel,
    							String categoryAxisLabel,XYSeriesCollection dataset,double lower,double upper){
    	JFreeChart chart = ChartFactory.createXYLineChart(title,categoryAxisLabel,valueAxisLabel,
                dataset,//データーセット
                PlotOrientation.VERTICAL,//値の軸方向
                false,//凡例
                false,//tooltips
                false);//urls
        // 背景色を設定
    	chart.setBackgroundPaint(ChartColor.WHITE);

        // 凡例の設定
        //LegendTitle lt = chart.getLegend();
        //lt.setFrame(new BlockBorder(1d, 2d, 3d, 4d, ChartColor.WHITE));

        XYPlot plot = (XYPlot) chart.getPlot();
        // 背景色
        plot.setBackgroundPaint(ChartColor.gray);
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 縦線の色
        plot.setDomainGridlinePaint(ChartColor.white);
        // 横線の色
        plot.setRangeGridlinePaint(ChartColor.white);
        // カーソル位置で横方向の補助線をいれる
        plot.setDomainCrosshairVisible(true);
        // カーソル位置で縦方向の補助線をいれる
        plot.setRangeCrosshairVisible(true);
        // 横軸の設定
        NumberAxis xAxis = (NumberAxis)plot.getDomainAxis();
        xAxis.setAutoRange(false);
        xAxis.setRange(1,200);
        // 縦軸の設定
        NumberAxis yAxis = (NumberAxis)plot.getRangeAxis();
        yAxis.setAutoRange(false);
        yAxis.setRange(lower,upper);

        // プロットをつける
        XYLineAndShapeRenderer  renderer = new XYLineAndShapeRenderer(true,false);
        plot.setRenderer(renderer);
        //renderer.setDefaultShapesVisible(true);
        //renderer.setDefaultShapesFilled(true);
        //プロットのサイズ
        Stroke stroke = new BasicStroke(1.0f);
        renderer.setSeriesStroke(0, stroke);
		//色
        renderer.setSeriesPaint(0, ChartColor.BLUE);

        /*
        // プロットに値を付ける
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        XYItemLabelGenerator generator =
            new StandardXYItemLabelGenerator(
                StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT,
                format, format);
        renderer.setDefaultItemLabelGenerator(generator);
        renderer.setDefaultItemLabelsVisible(true);
         */
        return chart;

    }
    private XYSeriesCollection getChartData_P2(){
        XYSeriesCollection p2_dataset = new XYSeriesCollection();
        XYSeries p2_series = new XYSeries("P2");
        p2_dataset.addSeries(p2_series);

        return p2_dataset;
    }

}
