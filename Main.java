import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
   Shopping Cart
*/

public class ShoppingCart extends Application
{
   // ArrayList fields
   private ArrayList<String> inventoryTitles = new ArrayList<>();
   private ArrayList<Double> inventoryPrices = new ArrayList<>();
   private ArrayList<String> cartTitles = new ArrayList<>();
   private ArrayList<Double> cartPrices = new ArrayList<>();

   private double cartSubtotal = 0.0;

   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }

   @Override
   public void start(Stage primaryStage)
   {
      // Build the inventory ArrayLists
      try
      {
         readBookFile();
      }
      catch (IOException e)
      {
         System.out.println("Error reading the file.");
      }

      // Convert the inventoryTtitles ArrayList to an ObservableList.
      ObservableList<String> strList = FXCollections.observableArrayList(inventoryTitles);

      // Build the Book ListView
      ListView<String> booksListView = new ListView(strList);
      Label bookPromptLabel = new Label("Pick a Book");
      VBox bookVBox = new VBox(10, bookPromptLabel, booksListView);

      // Build the Shopping Cart ListView




      // Create the output label for the cart subtotal.
      Label subtotalDescriptor = new Label("Subtotal:");
      Label subtotalOutputLabel = new Label("0.00");
      HBox subtotalHBox = new HBox(10, subtotalDescriptor, subtotalOutputLabel);
      subtotalHBox.setAlignment(Pos.CENTER);

      // Create the output label for the tax.



      // Create the output label for the cart total.



      // Add To Cart Button
      Button addToCartButton = new Button("Add To Cart");
      addToCartButton.setOnAction(e ->
      {
         // Get the selected index.
         int index = booksListView.getSelectionModel().getSelectedIndex();

         // Add the item to the cart.
         if (index != -1)
         {
            // Update the cart ArrayLists
            cartTitles.add(inventoryTitles.get(index));
            cartPrices.add(inventoryPrices.get(index));

            // Update the cartListView
            ObservableList<String> tempOList = FXCollections.observableArrayList(cartTitles);
            cartListView.setItems(tempOList);

            // Update the subtotal
            cartSubtotal += inventoryPrices.get(index);
            subtotalOutputLabel.setText(String.format("%,.2f", cartSubtotal));
         }
      });

      // Remove From Cart Button
      Button removeFromCartButton = new Button("Remove From Cart");
      removeFromCartButton.setOnAction(e ->
      {
         // Get the selected index.

         // Add the item to the cart.

         {
            // Update the subtotal

         }
      });

      // Clear Cart Button

      {
         // Update the subtotal


         // Clear the cart ArrayLists


         // Update the cartListView

      });

      // Checkout Button
      Button checkoutButton = new Button("Checkout");
      checkoutButton.setOnAction(e ->
      {
         final double TAX_RATE = 0.07;

         // Calculate the tax
         double tax = cartSubtotal * TAX_RATE;
         taxOutputLabel.setText(String.format("%,.2f", tax));

         // Calculate the total
         double total = cartSubtotal + tax;
         totalOutputLabel.setText(String.format("%,.2f", total));
      });

      // Build the VBox to hold the Add button
      VBox addButtonVBox = new VBox(10, addToCartButton);
      addButtonVBox.setAlignment(Pos.CENTER);

      // Build the VBox to hold the cart buttons
      VBox cartButtonsVBox = new VBox(10, removeFromCartButton, 
                                          clearCartButton, 
                                          checkoutButton);
      cartButtonsVBox.setAlignment(Pos.CENTER);

      // Build the top part of the GUI
      HBox topHBox = new HBox(10, bookVBox, addButtonVBox, cartVBox, cartButtonsVBox);

      // Build the bottom part of the GUI
      HBox bottomHBox = new HBox(10, subtotalHBox, taxHBox, totalHBox);

      // Put everything into a VBox
      VBox mainVBox = new VBox(10, topHBox, bottomHBox);
      mainVBox.setAlignment(Pos.CENTER);
      mainVBox.setPadding(new Insets(10));

      // Add the main VBox to a scene.
      Scene scene = new Scene(mainVBox);

      // Set the scene to the stage aand display it.
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   private void readBookFile() throws IOException
   {
      String input;  // To hold a line from the file

      // Open the file.
      File file = new File("BookPrices.txt");
      Scanner inFile = new Scanner(file);

      // Read the file.
      while (inFile.hasNext())
      {
         // Read a line.
         input = inFile.nextLine();

         // Tokenize the line.
         String[] tokens = input.split(",");

         // Add the book info to the ArrayLists.
         inventoryTitles.add(tokens[0]);
         inventoryPrices.add(Double.parseDouble(tokens[1]));
      }

      // Close the file.
      inFile.close();
   }
}