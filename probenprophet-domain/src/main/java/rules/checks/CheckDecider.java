package rules.checks;

import twel.probenprophet.base.IntTriple;

public interface CheckDecider {

	CheckResult determineResult(Check check, IntTriple dice);

}
