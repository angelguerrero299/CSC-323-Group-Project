/* Changes Made:
 *  Added comments and descriptions for methods.
 *  Changed the name of class from QueueList to List
 *  Removed the extend statement so that this class uses the item class but does not extend it
 *  Added indentation to statements within if/else statement
 *  Changed name of print method to displayItem because the name print is ambiguous. Method actually searches
 *   for an item within list and displays its information
 *
 */

import java.io.*;
import java.util.*;

public class List{

    private Item head; //points to head of list
    private Item curr; //used to look through list

    //List constructor, creates an empty list
    public List(){
        this.head = null;
        this.curr = null;
    }

    public boolean isEmpty(){
     //Returns true if list is empty
        return head == null;
    }//end isEmpty method


    public void insert(String item,int numStock, int minInv){
     /*Description: Inserts a new item at the end of the list
      *Precondition: List must be instantiated. Provide String item (name), int numStock (current stock),
      * and int minInv (minimum number allowed in stock before reorder)
      *Postcondition: Item will be added to end of list
      */
  Item newItem = new Item(item, numStock, minInv);
  curr = head;
        if(curr != null){
         //list is not empty, find end of list
         while(curr.nextItem != null)
          curr = curr.nextItem;
          if(curr.nextItem == null){
           //end of list found, insert newItem here
           curr.nextItem = newItem;
          }
       }
        else{
         //curr is null, list is empty
        head = newItem;
         curr = head;
        }
    }//end insert method

    public void displayItem(String item){
     /* Description: Checks to see if item exists and prints its information
      * Precondition: Provide the name of item
      * Postcondition:
      */
       curr = head; //start at beginning of list
       boolean stop = true;
       while(stop){
         //curr = curr.nextItem;
         String currName = curr.getItem().toLowerCase();
         String itemName = item.toLowerCase();
         if(currName.compareTo(itemName) == 0){
          //item found
            stop = false;
            System.out.println(curr.getItem() + ", " + curr.getNumStock() + ", " + curr.getMinInv());
         }
           if(stop){
            //continue examining list
            curr = curr.nextItem;
            if(curr == null){
             //end of list, item not found
             stop = false;
            }
           }
       }

    }//end displayItem method
 
    public void displayList(){
  /* Description: Display items in list
   * Precondition: List must not be empty
   * Postcondition: List will be printed
   */

   if(!isEmpty()){
    //list is not empty
    curr = head;
    System.out.println("Item: " + curr.getItem() + ", Stock: " + curr.getNumStock() +
      ", Minimum: " + curr.getMinInv());
    while(curr != null){
     System.out.println("Item: " + curr.getItem() + ", Stock: " + curr.getNumStock() +
       ", Minimum: " + curr.getMinInv());
     curr = curr.nextItem;
    }
   }
   else{
    System.out.println("Error: Cannot display list because list is empty");
   }

     }//end displayList method

    public void saveList(){
     /* Description: Saves the current list to a file
      * Precondition: List must not be empty
      * Postcondition: List will be saved to a file, each parameter will be separated by a comma
      */
       try{
       PrintWriter writer = new PrintWriter("test.txt","UTF-8");
       curr = head;
       while(curr != null){
        writer.println(curr.getItem() + "," + curr.getNumStock() + "," + curr.getMinInv());
        curr = curr.nextItem;
       }
         writer.close();
      }
       catch(IOException e){
        System.out.println("Error: Could not save data");
       }
    }//end saveList method

    public void loadList(){
     /* Description: Load a list saved to a text file
      * Precondition: File must exist in same folder
      * Postcondition: List in file will be saved to a list to be examined
      */
     File file = new File("test.txt");
     try {
         Scanner sc = new Scanner(file);
         while (sc.hasNextLine()) {
          //file has data within it
             String st = sc.nextLine();

             String temp[] = st.split(","); //save line of data to a string array
             String temp1 = temp[0]; //should contain the name of item
             int temp2 = Integer.parseInt(temp[1]); //should contain the number in stock that was saved
             int temp3 = Integer.parseInt(temp[2]); //should contain the minimum num in stock allowed
             insert(temp1,temp2,temp3); //add to new list
         }
         sc.close(); //close file
     }
     catch (FileNotFoundException e) {
      //file does not exist, creates blank file
       saveList();
         e.printStackTrace();
     }
    }//end loadList method
        public void itemExists(String item,int stock,int minAmount){
     /* Description: Checks to see if item exists and overwrites it
      * Precondition: Provide the name of item, stock amount, and min amount till reorder
      * Postcondition:
      */
       curr = head; //start at beginning of list
       boolean stop = true;
       if(curr==null){
         //checks if list isnt empty
         stop=false;
         insert(item,stock,minAmount);
       }
       while(stop){
         //curr = curr.nextItem;
         String currName = curr.getItem().toLowerCase();
         String itemName = item.toLowerCase();
         if(currName.compareTo(itemName) == 0){
          //item found
            curr.setItem(item);
            curr.setNumStock(stock);
            curr.setMinInv(minAmount);
            stop = false;
         }
           if(stop){
            //continue examining list
            curr = curr.nextItem;
            if(curr == null){
             //end of list, item not found
             stop = false;
             insert(item,stock,minAmount);
             //calls insert to add item if not found in list
            }
           }
       }

    }//end itemExists method
}//end class
