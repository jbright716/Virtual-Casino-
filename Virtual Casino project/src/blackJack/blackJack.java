package blackJack;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

import Menus.ChipProfile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import roulette.Space;
import tableAccessories.Card;
import tableAccessories.Deck;



public class blackJack  implements Initializable{
	
	// ArrayLists to hold hands of cards
	ArrayList<Card> dealerHand = new ArrayList<Card>();	
	ArrayList<Card> playerHand = new ArrayList<Card>();
	private Stack<Integer> betsPlaced = new Stack<>();
	// Generate a new deck of cards
	Deck currentDeck = new Deck();
		
	private static ChipProfile player;
	private static Scene sc;
	

	// Pass in the scene for the main menu
	public static void setScene(Scene scene) {
		sc = scene;
	}
	// pass in the player for game
	public static void setPlayer(ChipProfile currentPlayer) {
		// TODO Auto-generated method stub
		player = currentPlayer;
	}
		
	// Balance and bet variables
		int balance;
		int bet;
		int insuranceBet = 0;
		boolean dealerBlackJack;
		boolean hasBust;
		// FXML variables for GUI
		@FXML
		private Button newGame;
		@FXML
		private Button yes;
		@FXML
		private Button no;
		@FXML
		private Label promptLabel;
		
		@FXML
		private Label playerCards;
		@FXML
		private Image bg = new Image("blackJack/Assets/Bj.png");
		@FXML
		private Label dealerCards;
		
		@FXML
		private Label currentBalance;
		@FXML
		private Label totBet;
		@FXML
		private Pane betView;
		
		@FXML
		private Label win;

		
		@FXML
		private Label winOrLose;
		
		@FXML
		private Label totalP;
		@FXML
		private Label totalD;
		
		
		@FXML
		private Pane promptPane;
		
		@FXML
		private Pane gameUI;
		@FXML
		private HBox dealerHbox ;
		@FXML
		private HBox playerHbox ;
		
	    @FXML
	    private ToggleButton chipFifty;

	    @FXML
	    private ImageView chipFiftyImg;
	    @FXML
	    private ImageView exit;

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

	    @FXML
	    private ImageView backgroundImage;
		// Method to start a new hand of Black Jack
		public void startHand() {
			
			 if (bet == 0){	
				 winOrLose.setVisible(true);
				winOrLose.setText("Please Place A Bet!");
				return;
				}
			 if (bet >= player.getTotalChips())
				 bet = player.getTotalChips();
			
			if (bet > 0 && bet <= player.getTotalChips()) {
				
				// Reset GUI variables for start of new game
				betView.setVisible(false);
				winOrLose.setVisible(false);
				gameUI.setVisible(true);
				totBet.setText(String.valueOf(bet));
				win.setText("");
			
	
				// Reset hands
				playerHand.clear();
				dealerHand.clear();
				
				
				
				// Draw and print out cards
				playerHand.add(currentDeck.Draw());
				printPlayerCard(playerHand.get(0));
				dealerHand.add(currentDeck.Draw());
				printDealerCard(dealerHand.get(0));
				playerHand.add(currentDeck.Draw());
				printPlayerCard(playerHand.get(1));
				
				totalP.setText(String.valueOf(calcTotal(playerHand)));
				totalD.setText(String.valueOf(calcTotal(dealerHand)));
				
				// If player hit blackjack, inform the player and add 1.5xbet to balance
				if (calcTotal(playerHand) == 21) {
					winOrLose.setText("Player BlackJack!");
					winOrLose.setVisible(true);
					win.setText(String.valueOf((int) Math.floor(bet * 1.5)));
					player.addChips((int) Math.floor(bet * 1.5));
					currentBalance.setText(String.valueOf(player.getTotalChips()));
					playerHbox.getChildren().clear();
					dealerHbox.getChildren().clear();
					//gameUI.setVisible(false);
					betView.setVisible(true);
					
				}
				
				if (dealerHand.get(0).getType() == "Ace") {
					promptPane.setVisible(true);
					promptLabel.setText("Insurance?");
				}
			}
		}
		
