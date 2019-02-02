package com.krscode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Reconciliation {

	public Map<CPTCode, Integer> reconcile(List<CPTCode> sourceCodes, List<CPTCode> targetCodes) {

		List<CPTCode> perfectMatches = getPerfectMatches(sourceCodes, targetCodes);

		Map<CPTCode, Integer> result = buildPerfectMatchResult(sourceCodes, perfectMatches);

		Map<CPTCode, Integer> softMatchResult = buildSoftMatchResult(sourceCodes, targetCodes, perfectMatches);

		result.putAll(softMatchResult);

		return result;
	}

	private Map<CPTCode, Integer> buildSoftMatchResult(List<CPTCode> sourceCodes, List<CPTCode> targetCodes,
			List<CPTCode> perfectMatches) {

		Map<CPTCode, Integer> result = new HashMap<>();

		List<CPTCode> prunedSourceCodes = difference(sourceCodes, perfectMatches);
		List<CPTCode> prunedTargetCodes = difference(targetCodes, perfectMatches);

		DataMatcher dataMatcher = new DataMatcher();

		for (CPTCode targetCode : prunedTargetCodes) {
			MatchResult matchResult = dataMatcher.firstMatch(prunedSourceCodes, targetCode);
			result.put(targetCode, sourceCodes.indexOf(matchResult.getSourceCode()));
		}

		return result;
	}

	private Map<CPTCode, Integer> buildPerfectMatchResult(List<CPTCode> sourceCodes, List<CPTCode> perfectMatches) {
		Map<CPTCode, Integer> result = new HashMap<>();
		for (CPTCode perfectMatch : perfectMatches) {
			result.put(perfectMatch, sourceCodes.indexOf(perfectMatch));
		}
		return result;
	}

	private List<CPTCode> difference(List<CPTCode> codes, List<CPTCode> removable) {
		List<CPTCode> diffList = new ArrayList<>();

		Iterator<CPTCode> iterator = codes.iterator();
		while (iterator.hasNext()) {
			CPTCode code = iterator.next();
			if (!removable.contains(code)) {
				diffList.add(code);
			}
		}

		return diffList;
	}

	private List<CPTCode> getPerfectMatches(List<CPTCode> sourceCodes, List<CPTCode> targetCodes) {
		List<CPTCode> perfectMatches = new ArrayList<>();
		for (CPTCode targetCode : targetCodes) {
			for (CPTCode sourceCode : sourceCodes) {
				if (sourceCode.equals(targetCode)) {
					perfectMatches.add(targetCode);
				}
			}
		}
		return perfectMatches;
	}

}
