package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        final Car car = new Car(true, 2021, new Engine("WVWZZZ3CZLE028910"), new String[] {"AC", "ABS", "Airbag"});

        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Car.class);

        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();

        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        String xml = "";
        try (StringWriter writer = new StringWriter()) {

            // Сериализуем
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}