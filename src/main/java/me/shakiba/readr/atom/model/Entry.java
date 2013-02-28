package me.shakiba.readr.atom.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Entry {
    @XmlAttribute(name = "is-read-state-locked", namespace = NS.GR)
    Boolean isReadStateLocked;

    @XmlAttribute(name = "crawl-timestamp-msec", namespace = NS.GR)
    Long crawlTimestamp;

    @XmlElement
    Id id;

    @XmlElement
    List<Category> category = new ArrayList<Category>();

    @XmlElement
    Content title;

    @XmlElement
    Date published;

    @XmlElement
    Date updated;

    @XmlElement
    Link link;

    @XmlElement
    Content content;

    @XmlElement
    Author author;

    @XmlElement(namespace = NS.GR)
    List<String> likingUser;

    @XmlElement
    Source Source;
}
