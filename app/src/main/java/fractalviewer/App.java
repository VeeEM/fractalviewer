package fractalviewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

	Group root = new Group();
        Scene scene = new Scene(root);
	Mandelbrot mbrot = new Mandelbrot();
        stage.setScene(scene);
	stage.setMaximized(true);
        stage.show();
	ImageView iw = new ImageView(mbrot.getImage((int)scene.getWidth(), (int)scene.getHeight()));
	root.getChildren().add(iw);

	scene.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
	    System.out.println("Scene " + event.getSceneX() + " " + event.getSceneY());
	    System.out.println("Screen " + event.getScreenX() + " " + event.getScreenY());
	    System.out.println("Source " + event.getX() + " " + event.getY());
	    System.out.println("Scene width: " + scene.getWidth());
	});

	System.out.println("Scene width: " + scene.getWidth());
	System.out.println("Scene height: " + scene.getHeight());
    }

    public static void main(String[] args) {
        launch();
    }

}
