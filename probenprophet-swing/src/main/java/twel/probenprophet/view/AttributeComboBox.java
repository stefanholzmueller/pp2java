package twel.probenprophet.view;

public class AttributeComboBox extends IntegerRangeComboBox {

	private static final int DEFAULT_ATTRIBUTE_VALUE = 12;
	private static final int ATTRIBUTE_VALUE_MINIMUM = 1;
	private static final int ATTRIBUTE_VALUE_MAXIMUM = 25;
	private static final long serialVersionUID = 1L;

	public AttributeComboBox(String toolTipText) {
		super(ATTRIBUTE_VALUE_MINIMUM, ATTRIBUTE_VALUE_MAXIMUM);
		setSelectedItem(DEFAULT_ATTRIBUTE_VALUE);
		setToolTipText(toolTipText);
	}
}
