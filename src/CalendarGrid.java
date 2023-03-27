//Chceck this 
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CalendarGrid extends Application {

    @Override
    public void start(Stage stage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        
        // Add day-of-week labels
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < daysOfWeek.length; i++) {
            Text dayText = new Text(daysOfWeek[i]);
            dayText.setStyle("-fx-font-weight: bold");
            grid.add(dayText, i, 0);
            GridPane.setHalignment(dayText, HPos.CENTER);
        }
        
        // Get current month and year
        LocalDate today = LocalDate.now();
        Month currentMonth = today.getMonth();
        int currentYear = today.getYear();
        
        // Get first day of month and number of days in month
        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        int daysInMonth = currentMonth.length(firstDayOfMonth.isLeapYear());
        
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
        
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
