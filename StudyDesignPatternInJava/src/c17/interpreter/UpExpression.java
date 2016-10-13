package c17.interpreter;
/**
 * 如果第一個字為A，數字*2
 */
public class UpExpression extends Expression {
	@Override
	public void excute(Integer number) {
		System.out.print(number*2 + " ");
	}

}
