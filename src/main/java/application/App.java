package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.Path;

public class App extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Path.MAIN_VIEW));

        AnchorPane pane = loader.load();

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.setTitle("Inicio");
        stage.setResizable(false);
        stage.show();
    }
}
