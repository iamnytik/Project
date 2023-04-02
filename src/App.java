/*Help chatGPT the placement of the buttons is affecting the layout of my calendar grid?*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.text.*;
import javafx.event.EventHandler;
import java.time.LocalDate;
import java.time.Month;
public class App extends Application {
    
    static int month=LocalDate.now().getMonth().getValue();
    static int year=LocalDate.now().getYear();
    @Override
    public void start(Stage stage) {
        /*This function is unable to display the Hbox  onto scene1 can you fix this chatGPT?*/
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        //needs to get screen resolution 
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double X=screenBounds.getMaxX();
        double Y=screenBounds.getMaxY();
        StackPane stkobj =new StackPane(l);
        stkobj.setStyle("-fx-background-color: #90EE90;");

        Scene scene = new Scene(stkobj, X, Y);//ads a scene to the stage and we pass a root node specifying
        //the required dimensions
        Image left= new Image("file:../img/left.jpeg");
        Image right= new Image("file:../img/right.jpeg");
        /*Image icon= new Image("file:../img/OIP.jpeg");//icon of our app
        stage.getIcons().add(icon); fix this later*/
        scene.setFill(Color.web("#90EE90"));
        
        StackPane root = (StackPane) scene.getRoot();

        HBox hbox = new HBox();
        Button prevButton = new Button("Previous");
        Button nextButton = new Button("Next");
        hbox.getChildren().addAll(prevButton, nextButton);
        hbox.setSpacing(0.8*X);
        HBox.setHgrow(prevButton, Priority.SOMETIMES);
        HBox.setHgrow(nextButton, Priority.SOMETIMES);
        HBox.setMargin(prevButton, new Insets(10, 10, 10, 10));
        HBox.setMargin(nextButton, new Insets(10, 10, 10, 10));
        hbox.setAlignment(Pos.CENTER_LEFT);
        GridPane grid= drawGrid(X,Y);
        setMonthText(grid, "March 2023");
        root.getChildren().add(grid);
        root.getChildren().add(hbox);
        initialize(X,Y,root,grid,hbox,prevButton,nextButton);
        stage.setScene(scene);
        stage.show();
      

    }
        void setMonthText(GridPane grid, String month) {
            // Create the label with the month name
            Label monthLabel = new Label(month);
            monthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            monthLabel.setTextFill(Color.ORANGE);
        
            // Add the label to the top row of the grid
            grid.add(monthLabel, 0, 1, 18, 2);
            GridPane.setHalignment(monthLabel, HPos.CENTER);
        }
        
    
    GridPane drawGrid(double X, double Y)
    {  

        GridPane grid = new GridPane();
    grid.setPadding(new Insets(10));//sus
    grid.setHgap(10);
    grid.setVgap(10);

    // Add day-of-week labels
    String[] daysOfWeek = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
    for (int i = 0; i < daysOfWeek.length; i++) {
        Text dayText = new Text(daysOfWeek[i]);
        dayText.setStyle("-fx-font-weight: bold");
        grid.add(dayText, i, 0);
        GridPane.setHalignment(dayText, HPos.CENTER);
    }

    // Get current month and year
    LocalDate today = LocalDate.now();
    Month currentMonth = today.getMonth();
    //int currentYear = today.getYear();

    // Get first day of month and number of days in month
    LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
    int daysInMonth = currentMonth.length(firstDayOfMonth.isLeapYear());


    // Center grid in window
    double offsetX = (X ) *0.40;
    double offsetY = (Y) *0.2;
    grid.setTranslateX(offsetX);
    grid.setTranslateY(offsetY);

    // Add days of month to grid
    int dayOfMonth = 1;
    for (int row = 1; row < 7; row++) {
        for (int col = 0; col < 7; col++) {
            if (row == 1 && col < firstDayOfMonth.getDayOfWeek().getValue()) {
                // Add empty cell for days before first day of month
                Text emptyText = new Text("");
                grid.add(emptyText, col, row);
                GridPane.setHalignment(emptyText, HPos.CENTER);
            } else if (dayOfMonth <= daysInMonth) {
                // Add day of month to cell
                Text dayText = new Text(Integer.toString(dayOfMonth));
                grid.add(dayText, col, row);
                GridPane.setHalignment(dayText, HPos.CENTER);
                dayOfMonth++;
            }
        }
    }
    grid.setStyle("-fx-background-color: #90EE90;");
    //grid.setBlendMode( BlendMode.SOFT_LIGHT);
    
    return grid;
    }
    public static void main(String[] args) {
        System.out.println("Hello world");
        launch();//step 1 we can send args to this 
    }

    public void initialize(final double X, final double  Y,final StackPane s,GridPane oldgrid,final HBox olBox,Button... Buttons) {

            
            final Button b0=Buttons[0];//weird fix for a weird error
            b0.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("hello");
                    setSelectedMonth(-1);// adding a even listener that reduces -1 and sets the currently selected year and month
                    GridPane newgrid= drawGrid(X,Y);
                    s.getChildren().remove(oldgrid);
                    s.getChildren().remove(olBox);
                    
                    s.getChildren().add(newgrid);
                    s.getChildren().add(olBox);
                    
                    
                }
               
            });


            Buttons[1].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("hello");
                    setSelectedMonth(1);// adding a event listener that increases the month counter
                    GridPane newgrid= drawGrid(X,Y);
                    s.getChildren().remove(oldgrid);
                    s.getChildren().remove(olBox);
                    
                    s.getChildren().add(newgrid);
                    s.getChildren().add(olBox);


                    
                }
               
            });
        

    }
    public void setSelectedMonth(int op)
    {   
       
        
            month=month+op;
            if(month>12 || month<1 )//handling edge cases
            {
                year=year+op;
                if(op==1)
                {
                    month=1;

                }
                else
                {
                    month=12;
                }
            }
            System.out.println("yearis "+year+"month is"+month);
    
    }

}
