package com.krscode;

import java.util.List;
import java.util.Map;

public class MatchTypeSelector {

	static MatchingType resolveBestMatchingType(Map<MatchingType, List<CPTCode>> matchingTypes) {

		List<CPTCode> perfectMatchCodes = matchingTypes.get(MatchingType.PERFECT);
		if (perfectMatchCodes != null && !perfectMatchCodes.isEmpty()) return MatchingType.PERFECT;

		List<CPTCode> softMatchCodes = matchingTypes.get(MatchingType.SOFT);
		if (softMatchCodes != null && softMatchCodes.size() == 1) return MatchingType.SOFT;
		if (softMatchCodes != null && softMatchCodes.size() > 1) return MatchingType.CONFLICT;

		return MatchingType.NO_MATCH;
	}
}
