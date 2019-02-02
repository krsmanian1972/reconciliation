package com.krscode;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.krscode.ReconciliationResult.Match;

public class ListReconciliationShould {

	@Test
	public void discoverPerfectMatches() {
		
		List<CPTCode> sourceCodes = new ArrayList<>();
		CPTCode firstSource = new CPTCode("99214","25");
		sourceCodes.add(firstSource);
		
		CPTCode firstTarget = new CPTCode("99214","");
		CPTCode secondTarget = new CPTCode("99214","25");
		
		List<CPTCode> targetCodes = new ArrayList<>();
		targetCodes.add(firstTarget);
		targetCodes.add(secondTarget);
		
		PerfectMatcher perfectMatcher = new PerfectMatcher();
		ReconciliationResult result = perfectMatcher.reconcile(sourceCodes,targetCodes);
		
		Match match = result.matches().get(0);
		
		assertEquals(secondTarget,match.sourceCode);
		assertEquals(secondTarget,match.targetCode);
		
		int sourceIndex = sourceCodes.indexOf(match.sourceCode);
		int targetIndex = targetCodes.indexOf(match.targetCode);
	
		assertEquals("Should be removed from the source List",-1,sourceIndex);
		assertEquals("Should be removed from the target List",-1,targetIndex);
	}

	// Look for Billed Amount, From DOS and toDOS
	@Test
	public void discoverSoftMatches() {
		
		List<CPTCode> sourceCodes = new ArrayList<>();
		CPTCode firstSource = new CPTCode("99214","25");
		sourceCodes.add(firstSource);
		
		CPTCode firstTarget = new CPTCode("99213","");
		CPTCode secondTarget = new CPTCode("99214","26");
		
		List<CPTCode> targetCodes = new ArrayList<>();
		targetCodes.add(firstTarget);
		targetCodes.add(secondTarget);
		
		SoftMatcher softMatcher = new SoftMatcher();
		ReconciliationResult result = softMatcher.reconcile(sourceCodes,targetCodes);
		Match match = result.matches().get(0);
		
		assertEquals(firstSource,match.sourceCode);
		assertEquals(secondTarget,match.targetCode);
		
		int sourceIndex = sourceCodes.indexOf(match.sourceCode);
		int targetIndex = targetCodes.indexOf(match.targetCode);
		assertEquals("Should be removed from the source List",-1,sourceIndex);
		assertEquals("Should be removed from the target List",-1,targetIndex);
	}
	
	@Test
	public void rejectConflictingSoftMatches() {
		
		List<CPTCode> sourceCodes = new ArrayList<>();
		CPTCode firstSource = new CPTCode("99214","25");
		CPTCode secondSource = new CPTCode("99214","23");
		sourceCodes.add(firstSource);
		sourceCodes.add(secondSource);
		
		CPTCode firstTarget = new CPTCode("99213","");
		CPTCode secondTarget = new CPTCode("99214","26");
		
		List<CPTCode> targetCodes = new ArrayList<>();
		targetCodes.add(firstTarget);
		targetCodes.add(secondTarget);
		
		SoftMatcher softMatcher = new SoftMatcher();
		ReconciliationResult result = softMatcher.reconcile(sourceCodes,targetCodes);
		
		assertEquals(0,result.matches().size());
		
		assertEquals("Source List should not be altered",2,sourceCodes.size());
		assertEquals("Target List should not be altered",2,targetCodes.size());
	}
	
	
}
