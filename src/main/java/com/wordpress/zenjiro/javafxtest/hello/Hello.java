package com.wordpress.zenjiro.javafxtest.hello;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * 標準出力にこんにちはと出力するアプリケーション
 * http://docs.oracle.com/javafx/2/get_started/hello_world.htm
 */
public class Hello extends Application {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("こんにちは");
		final Button button = new Button();
		button.setText("こんにちは(_H)");
		button.setMnemonicParsing(true);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				System.out.println("こんにちは");
			}
		});
		final StackPane rootPane = new StackPane();
		rootPane.getChildren().add(button);
		stage.setScene(new Scene(rootPane, 300, 250));
		stage.show();
	}
}
