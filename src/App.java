import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
public class App extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        //needs to get screen resolution 
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double X=screenBounds.getMaxX();
        double Y=screenBounds.getMaxY();
        Scene scene = new Scene(new StackPane(l), X, Y);//ads a scene to the stage and we pass a root node specifying
        //the required dimensions
        stage.setScene(scene);//set the scene on the stage
        stage.show();//display stages
    }

    public static void main(String[] args) {
        launch();//step 1 we can send args to this 
    }

}