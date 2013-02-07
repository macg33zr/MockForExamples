import Collab1
import Collab2

class MainThing {
	
	Collab1 c1
	Collab2 c2
	
	MainThing() {
		c1 = new Collab1()
		c2 = new Collab2()
	}
	
	public void doSomething(String something) {
		println c1.test()
		println c1.testParam(something)
		println c2.test()
		println c2.testParam(something)
	}

}
