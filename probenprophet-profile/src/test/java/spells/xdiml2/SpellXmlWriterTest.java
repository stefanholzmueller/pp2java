package spells.xdiml2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import rules.concepts.Complexity;
import rules.concepts.Spell;
import rules.concepts.Trait;
import spells.Spells;

public class SpellXmlWriterTest {

	private SpellXmlWriter spellXmlWriter;

	@BeforeMethod
	public void setUp() throws Exception {
		spellXmlWriter = new SpellXmlWriter();
	}

	@Test
	public void shouldWriteXdimlFile() throws Exception {
		String xml = writeXmlString();

		assertThat(xml, startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"));
		assertThat(xml, containsString("<XDIML version=\"2.0\" lang=\"de\">"));
		assertThat(xml, containsString("<Inhalt>"));
		assertThat(xml, containsString("</Inhalt>"));
		assertThat(xml, containsString("</XDIML>"));
	}

	@Test
	public void shouldWriteSpellCheckAttributes() throws Exception {
		Spell spell = new Spell();
		spell.setCheckAttributes("MU/KL/IN");

		Spells.setSpells(Arrays.asList(spell));

		String xml = writeXmlString();

		assertThat(xml, containsString("<Probe>MU/KL/IN</Probe>"));
	}

	@Test
	public void shouldWriteSpellComplexity() throws Exception {
		Spell spell = new Spell();
		spell.setComplexity(Complexity.A);

		Spells.setSpells(Arrays.asList(spell));

		String xml = writeXmlString();

		assertThat(xml, containsString("<Komplexität>A</Komplexität>"));
	}

	@Test
	public void shouldWriteSpellTraits() throws Exception {
		Spell spell = new Spell();
		spell.setTraits(EnumSet.of(Trait.OBJEKT, Trait.SCHADEN));

		Spells.setSpells(Arrays.asList(spell));

		String xml = writeXmlString();

		assertThat(xml, containsString("<Merkmale>"));
		assertThat(xml, containsString("<Merkmal>Objekt</Merkmal>"));
		assertThat(xml, containsString("<Merkmal>Schaden</Merkmal>"));
		assertThat(xml, containsString("</Merkmale>"));
	}

	@Test
	public void shouldWriteSpellVariants() throws Exception {
		Spell spell = new Spell();
		spell.setVariants(Arrays.asList("Variante1", "Variante2"));

		Spells.setSpells(Arrays.asList(spell));

		String xml = writeXmlString();

		assertThat(xml, containsString("<Varianten>"));
		assertThat(xml, containsString("<Variante>Variante1</Variante>"));
		assertThat(xml, containsString("<Variante>Variante2</Variante>"));
		assertThat(xml, containsString("</Varianten>"));
	}

	@Test
	public void shouldWriteMultipleSpells() throws Exception {
		Spell spell1 = createSpell("one");
		Spell spell2 = createSpell("two");
		List<Spell> twoSpells = Arrays.asList(spell1, spell2);

		Spells.setSpells(twoSpells);

		String xml = writeXmlString();

		assertThat(xml, containsString("<Zaubersprüche>"));
		assertThat(xml, containsString("<Zauber ID=\"one\"/>"));
		assertThat(xml, containsString("<Zauber ID=\"two\"/>"));
		assertThat(xml, containsString("</Zaubersprüche>"));
	}

	private Spell createSpell(String name) {
		Spell spell = new Spell();
		spell.setName(name);
		return spell;
	}

	private String writeXmlString() {
		StringWriter stringWriter = new StringWriter();
		spellXmlWriter.write(stringWriter);
		return stringWriter.toString();
	}
}
