package c5.decorator;

import org.junit.Test;

import c5.decorator.title.Adventurer;
import c5.decorator.title.Lancer;
import c5.decorator.title.TitleAgile;
import c5.decorator.title.TitleInFire;
import c5.decorator.title.TitleStrong;
/**
 * 冒險者使用不同稱號來強化-測試
 */
public class TitleTest {
	@Test
	public void test(){
		// 一開始沒有任何稱號的冒險者
		Adventurer lancer = new Lancer("Jacky");
		System.out.println("---長槍兵Jacky---");
		lancer.attack();
		
		System.out.println();	
		System.out.println("---取得強壯稱號的jacky---");
		TitleStrong sJacky = new TitleStrong(lancer);
		sJacky.attack();

		
		System.out.println();
		System.out.println("---取得敏捷稱號的jacky---");
		TitleAgile aJacky = new TitleAgile(sJacky);
		aJacky.attack();
		aJacky.useFlash();
		
		System.out.println();
		System.out.println("---取得燃燒稱號的jacky---");
		TitleInFire fJacky = new TitleInFire(sJacky);
		fJacky.attack();
		fJacky.fireball();	
		
		System.out.println("---jacky決定成為一個非常強壯的槍兵---");
		TitleStrong ssJacky = new TitleStrong(fJacky);
		ssJacky.attack();
	}
}
