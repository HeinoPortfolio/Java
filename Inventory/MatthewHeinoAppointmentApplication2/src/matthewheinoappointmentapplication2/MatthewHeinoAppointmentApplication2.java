package matthewheinoappointmentapplication2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Main entry point for the application.
 *
 * @author Matthew Heino
 */
public class MatthewHeinoAppointmentApplication2 extends Application{

    /**Main driver 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().
                getResource("/View_Controller/LogInFXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Appointment Login Screen");
        stage.show();
        
    }
    
} // end class. 
