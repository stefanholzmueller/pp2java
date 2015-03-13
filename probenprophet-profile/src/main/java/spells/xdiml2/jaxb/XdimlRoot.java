package spells.xdiml2.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("unused")
@XmlRootElement(name = "XDIML")
@XmlAccessorType(XmlAccessType.FIELD)
public class XdimlRoot {

	@XmlAttribute
	private String lang = "de";

	@XmlAttribute
	private String version = "2.0";

	@XmlElement(name = "Inhalt")
	private XdimlContent xdimlContents = new XdimlContent();

	public XdimlContent getXdimlContents() {
		return xdimlContents;
	}

	public void setXdimlContents(XdimlContent xdimlContents) {
		this.xdimlContents = xdimlContents;
	}

}
