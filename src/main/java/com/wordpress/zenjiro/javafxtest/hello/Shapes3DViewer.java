package com.wordpress.zenjiro.javafxtest.hello;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * 3D表示のテスト
 * http://fxexperience.com/2013/02/javafx-3d-early-access-available/
 */
public class Shapes3DViewer extends Application {
	@Override
	public void start(final Stage stage) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.LIGHTGRAY);
		material.setSpecularColor(Color.rgb(30, 30, 30));
		final Shape3D[] meshView = new Shape3D[] { new Box(200, 200, 200), new Sphere(100),
				new Cylinder(100, 200), };
		for (int i = 0; i != 3; ++i) {
			meshView[i].setMaterial(material);
			meshView[i].setTranslateX((i + 1) * 220);
			meshView[i].setTranslateY(500);
			meshView[i].setTranslateZ(20);
			meshView[i].setDrawMode(DrawMode.FILL);
			meshView[i].setCullFace(CullFace.BACK);
		}
		final PointLight pointLight = new PointLight(Color.ANTIQUEWHITE);
		pointLight.setTranslateX(800);
		pointLight.setTranslateY(-100);
		pointLight.setTranslateZ(-1000);
		final Group root = new Group(meshView);
		root.getChildren().add(pointLight);
		final Scene scene = new Scene(root, 800, 800, true);
		scene.setFill(Color.rgb(10, 10, 40));
		scene.setCamera(new PerspectiveCamera(false));
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
