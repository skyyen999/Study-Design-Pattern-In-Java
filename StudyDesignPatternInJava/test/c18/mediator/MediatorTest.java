package c18.mediator;

import org.junit.Test;

import c18.mediator.Chatroom.CommonUser;
import c18.mediator.Chatroom.MessageMediator;
import c18.mediator.Chatroom.Messager;
import c18.mediator.Chatroom.VIPUser;
/**
 * 中介者模式-測試
 */
public class MediatorTest {
	@Test
	public void Test(){
		
		Messager jacky = new VIPUser("jacky");
		Messager huant = new CommonUser("huant");
		Messager neil = new CommonUser("neil");
		
		MessageMediator.joinChat(jacky);
		MessageMediator.joinChat(huant);
		MessageMediator.joinChat(neil);
		
		jacky.sendToAll("hi, 你好");
		jacky.send("單挑阿!PK阿!", huant);

		neil.send("收假了!!掰掰", jacky);
		neil.sendToAll("阿阿阿!!!");
	}
}
