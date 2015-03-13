package spells.xdiml2;

import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import spells.Spells;
import spells.xdiml2.jaxb.XdimlContent;
import spells.xdiml2.jaxb.XdimlRoot;

public class SpellXmlWriter {

	public void write(Writer writer) {
		XdimlContent xdimlContents = new XdimlContent();
		xdimlContents.setSpells(Spells.getSpells());

		XdimlRoot xdimlRoot = new XdimlRoot();
		xdimlRoot.setXdimlContents(xdimlContents);

		try {
			JAXBContext context = JAXBContext.newInstance(XdimlRoot.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(xdimlRoot, writer);
		} catch (JAXBException e) {
			throw new RuntimeException("JAXB marshalling failed", e);
		}
	}

}
