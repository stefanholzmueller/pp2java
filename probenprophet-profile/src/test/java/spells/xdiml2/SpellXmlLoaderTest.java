package spells.xdiml2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import rules.concepts.Complexity;
import rules.concepts.Spell;
import rules.concepts.Trait;

public class SpellXmlLoaderTest {

	private static final String ABVENENUM_XML_PATH = "src/test/resources/spells/xdiml2/abvenenum.xml";
	private static final String ZAUBER_XML_PATH = "src/test/resources/spells/xdiml2/zauber.xml";

	private SpellXmlLoader spellXmlLoader;

	@BeforeMethod
	public void setUp() throws Exception {
		spellXmlLoader = new SpellXmlLoader();
	}

	@Test
	public void shouldLoadAllSpells() throws Exception {
		Collection<Spell> spells = loadAllSpells();

		assertThat(spells.size(), is(278));
	}

	@Test
	public void shouldUnmarshallAllKnownTraits() throws Exception {
		Collection<Spell> spells = loadAllSpells();

		for (Iterator<Spell> iterator = spells.iterator(); iterator.hasNext();) {
			Spell spell = (Spell) iterator.next();
			EnumSet<Trait> traits = spell.getTraits();
			assertThat(traits.size(), greaterThan(0));
			for (Trait trait : traits) {
				assertThat(trait, notNullValue());
			}
		}
	}

	private Collection<Spell> loadAllSpells() throws FileNotFoundException {
		FileReader fileReader = new FileReader(ZAUBER_XML_PATH);

		Collection<Spell> spells = spellXmlLoader.load(fileReader);
		return spells;
	}

	@Test
	public void shouldReturnOneSpell() throws Exception {
		FileReader fileReader = new FileReader(ABVENENUM_XML_PATH);

		Collection<Spell> spells = spellXmlLoader.load(fileReader);

		assertThat(spells.size(), is(1));
	}

	@Test
	public void shouldReturnCorrectName() throws Exception {
		Spell spell = loadAbvenenum();
		assertThat(spell.getName(), is("Abvenenum reine Speise"));
	}

	@Test
	public void shouldReturnCorrectCheckAttributes() throws Exception {
		Spell spell = loadAbvenenum();
		assertThat(spell.getCheckAttributes(), is("KL/KL/FF"));
	}

	@Test
	public void shouldReturnCorrectComplexity() throws Exception {
		Spell spell = loadAbvenenum();
		assertThat(spell.getComplexity(), is(Complexity.C));
	}

	@Test
	public void shouldReturnCorrectTraits() throws Exception {
		Spell spell = loadAbvenenum();
		assertThat(spell.getTraits(), is(EnumSet.of(Trait.OBJEKT)));
	}

	@Test
	public void shouldReturnCorrectVariants() throws Exception {
		Spell spell = loadAbvenenum();

		String[] expected = new String[] { "Zauberdauer", "Reichweite", "Schutz vor Übelkeit", "Schutz vor Vergiftung" };

		assertThat(spell.getVariants().size(), is(expected.length));
		assertThat(spell.getVariants(), hasItems(expected));
	}

	private Spell loadAbvenenum() throws FileNotFoundException {
		FileReader fileReader = new FileReader(ABVENENUM_XML_PATH);

		Collection<Spell> spells = spellXmlLoader.load(fileReader);

		Spell spell = spells.iterator().next();
		return spell;
	}
}
