package c2.factory.village;

import c2.factory.village.Adventurer;
import c2.factory.village.Archer;
/**
 * 實體工廠-弓箭手訓練營
 */
public class ArcherTrainingCamp implements TrainingCamp {

	@Override
	public Adventurer trainAdventurer() {
		System.out.println("訓練一個弓箭手");
		return new Archer(); 
	}

}
