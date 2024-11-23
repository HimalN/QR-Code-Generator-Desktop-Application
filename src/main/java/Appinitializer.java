import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Appinitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/qrGeneratorHome.fxml"));
        Scene scene = new Scene(parent);
        // Load the icon image
        Image icon = new Image(getClass().getResourceAsStream("/images/icon.png"));
        // Set the taskbar icon
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Qr Code Generator");
        stage.show();

    }
}