		/**
		 * Calculate the value of a hand
		 * @param hand - the hand to get the total value of
		 * @return - the total value
		 */
		public int calcTotal(ArrayList<Card> hand) {
			// Total variable
			int total = 0;
			// For each card in the hand, add the total value
			for (int i = 0; i < hand.size(); i++) {
				total += hand.get(i).getBJValue();
			}
			// Return the total value
			return total;
		}
		
		 
	    @FXML
	    void chipClicked(ActionEvent e) {
	    	
	    	
	    	if (e.getSource() == chipOne ) {
	    		if ( bet + 1 <= player.getTotalChips()) {
		    		chipOneImg.setEffect(new DropShadow(25,Color.YELLOW));
		    		bet += 1;
		    		betsPlaced.push(1);
		    		totBet.setText(String.valueOf(bet));
		    		}
	    	} else {
	    		chipOneImg.setEffect(new DropShadow());
	    	}
	    	if (e.getSource() == chipFive ) {
	    		if ( bet + 5 <= player.getTotalChips()) {
		    		chipFiveImg.setEffect(new DropShadow(25,Color.YELLOW));
		    		bet += 5;
		    		betsPlaced.push(5);
		    		totBet.setText(String.valueOf(bet));
	    		}
	    	} else {
	    		chipFiveImg.setEffect(new DropShadow());
	    	}
	    	if (e.getSource() == chipTen ) {
	    		if ( bet + 10 <= player.getTotalChips()) {
		    		chipTenImg.setEffect(new DropShadow(25,Color.YELLOW));
		    		bet += 10;
		    		betsPlaced.push(10);
		    		totBet.setText(String.valueOf(bet));
	    		}
	    	} else {
	    		chipTenImg.setEffect(new DropShadow());
	    	}
	    	if (e.getSource() == chipTwentyFive ) {
	    		if ( bet + 25 <= player.getTotalChips()) {
		    		chipTwentyFiveImg.setEffect(new DropShadow(25,Color.YELLOW));
		    		bet += 25;
		    		betsPlaced.push(25);
		    		totBet.setText(String.valueOf(bet));
	    		}
	    	} else {
	    		chipTwentyFiveImg.setEffect(new DropShadow());
	    	}
	    	if (e.getSource() == chipFifty ) {
	    		if ( bet + 50 <= player.getTotalChips()) {
		    		chipFiftyImg.setEffect(new DropShadow(25,Color.YELLOW));
		    		bet += 50;
		    		betsPlaced.push(50);
		    		totBet.setText(String.valueOf(bet));
		    	}
	    	} else {
	    		chipFiftyImg.setEffect(new DropShadow());
	    	}
	    	if (e.getSource() == chipHundred ) {
	    		if ( bet + 100 <= player.getTotalChips()) {
		    		chipHundredImg.setEffect(new DropShadow(25,Color.YELLOW));
		    		bet += 100;
		    		betsPlaced.push(100);
		    		totBet.setText(String.valueOf(bet));
	    		}
	    	} else {
	    		chipHundredImg.setEffect(new DropShadow());
	    	}
	    }
		
	    // undo the last bet
	    @FXML
	    void undoBet(ActionEvent event) {

	    	if(!betsPlaced.isEmpty()) {
	    	bet -= betsPlaced.pop();
	    	totBet.setText(String.valueOf(bet));
	    	} else {
	    		bet = 0;
	    		totBet.setText(String.valueOf(bet));
	    	}
	    }
		/**
		 * Doubles the player's current bet
		 */
		public void doubleDown() {
			bet *= 2;
			totBet.setText(String.valueOf(bet));
			hitLogic();
			if(!hasBust) {
			standLogic();
			}
		}
	
		
		public void placeInsBet() {
			insuranceBet = bet / 2;
			player.removeChips(insuranceBet);
			int newTot = Integer.valueOf(totBet.getText() )+ insuranceBet;
			totBet.setText(String.valueOf(newTot));
		}
		
		/**
		 * Removes half of the user's bet and prompts a new game
		 */
		public void surrender() {
			player.removeChips((int) Math.ceil(bet / 2.0));
			currentBalance.setText(String.valueOf(player.getTotalChips()));
			
			
			
			// clear the cards
			// Reset hands
			playerHand.clear();
			dealerHand.clear();
			playerHbox.getChildren().clear();
			dealerHbox.getChildren().clear();
			
			//reset the deck
			currentDeck.resetDeck();
			
			winOrLose.setText("You Surrendered!");
			winOrLose.setVisible(true);
			//reset the game
			betView.setVisible(true);
		}
		
		/**
		 * If player clicks hit, call hit method
		 */
		public void hit(ActionEvent event) {
			hitLogic();
		}
		
		
		private void hitLogic() {
			// Draw the new card
			Card c = currentDeck.Draw();
			
			// Add the card to their hand
			playerHand.add(c);
			printPlayerCard(c);
			totalP.setText(String.valueOf(calcTotal(playerHand)));		
			// If the hand is a bust, remove the bet from balance
			if (calcTotal(playerHand) > 21) {
				hasBust = true;
				winOrLose.setText("Bust!");
				winOrLose.setVisible(true);
				player.removeChips(bet);
				currentBalance.setText(String.valueOf(player.getTotalChips()));
				win.setText("0");
				
				// clear the player boxes
				playerHand.clear();
				dealerHand.clear();
				playerHbox.getChildren().clear();
				dealerHbox.getChildren().clear();
				
				//reset the deck
				currentDeck.resetDeck();
				
				//reset the game 
				betView.setVisible(true);
				} else {
					hasBust = false;
				}

			if (calcTotal(playerHand) == 21) 
				standLogic();
			}
			
		
		/**
		 * If player clicks stand, determine if the dealer draws or play second hand
		 * Calculate total of hands and determine if the player wins
		 */
		public void stand(ActionEvent event) {
			standLogic();
		}
		
