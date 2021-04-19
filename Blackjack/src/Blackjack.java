import java.util.*;

public class Blackjack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int seed = Integer.parseInt(args[0]);
		int num_of_players = Integer.parseInt(args[1]);
		boolean go = true;
		boolean done = false;
		
		House house = new House();
		Player player = new Player();
		Computer[] computer = new Computer[num_of_players-1];
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		Deck deck = new Deck();
		
		deck.shuffle(seed);
		
		player.AddCard(deck.dealCard(seed));
		 for(int i = 0; i<computer.length; ++i) {
	        computer[i] = new Computer();
	        computer[i].AddCard(deck.dealCard(seed));
	      }
		house.AddCard(deck.dealCard(seed));
		
		player.AddCard(deck.dealCard(seed));
		for(int i = 0; i<computer.length; ++i)
			computer[i].AddCard(deck.dealCard(seed));
		house.AddCard(deck.dealCard(seed)); 

		for(int i = 0; i<computer.length; ++i)
			if(computer[i].getScore() == 21 || player.getScore() == 21 || house.getScore() == 21)
				done = true;
		
		
		
		System.out.println("House: HIDDEN, " + house.getCard(1).CardInfo());

		System.out.print("Player1: ");
		PrintStatus(player);
		 
		 for(int i = 0; i<computer.length;i++) {
			System.out.print("Player" + (i+2) + ": ");
			PrintStatus(computer[i]);
		 }
		 if(!done) {
		 System.out.println();
		 System.out.println("--- Player1 turn ---");
		 
			System.out.print("Player1: ");
			PrintStatus(player);
		 
		 while(go) {
			 
			String decision = scan.nextLine();
			switch(decision) {
			 
			case "Hit":
				player.AddCard(deck.dealCard(seed));
				System.out.print("Player1: ");
				if(player.getScore() > 21) {
					player.bust = true;
					go = false;
				}
				PrintStatus(player);
				break;
				
			case "Stand":
				System.out.print("Player1: ");
				
				PrintStatus(player);
				go = false;
				break;

			default:
				System.out.println("Wrong input");
				break;
			 }

		 }
		
		 
		 for(int i = 0; i < computer.length; i++) {
			go = true;
			System.out.println();
			System.out.println("--- Player" + (i+2) + " turn ---");
			 
			while(go) {	
				
				System.out.print("Player" + (i+2) + ": ");
				PrintStatus(computer[i]);

				if(computer[i].getScore() < 14) {
					System.out.println("Hit");
					computer[i].AddCard(deck.dealCard(seed));
				}
				
				else if(computer[i].getScore() > 17) {
					System.out.println("Stand");
					System.out.print("Player" + (i+2) + ": ");
					PrintStatus(computer[i]);
					go = false;
				}
				
				else if(computer[i].getScore() >= 14 && computer[i].getScore() <= 17) {
					
					if((int)(rand.nextInt(2)) == 1) {
						System.out.println("Hit");
						computer[i].AddCard(deck.dealCard(seed));
					}
					
					else {
						System.out.println("Stand");
						System.out.print("Player" + (i+2) + ": ");
						PrintStatus(computer[i]);
						go = false;
					}
				}
				if	(computer[i].getScore() > 21) {
					System.out.print("Player" + (i+2) + ": ");
					computer[i].bust = true;
					PrintStatus(computer[i]);
					go = false;
				}
			}
		}
		 	go = true;
		 	System.out.println();
			System.out.println("--- House turn ---");
			
			while(go) {
				
				System.out.print("House: ");
				PrintStatus(house);
				
				if(house.getScore() <= 16) {
					System.out.println("Hit");
					house.AddCard(deck.dealCard(seed));
				}
				
				else if(house.getScore() >= 17 && house.getScore() <= 21) {
					System.out.println("Stand");
					System.out.print("House: ");
					PrintStatus(house);
					go = false;
				}

				
				if	(house.getScore() > 21) {
					System.out.print("House: ");
					house.bust = true;
					PrintStatus(house);
					go = false;
				}
			}
		}
			System.out.println();
			System.out.println("--- Game Results ---");
			
			System.out.print("House: ");
			PrintStatus(house);
			
			if(player.bust || player.getScore() < house.getScore())
				System.out.print("[Lose]");
			
			else if(player.getScore() == house.getScore())
				System.out.print("[Tied]");
			
			else
				System.out.print("[Win]");
			
			System.out.print("Player1: ");
			PrintStatus(player);
			
			
			for(int i = 0; i < computer.length; i++) {
				if(computer[i].bust || computer[i].getScore() < house.getScore())
					System.out.print("[Lose]");
				
				else if(computer[i].getScore() == house.getScore())
					System.out.print("[Tied]");
				
				else
					System.out.print("[Win]");
				
				System.out.print("Player" + (i+2) + ": ");
				PrintStatus(computer[i]);
				
			}
			
	}

	public static void PrintStatus(Hand h) {
		for(int j = 0; j<h.getCardQuantity(); j++) {
			System.out.print(h.getCard(j).CardInfo());
			if(j != h.getCardQuantity() - 1)
				System.out.print(", ");
		}
		System.out.print (" (" + h.getScore() + ")");
		if(h.bust)
			System.out.print(" - Bust!");
		System.out.println();
	}
	
}


