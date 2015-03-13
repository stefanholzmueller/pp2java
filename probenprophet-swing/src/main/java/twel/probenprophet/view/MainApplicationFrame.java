package twel.probenprophet.view;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;

import twel.probenprophet.base.MainView;

public class MainApplicationFrame extends JFrame implements MainView {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	private CheckProbabilityPanel checkProbabilityPanel;

	@PostConstruct
	public void init() {
		add(checkProbabilityPanel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pack();
		setMinimumSize(getSize());
		setVisible(true);
	}

	public void startApplication(@Observes ContainerInitialized event) {
		setSystemLookAndFeel();
	}

	private void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			logError(e);
		} catch (InstantiationException e) {
			logError(e);
		} catch (IllegalAccessException e) {
			logError(e);
		} catch (UnsupportedLookAndFeelException e) {
			logError(e);
		}
	}

	private void logError(Exception e) {
		logger.error(e.getMessage(), e);
	}

}