		private void standLogic() {

			
				// Dealer draw until they get at least 17
				while (calcTotal(dealerHand) < 17) {
					dealerHand.add(currentDeck.Draw());
					printDealerCard(dealerHand.get(dealerHand.size() - 1));
				}
				// If the dealer got 21 and the player made an insurance bet
				if (calcTotal(dealerHand) == 21 && insuranceBet > 0) {
					winOrLose.setText("Dealer BlackJack!");
					winOrLose.setVisible(true);
					player.addChips(insuranceBet * 2);
					win.setText(String.valueOf(insuranceBet));
					currentBalance.setText(String.valueOf(player.getTotalChips()));
				}
				// If the dealer busted, inform player and add bet to balance
				else if (calcTotal(dealerHand) > 21) {
					winOrLose.setText("Dealer bust!");
					winOrLose.setVisible(true);
					player.addChips(bet);
					win.setText(String.valueOf(bet));
					currentBalance.setText(String.valueOf(player.getTotalChips()));
				} // If the player's hand is greater than the dealer's
				else if (calcTotal(playerHand) > calcTotal(dealerHand)) {
					// Inform the player and add bet to balance
					winOrLose.setText("You Win!");
					winOrLose.setVisible(true);
					player.addChips(bet);
					win.setText(String.valueOf(bet));
					currentBalance.setText(String.valueOf(player.getTotalChips()));
				} // If the player's hand is less than the dealer's
				else if (calcTotal(playerHand) < calcTotal(dealerHand)) {
					// Inform the player and remove bet from balance
					winOrLose.setText("Dealer Wins!");
					winOrLose.setVisible(true);
					player.removeChips(bet);
					win.setText("0");
					currentBalance.setText(String.valueOf(player.getTotalChips()));
				} // Else, inform the player it's a draw
				else {
					winOrLose.setText("Push!");
					winOrLose.setVisible(true);
					win.setText("0");
				}
			
			
				totalD.setText(String.valueOf(calcTotal(dealerHand)));
				insuranceBet = 0;
				// clear the player boxes
				playerHand.clear();
				dealerHand.clear();
				playerHbox.getChildren().clear();
				dealerHbox.getChildren().clear();
				
				//reset the deck
				currentDeck.resetDeck();
				// Prompt for a new game
				betView.setVisible(true);
				
			}
			
	    
	    /**
	     * Prints out the player's hand
	     * @param c - the new card to print
	     */
	    void printPlayerCard(Card c) {
	    	
	    	// make a player card and add it to the Hbox
	    	Label cardIMG = new Label(c.getCard());
	    	cardIMG.setPrefSize(120, 100);
	    	cardIMG.setStyle("-fx-background-color: white; -fx-text-fill: Red;");
	    	playerHbox.getChildren().add(cardIMG);
		   	
		    
	    }
	    
	    /**
	     * Prints out the dealer's hand
	     * @param c - the new card to print
	     */
	    void printDealerCard(Card c) {
	    	// make a player card and add it to the Hbox
	    	Label cardIMG = new Label(c.getCard());
	    	cardIMG.setPrefSize(120, 100);
	    	cardIMG.setStyle("-fx-background-color: white; -fx-text-fill: Red;");
	    	dealerHbox.getChildren().add(cardIMG);
		   	
	    }
	    
	    @FXML
	    /**
	     * If the player confirms a prompt
	     * Check the prompt and call the respective method 
	     */
	    void confirmPrompt(ActionEvent event) {
	    	placeInsBet();
	    	promptPane.setVisible(false);
	    	
	    	dealerHand.add(currentDeck.Draw());
			
	    	if (calcTotal(dealerHand) == 21) {
	    		printDealerCard(dealerHand.get(1));
	    		totalD.setText(String.valueOf(calcTotal(dealerHand)));
	    		standLogic();
	    	}
	    }
	    
	    @FXML
	    /**
	     * If the player cancels a prompt, hide the prompt
	     */
	    void cancelPrompt(ActionEvent event) {
	    	promptPane.setVisible(false);
	    	
	    }
	    
	   // method to return to menu 
	  	public void exitGame() {
	  		
	  	
	  		
	  		//change back the scene
	  		Stage stage = (Stage) totBet.getScene().getWindow();
	  		stage.centerOnScreen();
	  		stage.setTitle("Casino Floor");
	  		stage.setScene(sc);
	  	}
	  	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
			// set background image
			backgroundImage.setImage(bg);
			// set the images of the chips
	    	chipOneImg.setImage(new Image("roulette/Assets/PokerChip1.png"));
	    	chipFiveImg.setImage(new Image("roulette/Assets/PokerChip5.png"));
	    	chipTenImg.setImage(new Image("roulette/Assets/PokerChip10.png"));
	    	chipTwentyFiveImg.setImage(new Image("roulette/Assets/PokerChip25.png"));
	    	chipFiftyImg.setImage(new Image("roulette/Assets/PokerChip50.png"));
	    	chipHundredImg.setImage(new Image("roulette/Assets/PokerChip100.png"));
	    	exit.setImage(new Image("roulette/Assets/exit.png"));
			
			currentBalance.setText(String.valueOf(player.getTotalChips()));
			totBet.setText("0");
			bet = 0;
			win.setText("0");
		}
    
    
    
}