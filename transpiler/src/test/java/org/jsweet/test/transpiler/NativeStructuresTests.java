package org.jsweet.test.transpiler;

import static org.junit.Assert.assertEquals;

import org.jsweet.transpiler.JSweetFactory;
import org.jsweet.transpiler.extensions.RemoveJavaDependenciesFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import source.nativestructures.Collections;
import source.nativestructures.Dates;
import source.nativestructures.Exceptions;
import source.nativestructures.Maps;
import source.nativestructures.NativeArrays;
import source.nativestructures.NativeStringBuilder;
import source.nativestructures.NativeSystem;
import source.nativestructures.WeakReferences;

public class NativeStructuresTests extends AbstractTest {

	@BeforeClass
	public static void start() {
		createTranspiler(new RemoveJavaDependenciesFactory<>());
	}

	@AfterClass
	public static void end() {
		createTranspiler(new JSweetFactory<>());
	}

	@Test
	public void testCollections() {
		eval((logHandler, result) -> {
			Assert.assertEquals("There should be no errors", 0, logHandler.reportedProblems.size());
			assertEquals(
					"1,a,1,b,3,4,d,a,d,0,0,0,a,a,2,a,true,false,3,c,c,a,b,c,a,b,c,b,1,c,b,a,b,c,a,0,true,true,it,true,1,[a,b,c],0,false",
					result.get("trace"));
		}, getSourceFile(Collections.class));
	}

	@Test
	public void testStringBuilder() {
		eval((logHandler, result) -> {
			Assert.assertEquals("There should be no errors", 0, logHandler.reportedProblems.size());
			assertEquals("a,abc,a,abc", result.get("trace"));
		}, getSourceFile(NativeStringBuilder.class));
	}

	@Test
	public void testWeakReferences() {
		eval((logHandler, result) -> {
			Assert.assertEquals("There should be no errors", 0, logHandler.reportedProblems.size());
			assertEquals("test,1", result.get("trace"));
		}, getSourceFile(WeakReferences.class));
	}

	@Test
	public void testExceptions() {
		eval((logHandler, result) -> {
			Assert.assertEquals("There should be no errors", 0, logHandler.reportedProblems.size());
			assertEquals("test,test,finally,test2,test3", result.get("trace"));
		}, getSourceFile(Exceptions.class));
	}

	@Test
	public void testMaps() {
		eval((logHandler, result) -> {
			Assert.assertEquals("There should be no errors", 0, logHandler.reportedProblems.size());
			assertEquals("2,a,true,[1,2],[a,b],1,true,1,2", result.get("trace"));
		}, getSourceFile(Maps.class));
	}

	@Test
	public void testNativeArrays() {
		eval((logHandler, result) -> {
			Assert.assertEquals("There should be no errors", 0, logHandler.reportedProblems.size());
			assertEquals("3,a,b,c,true,false,false,false,true", result.get("trace"));
		}, getSourceFile(NativeArrays.class));
	}

	@Test
	public void testSystem() {
		eval((logHandler, result) -> {
			Assert.assertEquals("There should be no errors", 0, logHandler.reportedProblems.size());
			assertEquals("true,true", result.get("trace"));
		}, getSourceFile(NativeSystem.class));
	}

	@Test
	public void testDates() {
		eval((logHandler, result) -> {
			Assert.assertEquals("There should be no errors", 0, logHandler.reportedProblems.size());
			assertEquals("114937200000,1973,7,23,7,0,0", result.get("trace"));
		}, getSourceFile(Dates.class));
	}
	
}