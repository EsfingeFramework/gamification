package net.sf.esfinge.gamification.proxy;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.sf.esfinge.gamification.processors.AchievementProcessor;

public class TestContainerMapDb {

	ContainerMemoryDb containerMemoryDb;
	
	@Before
	public void setUp(){
		containerMemoryDb = new ContainerMemoryDb();
	}
	
	@Test
	public void testMapWithMetadasIsNotNullAndVerifyIfTheClassWasSalvedInMap() {		
		Object encapsulatedClass = new TestPointAnnotation();
		
		Map<Method, AchievementProcessor> mapWithMetadas = 
						containerMemoryDb.getMetadatasFrom(encapsulatedClass);
		
		Map<String, Map<Method, AchievementProcessor>> containerMapDb = 
						containerMemoryDb.getContainerMapDb();		
		
		assertNotNull(mapWithMetadas);
		assertTrue( containerMapDb.containsKey(encapsulatedClass.getClass().getName()) );		
	}

}
