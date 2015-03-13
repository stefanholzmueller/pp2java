package twel.probenprophet.view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class IntegerRangeComboBox extends JComboBox {

	private static final long serialVersionUID = 1L;

	private final int minimumValue;
	private final int maximumValue;

	public IntegerRangeComboBox(int minimumValue, int maximumValue) {
		super();
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		setModel(new DefaultComboBoxModel(getComboBoxItems()));
	}

	protected Object[] getComboBoxItems() {
		int size = maximumValue - minimumValue + 1;
		Object[] items = new Object[size];
		for (int i = 0; i < size; i++) {
			items[i] = minimumValue + i;
		}
		return items;
	}

}