# 策略模式實例-排序
  
在java提供的API中可以找到策略模式的實際應用，Collection類別提供了sort這個方法方便對一群資料進行排序  

***Collections.sort(List<T> list, Comparator<? super T> c)***

sort接收兩個參數，第一個為要排序的清單(List)，第二個是比較器(Comparator)，Comparator裡面的演算法決定如何排序清單中的資料。  

這邊有三個村莊，分別將以ID排序的策略(SortVillageById)、以名稱排序的策略(SortVillageByName)、以人口排序的策略(SortVillageByPopulation)
傳入sort排序清單中的村莊。
   
###程式碼   
```
/**
 * 村莊類別，等等拿來做排序用
 */
public class Village {
	public int id;
	public String name;
	public int population;
	public double area;
	
	public Village (int id, String name, int population, double area){
		this.id = id; 
		this.name = name;
		this.population = population;
		this.area = area;
	}
	
	@Override
	public String toString(){
		return id + "." + name + "(人口: " + population + " 面積: "+ area + ")";
	}
}


/**
 * 使用ID排序(ConcretStrategy)
 */
public class SortVillageById implements Comparator<Village>{
	@Override
	public int compare(Village o1, Village o2) {
		if(o1.id > o2.id){
			return 1;
		} 
		
		if(o1.id < o2.id){
			return -1;
		}
		return 0;
	}
}

/**
 * 用村莊面積做排序(ConcretStrategy)
 */
public class SortVillageByArea implements Comparator<Village>{
	@Override
	public int compare(Village o1, Village o2) {
		if(o1.area > o2.area){
			return 1;
		} 
		
		if(o1.area < o2.area){
			return -1;
		}
		return 0;
	}
}

/**
 * 村莊名稱做排序(ConcretStrategy)
 */
public class SortVillageByName implements Comparator<Village>{
	@Override
	public int compare(Village o1, Village o2) {
		if(o1.name.charAt(0) > o2.name.charAt(0)){
			return 1;
		} 
		
		if(o1.name.charAt(0) < o2.name.charAt(0)){
			return -1;
		}
		return 0;
	}
}


/**
 * 策略模式排序-測試
 */
public class StrategyExample {

	public static void main(String[] args) {
		// 準備三個村莊的資料
		Village appleFarm = new Village(3,"apple farm",32,5.1);
		Village barnField = new Village(1,"barn field",22,1.7);
		Village capeValley = new Village(2, "cape valley",  10  ,10.2);
		
		
		ArrayList<Village> vilages = new ArrayList<>();
		vilages.add(appleFarm);
		vilages.add(barnField);
		vilages.add(capeValley);
		
		System.out.println("沒排序過的資料");
		showList(vilages);
		
		System.out.println("根據ID排序");
		Collections.sort(vilages,new SortVillageById());
		showList(vilages);

		System.out.println("根據名子排序");
		Collections.sort(vilages,new SortVillageByName());
		showList(vilages);
		
		System.out.println("根據人口排序");
		Collections.sort(vilages,new SortVillageByPopulation());
		showList(vilages);
		
		System.out.println("根據面積排序");
		Collections.sort(vilages,new SortVillageByArea());
		showList(vilages);
	}

	// 只是為了把資料印出來看
	public static void showList (ArrayList<Village> list){
		for(Village v : list){
			System.out.println(v);
		}
	}
}
```
測試結果  
```
==========策略模式排序測試==========
沒排序過的資料
3.apple farm(人口: 32 面積: 5.1)
1.barn field(人口: 22 面積: 1.7)
2.cape valley(人口: 10 面積: 10.2)
根據ID排序
1.barn field(人口: 22 面積: 1.7)
2.cape valley(人口: 10 面積: 10.2)
3.apple farm(人口: 32 面積: 5.1)
根據名子排序
3.apple farm(人口: 32 面積: 5.1)
1.barn field(人口: 22 面積: 1.7)
2.cape valley(人口: 10 面積: 10.2)
根據人口排序
2.cape valley(人口: 10 面積: 10.2)
1.barn field(人口: 22 面積: 1.7)
3.apple farm(人口: 32 面積: 5.1)
根據面積排序
1.barn field(人口: 22 面積: 1.7)
3.apple farm(人口: 32 面積: 5.1)
2.cape valley(人口: 10 面積: 10.2)
```