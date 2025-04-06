import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    public static ArrayList<String> myArrList = new ArrayList<>();
    public static ArrayList<String> MainMenu = new ArrayList<>();
    public static Scanner in = new Scanner(System.in);
    public static boolean quitMenu = false;


    public static void main(String[] args) {
        String selection = "";
        initMenu();
        do
        {
            //before the user gets to do anything, print the menu.
            printMenu();
            do
            {
                selection = SafeInput.getRegExString(in, "What do you want to do?","[AaDdIiPpQq]");
                //the switch allows us to add/change items later if we want. See also: note in initMenu()
                switch(selection) {
                    case "A":
                    case "a":
                        addItem();
                        break;
                    case "D":
                    case "d":
                        deleteItem();
                        break;
                    case "I":
                    case "i":
                        insertItem();
                        break;
                    case "P":
                    case "p":
                        printList();
                        break;
                    case "Q":
                    case "q":
                        quitList();
                        break;
                }
            }while(selection == "");//prompt user until there is a valid input.
        }
        while(!quitMenu);//if the user wants to leave stop looping.
    }

    public static void addItem()
    {
        //Add an item always puts it at the end of the list.
        myArrList.add(SafeInput.getNonZeroLenString(in, "What do you want to enter?"));
    }

    public static void deleteItem()
    {
        printList();
        //Delete an item user has to specify which one using the item number from the display.
        myArrList.remove(SafeInput.getRangedInt(in,"Enter the line you would like to delete",1,myArrList.size())-1);
    }


    public static void insertItem()
    {
        //this only runs if the user wants to insert a line into an empty list.
        if(myArrList.size() == 0)
        {
            System.out.println("There are no items in the list, adding one instead.");
            addItem();
        }
        //...or a "list" of one item.
        else if(myArrList.size() == 1)
        {
            // also adds a custom message per number of item(s) available.
            System.out.println("There is only one item, adding to it instead.");
            addItem();
        }
        //otherwise, they get to choose where to insert the item.
        else {
            printList();//I wanted to display the list for the user before they made a potentially blind decision.
            //Insert an item user has to indicate where using a location number.
            myArrList.add(SafeInput.getRangedInt(in, "Enter the line number you would like to insert a line AFTER",
                    1, myArrList.size()), SafeInput.getNonZeroLenString(in, "What do you want to enter?"));
        }
    }

    public static void printList()
    {
        //Print the list just displays the list.
        //I chose to add numbers to the list for the user to easily select an item.
        for(String i : myArrList) {
            System.out.println(myArrList.indexOf(i)+1+": "+i);
        }
    }

    // Sets the bool to quit the program after making sure the user meant to.
    public static void quitList()
    {
        //Quit asks the user if they are sure and then terminates the program.
        if (SafeInput.getYNConfirm(in,"Are you sure you want to quit?")) {
            quitMenu = true;
            System.out.println("Goodbye!");
        }
    }

    //Called once, initializes menu.
    //I didn't want this list initialization up in the variables as it made a mess,
    // though this isn't really necessary it looks cleaner to me,
    // and it gives the possibility to customize the menu more easily.
    public static void initMenu()
    {
        MainMenu.add("A – Add an item to the list");
        MainMenu.add("D – Delete an item from the list");
        MainMenu.add("I – Insert an item into the list");
        MainMenu.add("P – Print (i.e. display) the list");
        MainMenu.add("Q – Quit the program");
    }

    // Display the main menu, this will happen until the user quits.
    public static void printMenu()
    {
        System.out.println();
        //Print the list just displays the list.
        for(String i : MainMenu) {
            System.out.println(i);
        }
        System.out.println();
    }
}
