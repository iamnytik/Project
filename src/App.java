import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javax.imageio.ImageIO;
import javafx.scene.text.*;
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
        Image left= new Image("file:../img/left.jpeg");
        Image right= new Image("file:../img/right.jpeg");
        /*Image icon= new Image("file:../img/OIP.jpeg");//icon of our app
        stage.getIcons().add(icon); fix this later*/
        scene.setFill(Color.web("#90EE90"));
        StackPane root = (StackPane) scene.getRoot();
        root.setStyle("-fx-background-color: #90EE90");
        stage.setScene(scene);//set the scene on the stage
        stage.setTitle("Calender Remainder System");
        /*Just passing some sample months this is needed when we click on left and right buttons */
        setMonthText(root, "March 2023",Y);
        /*scene.setRoot(root);
       
       CalendarGrid calendarGrid=new CalendarGrid();
        
      //  root.getChildren().add(calendarGrid. );
        
      */
        stage.setScene(scene);

        
        stage.show();//display stages
        
        

    }
    void setMonthText(StackPane root, String month,double sceneHeight)
    {
        //The String should change every time you pass a month
        Text disp_month = new Text(month);
        disp_month.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        disp_month.setFill(Color.ORANGE);
        disp_month.setText(month);
        StackPane.setAlignment(disp_month, Pos.CENTER);//setting the text at the centere of the screen
        root.getChildren().add(disp_month);
        double translateY = -sceneHeight * 0.3; //moving the text 30% up
        disp_month.setTranslateY(translateY);

    }
    public static void main(String[] args) {
        System.out.println("Hello world");
        launch();//step 1 we can send args to this 
    }

}