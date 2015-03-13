package twel.probenprophet.view;

public class SkillPointComboBox extends IntegerRangeComboBox {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_SKILL_POINT_VALUE = 3;
	private static final int SKILL_POINT_VALUE_MINIMUM = -5;
	private static final int SKILL_POINT_VALUE_MAXIMUM = 30;

	public SkillPointComboBox() {
		super(SKILL_POINT_VALUE_MINIMUM, SKILL_POINT_VALUE_MAXIMUM);
		setSelectedItem(DEFAULT_SKILL_POINT_VALUE);
	}
}
