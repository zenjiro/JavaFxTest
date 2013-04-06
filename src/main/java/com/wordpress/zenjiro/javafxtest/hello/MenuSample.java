package com.wordpress.zenjiro.javafxtest.hello;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * メニューの練習
 */
public class MenuSample extends Application {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) {
		stage.setTitle("メニューの練習");
		final VBox root = new VBox();
		final MenuBar menuBar = new MenuBar();
		final Menu fileMenu = new Menu("ファイル(_F)");
		final MenuItem exitItem = new MenuItem("終了(_X)");
		exitItem.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN));
		exitItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				Logger.getAnonymousLogger().log(Level.INFO, "終了します。");
				stage.close();
			}
		});
		fileMenu.getItems().add(exitItem);
		final Menu testMenu = new Menu("てすと(_X)");
		final MenuItem testItem = new MenuItem("てすと(_X)");
		testItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				Logger.getAnonymousLogger().log(Level.INFO, "てすと");
			}
		});
		testMenu.getItems().add(testItem);
		menuBar.getMenus().addAll(fileMenu, testMenu);
		root.getChildren().addAll(menuBar);
		stage.setScene(new Scene(root, 400, 400));
		stage.show();
	}
}
