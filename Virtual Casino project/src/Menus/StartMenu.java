package Menus;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import blackJack.blackJack;
import blazingCherrySlot.blazingCherryController;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import poker.Poker;
import roulette.Roulette;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;

public class StartMenu extends Application{
	int pSelect;
	boolean resumeState = false;
	
	public StartMenu(){

		// Stage for everything
		Stage stage = new Stage();
		
		
		
		// Array filled with empty profiles.
		final ChipProfile[] cpArray = new ChipProfile[3];
		cpArray[0] = new ChipProfile(1, "Profile 1");
		cpArray[1] = new ChipProfile(2, "Profile 2");
		cpArray[2] = new ChipProfile(3, "Profile 3");
		
		// *******************PANES/MENUS***********************
		// Opening menu.
		GridPane menu = new GridPane();
		menu.setVgap(50);
		menu.setStyle("-fx-background-color: black;");
		
		// Menu for presenting profiles.
		GridPane profileMenu = new GridPane();
		profileMenu.setVgap(50);
		profileMenu.setStyle("-fx-background-color: black;");
		
		// New profile creation menu.
		GridPane newProfilePane = new GridPane();
		
		// Override confirmation menu.
		VBox overrideMenu = new VBox();
		overrideMenu.setPrefSize(1700, 100);

		// *******************TEXTFIELDS***********************
		// Title for opening menu.
		TextField title = new TextField("VIRTUAL CASINO");
		title.setEditable(false);
		title.setMouseTransparent( true );
		title.setFocusTraversable( false );
		title.setAlignment(Pos.CENTER);
		title.setMaxWidth(Double.MAX_VALUE);
		menu.setHgrow(title, Priority.ALWAYS);
		title.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 50));
		title.setStyle("-fx-control-inner-background: black; -fx-text-box-border: yellow; -fx-focus-color: yellow; -fx-background-insets: 0, 10;");
		
		// Title for profile menu.
		TextField pTitle = new TextField("Profiles");
		pTitle.setEditable(false);
		pTitle.setMouseTransparent( true );
		pTitle.setFocusTraversable( false );
		pTitle.setAlignment(Pos.CENTER);
		pTitle.setMaxWidth(Double.MAX_VALUE);
		menu.setHgrow(pTitle, Priority.ALWAYS);
		pTitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 45));
		pTitle.setStyle("-fx-control-inner-background: black;");
		
		// Title for override menu.
		TextField overrideText = new TextField("This profile is already in use. Do you want to overwrite the data currently stored here to make a new profile?");
		overrideText.setEditable(false);
		overrideText.setMouseTransparent(true);
		overrideText.setFocusTraversable(false);
		overrideText.setAlignment(Pos.CENTER);
		overrideText.setMaxWidth(Double.MAX_VALUE);
		overrideMenu.setVgrow(overrideText, Priority.ALWAYS);
		overrideText.setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		overrideText.setStyle("-fx-control-inner-background: black;");
		
		// Text field asking user to enter profile name.
		TextField pNameText = new TextField("Please enter profile name:");
		pNameText.setEditable(false);
		pNameText.setMouseTransparent(true);
		pNameText.setFocusTraversable(false);
		pNameText.setMaxWidth(Double.MAX_VALUE);
		newProfilePane.setHgrow(pNameText, Priority.ALWAYS);
		pNameText.setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		pNameText.setStyle("-fx-control-inner-background: black; -fx-text-box-border: yellow;");
		
		// Text field for entering profile name.
		TextField pNameEnter = new TextField();
		
		// Text field for asking user to choose profile starting chip balance.
		TextField pChipStart = new TextField("Please select starting chip balance:");
		pChipStart.setEditable(false);
		pChipStart.setMouseTransparent(true);
		pChipStart.setFocusTraversable(false);
		pChipStart.setMaxWidth(Double.MAX_VALUE);
		newProfilePane.setHgrow(pChipStart, Priority.ALWAYS);
		pChipStart.setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		pChipStart.setStyle("-fx-control-inner-background: black; -fx-text-box-border: yellow;");
		
		// TextField for entering number of custom chips.
		TextField cChipsText = new TextField();
		
		// *******************BUTTONS***********************
		// New game button of opening menu.
		Button newGame = new Button("New Game");
		
		// Resume game button of opening menu.
		Button resumeGame = new Button("Continue");
		
		// Exit game button of opening menu.
		Button exitGame = new Button("Exit Game");
		
		// Profile1 button of profile menu.
		Button pButton1 = new Button("Profile 1");

		// Profile2 button of profile menu.
		Button pButton2 = new Button("Profile 2");

		// Profile3 button of profile menu.
		Button pButton3 = new Button("Profile 3");

		// Back button for profile menu.
		Button pBack = new Button("Back");

		// Yes button for overriding profile.
		Button overYes = new Button("YES");

		// No button for overriding profile.
		Button overNo = new Button("NO");
		
		// Buttons for selecting amount of chips while making new profile.
		Button[] chipAmounts = new Button[5];
		chipAmounts[0] = new Button("100 Chips");
		chipAmounts[1] = new Button("500 Chips");
		chipAmounts[2] = new Button("1000 Chips");
		chipAmounts[3] = new Button("Unlimited Chips");
		chipAmounts[4] = new Button("Custom Amount");
		
		// The create profile button.
		Button createProfile = new Button("Create Profile");

		// Back button in the making new profile screen.
		Button nPBack = new Button("Back");
		
		// Buttons in a list for eventHandlers and formatting.
		List<Button> smButtons = new ArrayList<Button>();
		smButtons.add(newGame); //0
		smButtons.add(resumeGame); //1
		smButtons.add(exitGame); //2
		smButtons.add(pButton1); //3
		smButtons.add(pButton2); //4
		smButtons.add(pButton3); //5
		smButtons.add(pBack); //6
		smButtons.add(overYes); //7
		smButtons.add(overNo); //8
		for (int i = 0; i < chipAmounts.length; i++) {
			smButtons.add(chipAmounts[i]); //9 10 11 12 13
		}
		smButtons.add(createProfile);//14
		smButtons.add(nPBack);//15
		
		// *******************ADD NODES TO PANES***********************
		// Add nodes to the opening menu.
		menu.add(title, 0, 0);
		menu.add(newGame, 0, 1);
		menu.add(resumeGame, 0, 2);
		menu.add(exitGame, 0, 3);
		menu.setPrefSize(1000,1000);
		
		// Add nodes to override menu.
		overrideMenu.getChildren().add(overrideText);
		overrideMenu.getChildren().add(overYes);
		overrideMenu.getChildren().add(overNo);
		
		// Add nodes to the profile menu.
		profileMenu.add(pTitle, 0, 0);
		profileMenu.add(pButton1, 0, 1);
		profileMenu.add(pButton2, 0, 2);
		profileMenu.add(pButton3, 0, 3);
		profileMenu.add(pBack, 0, 4);
		profileMenu.setPrefSize(1000,1000);
		
		// Add nodes to the new profile menu.
		newProfilePane.add(pNameText, 0, 0);
		newProfilePane.add(pNameEnter, 0, 1);
		newProfilePane.add(pChipStart, 0, 2);
		for (int i = 3; i < 3 + chipAmounts.length; i++) {
			newProfilePane.add(chipAmounts[i-3], 0, i);
		}
		newProfilePane.add(cChipsText, 0, 9);
		newProfilePane.add(createProfile, 0, 10);
		newProfilePane.add(nPBack, 0, 11);
		
		// *******************SCENES***********************
		// Scene for opening menu.
		Scene startMenu = new Scene(menu);
		stage.setScene(startMenu);
		stage.centerOnScreen();
		stage.setTitle("Virtual Casino Main Menu");
		
		// Scene for override menu.
		Scene oMenu = new Scene(overrideMenu);
		
		// Scene for profile menu.
		Scene pMenu = new Scene(profileMenu);
		
		// Scene for the new profile menu
		Scene newProfileScene = new Scene(newProfilePane);
		
		
		
		// ----------------------------GAME OVER---------------------------
		BorderPane gameOverPane = new BorderPane();
		Button gameOverButton = new Button("OK");
		TextField gameOverText = new TextField("no more chips... GAME OVER");
		
		gameOverText.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 100));
		gameOverText.setEditable(false);
		gameOverText.setMouseTransparent( true );
		gameOverText.setFocusTraversable( false );
		gameOverText.setMaxWidth(Double.MAX_VALUE);
		gameOverText.setMaxHeight(Double.MAX_VALUE);
		gameOverText.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-focus-color: yellow; -fx-text-alignment: center;");
		
		gameOverButton.setAlignment(Pos.CENTER);
		gameOverButton.setMaxWidth(Double.MAX_VALUE);
		gameOverButton.setMaxHeight(Double.MAX_VALUE);
		gameOverButton.setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 30));
		gameOverButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-focus-color: yellow;");
		gameOverButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				gameOverButton.setStyle(gameOverButton.getStyle() + "-fx-border-color: yellow;");
			}
		});
		gameOverButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				gameOverButton.setStyle(gameOverButton.getStyle().replace("-fx-border-color: yellow;", ""));
			}
		});
		
		gameOverPane.setCenter(gameOverText);
		gameOverPane.setBottom(gameOverButton);
		gameOverPane.setPrefSize(1700, 300);
		
		Scene gameOver = new Scene(gameOverPane);
		
		gameOverButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					cpArray[pSelect] = new ChipProfile(pSelect + 1, "Profile " + (pSelect + 1));
					switch (pSelect + 1) {
					case 1:
						pButton1.setText("Profile 1");
						break;
					case 2:
						pButton2.setText("Profile 2");
						break;
					case 3:
						pButton3.setText("Profile 3");
						break;
					}
					stage.setScene(startMenu);
					stage.centerOnScreen();
					stage.setTitle("Virtual Casino Main Menu");
				}
			}
		});
		
		
		//CASINO FLOOR
		
		GridPane casinoFloor = new GridPane();
		Button rouletteButton = new Button("American Roulette");
		Button slotsButton = new Button("Blazin' Cherry");
		Button blackJackButton = new Button("Black Jack");
		Button pokerButton = new Button("5-Stud Poker");
		Button cashOutButton = new Button("Cash Out");
		
		List<Button> cfButtons = new ArrayList<Button>();
		cfButtons.add(rouletteButton);
		cfButtons.add(slotsButton);
		cfButtons.add(blackJackButton);
		cfButtons.add(pokerButton);
		cfButtons.add(cashOutButton);
		
		casinoFloor.add(rouletteButton, 0, 0);
		casinoFloor.add(slotsButton, 0, 1);
		casinoFloor.add(blackJackButton, 0, 2);
		casinoFloor.add(pokerButton, 0, 3);
		casinoFloor.add(cashOutButton, 0, 4);
		casinoFloor.setPrefSize(1000, 700);
		casinoFloor.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #cf9e55, #661a33)");
		
		Scene cFloor = new Scene(casinoFloor);
		
	
		
		for (int i = 0; i < cfButtons.size(); i++) {
			cfButtons.get(i).setAlignment(Pos.CENTER);
			cfButtons.get(i).setMaxWidth(Double.MAX_VALUE);
			casinoFloor.setHgrow(cfButtons.get(i), Priority.ALWAYS);
			casinoFloor.setVgrow(cfButtons.get(i), Priority.ALWAYS);
			cfButtons.get(i).setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 30));
			cfButtons.get(i).setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-focus-color: yellow;");
		}	
		
		
		for (Button b : cfButtons) {
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
		
		
		newGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					stage.setScene(pMenu);
					stage.centerOnScreen();
					stage.setTitle("Virtual Casino Profile Menu");
				}
			}
		});
		
		// Event for resume game button.
		resumeGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					resumeState = true;
					stage.setScene(pMenu);
					stage.centerOnScreen();
					stage.setTitle("Virtual Casino Profile Menu");
				}
			}
		});
		
		// Handling buttons for both creating new and entering existing profiles.
		for (int i = 3; i < 6; i++) {
			smButtons.get(i).setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if (e.getSource() instanceof Button) {
						if (resumeState == false) {
							String profileName;
							int profileInt = -1;
							profileName = ((Button) e.getSource()).getText();
							for (int i = 0; i < cpArray.length; i++) {
								if (cpArray[i].getProfileName().equals(profileName)) {
									profileInt = cpArray[i].getProfileInt();
								}
							}
							if (cpArray[profileInt - 1].getProfileActive()) {
								pSelect = profileInt - 1;
								stage.setScene(oMenu);
								stage.centerOnScreen();
								stage.setTitle("Overwrite Data");
							} else if (cpArray[profileInt - 1].getProfileActive() == false) {
								pSelect = profileInt - 1;
								stage.setScene(newProfileScene);
								stage.centerOnScreen();
								stage.setTitle("New Profile");
							}
						}
						else if (resumeState == true) {
							for (int i = 0; i < cpArray.length; i++) {
								if (cpArray[i].getProfileName().equals(((Button) e.getSource()).getText()) && cpArray[i].getProfileActive()) {
									pSelect = cpArray[i].getProfileInt() - 1;
									stage.setScene(cFloor);
									stage.centerOnScreen();
									stage.setTitle("Casino Floor");
									resumeState = false;
								}
							}
						}
					}
				}
			});
		}
		
		// Event for override yes button.
		overYes.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					stage.setScene(newProfileScene);
					stage.centerOnScreen();
					stage.setTitle("New Profile");
				}
			}
		});
		
		// Event for override no button.
		overNo.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					stage.setScene(pMenu);
					stage.centerOnScreen();
					stage.setTitle("Virtual Casino Profile Menu");
				}
			}
		});
		
		// Handling selecting chip amounts while creating a new profile.
		for (int i = 0; i < chipAmounts.length; i++) {
			chipAmounts[i].setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if (e.getSource() instanceof Button) {
						((Node) e.getSource()).setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-focus-color: yellow;");
					}
					for (int i = 0; i < chipAmounts.length; i++) {
						if (chipAmounts[i].getText().equals(((Labeled) e.getSource()).getText())) {
							continue;
						}
						else chipAmounts[i].setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-focus-color: yellow;");
					}
				}
			});
		}

		// Handling back button from profile menu to main menu.
		pBack.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					resumeState = false;
					stage.setScene(startMenu);
					stage.centerOnScreen();
					stage.setTitle("Virtual Casino Main Menu");
				}
			}
		});
		
		// Handling back button from creating a new profile to the new game profile menu.
		nPBack.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					resumeState = false;
					stage.setScene(pMenu);
					stage.centerOnScreen();
					stage.setTitle("Virtual Casino Profile Menu");
					pNameEnter.setText("");
					for (int j = 9; j < 16; j++) {
						smButtons.get(j).setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-focus-color: yellow;");
					}
					cChipsText.setText("");		
				}
			}
		});

		// Handling the profile creation for the create profile button in the creating new profile menu.
		createProfile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					int chipNum = -1;
					String pName = null;
					for (int i = 0; i < chipAmounts.length; i++) {
						if(chipAmounts[i].getStyle().contains("-fx-background-color: white; -fx-text-fill: black;")) {
							switch (chipAmounts[i].getText()) {
							case "100 Chips":
								chipNum = 100;
								break;
							case "500 Chips":
								chipNum = 500;
								break;
							case "1000 Chips":
								chipNum = 1000;
								break;
							case "Unlimited Chips":
								chipNum = -9999;
								break;
							case "Custom Amount":
								chipNum = Integer.valueOf(cChipsText.getText());
								if (chipNum <= 0) {
									chipNum = 1;
									break;
								}
								break;
							default:
								break;
							}
							if (pNameEnter.getText().equals(null) || pNameEnter.getText().equals("")) {
								pName = "No Name McGee";
							}
							else pName = pNameEnter.getText();
							switch (pSelect + 1) {
							case 1:
								pButton1.setText(pName);
								break;
							case 2:
								pButton2.setText(pName);
								break;
							case 3:
								pButton3.setText(pName);
								break;
							}
							cpArray[pSelect] = new ChipProfile(pName, chipNum, pSelect + 1);
							pNameEnter.setText("");
							for (int j = 9; j < 16; j++) {
								smButtons.get(j).setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-focus-color: yellow;");
							}
							cChipsText.setText("");	
							stage.setScene(cFloor);
							stage.centerOnScreen();
							stage.setTitle("Casino Floor");
						}
						else continue;
					}
				}
			}
		});
		
		// Handling the exit game button in the main menu
		exitGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					stage.close();
				}
			}
		});
		
		cashOutButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					stage.setScene(startMenu);
					stage.centerOnScreen();
					stage.setTitle("Virtual Casino Main Menu");
				}
			}
		});
		// pass in the scene and the chip profile 
		Roulette.setScene(cFloor);
		
		rouletteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					// pass the plater to the game
					Roulette.setPlayer(cpArray[pSelect]);
					
					try {
						stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/roulette/RoulleteBoard.fxml")),1000,500));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//stage.setScene(rou.startGame(cpArray[pSelect]));
					stage.centerOnScreen();
					stage.setTitle("American Roullete");
					
					
				}
			}
		});
		
		// pass in the scene and the chip profile 
		blazingCherryController.setScene(cFloor);
		slotsButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					// pass the player to the game
					blazingCherryController.setPlayer(cpArray[pSelect]);
					try {
						stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/blazingCherrySlot/blazinCherry.fxml")),1000,500));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
										stage.centerOnScreen();
					stage.setTitle("Blazin' Cherry");
				}
			}
			
		});
		
		
		// pass in the scene and the chip profile 
		blackJack.setScene(cFloor);
		blackJackButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					// pass the player to the game
					blackJack.setPlayer(cpArray[pSelect]);
					try {
						stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/blackJack/BlackJack.fxml")),1000,500));
					} catch (IOException e1) {
						// TODO Auto-generated catch block							
						e1.printStackTrace();
					}
					stage.centerOnScreen();
					stage.setTitle("Black Jack");
				}
			}
		});
		// pass in the scene and the chip profile 
		Poker.setScene(cFloor);
		pokerButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (e.getSource() instanceof Button) {
					// pass the player to the game
					Poker.setPlayer(cpArray[pSelect]);
					
					try {
						stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/poker/Poker.fxml")),1000,500));
					} catch (IOException e1) {
						// TODO Auto-generated catch block							
						e1.printStackTrace();
					}
					stage.centerOnScreen();
					stage.setTitle("5-Stud Poker");
					
				}
			}
		});
		
		// Formatting for start menu buttons.
		for (int i = 0; i < 3; i++) {
			smButtons.get(i).setAlignment(Pos.CENTER);
			smButtons.get(i).setMaxWidth(Double.MAX_VALUE);
			menu.setHgrow(smButtons.get(i), Priority.ALWAYS);
			smButtons.get(i).setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 30));
			smButtons.get(i).setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-focus-color: yellow;");
		}
		
		// Formatting for profile menu buttons.
		for (int i = 3; i < 7; i++) {
			smButtons.get(i).setAlignment(Pos.CENTER);
			smButtons.get(i).setMaxWidth(Double.MAX_VALUE);
			profileMenu.setHgrow(smButtons.get(i), Priority.ALWAYS);
			smButtons.get(i).setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 30));
			smButtons.get(i).setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-focus-color: yellow;");
		}
		
		// Formatting for override menu buttons.
		for (int i = 7; i < 9; i++) {
			smButtons.get(i).setAlignment(Pos.CENTER);
			smButtons.get(i).setMaxWidth(Double.MAX_VALUE);
			smButtons.get(i).setMaxHeight(Double.MAX_VALUE);
			smButtons.get(i).setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 30));
			smButtons.get(i).setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-focus-color: yellow;");
		}
		
		// Formatting for chip amount buttons in creating new profile menu.
		for (int i = 9; i < 16; i++) {
			smButtons.get(i).setAlignment(Pos.CENTER);
			smButtons.get(i).setMaxWidth(Double.MAX_VALUE);
			newProfilePane.setHgrow(smButtons.get(i), Priority.ALWAYS);
			smButtons.get(i).setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 30));
			smButtons.get(i).setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-focus-color: yellow;");
		}
		
		// Formatting for button lightup on hover.
		for (Button b : smButtons) {
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
			});}
		// Show stage.g
		stage.show();
		}
	
	public static void main(String[] args) {
		StartMenu.launch(args);
		//Application.launch(args);

	}
	

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
