import static org.junit.Assert.*;

import org.junit.Test

import groovy.mock.interceptor.MockFor

import MainThing

class MainThingUnitTest {

	@Test
	public void testMainThing() {
	
	}

	@Test
	public void testDoSomething_No_Mock() {
	
		def m = new MainThing()
		
		m.doSomething("Hello There")
		
	}
	
	@Test
	public void testDoSomething_Mock_Collaborators() {
	
		def mockC1 = new MockFor(Collab1.class)
		mockC1.demand.test(1) { "Mock-Collab1:test"}
		mockC1.demand.testParam(1) { p -> "Mock-Collab1:testParam $p"}
		
		def mockC2 = new MockFor(Collab2.class)
		mockC2.demand.test(1) { "Mock-Collab2:test"}
		mockC2.demand.testParam(1) { p -> "Mock-Collab2:testParam $p"}
		
		def mockC3 = new MockFor(Collab3.class)
		def mockC3Params = ["mockName", 111]
		//def mockC3Proxy = mockC3.proxyInstance(mockC3Params as Object[])
		//mockC3.demand.with {  
		//(String, int) { new Expando([name: "fake", value:1])}
		//}
		mockC3.demand.test(1) { "Mock-Collab3:test name = ${mockC3Params[0]}, value = ${mockC3Params[1]}"}
		mockC3.demand.getName(1)  { mockC3Params[0] }
		mockC3.demand.getValue(1) { mockC3Params[1] }
		
		// You have to nest the use calls (it is not like a category 'use(c1,c2)'...)
		mockC1.use {
			mockC2.use {
				mockC3.use {
					
					def m = new MainThing()
			
					m.doSomething("Hello There Mock")
				}
			}
		}
		//mockC3.verify mockC3Proxy
	}
}
