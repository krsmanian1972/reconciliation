package com.krscode;

import java.util.ArrayList;
import java.util.List;

public class ReconciliationResult {

	private final List<Match> matches = new ArrayList<>();

	public void add(CPTCode sourceCode, CPTCode targetCode) {
		matches.add(new Match(sourceCode,targetCode));
	}
	
	public List<Match> matches()
	{
		return matches;
	}
	
	class Match {
		
		CPTCode sourceCode,targetCode;
		
		Match(CPTCode sourceCode,CPTCode targetCode)
		{
			this.sourceCode = sourceCode;
			this.targetCode = targetCode;		
		}
	}

}
