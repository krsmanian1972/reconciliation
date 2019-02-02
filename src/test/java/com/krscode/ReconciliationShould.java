package com.krscode;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ReconciliationShould {

	@Test
	public void handleForMultiMatch() {
		List<CPTCode> sourceCodes = new ArrayList<>();
		sourceCodes.add(new CPTCode("99214","25"));
		
		CPTCode firstTarget = new CPTCode("99214","");
		CPTCode secondTarget = new CPTCode("99214","25");
		
		List<CPTCode> targetCodes = new ArrayList<>();
		targetCodes.add(firstTarget);
		targetCodes.add(secondTarget);
		
		Reconciliation reconciliation = new Reconciliation();
		Map<CPTCode,Integer> result = reconciliation.reconcile(sourceCodes,targetCodes);

		assertEquals(new Integer(0),result.get(secondTarget));
		assertEquals(new Integer(-1),result.get(firstTarget));
	}
}
