package x.readr.atom.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
public class Category {

    @XmlAttribute
    String term;

    @XmlAttribute
    String scheme;

    @XmlAttribute
    String label;

    @XmlValue
    String value;
}