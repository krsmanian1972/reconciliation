package com.krscode;

public class CPTCode {
	
	private String procedureCode;
	private String modifier;

	public CPTCode(String code, String modifier) {
		this.procedureCode = code;
		this.modifier = modifier;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modifier == null) ? 0 : modifier.hashCode());
		result = prime * result + ((procedureCode == null) ? 0 : procedureCode.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CPTCode other = (CPTCode) obj;
		if (modifier == null) {
			if (other.modifier != null)
				return false;
		} else if (!modifier.equals(other.modifier))
			return false;
		if (procedureCode == null) {
			if (other.procedureCode != null)
				return false;
		} else if (!procedureCode.equals(other.procedureCode))
			return false;
		return true;
	}


	public MatchingType isMatching(CPTCode targetCode) {
		if(this.equals(targetCode))
		{
			return MatchingType.PERFECT;
		}
		
		if(procedureCode.equals(targetCode.procedureCode))
		{
			return MatchingType.SOFT;
		}

		return MatchingType.NO_MATCH;
	}
}
