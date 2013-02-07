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

		// You have to nest the use calls (it is not like a category 'use(c1,c2)'...)
		mockC1.use {
			mockC2.use {
				def m = new MainThing()
		
				m.doSomething("Hello There Mock")
			}
		}
	}
}
