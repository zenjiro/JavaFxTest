package com.wordpress.zenjiro.javafxtest.hello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

/**
 * グラフを表示する練習
 * http://docs.oracle.com/javafx/2/charts/area-chart.htm
 */
public class GraphSample extends Application {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("グラフの練習");
		final NumberAxis xAxis = new NumberAxis(1, 31, 1);
		xAxis.setLabel("日");
		final NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("温度[℃]");
		final AreaChart<Number, Number> chart = new AreaChart<Number, Number>(xAxis, yAxis);
		chart.setTitle("温度");
		final Series<Number, Number> seriesApril = new Series<Number, Number>();
		seriesApril.setName("4月");
		seriesApril.getData().add(new Data<Number, Number>(1, 4));
		seriesApril.getData().add(new Data<Number, Number>(3, 10));
		seriesApril.getData().add(new Data<Number, Number>(6, 15));
		seriesApril.getData().add(new Data<Number, Number>(9, 8));
		seriesApril.getData().add(new Data<Number, Number>(12, 5));
		seriesApril.getData().add(new Data<Number, Number>(15, 18));
		seriesApril.getData().add(new Data<Number, Number>(18, 15));
		seriesApril.getData().add(new Data<Number, Number>(21, 13));
		seriesApril.getData().add(new Data<Number, Number>(24, 19));
		seriesApril.getData().add(new Data<Number, Number>(27, 21));
		seriesApril.getData().add(new Data<Number, Number>(30, 21));
		final XYChart.Series<Number, Number> seriesMay = new Series<Number, Number>();
		seriesMay.setName("5月");
		seriesMay.getData().add(new Data<Number, Number>(1, 20));
		seriesMay.getData().add(new Data<Number, Number>(3, 15));
		seriesMay.getData().add(new Data<Number, Number>(6, 13));
		seriesMay.getData().add(new Data<Number, Number>(9, 12));
		seriesMay.getData().add(new Data<Number, Number>(12, 14));
		seriesMay.getData().add(new Data<Number, Number>(15, 18));
		seriesMay.getData().add(new Data<Number, Number>(18, 25));
		seriesMay.getData().add(new Data<Number, Number>(21, 25));
		seriesMay.getData().add(new Data<Number, Number>(24, 23));
		seriesMay.getData().add(new Data<Number, Number>(27, 26));
		seriesMay.getData().add(new Data<Number, Number>(31, 26));
		stage.setScene(new Scene(chart, 640, 480));
		stage.show();
		chart.getData().add(seriesApril);
		chart.getData().add(seriesMay);
	}
}
