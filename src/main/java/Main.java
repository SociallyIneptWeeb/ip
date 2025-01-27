import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for SunderRay using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Image stageIcon = new Image(Objects.requireNonNull(
                    this.getClass().getResourceAsStream("/images/message-icon.png")));
            stage.getIcons().add(stageIcon);

            Scene scene = new Scene(ap);
            stage.setTitle("SunderRay");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