class Card {
	private int suit;
	private int value;
	
	   public Card() {}
	   public Card (int theValue, int theSuit) {
		   this.setSuit(theSuit);
		   this.setValue(theValue);
	   }
	   
	public int getSuit() {
		return suit;
	}
	
	public void setSuit(int suit) {
		this.suit = suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	   
	public String Suit_to_String() {
		switch(suit) {
		case 0:
			return "c";
		case 1:
			return "h";
		case 2:
			return "d";
		case 3:
			return "s";
		default:
			return "Something Wrong";
		}
	}
	   
	public String Value_to_String() {
		switch(value) {
		case 1:
			return "A";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";	
		default:
			return "Something Wrong";
			
		}
	}
	
	public String CardInfo() {
		return Value_to_String() + Suit_to_String();
	}
}


class Deck {
	   private Card[] deck;
	   private int cardsUsed;
	   
	   public Deck() {
		   deck = new Card[52];							// 52 Cards in a deck
		   int count = 0;								// Quantity of cards in a deck
		   
		   for(int i = 1; i<=13; i++) {					// Put cards in deck.
			   for (int j = 0; j<=3; j++) {
				   deck[count] = new Card(i,j);
				   count++;
			   }
		   }
		   cardsUsed = 0;								// Initialize variable 'cardsUsed'
	   }
	   
	   public void shuffle (int seed) {					// Deck shuffling function
	      Random random = new Random(seed);
	      for ( int i = deck.length - 1; i > 0; i--) {
	         int rand = (int)(random.nextInt(i+1));
	         Card temp = deck[i];
	         deck [i] = deck[rand];
	         deck[rand] = temp;
	      }
	      cardsUsed = 0;
	   }
	 
	   public Card dealCard(int seed) {					// Give card
		   if (cardsUsed == 52)
			   shuffle(seed);
		   cardsUsed++;
		   return deck[cardsUsed - 1];
	   }
}


class Hand{
	ArrayList<Card> hand;
	boolean bust = false;
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void AddCard(Card c) {
		if(c!=null)
			hand.add(c);
	}
	
	public Card getCard(int pos) {
		if(pos >= 0 && pos < hand.size()) {
			return (Card)hand.get(pos);
		}
			
		else
			return null;
	}
	
	public int getCardQuantity() {
		return hand.size();
	}
	
	public int getScore() {
		int score = 0;
		boolean ace = false;
		
		for(int i = 0; i < hand.size(); i++) {
			Card card = getCard(i);
			int CardValue = card.getValue();
			
			if(CardValue == 11 || CardValue == 12 || CardValue == 13)
				CardValue = 10;
			
			if(CardValue == 1)
				ace = true;
			
			score += CardValue;
			
			if(score <= 11 && ace)
				score += 10;
			
			else if (score >21 && ace)
				score -= 10;
		}
		return score;
	}
	
	
}

class Player extends Hand{
	public Player() {}
}

class Computer extends Hand {
	public Computer() {}
}

class House extends Hand{
	public House() {}
}
