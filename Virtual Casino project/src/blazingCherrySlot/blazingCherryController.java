package blazingCherrySlot;

import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;



import Menus.ChipProfile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

public class blazingCherryController implements Initializable {

	// Initialize all the symbols
	 private Image blazinCherry = new Image("blazingCherrySlot/Assets/BlazinCherry.png");
	 private Image doubleCherry = new Image("blazingCherrySlot/Assets/CherryPair.png");
	 private Image baseCherry = new Image("blazingCherrySlot/Assets/BaseCherry.png");
	 private Image bar = new Image("blazingCherrySlot/Assets/Bar.png");
	 private Image barDouble= new Image("blazingCherrySlot/Assets/BarDouble.png");
	 private Image BarTriple = new Image("blazingCherrySlot/Assets/BarTriple.png");
	 private Image blazinSeven = new Image("blazingCherrySlot/Assets/Blazinseven.png");
	 private Image blank = new Image("blazingCherrySlot/Assets/Blank.png");
	 private Image bg = new Image("blazingCherrySlot/Assets/BlazinCherryInterface.png");
	 private ArrayList<Image> reel = new ArrayList<>();
	 private ArrayList<Pair<String, Integer>> winHistory = new ArrayList<Pair<String, Integer>>();
	 private static ChipProfile prof;
	 private static Scene sc;
	
	 
	 private int balance;
	 private int bet = 1;
	 private int win = 0;
	 
	// Pass in the scene for the main menu
		public static void setScene(Scene scene) {
			sc = scene;
		}
		// pass in the player for game
		public static void setPlayer(ChipProfile player) {
			// TODO Auto-generated method stub
			prof = player;
		}
    @FXML
    private Label Credits;

    @FXML
    private ImageView Slot1;

    @FXML
    private ImageView Slot2;

    @FXML
    private ImageView Slot3;

    @FXML
    private Label Win;

    @FXML
    private Label addBet;

    @FXML
    private Label betAmount;

    @FXML
    private Label minusBet;
    
    @FXML
    private ImageView backgroundImage;

    @FXML
    private ImageView logo;
    
  
    @FXML
    void Spin(ActionEvent event) throws InterruptedException {

    	// only spin if the balance is higher than the bet
    	if (balance >= bet) {
	    	// subtract the bet from the balance
    		balance -= bet;
    		
    		
    		
    		//generate random number 0-99 and assign the image of that index to the slot positions
	    	Random ran = new Random();
	    	
	    	int num = ran.nextInt(100);
    		num = ran.nextInt(100);
	    	Image pos1 = reel.get(num);
	    	Slot1.setImage(pos1);	
	    	
	    	
    		num = ran.nextInt(100);
	    	Image pos2 = reel.get(num);
	    	Slot2.setImage(pos2);
	    	
    		num = ran.nextInt(100);
	    	Image pos3 = reel.get(num);
	    	Slot3.setImage(pos3);	
	    	//check the game for a win
	    	findWin(Slot1.getImage(),Slot2.getImage(),Slot3.getImage());

	    	// update labels 
	    	Win.setText(String.valueOf(win));
	    	Credits.setText(String.valueOf(balance));
    	}
    }
    
    @FXML
    void minus(MouseEvent event) {
    	// if the bet is more than the min bet subtract one from it
    	if (bet > 1) {
    		bet -= 1;
    		betAmount.setText(String.valueOf(bet));
    	}
    	
    } 
    @FXML
    void plus(MouseEvent event) {
    	// if the bet is less than the max bet add one to it 
    	if (bet < 10) {
    		bet += 1;
    		betAmount.setText(String.valueOf(bet));
    	}
    }

    @FXML
    void InfoButtonClicked(MouseEvent event) {

    	//make the win pop up as its own window
		Stage result = new Stage(); 
		result.initModality(Modality.APPLICATION_MODAL);
		
		//display the spins that won
		VBox vertList = new VBox();
		vertList.setStyle("-fx-border-color: black; -fx-border-width: 10;");
			
		for (Pair<String, Integer> p: winHistory) {
			Label bet = new Label();
			bet.setText("Win type: " + p.getKey() + " Amount: " + String.valueOf(p.getValue()));			
			bet.setStyle("-fx-font-size: 22; -fx-font-weight: bold; -fx-font-weight: bold; -fx-text-fill: green; ");
			vertList.getChildren().add(bet);
		}
				
		// make the scene and wait for the user to close it
		Scene resultingScene = new Scene(vertList);
		result.setScene(resultingScene);
		result.setResizable(false);
		result.setTitle("Blazin' Cherry Info");
		result.showAndWait();
    }
   
