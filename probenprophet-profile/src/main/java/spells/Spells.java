package spells;

import java.util.Collection;

import rules.concepts.Spell;

public class Spells {

	private static Collection<Spell> spells;

	public static Collection<Spell> getSpells() {
		return spells;
	}

	public static void setSpells(Collection<Spell> spells) {
		Spells.spells = spells;
	}

}
