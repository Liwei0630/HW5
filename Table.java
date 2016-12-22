package hw5;

public class Table {

	static final int MAXPLAYER = 4; 
	 	private Deck AllCards;	//�s��Ҧ����P 
	 	private Player[] players ;		//�s��Ҧ����a 
	 	private Dealer dealer;		//�s��@�Ӳ��a 
	 	int[] pos_betArray ;		//�s��C�Ӫ��a�@�����U�` 
	 	private Card face_up_card_of_dealer; 
	 	 
	 	public Table ( int nDeck){ 
	 		AllCards = new Deck(nDeck); 
	 		players = new Player[MAXPLAYER]; 
	 	} 
	 	 
	 	public void set_player(int pos, Player p){ 
	 		players[pos] = p; 
	 	} 
	 	 
	 	public Player[] get_player(){		//�^�ǩҦ���Player 
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
	 			pos_betArray[n] = x.bet;	//�x�s�C�Ӫ��a���U�` 
	 			n++; 
	 		} 
	 	} 
	 	 
	 	private void distribute_cards_to_dealer_and_players(){		//�o�P�����a�M���a 
	 		for ( Player x : players)		//�o�P���C�쪱�a 
	 		{ 
	 			x.setOneRoundCard(AllCards.getOneCard(true)); 
	 			x.setOneRoundCard(AllCards.getOneCard(true)); 
	 		} 
	 		dealer.setOneRoundCard(AllCards.getOneCard(false));	//�o�@�i�t�P�����a 
	 		dealer.setOneRoundCard(AllCards.getOneCard(true));	//�o�@�i�G�P�����a 
	 		face_up_card_of_dealer = dealer.getOneRoundCard().get(1);	//�x�s���a���ĤG�i�P 
	 		System.out.print("Dealer's face up card is "); 
	 		dealer.getOneRoundCard().get(1).printCard(); 
	 	} 
	 	 
	 	private void ask_each_player_about_hits(){	//�ݨC�Ӫ��a�٭n���n�P 
	 		for (Player x : players) 
	 		{ 
	 			System.out.println(x.get_name()+"'s Cards now:"); 
	 			x.printAllCard(); 
	 			while ( x.hit_me(this))		//�O�_�n�[�s���P 
	 			{ 
	 				x.setOneRoundCard(AllCards.getOneCard(true));		//�[�s���P�i�h 
	 				System.out.println("Hit! "+x.get_name()+"'s Cards now:"); 
	 				x.printAllCard(); 
	 			} 
	 			System.out.println("Pass hit!"); 
	 			System.out.println(x.get_name()+"'s hit is over!"); 
	 		} 
	 	} 
	 
	 	private void ask_dealer_about_hits(){		//�ݲ��a�n���n�P 
	 		while ( dealer.hit_me(this)) 
	 		{ 
	 			dealer.setOneRoundCard(AllCards.getOneCard(true));		//�[�s���P�i�h 
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
	 			//����a����P 
	 			if ( m >21	&&  o>21)		//��ӳ��z�F 
	 			{ 
	 				System.out.println(", chips have no change! The Chips now is: "+x.get_current_chips()); 
	 			} 
	 			else if ( m>21 && o<=21)		//���aĹ�F 
	 			{ 
	 				x.increase_chips(b); 
	 				System.out.println(", Get "+b+"Chips, the Chips now is: "+ x.get_current_chips()); 
	 			} 
	 			else if ( m<=21 && o>21)		//���a��F 
	 			{ 
	 				x.increase_chips(-b); 
	 				System.out.println(", Loss "+b+"Chips, the Chips now is: "+ x.get_current_chips()); 
	 			} 
	 			else if ( m<=21 && o<=21)		//��ӳ��S�z 
	 			{ 
	 				if ( m > o)		//���a��F 
	 				{ 
	 					x.increase_chips(-b); 
	 					System.out.println(", Loss "+b+"Chips, the Chips now is: "+ x.get_current_chips()); 
	 				} 
	 				else if ( m < o)		//���aĹ�F 
	 				{ 
	 					x.increase_chips(b); 
	 					System.out.println(", Get "+b+"Chips, the Chips now is: "+ x.get_current_chips()); 
	 				} 
	 				else if ( m == o)	//��̬۵� 
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
	 		ask_each_player_about_bets();						//���w�X 
	 		distribute_cards_to_dealer_and_players();		//�o�P 
	 		ask_each_player_about_hits();						//�ݪ��a�n���n�[�P 
	 		ask_dealer_about_hits();								//�ݲ��a�n���n�[�P 
	 		calculate_chips();											// 
	 	} 
}
