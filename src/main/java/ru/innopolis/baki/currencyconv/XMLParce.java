package ru.innopolis.baki.currencyconv;

import com.sun.xml.bind.v2.model.core.ID;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.innopolis.baki.currencyconv.models.Valute;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLParce {
    public List<Valute> xmlParcer() {
        List<Valute> valutes = new ArrayList<>();
        Valute rubValute = new Valute("RUB01", 000,"RUB", 1,"Российский рубль", 1.0, new Date());
        valutes.add(rubValute);
        try {
            String url = "http://www.cbr.ru/scripts/XML_daily.asp";
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(new URL(url).openStream());

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of values:");
            System.out.println();
            // Просматриваем все подэлементы корневого - т.е. валюты
            NodeList valutesNodeList = root.getChildNodes();
            for (int i = 0; i < valutesNodeList.getLength(); i++) {
                Node valuteNode = valutesNodeList.item(i);
                // Если нода не текст, то это валюта - заходим внутрь
                if (valuteNode.getNodeType() != Node.TEXT_NODE) {
                    Valute valute = new Valute();
                    Element element = (Element) valuteNode;
                    valute.setId(((Element) valuteNode).getAttribute("ID"));
                    valute.setDate(new Date());
                    valute.setNumCode(Integer.valueOf(element.getElementsByTagName("NumCode").item(0).getTextContent()));
                    valute.setCharCode(element.getElementsByTagName("CharCode").item(0).getTextContent());
                    valute.setNominal(Integer.valueOf(element.getElementsByTagName("Nominal").item(0).getTextContent()));
                    valute.setName(element.getElementsByTagName("Name").item(0).getTextContent());
                    valute.setValue(Double.parseDouble(element.getElementsByTagName("Value").item(0).getTextContent().replace(',', '.')));
                    valutes.add(valute);
                }
            }
        } catch (
                ParserConfigurationException | IOException ex) {
            ex.printStackTrace(System.out);
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
        return valutes;
    }
}
