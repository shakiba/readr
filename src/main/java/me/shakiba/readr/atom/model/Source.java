package me.shakiba.readr.atom.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Source {

    @XmlAttribute(namespace = NS.GR)
    String streamId;

    @XmlElement
    Id id;

    @XmlElement
    Content title;

    @XmlElement
    List<Link> link = new ArrayList<Link>();

}
