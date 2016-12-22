package hw5;

public class Table {

	static final int MAXPLAYER = 4; 
	 	private Deck AllCards;	//存放所有的牌 
	 	private Player[] players ;		//存放所有玩家 
	 	private Dealer dealer;		//存放一個莊家 
	 	int[] pos_betArray ;		//存放每個玩家一局的下注 
	 	private Card face_up_card_of_dealer; 
	 	 
	 	public Table ( int nDeck){ 
	 		AllCards = new Deck(nDeck); 
	 		players = new Player[MAXPLAYER]; 
	 	} 
	 	 
	 	public void set_player(int pos, Player p){ 
	 		players[pos] = p; 
	 	} 
	 	 
	 	public Player[] get_player(){		//回傳所有的Player 
	 		return players; 
	 	} 
	 
	 	public void set_dealer(Dealer d){ 
	 		dealer = d; 
	 	} 
	 	 
	 	public  Card get_face_up_card_of_dealer(){ 
	 		return face_up_card_of_dealer; 
	 	} 
	 	 
	 	private void ask_each_player_about_bets(){ 
	 		int n =0; 
	 		pos_betArray =new int[players.length]; 
	 		for ( Player x : players) 
			{ 
	 			x.say_hello(); 
	 			x.make_bet(); 
	 			pos_betArray[n] = x.bet;	//儲存每個玩家的下注 
	 			n++; 
	 		} 
	 	} 
	 	 
	 	private void distribute_cards_to_dealer_and_players(){		//發牌給玩家和莊家 
	 		for ( Player x : players)		//發牌給每位玩家 
	 		{ 
	 			x.setOneRoundCard(AllCards.getOneCard(true)); 
	 			x.setOneRoundCard(AllCards.getOneCard(true)); 
	 		} 
	 		dealer.setOneRoundCard(AllCards.getOneCard(false));	//發一張暗牌給莊家 
	 		dealer.setOneRoundCard(AllCards.getOneCard(true));	//發一張亮牌給莊家 
	 		face_up_card_of_dealer = dealer.getOneRoundCard().get(1);	//儲存莊家的第二張牌 
	 		System.out.print("Dealer's face up card is "); 
	 		dealer.getOneRoundCard().get(1).printCard(); 
	 	} 
	 	 
	 	private void ask_each_player_about_hits(){	//問每個玩家還要不要牌 
	 		for (Player x : players) 
	 		{ 
	 			System.out.println(x.get_name()+"'s Cards now:"); 
	 			x.printAllCard(); 
	 			while ( x.hit_me(this))		//是否要加新的牌 
	 			{ 
	 				x.setOneRoundCard(AllCards.getOneCard(true));		//加新的牌進去 
	 				System.out.println("Hit! "+x.get_name()+"'s Cards now:"); 
	 				x.printAllCard(); 
	 			} 
	 			System.out.println("Pass hit!"); 
	 			System.out.println(x.get_name()+"'s hit is over!"); 
	 		} 
	 	} 
	 
	 	private void ask_dealer_about_hits(){		//問莊家要不要牌 
	 		while ( dealer.hit_me(this)) 
	 		{ 
	 			dealer.setOneRoundCard(AllCards.getOneCard(true));		//加新的牌進去 
	 		} 
	 		System.out.println("Dealer's hit is over!"); 
	 	} 
	 
	
	 	private void calculate_chips(){ 
	 		System.out.println("Dealer's card value is "+dealer.getTotalValue()+" ,Cards:"); 
	 		dealer.printAllCard(); 
	 		int m = dealer.getTotalValue(); 
	 		int n = 0; 
	 		for ( Player x : players) 
	 		{ 
	 			int o = x.getTotalValue(); 
	 			int b = pos_betArray[n]; 
	 			n++; 
	 			System.out.println(x.get_name()+"'s Cards: "); 
	 			x.printAllCard(); 
	 			System.out.print(x.get_name()+" card value is "+x.getTotalValue()); 
	 			//跟莊家比較牌 
	 			if ( m >21	&&  o>21)		//兩個都爆了 
	 			{ 
	 				System.out.println(", chips have no change! The Chips now is: "+x.get_current_chips()); 
	 			} 
	 			else if ( m>21 && o<=21)		//玩家贏了 
	 			{ 
	 				x.increase_chips(b); 
	 				System.out.println(", Get "+b+"Chips, the Chips now is: "+ x.get_current_chips()); 
	 			} 
	 			else if ( m<=21 && o>21)		//玩家輸了 
	 			{ 
	 				x.increase_chips(-b); 
	 				System.out.println(", Loss "+b+"Chips, the Chips now is: "+ x.get_current_chips()); 
	 			} 
	 			else if ( m<=21 && o<=21)		//兩個都沒爆 
	 			{ 
	 				if ( m > o)		//玩家輸了 
	 				{ 
	 					x.increase_chips(-b); 
	 					System.out.println(", Loss "+b+"Chips, the Chips now is: "+ x.get_current_chips()); 
	 				} 
	 				else if ( m < o)		//玩家贏了 
	 				{ 
	 					x.increase_chips(b); 
	 					System.out.println(", Get "+b+"Chips, the Chips now is: "+ x.get_current_chips()); 
	 				} 
	 				else if ( m == o)	//兩者相等 
	 				{ 
	 					System.out.println(", chips have no change! The Chips now is: "+x.get_current_chips()); 
	 				} 
				} 
	 		} 
	 	} 
	 
	 	public int[] get_palyers_bet(){ 
	 		return pos_betArray; 
	 	} 
	 	 
	 	public void play(){ 
	 		ask_each_player_about_bets();						//問籌碼 
	 		distribute_cards_to_dealer_and_players();		//發牌 
	 		ask_each_player_about_hits();						//問玩家要不要加牌 
	 		ask_dealer_about_hits();								//問莊家要不要加牌 
	 		calculate_chips();											// 
	 	} 
}
