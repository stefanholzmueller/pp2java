package rules.concepts;

import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlEnumValue;

public enum Trait {

	@XmlEnumValue(value = "Antimagie")
	ANTIMAGIE, //
	@XmlEnumValue(value = "Beschwörung")
	BESCHWÖRUNG,
	@XmlEnumValue(value = "Dämonisch")
	DÄMONISCH,
	@XmlEnumValue(value = "Dämonisch (Amazeroth)")
	DÄMONISCH_AMAZEROTH,
	@XmlEnumValue(value = "Dämonisch (Asfaloth)")
	DÄMONISCH_ASFALOTH,
	@XmlEnumValue(value = "Dämonisch (Blakharaz)")
	DÄMONISCH_BLAKHARAZ,
	@XmlEnumValue(value = "Dämonisch (Lolgramoth)")
	DÄMONISCH_LOLGRAMOTH,
	@XmlEnumValue(value = "Dämonisch (Mishkhara)")
	DÄMONISCH_MISHKHARA,
	@XmlEnumValue(value = "Dämonisch (Thargunitoth)")
	DÄMONISCH_THARGUNITOTH,
	@XmlEnumValue(value = "Eigenschaften")
	EIGENSCHAFTEN,
	@XmlEnumValue(value = "Einfluss")
	EINFLUSS,
	@XmlEnumValue(value = "Elementar")
	ELEMENTAR,
	@XmlEnumValue(value = "Elementar (Eis)")
	ELEMENTAR_EIS,
	@XmlEnumValue(value = "Elementar (Erz)")
	ELEMENAR_ERZ,
	@XmlEnumValue(value = "Elementar (Feuer)")
	ELEMENTAR_FEUER,
	@XmlEnumValue(value = "Elementar (Humus)")
	ELEMENAR_HUMUS,
	@XmlEnumValue(value = "Elementar (Luft)")
	ELEMENTAR_LUFT,
	@XmlEnumValue(value = "Elementar (Wasser)")
	ELEMENTAR_WASSER,
	@XmlEnumValue(value = "Form")
	FORM,
	@XmlEnumValue(value = "Geisterwesen")
	GEISTERWESEN,
	@XmlEnumValue(value = "Heilung")
	HEILUNG,
	@XmlEnumValue(value = "Hellsicht")
	HELLSICHT,
	@XmlEnumValue(value = "Herbeirufung")
	HERBEIRUFUNG,
	@XmlEnumValue(value = "Herrschaft")
	HERRSCHAFT,
	@XmlEnumValue(value = "Illusion")
	ILLUSION,
	@XmlEnumValue(value = "Kraft")
	KRAFT,
	@XmlEnumValue(value = "Limbus")
	LIMBUS,
	@XmlEnumValue(value = "Metamagie")
	METAMAGIE,
	@XmlEnumValue(value = "Objekt")
	OBJEKT,
	@XmlEnumValue(value = "Schaden")
	SCHADEN,
	@XmlEnumValue(value = "Telekinese")
	TELEKINESE,
	@XmlEnumValue(value = "Temporal")
	TEMPORAL,
	@XmlEnumValue(value = "Umwelt")
	UMWELT,
	@XmlEnumValue(value = "Verständigung")
	VERSTÄNDIGUNG;

	public String toString() {
		try {
			Field enumConstant = Trait.class.getField(name());
			XmlEnumValue xmlEnumValue = enumConstant.getAnnotation(XmlEnumValue.class);
			return xmlEnumValue.value();
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	};
}
