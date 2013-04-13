package com.wordpress.zenjiro.javafxtest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * ログイン画面の練習
 * http://docs.oracle.com/javafx/2/get_started/form.htm
 */
public class Login extends Application {
	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		stage.setTitle("ログイン");
		final GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(Font.getDefault().getSize() / 2);
		gridPane.setVgap(Font.getDefault().getSize() / 2);
		gridPane.setPadding(new Insets(Font.getDefault().getSize()));
		final Text loginText = new Text("ログイン");
		loginText.setFont(new Font(Font.getDefault().getSize() * 2));
		gridPane.add(loginText, 0, 0, 2, 1);
		final Label userLabel = new Label("ユーザ名(_U)");
		userLabel.setMnemonicParsing(true);
		gridPane.add(userLabel, 0, 1);
		GridPane.setHalignment(userLabel, HPos.RIGHT);
		final TextField userField = new TextField();
		userLabel.setLabelFor(userField);
		gridPane.add(userField, 1, 1, 2, 1);
		final Label passwordLabel = new Label("パスワード(_P)");
		passwordLabel.setMnemonicParsing(true);
		gridPane.add(passwordLabel, 0, 2);
		GridPane.setHalignment(passwordLabel, HPos.RIGHT);
		final PasswordField passwordField = new PasswordField();
		passwordLabel.setLabelFor(passwordField);
		gridPane.add(passwordField, 1, 2, 2, 1);
		final Label messageLabel = new Label("ここにメッセージ");
		messageLabel.setWrapText(true);
		gridPane.add(messageLabel, 0, 3, 2, 1);
		GridPane.setValignment(messageLabel, VPos.BOTTOM);
		final Button loginButton = new Button("ログイン(_L)");
		loginButton.setPrefHeight(Font.getDefault().getSize() * 3);
		loginButton.setDefaultButton(true);
		// XXX Enterキーやマウスクリックでは押せないけど、Alt+Lなら押せてしまう。
		loginButton.disableProperty().bind(userField.lengthProperty().greaterThan(0).not());
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				Logger.getAnonymousLogger().log(
						Level.INFO,
						"ログインボタンが押されました。ユーザ名：{0}、パスワード：{1}",
						new CharSequence[] { userField.getCharacters(),
								passwordField.getCharacters() });
			}
		});
		gridPane.add(loginButton, 2, 3);
		GridPane.setHalignment(loginButton, HPos.RIGHT);
		GridPane.setValignment(loginButton, VPos.BOTTOM);
		final Scene scene = new Scene(gridPane);
		stage.setScene(scene);
		stage.show();
		userLabel.setMinWidth(userLabel.getWidth());
		passwordLabel.setMinWidth(passwordLabel.getWidth());
		loginButton.setMinWidth(loginButton.getWidth());
	}
}
