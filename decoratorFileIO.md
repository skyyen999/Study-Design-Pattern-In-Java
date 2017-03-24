# 裝飾者模式實例 File IO

最著名的裝飾模式應用，應該就是java.io這套讀寫檔案的API了，這邊取出幾個java.io package內的類別，類別圖如下圖所示：  
  
Reader是被裝飾者介面（實際上是抽象類別），InputerStreamReader與FileReader是實作類別，也就是實際上的被裝飾者。左邊的BufferedReader是裝飾者，最主要的功能是提供一個readLine的方法，這個方法可以讓Reader一次讀取一行文字，比起FileReader一次只能讀取一個字元，使用上方便許多。  
  
ReverseReader是我們自己寫的裝飾者，與BufferedReader一樣可以用來增加FileReader的功能，這邊提供的是reverseLine方法來將讀出的字串反轉。  
  
![File IO](image/fileIO.gif)  

程式碼
```
/**
 * 裝飾類別-將讀入的字串反轉
 */
public class ReverseReader extends BufferedReader{

	public ReverseReader(Reader in) {
		super(in);
	}

	public String reverseLine() throws IOException {
		String line = super.readLine();
		if(line == null) return null;
		return reverse(line);
	}

	// 反轉字串
	private String reverse(String source){
		String result = "";
		for(int i = 0; i < source.length() ; i++ ){
			result = source.charAt(i) + result;
		}
		return result;
	}
}
```

測試碼
```
/**
 * 裝飾模式實例javaIO-測試
 */
public class JavaIOTest {
	@SuppressWarnings("resource")
	@Test
	public void test() throws IOException{
			System.out.println("=========FileReader讀取檔案==========");
			FileReader reader = new FileReader("test.txt");
			int c = reader.read();
			while (c >= 0) {
	            System.out.print((char)c);
	            c = reader.read();
	        }	
			
			System.out.println("=========BufferedReader讀取檔案==========");
			BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"));
			String line = bufferedReader.readLine();;
			while (line!=null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }	
			
			System.out.println("=========Reverse Reader反轉讀入的內容==========");
			// 測試將讀入的句子倒轉
			ReverseReader reverseReader = new ReverseReader(new FileReader("test.txt"));
			String rLine = reverseReader.reverseLine();
			while (rLine!=null) {
                System.out.println(rLine);
                rLine = reverseReader.reverseLine();
            }		
	}
}
```
 
測試結果    
```
=========FileReader讀取檔案==========
apple pen
pineapple pen  
=========BufferedReader讀取檔案==========
apple pen
pineapple pen  
=========Reverse Reader反轉讀入的內容==========
nep elppa
  nep elppaenip
```
