package com.krscode;


public class MatchResult {
	private CPTCode sourceCode;
	private MatchingType matchingType;
	
	public MatchResult(CPTCode sourceCode, MatchingType matchingType)
	{
		this.sourceCode = sourceCode;
		this.matchingType = matchingType;
	}

	public CPTCode getSourceCode() {
		return sourceCode;
	}

	public MatchingType getMatchingType() {
		return matchingType;
	}
	
	
}
