package com.epam.xml.parser.impl;

import com.epam.xml.parser.TariffParser;
import com.epam.xml.parser.TariffXmlTag;
import com.epam.xml.entity.InternetTariff;
import com.epam.xml.entity.PhoneTariff;
import com.epam.xml.entity.Tariff;
import com.epam.xml.entity.type.OperatorType;
import com.epam.xml.entity.type.Tariffication;
import com.epam.xml.exception.TariffException;
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

            parseElementsByTagName(tariffs, root, TariffXmlTag.PHONE_TARIFF);
            LOGGER.log(Level.INFO, "PhoneTariffs have been created and added to list.");

            parseElementsByTagName(tariffs, root, TariffXmlTag.INTERNET_TARIFF);
            LOGGER.log(Level.INFO, "InternetTariffs have been created and added to list.");

        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new TariffException("Dom parser error cause: ", e);
        }

        return tariffs;
    }

    private void parseElementsByTagName(List<Tariff> tariffs, Element root, TariffXmlTag tagName) {
        NodeList internetTariffs = root.getElementsByTagName(tagName.getValue());

        for (int i = 0; i < internetTariffs.getLength(); i++) {
            Element internetTariffElement = (Element) internetTariffs.item(i);
            Tariff tariff = parseElement(internetTariffElement);
            tariffs.add(tariff);
        }
    }

    private Tariff parseElement(Element tariffElement) {
        String className = tariffElement.getTagName();
        String phoneTariffTag = TariffXmlTag.PHONE_TARIFF.getValue();
        Tariff currentTariff = className.equals(phoneTariffTag) ? new PhoneTariff() : new InternetTariff();

        parseTariffFields(tariffElement, currentTariff);

        if (className.equals(phoneTariffTag)) {
            currentTariff = expandToPhoneTariff(tariffElement, currentTariff);
        } else {
            currentTariff = expandToInternetTariff(tariffElement, currentTariff);
        }

        return currentTariff;
    }

    private void parseTariffFields(Element tariffElement, Tariff currentTariff) {
        String content = tariffElement.getAttribute(TariffXmlTag.ID.getValue());
        currentTariff.setId(content);

        content = tariffElement.getAttribute(TariffXmlTag.AVAILABLE.getValue());

        if (!content.isEmpty()) {
            boolean isAvailable = Boolean.parseBoolean(content);
            currentTariff.setAvailable(isAvailable);
        }

        content = getElementTextContent(tariffElement, TariffXmlTag.NAME.getValue());
        currentTariff.setName(content);

        content = getElementTextContent(tariffElement, TariffXmlTag.OPERATOR.getValue());
        OperatorType operator = OperatorType.valueOf(content);
        currentTariff.setOperator(operator);

        content = getElementTextContent(tariffElement, TariffXmlTag.PAYROLL.getValue());
        double payroll = Double.parseDouble(content);
        currentTariff.setPayroll(payroll);

        String tagName = TariffXmlTag.PARAMETERS.getValue();
        Element parameters = (Element) tariffElement.getElementsByTagName(tagName).item(0);

        content = getElementTextContent(parameters, TariffXmlTag.TARIFFICATION.getValue());
        Tariffication tariffication = Tariffication.findByValue(content);
        currentTariff.getParameters().setTariffication(tariffication);

        content = getElementTextContent(parameters, TariffXmlTag.CONNECTION_PRICE.getValue());
        double connectionPrice = Double.parseDouble(content);
        currentTariff.getParameters().setConnectionPrice(connectionPrice);

    }

    private Tariff expandToInternetTariff(Element tariffElement, Tariff currentTariff) {
        String content;
        InternetTariff internetTariff = (InternetTariff) currentTariff;

        content = getElementTextContent(tariffElement, TariffXmlTag.DOWNLOAD_SPEED.getValue());
        double downloadSpeed = Double.parseDouble(content);
        internetTariff.setDownloadSpeed(downloadSpeed);

        content = getElementTextContent(tariffElement, TariffXmlTag.UPLOAD_SPEED.getValue());
        double uploadSpeed = Double.parseDouble(content);
        internetTariff.setUploadSpeed(uploadSpeed);

        currentTariff = internetTariff;

        return currentTariff;
    }

    private Tariff expandToPhoneTariff(Element tariffElement, Tariff currentTariff) {
        String tagName;
        String content;
        PhoneTariff phoneTariff = (PhoneTariff) currentTariff;

        tagName = TariffXmlTag.CALL_PRICE.getValue();
        Element callPrice = (Element) tariffElement.getElementsByTagName(tagName).item(0);

        content = getElementTextContent(callPrice, TariffXmlTag.NETWORK_CALL_PRICE.getValue());
        double networkCallPrice = Double.parseDouble(content);
        phoneTariff.getCallPrice().setNetworkCallPrice(networkCallPrice);

        content = getElementTextContent(callPrice, TariffXmlTag.ROAMING_CALL_PRICE.getValue());
        double roamingCallPrice = Double.parseDouble(content);
        phoneTariff.getCallPrice().setRoamingCallPrice(roamingCallPrice);

        content = getElementTextContent(tariffElement, TariffXmlTag.SMS_PRICE.getValue());
        double smsPrice = Double.parseDouble(content);
        phoneTariff.setSmsPrice(smsPrice);

        currentTariff = phoneTariff;

        return currentTariff;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);

        return node.getTextContent();
    }
}