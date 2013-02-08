import Collab1
import Collab2
import Collab3

class MainThing {
	
	Collab1 c1
	Collab2 c2
	Collab3 c3
	
	MainThing() {
		c1 = new Collab1()
		c2 = new Collab2()
		c3 = new Collab3("Collab3", 42)
		//c3 = new Collab3()
	}
	
	public void doSomething(String something) {
		println c1.test()
		println c1.testParam(something)
		println c2.test()
		println c2.testParam(something)
		println c3.test()
		println c3.getName()
		println c3.getValue()
	}

}
