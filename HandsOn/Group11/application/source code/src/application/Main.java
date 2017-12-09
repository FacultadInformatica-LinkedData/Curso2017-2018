package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			root.setPadding(new Insets(100, 0, 0, 0));
			//Welcome text
			Text text = new Text();
			text.setText("Welcome to Madrid Events");
			text.setFont(Font.font("Arial", 30));
			//Error text
			Text textData = new Text();
			textData.setText("Couldn't connect to data");
			textData.setFont(Font.font("Arial", 30));
			VBox vbox = new VBox(); 
			vbox.getChildren().add(text);
			vbox.getChildren().add(textData);
			textData.setVisible(false);
			vbox.setSpacing(100);
			vbox.setAlignment(Pos.CENTER);
			root.setTop(vbox);
			//Start button
			Button startButton = new Button("Start");
			startButton.setPrefSize(100, 100);
			startButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	if(connectToData())
	            		primaryStage.close();
	            	else{
	            		textData.setVisible(true);
	            		startButton.setText("Try again");
	            	}
	            }
	        });
			root.setCenter(startButton);
			//Main scene
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//Title
			primaryStage.setTitle("Madrid Events");
			//Background
			BackgroundImage bck = new BackgroundImage(new Image("resources/background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(100, 100, true, true, true, true));
			root.setBackground(new Background(bck));
			//Deployment
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private boolean connectToData() {
		//TODO Connect to data on AWS or local server Virtuoso
		return false;
	}
}
