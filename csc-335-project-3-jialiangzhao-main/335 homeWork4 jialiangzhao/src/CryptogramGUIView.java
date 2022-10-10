/**
 * @author: jialiangzhao
*Classroom: csc 335
*file:CryptogramGUIView.java
*Content: This is the javafx program, he will create a window.
* It uses many programs to capture the current situation and
*  then make judgments. There are many buttons on it, each 
*  button has different functions.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CryptogramGUIView extends Application {
	public final String args="src/quotes.txt";
	public ArrayList<String> show;
	public ArrayList< TextField > list;
	public ArrayList< Label> key;
	public HashMap< TextField,Character > map;
	public static  CryptogramModel a;
	public  CryptogramController b;
   public int i;
   public BorderPane hehe;
   public ArrayList< Label > change;
   public  ObservedObject watched;
   public static ArrayList<String> content;
   /**
	 *Trigger run
	 */
	public static void main(String[] args) throws FileNotFoundException {
	
	
		launch(args);
		
	}
	/**
	 *This is a window class that will lay out our 
	 *interface and place my buttons.
	 */
	@Override
	public void start(Stage arg0) throws Exception {
	
		content= new ArrayList<String>();
		Scanner file = new Scanner(new File(args));
        String first = file.nextLine();
        content.add(first);
	   while(file.hasNext()) {
		   first = file.nextLine();
		   content.add(first);
	   }

	 int line=new Random().nextInt(content.size());
		 a=new CryptogramModel(content.get(line));
	 //a=new CryptogramModel("Everyone knows that debugging is twice as hard as writing a program in the first place. So if you're as clever as you can be when you write it, how will you ever debug it? - Brian Kernighan");
		 b=new CryptogramController(a);
		 String c=b.getEncryptedQuote1();
		 map=new HashMap< TextField,Character >();
		 change=new ArrayList< Label >();
		 key=new ArrayList< Label >();
	
		  hehe=new BorderPane();
		 
		 GridPane root = new GridPane();
		 GridPane root1 = new GridPane();
	       
	         VBox box=new VBox();
	
		
	     CheckBox checkBox = new CheckBox("Show Hints");
	  
	     GridPane.setHalignment(checkBox, HPos.RIGHT);
	     root1.add(checkBox,1,2); 
	     show=b.showWord();
	     
	     for(int i=0;i<show.size();i++) {
	    	 Label word = new Label( show.get(i));
	    	 change.add(word);
	    	 GridPane.setHalignment(word, HPos.LEFT);
	    	 box.getChildren().add(word);
	        
	     }
	     root1.add(box, 1, 3);
	     
	     
	     
	   list=new ArrayList<TextField>();
	     hehe.setCenter(root);

	     hehe.setRight(root1);
	     
	       // Put on cell (0,0), span 2 column, 1 row.
	     //  GridPane.setHalignment(labelTitle, HPos.RIGHT);
	      // root.add(labelTitle, 22, 2);
	     
	       int x=0;
	       int j=0;
	       int d=0;
	       TextField fieldUserName = new TextField();
	       for(int i=0;i<c.length();i++) {
	    	   VBox newBox=new VBox();
	    	   d++;
	    	   if(c.charAt(i)=='$') {
		    	   x=+d;
		    	   j=j+2;
		    	   continue;}
	    	   
	    if(c.charAt(i)>='A'&& c.charAt(i)<='Z') {
	    	  fieldUserName = new TextField();
	    	 
		       fieldUserName.setPrefColumnCount(1);
		       list.add( fieldUserName);
		      
		       newBox.getChildren().add(fieldUserName);
	    }else {
	        fieldUserName = new TextField(""+c.charAt(i));
	      
	       list.add( fieldUserName);
	       fieldUserName.setPrefColumnCount(1);
	     
	       fieldUserName.setDisable(true);
	       newBox.getChildren().add(fieldUserName);
	       }
	    
	       Label labelTitle1 = new Label("  "+c.charAt(i)+"");
	       key.add(labelTitle1);
	     map.put(fieldUserName,c.charAt(i));
	      // GridPane.setHalignment(labelTitle1, HPos.CENTER);
	       newBox.getChildren().add(labelTitle1);
	       
	       newBox.setPrefWidth(26);
	     root.add(newBox, i-x, j+1);
	       }
	     
	       for(i=0;i<list.size();i++) {
	     list.get(i).setOnKeyTyped(new EventHandler<KeyEvent>() {
	   /**
	    * Trigger the when some input setin button.
	    */
			public void handle(KeyEvent event) {
				
				TextField field = (TextField) event.getSource();
				String text=event.getCharacter();

		            watched=new ObservedObject();
				     Look look=new Look(text, map.get(field)+"");
				     watched.addObserver(look);
				     watched.setValue();
				
		            if(text.length()>=1) {
		            	event.consume();
		            	return;
		            }
		           
			}
		});
	       }
	      

	       Button loginButton = new Button("New Puzzle");
	       Button loginButton1 = new Button("Hint");
	       checkBox.setSelected(false);
	   	
	       box.visibleProperty().bind(checkBox.selectedProperty());
	       
	       loginButton.setOnAction(new EventHandler<ActionEvent>() {
	    	/**
	    	 * Trigger the new puzzle button.
	    	 */
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				 int line=new Random().nextInt(content.size());
				 a=new CryptogramModel(content.get(line));
				 b=new CryptogramController(a);
				 String c=b.getEncryptedQuote1();
				
				
				
				   show=b.showWord();
				 for(int i=0;i<show.size();i++) {
					 change.get(i).setText(show.get(i)); 
					
			     }
				 GridPane root = new GridPane();
				 list=new ArrayList<TextField>();
				 key=new ArrayList<Label>();
			     hehe.setCenter(root);
			       int x=0;
			       int j=0;
			       int d=0;
			       TextField fieldUserName = new TextField();
			       for(int i=0;i<c.length();i++) {
			    	   VBox newBox=new VBox();
			    	   d++;
			    	   if(c.charAt(i)=='$') {
				    	   x=+d;
				    	   j=j+2;
				    	   continue;}
			    	   
			    if(c.charAt(i)>='A'&& c.charAt(i)<='Z') {
			    	  fieldUserName = new TextField();
				       fieldUserName.setPrefColumnCount(1);
				       list.add( fieldUserName);
				       newBox.getChildren().add(fieldUserName);
			    }else {
			        fieldUserName = new TextField(""+c.charAt(i));
			       
			       list.add( fieldUserName);
			       fieldUserName.setPrefColumnCount(1);
			       fieldUserName.setDisable(true);
			       newBox.getChildren().add(fieldUserName);
			       }
			    
			       Label labelTitle1 = new Label("  "+c.charAt(i)+"");
			       key.add(labelTitle1);
			     map.put(fieldUserName,c.charAt(i));
			      // GridPane.setHalignment(labelTitle1, HPos.CENTER);
			       newBox.getChildren().add(labelTitle1);
			       
			       newBox.setPrefWidth(26);
			     root.add(newBox, i-x, j+1);
			       }
			       for(i=0;i<list.size();i++) {
			  	     list.get(i).setOnKeyTyped(new EventHandler<KeyEvent>() {
			  	   
			  			public void handle(KeyEvent event) {
			  				
			  				TextField field = (TextField) event.getSource();
			  				String text=event.getCharacter();

			  		            watched=new ObservedObject();
			  				     Look look=new Look(text, map.get(field)+"");
			  				     watched.addObserver(look);
			  				     watched.setValue();
			  				
			  		            if(text.length()>=1) {
			  		            	event.consume();
			  		            	return;
			  		            }
			  		           
			  			}
			  		});
			  	       }
				 
			    
				 
			}
			});
	       loginButton1.setOnAction(new EventHandler<ActionEvent>() {
	    	   /**
	    	    * Trigger the hint button.
	    	    */
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					char aChar = 0;
					
					for(int i=0;i<list.size();i++) {
						
						if(list.get(i).getText().equals("")) {
							list.get(i).setText(""+a.correctAnswer.get(map.get(list.get(i))));
							 aChar=map.get(list.get(i));
							 watched=new ObservedObject();
		  				     Look look=new Look(""+a.correctAnswer.get(map.get(list.get(i))),""+aChar );
		  				     watched.addObserver(look);
		  				     watched.setValue();
							break;
						}
					}
					
					
				}
			});
	       
	       
	       GridPane.setHalignment(loginButton, HPos.LEFT);
	       root1.add(loginButton, 1, 0);
	       GridPane.setHalignment(loginButton1, HPos.LEFT);
	       root1.add(loginButton1, 1, 1);

	       Scene scene = new Scene(hehe, 900, 400);
	       arg0.setTitle("Cryptograms");
	       arg0.setScene(scene);
	       arg0.show();
	       
		
	}
	/**
	 * He is an observer, he will judge the current 
	 * situation and make judgments. Such as judging 
	 * the end of the game, judging whether to start 
	 * the next round.
	 * @author zhaojialiang
	 *
	 */
	public class Look implements Observer{
		String ch;
		String ch1;
		public Look(String ch,String ch1) {
			this.ch=ch;
			this.ch1=ch1;
		   }
	
		public void update(Observable o, Object arg) {
		
			for(int i=0;i<list.size();i++) {
			
				if(key.get(i).getText().equals("  "+ch1)) {
					
					list.get(i).setText(ch);
				
			}}
			int x=0;
		for(int i=0;i<list.size();i++) {
			if(a.correctAnswer.get(key.get(i).getText().charAt(2))==null){
				continue;
			}
			if(!list.get(i).getText().equals(""+a.correctAnswer.get(key.get(i).getText().charAt(2)))) {
				x=1;
			
		}
		}
	if(x==0) {
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setTitle("Message");
		a.setContentText("You won!");
		a.setHeaderText("Message");
		for(int i=0;i<list.size();i++) {
			list.get(i).setDisable(true);
		}
		a.showAndWait();
	}
			
		}
		
		}
	/**
	 * This is an observation class that will observe
	 *  changes every moment and remind the observer.
	 * @author zhaojialiang
	 *
	 */
	class ObservedObject extends Observable {
	
		   
		   public ObservedObject() {
		 
		   }
		   
		   public void setValue() {
		      // if value has changed notify observers
		    
		 
		         
		         // mark as value changed
		         setChanged();
		         // trigger notification
		         notifyObservers();
		      
		   
		   }
		}
	
}

