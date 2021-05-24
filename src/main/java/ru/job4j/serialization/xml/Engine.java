package ru.job4j.serialization.xml;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "engine")
public class Engine {

    @XmlAttribute
    private String vincode;

    public Engine() { }

    public Engine(String vincode) {
        this.vincode = vincode;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "vincode='" + vincode + '\''
                + '}';
    }
}