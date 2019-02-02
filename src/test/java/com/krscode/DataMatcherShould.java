package com.krscode;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DataMatcherShould {
	
	@Test
	public void performPerfectMatch() {
		List<CPTCode> sourceCodes = new ArrayList<>();
		
		CPTCode source1 = new CPTCode("1233","1");
		CPTCode source2 = new CPTCode("1234","");
		CPTCode source3 = new CPTCode("1234","2");
		
		sourceCodes.add(source1);
		sourceCodes.add(source2);
		sourceCodes.add(source3);
	
		CPTCode targetCode = new CPTCode("1234","2");
		
		DataMatcher matcher = new DataMatcher();
		MatchResult result = matcher.firstMatch(sourceCodes, targetCode);

		assertEquals(source3,result.getSourceCode());
		assertEquals(MatchingType.PERFECT,result.getMatchingType());
	}
	
	@Test
	public void performSoftMatch() {
		List<CPTCode> sourceCodes = new ArrayList<>();
		
		CPTCode source1 = new CPTCode("1233","1");
		CPTCode source2 = new CPTCode("1234","2");
		
		sourceCodes.add(source1);
		sourceCodes.add(source2);

		CPTCode targetCode = new CPTCode("1234","");

		DataMatcher matcher = new DataMatcher();
		MatchResult result = matcher.firstMatch(sourceCodes,targetCode);
		
		assertEquals(source2,result.getSourceCode());
		assertEquals(MatchingType.SOFT,result.getMatchingType());
	}
	
	@Test
	public void rejectForMultiSoftMatch() {
		List<CPTCode> sourceCodes = new ArrayList<>();
		
		CPTCode source1 = new CPTCode("99214","");
		CPTCode source2 = new CPTCode("99214","26");
		
		sourceCodes.add(source1);
		sourceCodes.add(source2);

		CPTCode targetCode = new CPTCode("99214","25");

		DataMatcher matcher = new DataMatcher();
		MatchResult result = matcher.firstMatch(sourceCodes,targetCode);
		
		assertEquals(null,result.getSourceCode());
		assertEquals(MatchingType.CONFLICT,result.getMatchingType());
	}
	
	@Test
	public void handleNoMatch() {
		List<CPTCode> sourceCodes = new ArrayList<>();
		
		CPTCode source1 = new CPTCode("99214","");
		CPTCode source2 = new CPTCode("99214","26");
		
		sourceCodes.add(source1);
		sourceCodes.add(source2);

		CPTCode targetCode = new CPTCode("99215","25");

		DataMatcher matcher = new DataMatcher();
		MatchResult result = matcher.firstMatch(sourceCodes,targetCode);
		
		assertEquals(null,result.getSourceCode());
		assertEquals(MatchingType.NO_MATCH,result.getMatchingType());
	}
	
	
	
	
}
