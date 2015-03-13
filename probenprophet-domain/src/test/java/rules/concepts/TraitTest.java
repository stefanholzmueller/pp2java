package rules.concepts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

public class TraitTest {

	@Test
	public void toStringShouldReturnGermanName() throws Exception {
		assertThat(Trait.OBJEKT.toString(), is("Objekt"));
	}
}
