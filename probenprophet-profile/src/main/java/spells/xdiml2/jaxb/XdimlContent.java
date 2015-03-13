package spells.xdiml2.jaxb;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import rules.concepts.Spell;

@XmlAccessorType(XmlAccessType.FIELD)
public class XdimlContent {

	@XmlElementWrapper(name = "Zaubersprüche")
	@XmlElement(name = "Zauber")
	private Collection<Spell> spells;

	public Collection<Spell> getSpells() {
		return spells;
	}

	public void setSpells(Collection<Spell> spells) {
		this.spells = spells;
	}

}
