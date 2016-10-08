package c08.adapter;

import org.junit.Test;

import c08.adapter.Adapter;
import c08.adapter.NormalArcher;
import c08.adapter.Wizard;

/**
 * 弓箭手轉接成法師丟火球-測試(Client)
 */
public class AdapterClient {
	@Test	public void test(){
		Wizard wizard = new Adapter(new NormalArcher());
		wizard.fireBall();
	}
}
