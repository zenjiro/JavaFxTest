package com.wordpress.zenjiro.javafxtest.hello;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * メニューの練習2
 * http://docs.oracle.com/javafx/2/ui_controls/menu_controls.htm
 */
public class MenuSample2 extends Application {
	/**
	 * ページデータ
	 */
	private class PageData {
		/**
		 * 名前
		 */
		public String name;
		/**
		 * 説明
		 */
		public String description;
		/**
		 * bin名前
		 */
		public String binNames;
		/**
		 * 画像
		 */
		public Image image;

		/**
		 * ページデータを初期化します。
		 * @param name 名前
		 * @param description 説明
		 * @param binNames bin名前
		 */
		public PageData(final String name, final String description, final String binNames) {
			this.name = name;
			this.description = description;
			this.binNames = binNames;
			this.image = new Image("http://docs.oracle.com/javafx/2/ui_controls/img/menu-intro.png");
		}
	}

	/**
	 * メインメソッド
	 * @param args コマンドライン引数
	 */
	public static void main(final String[] args) {
		launch(args);
	}

	/**
	 * ページデータの一覧
	 */
	final PageData[] pages = new PageData[] {
			new PageData("Apple",
					"The apple is the pomaceous fruit of the apple tree, species Malus "
							+ "domestica in the rose family (Rosaceae). It is one of the most "
							+ "widely cultivated tree fruits, and the most widely known of "
							+ "the many members of genus Malus that are used by humans. "
							+ "The tree originated in Western Asia, where its wild ancestor, "
							+ "the Alma, is still found today.", "Malus domestica"),
			new PageData("Hawthorn",
					"The hawthorn is a large genus of shrubs and trees in the rose "
							+ "family, Rosaceae, native to temperate regions of the Northern "
							+ "Hemisphere in Europe, Asia and North America. "
							+ "The name hawthorn was "
							+ "originally applied to the species native to northern Europe, "
							+ "especially the Common Hawthorn C. monogyna, and the unmodified "
							+ "name is often so used in Britain and Ireland.", "Crataegus monogyna"),
			new PageData("Ivy",
					"The ivy is a flowering plant in the grape family (Vitaceae) native"
							+ " to eastern Asia in Japan, Korea, and northern and eastern China."
							+ " It is a deciduous woody vine growing to 30 m tall or more given "
							+ "suitable support,  attaching itself by means of numerous small "
							+ "branched tendrils tipped with sticky disks.",
					"Parthenocissus tricuspidata"),
			new PageData("Quince",
					"The quince is the sole member of the genus Cydonia and is native"
							+ " to warm-temperate southwest Asia in the Caucasus region. The "
							+ "immature fruit is green with dense grey-white pubescence, most "
							+ "of which rubs off before maturity in late autumn when the fruit "
							+ "changes color to yellow with hard, strongly perfumed flesh.",
					"Cydonia oblonga") };
	/**
	 * オプションの一覧
	 */
	final String[] viewOptions = new String[] { "Title", "Binomial name", "Picture", "Description" };
	/**
	 * エフェクトの一覧
	 */
	@SuppressWarnings("unchecked")
	final Entry<String, Effect>[] effects = new Entry[] {
			new SimpleEntry<String, Effect>("Sepia Tone", new SepiaTone()),
			new SimpleEntry<String, Effect>("Glow", new Glow()),
			new SimpleEntry<String, Effect>("Shadow", new DropShadow()) };
	/**
	 * 画像
	 */
	final ImageView pic = new ImageView();
	/**
	 * 名前
	 */
	final Label name = new Label();
	/**
	 * bin名前
	 */
	final Label binName = new Label();
	/**
	 * 説明
	 */
	final Label description = new Label();
	/**
	 * 現在のインデックス
	 */
	private int currentIndex = -1;

	/**
	 * シャッフルします。
	 */
	void shuffle() {
		int i = this.currentIndex;
		while (i == this.currentIndex) {
			i = (int) (Math.random() * this.pages.length);
		}
		this.pic.setImage(this.pages[i].image);
		this.name.setText(this.pages[i].name);
		this.binName.setText("(" + this.pages[i].binNames + ")");
		this.description.setText(this.pages[i].description);
		this.currentIndex = i;
	}

	@Override
	public void start(final Stage stage) {
		stage.setTitle("Menu Sample");
		final Scene scene = new Scene(new VBox(), 400, 350);
		scene.setFill(Color.OLDLACE);
		this.name.setFont(new Font("Verdana Bold", 22));
		this.binName.setFont(new Font("Arial Italic", 10));
		this.pic.setFitHeight(150);
		this.pic.setPreserveRatio(true);
		this.description.setWrapText(true);
		this.description.setTextAlignment(TextAlignment.JUSTIFY);
		shuffle();
		final MenuBar menuBar = new MenuBar();
		final VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(0, 10, 0, 10));
		vbox.getChildren().addAll(this.name, this.binName, this.pic, this.description);
		final Menu menuFile = new Menu("_File");
		final MenuItem add = new MenuItem("_Shuffle", new ImageView(new Image(
				"http://docs.oracle.com/javafx/javafx/author_images/alla_redko_sm.jpg")));
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent t) {
				shuffle();
				vbox.setVisible(true);
			}
		});
		menuFile.getItems().addAll(add);
		final Menu menuEdit = new Menu("_Edit");
		final Menu menuView = new Menu("_View");
		menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
		((VBox) scene.getRoot()).getChildren().addAll(menuBar, vbox);
		stage.setScene(scene);
		stage.show();
	}
}
