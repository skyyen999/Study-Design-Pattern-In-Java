package c05.decorator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import c05.decorator.javaIO.UpperCaseInputStream;
/**
 * 裝飾模式實例javaIO-測試
 */
public class JavaIOTest {
	@Test
	public void test() throws IOException{
			// 測試將讀入的字母換成大寫
			InputStream in = new UpperCaseInputStream(new BufferedInputStream(new FileInputStream("test.txt")));
			
			int c;
			// 這是 BufferedInputStream 提供的mark
			in.mark(0);
			while(( c = in.read()) >= 0){
				System.out.print((char)c);
			}
			
			// 這是 BufferedInputStream 提供的reset
			in.reset();
			System.out.println("--------------reset--------------");
			while( (c = in.read()) >= 0){
				System.out.print((char)c);
			}
			in.close();
			
	}
}
