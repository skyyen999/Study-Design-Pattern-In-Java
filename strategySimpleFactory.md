# 策略模式與簡單工廠模式有什麼不同?

##先看圖
####簡單工廠模式  
![Simple Factory](image/simpleFactory.gif) 

####策略模式  
![Fight Strategy](image/strategy.gif)  
  
這問題我困擾了很久，簡單工廠模式跟策略模式的類別圖看起來根本是一模一樣的，用法似乎也是一樣  
***訓練營提供不同的冒險者讓我們可以去進行不同的任務 vs 冒險者選擇不同的攻擊策略來攻擊怪物***
	
上面這句怎麼看都只是把名詞換掉而已，搜片了中英文各家解釋，一般來說會得到這樣的答案：  
* 簡單工廠模式是用來建立物件的模式，關注物件如何被產生  
* 策略模式是一種行為模式，關注的是行為的封裝  

所以兩者是不同的喔!! 阿...所以到底這兩個模式有什麼差異???  

關鍵在於使用的時候，看下面的code，簡單工廠模式只負責創立物件，因此只要給跟訓練營(TrainingCamp)說，給我一個弓手(archer)，
給我一個鬥士(warrior)，訓練營就會產生一個物件給你一個相對應的冒險者，致於這個冒險者要去燒殺擄掠還是解救村民
，訓練營表示不關我的事情，我也不想知道，我只負責訓練冒險者。  

來到了策略模式，冒險者根據不同的情境來產生攻擊策略(這邊其實也可以改成用字串來產生不同的策略，
這樣策略模式看起來跟簡單工廠更像了)，不過這個攻擊策略跟冒險者是息息相關的，
畢竟用錯策略輕則被隊友笑說你怎麼這麼笨，嚴重的話就要請大俠重新來過了。  

* * *  

####總結一下
其實搞不清楚這兩個有什麼不一樣沒關係，畢竟設計模式只是提供一種解決問題的方法，總是會有其他方法可以解決的，除非你跟我得了一種
不找出一個自己覺得還可以的解釋會睡不著的病，其實不用花太多時間在這個問題上。  

簡單工廠模式跟策略模式都是透過子類別實現介面來減少類別之間的耦合度，兩者之間的差異非常的微妙，
要到實際調用的時候才會發現簡單工廠模式是創建型模式，真的只關注在物件的建立；策略模式是行為模式，
關注行為的封裝。好吧，這兩句前面出現過了，不過兩者的差別真的也就是這兩句話，我想這是學習必經的過程，
同樣的一段話，自己努力掙扎一番後回來看才發現原來答案真的是這樣。  
  
簡單工廠模式  
```
	@Test
	public void test(){
		System.out.println("==========簡單工廠模式測試==========");
		
		//訓練營訓練冒險者
		Adventurer memberA = TrainingCamp.trainAdventurer("archer");
		Adventurer memberB = TrainingCamp.trainAdventurer("warrior");
		
		//看看是不是真的訓練出我們想要的冒險者
		Assert.assertEquals(memberA.getType(), "Archer");
		Assert.assertEquals(memberB.getType(), "Warrior");
		//memberB應該是Warrior不是Knight，因此這邊會報錯
		// Assert.assertEquals(memberB.getType(), "Knight");
	}
```


策略模式  
```
	@Test
	public void test(){
		Adventurer ad = new Adventurer();
		
		// 史萊姆用一般攻擊就可以
		System.out.println("出現史萊姆>>>");
		ad.choiceStrategy(new NormalAttack());
		ad.attack();
		System.out.println();
		
		// 利害的敵人要用厲害的招式打他
		System.out.println("非常非常巨大的史萊姆>>>");
		ad.choiceStrategy(new UseSkill());
		ad.attack();
		System.out.println();
	}	
```