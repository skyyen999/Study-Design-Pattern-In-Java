# 責任鏈模式 ChainOfResponsibility Pattern

####目的：讓不同的物件有機會能處理同一個請求 

###請假要過幾關    
這個模式就是處理簽核流程，下面的範例是當員工提出休假申請時，如果在兩天以下，直屬主管經理就可以批准，兩天以上，
五天以下則是給更高一階的主管簽核才行，超過五天則要由總經理批准。另外員工也可以提出加薪的要求，這時候就由總經理
來決定是否加薪。  
因此我們可以將提出申請封裝成一個Request類別，另外可以處理Request的物件則抽出成為Handler介面，上面那些主管就是實作
Handler的ConcreteHandler  
  
比起用if else來實作上述的情境，使用責任鏈的好處是我們可以輕易調整職責鏈，例如說現在公司要簡化流程，請假五天以下由
直屬主管批准，以上由總經理批准，那只要在客戶端重新設定職責鏈就好，不需要改動寫好的經理類別。
 
###類別圖  
![ChainOfResponsibility Class Diagram](image/chainOfResponsibility.gif)  
  
###程式碼： 
Request類別
```
/**
 * 提出申請(Request)
 */
public class ApplyRequest {
	/**
	 * 申請類別
	 */
	private String requestType;
	/**
	 * 申請內容
	 */
	private String requestContent;
	/**
	 * 申請數
	 */
	private int requestCount;
	
	
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestContent() {
		return requestContent;
	}
	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}
	public int getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
}
```
Handler介面與實作類別
```
/**
 * 管理者(Handler)
 *
 */
public abstract class Manager {
	protected String name;
	
	//上一級管理者
	protected Manager superior;
	
	public Manager(String name){
		this.name = name;
	}
	
	//設定上一級的管理者
	public void setSuperior(Manager superior){
		this.superior = superior;
	}
	/**
	 * 提出申請
	 * @param request
	 */
	abstract public void apply(ApplyRequest request);
}

/**
 * 經理(Concretehandler)
 */
public class CommonManager extends Manager {

	public CommonManager(String name) {
		super(name);
	}

	@Override
	public void apply(ApplyRequest request) {
		//2天以下的假就批准，其他丟給上級
		if(request.getRequestType().equals("請假") && request.getRequestCount() <= 2){
			System.out.print(request.getRequestType() + ":" + request.getRequestContent());
			System.out.println(" " + request.getRequestCount() + "天 被" + name + "批准");
		} else {
			if(superior != null){
				superior.apply(request);
			}
		}
	}

}

/**
 * 總監(Concretehandler)
 *
 */
public class Majordomo extends Manager {

	public Majordomo(String name) {
		super(name);
	}

	@Override
	public void apply(ApplyRequest request) {
		//5天以下的假就批准，其他丟給上級
		if(request.getRequestType().equals("請假") && request.getRequestCount() <= 5){
			System.out.print(request.getRequestType() + ":" + request.getRequestContent());
			System.out.println(" " + request.getRequestCount() + "天 被" + name + "批准");
		} else {
			if(superior != null){
				superior.apply(request);
			}
		}
	}

}

/**
 * 總經理(Concretehandler)
 *
 */
public class GeneralManager extends Manager {

	public GeneralManager(String name) {
		super(name);
	}

	@Override
	public void apply(ApplyRequest request) {
		if(request.getRequestType().equals("請假")){
			System.out.print(request.getRequestType() + ":" + request.getRequestContent());
			System.out.println(" " + request.getRequestCount()  + "天 被" + name + "批准");
		} else {
			if(request.getRequestCount() <= 1000){
				System.out.print(request.getRequestType() + ":" + request.getRequestContent());
				System.out.println(" " + request.getRequestCount() + "元 被"  + name + "批准");
			} else {
				System.out.print(request.getRequestType() + ":" + request.getRequestContent());
				System.out.println(" " + request.getRequestCount() + "元 被"  + name + "駁回");
			}
		}
	}

}

```  
測試碼
```  
/**
 * 責任鏈模式-測試
 */
public class ManagerClient {

	
	public static void main(String[] args) {
		System.out.println("============責任鏈模式測試============");

		Manager pm = new CommonManager("PM經理");
		Manager gl = new Majordomo("總監");
		Manager gm = new GeneralManager("總經理");
		
		// 設定上級，可隨實際需求更改
		pm.setSuperior(gl);
		gl.setSuperior(gm);
		
		ApplyRequest request = new ApplyRequest();
		request.setRequestType("請假");
		request.setRequestContent("小菜請假");
		request.setRequestCount(2);
		pm.apply(request);
		
		request.setRequestCount(4);
		pm.apply(request);

		request.setRequestType("加薪");
		request.setRequestContent("小菜加薪");
		request.setRequestCount(2000);
		pm.apply(request);

		request.setRequestCount(900);
		pm.apply(request);


	}

}

```  

測試結果
```  
============責任鏈模式測試============
請假:小菜請假 2天 被PM經理批准
請假:小菜請假 4天 被總監批准
加薪:小菜加薪 2000元 被總經理駁回
加薪:小菜加薪 900元 被總經理批准
```  