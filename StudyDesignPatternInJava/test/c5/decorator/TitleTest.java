package c5.decorator;

import org.junit.Test;

import c5.decorator.title.Adventurer;
import c5.decorator.title.Agile;
import c5.decorator.title.InFire;
import c5.decorator.title.Strong;
import c5.decorator.title.Title;

public class TitleTest {
	@Test
	public void test(){
		// 一開始沒有任何稱號的冒險者
		Adventurer adventurer = new Adventurer("Jacky");
		System.out.println("冒險者Jacky");
		adventurer.attack();
		
		//jacky 變成強壯的冒險者
		Title sJacky = new Strong(adventurer);
		sJacky.attack();
	
		//jacky 變成燃燒的強壯的冒險者
		Title fJacky = new InFire(sJacky);
		fJacky.attack();
		
		// jacky 增加敏捷稱號
		Title aJacky = new Agile(fJacky);
		aJacky.attack();
		
	}
}
