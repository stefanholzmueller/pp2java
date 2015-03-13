package rules.concepts;

import java.util.Collection;
import java.util.EnumSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Spell {

	@XmlAttribute(name = "ID")
	private String name;

	@XmlElement(name = "Komplexität")
	private Complexity complexity;

	@XmlElement(name = "Probe")
	private String checkAttributes;

	@XmlElementWrapper(name = "Merkmale")
	@XmlElement(name = "Merkmal")
	private Collection<Trait> traits;

	@XmlElementWrapper(name = "Varianten")
	@XmlElement(name = "Variante")
	private Collection<String> variants;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Complexity getComplexity() {
		return complexity;
	}

	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}

	public String getCheckAttributes() {
		return checkAttributes;
	}

	public void setCheckAttributes(String checkAttributes) {
		this.checkAttributes = checkAttributes;
	}

	public EnumSet<Trait> getTraits() {
		return EnumSet.copyOf(traits);
	}

	public void setTraits(EnumSet<Trait> traits) {
		this.traits = traits;
	}

	public Collection<String> getVariants() {
		return variants;
	}

	public void setVariants(Collection<String> variants) {
		this.variants = variants;
	}

	@Override
	public String toString() {
		return name;
	}

}
