package c1.simpleFactory;

public class SimpleFactory {
	public Product createProduct(String type){
		switch(type){
			case "A": return new ProductA(); 
			case "B": return new ProductB();
			default : return null;
		}
	}
}
