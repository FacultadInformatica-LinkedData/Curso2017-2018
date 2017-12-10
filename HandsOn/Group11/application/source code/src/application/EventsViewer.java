package application;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class EventsViewer{
	
	private Scene scene;
	private BorderPane root;

	public EventsViewer(Scene scene){
		this.scene = scene;
		this.root = new BorderPane();
		BackgroundImage bck = new BackgroundImage(new Image("resources/background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(100, 100, true, true, true, true));
		root.setBackground(new Background(bck));
		setTopFilters();
		setResultsArea();
		this.scene.setRoot(root);
	}

	private void setTopFilters(){
		//Vertical box for filters
		VBox filtersBox = new VBox();
		filtersBox.setSpacing(20);
		
		//Filters' label
		Label labelFilter = new Label("Filters");
		labelFilter.setFont(new Font("Arial", 30));
		labelFilter.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		filtersBox.getChildren().add(labelFilter);
		
		//First Horizontal Box for filters
		HBox firstRowfilters = new HBox();
		firstRowfilters.setSpacing(20);
		Text name = new Text("Name:");
		name.setFont(new Font("Arial", 20));
		TextField textField = new TextField ();
		//Box for days of the week
		Text daysOfTheWeekText = new Text("Days of the Week:");
		daysOfTheWeekText.setFont(new Font("Arial", 20));
		HBox daysOfTheWeek = new HBox();
		daysOfTheWeek.setSpacing(5);
		VBox monday = new VBox();
			Text mondayText = new Text("Mo");
			mondayText.setFont(new Font("Arial", 15));
			CheckBox mondayCheckbox = new CheckBox();
			monday.getChildren().add(mondayText);
			monday.getChildren().add(mondayCheckbox);
			daysOfTheWeek.getChildren().add(monday);
		VBox tuesday = new VBox();
			Text tuesdayText = new Text("Tu");
			tuesdayText.setFont(new Font("Arial", 15));
			CheckBox tuesdayCheckbox = new CheckBox();
			tuesday.getChildren().add(tuesdayText);
			tuesday.getChildren().add(tuesdayCheckbox);
			daysOfTheWeek.getChildren().add(tuesday);
		VBox wednesday = new VBox();
			Text wednesdayText = new Text("We");
			wednesdayText.setFont(new Font("Arial", 15));
			CheckBox wednesdayCheckbox = new CheckBox();
			wednesday.getChildren().add(wednesdayText);
			wednesday.getChildren().add(wednesdayCheckbox);
			daysOfTheWeek.getChildren().add(wednesday);
		VBox thursday = new VBox();
			Text thursdayText = new Text("Th");
			thursdayText.setFont(new Font("Arial", 15));
			CheckBox thursdayCheckbox = new CheckBox();
			thursday.getChildren().add(thursdayText);
			thursday.getChildren().add(thursdayCheckbox);
			daysOfTheWeek.getChildren().add(thursday);
		VBox friday = new VBox();
			Text fridayText = new Text("Fr");
			fridayText.setFont(new Font("Arial", 15));
			CheckBox fridayCheckbox = new CheckBox();
			friday.getChildren().add(fridayText);
			friday.getChildren().add(fridayCheckbox);
			daysOfTheWeek.getChildren().add(friday);
		VBox saturday = new VBox();
			Text saturdayText = new Text("Sa");
			saturdayText.setFont(new Font("Arial", 15));
			CheckBox saturdayCheckbox = new CheckBox();
			saturday.getChildren().add(saturdayText);
			saturday.getChildren().add(saturdayCheckbox);
			daysOfTheWeek.getChildren().add(saturday);
		VBox sunday = new VBox();
			Text sundayText = new Text("Su");
			sundayText.setFont(new Font("Arial", 15));
			CheckBox sundayCheckbox = new CheckBox();
			sunday.getChildren().add(sundayText);
			sunday.getChildren().add(sundayCheckbox);
			daysOfTheWeek.getChildren().add(sunday);
		firstRowfilters.getChildren().add(name);
		firstRowfilters.getChildren().add(textField);
		firstRowfilters.getChildren().add(daysOfTheWeekText);
		firstRowfilters.getChildren().add(daysOfTheWeek);
		//Search Button
		Button startButton = new Button("Search");
		startButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
		startButton.setPrefSize(100, 50);
		firstRowfilters.getChildren().add(startButton);
		filtersBox.getChildren().add(firstRowfilters);
		
		//Second Horizontal Box for filters
		HBox secondRowfilters = new HBox();
		secondRowfilters.setSpacing(20);
		Text district = new Text("District:");
		district.setFont(new Font("Arial", 20));
		TextField districtField = new TextField ();
		secondRowfilters.getChildren().add(district);
		secondRowfilters.getChildren().add(districtField);
		filtersBox.getChildren().add(secondRowfilters);
		
		//Third and Last Horizontal Box for filters
		HBox thirdRowfilters = new HBox();
		thirdRowfilters.setSpacing(20);
		Text isFree = new Text("Free:");
		isFree.setFont(new Font("Arial", 20));
		CheckBox isFreeCheckbox = new CheckBox();
		Text isLong = new Text("Long:");
		isLong.setFont(new Font("Arial", 20));
		CheckBox isLongCheckbox = new CheckBox();
		thirdRowfilters.getChildren().add(isFree);
		thirdRowfilters.getChildren().add(isFreeCheckbox);
		thirdRowfilters.getChildren().add(isLong);
		thirdRowfilters.getChildren().add(isLongCheckbox);
		filtersBox.getChildren().add(thirdRowfilters);
		Separator separator = new Separator();
		separator.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		separator.setOrientation(Orientation.HORIZONTAL);
		filtersBox.getChildren().add(separator);
		
		this.root.setTop(filtersBox);
	}
	
	private void setResultsArea() {
		
	}


}
