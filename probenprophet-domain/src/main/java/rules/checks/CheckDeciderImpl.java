package rules.checks;

import java.util.List;

import twel.probenprophet.base.IntTriple;

public class CheckDeciderImpl implements CheckDecider {

	public CheckResult determineResult(Check check, IntTriple dice) {
		if (check == null || dice == null) {
			throw new IllegalArgumentException("arguments must not be null");
		}

		return calculateResult(check, dice);
	}

	private CheckResult calculateResult(Check check, IntTriple dice) {

		int points = getPoints(check);

		CheckOutcome specialSuccess = determineSpecialCheckOutcome(check, dice);
		if (specialSuccess != null) {
			return buildSpecialSuccessResult(check, specialSuccess);
		}

		if (points > 0) {
			return buildResultWithPoints(check, dice, points);
		} else {
			return buildResultWithoutPoints(check, dice, points);
		}
	}

	private int getPoints(Check check) {
		int value = check.getValue();
		int difficulty = check.getDifficulty();

		if (value < 0) {
			difficulty -= value;
			value = 0;
		}

		return value - difficulty;
	}

	private CheckResult buildResultWithPoints(Check check, IntTriple dice, int points) {
		IntTriple attributeTriple = check.getAttributeTriple();

		int leftoverPoints = getLeftoverPoints(dice, attributeTriple, points);

		if (leftoverPoints >= 0) {
			int leftoverValue = getMinimumNumber(leftoverPoints, check.getValue());
			int quality = Math.max(leftoverValue, getMinimumEffect(check));
			int possibleDifficulty = getPossibleDifficultyViaAttributes(dice, attributeTriple.first,
					attributeTriple.second, attributeTriple.third) + leftoverPoints;
			return new CheckResult(CheckOutcome.SUCCESSFUL, quality, possibleDifficulty);
		} else {
			return new CheckResult(CheckOutcome.UNSUCCESSFUL, null, leftoverPoints);
		}
	}

	private int getLeftoverPoints(IntTriple dice, IntTriple attributeTriple, int points) {
		List<Integer> attributeList = attributeTriple.getList();
		List<Integer> diceList = dice.getList();

		int leftoverPoints = points;
		for (int i = 0; i <= 2; i++) {
			Integer die = diceList.get(i);
			Integer attribute = attributeList.get(i);

			if (die > attribute) {
				leftoverPoints -= die - attribute;
			}
		}

		return leftoverPoints;
	}

	private CheckResult buildResultWithoutPoints(Check check, IntTriple dice, int points) {

		int reducedAttr1 = check.getAttribute1() + points;
		int reducedAttr2 = check.getAttribute2() + points;
		int reducedAttr3 = check.getAttribute3() + points;

		if (dice.first > reducedAttr1 || dice.second > reducedAttr2 || dice.third > reducedAttr3) {
			return buildUnsuccessfulResultWithoutPoints(dice, reducedAttr1, reducedAttr2, reducedAttr3);
		} else {
			return buildSuccessfulResultWithoutPoints(check, dice, reducedAttr1, reducedAttr2, reducedAttr3);
		}
	}

	private CheckResult buildUnsuccessfulResultWithoutPoints(IntTriple dice, int reducedAttr1, int reducedAttr2,
			int reducedAttr3) {

		int negativePossibleDifficulty = 0;
		negativePossibleDifficulty -= getNegativePossibleDifficulty(dice.first, reducedAttr1);
		negativePossibleDifficulty -= getNegativePossibleDifficulty(dice.second, reducedAttr2);
		negativePossibleDifficulty -= getNegativePossibleDifficulty(dice.third, reducedAttr3);

		return new CheckResult(CheckOutcome.UNSUCCESSFUL, null, negativePossibleDifficulty);
	}

	private int getNegativePossibleDifficulty(int die, int attr) {
		return die > attr ? die - attr : 0;
	}

	private CheckResult buildSuccessfulResultWithoutPoints(Check check, IntTriple dice, int reducedAttr1,
			int reducedAttr2, int reducedAttr3) {

		int minimumQuality = getMinimumEffect(check);
		int possibleDifficulty = getPossibleDifficultyViaAttributes(dice, reducedAttr1, reducedAttr2, reducedAttr3);

		return new CheckResult(CheckOutcome.SUCCESSFUL, minimumQuality, possibleDifficulty);
	}

	private int getPossibleDifficultyViaAttributes(IntTriple dice, int attribute1, int attribute2, int attribute3) {
		int possibleDifficulty1 = attribute1 - dice.first;
		int possibleDifficulty2 = attribute2 - dice.second;
		int possibleDifficulty3 = attribute3 - dice.third;

		return getMinimumNumber(possibleDifficulty1, possibleDifficulty2, possibleDifficulty3);
	}

	private int getMinimumNumber(int... numbers) {
		int result = numbers[0];
		for (int num : numbers) {
			result = Math.min(result, num);
		}
		return result;
	}

	private CheckResult buildSpecialSuccessResult(Check check, CheckOutcome outcome) {
		if (outcome == CheckOutcome.LUCKY_CHECK || outcome == CheckOutcome.SPECTACULAR_SUCCESS) {
			return new CheckResult(outcome, getMaximumQuality(check), null);
		} else {
			return new CheckResult(outcome, null, null);
		}
	}

	private int getMaximumQuality(Check check) {
		int value = check.getValue();
		return value > 0 ? value : getMinimumEffect(check);
	}

	private int getMinimumEffect(Check check) {
		return check.hasMinimumEffect() ? 1 : 0;
	}

	private CheckOutcome determineSpecialCheckOutcome(Check check, IntTriple dice) {

		if (dice.first + dice.second + dice.third == 3) {
			return CheckOutcome.SPECTACULAR_SUCCESS;
		}
		if ((dice.first + dice.second == 2) || (dice.first + dice.third == 2) || (dice.second + dice.third == 2)) {
			return CheckOutcome.LUCKY_CHECK;
		}
		if (dice.first + dice.second + dice.third == 60) {
			return CheckOutcome.SPECTACULAR_FUMBLE;
		}
		if (isFumble(check, dice)) {
			return CheckOutcome.FUMBLE;
		}
		if (isSpruchhemmung(check, dice)) {
			return CheckOutcome.SPRUCHHEMMUNG;
		}

		return null;
	}

	boolean isFumble(Check check, IntTriple dice) {
		if (check.hasFesteMatrix()) {
			return isFumbleWithFesteMatrix(dice);
		}
		if (check.hasTollpatsch()) {
			return isFumbleWithTollpatsch(dice);
		}

		return (dice.first + dice.second == 40) || (dice.first + dice.third == 40) || (dice.second + dice.third == 40);
	}

	boolean isFumbleWithFesteMatrix(IntTriple dice) {
		return ((dice.first + dice.second == 40) || (dice.first + dice.third == 40) || (dice.second + dice.third == 40))
				&& ((dice.first + dice.second + dice.third) >= 58);
	}

	boolean isFumbleWithTollpatsch(IntTriple dice) {
		return (dice.first >= 19 && dice.second >= 19)
				|| (dice.first >= 19 && dice.third >= 19)
				|| (dice.second >= 19 && dice.third >= 19);
	}

	boolean isSpruchhemmung(Check check, IntTriple dice) {
		return check.hasSpruchhemmung()
				&& (dice.first == dice.second || dice.second == dice.third || dice.first == dice.third);
	}
}
