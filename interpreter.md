# 解譯器模式 Interpreter Pattern

####目的：定義一個語言與其文法，使用一個解譯器來表示這個語言的敘述  

###建議直接跳過，這個模式真得很少用也很難用
解譯器模式就是將有一定規則的文字依照規則將他真正表達的意思解譯出來，下面的範例很簡單，待解譯的資料如下所述
* 以空白分段，每段開頭為字母A或B，之後接一數字 (ex. A122 B11 A178)   
* 如果開頭為A，請將後面的數字*2  
* 如果開頭為B，請將後面的數字/2  
因此我們需要一個Context來存放待解譯資料，然後一個Expression介面規範解譯器應該有什麼功能，藉由不同子類別實作Expression
來擴充解譯能力。  

類別圖：  
![Interpreter Class Diagram](image/interpreter.gif)  

程式碼：  
```
/**
 * 待解譯的資料
 */
public class Context {
	// 存放待解譯資料
    private String text;

	public String getText() {
		return text;
	}

	/**
	 * 以空白分段，每段開頭為字母A或B，之後接一數字 (ex. A122 B11 A178) 
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

}

/**
 * 共用解譯器類別
 */
public abstract class Expression {
	public void interpret(String str){
		if(str.length() > 0){
			String text = str.substring(1, str.length());
			Integer number = Integer.valueOf(text);
			excute(number);
		}
	}
	
	/**
	 * 
	 * @param text 要解譯的內容
	 */
	protected abstract void excute(Integer number);
}


/**
 * 如果第一個字為A，數字*2
 */
public class UpExpression extends Expression {
	@Override
	public void excute(Integer number) {
		System.out.print(number*2 + " ");
	}
}

/**
 * 如果第一個字為B，數字/2
 */
public class DownExpression extends Expression {
	@Override
	public void excute(Integer number) {
		System.out.print(number/2 + " ");
	}
}

/**
 * 解譯器-測試
 */
public class InterpreterTest {
	@Test
	public void test(){
		Expression ex ;
		Context context = new Context();
		context.setText("A4461 B1341 A676 B1787");

		// A則後面的數字*2，B則後面的數字/2
		for(String str : context.getText().split("\\s")){
			// 不同的解譯器代表可以藉由實作更多Expression的字類別來擴充解譯器能力
			if(str.charAt(0) == 'A'){
				ex = new UpExpression();
			} else {
				ex = new DownExpression();
			}
			
			ex.interpret(str);
		}
	}
}
```  
