package twel.probenprophet.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import twel.probenprophet.domain.presenter.CheckProbabilityPresenter;

public class CheckProbabilityPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	@Inject
	private ComboBoxesPanel comboBoxesPanel;

	private CheckProbabilityPresenter presenter;

	@Inject
	public void setPresenter(CheckProbabilityPresenter presenter) {
		this.presenter = presenter;
	}

	@PostConstruct
	public void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(comboBoxesPanel);
		JButton button = new JButton("Berechne!");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				presenter.calculate();
			}
		});
		add(button);
	}
}
