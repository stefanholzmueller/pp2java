package spells.xdiml2;

import java.io.Reader;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import rules.concepts.Spell;
import spells.xdiml2.jaxb.XdimlRoot;

public class SpellXmlLoader {

	public Collection<Spell> load(Reader reader) {
		try {
			JAXBContext context = JAXBContext.newInstance(XdimlRoot.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			XdimlRoot xdimlRoot = (XdimlRoot) unmarshaller.unmarshal(reader);
			return xdimlRoot.getXdimlContents().getSpells();
		} catch (JAXBException e) {
			throw new RuntimeException("JAXB unmarshalling failed", e);
		}
	}

}
