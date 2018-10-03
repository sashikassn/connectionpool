/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.dep.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author slash
 */
public class StartUp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    
              try {

            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/UserInterface.fxml"));

            Scene mainScene = new Scene(root);

            primaryStage.setTitle("Connection Pool");
            primaryStage.setScene(mainScene);
            primaryStage.setResizable(false);
            
            primaryStage.show();
           

        } catch (IOException ex) {
           ex.printStackTrace();
        } catch (Throwable ex) {
           ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
