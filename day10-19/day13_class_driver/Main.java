package day13_class_driver; // package name for organization

public class Main {
    public static void main(String[] args) {

        // Create an array to hold all 52 cards
        Card[] cards = new Card[52];
        int index = 0;

        // Nested loop to generate every card:
        // suits 0–3, ranks 1–13
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                cards[index] = new Card(rank, suit); // create a new Card
                index++; // move to next slot in array
            }
        }

        // Choose a target card to search for (King of Hearts: rank=13, suit=2)
        Card target = new Card(13, 2); 
        
        // Perform linear and binary search on the deck
        int result = linearSearch(cards, target);
        int result2 = binarySearch(cards, target);

        // Print results depending on whether the card was found
        if (result != -1 && result2 != -1) {
            System.out.println("Found " + target + " at index: " + result);
            System.out.println("Found " + target + " at index (binary): " + result2);
        } else {
            System.out.println(target + " not found");
        }

        
    }
        
    // Linear search: check each card one by one until match is found
    public static int linearSearch(Card[] cards, Card target) {
        for (int i = 0; i < cards.length; i++) {    // uses .equals from Card class
            if (cards[i].equals(target)) {
                return i;   // return index if found
            }
        }
        return -1;
    }

    public static int binarySearch(Card[] cards, Card target) {
        int low = 0;
        int high = cards.length - 1; 

         // Repeat until the search space is empty
        while (low <= high) {
            int mid = (low + high) / 2;     // middle index
            int comp = cards[mid].compareTo(target);    // compare target with mid card

            if (comp == 0){     // match found
                return mid;

            } else if (comp < 0) {
                low = mid + 1;      // search in right half

            }else{  
                high = comp - 1;    // search in left half
            }
                            
        }
        return -1;   // not found


    }

}