package com.epam.xml.builder.impl;

import com.epam.xml.builder.TariffParser;
import com.epam.xml.builder.TariffXmlTag;
import com.epam.xml.entity.InternetTariff;
import com.epam.xml.entity.PhoneTariff;
import com.epam.xml.entity.Tariff;
import com.epam.xml.entity.type.OperatorType;
import com.epam.xml.entity.type.Tariffication;
import com.epam.xml.exceptin.TariffException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TariffDomParser implements TariffParser {

    private static final Logger LOGGER = Logger.getLogger(TariffDomParser.class.getName());

    public List<Tariff> parse(String xmlPath) throws TariffException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Tariff> tariffs = new ArrayList<>();

        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            Document document = documentBuilder.parse(xmlPath);
            Element root = document.getDocumentElement();
            NodeList phoneTariffs = root.getElementsByTagName(TariffXmlTag.PHONE_TARIFF.toString());

            for (int i = 0; i < phoneTariffs.getLength(); i++) {
                Element phoneTariffElement = (Element) phoneTariffs.item(i);
                Tariff tariff = parseElement(phoneTariffElement);
                tariffs.add(tariff);
            }
            LOGGER.log(Level.INFO, "PhoneTariffs have been created and added to list.");

            NodeList internetTariffs = root.getElementsByTagName(TariffXmlTag.INTERNET_TARIFF.toString());

            for (int i = 0; i < internetTariffs.getLength(); i++) {
                Element internetTariffElement = (Element) internetTariffs.item(i);
                Tariff tariff = parseElement(internetTariffElement);
                tariffs.add(tariff);
            }
            LOGGER.log(Level.INFO, "InternetTariffs have been created and added to list.");

        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new TariffException("Dom parser error cause: ", e);
        }

        return tariffs;
    }

    private Tariff parseElement(Element tariffElement) {
        String className = tariffElement.getTagName();
        String phoneTariffTag = TariffXmlTag.PHONE_TARIFF.toString();
        Tariff currentTariff = className.equals(phoneTariffTag) ? new PhoneTariff() : new InternetTariff();

        parseTariffFields(tariffElement, currentTariff);

        if (className.equals(phoneTariffTag)) {
            currentTariff = createPhoneTariff(tariffElement, currentTariff);
        } else {
            currentTariff = createInternetTariff(tariffElement, currentTariff);
        }

        return currentTariff;
    }

    private void parseTariffFields(Element tariffElement, Tariff currentTariff) {
        String content = tariffElement.getAttribute(TariffXmlTag.ID.toString());
        currentTariff.setId(content);

        content = tariffElement.getAttribute(TariffXmlTag.AVAILABLE.toString());

        if (!content.isEmpty()) {
            boolean isAvailable = Boolean.parseBoolean(content);
            currentTariff.setAvailable(isAvailable);
        }

        content = getElementTextContent(tariffElement, TariffXmlTag.NAME.toString());
        currentTariff.setName(content);

        content = getElementTextContent(tariffElement, TariffXmlTag.OPERATOR.toString());
        OperatorType operator = OperatorType.valueOf(content);
        currentTariff.setOperator(operator);

        content = getElementTextContent(tariffElement, TariffXmlTag.PAYROLL.toString());
        double payroll = Double.parseDouble(content);
        currentTariff.setPayroll(payroll);

        String tagName = TariffXmlTag.PARAMETERS.toString();
        Element parameters = (Element) tariffElement.getElementsByTagName(tagName).item(0);

        content = getElementTextContent(parameters, TariffXmlTag.TARIFFICATION.toString());
        Tariffication tariffication = Tariffication.valueOf(content);
        currentTariff.getParameters().setTariffication(tariffication);

        content = getElementTextContent(parameters, TariffXmlTag.CONNECTION_PRICE.toString());
        double connectionPrice = Double.parseDouble(content);
        currentTariff.getParameters().setConnectionPrice(connectionPrice);
    }

    private Tariff createInternetTariff(Element tariffElement, Tariff currentTariff) {
        String content;
        InternetTariff internetTariff = (InternetTariff) currentTariff;

        content = getElementTextContent(tariffElement, TariffXmlTag.DOWNLOAD_SPEED.toString());
        double downloadSpeed = Double.parseDouble(content);
        internetTariff.setDownloadSpeed(downloadSpeed);

        content = getElementTextContent(tariffElement, TariffXmlTag.UPLOAD_SPEED.toString());
        double uploadSpeed = Double.parseDouble(content);
        internetTariff.setUploadSpeed(uploadSpeed);

        currentTariff = internetTariff;

        return currentTariff;
    }

    private Tariff createPhoneTariff(Element tariffElement, Tariff currentTariff) {
        String tagName;
        String content;
        PhoneTariff phoneTariff = (PhoneTariff) currentTariff;

        tagName = TariffXmlTag.CALL_PRICE.toString();
        Element callPrice = (Element) tariffElement.getElementsByTagName(tagName).item(0);

        content = getElementTextContent(callPrice, TariffXmlTag.NETWORK_CALL_PRICE.toString());
        double networkCallPrice = Double.parseDouble(content);
        phoneTariff.getCallPrice().setNetworkCallPrice(networkCallPrice);

        content = getElementTextContent(callPrice, TariffXmlTag.ROAMING_CALL_PRICE.toString());
        double roamingCallPrice = Double.parseDouble(content);
        phoneTariff.getCallPrice().setRoamingCallPrice(roamingCallPrice);

        content = getElementTextContent(tariffElement, TariffXmlTag.SMS_PRICE.toString());
        double smsPrice = Double.parseDouble(content);
        phoneTariff.setSmsPrice(smsPrice);

        currentTariff = phoneTariff;

        return currentTariff;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);

        return node.getTextContent();
    }
}