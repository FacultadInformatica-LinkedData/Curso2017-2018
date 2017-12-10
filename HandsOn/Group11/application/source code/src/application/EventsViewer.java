package application;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class EventsViewer{
	
	private Scene scene;
	private BorderPane root;
	
	private VBox nameResults;
	private VBox descriptionResults;
	private Button startButton;

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
		createButtonSearch();
		firstRowfilters.getChildren().add(this.startButton);
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
		HBox resultsBox = new HBox();
		
		this.nameResults = new VBox();
		this.nameResults.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
		this.nameResults.setPrefWidth(200);
		this.nameResults.setOnMouseClicked(e -> {
			this.nameResults.requestFocus();
		});
		resultsBox.getChildren().add(nameResults);
		Label labelNames = new Label("Name");
		labelNames.setFont(new Font("Arial", 20));
		labelNames.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		labelNames.setPrefWidth(200);
		nameResults.getChildren().add(labelNames);
		ScrollPane scrollNames = new ScrollPane();
		scrollNames.setContent(nameResults); 
		resultsBox.getChildren().add(scrollNames);
		
		Separator separator = new Separator();
		separator.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		separator.setOrientation(Orientation.VERTICAL);
		resultsBox.getChildren().add(separator);
		
		this.descriptionResults = new VBox();
		this.descriptionResults.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
		this.descriptionResults.setPrefWidth(200);
		resultsBox.getChildren().add(descriptionResults);
		Label labelDescription = new Label("Description");
		labelDescription.setFont(new Font("Arial", 20));
		labelDescription.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		labelDescription.setPrefWidth(200);
		descriptionResults.getChildren().add(labelDescription);
		
		Separator separator2 = new Separator();
		separator2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		separator2.setOrientation(Orientation.VERTICAL);
		resultsBox.getChildren().add(separator2);
		
		this.root.setLeft(resultsBox);
	}
	
	//Search Button
	private void createButtonSearch(){
		this.startButton = new Button("Search");
		startButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
		startButton.setPrefSize(100, 50);
		startButton.setOnAction(e -> {
			//DEBUG
			List<String> strings = new LinkedList<String>();
			for(int i = 0; i < 100; i++){
				strings.add(Double.toString(Math.random()));
			}
			strings.add("----------------------------------------------------------------------------");
			setResults(strings);
	    	});
	}
	
	private void setResults(List<String> list){
		ObservableList<Node> children = this.nameResults.getChildren();
		Label label = null;
		for(Node n : children){
			if(n instanceof Label){
				label = (Label)n;
			}
		}
		children.clear();
		children.add(label);
		for (String result : list){
			Text s = new Text(result);
			s.setOnMouseClicked(e -> {
				setDescription(getDescription(result));
				s.requestFocus();
			});
			s.fillProperty().bind(Bindings
					.when(s.focusedProperty())
					.then(Color.RED)
					.otherwise(Color.BLACK)
			);
			s.setFont(new Font("Arial", 15));
			children.add(s);
		}
		System.gc();
		return;
	}
	
	private void setDescription(String description){
		ObservableList<Node> children = this.descriptionResults.getChildren();
		Label label = null;
		for(Node n : children){
			if(n instanceof Label){
				label = (Label)n;
				break;
			}
		}
		children.clear();
		children.add(label);
		Label descrip = new Label(description);
		descrip.setWrapText(true);
		children.add(descrip);
	}
	
	private String getDescription(String s){
		return s+"ABC";
	}
	
	public int[] getFilters(){
		return null;
	}
	
	private List<String> makeSearch(){
		return null;
	}


}
