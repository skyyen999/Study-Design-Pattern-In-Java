# 責任鏈模式 ChainOfResponsibility Pattern

####目的：讓不同的物件有機會能處理同一個請求 

###加薪，請假要過幾關    
這個模式就是處理簽核流程，下面的範例是當員工提出休假申請時，如果在兩天以下，直屬主管經理就可以批准，兩天以上，
五天以下則是給更高一階的主管簽核才行，超過五天則要由總經理批准。另外員工也可以提出加薪的要求，這時候就由總經理
來決定是否加薪。  
  
比起用if else來實作上述的情境，使用責任鏈的好處是我們可以輕易調整職責鏈，例如說現在公司要簡化流程，請假五天以下由
直屬主管批准，以上由總經理批准，那只要在客戶端重新設定職責鏈就好，不需要改動寫好的經理類別。
 
類別圖：  
![ChainOfResponsibility Class Diagram](image/chainOfResponsibility.gif)  
  
程式碼：  
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

/**
 * 管理者
 * @author Yan
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

```  
