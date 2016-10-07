package c8.adapter;

import org.junit.Test;

/**
 * 弓箭手轉接成法師丟火球-測試(Client)
 */
public class AdapterClient {
	@Test	public void test(){
		Wizard wizard = new Adapter(new NormalArcher());
		wizard.fireBall();
	}
}
