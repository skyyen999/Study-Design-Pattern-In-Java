# 橋梁模式 Bridge Pattern

####目的：將抽象介面與實作類別切開，使兩者可以各自變化而不影響彼此  

###寄個信有這麼複雜嗎
現在要設計一個郵件寄送系統，要寄信到另外一個地方，只要三天就會到，如果你很急，只要多出一些郵資，
郵差會在24小時幫你把限時信件送到；接下來如果我們擔心寄出的信沒有確實被收到，那可以寄掛號信，掛號信請收信人簽收，當然掛號信
也是分為三天到跟24小時到兩種。以上的系統設計出來如下圖：  
![Bridge descrption](image/bridgeDemo1.gif)  
  
以上的系統看起來沒啥問題，現在系統要加一個雙掛號信，對方簽收後郵差會將收信者簽名寄回來，架構會變成這樣。
![Bridge descrption](image/bridgeDemo3.gif)  
然後假如我們要再加一個特急件，保證6小時會到，類別的數量就會變成3x3=9種，會變動維度有兩個（信件到達時間與掛號），
因此類別數量一不小心就會堆的跟山一樣高。為了改善這種情況，我們將寄信這個動作抽出成為一個新的實體，信件就變成了到達時間+寄信兩者的組合。
這就是橋梁模式。  
![Bridge descrption](image/bridgeDemo2.gif)

  
###類別圖 
![Bridge Class Diagram](image/bridgeMail.gif)   

###程式碼 
寄信類別  
```
public abstract class MailSender {
	protected Mail mail;
	
	@SuppressWarnings("unused")
	private MailSender(){};
	
	public MailSender(Mail mail){
		this.mail = mail;
	}
	
	// 寄信
	abstract public void send();
}

//一般信件
public class NormalMail extends MailSender{
	public NormalMail(Mail mail) {
		super(mail);
	}
	@Override
	public void send() {
		System.out.print(">>信件寄出後3~5天內抵達  ");
		super.mail.resgiterState();
	}
}

//限時信
public class PromptMail extends MailSender{
	public PromptMail(Mail mail) {
		super(mail);
	}

	@Override
	public void send() {
		System.out.print(">>信件寄出後24小時內抵達  ");
		super.mail.resgiterState();
	}
}
```  
平信，掛號信，雙掛號信類別 
```
public abstract class Mail {
	// 平信，掛號信，雙掛號信等
	abstract void resgiterState();
}
//非掛號信
public class NoRegisterMail extends Mail{
	@Override
	void resgiterState() {
		System.out.println("這是封信不是註冊信，收件人不用簽名  ");		
	}
}
//掛號信
public class RegisterMail extends Mail{
	@Override
	void resgiterState() {
		System.out.println("這是一封掛號信，收件人必需簽名  ");
	}
}
```  


測試碼
```  
/**
 * 橋接模式-測試
 */
public class MailTest {

	@Test
	public void test(){
		System.out.println("============橋接模式測試============");
		System.out.println("----ㄧ般信件測試----");
		MailSender mailSender = new NormalMail(new NoRegisterMail());
		mailSender.send();
		mailSender= new NormalMail(new RegisterMail());
		mailSender.send();
		System.out.println("----限時信件測試----");
		mailSender = new PromptMail(new NoRegisterMail());
		mailSender.send();
		mailSender= new PromptMail(new RegisterMail());
		mailSender.send();
	}
}

```  
測試結果
``` 
============橋接模式測試============
----ㄧ般信件測試----
>>信件寄出後3~5天內抵達  這是封信不是註冊信，收件人不用簽名  
>>信件寄出後3~5天內抵達  這是一封掛號信，收件人必需簽名  
----限時信件測試----
>>信件寄出後24小時內抵達  這是封信不是註冊信，收件人不用簽名  
>>信件寄出後24小時內抵達  這是一封掛號信，收件人必需簽名  

``` 