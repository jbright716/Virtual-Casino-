package roulette;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.TreeMap;

import Menus.ChipProfile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Roulette implements Initializable  {

	
	@FXML
    private GridPane topAlts;
	@FXML
    private GridPane botAlts;
    @FXML
    private GridPane numGP;
    @FXML
    private Label balance;
    @FXML
    private Label totalBet;
    @FXML
    private Label win;
    @FXML
    private Pane doubleZero;
    @FXML
    private Pane zero;
    @FXML
    private BorderPane board;
    @FXML
    private ImageView exit;

    
    
    @FXML
    private ToggleButton chipFifty;

    @FXML
    private ImageView chipFiftyImg;

    @FXML
    private ToggleButton chipFive;

    @FXML
    private ImageView chipFiveImg;
    @FXML
    private ImageView chipHundredImg;
    @FXML
    private ToggleButton chipHundred;

    @FXML
    private ImageView chipOneImg;

    @FXML
    private ToggleGroup chipSelect;

    @FXML
    private ToggleButton chipTen;
    
    @FXML
    private ToggleButton chipOne;

    @FXML
    private ImageView chipTenImg;

    @FXML
    private ToggleButton chipTwentyFive;

    @FXML
    private ImageView chipTwentyFiveImg;

   
   
    // declare variables for needed for the roulette game
	private List<Space> boardOrder;
	private Map<Integer,Space> wheelOrder;
	public Map<Space,Integer> bets = new HashMap<Space,Integer>();
	private Stack<Pair<Space,Integer>> betsPlaced = new Stack<>();
	private int chpAmt;
	private int gBalance;
	private int totBet = 0;
	private Space winningSpace;
	private static ChipProfile prof;
	private static Scene sc;
	//File bgsong = new File("roulette/Assets/bgSong.mp3");
	

	// load background music 
	//Media bgMus = new Media(bgsong.toURI().toString());
	//MediaPlayer ply = new MediaPlayer(bgMus);
	
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
    void spin(ActionEvent event) {

    	
    	if (totBet >= 5) {
    	spinWheel();
    	balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
    	}
    }
    
    @FXML
    void clearBets(ActionEvent event) {
    	clear();
    	balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
    
    }
    @FXML
    void undoBet(ActionEvent event) {
    	undoBet();
    	balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
    }
    
    @FXML
    void exit(MouseEvent event) {
    	exitGame();
    }
   
    
    @FXML
    void chipClicked(ActionEvent e) {
    	
    	
    	if (e.getSource() == chipOne ) {
    		chipOneImg.setEffect(new DropShadow(25,Color.YELLOW));
    		setChipSelected(1);
    	} else {
    		chipOneImg.setEffect(new DropShadow());
    	}
    	if (e.getSource() == chipFive ) {
    		chipFiveImg.setEffect(new DropShadow(25,Color.YELLOW));
    		setChipSelected(5);
    	} else {
    		chipFiveImg.setEffect(new DropShadow());
    	}
    	if (e.getSource() == chipTen ) {
    		chipTenImg.setEffect(new DropShadow(25,Color.YELLOW));
    		setChipSelected(10);
    	} else {
    		chipTenImg.setEffect(new DropShadow());
    	}
    	if (e.getSource() == chipTwentyFive ) {
    		chipTwentyFiveImg.setEffect(new DropShadow(25,Color.YELLOW));
    		setChipSelected(25);
    	} else {
    		chipTwentyFiveImg.setEffect(new DropShadow());
    	}
    	if (e.getSource() == chipFifty ) {
    		chipFiftyImg.setEffect(new DropShadow(25,Color.YELLOW));
    		setChipSelected(50);
    	} else {
    		chipFiftyImg.setEffect(new DropShadow());
    	}
    	if (e.getSource() == chipHundred ) {
    		chipHundredImg.setEffect(new DropShadow(25,Color.YELLOW));
    		setChipSelected(100);
    	} else {
    		chipHundredImg.setEffect(new DropShadow());
    	}
    }
   
    @Override
    	public void initialize(URL url, ResourceBundle resourceBundle ){
    	
    	wheelOrder = new TreeMap<Integer,Space>();
		boardOrder = new ArrayList<Space>();
		fillBoard(wheelOrder);
		
    	
    	
    	// set the images of the chips
    	chipOneImg.setImage(new Image("roulette/Assets/PokerChip1.png"));
    	chipFiveImg.setImage(new Image("roulette/Assets/PokerChip5.png"));
    	chipTenImg.setImage(new Image("roulette/Assets/PokerChip10.png"));
    	chipTwentyFiveImg.setImage(new Image("roulette/Assets/PokerChip25.png"));
    	chipFiftyImg.setImage(new Image("roulette/Assets/PokerChip50.png"));
    	chipHundredImg.setImage(new Image("roulette/Assets/PokerChip100.png"));
    	exit.setImage(new Image("roulette/Assets/exit.png"));
    	//set up 0 and 00 spaces
    	Space zerosp = new Space(0,Color.GREEN,"Straight up");
		Space doubleZerosp =  new Space(100,Color.GREEN,"Straight up");
    	
    	// add 0 to the left of the border Pane 
    	Pane zeroPane = zerosp.makeSpecNumSpace();
		zeroPane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(zerosp);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
		board.setLeft(zeroPane);
		
		
		//add 00 to the right of the ZeroPane
    	Pane dzeroPane = doubleZerosp.makeSpecNumSpace();
		dzeroPane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(doubleZerosp);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
		board.setRight(dzeroPane);
    	// Set up the number spaces for the board
        int count = 0;
        
    	for(int i=0; i < 12; i++) {
    		
        	for(int j = 2; j > -1; j --) {	
        		//add the space to the grid
        		Space currentSpace = boardOrder.get(count);
        		Pane tempPane = currentSpace.makeNumSpace();
        		tempPane.getChildren().get(0).setOnMouseClicked(e -> 
        		{placeBet(currentSpace);
        		balance.setText(String.valueOf(gBalance));
            	totalBet.setText(String.valueOf(totBet));
        			});
        		numGP.add(tempPane, i, j);
        	

       
        		count++;
        	}
        	
        }
    	
    	
    	
    	
    	// Make the top row of alternate bets
    	Space fTwelve = new Space("First 12");
    	Pane fTwelvePane = fTwelve.makeTopAltSpace();
    	topAlts.add(fTwelvePane, 0, 0);
    	fTwelvePane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(fTwelve);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
    	
    	Space sTwelve = new Space("Second 12");
    	Pane sTwelvePane = sTwelve.makeTopAltSpace();
    	topAlts.add(sTwelvePane, 1, 0);
    	sTwelvePane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(sTwelve);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
    	
    	Space lTwelve = new Space("Last 12");
    	Pane lTwelvePane = lTwelve.makeTopAltSpace();
    	topAlts.add(lTwelvePane, 2, 0);
    	lTwelvePane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(lTwelve);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
    	
    	
    
    	//create the bottom row of alternate bets
    	
    	// make a space for the first 18 bet
    	Space fHalf = new Space("First 18");
    	Pane fHalfPane = fHalf.makeBotAltSpace();
    	botAlts.add(fHalfPane, 0, 0);
    	fHalfPane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(fHalf);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
    	// make a space for an even bet
    	Space even = new Space("Even");
    	Pane evenPane = even.makeBotAltSpace();
    	botAlts.add(evenPane, 1, 0);
    	evenPane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(even);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
    	// make a space for the red bet
    	Space red = new Space("Red");
    	Pane redPane = red.makeBotAltSpace();
    	botAlts.add(redPane, 2, 0);
    	redPane.setBackground(new Background( new BackgroundFill(Color.RED,null,null)));
    	redPane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(red);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
    	// make a space for a black bet
    	Space black = new Space("Black");
    	Pane blackPane = black.makeBotAltSpace();
    	botAlts.add(blackPane, 3, 0);
    	blackPane.setBackground(new Background( new BackgroundFill(Color.BLACK,null,null)));
    	blackPane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(black);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
    	// make a space for an odd bet
    	Space odd = new Space("Odd");
    	Pane oddPane = odd.makeBotAltSpace();
    	botAlts.add(oddPane, 4, 0);
    	oddPane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(odd);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
    	// make a space for the last 18 bet
    	Space sHalf = new Space("Last 18");
    	Pane sHalfPane = sHalf.makeBotAltSpace();
    	botAlts.add(sHalfPane, 5, 0);
    	sHalfPane.getChildren().get(0).setOnMouseClicked(e -> 
		{placeBet(sHalf);
		balance.setText(String.valueOf(gBalance));
    	totalBet.setText(String.valueOf(totBet));
			});
    	
    	// set the balance
    	gBalance = prof.getTotalChips();
    	balance.setText(String.valueOf(gBalance));
    	
    	

    	setUpMus();
    	
    }



	//select the chip thats being placed
	public void setChipSelected(int amt) {
		chpAmt = amt;
		}
	
	

	public void setUpMus(){
		// load background music 
		//Media bgMus = new Media(bgsong.toURI().toString());
		//MediaPlayer ply = new MediaPlayer(bgMus);
		
		//ply.play();
		
		
	}

	public void fillBoard (Map<Integer,Space> gameBoard) {
		
		List<Space>  genOrder = new ArrayList<>();
		int redCount = 0;
		int blackCount = 0;
		Random rand = new Random();
		//Generate random color for each number up to 36 excluding 0
		for (int i = 1; i < 37; i++) {
			
			do {
				
				// generate random integer either  0 and 1
				int n = rand.nextInt(2);
				
				//If the generated number is 0 create a red space of the index number
				if( n == 0 && redCount < 18 && preventTriple(Color.RED,i-1,genOrder)) {
					// put space into stack
					genOrder.add(new Space(i,Color.RED,"Straight up"));
					redCount++;
					break;
				}
				//If the generated number is 1 create a black space of the index number
				if ( n == 1 && blackCount < 18 && preventTriple(Color.BLACK,i-1,genOrder)) {
					// put space into stack
					genOrder.add(new Space(i,Color.BLACK,"Straight up"));
					blackCount++;
					break;
				
				}
			
			}
			while (true);
		}
	
		//set the board 
		boardOrder.addAll(genOrder);
		
		
		
		//shuffle the board
		Collections.shuffle(genOrder);
		
		// add the two alternate colors 0 & 00 at opposing ends
			gameBoard.put(0, new Space(0,Color.GREEN,"Straight up"));
			gameBoard.put(18, new Space(100,Color.GREEN,"Straight up"));
		// fill the first slots
			gameBoard.put(1, genOrder.get(0));
			genOrder.remove(0);
		//add Spaces into the game board in alternating colors	
		for ( int i = 0; i < 38;i++) {
			
			// continue for filled spots
			if (i == 0)
				continue;
			if (i == 18)
				continue;
			if (i == 1) 
				continue;
			
			int itr = 0;
			while(true) {
				//if there is only one space left put it in the board
				if(genOrder.size() == 1) {
					gameBoard.put(i, genOrder.get(itr));
					break;
				} else {
				
				// If the previous color wasn't the same put it into the board
				if (gameBoard.get(i-1).getColor() != genOrder.get(itr).getColor()) {
					gameBoard.put(i, genOrder.get(itr));
					genOrder.remove(itr);
					break;
				}
				
				
				//try the next spot
				itr++;
				}
			}
			
		}
				
		
	
	}

	

	private boolean preventTriple(Color color, int index, List<Space> currentList) {
		
		//if there are no spaces allow one to be created
		if (currentList.isEmpty()) 
			return true;

		// if there are not 2 previous spaces to check return true
		if (index - 2 < 0) 
			return true; 
			
		
		// Ignore method towards the end of list to maintain odds
		if (index > 30) {
			return true;
		}
		// check if there have already been two of the same color spaces in a row
		if (color.equals(Color.RED))
			if (!currentList.get(index - 1).getColor().equals(Color.RED) || !currentList.get(index - 2).getColor().equals(Color.RED)) 
				return true;
				
			
			
		//check if there have already been two of the same color spaces in a row
		if (color.equals(Color.BLACK))
			if (!currentList.get(index - 1).getColor().equals(Color.BLACK) || !currentList.get(index - 2).getColor().equals(Color.BLACK)) 
				return true;
				
		
		// if there were two spaces in a row of the same color return false
		return false;
	}
	
	
	

	// method to spin the wheel
	public void spinWheel(){
		// generate a random number and get the space that corresponds with it
		Random ran = new Random();
		int num = ran.nextInt(38);
		winningSpace = wheelOrder.get(num);
		int totalWinnings = 0;
		//make the win pop up as its own window
			Stage result = new Stage(); 
			result.initModality(Modality.APPLICATION_MODAL);
			
		//display which space won
		StackPane spinDisplay = new StackPane();
		spinDisplay.setStyle("-fx-border-color: black; -fx-border-width: 10;");
		
		Text txt;
		if(winningSpace.getNumber() != 100) {
			txt = new Text(Integer.toString(winningSpace.getNumber()));
		} else {
			txt = new Text("00");
		}
		
		txt.setFill(Color.WHITE);
		txt.setStyle("-fx-font-size: 90; -fx-font-weight: bold; ");

		spinDisplay.getChildren().addAll(new Circle(100, winningSpace.getColor()),txt);
			
		//display the bets the user played
		VBox vertList = new VBox();
		vertList.setStyle("-fx-border-color: black; -fx-border-width: 10;");
		
		// check each bet to see if it won
		for (Space space: bets.keySet()) {
			//System.out.println("Result is " + wheelOrder.get(num).getNumber() + ". Color is " + wheelOrder.get(num).getColor());
			boolean won = false;
			int win = 0;
			won = findWin(space,wheelOrder.get(num));
			//System.out.println("Starting Balance is " + balance);
			if (won) {
				win = calculatePayout(space, bets.get(space));
				totalWinnings += win;
				gBalance = gBalance + win;
				
				//display the bet in green text to signal the win
				Label bet = new Label();
				bet.setText("Bet: " + space.getName() + " Amount: " + bets.get(space));
				bet.setStyle("-fx-font-size: 22; -fx-font-weight: bold; -fx-font-weight: bold; -fx-text-fill: green; ");
				vertList.getChildren().add(bet);
				//System.out.println("You won $" + win + ". New balance is " + balance);
			}else {
				//System.out.println("You lost $" + bets.get(space) + ". New balance is " + balance);
				//display the bet in res text to signal the loss
				Label bet = new Label();
				bet.setText("Bet: " + space.getName() + " Amount: " + bets.get(space));
				bet.setStyle("-fx-font-size: 22; -fx-font-weight: bold; -fx-font-weight: bold; -fx-text-fill: Red; ");
				vertList.getChildren().add(bet);
			}
			
			
		}
	
		
		//add both sections to a vbox
		HBox sections = new HBox();
		sections.getChildren().addAll(vertList,spinDisplay);
		
		HBox outputText = new HBox();
		outputText.setStyle("-fx-border-color: black; -fx-border-width: 10;");
		Label output = new Label();
		output.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-font-weight: bold; -fx-text-fill: #bdaa0a; ");
		
		if (totalWinnings > 0) {
			output.setText("Congratulations, your total winnings are " + totalWinnings + "!");
		} else {
			output.setText("No wins, Better luck next time.");
		}
			
		outputText.getChildren().add(output);
		

		VBox display = new VBox();
		display.setStyle("-fx-background-color:  #52595d ");
		display.getChildren().addAll(sections, outputText);
		// make the scene and wait for the user to close it
		Scene resultingScene = new Scene(display);
		result.setScene(resultingScene);
		result.setResizable(false);
		result.setTitle("Spin Result");
		result.showAndWait();
		
		
		
		
		//reset the game for the next spin
		bets.clear();
		totBet = 0;
		betsPlaced.clear();
	}
	
	//clear bets from he list
	public void clear() {
		bets.clear();
		gBalance += totBet;
		totBet = 0;
		//System.out.println("bets cleared");
		
	}
	// method to place bet
	public void placeBet(Space sp) {
	
		if (chpAmt != 0 && gBalance >= chpAmt && totBet < 500) {
		// add the placed chip to the total bet amount and remove it from the balance
		gBalance -= chpAmt;
		totBet += chpAmt;
		
		betsPlaced.push(new Pair<>(sp,chpAmt));
		// place each bet into an map of the bets with the total amount as the value
		if (bets.containsKey(sp)) {
			bets.put(sp, chpAmt + bets.get(sp));
			//System.out.println("bet amount on " + sp.getName() + " is: " + bets.get(sp));
		}
		else {
			bets.put(sp, chpAmt);
			//System.out.println("bet amount on " + sp.getName() + " is: " + bets.get(sp));
		}
		//System.out.println("Bet Placed. Total bet is " + totBet);
		}
	}
	// method to undo Bets
	public void undoBet() {
		
		if(!betsPlaced.isEmpty()) {
		// get the last bet
		Pair<Space,Integer> lastBet = betsPlaced.pop();
		
		// remove last bet from the total and add it back to the balance
		totBet -= lastBet.getValue();
		gBalance += lastBet.getValue();
		// remove the bet
		bets.put(lastBet.getKey(), bets.get(lastBet.getKey()) - lastBet.getValue());
		}
		
	}
	
	
	// Calculate the payout for the won bet
	private int  calculatePayout(Space space, int betAmount) {
		int payOut;
		
		switch (space.getBetType()) {
			case ("Even") :
			case ("Odd"):
			case ("Red"):
			case ("Black"):
			case ("First 18"):
			case ("Last 18"):
				payOut = betAmount * 2;
				break;
			case "First 12":
			case "Second 12":
			case "Last 12":
				payOut = betAmount * 3;
				break;
			case "Straight up":
				payOut = betAmount * 36;
				break;
			default:
				payOut = 0;
		}
		return payOut;
	}

	
	// Determine if the player has won this spin
	private boolean findWin(Space bet, Space result) {
		boolean won = false;
		
		switch (bet.getBetType()) {
		case ("Even") :
			if (result.getNumber() % 2 == 0)
				won = true;
			break;
		case ("Odd"):
			if (result.getNumber() % 2 != 0)
				won = true;
			break;
		case ("Red"):
			if (Color.RED.equals(result.getColor()))
				won = true;
			break;
		case ("Black"):
			if (Color.BLACK.equals(result.getColor()))
				won = true;
			break;
		case ("First 18"):
			if(result.getNumber() < 19)
				won = true;
			break;
		case ("Last 18"):
			if(result.getNumber() > 18)
				won = true;
			break;
		case "First 12":
			if(result.getNumber() <= 12)
				won = true;
			break;
		case "Second 12":
			if(result.getNumber() > 12 && result.getNumber() <= 24)
				won = true;
			break;
		case "Last 12":
			if(result.getNumber() > 25)
				won = true;
			break;
		case "Straight up":
			if (bet.getNumber() == result.getNumber())
				won = true;
			break;
		default:
			won = false;
	}
		
		
		return won;
	}
	// method to return to menu 
	public void exitGame() {
		// update the profiles balance 
		prof.setChips(gBalance);
		
		//change back the scene
		Stage stage = (Stage) exit.getScene().getWindow();
		stage.centerOnScreen();
		stage.setTitle("Casino Floor");
		stage.setScene(sc);
	}
	
}


