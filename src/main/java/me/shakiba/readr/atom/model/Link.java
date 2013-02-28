package me.shakiba.readr.atom.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Link {

    @XmlAttribute
    String rel;

    @XmlAttribute
    String href;

    @XmlAttribute
    String type;
}