package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean transmission;

    @XmlAttribute
    private int year;

    private Engine engine;

    @XmlElementWrapper(name = "equipments")
    @XmlElement(name = "equipment")
    private String[] equipment;

    public Car() { }

    public Car(boolean transmission, int year, Engine engine, String[] equipment) {
        this.transmission = transmission;
        this.year = year;
        this.engine = engine;
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Car{"
                + "transmission=" + transmission
                + ", year=" + year
                + ", engine=" + engine
                + ", equipment=" + Arrays.toString(equipment)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Car car = new Car(true, 2021, new Engine("WVWZZZ3CZLE028910"), new String[] {"AC", "ABS", "Airbag"});

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
        }
    }
}