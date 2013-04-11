package com.wordpress.zenjiro.javafxtest.hello;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * リアルタイムグラフを表示する練習
 * https://forums.oracle.com/forums/thread.jspa?threadID=2411087
 */
public class GraphSample2 extends Application {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("リアルタイムグラフの練習");
		final NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("時刻");
		xAxis.setAutoRanging(false);
		final NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("値");
		yAxis.setAutoRanging(false);
		yAxis.setUpperBound(200);
		final StackedAreaChart<Number, Number> chart = new StackedAreaChart<Number, Number>(xAxis,
				yAxis);
		final Series<Number, Number> series1 = new Series<Number, Number>();
		series1.setName("系列1");
		final Series<Number, Number> series2 = new Series<Number, Number>();
		series2.setName("系列2");
		final AtomicInteger count = new AtomicInteger();
		series1.getData().add(new Data<Number, Number>(count.get(), new Random().nextInt(100)));
		series2.getData().add(new Data<Number, Number>(count.get(), new Random().nextInt(100)));
		xAxis.setLowerBound(count.get() - 30);
		xAxis.setUpperBound(count.get());
		chart.getData().add(series1);
		chart.getData().add(series2);
		stage.setScene(new Scene(chart, 640, 480));
		stage.show();
		final ConcurrentLinkedQueue<Data<Number, Number>> queue1 = new ConcurrentLinkedQueue<Data<Number, Number>>();
		final ConcurrentLinkedQueue<Data<Number, Number>> queue2 = new ConcurrentLinkedQueue<Data<Number, Number>>();
		final ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				count.incrementAndGet();
				queue1.add(new Data<Number, Number>(count.get(), new Random().nextInt(100)));
				queue2.add(new Data<Number, Number>(count.get(), new Random().nextInt(100)));
			}
		}, 1, 1, TimeUnit.SECONDS);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(final WindowEvent event) {
				service.shutdown();
			}
		});
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (!queue1.isEmpty()) {
					series1.getData().add(queue1.poll());
				}
				if (!queue2.isEmpty()) {
					series2.getData().add(queue2.poll());
					xAxis.setLowerBound(count.get() - 30);
					xAxis.setUpperBound(count.get());
				}
			}
		}.start();
	}
}
