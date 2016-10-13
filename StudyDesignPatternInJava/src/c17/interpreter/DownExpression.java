package c17.interpreter;
/**
 * 如果第一個字為B，數字/2
 */
public class DownExpression extends Expression {
	@Override
	public void excute(Integer number) {
		System.out.print(number/2 + " ");
	}

}
