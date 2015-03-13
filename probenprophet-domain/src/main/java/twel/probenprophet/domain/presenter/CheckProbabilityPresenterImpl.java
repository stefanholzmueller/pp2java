package twel.probenprophet.domain.presenter;

import javax.enterprise.context.Dependent;

@Dependent
public class CheckProbabilityPresenterImpl implements CheckProbabilityPresenter {

	public void calculate() {
		System.out.println("hi");
	}

}
