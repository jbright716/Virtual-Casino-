package roulette;




import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

	public class Space {
		//declare variables to hold the number and color of the space
		private int number;
		private Color color;
		private String BetType;
		private String name;
		
		// Default constructor
		public Space(int number, Color color, String BetType) {
			super();
			this.number = number;
			this.color = color;
			this.BetType = BetType;
			
			if ( number == 100) {
				this.setName("00");
			} else {
			this.setName(String.valueOf(number));
			}
		}
		public Space(String betType) {
			this.BetType = betType;
			this.setName(betType);
		}

		/**
		 * @return the number
		 */
		public int getNumber() {
			return number;
		}
		
		/**
		 * @param number the number to set
		 */
		public void setNumber(int number) {
			this.number = number;
		}
		public String getName() {
			return name;
		}
		
		/**
		 * @param number the number to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the color
		 */
		public Color getColor() {
			return color;
		}

		/**
		 * @param color the color to set
		 */
		public void setColor(Color color) {
			this.color = color;
		}
		/**
		 * @param betType the bettype to set
		 */
		public void setBetType(String betType) {
			this.BetType = betType;
		}
		/**
		 * @return the bet type
		 */
		public String getBetType() {
			return BetType;
		}
		
		public Pane makeNumSpace()  {
    	
			// style a pane the space
			Pane innerPane = new Pane();
    		StackPane temp = new StackPane();
    		innerPane.setStyle("-fx-border-color: black; -fx-border-color: white; -fx-border-width: 3;");
    		
    		//format the number on 
    		Text txt = new Text(Integer.toString(this.getNumber()));
    		txt.setFill(Color.WHITE);
    		txt.setStyle("-fx-font-size: 30; -fx-font-weight: bold; ");
    		Button b1 = new Button();
    		b1.setBackground(null);
    		Circle circ = new Circle(25,color);
    		
	   		//Add components to the pane
	    	temp.getChildren().addAll(circ, txt);
			
    		
    		temp.setRotate(270);
    		
    		b1.setGraphic(temp);

    		innerPane.getChildren().add(b1);
    		
    		//center the space
    		
    		b1.setLayoutX(0);
    		b1.setLayoutY(10);
			//return the pane
    		return innerPane;
			
		}
		
		public Pane makeSpecNumSpace() {
	    	
			// style a pane the space
			Pane innerPane = new Pane();
    		StackPane temp = new StackPane();
    		innerPane.setStyle("-fx-border-color: black; -fx-border-color: white; -fx-border-width: 5;");
    		String numTxt = "";
    		if (this.getNumber() == 100) {
    			numTxt = "00";
    		} else{
    			numTxt = "0";
    		}
    		//format the number on 
    		Text txt = new Text(numTxt);
    		txt.setFill(Color.WHITE);
    		txt.setStyle("-fx-font-size: 30; -fx-font-weight: bold; ");
    		Button b1 = new Button();
    		b1.setBackground(null);
    		Circle circ = new Circle(25,color);
    		
    		//Add components to the pane
    		temp.getChildren().addAll(circ, txt);
    		temp.setRotate(270);
    		b1.setGraphic(temp);

    		innerPane.getChildren().add(b1);
    		
    		//center the space
    		
    		b1.setLayoutX(0);
    		b1.setLayoutY(100);
			//return the pane
    		return innerPane;
			
		}
		public Pane makeTopAltSpace() {
			
			
			Pane innerPane = new Pane();
    		StackPane temp = new StackPane();
    		innerPane.setStyle("-fx-border-color: black; -fx-border-color: white; -fx-border-width: 5;");

    		//format the on 
    		Text txt = new Text(BetType);
    		txt.setFill(Color.WHITE);
    		txt.setStyle("-fx-font-size: 30; -fx-font-weight: bold; ");
    		
    		Button b1 = new Button();
    		b1.setBackground(null);
    		
    		
    		temp.getChildren().add(txt);
    		b1.setGraphic(temp);
   
    		b1.setLayoutX(100);
    		
    		
    		innerPane.getChildren().add(b1);
			return innerPane;
			
		}
		
		
		public Pane makeBotAltSpace() {
					
					
				Pane innerPane = new Pane();
		    	StackPane temp = new StackPane();
		   		innerPane.setStyle("-fx-border-color: black; -fx-border-color: white; -fx-border-width: 5;");
		
		   		//format the on 
		   		Text txt = new Text(BetType);
		   		txt.setFill(Color.WHITE);
	    		txt.setStyle("-fx-font-size: 30; -fx-font-weight: bold; ");
		    		
	    		Button b1 = new Button();		    		
	    		b1.setBackground(null);
	    			
		    		
		    	temp.getChildren().add(txt);
		   		b1.setGraphic(temp);
		   
		   		b1.setLayoutX(50);
		    		
		    		
		   		innerPane.getChildren().add(b1);
				return innerPane;
					
				}
		

	}


