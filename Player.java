package hw5;

import java.util.ArrayList;

public class Player  extends Person{
  private String name;
  private int chips;
  public int bet=0;
  private ArrayList<Card>oneRoundCard=new ArrayList<>();
	
  public Player(String name, int chips){
	  this.name=name;
	  this.chips= chips;
}

       public  String get_name(){
	   return name;
       }
	   public int  make_bet(){
		   if(bet>chips)
		   {
			   System.out.println("�������y~����A�U�`�F�I");
			return bet;   
		   }		  
		   else
		   return bet=1;
	   }
 	   
	   public boolean hit_me(Table table){
		   
		   if(getTotalValue()<17)return true;
		   else return false;
	   }
	   
	   public int get_current_chips(){
		   return chips;
	   }
	   public void increase_chips(int diff){
	   
	   chips+=diff;
	   }

	   public void say_hello(){
		   
		   System.out.println("�A�n�A�ڬO"+name+",");
		   System.out.println("�ڦ�"+chips+"chips.");
		   
	   }
}
	  