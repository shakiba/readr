package x.readr.atom.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
public class Id {

    @XmlValue
    String value;

    @XmlAttribute(name = "original-id", namespace = NS.GR)
    String originalId;
}
