package controllers;

import java.util.Random;

public class test {
	mport java.util.Random;

	public class GameMaster {
		private static DiceBag bag = new DiceBag();
		private static Hero currentHero;
		private static Orc currentOrc;
		private static Troll currentTroll;
		private static Dragon currentDragon;
	    private static int first;
	    private static int last;
	    private static int round = 0;
	 public static void run() {
		 boolean playAgain = true;
		 createHero();
		 promptForName();
		 do {
			 playAgain = true;
		int choice = promptForFight();
		if(choice == 1) {
			int counter = 1;
			boolean stillPlaying = true;
			createOrc();
			int initiative = 0;
			do {
				if(!currentHero.isAlive()){
					currentHero.setCurrentHP(currentHero.getCurrentHP());
					currentHero.setCurrentMP(currentHero.getCurrentMP());
				}
				stillPlaying = true;
				if(round % 5 == 0) {
					initiative = rollInitiative();
				}
				else if (round == 0) {
			 initiative = rollInitiative();
				}
			if(initiative == first) {
				
				 if(counter == 1) {
					 System.out.println("You go first.");
				 }
				 counter = 0;
				heroTurnOrc();
				if(currentOrc.isAlive() == false) {
					stillPlaying = false;
				}
				orcTurn();
				round++;
			}
			 else if(initiative == last) {
				  
				 if(counter == 1) {
					 System.out.println("The enemy goes first.");
				 }
				 counter = 0;
				 orcTurn();
				 if (currentHero.isAlive() == false) {
						stillPlaying = false;
				 }
				 heroTurnOrc();
				 round++;
			}
			if(currentOrc.isAlive() == false) {
				stillPlaying = false;
			}
			else if (currentHero.isAlive() == false) {
				stillPlaying = false;
			}
			}while(stillPlaying);
			playAgain = ConsoleIO.promptForBool("Would you like to play again? Enter yes or no","yes", "no");
		}
		else if (choice == 2) {
			int counter = 1;
			boolean stillPlaying = true;
			createTroll();
			int initiative = 0;
			do {
				stillPlaying = true;
				if(round % 5 == 0) {
					initiative = rollInitiative();
				}
				else if (round == 0) {
			 initiative = rollInitiative();
				}
			if(initiative == first) {
				
				 if(counter == 1) {
					 System.out.println("You go first.");
				 }
				 counter = 0;
				heroTurnTroll();
				if(currentTroll.isAlive() == false) {
					stillPlaying = false;
				}
				trollTurn();
				round++;
			}
			 else if(initiative == last) {
				  
				 if(counter == 1) {
					 System.out.println("The enemy goes first.");
				 }
				 counter = 0;
				 trollTurn();
				 if (currentHero.isAlive() == false) {
						stillPlaying = false;
				 }
				 heroTurnTroll();
				 round++;
			}
			if(currentTroll.isAlive() == false) {
				stillPlaying = false;
			}
			else if (currentHero.isAlive() == false) {
				stillPlaying = false;
			}
			}while(stillPlaying);
			playAgain = ConsoleIO.promptForBool("Would you like to play again? Enter yes or no","yes", "no");
		}
		else {
			int counter = 1;
			boolean stillPlaying = true;
			createDragon();
			int initiative = 0;
			do {
				stillPlaying = true;
				if(round % 5 == 0) {
					initiative = rollInitiative();
				}
				else if (round == 0) {
			 initiative = rollInitiative();
				}
			if(initiative == first) {
				
				 if(counter == 1) {
					 System.out.println("You go first.");
				 }
				 counter = 0;
				heroTurnDragon();
				if(currentDragon.isAlive() == false) {
					stillPlaying = false;
				}
				dragonTurn();
				round++;
			}
			 else if(initiative == last) {
				  
				 if(counter == 1) {
					 System.out.println("The enemy goes first.");
				 }
				 counter = 0;
				 dragonTurn();
				 if (currentHero.isAlive() == false) {
						stillPlaying = false;
				 }
				 heroTurnDragon();
				 round++;
			}
			if(currentDragon.isAlive() == false) {
				stillPlaying = false;
			}
			else if (currentHero.isAlive() == false) {
				stillPlaying = false;
			}
			}while(stillPlaying);
			playAgain = ConsoleIO.promptForBool("Would you like to play again? Enter yes or no","yes", "no");
		}
		 }while(playAgain);
	 }
	 
	 private static int rollInitiative() {
		  bag = new DiceBag();
		 boolean rollAgain = false;
		 do {
			 rollAgain = false;
		 int rollHero = bag.roll(1,20,0);
		 int rollMonster = bag.roll(1,20,0);
		  first = 1;
		  last = 2;
		 if(rollHero > rollMonster) {
			 return first;
		 }
		 else if(rollHero == rollMonster){
			 rollAgain = true;
			 return 0;
		 }
		 else {
			 return last;
		 }
		 }while(rollAgain);
	 }
	 
