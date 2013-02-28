package me.shakiba.readr.atom.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Feed {

    @XmlAttribute(namespace = NS.IDX)
    String index;

    @XmlAttribute(namespace = NS.GR)
    String dir;

    @XmlElement
    Generator generator;

    @XmlElement
    Id id;

    @XmlElement
    String title;

    @XmlElement
    Content subtitle;

    @XmlElement(namespace = NS.GR)
    String continuation;

    @XmlElement
    List<Link> link = new ArrayList<Link>();

    @XmlElement
    Date updated;

    @XmlElement
    List<Entry> entry = new ArrayList<Entry>();

}