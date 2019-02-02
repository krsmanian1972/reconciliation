package com.krscode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataMatcher {

	public MatchResult firstMatch(List<CPTCode> source, CPTCode targetCode) {

		Map<MatchingType, List<CPTCode>> matchMap = groupByMatchingType(source, targetCode);

		MatchingType matchingType = MatchTypeSelector.resolveBestMatchingType(matchMap);

		CPTCode matchedSourceCode = getFirstMatchingSource(matchingType,matchMap.get(matchingType));
		
		return new MatchResult(matchedSourceCode,matchingType);
	}

	private CPTCode getFirstMatchingSource(MatchingType matchingType, List<CPTCode> source) {
		if(matchingType == MatchingType.NO_MATCH || matchingType == MatchingType.CONFLICT)
		{
			return null;
		}
		return source.get(0);
	}

	/**
	 * We need not to proceed further if we encounter the first perfect match
	 * @param source
	 * @param targetCode
	 * @return
	 */
	private Map<MatchingType, List<CPTCode>> groupByMatchingType(List<CPTCode> source, CPTCode targetCode) {
		Map<MatchingType, List<CPTCode>> matchingTypes = new HashMap<>();

		for (CPTCode sourceCode:source) {
			MatchingType type = sourceCode.isMatching(targetCode);
			record(matchingTypes, type, sourceCode);
			if (type == MatchingType.PERFECT) {
				break;
			}
		}

		return matchingTypes;
	}

	/**
	 * Discard No matches and record the (potential) matches.
	 * 
	 * @param matchingTypes
	 * @param type
	 * @param cptCode
	 */
	private void record(Map<MatchingType, List<CPTCode>> matchingTypes, MatchingType type, CPTCode cptCode) {
		if (type == MatchingType.NO_MATCH) {
			return;
		}
		List<CPTCode> matchingCodes = matchingTypes.get(type);
		if (matchingCodes == null) {
			matchingCodes = new ArrayList<>();
			matchingTypes.put(type, matchingCodes);
		}
		matchingCodes.add(cptCode);
	}

}