    public void initialize(URL url, ResourceBundle resourceBundle ){
    	
    	backgroundImage.setImage(bg);
    	logo.setImage(blazinCherry);
    	Slot1.setImage(blazinCherry);
    	Slot2.setImage(blazinCherry);
    	Slot3.setImage(blazinCherry);
    	//add bar and blank in at 0.25 probability each
    	for (int i = 0; i < 25; i++) {
    		reel.add(bar);
    		reel.add(blank);
    	}
    	//add bar double at 0.15 probability
    	for(int i = 0; i < 15; i ++) {
    		reel.add(barDouble);
    	}
    	//add baseCherry and Triple bar at 0.10 probability each
    	for(int i = 0; i < 10; i ++) {
    		reel.add(baseCherry);
    		reel.add(BarTriple);
    	}
    	// add double Cherry at 0.07 probability
    	for(int i = 0; i < 7; i ++) {
    		reel.add(doubleCherry);
    	}
    	// // add blazinSeven at 0.05 probability
    	for(int i = 0; i < 5; i++) {
    		reel.add(blazinSeven);
    	} 
    	// add blazinCherry at 0.03 probability
    	for(int i = 0; i < 3; i++) {
    		reel.add(blazinCherry);
    	}
    	reel.add(blazinCherry);
    	//set the labels to starting values
    	balance = prof.getTotalChips();
       	Win.setText(String.valueOf(win));
    	Credits.setText(String.valueOf(balance));
    	betAmount.setText(String.valueOf(bet));
    	Collections.shuffle(reel);
    }

 // Method to check the slots for wins
    public void findWin(Image slot1, Image slot2, Image slot3) {
    	
    	// create an array of the spin results
    	Image[] spinResult = new Image[3];
    	spinResult[0] = slot1;
    	spinResult[1] = slot2;
    	spinResult[2] = slot3;
    	
    	
    	// declare variables needed to track the symbol count
    	win = 0;
    	int anyBarCount = 0;
    	int barCount = 0;
    	int doubleBarCount = 0;
    	int tripleBarCount = 0;
    	int baseCherryCount = 0;
    	int doubleCherryCount = 0;
    	int blazinCherryCount = 0;
    	int blazinSevenCount = 0;
    	int anyCherryCount = 0;
    	
    	
    	// Get the identity of the three slots and increment the counts based on the result
    	for (int i = 0; i < spinResult.length; i++) {
    		
	    	if (spinResult[i].equals(bar)) {
	    		barCount++;
	    		anyBarCount++;
	    	} else if (spinResult[i].equals(barDouble)) {
	    		doubleBarCount++;
	    		anyBarCount++;
	    	} else if (spinResult[i].equals(BarTriple)) {
	    		tripleBarCount++;
	    		anyBarCount++;
	    	} else if (spinResult[i].equals(baseCherry)) {
	    		baseCherryCount++;
	    		anyCherryCount++;
	    	} else if (spinResult[i].equals(doubleCherry)) {
	    		doubleCherryCount++;
	    		anyCherryCount++;
	    	} else if (spinResult[i].equals(blazinCherry)) {
	    		blazinCherryCount++;
	    		anyCherryCount++;
	    	} else if (spinResult[i].equals(blazinSeven)) {
	    		blazinSevenCount++;
	    	} 
    	}
    	
    	//check the bar bets for wins
    	if (anyBarCount == 3) {
    		if (tripleBarCount == 3) {
        		balance += bet * 50;
        		win = bet * 50;
        		winHistory.add(new Pair<>("Triple Bar", bet * 50));
        	} else if (doubleBarCount == 3) {
        		balance += bet * 25;
        		win = bet * 25;
        		winHistory.add(new Pair<>("Double Bar", bet * 25));
        	} else if (barCount == 3) {
        		balance += bet * 10;
        		win = bet * 10;
        		winHistory.add(new Pair<>("Bar", bet * 10));
        	} else {
        		balance += bet * 2;
        		win = bet * 2;
        		winHistory.add(new Pair<>("Any Three Bars", bet * 2));
        	}
    		return;
    	}
    	//check the cherry bets for wins
    	if (anyCherryCount == 3 ) {
    		if (blazinCherryCount == 3) {
        		balance += bet * 1000;
        		win = bet * 1000;
        		winHistory.add(new Pair<>("JackPot", bet * 1000));
        	}  else if (doubleCherryCount == 3) {
        		balance += bet * 250;
        		win = bet * 250;
        		winHistory.add(new Pair<>("Double cherry", bet * 250));
        	} else if (blazinCherryCount == 2) {
        		balance += bet * 100;
        		win = bet * 100;
        		winHistory.add(new Pair<>("Double Blaze", bet * 100));
        	} else if (blazinCherryCount == 1 && (doubleCherryCount == 2 || baseCherryCount == 2)) {
        		balance += bet * 100;
        		win = bet * 100;
        		winHistory.add(new Pair<>("Blazin' plus match", bet * 100));
        	} else if (blazinCherryCount == 1) {
        		balance += bet * 75;
        		win = bet * 75;
        		winHistory.add(new Pair<>("Blazin' + Any Two Cherries", bet * 75));
        	} else if (baseCherryCount == 3) {
        		balance += bet * 50;
        		win = bet * 50;
        		winHistory.add(new Pair<>("baseCherry", bet * 50));
        	} else {
        		balance += bet * 10;
        		win = bet * 10;
        		winHistory.add(new Pair<>("Any Three Cherries", bet * 10));
        	}
    		return;
    	}
    	// check if the blazing seven won
    	if (blazinSevenCount == 3) {
    		balance += bet * 500;
    		win = bet * 500;
    		winHistory.add(new Pair<>("Balazin Sevens", bet * 500));
    		return;
    	}
    	
    	
    	return;
    }


 // method to return to menu 
 	public void exitGame() {
 		// update the profiles balance 
 		prof.setChips(balance);
 		
 		//change back the scene
 		Stage stage = (Stage) logo.getScene().getWindow();
 		stage.centerOnScreen();
 		stage.setTitle("Casino Floor");
 		stage.setScene(sc);
 	}
    
}
