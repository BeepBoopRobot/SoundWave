import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        new JFXPanel();
        Platform.runLater(Main::launch);
    }
    public int windowWidth = 1000;
    public int windowHeight = 
    private static void launch() {
        Stage stage = new Stage();
        stage.setTitle("Doppler Effect Visualisation");
    }
}
