package c13.proxy;

import org.junit.Test;

import c13.proxy.protect.Person;
import c13.proxy.protect.PersonBean;
import c13.proxy.protect.ProxyPersonBean;
/**
 * protected-proxy 測試
 */
public class PersonTest {
	@Test
	public void test(){
		// 沒使用代理
		Person realPerson = new PersonBean();
		realPerson.setLikeCount(10);
		System.out.println("like " +realPerson.getLikeCount());
		// 使用代理
		Person proxy = new ProxyPersonBean(new PersonBean());
		proxy.setLikeCount(10); // 代理會使這個程式無法被調用
		System.out.println("like " +proxy.getLikeCount());

	}
}