	 private static void orcTurn() {
		 Random rng = new Random();
		 if(currentOrc.getCurrentMP() > 10) {
			 int randNum = rng.nextInt(10)+1;
			 if (randNum >= 4) {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
				 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
				 
				 if(heroRoll == 20 && orcRoll == 20) {
					 if(heroTotal < orcTotal) {
						 int damage = currentOrc.attackNormal();
						 int crit = damage * 2;
						 currentHero.receiveDamage(crit);
						 System.out.println("You took " + crit + " critical damage.");
					 }
					 else {
						 
					 }
				 }
				 else if(heroRoll != 20 && orcRoll == 20) {
					 int damage = currentOrc.attackNormal();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 else if(orcRoll != 20 && heroRoll == 20) {
					 System.out.println("The attack missed.");
					
				 }
				 else if(orcRoll == 1) {
					 System.out.println("The attack missed.");
				 }
				 else if(orcRoll == 20 && heroRoll == 1) {
					 int damage = currentOrc.attackNormal();
					 int quad = damage * 4;
					 currentHero.receiveDamage(quad);
					 System.out.println("You took " + quad + " quad damage.");
				 }
				 else if(heroRoll == 1 && orcRoll != 1) {
					 int damage = currentOrc.attackNormal();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal < orcTotal) {
					 int damage = currentOrc.attackNormal();
					 currentHero.receiveDamage(damage);
					 System.out.println("You took " + damage + " damage.");
					 
				 }
				  
				 else if (heroTotal > orcTotal){
					 System.out.println("The attack missed.");
				 }
			 }
			 else {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
				 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
				 
				 if(heroRoll == 20 && orcRoll == 20) {
					 if(heroTotal < orcTotal) {
						 int damage = currentOrc.attackSpecial();
						 int crit = damage * 2;
						 currentHero.receiveDamage(crit);
						 System.out.println("You took " + crit + " critical damage.");
					 }
					 else {
						 
					 }
				 }
				 else if(heroRoll != 20 && orcRoll == 20) {
					 int damage = currentOrc.attackSpecial();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 else if(orcRoll != 20 && heroRoll == 20) {
					 System.out.println("The attack missed.");
					 currentOrc.setCurrentMP(currentOrc.getCurrentMP() - 10);
				 }
				 else if(orcRoll == 1) {
					 System.out.println("The attack missed.");
					 currentOrc.setCurrentMP(currentOrc.getCurrentMP() - 10);
				 }
				 else if(orcRoll == 20 && heroRoll == 1) {
					 int damage = currentOrc.attackSpecial();
					 int quad = damage * 4;
					 currentHero.receiveDamage(quad);
					 System.out.println("You took " + quad + " quad damage.");
				 }
				 else if(heroRoll == 1 && orcRoll != 1) {
					 int damage = currentOrc.attackSpecial();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal < orcTotal) {
					 int damage = currentOrc.attackSpecial();
					 currentHero.receiveDamage(damage);
					 System.out.println("You took " + damage + " damage.");
					 
				 }
				  
				 else if (heroTotal > orcTotal){
					 System.out.println("The attack missed.");
					 currentOrc.setCurrentMP(currentOrc.getCurrentMP() - 10);
				 }
			 }
			 
		 }
		 else {
			 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
			 int heroRoll = heroTotal - currentHero.getStrikeBonus();
			 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
			 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
			 
			 if(heroRoll == 20 && orcRoll == 20) {
				 if(heroTotal < orcTotal) {
					 int damage = currentOrc.attackNormal();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 else {
					 
				 }
			 }
			 else if(heroRoll != 20 && orcRoll == 20) {
				 int damage = currentOrc.attackNormal();
				 int crit = damage * 2;
				 currentHero.receiveDamage(crit);
				 System.out.println("You took " + crit + " critical damage.");
			 }
			 else if(orcRoll != 20 && heroRoll == 20) {
				 System.out.println("The attack missed.");
			 }
			 else if(orcRoll == 1) {
				 System.out.println("The attack missed.");
			 }
			 else if(orcRoll == 20 && heroRoll == 1) {
				 int damage = currentOrc.attackNormal();
				 int quad = damage * 4;
				 currentHero.receiveDamage(quad);
				 System.out.println("You took " + quad + " quad damage.");
			 }
			 else if(heroRoll == 1 && orcRoll != 1) {
				 int damage = currentOrc.attackNormal();
				 int crit = damage * 2;
				 currentHero.receiveDamage(crit);
				 System.out.println("You took " + crit + " critical damage.");
			 }
			 
			 else if(heroTotal < orcTotal) {
				 int damage = currentOrc.attackNormal();
				 currentHero.receiveDamage(damage);
				 System.out.println("You took " + damage + " damage.");
				 
			 }
			  
			 else if (heroTotal > orcTotal){
				 System.out.println("The attack missed.");
				
			 }
			 
		 }
		 if (currentOrc.isAlive() == false) {
			 Random rng1 = new Random();
			 int num =rng1.nextInt(4)+1;
			int rand ;
			for(int i = 0; i < num; i++) {
				 rand = rng1.nextInt(2)+1;
				if(rand == 1) {
					HealthPotion potion = new HealthPotion();
					currentHero.receiveHealthPotion(potion);
					System.out.println("You received a health potion");
				}
				 else if (rand == 2) {
					MagicPotion potion = new MagicPotion();
					currentHero.receiveMagicPotion(potion);
					System.out.println("You received a magic potion");
				}
			}
			 
		 }
	 }
	 
	 private static void trollTurn() {
		 Random rng = new Random();
		 if(currentOrc.getCurrentMP() > 10) {
			 int randNum = rng.nextInt(10)+1;
			 if (randNum >= 4) {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int trollTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
				 int trollRoll = trollTotal - currentTroll.getDodgeBonus();
				 
				 if(heroRoll == 20 && trollRoll == 20) {
					 if(heroTotal < trollTotal) {
						 int damage = currentTroll.attackNormal();
						 int crit = damage * 2;
						 currentHero.receiveDamage(crit);
						 System.out.println("You took " + crit + " critical damage.");
					 }
					 else {
						 
					 }
				 }
				 else if(heroRoll != 20 && trollRoll == 20) {
					 int damage = currentTroll.attackNormal();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 else if(trollRoll != 20 && heroRoll == 20) {
					 System.out.println("The attack missed.");
				 }
				 else if(trollRoll == 1) {
					 System.out.println("The attack missed.");
				 }
				 else if(trollRoll == 20 && heroRoll == 1) {
					 int damage = currentTroll.attackNormal();
					 int quad = damage * 4;
					 currentHero.receiveDamage(quad);
					 System.out.println("You took " + quad + " quad damage.");
				 }
				 else if(heroRoll == 1 && trollRoll != 1) {
					 int damage = currentTroll.attackNormal();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal < trollTotal) {
					 int damage = currentTroll.attackNormal();
					 currentHero.receiveDamage(damage);
					 System.out.println("You took " + damage + " damage.");
					 
				 }
				  
				 else if (heroTotal > trollTotal){
					 System.out.println("The attack missed.");
				 }
			 }
			 else {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int trollTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
				 int trollRoll = trollTotal - currentTroll.getDodgeBonus();
				 
				 if(heroRoll == 20 && trollRoll == 20) {
					 if(heroTotal < trollTotal) {
						 int damage = currentOrc.attackSpecial();
						 int crit = damage * 2;
						 currentHero.receiveDamage(crit);
						 System.out.println("You took " + crit + " critical damage.");
					 }
					 else {
						 
					 }
				 }
				 else if(heroRoll != 20 && trollRoll == 20) {
					 int damage = currentTroll.attackSpecial();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 else if(trollRoll != 20 && heroRoll == 20) {
					 System.out.println("The attack missed.");
					 currentTroll.setCurrentMP(currentTroll.getCurrentMP() -10);
				 }
				 else if(trollRoll == 1) {
					 System.out.println("The attack missed.");
					 currentTroll.setCurrentMP(currentTroll.getCurrentMP() -10);
				 }
				 else if(trollRoll == 20 && heroRoll == 1) {
					 int damage = currentTroll.attackSpecial();
					 int quad = damage * 4;
					 currentHero.receiveDamage(quad);
					 System.out.println("You took " + quad + " quad damage.");
				 }
				 else if(heroRoll == 1 && trollRoll != 1) {
					 int damage = currentTroll.attackSpecial();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal < trollTotal) {
					 int damage = currentTroll.attackSpecial();
					 currentHero.receiveDamage(damage);
					 System.out.println("You took " + damage + " damage.");
					 
				 }
				  
				 else if (heroTotal > trollTotal){
					 System.out.println("The attack missed.");
					 currentTroll.setCurrentMP(currentTroll.getCurrentMP() -10);
				 }
			 }
			 
		 }
		 else {
			 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
			 int heroRoll = heroTotal - currentHero.getStrikeBonus();
			 int orcTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
			 int orcRoll = orcTotal - currentTroll.getDodgeBonus();
			 
			 if(heroRoll == 20 && orcRoll == 20) {
				 if(heroTotal < orcTotal) {
					 int damage = currentTroll.attackNormal();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 else {
					 
				 }
			 }
			 else if(heroRoll != 20 && orcRoll == 20) {
				 int damage = currentTroll.attackNormal();
				 int crit = damage * 2;
				 currentHero.receiveDamage(crit);
				 System.out.println("You took " + crit + " critical damage.");
			 }
			 else if(orcRoll != 20 && heroRoll == 20) {
				 System.out.println("The attack missed.");
			 }
			 else if(orcRoll == 1) {
				 System.out.println("The attack missed.");
			 }
			 else if(orcRoll == 20 && heroRoll == 1) {
				 int damage = currentTroll.attackNormal();
				 int quad = damage * 4;
				 currentHero.receiveDamage(quad);
				 System.out.println("You took " + quad + " quad damage.");
			 }
			 else if(heroRoll == 1 && orcRoll != 1) {
				 int damage = currentTroll.attackNormal();
				 int crit = damage * 2;
				 currentHero.receiveDamage(crit);
				 System.out.println("You took " + crit + " critical damage.");
			 }
			 
			 else if(heroTotal < orcTotal) {
				 int damage = currentTroll.attackNormal();
				 currentHero.receiveDamage(damage);
				 System.out.println("You took " + damage + " damage.");
				 
			 }
			  
			 else if (heroTotal > orcTotal){
				 System.out.println("The attack missed.");
			 }
			 
		 }
		 if (currentTroll.isAlive() == false) {
			 Random rng1 = new Random();
			 int num =rng1.nextInt(4)+1;
			int rand ;
			for(int i = 0; i < num; i++) {
				 rand = rng1.nextInt(2)+1;
				if(rand == 1) {
					HealthPotion potion = new HealthPotion();
					currentHero.receiveHealthPotion(potion);
					System.out.println("You received a health potion");
				}
				 else if (rand == 2) {
					MagicPotion potion = new MagicPotion();
					currentHero.receiveMagicPotion(potion);
					System.out.println("You received a magic potion");
				}
			}
		 }
	 }
	 
	 
	 private static void dragonTurn() {
		 Random rng = new Random();
		 if(currentDragon.getCurrentMP() > 10) {
			 int randNum = rng.nextInt(10)+1;
			 if (randNum >= 4) {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int dragTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
				 int dragRoll = dragTotal - currentDragon.getDodgeBonus();
				 
				 if(heroRoll == 20 && dragRoll == 20) {
					 if(heroTotal < dragTotal) {
						 int damage = currentDragon.attackNormal();
						 int crit = damage * 2;
						 currentHero.receiveDamage(crit);
						 System.out.println("You took " + crit + " critical damage.");
					 }
					 else {
						 System.out.println("The attack missed.");
					 }
				 }
				 else if(heroRoll != 20 && dragRoll == 20) {
					 int damage = currentDragon.attackNormal();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 else if(dragRoll != 20 && heroRoll == 20) {
					 System.out.println("The attack missed.");
				 }
				 else if(dragRoll == 1) {
					 System.out.println("The attack missed.");
				 }
				 else if(dragRoll == 20 && heroRoll == 1) {
					 int damage = currentDragon.attackNormal();
					 int quad = damage * 4;
					 currentHero.receiveDamage(quad);
					 System.out.println("You took " + quad + " quad damage.");
				 }
				 else if(heroRoll == 1 && dragRoll != 1) {
					 int damage = currentDragon.attackNormal();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal < dragTotal) {
					 int damage = currentDragon.attackNormal();
					 currentHero.receiveDamage(damage);
					 System.out.println("You took " + damage + " damage.");
					 
				 }
				  
				 else if (heroTotal > dragTotal){
					 System.out.println("The attack missed.");
				 }
			 }
			 else {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int dragTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
				 int dragRoll = dragTotal - currentDragon.getDodgeBonus();
				 
				 if(heroRoll == 20 && dragRoll == 20) {
					 if(heroTotal < dragTotal) {
						 int damage = currentDragon.attackSpecial();
						 int crit = damage * 2;
						 currentHero.receiveDamage(crit);
						 System.out.println("You took " + crit + " critical damage.");
					 }
					 else {
						 System.out.println("Your attack missed.");
						 currentDragon.setCurrentMP(currentDragon.getCurrentMP() - 10);
					 }
				 }
				 else if(heroRoll != 20 && dragRoll == 20) {
					 int damage = currentDragon.attackSpecial();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 else if(dragRoll != 20 && heroRoll == 20) {
					 System.out.println("The attack missed.");
					 currentDragon.setCurrentMP(currentDragon.getCurrentMP() - 10);
				 }
				 else if(dragRoll == 1) {
					 System.out.println("The attack missed.");
					 currentDragon.setCurrentMP(currentDragon.getCurrentMP() - 10);
				 }
				 else if(dragRoll == 20 && heroRoll == 1) {
					 int damage = currentDragon.attackSpecial();
					 int quad = damage * 4;
					 currentHero.receiveDamage(quad);
					 System.out.println("You took " + quad + " quad damage.");
				 }
				 else if(heroRoll == 1 && dragRoll != 1) {
					 int damage = currentDragon.attackSpecial();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal < dragTotal) {
					 int damage = currentDragon.attackSpecial();
					 currentHero.receiveDamage(damage);
					 System.out.println("You took " + damage + " damage.");
					 
				 }
				  
				 else if (heroTotal > dragTotal){
					 System.out.println("The attack missed.");
				 
					 currentDragon.setCurrentMP(currentDragon.getCurrentMP() - 10);
				 }
			 }
			 
		 }
		 else {
			 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
			 int heroRoll = heroTotal - currentHero.getStrikeBonus();
			 int dragTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
			 int dragRoll = dragTotal - currentDragon.getDodgeBonus();
			 
			 if(heroRoll == 20 && dragRoll == 20) {
				 if(heroTotal < dragTotal) {
					 int damage = currentDragon.attackNormal();
					 int crit = damage * 2;
					 currentHero.receiveDamage(crit);
					 System.out.println("You took " + crit + " critical damage.");
				 }
				 else {
					 
				 }
			 }
			 else if(heroRoll != 20 && dragRoll == 20) {
				 int damage = currentDragon.attackNormal();
				 int crit = damage * 2;
				 currentHero.receiveDamage(crit);
				 System.out.println("You took " + crit + " critical damage.");
			 }
			 else if(dragRoll != 20 && heroRoll == 20) {
				 System.out.println("The attack missed.");
			 }
			 else if(dragRoll == 1) {
				 System.out.println("The attack missed.");
			 }
			 else if(dragRoll == 20 && heroRoll == 1) {
				 int damage = currentDragon.attackNormal();
				 int quad = damage * 4;
				 currentHero.receiveDamage(quad);
				 System.out.println("You took " + quad + " quad damage.");
			 }
			 else if(heroRoll == 1 && dragRoll != 1) {
				 int damage = currentDragon.attackNormal();
				 int crit = damage * 2;
				 currentHero.receiveDamage(crit);
				 System.out.println("You took " + crit + " critical damage.");
			 }
			 
			 else if(heroTotal < dragTotal) {
				 int damage = currentDragon.attackNormal();
				 currentHero.receiveDamage(damage);
				 System.out.println("You took " + damage + " damage.");
				 
			 }
			  
			 else if (heroTotal > dragTotal){
				 System.out.println("The attack missed.");
			 }
			 
		 }
		 if (currentDragon.isAlive() == false) {
			 Random rng1 = new Random();
			 int num =rng1.nextInt(4)+1;
			int rand ;
			for(int i = 0; i < num; i++) {
				 rand = rng1.nextInt(2)+1;
				if(rand == 1) {
					HealthPotion potion = new HealthPotion();
					currentHero.receiveHealthPotion(potion);
					System.out.println("You received a health potion");
				}
				 else if (rand == 2) {
					MagicPotion potion = new MagicPotion();
					currentHero.receiveMagicPotion(potion);
					System.out.println("You received a magic potion");
				}
			}
			 
		 }
	 }
	 
	 private static void heroTurnOrc(){
		 String[] menu = {"Perform Normal Attack","Perform Special Attack",};
		 String[] menu1 = {"Perform Normal Attack","Perform Special Attack", "Use Health Potion"};
		 String[] menu2 = {"Perform Normal Attack","Perform Special Attack", "Use Magic Potion"};
		 String[] menu3 = {"Perform Normal Attack","Perform Special Attack", "Use Health Potion","Use Magic Potion"};
		int hPotions = currentHero.getNumOfHPotions();
		int mPotions = currentHero.getNumOfMPotions();
		int mana = currentHero.getCurrentMP();
		boolean notEnoughMana = false;
		int x = 1;
		if(hPotions == 0 && mPotions == 0) {
			do {
				notEnoughMana = false;
				System.out.println(currentHero.getStats());
				System.out.println(currentOrc);
			 x = ConsoleIO.promptForMenuSelection(menu, false);
			 if(x == 1) {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
				 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
				 
				 if(heroRoll == 20 && orcRoll == 20) {
					 if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 else {
						 System.out.println("Your attack missed.");
					 }
				 }
				 else if(heroRoll == 20 && orcRoll != 20) {
					 int damage = currentHero.attackNormal();
					 int crit = damage * 2;
					 currentOrc.receiveDamage(crit);
					 System.out.println("The orc took " + crit + " critical damage.");
				 }
				 else if(orcRoll == 20 && heroRoll != 20) {
					 System.out.println("Your attack missed.");
				 }
				 else if(heroRoll == 1) {
					 System.out.println("Your attack missed.");
				 }
				 else if(heroRoll == 20 && orcRoll == 1) {
					 int damage = currentHero.attackNormal();
					 int quad = damage * 4;
					 currentOrc.receiveDamage(quad);
					 System.out.println("The orc took " + quad + " quad damage.");
				 }
				 else if(heroRoll != 1 && orcRoll == 1) {
					 int damage = currentHero.attackNormal();
					 int crit = damage * 2;
					 currentOrc.receiveDamage(crit);
					 System.out.println("The orc took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal > orcTotal) {
					 int damage = currentHero.attackNormal();
					 currentOrc.receiveDamage(damage);
					 System.out.println("The orc took " + damage + " damage.");
					 
				 }
				  
				 else {
					 System.out.println("Your attack missed.");
				 }
				
			 }
			 else if(x == 2 && mana > 10) {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
				 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
				 
				 if(heroRoll == 20 && orcRoll == 20) {
					 if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 else {
						 System.out.println("Your attack missed.");
					 }
				 }
				 else if(heroRoll == 20 && orcRoll != 20) {
					 int damage = currentHero.attackSpecial();
					 int crit = damage * 2;
					 currentOrc.receiveDamage(crit);
					 System.out.println("The orc took " + crit + " critical damage.");
				 }
				 else if(orcRoll == 20 && heroRoll != 20) {
					 System.out.println("Your attack missed.");
					 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
				 }
				 else if(heroRoll == 1) {
					 System.out.println("Your attack missed.");
					 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
				 }
				 else if(heroRoll == 20 && orcRoll == 1) {
					 int damage = currentHero.attackSpecial();
					 int quad = damage * 4;
					 currentOrc.receiveDamage(quad);
					 System.out.println("The orc took " + quad + " quad damage.");
				 }
				 else if(heroRoll != 1 && orcRoll == 1) {
					 int damage = currentHero.attackSpecial();
					 int crit = damage * 2;
					 currentOrc.receiveDamage(crit);
					 System.out.println("The orc took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal > orcTotal) {
					 int damage = currentHero.attackSpecial();
					 currentOrc.receiveDamage(damage);
					 System.out.println("The orc took " + damage + " damage.");
					 
				 }
				  
				 else {
					 System.out.println("Your attack missed.");
					 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
				 }
			 }
			 else if(x == 2 && mana < 10) {
				 System.out.println("You don't have enough MP");
				 notEnoughMana = true;
			 }
			}while(notEnoughMana);
		}
		///////////////////////////////menu1////////////////////////////////////////////////////////////
		 else if(hPotions != 0 && mPotions == 0) {
			 do {
					notEnoughMana = false;
					System.out.println(currentHero.getStats());
					System.out.println(currentOrc);
				 x = ConsoleIO.promptForMenuSelection(menu1, false);
				 if(x == 1) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
					 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackNormal();
							 int crit = damage * 2;
							 currentOrc.receiveDamage(crit);
							 System.out.println("The orc took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int quad = damage * 4;
						 currentOrc.receiveDamage(quad);
						 System.out.println("The orc took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 currentOrc.receiveDamage(damage);
						 System.out.println("The orc took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 
					 }
					
				 }
				 else if(x == 2 && mana > 10) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
					 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackSpecial();
							 int crit = damage * 2;
							 currentOrc.receiveDamage(crit);
							 System.out.println("The orc took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int quad = damage * 4;
						 currentOrc.receiveDamage(quad);
						 System.out.println("The orc took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						 currentOrc.receiveDamage(damage);
						 System.out.println("The orc took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
				 }
				 else if(x == 2 && mana < 10) {
					 System.out.println("You don't have enough MP");
					 notEnoughMana = true;
				 }
				 else if ( x == 3) {
					 int hp = currentHero.useHealthPotion();
					 currentHero.setCurrentHP(currentHero.getCurrentHP() + hp);
					 if(currentHero.getCurrentHP() >= currentHero.getBaseHP()) {
						 currentHero.setCurrentHP(currentHero.getBaseHP());
					 }
				 }
				}while(notEnoughMana);
			  
		 }
		//////////////////////////////menu2///////////////////////////////////////////////////////////////
		 else if(hPotions == 0 && mPotions != 0) {
			 do {
					notEnoughMana = false;
					System.out.println(currentHero.getStats());
					System.out.println(currentOrc);
				 x = ConsoleIO.promptForMenuSelection(menu2, false);
				 if(x == 1) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
					 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackNormal();
							 int crit = damage * 2;
							 currentOrc.receiveDamage(crit);
							 System.out.println("The orc took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int quad = damage * 4;
						 currentOrc.receiveDamage(quad);
						 System.out.println("The orc took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 currentOrc.receiveDamage(damage);
						 System.out.println("The orc took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 
					 }
					
				 }
				 else if(x == 2 && mana > 10) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
					 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackSpecial();
							
							 int crit = damage * 2;
							 currentOrc.receiveDamage(crit);
							 System.out.println("The orc took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackSpecial();
						
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 
						 int quad = damage * 4;
						 currentOrc.receiveDamage(quad);
						 System.out.println("The orc took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						
						 currentOrc.receiveDamage(damage);
						 System.out.println("The orc took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
				 }
				 else if(x == 2 && mana < 10) {
					 System.out.println("You don't have enough MP");
					 notEnoughMana = true;
				 }
				 else if ( x == 3) {
				int	hp =  currentHero.useMagicPotion();
					 currentHero.setCurrentMP(currentHero.getCurrentMP() + hp);
					 if(currentHero.getCurrentMP() >= currentHero.getBaseMP()) {
						 currentHero.setCurrentMP(currentHero.getBaseMP());
					 }
				 }
				}while(notEnoughMana);
		 }
		 else {
			 do {
					notEnoughMana = false;
					System.out.println(currentHero.getStats());
					System.out.println(currentOrc);
				 x = ConsoleIO.promptForMenuSelection(menu3, false);
				 if(x == 1) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
					 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackNormal();
							 int crit = damage * 2;
							 currentOrc.receiveDamage(crit);
							 System.out.println("The orc took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int quad = damage * 4;
						 currentOrc.receiveDamage(quad);
						 System.out.println("The orc took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 currentOrc.receiveDamage(damage);
						 System.out.println("The orc took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
					 }
					
				 }
				 else if(x == 2 && mana > 10) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentOrc.getDodgeBonus();
					 int orcRoll = orcTotal - currentOrc.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackSpecial();
							
							 int crit = damage * 2;
							 currentOrc.receiveDamage(crit);
							 System.out.println("The orc took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackSpecial();
						
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
					
						 int quad = damage * 4;
						 currentOrc.receiveDamage(quad);
						 System.out.println("The orc took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						
						 int crit = damage * 2;
						 currentOrc.receiveDamage(crit);
						 System.out.println("The orc took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
					
						 currentOrc.receiveDamage(damage);
						 System.out.println("The orc took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
				 }
				 else if(x == 2 && mana < 10) {
					 System.out.println("You don't have enough MP");
					 notEnoughMana = true;
				 }
				 else if ( x == 3) {
				int hp =	 currentHero.useHealthPotion();
					 currentHero.setCurrentHP(currentHero.getCurrentHP() + hp);
					 if(currentHero.getCurrentHP() >= currentHero.getBaseHP()) {
						 currentHero.setCurrentHP(currentHero.getBaseHP());
					 }
				 }
				 else if ( x == 4) {
				int mp =	 currentHero.useMagicPotion();
				currentHero.setCurrentMP(currentHero.getCurrentMP() + mp);
				 if(currentHero.getCurrentMP() >= currentHero.getBaseMP()) {
					 currentHero.setCurrentMP(currentHero.getBaseMP());
				 }
				}
			 }while(notEnoughMana);
		
		 
		 
	 }
	 }

	 private static void heroTurnTroll(){
		 String[] menu = {"Perform Normal Attack","Perform Special Attack",};
		 String[] menu1 = {"Perform Normal Attack","Perform Special Attack", "Use Health Potion"};
		 String[] menu2 = {"Perform Normal Attack","Perform Special Attack", "Use Magic Potion"};
		 String[] menu3 = {"Perform Normal Attack","Perform Special Attack", "Use Health Potion","Use Magic Potion"};
		int hPotions = currentHero.getNumOfHPotions();
		int mPotions = currentHero.getNumOfMPotions();
		int mana = currentHero.getCurrentMP();
		boolean notEnoughMana = false;
		int x = 1;
		if(hPotions == 0 && mPotions == 0) {
			do {
				notEnoughMana = false;
				System.out.println(currentHero.getStats());
				System.out.println(currentTroll);
			 x = ConsoleIO.promptForMenuSelection(menu, false);
			 if(x == 1) {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int orcTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
				 int orcRoll = orcTotal - currentTroll.getDodgeBonus();
				 
				 if(heroRoll == 20 && orcRoll == 20) {
					 if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 else {
						 System.out.println("Your attack missed.");
					 }
				 }
				 else if(heroRoll == 20 && orcRoll != 20) {
					 int damage = currentHero.attackNormal();
					 int crit = damage * 2;
					 currentTroll.receiveDamage(crit);
					 System.out.println("The troll took " + crit + " critical damage.");
				 }
				 else if(orcRoll == 20 && heroRoll != 20) {
					 System.out.println("Your attack missed.");
				 }
				 else if(heroRoll == 1) {
					 System.out.println("Your attack missed.");
				 }
				 else if(heroRoll == 20 && orcRoll == 1) {
					 int damage = currentHero.attackNormal();
					 int quad = damage * 4;
					 currentTroll.receiveDamage(quad);
					 System.out.println("The troll took " + quad + " quad damage.");
				 }
				 else if(heroRoll != 1 && orcRoll == 1) {
					 int damage = currentHero.attackNormal();
					 int crit = damage * 2;
					 currentTroll.receiveDamage(crit);
					 System.out.println("The troll took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal > orcTotal) {
					 int damage = currentHero.attackNormal();
					 currentTroll.receiveDamage(damage);
					 System.out.println("The troll took " + damage + " damage.");
					 
				 }
				  
				 else {
					 System.out.println("Your attack missed.");
				 }
				
			 }
			 else if(x == 2 && mana > 10) {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int orcTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
				 int orcRoll = orcTotal - currentTroll.getDodgeBonus();
				 
				 if(heroRoll == 20 && orcRoll == 20) {
					 if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 else {
						 
					 }
				 }
				 else if(heroRoll == 20 && orcRoll != 20) {
					 int damage = currentHero.attackSpecial();
					 int crit = damage * 2;
					 currentTroll.receiveDamage(crit);
					 System.out.println("The troll took " + crit + " critical damage.");
				 }
				 else if(orcRoll == 20 && heroRoll != 20) {
					 System.out.println("Your attack missed.");
					 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
				 }
				 else if(heroRoll == 1) {
					 System.out.println("Your attack missed.");
					 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
				 }
				 else if(heroRoll == 20 && orcRoll == 1) {
					 int damage = currentHero.attackSpecial();
					 int quad = damage * 4;
					 currentTroll.receiveDamage(quad);
					 System.out.println("The troll took " + quad + " quad damage.");
				 }
				 else if(heroRoll != 1 && orcRoll == 1) {
					 int damage = currentHero.attackSpecial();
					 int crit = damage * 2;
					 currentTroll.receiveDamage(crit);
					 System.out.println("The troll took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal > orcTotal) {
					 int damage = currentHero.attackSpecial();
					 currentTroll.receiveDamage(damage);
					 System.out.println("The troll took " + damage + " damage.");
					 
				 }
				  
				 else {
					 System.out.println("Your attack missed.");
					 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
				 }
			 }
			 else if(x == 2 && mana < 10) {
				 System.out.println("You don't have enough MP");
				 notEnoughMana = true;
			 }
			}while(notEnoughMana);
		}
		///////////////////////////////menu1////////////////////////////////////////////////////////////
		 else if(hPotions != 0 && mPotions == 0) {
				do {
					notEnoughMana = false;
					System.out.println(currentHero.getStats());
					System.out.println(currentTroll);
				 x = ConsoleIO.promptForMenuSelection(menu1, false);
				 if(x == 1) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
					 int orcRoll = orcTotal - currentTroll.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackNormal();
							 int crit = damage * 2;
							 currentTroll.receiveDamage(crit);
							 System.out.println("The troll took " + crit + " critical damage.");
						 }
						 else {
							 
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int quad = damage * 4;
						 currentTroll.receiveDamage(quad);
						 System.out.println("The troll took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 currentTroll.receiveDamage(damage);
						 System.out.println("The troll took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
					 }
					
				 }
				 else if(x == 2 && mana > 10) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
					 int orcRoll = orcTotal - currentTroll.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackSpecial();
							 int crit = damage * 2;
							 currentTroll.receiveDamage(crit);
							 System.out.println("The troll took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int quad = damage * 4;
						 currentTroll.receiveDamage(quad);
						 System.out.println("The troll took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						 currentTroll.receiveDamage(damage);
						 System.out.println("The troll took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
				 }
				 else if(x == 2 && mana < 10) {
					 System.out.println("You don't have enough MP");
					 notEnoughMana = true;
				 }
				 else if(x == 3) {
					int hp = currentHero.useHealthPotion();
					 currentHero.setCurrentHP(currentHero.getCurrentHP() + hp);
				 }
				}while(notEnoughMana);
			  
		 }
		//////////////////////////////menu2///////////////////////////////////////////////////////////////
		 else if(hPotions == 0 && mPotions != 0) {
				do {
					notEnoughMana = false;
					System.out.println(currentHero.getStats());
					System.out.println(currentTroll);
				 x = ConsoleIO.promptForMenuSelection(menu2, false);
				 if(x == 1) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
					 int orcRoll = orcTotal - currentTroll.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackNormal();
							 int crit = damage * 2;
							 currentTroll.receiveDamage(crit);
							 System.out.println("The troll took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int quad = damage * 4;
						 currentTroll.receiveDamage(quad);
						 System.out.println("The troll took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 currentTroll.receiveDamage(damage);
						 System.out.println("The troll took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
					 }
					
				 }
				 else if(x == 2 && mana > 10) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
					 int orcRoll = orcTotal - currentTroll.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackSpecial();
							 int crit = damage * 2;
							 currentTroll.receiveDamage(crit);
							 System.out.println("The troll took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int quad = damage * 4;
						 currentTroll.receiveDamage(quad);
						 System.out.println("The troll took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						 currentTroll.receiveDamage(damage);
						 System.out.println("The troll took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
				 }
				 else if(x == 2 && mana < 10) {
					 System.out.println("You don't have enough MP");
					 notEnoughMana = true;
				 }
				 else if(x == 3) {
					int mp = currentHero.useMagicPotion();
					 currentHero.setCurrentMP(currentHero.getCurrentMP() + mp);
				 }
				}while(notEnoughMana);
		 }
		///////////////////////////////////////menu3/////////////////////////////////////////////////////////////
		 else {
				do {
					notEnoughMana = false;
					System.out.println(currentHero.getStats());
					System.out.println(currentTroll);
				 x = ConsoleIO.promptForMenuSelection(menu3, false);
				 if(x == 1) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
					 int orcRoll = orcTotal - currentTroll.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackNormal();
							 int crit = damage * 2;
							 currentTroll.receiveDamage(crit);
							 System.out.println("The troll took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int quad = damage * 4;
						 currentTroll.receiveDamage(quad);
						 System.out.println("The troll took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 currentTroll.receiveDamage(damage);
						 System.out.println("The troll took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
					 }
					
				 }
				 else if(x == 2 && mana > 10) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentTroll.getDodgeBonus();
					 int orcRoll = orcTotal - currentTroll.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackSpecial();
							 int crit = damage * 2;
							 currentTroll.receiveDamage(crit);
							 System.out.println("The troll took " + crit + " critical damage.");
						 }
						 else {
							 
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int quad = damage * 4;
						 currentTroll.receiveDamage(quad);
						 System.out.println("The troll took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentTroll.receiveDamage(crit);
						 System.out.println("The troll took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						 currentTroll.receiveDamage(damage);
						 System.out.println("The troll took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
				 }
				 else if(x == 2 && mana < 10) {
					 System.out.println("You don't have enough MP");
					 notEnoughMana = true;
				 }
				 else if(x == 3) {
					int hp = currentHero.useHealthPotion();
					 currentHero.setCurrentHP(currentHero.getCurrentHP() + hp);
				 }
				 else if (x==4) {
				int mp =	 currentHero.useMagicPotion();
					 currentHero.setCurrentMP(currentHero.getCurrentMP() + mp);
				 }
				}while(notEnoughMana);
		
		 
		 
	 }
	 }
	 
	 private static void heroTurnDragon(){
		 String[] menu = {"Perform Normal Attack","Perform Special Attack",};
		 String[] menu1 = {"Perform Normal Attack","Perform Special Attack", "Use Health Potion"};
		 String[] menu2 = {"Perform Normal Attack","Perform Special Attack", "Use Magic Potion"};
		 String[] menu3 = {"Perform Normal Attack","Perform Special Attack", "Use Health Potion","Use Magic Potion"};
		int hPotions = currentHero.getNumOfHPotions();
		int mPotions = currentHero.getNumOfMPotions();
		int mana = currentHero.getCurrentMP();
		boolean notEnoughMana = false;
		int x = 1;
		if(hPotions == 0 && mPotions == 0) {
			do {
				notEnoughMana = false;
				System.out.println(currentHero.getStats());
				System.out.println(currentDragon);
			 x = ConsoleIO.promptForMenuSelection(menu, false);
			 if(x == 1) {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int orcTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
				 int orcRoll = orcTotal - currentDragon.getDodgeBonus();
				 
				 if(heroRoll == 20 && orcRoll == 20) {
					 if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The dragon took " + crit + " critical damage.");
					 }
					 else {
						 System.out.println("Your attack missed.");
					 }
				 }
				 else if(heroRoll == 20 && orcRoll != 20) {
					 int damage = currentHero.attackNormal();
					 int crit = damage * 2;
					 currentDragon.receiveDamage(crit);
					 System.out.println("The Dragon took " + crit + " critical damage.");
				 }
				 else if(orcRoll == 20 && heroRoll != 20) {
					 System.out.println("Your attack missed.");
				 }
				 else if(heroRoll == 1) {
					 System.out.println("Your attack missed.");
				 }
				 else if(heroRoll == 20 && orcRoll == 1) {
					 int damage = currentHero.attackNormal();
					 int quad = damage * 4;
					 currentDragon.receiveDamage(quad);
					 System.out.println("The Dragon took " + quad + " quad damage.");
				 }
				 else if(heroRoll != 1 && orcRoll == 1) {
					 int damage = currentHero.attackNormal();
					 int crit = damage * 2;
					 currentDragon.receiveDamage(crit);
					 System.out.println("The Dragon took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal > orcTotal) {
					 int damage = currentHero.attackNormal();
					 currentDragon.receiveDamage(damage);
					 System.out.println("The Dragon took " + damage + " damage.");
					 
				 }
				  
				 else {
					 System.out.println("Your attack missed.");
				 }
				
			 }
			 else if(x == 2 && mana > 10) {
				 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
				 int heroRoll = heroTotal - currentHero.getStrikeBonus();
				 int orcTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
				 int orcRoll = orcTotal - currentDragon.getDodgeBonus();
				 
				 if(heroRoll == 20 && orcRoll == 20) {
					 if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 else {
						 System.out.println("Your attack missed.");
					 }
				 }
				 else if(heroRoll == 20 && orcRoll != 20) {
					 int damage = currentHero.attackSpecial();
					 int crit = damage * 2;
					 currentDragon.receiveDamage(crit);
					 System.out.println("The Dragon took " + crit + " critical damage.");
				 }
				 else if(orcRoll == 20 && heroRoll != 20) {
					 System.out.println("Your attack missed.");
					 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
				 }
				 else if(heroRoll == 1) {
					 System.out.println("Your attack missed.");
					 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
				 }
				 else if(heroRoll == 20 && orcRoll == 1) {
					 int damage = currentHero.attackSpecial();
					 int quad = damage * 4;
					 currentDragon.receiveDamage(quad);
					 System.out.println("The Dragon took " + quad + " quad damage.");
				 }
				 else if(heroRoll != 1 && orcRoll == 1) {
					 int damage = currentHero.attackSpecial();
					 int crit = damage * 2;
					 currentDragon.receiveDamage(crit);
					 System.out.println("The Dragon took " + crit + " critical damage.");
				 }
				 
				 else if(heroTotal > orcTotal) {
					 int damage = currentHero.attackSpecial();
					 currentDragon.receiveDamage(damage);
					 System.out.println("The Dragon took " + damage + " damage.");
					 
				 }
				  
				 else {
					 System.out.println("Your attack missed.");
					 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
				 }
			 }
			 else if(x == 2 && mana < 10) {
				 System.out.println("You don't have enough MP");
				 notEnoughMana = true;
			 }
			}while(notEnoughMana);
		}
		///////////////////////////////menu1////////////////////////////////////////////////////////////
		 else if(hPotions != 0 && mPotions == 0) {
			 do {
					notEnoughMana = false;
					System.out.println(currentHero.getStats());
					System.out.println(currentDragon);
				 x = ConsoleIO.promptForMenuSelection(menu1, false);
				 if(x == 1) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
					 int orcRoll = orcTotal - currentDragon.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackNormal();
							 int crit = damage * 2;
							 currentDragon.receiveDamage(crit);
							 System.out.println("The dragon took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int quad = damage * 4;
						 currentDragon.receiveDamage(quad);
						 System.out.println("The Dragon took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 currentDragon.receiveDamage(damage);
						 System.out.println("The Dragon took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
					 }
					
				 }
				 else if(x == 2 && mana > 10) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
					 int orcRoll = orcTotal - currentDragon.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackSpecial();
							 int crit = damage * 2;
							 currentDragon.receiveDamage(crit);
							 System.out.println("The Dragon took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int quad = damage * 4;
						 currentDragon.receiveDamage(quad);
						 System.out.println("The Dragon took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						 currentDragon.receiveDamage(damage);
						 System.out.println("The Dragon took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
				 }
				 else if(x == 2 && mana < 10) {
					 System.out.println("You don't have enough MP");
					 notEnoughMana = true;
				 }
				 else if (x == 3) {
					int hp = currentHero.useHealthPotion();
					 currentHero.setCurrentHP(currentHero.getCurrentHP() + hp);
				 }
				}while(notEnoughMana);
		 }
		//////////////////////////////menu2///////////////////////////////////////////////////////////////
		 else if(hPotions == 0 && mPotions != 0) {
			 do {
					notEnoughMana = false;
					System.out.println(currentHero.getStats());
					System.out.println(currentDragon);
				 x = ConsoleIO.promptForMenuSelection(menu2, false);
				 if(x == 1) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
					 int orcRoll = orcTotal - currentDragon.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackNormal();
							 int crit = damage * 2;
							 currentDragon.receiveDamage(crit);
							 System.out.println("The dragon took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int quad = damage * 4;
						 currentDragon.receiveDamage(quad);
						 System.out.println("The Dragon took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 currentDragon.receiveDamage(damage);
						 System.out.println("The Dragon took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
					 }
					
				 }
				 else if(x == 2 && mana > 10) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
					 int orcRoll = orcTotal - currentDragon.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackSpecial();
							 int crit = damage * 2;
							 currentDragon.receiveDamage(crit);
							 System.out.println("The Dragon took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int quad = damage * 4;
						 currentDragon.receiveDamage(quad);
						 System.out.println("The Dragon took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						 currentDragon.receiveDamage(damage);
						 System.out.println("The Dragon took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
				 }
				 else if(x == 2 && mana < 10) {
					 System.out.println("You don't have enough MP");
					 notEnoughMana = true;
				 }
				 else if(x ==3) {
				int mp =	 currentHero.useMagicPotion();
					 currentHero.setCurrentMP(currentHero.getCurrentMP() + mp);
				 }
				}while(notEnoughMana);
		 }
		///////////////////////////////////////menu3/////////////////////////////////////////////////////////////
		 else {
			 do {
					notEnoughMana = false;
					System.out.println(currentHero.getStats());
					System.out.println(currentDragon);
				 x = ConsoleIO.promptForMenuSelection(menu3, false);
				 if(x == 1) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
					 int orcRoll = orcTotal - currentDragon.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackNormal();
							 int crit = damage * 2;
							 currentDragon.receiveDamage(crit);
							 System.out.println("The dragon took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int quad = damage * 4;
						 currentDragon.receiveDamage(quad);
						 System.out.println("The Dragon took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackNormal();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackNormal();
						 currentDragon.receiveDamage(damage);
						 System.out.println("The Dragon took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
					 }
					
				 }
				 else if(x == 2 && mana > 10) {
					 int heroTotal = bag.roll(1, 20, 0) + currentHero.getStrikeBonus();
					 int heroRoll = heroTotal - currentHero.getStrikeBonus();
					 int orcTotal = bag.roll(1, 20, 0) + currentDragon.getDodgeBonus();
					 int orcRoll = orcTotal - currentDragon.getDodgeBonus();
					 
					 if(heroRoll == 20 && orcRoll == 20) {
						 if(heroTotal > orcTotal) {
							 int damage = currentHero.attackSpecial();
							 int crit = damage * 2;
							 currentDragon.receiveDamage(crit);
							 System.out.println("The Dragon took " + crit + " critical damage.");
						 }
						 else {
							 System.out.println("Your attack missed.");
						 }
					 }
					 else if(heroRoll == 20 && orcRoll != 20) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 else if(orcRoll == 20 && heroRoll != 20) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 1) {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
					 else if(heroRoll == 20 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int quad = damage * 4;
						 currentDragon.receiveDamage(quad);
						 System.out.println("The Dragon took " + quad + " quad damage.");
					 }
					 else if(heroRoll != 1 && orcRoll == 1) {
						 int damage = currentHero.attackSpecial();
						 int crit = damage * 2;
						 currentDragon.receiveDamage(crit);
						 System.out.println("The Dragon took " + crit + " critical damage.");
					 }
					 
					 else if(heroTotal > orcTotal) {
						 int damage = currentHero.attackSpecial();
						 currentDragon.receiveDamage(damage);
						 System.out.println("The Dragon took " + damage + " damage.");
						 
					 }
					  
					 else {
						 System.out.println("Your attack missed.");
						 currentHero.setCurrentMP(currentHero.getCurrentMP() - 10);
					 }
				 }
				 else if(x == 2 && mana < 10) {
					 System.out.println("You don't have enough MP");
					 notEnoughMana = true;
				 }
				 else if( x == 3) {
				int hp =	 currentHero.useHealthPotion();
					 currentHero.setCurrentHP(currentHero.getCurrentHP() + hp);
				 }
				 else if( x == 4) {
					int mp = currentHero.useMagicPotion();
					 currentHero.setCurrentMP(currentHero.getCurrentMP() + mp);
				 }
				}while(notEnoughMana);
		
		 
		 
	 }
	 }
	 
	 private static int promptForFight() {
		 System.out.println("Which monster do you wish to fight?");
		String[] menu = {"Orc","Troll", "Dragon"};
		int x = ConsoleIO.promptForMenuSelection(menu, false);
		
		 return x;
	 }
	 private static void createHero() {
		 boolean createAgain;
		 do {
			
			 currentHero = new Hero(rollAttribute(), rollAttribute(), rollAttribute());
			 currentHero.setCurrentHP(currentHero.getBaseHP());
			 currentHero.setCurrentMP(currentHero.getBaseMP());
			 System.out.println(currentHero);
			 createAgain = ConsoleIO.promptForBool("Do you want another hero? Type yes or no.","yes", "no");
		  }while(createAgain);
	 }
	 
	 private static void promptForName() {
		 String nameOfCharacter =	ConsoleIO.promptForInput("Enter your character's name.", false);
		currentHero.setName(nameOfCharacter);
		
		 
	 }
		
	 private static int rollAttribute() {
		  bag = new DiceBag();
		int total = bag.roll(3, 6, 0);
		int total2;
		int sum = 0;
		 if (total >= 16) {
			 total2 = bag.roll(1, 6, 0);
			sum = total + total2;
			 if(total2 == 6) {
				 sum += bag.roll(1, 6, 0);
			 }
		 }
		 else {
		 sum = total;
		 }
		return sum;
		 
	 }
	 private static void createOrc() {
		  currentOrc = new Orc(rollAttributeOrc(),rollAttributeOrc(),rollAttributeOrc());
		  currentOrc.setCurrentHP(currentOrc.getBaseHP());
			 currentOrc.setCurrentMP(currentOrc.getBaseMP());
	 }
	 private static int rollAttributeOrc() {
		 DiceBag bag = new DiceBag();
		int total = bag.roll(2, 5, 0);
		
		return total;
		 
	 }
	 private static void createTroll() {
		  currentTroll = new Troll(rollAttributeTroll(),rollAttributeTroll(),rollAttributeTroll());
		  currentTroll.setCurrentHP(currentTroll.getBaseHP());
			 currentTroll.setCurrentMP(currentTroll.getBaseMP());
	 }
	 private static int rollAttributeTroll() {
		 DiceBag bag = new DiceBag();
		int total = bag.roll(3, 6, 0);
		int total2;
		int sum = 0;
		 if (total >= 16) {
			 total2 = bag.roll(1, 6, 0);
			sum = total + total2;
			
		 }
		 else {
		 sum = total;
		 }
		return sum;
	}
	 private static void createDragon() {
		  currentDragon = new Dragon(rollAttributeDragon(),rollAttributeDragon(),rollAttributeDragon());
		  currentDragon.setCurrentHP(currentDragon.getBaseHP());
			 currentDragon.setCurrentMP(currentDragon.getBaseMP());
	 }
	 private static int rollAttributeDragon() {
		 DiceBag bag = new DiceBag();
		int total = bag.roll(4, 7, 0);
		int total2;
		int sum = 0;
		 if (total >= 20) {
			 total2 = bag.roll(2, 6, 0);
			sum = total + total2;
			
		 }
		 else {
		 sum = total;
		 }
		return sum;
	}
}
