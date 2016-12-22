package hw5;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> usedCard= new ArrayList<Card>();
	private ArrayList<Card> cards= new ArrayList<Card>();
	private ArrayList<Card> openCard= new ArrayList<Card>();
	public int nUsed;
	
	 	//TODO: Please implement the constructor 
	 	public Deck(int nDeck){ 
	 		cards=new ArrayList<Card>();
	 		for (int a=1;a<=nDeck;a++)
	 		{
	 		for(Card.Suit b:Card.Suit.values())
	 		{
	 		for (int y=1;y<=13;y++)
	 		{
	 		Card card=new Card(b,y);
	 		cards.add(card);
	 		}
	 		}
	 		}
	 		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker 
	 		//Hint: Use new Card(x,y) and 3 for loops to add card into deck 
	 		//Sample code start 
	 		//Card card=new Card(1,1); ->means new card as clubs ace 
	 		//cards.add(card); 
	 		//Sample code end 
	 
	 shuffle();
	 	}	 
	 	//TODO: Please implement the method to print all cards on screen 
	 	public void printDeck(){
	 		for(Card z: cards)
	 		{
	 			z.printCard();
	 		}
	 		//Hint: print all items in ArrayList<Card> cards,  
	 		//please implement and reuse printCard method in Card class 
	 	} 
	 	public ArrayList<Card> getAllCards(){ 
	 		return cards; 
	}
	 	public ArrayList<Card> getOpenCards(){
	 	   return openCard;
	 	}
 public Card getOneCard(boolean isOpened)
 {
	 int n = usedCard.size();
	 if(n<52)
	 {
		 usedCard.add(cards.get(n));
	 if (isOpened) openCard.add(cards.get(n));
	 nUsed ++; 
	 if (nUsed == 52)  
	 {
		 Card x = usedCard.get(51);
		 shuffle();
		 return x;
	 }
	 }
	 return usedCard.get(n); 

	 
	 
 }
	 public void shuffle(){
	 Random R= new Random();
	 for(int n=1;n<=52;n++)
	 {
	 int first = R.nextInt(51); 
	 int second = R.nextInt(51); 
	 Card x = cards.get(first); 
	 cards.set(first, cards.get(second)); 
	 cards.set(second,x); 
	 	}
	 
	 usedCard.clear();
	 openCard.clear();
	 nUsed = 0;
			 }
}
