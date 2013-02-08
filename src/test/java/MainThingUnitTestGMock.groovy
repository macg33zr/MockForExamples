import static org.junit.Assert.*;

import org.junit.Test

import org.gmock.GMockTestCase


import MainThing

class yObj {
	String a
	
	public void doY(){
		"doY: $a"
	}
}

class x {
	
	def yVal;
	
	x(yObj _y) {
		yVal =_y
		println "x ctor"
	}
	
	public void doX() {
		"doX"
	}
}

class z {
	
	def xVal
	def yVal
	
	public void doSomething() {
		yVal = new yObj(a:'fred')
		xVal = new x(yVal)
		println xVal.toString()
		println xVal.doX()
		println yVal.doY()
	}
}

class MainThingUnitTestGMock extends GMockTestCase {

	@Test
	public void testMainThing() {
		def zz = new z()
		zz.doSomething()
	}
	
	@Test
	public void testingMock() {
		
		println "TestingMock"
		
		def mocky = mock(yObj, constructor([a:'fred']))	
		mocky.doY().returns("Mock doY").once()
		def mockx = mock(x, constructor(mocky))
		mockx.doX().returns("Mock doX").once()
		
		play {
			def zz = new z()
			zz.doSomething()
		}
	}

	@Test
	public void testDoSomething_No_Mock() {
	
		def m = new MainThing()
		
		m.doSomething("Hello There")
		
	}
	
	@Test
	public void testDoSomething_Mock_Collaborators() {
		Collab1 mockC1 = mock(Collab1, constructor() )
		ordered {
			mockC1.test().returns("Mock-Collab1:test").once()
			mockC1.testParam("Hello GMock").returns("Mock-Collab1:testParam GMOCK").once()
		}
		Collab2 mockC2 = mock(Collab2, constructor() )
		ordered {
			mockC2.test().returns("Mock-Collab2:test").once()
			mockC2.testParam("Hello GMock").returns("Mock-Collab2:testParam GMOCK").once()
		}
		Collab3 mockC3 = mock(Collab3, constructor("Collab3",42))
		ordered {
			mockC3.test().returns("Mock-Collab3:test xx 11").once()
			mockC3.getName().returns("GMock Collab3").once()
			mockC3.getValue().returns(42).once()
		}
		play {
			def m = new MainThing()
			m.doSomething("Hello GMock")
		}
		
	}
			
}
