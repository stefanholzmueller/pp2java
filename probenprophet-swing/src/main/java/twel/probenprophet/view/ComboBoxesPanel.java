package twel.probenprophet.view;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.inject.Inject;
import javax.swing.JPanel;

import twel.probenprophet.domain.presenter.CheckProbabilityPresenter;

public class ComboBoxesPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	@Inject
	private CheckProbabilityPresenter checkProbabilityPresenter;

	public ComboBoxesPanel() {
		add(new Label("Eigenschaften:"));
		addAttributeComboBox("Erste Eigenschaft");
		addAttributeComboBox("Zweite Eigenschaft");
		addAttributeComboBox("Dritte Eigenschaft");
		add(new Label("Talentwert:"));
		add(new SkillPointComboBox());
	}

	private void addAttributeComboBox(String toolTipText) {
		AttributeComboBox attributeComboBox1 = new AttributeComboBox(toolTipText);
		attributeComboBox1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				checkProbabilityPresenter.calculate();
			}
		});
		add(attributeComboBox1);
	}
}
