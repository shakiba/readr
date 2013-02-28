package me.shakiba.readr.atom.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Author {

    @XmlElement
    List<String> name;

    @XmlAttribute(name = "unknown-author", namespace = NS.GR)
    Boolean unknownAuthor;
}