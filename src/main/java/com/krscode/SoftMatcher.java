package com.krscode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SoftMatcher {
	public ReconciliationResult reconcile(List<CPTCode> sourceCodes, List<CPTCode> targetCodes) {
		ReconciliationResult result = new ReconciliationResult();

		Map<CPTCode, List<CPTCode>> targetMap = groupByTarget(sourceCodes, targetCodes);
		Set<CPTCode> targets = targetMap.keySet();
		for(CPTCode targetCode : targets)
		{
			List<CPTCode> potentialSource = targetMap.get(targetCode);
			if(potentialSource.size() == 1)
			{
				CPTCode sourceCode = potentialSource.get(0);
				result.add(sourceCode,targetCode);
				remove(sourceCodes,sourceCode);
				remove(targetCodes,targetCode);
			}
		}
		
		return result;
	}

	private void remove(List<CPTCode> codes, CPTCode toRemove) {
		codes.removeIf(item -> item.equals(toRemove));
	}

	private Map<CPTCode, List<CPTCode>> groupByTarget(List<CPTCode> sourceCodes, List<CPTCode> targetCodes) {
		Map<CPTCode, List<CPTCode>> matches = new HashMap<>();
		
		Iterator<CPTCode> sourceIterator = sourceCodes.iterator();
		while(sourceIterator.hasNext())
		{
			CPTCode sourceCode = sourceIterator.next();
			
			Iterator<CPTCode> targetIterator = targetCodes.iterator();
			while(targetIterator.hasNext())
			{
				CPTCode targetCode = targetIterator.next();
				if(sourceCode.isMatching(targetCode) == MatchingType.SOFT)
				{
					group(matches,targetCode,sourceCode);
				}
			}
		}
		return matches;
	}

	private void group(Map<CPTCode, List<CPTCode>> matches, CPTCode targetCode, CPTCode sourceCode) {
		
		List<CPTCode> matchingSource = matches.get(targetCode);
		if(matchingSource == null)
		{
			matchingSource = new ArrayList<>();
			matches.put(targetCode, matchingSource);
		}
		matchingSource.add(sourceCode);
	}
	
}
