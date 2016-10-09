package c11.composite.organizationTree;
/**
 * 部門單位抽項類別(Leaf)
 */
public abstract class Department extends AbstractAssociation {

	public Department(String name) {
		super(name);
	}

	@Override
	public void add(AbstractAssociation a) {
	}

	@Override
	public void remove(AbstractAssociation a) {
	}

	@Override
	public void display(int depth) {
		for(int i = 0 ; i < depth ; i++){
			System.out.print('-');
		}
		System.out.println(name);
	}

	/**
	 * 部門實際的工作由子類別決定
	 */
	@Override
	public void lineOfDuty() {

	}

}
