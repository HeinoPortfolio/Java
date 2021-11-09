
package matthewheino_inventorysystem;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import Model.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main entry point of the Inventory System. The tables will be initialized
 * from here as well.   
 * <p><b>FUTURE ENHANCEMENT</b> These are a few suggestions for the project. 
 * In the Inventory class a more robust search(lookup) method could be employed.
 * The current method can look for string that may be contained in the 
 * elements of the table but it is not the most efficient</p>
 * 
 * @author Matthew Heino
 */
public class MatthewHeino_InventorySystem extends Application{

    /**
     *  Main method of the Inventory System.
     * <b>Location of the java doc is in a separate zip folder called 
     * MHeinoJavadoc. </b>
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Create some Part objects and insert them into the inventory.
        InHouse in1 = new InHouse(Inventory.getnextAvailID(), "Brakes", 75.23
                ,5,3,7, 10);
        
        InHouse in2 = new InHouse(Inventory.getnextAvailID(), "Rims", 1000.55
                ,3,1,5, 12);
        
        InHouse in3 = new InHouse(Inventory.getnextAvailID(), "Gold Rims", 2000.00
                ,3,1,4, 32);
        
        InHouse in4 = new InHouse(Inventory.getnextAvailID(), "Steering Wheel", 2000.00
                ,3,1,4, 20);
        
        OutSourced os1 = new OutSourced(Inventory.getnextAvailID(), 
                "Carbon Rims", 5999.99, 10, 4, 15, "Ace Tires") ;
        
        OutSourced os2 = new OutSourced(Inventory.getnextAvailID(), 
                "Gearbox", 510.99, 10, 4, 15, "Clutchmatic");
        
        //Insert into the appropriate list.
        Inventory.addPart(in1);
        Inventory.addPart(in2);
        Inventory.addPart(in3);
        Inventory.addPart(in4);
        Inventory.addPart(os1);
        Inventory.addPart(os2);
        
        ObservableList<Part> assocparts = FXCollections.observableArrayList();
        
        assocparts.add(in1);
        assocparts.add(in2);
        
        //Create some Product objects. 
        Product prod1 = new Product(assocparts,Inventory.getnextAvailID(), "Simple Car", 3500, 12, 3, 15);
       
         Inventory.addProduct(prod1);
          
         // Clear associated parts.
        assocparts.clear();
        
        // Add parts to the associated parts lsit
        assocparts.add(in3);
        assocparts.add(os2);
        
        Product prod2 = new Product(assocparts,Inventory.getnextAvailID(), "Jazzy Car", 4500, 2, 1, 5);
        Inventory.addProduct(prod2);
     
        launch(args);
   
    }

    /**
     * Method to display the stage.  Shows the main window for the application.
     * 
     * @param stage             Main stage
     * @throws Exception        Exception if the stage cannot be created.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().
                getResource("/View_Controller/MainFXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory System");
        stage.show();
    }
    
}
