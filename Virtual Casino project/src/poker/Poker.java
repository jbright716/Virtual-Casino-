package poker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Menus.ChipProfile;
import tableAccessories.Card;
import tableAccessories.Deck;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Poker implements Initializable{
	
	// Poker -----------------------------------------------------------------
	private Deck pDeck = new Deck();
	private Card[] pCards = new Card[5];
	private Card[] dCards = new Card[5];
	private TextField[] dH = new TextField[5];
	private TextField[] pH = new TextField[5];
	private GridPane pDHand = new GridPane();
	private GridPane pHand = new GridPane();
	private TextField pInstructions = new TextField("");
	

	
	private GridPane pokerChoices = new GridPane();
	private Button pStartb = new Button("START");
	private Button pFold = new Button("FOLD");
	private Button pBet = new Button("BET");
	private TextField pBetNum = new TextField();
	private Button pLeave = new Button("LEAVE");
	
	int pSelect;
	boolean resumeState = false;
	boolean pStart = false;
	boolean pOver = false;
	int pRound = 0;
	int pBetAmount = 0;
	private static ChipProfile player;
	private static Scene sc;
	
	@FXML
    private StackPane loadingPane;
	
	// Pass in the scene for the main menu
		public static void setScene(Scene scene) {
			sc = scene;
		}
		// pass in the player for game
		public static void setPlayer(ChipProfile currentPlayer) {
			// TODO Auto-generated method stub
			player = currentPlayer;
		}
	
public void setup() {
	for (int i = 0; i < 5; i++) {
		pCards[i] = pDeck.Draw();
		dCards[i] = pDeck.Draw();
		pH[i] = new TextField(pCards[i].getCard());
		dH[i]= new TextField(dCards[i].getCard());
		pH[i].setPrefSize(150, 150);
		dH[i].setPrefSize(150, 150);
		pHand.add(pH[i], i, 0);
		pDHand.add(dH[i], i, 0);
	}
	
	BorderPane pokerPane = new BorderPane();
	pokerPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #cf9e55, #661a33)");
	
	
	pokerChoices.add(pStartb, 0, 0);
	pokerChoices.add(pBet, 2, 0);
	pokerChoices.add(pBetNum, 3, 0);
	pokerChoices.add(pFold, 4, 0);
	pokerChoices.add(pLeave, 5, 0);
	
	pokerPane.setTop(pDHand);
	pokerPane.setCenter(pokerChoices);
	pokerPane.setRight(pInstructions);
	pokerPane.setBottom(pHand);
	
	List<Button> pButtons = new ArrayList<Button>();
	pButtons.add(pStartb);
	pButtons.add(pBet);
	pButtons.add(pFold);
	pButtons.add(pLeave);
	
	List<TextField> pTextFields = new ArrayList<TextField>();
	pTextFields.add(pBetNum);
	pTextFields.add(pInstructions);
	for (int i = 0; i < 5; i++) {
		pTextFields.add(pH[i]);
	}
	for (int i = 0; i < 5; i++) {
		pTextFields.add(dH[i]);
	}
	for (int i = 0; i < pTextFields.size(); i++) {
		pTextFields.get(i).setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: orange;");
		pTextFields.get(i).setMinSize(100, 100);
		pTextFields.get(i).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		if (i >= 2 && i <= 6) {
			pHand.setHgrow(pTextFields.get(i), Priority.ALWAYS);
			pHand.setVgrow(pTextFields.get(i), Priority.ALWAYS);
			pTextFields.get(i).setEditable(false);
			pTextFields.get(i).setVisible(false);
		}
		if (i >= 7 && i < 12) {
			pHand.setHgrow(pTextFields.get(i), Priority.ALWAYS);
			pHand.setVgrow(pTextFields.get(i), Priority.ALWAYS);
			pTextFields.get(i).setEditable(false);
			pTextFields.get(i).setVisible(false);
		}
		pTextFields.get(i).setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 12));
	}
	pInstructions.setMinSize(500, 100);
	
	for (int i = 0; i < pButtons.size(); i++) {
		pButtons.get(i).setStyle("-fx-background-color: black; -fx-text-fill: white;");
		pButtons.get(i).setMinSize(100, 100);
		pButtons.get(i).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		pButtons.get(i).setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 12));
		pokerChoices.setHgrow(pButtons.get(i), Priority.ALWAYS);
		pokerChoices.setVgrow(pButtons.get(i), Priority.ALWAYS);
	}
	
	for (Button b : pButtons) {
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				b.setStyle(b.getStyle() + "-fx-border-color: yellow;");
			}
		});
		b.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				b.setStyle(b.getStyle().replace("-fx-border-color: yellow;", ""));
			}
		});
	}
	
	
	
	pStartb.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if (e.getSource() instanceof Button) {
				
				if (pStart == false) {
					if (player.getTotalChips() <= 50) {
						pInstructions.setText("You do not have enough chips to ante up CHIP AMOUNT: " + player.getTotalChips());
						return;
					}
					pStart = true;
					pRound = 1;
					pH[0].setVisible(true);
					pH[1].setVisible(true);
					dH[1].setVisible(true);
					pBetAmount += 50; 
					pInstructions.setText("Choose to bet (enter amount in text box) or to fold CHIP AMOUNT: " + (player.getTotalChips() - pBetAmount));
				}
				else if (pOver == true){
					pRound = 0;
					pStart = false;
					pHand.getChildren().clear();
					pDHand.getChildren().clear();
					pDeck.resetDeck();
					for (int i = 0; i < 5; i++) {
						pCards[i] = pDeck.Draw();
						dCards[i] = pDeck.Draw();
						pH[i] = new TextField(pCards[i].getCard());
						dH[i]= new TextField(dCards[i].getCard());
						pH[i].setPrefSize(150, 150);
						dH[i].setPrefSize(150, 150);
						pHand.add(pH[i], i, 0);
						pDHand.add(dH[i], i, 0);
					}
					for (int i = 0; i < 5; i++) {
						pH[i].setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: orange;");
						pH[i].setMinSize(100, 100);
						pH[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
						pHand.setHgrow(pH[i], Priority.ALWAYS);
						pHand.setVgrow(pH[i], Priority.ALWAYS);
						pH[i].setEditable(false);
						pH[i].setVisible(false);
						pH[i].setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 12));
						
						dH[i].setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: orange;");
						dH[i].setMinSize(100, 100);
						dH[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
						pDHand.setHgrow(dH[i], Priority.ALWAYS);
						pDHand.setVgrow(dH[i], Priority.ALWAYS);
						dH[i].setEditable(false);
						dH[i].setVisible(false);
						dH[i].setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					}
					pBetAmount = 0;
					pOver = false;
					pInstructions.setText("Press \"START\" to start the game, the ante is 50 chips CHIP AMOUNT: " + (player.getTotalChips()));
				}
				
			}
		}
	});
	
	pBet.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if (e.getSource() instanceof Button) {
				if (pStart == false || pOver == true) {
					return;
				}
				else {
					if (pBetNum.getText().equals(null) || pBetNum.getText().equals("")) {
						pInstructions.setText("Please enter an amount of chips to bet CHIP AMOUNT: " + (player.getTotalChips() - pBetAmount));
						return;
					}
					if (Integer.valueOf(pBetNum.getText()) > player.getTotalChips() - pBetAmount) {
						pInstructions.setText("You do not have enough chips to make that bet CHIP AMOUNT: " + (player.getTotalChips() - pBetAmount));
						return;
					}
					else{
						pBetAmount += Integer.valueOf(pBetNum.getText());
						pInstructions.setText("Choose to bet (enter amount in text box) or to fold CHIP AMOUNT: " + (player.getTotalChips() - pBetAmount));
						pRound ++;
						switch (pRound) {
							case 2: pH[2].setVisible(true);
									dH[2].setVisible(true);
									break;
							case 3: pH[3].setVisible(true);
									dH[3].setVisible(true);
									break;
							case 4: dH[0].setVisible(true);
									dH[4].setVisible(true);
									pH[4].setVisible(true);
									int pWin = pCompareCards(pCards, dCards);
									if (pWin == 1) {
										player.addChips(pBetAmount);
										pInstructions.setText("You won! Press \"START\" to continue");
										pOver = true;
									}
									else if (pWin == -1) {
										player.removeChips(pBetAmount);
										pInstructions.setText("You lost, press \"START\" to continue");
										pOver = true;
									}
									else pInstructions.setText("You tied, press \"START\" to continue"); pOver = true;
						}
					}
				}

			}
		}
	});
	
	pLeave.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if (pStart == true) {
				pInstructions.setText("You cannot leave while a game is taking place.");
			}
			else {
				exitGame();
			}
		}
	});
	
	pFold.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if (pStart == false || pOver == true) {
				return;
			}
			else {
				player.removeChips(pBetAmount);
				pHand.getChildren().clear();
				pDHand.getChildren().clear();
				pDeck.resetDeck();
				pRound = 0;
				pStart = false;
				for (int i = 0; i < 5; i++) {
					pCards[i] = pDeck.Draw();
					dCards[i] = pDeck.Draw();
					pH[i] = new TextField(pCards[i].getCard());
					dH[i]= new TextField(dCards[i].getCard());
					pH[i].setPrefSize(150, 150);
					dH[i].setPrefSize(150, 150);
					pHand.add(pH[i], i, 0);
					pDHand.add(dH[i], i, 0);
				}
				for (int i = 0; i < 5; i++) {
					pH[i].setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: orange;");
					pH[i].setMinSize(100, 100);
					pH[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					pHand.setHgrow(pH[i], Priority.ALWAYS);
					pHand.setVgrow(pH[i], Priority.ALWAYS);
					pH[i].setEditable(false);
					pH[i].setVisible(false);
					pH[i].setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					
					dH[i].setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: orange;");
					dH[i].setMinSize(100, 100);
					dH[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
					pDHand.setHgrow(dH[i], Priority.ALWAYS);
					pDHand.setVgrow(dH[i], Priority.ALWAYS);
					dH[i].setEditable(false);
					dH[i].setVisible(false);
					dH[i].setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 12));
				}
				pBetAmount = 0;
				pInstructions.setText("Press \"START\" to start the game, the ante is 50 chips CHIP AMOUNT: " + (player.getTotalChips()));
			}
		}
	});
	
	 loadingPane.getChildren().add(pokerPane);
}


	private int pCompareCards(Card[] pCards, Card[] dCards) {
		
		int dHValue = 0;
		int pHValue = 0;
		int pHighCard = pCards[0].getValue();
		int dHighCard = dCards[0].getValue();
		int pMatchValue = 0;
		int dMatchValue = 0;
		
		for (int i = 0; i < 5; i++) {
			if (pCards[i].getValue() == 1) {
				pHighCard = 1000;
				break;
			}
			if (pCards[i].getValue() > pHighCard) {
				pHighCard = pCards[i].getValue();
			}
		}
		
		for (int i = 0, x = 0, z = 0; i < 5; i++) {
			for (int j = i + 1; j < 5; j++) {
				if (pCards[i].getValue() == pCards[j].getValue()) {
					x++;
					if (x == 6) {
						if (z == 4) {
							pMatchValue = pCards[i].getValue();
							z++;
						}
						pHValue = 8;
					}
					if (x == 4) {
						if (z == 3) {
							pMatchValue = pCards[i].getValue();
							z++;
						}
						pHValue = 7;
					}
					if (x == 3) {
						if (z == 2) {
							pMatchValue = pCards[i].getValue();
							z++;
						}
						pHValue = 4;
					}
					if (x == 2) {
						if (z == 1) {
							pMatchValue = pCards[i].getValue();
							z++;
						}
	
						pHValue = 3;
					}
					if (x == 1) {
						if (z == 0) {
							pMatchValue = pCards[i].getValue();
							z++;
						}
						pHValue = 2;
					}
				}
			}
		}
		
		for (int i = 0, z = 0; i < 5; i++) {
			for (int j = 0, y = 0; j < 5; j++) {
				if (pCards[i].getValue() + 1 == pCards[j].getValue() || pCards[i].getValue() - 1 == pCards[j].getValue()) {
					y++;
				}
				if (y == 8) {
					if (z == 0) {
						pMatchValue = pCards[i].getValue();
						z++;
					}
					pHValue = 5;
				}
			}
		}
		
		if (pCards[0].getSuit().equals(pCards[1].getSuit()) && pCards[1].getSuit().equals(pCards[2].getSuit())
				&& pCards[2].getSuit().equals(pCards[3].getSuit()) && pCards[3].getSuit().equals(pCards[4].getSuit())) {
			pHValue = 6;
			for (int i = 0, x = 0; i < 5; i++) {
				if (pCards[i].getType().equals("Ace")) {
					x++;
				} 
				else if (pCards[i].getType().equals("King")) {
					x++;
				} 
				else if (pCards[i].getType().equals("Queen")) {
					x++;
				} 
				else if (pCards[i].getType().equals("Jack")) {
					x++;
				} 
				else if (pCards[i].getType().equals("Number") && pCards[i].getValue() == 10) {
					x++;
				}
				if (x == 5) {
					pHValue = 10;
				}
				if (i == 4 && x != 5) {
					for (int j = 0, y = 0, highV = pCards[j].getValue(); j < 5; j++) {
						if (pCards[i].getValue() + 1 == pCards[j].getValue() || pCards[i].getValue() - 1 == pCards[j].getValue()) {
							y++;
						}
						if (pCards[j].getValue() > highV) {
							highV = pCards[j].getValue();
						}
						if (y == 8) {
							pHValue = 9;
							pMatchValue = highV;
						}
					}
				}
			}
		}
		
		// check dealer hand value
		for (int i = 0; i < 5; i++) {
			if (dCards[i].getValue() == 1) {
				dHighCard = 1000;
				break;
			}
			if (dCards[i].getValue() > dHighCard) {
				dHighCard = dCards[i].getValue();
			}
		}
		
		for (int i = 0, x = 0, z = 0; i < 5; i++) {
			for (int j = i + 1; j < 5; j++) {
				if (dCards[i].getValue() == dCards[j].getValue()) {
					x++;
					if (x == 6) {
						if (z == 5) {
							dMatchValue = dCards[i].getValue();
							z++;
						}
						dHValue = 8;
					}
					if (x == 4) {
						if (z == 3) {
							pMatchValue = dCards[i].getValue();
							z++;
						}
						dHValue = 7;
					}
					if (x == 3) {
						if (z == 2) {
							dMatchValue = dCards[i].getValue();
							z++;
						}
						dHValue = 4;
					}
					if (x == 2) {
						if (z == 1) {
							dMatchValue = dCards[i].getValue();
							z++;
						}
						dHValue = 3;
					}
					if (x == 1) {
						if (z == 0) {
							dMatchValue = dCards[i].getValue();
							z++;
						}
						dHValue = 2;
					}
				}
			}
		}
		for (int i = 0, z = 0; i < 5; i++) {
			for (int j = 0, y = 0; j < 5; j++) {
				if (dCards[i].getValue() + 1 == dCards[j].getValue() || dCards[i].getValue() - 1 == dCards[j].getValue()) {
					y++;
				}
				if (y == 8) {
					if (z == 0) {
						pMatchValue = dCards[i].getValue();
						z++;
					}
					dHValue = 5;
				}
			}
		}
		
		if (dCards[0].getSuit().equals(dCards[1].getSuit()) && dCards[1].getSuit().equals(dCards[2].getSuit())
				&& dCards[2].getSuit().equals(dCards[3].getSuit()) && dCards[3].getSuit().equals(dCards[4].getSuit())) {
			dHValue = 6;
			for (int i = 0, x = 0; i < 5; i++) {
				if (dCards[i].getType().equals("Ace")) {
					x++;
				} 
				else if (dCards[i].getType().equals("King")) {
					x++;
				} 
				else if (dCards[i].getType().equals("Queen")) {
					x++;
				} 
				else if (dCards[i].getType().equals("Jack")) {
					x++;
				} 
				else if (dCards[i].getType().equals("Number") && dCards[i].getValue() == 10) {
					x++;
				}
				if (x == 5) {
					dHValue = 10;
				}
				if (i == 4 && x != 5) {
					for (int j = 0, y = 0, highV = dCards[j].getValue(); j < 5; j++) {
						if (dCards[i].getValue() + 1 == dCards[j].getValue() || dCards[i].getValue() - 1 == dCards[j].getValue()) {
							y++;
						}
						if (dCards[j].getValue() > highV) {
							highV = dCards[j].getValue();
						}
						if (y == 8) {
							dHValue = 9;
							pMatchValue = highV;
						}
					}
				}
			}
		}
		if (pHValue == 0) {
			pMatchValue = 0;
		}
		if (dHValue == 0) {
			dMatchValue = 0;
		}
		if (pHValue == dHValue) {
			if (pMatchValue > dMatchValue) {
				return 1;
			}
			else if (dMatchValue > pMatchValue) {
				return -1;
			}
			if (pHighCard == dHighCard) {
				return 0;
			}
			else if (pHighCard < dHighCard) {
				return -1;
			}
			else return 1;
		}
		else if (pHValue > dHValue) {
			return 1;
		}
		else return -1;
		
	
	}
	
	// method to return to menu 
  	public void exitGame() {
  		// update the profiles balance 
  	
  		
  		//change back the scene
  		Stage stage = (Stage) loadingPane.getScene().getWindow();
  		stage.centerOnScreen();
  		stage.setTitle("Casino Floor");
  		stage.setScene(sc);
  	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setup();
		pInstructions.setText("Press \"START\" to start the game, the ante is 50 chips CHIP AMOUNT: " + player.getTotalChips());
	}
}
