package me.shakiba.readr.atom.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType
public class Generator {

    @XmlAttribute
    String uri;

    @XmlValue
    String value;

}