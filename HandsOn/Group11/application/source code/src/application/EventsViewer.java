package application;

import java.util.HashMap;
import java.util.List;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class EventsViewer{
	
	private HashMap<String, String> results;
	private HashMap<String, String> resultsLocation;
	
	private Scene scene;
	private BorderPane root;
	
	private ListView<String> nameResultsList;
	private Label descriptionResultsLabel;
	private Button startButton;

	private TextField nameField;
	private CheckBox mondayCheckbox;
	private CheckBox tuesdayCheckbox;
	private CheckBox wednesdayCheckbox;
	private CheckBox thursdayCheckbox;
	private CheckBox fridayCheckbox;
	private CheckBox saturdayCheckbox;
	private CheckBox sundayCheckbox;
	private TextField districtField;
	private CheckBox isFreeCheckbox;

	private CheckBox isLongCheckbox;


	public EventsViewer(Scene scene){
		this.scene = scene;
		this.root = new BorderPane();
		BackgroundImage bck = new BackgroundImage(new Image("resources/background.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(100, 100, true, true, true, true));
		root.setBackground(new Background(bck));
		setTopFilters();
		setResultsArea();
		this.scene.setRoot(root);
		this.results = new HashMap<String, String>();
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
		this.nameField = new TextField ();
		//Box for days of the week
		Text daysOfTheWeekText = new Text("Days of the Week:");
		daysOfTheWeekText.setFont(new Font("Arial", 20));
		HBox daysOfTheWeek = new HBox();
		daysOfTheWeek.setSpacing(5);
		VBox monday = new VBox();
			Text mondayText = new Text("Mo");
			mondayText.setFont(new Font("Arial", 15));
			this.mondayCheckbox = new CheckBox();
			monday.getChildren().add(mondayText);
			monday.getChildren().add(mondayCheckbox);
			daysOfTheWeek.getChildren().add(monday);
		VBox tuesday = new VBox();
			Text tuesdayText = new Text("Tu");
			tuesdayText.setFont(new Font("Arial", 15));
			this.tuesdayCheckbox = new CheckBox();
			tuesday.getChildren().add(tuesdayText);
			tuesday.getChildren().add(tuesdayCheckbox);
			daysOfTheWeek.getChildren().add(tuesday);
		VBox wednesday = new VBox();
			Text wednesdayText = new Text("We");
			wednesdayText.setFont(new Font("Arial", 15));
			this.wednesdayCheckbox = new CheckBox();
			wednesday.getChildren().add(wednesdayText);
			wednesday.getChildren().add(wednesdayCheckbox);
			daysOfTheWeek.getChildren().add(wednesday);
		VBox thursday = new VBox();
			Text thursdayText = new Text("Th");
			thursdayText.setFont(new Font("Arial", 15));
			this.thursdayCheckbox = new CheckBox();
			thursday.getChildren().add(thursdayText);
			thursday.getChildren().add(thursdayCheckbox);
			daysOfTheWeek.getChildren().add(thursday);
		VBox friday = new VBox();
			Text fridayText = new Text("Fr");
			fridayText.setFont(new Font("Arial", 15));
			this.fridayCheckbox = new CheckBox();
			friday.getChildren().add(fridayText);
			friday.getChildren().add(fridayCheckbox);
			daysOfTheWeek.getChildren().add(friday);
		VBox saturday = new VBox();
			Text saturdayText = new Text("Sa");
			saturdayText.setFont(new Font("Arial", 15));
			this.saturdayCheckbox = new CheckBox();
			saturday.getChildren().add(saturdayText);
			saturday.getChildren().add(saturdayCheckbox);
			daysOfTheWeek.getChildren().add(saturday);
		VBox sunday = new VBox();
			Text sundayText = new Text("Su");
			sundayText.setFont(new Font("Arial", 15));
			this.sundayCheckbox = new CheckBox();
			sunday.getChildren().add(sundayText);
			sunday.getChildren().add(sundayCheckbox);
			daysOfTheWeek.getChildren().add(sunday);
		firstRowfilters.getChildren().add(name);
		firstRowfilters.getChildren().add(nameField);
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
		this.districtField = new TextField ();
		secondRowfilters.getChildren().add(district);
		secondRowfilters.getChildren().add(districtField);
		filtersBox.getChildren().add(secondRowfilters);
		
		//Third and Last Horizontal Box for filters
		HBox thirdRowfilters = new HBox();
		thirdRowfilters.setSpacing(20);
		Text isFree = new Text("Free:");
		isFree.setFont(new Font("Arial", 20));
		this.isFreeCheckbox = new CheckBox();
		Text isLong = new Text("Long:");
		isLong.setFont(new Font("Arial", 20));
		this.isLongCheckbox = new CheckBox();
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
		
		VBox nameResults = new VBox();
		nameResults.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
		nameResults.setPrefWidth(200);
		nameResults.setOnMouseClicked(e -> {
			nameResults.requestFocus();
		});
		resultsBox.getChildren().add(nameResults);
		Label labelNames = new Label("Name");
		labelNames.setFont(new Font("Arial", 20));
		labelNames.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		labelNames.setPrefWidth(200);
		nameResults.getChildren().add(labelNames);
		this.nameResultsList = new ListView<String>();
		nameResults.getChildren().add(nameResultsList);
		nameResultsList.setOnMouseClicked( e -> {
			setDescription(getDescription(nameResultsList.getSelectionModel().getSelectedItem()));
			setImage(nameResultsList.getSelectionModel().getSelectedItem());
		});
		
		
		Separator separator = new Separator();
		separator.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		separator.setOrientation(Orientation.VERTICAL);
		resultsBox.getChildren().add(separator);
		
		VBox descriptionResults = new VBox();
		descriptionResults.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, null)));
		descriptionResults.setPrefWidth(200);
		resultsBox.getChildren().add(descriptionResults);
		Label labelDescription = new Label("Description");
		labelDescription.setFont(new Font("Arial", 20));
		labelDescription.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		labelDescription.setPrefWidth(200);
		descriptionResults.getChildren().add(labelDescription);
		this.descriptionResultsLabel = new Label();
		descriptionResults.getChildren().add(this.descriptionResultsLabel);
		
		Separator separator2 = new Separator();
		separator2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
		separator2.setOrientation(Orientation.VERTICAL);
		resultsBox.getChildren().add(separator2);
		
		this.root.setLeft(resultsBox);
	}
	
	private void setImage(String name) {
		Image mapImage = QuerySearch.getMapImage(this.resultsLocation.get(name));
		ImageView mapView = new ImageView(mapImage);
		root.setCenter(mapView);
	}

	//Search Button
	private void createButtonSearch(){
		this.startButton = new Button("Search");
		startButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
		startButton.setPrefSize(100, 50);
		startButton.setOnAction(e -> {
			makeSearch();
		});
	}
	
	private void setResults(List<Event> list){
		this.nameResultsList.getItems().clear();
		for (Event result : list){
			this.nameResultsList.getItems().add(result.getName());
		}
		System.gc();
		return;
	}
	
	private void setDescription(String description){
		this.descriptionResultsLabel.setText(description);
	}
	
	private String getDescription(String name){
		return this.results.get(name);
	}
	
	private void makeSearch(){
		String name = nameField.getText();
		String district = districtField.getText();
		if(name.equals(""))
			name = null;
		if(district.equals(""))
			district = null;
		
		boolean free = isFreeCheckbox.isSelected();
		boolean lon = isLongCheckbox.isSelected();
		boolean mo = mondayCheckbox.isSelected();
		boolean tu = tuesdayCheckbox.isSelected();
		boolean we = wednesdayCheckbox.isSelected();
		boolean th = thursdayCheckbox.isSelected();
		boolean fr = fridayCheckbox.isSelected();
		boolean sa = saturdayCheckbox.isSelected();
		boolean su = sundayCheckbox.isSelected();
		
		String query = QuerySearch.createQuery(name,district,free,lon,mo,tu,we,th,fr,sa,su);
		List<Event> resultsList = QuerySearch.executeQuery(query);
		root.setCenter(null);
		this.results = new HashMap<String,String>();
		this.resultsLocation = new HashMap<String,String>();
		for(Event event : resultsList){
			this.results.put(event.getName(),event.getDescription());
			this.resultsLocation.put(event.getName(),event.getUriToLocation());
		}
		this.setResults(resultsList);
	}


}
