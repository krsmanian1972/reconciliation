package com.krscode;

import java.util.Iterator;
import java.util.List;

public class PerfectMatcher {

	public ReconciliationResult reconcile(List<CPTCode> sourceCodes, List<CPTCode> targetCodes) {
		ReconciliationResult result = new ReconciliationResult();
		Iterator<CPTCode> sourceIterator = sourceCodes.iterator();
		while(sourceIterator.hasNext())
		{
			CPTCode sourceCode = sourceIterator.next();
			
			Iterator<CPTCode> targetIterator = targetCodes.iterator();
			while(targetIterator.hasNext())
			{
				CPTCode targetCode = targetIterator.next();
				if(sourceCode.equals(targetCode))
				{
					result.add(sourceCode,targetCode);
					
					sourceIterator.remove();
					targetIterator.remove();
				}
			}
		}
		return result;
	}
}
