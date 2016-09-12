package c4.strategy.village;

import c4.strategy.flight.Weapon;

/**
 * 冒險者
 * @author Yan
 */
public abstract class Adventurer {
	/**
	 * 告訴別人你是哪種冒險者
	 */
	abstract String getType();
	
	Weapon weapon;  //不同的武器有不同的攻擊方式(strategy)
	/**
	 * 用武器攻擊
	 */
	public void attack(){
		if(weapon == null){
			System.out.println("用拳頭毆打");
		}
		weapon.use();
	}
	
	/**
	 * 選擇不同的武器(策略)
	 */
	public void choiceWeapon(Weapon weapon){
		this.weapon = weapon;
	}
}
