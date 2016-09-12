package c4.strategy;

import org.junit.Test;

import c4.strategy.flight.Adventurer;
import c4.strategy.flight.Bow;
import c4.strategy.flight.LongSword;

public class FlightTest {
	
	@Test
	public void test(){
		Adventurer ad = new Adventurer();
		
		// 一開始使用長劍
		ad.choiceWeapon(new LongSword());
		System.out.println("出現史萊姆");
		ad.attack();
		
		// 出現會飛的敵人，改用弓箭
		System.out.println("出現會飛的吸血蝙蝠");
		ad.choiceWeapon(new Bow());
		ad.attack();

		// 出現不怕刀槍只怕火的敵人，改用火把
		System.out.println("出現不怕刀槍的殭屍");
		
	}
	
}
