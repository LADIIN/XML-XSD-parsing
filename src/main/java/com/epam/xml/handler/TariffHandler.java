package com.epam.xml.handler;

import com.epam.xml.parser.TariffXmlTag;
import com.epam.xml.entity.type.OperatorType;
import com.epam.xml.entity.type.Tariffication;
import com.epam.xml.entity.InternetTariff;
import com.epam.xml.entity.PhoneTariff;
import com.epam.xml.entity.Tariff;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;


public class TariffHandler extends DefaultHandler {
    private static final Logger LOGGER = Logger.getLogger(TariffHandler.class.getName());

    private final List<Tariff> tariffs = new ArrayList<>();
    private Tariff currentTariff;
    private TariffXmlTag currentTag;
    private final EnumSet<TariffXmlTag> tagsWithText = EnumSet.range(TariffXmlTag.NAME, TariffXmlTag.UPLOAD_SPEED);

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String phoneTariffTag = TariffXmlTag.PHONE_TARIFF.getValue();
        String internetTariffTag = TariffXmlTag.INTERNET_TARIFF.getValue();

        if (phoneTariffTag.equals(qName) || internetTariffTag.equals(qName)) {
            currentTariff = phoneTariffTag.equals(qName) ? new PhoneTariff() : new InternetTariff();
            defineAttributes(attributes);
        } else {
            TariffXmlTag current = TariffXmlTag.valueOfXmlTag(qName);

            if (tagsWithText.contains(current)) {
                currentTag = current;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String phoneTariffTag = TariffXmlTag.PHONE_TARIFF.getValue();
        String internetTariffTag = TariffXmlTag.INTERNET_TARIFF.getValue();

        if (phoneTariffTag.equals(qName) || internetTariffTag.equals(qName)) {
            tariffs.add(currentTariff);

            LOGGER.log(Level.INFO, "Tariff have been added to list.");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        InternetTariff internetTariff;
        PhoneTariff phoneTariff;

        if (currentTag != null) {
            switch (currentTag) {
                case NAME:
                    currentTariff.setName(data);
                    break;
                case OPERATOR:
                    OperatorType operator = OperatorType.valueOf(data);
                    currentTariff.setOperator(operator);
                    break;
                case PAYROLL:
                    double payroll = Double.parseDouble(data);

                    currentTariff.setPayroll(payroll);
                    break;
                case TARIFFICATION:
                    Tariffication tariffication = Tariffication.findByValue(data);
                    currentTariff.getParameters().setTariffication(tariffication);
                    break;
                case CONNECTION_PRICE:
                    double connectionPrice = Double.parseDouble(data);
                    currentTariff.getParameters().setConnectionPrice(connectionPrice);
                    break;
                case NETWORK_CALL_PRICE:
                    phoneTariff = (PhoneTariff) currentTariff;
                    double networkCallPrice = Double.parseDouble(data);
                    phoneTariff.getCallPrice().setNetworkCallPrice(networkCallPrice);
                    currentTariff = phoneTariff;
                    break;
                case ROAMING_CALL_PRICE:
                    phoneTariff = (PhoneTariff) currentTariff;
                    double roamingCallPrice = Double.parseDouble(data);
                    phoneTariff.getCallPrice().setRoamingCallPrice(roamingCallPrice);
                    currentTariff = phoneTariff;
                    break;
                case SMS_PRICE:
                    phoneTariff = (PhoneTariff) currentTariff;
                    double smsPrice = Double.parseDouble(data);
                    phoneTariff.setSmsPrice(smsPrice);
                    currentTariff = phoneTariff;
                    break;
                case DOWNLOAD_SPEED:
                    internetTariff = (InternetTariff) currentTariff;
                    double downloadSpeed = Double.parseDouble(data);
                    internetTariff.setDownloadSpeed(downloadSpeed);
                    currentTariff = internetTariff;
                    break;
                case UPLOAD_SPEED:
                    internetTariff = (InternetTariff) currentTariff;
                    double uploadSpeed = Double.parseDouble(data);
                    internetTariff.setUploadSpeed(uploadSpeed);
                    currentTariff = internetTariff;
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentTag.getDeclaringClass(), currentTag.name());
            }

            LOGGER.log(Level.INFO, String.format("Parsed data [%s] from tag <%s>", data, currentTag.toString()));
        }

        currentTag = null;

    }

    private void defineAttributes(Attributes attributes) {

        String medProductId = attributes.getValue(TariffXmlTag.ID.getValue());
        currentTariff.setId(medProductId);

        String isAvailable = attributes.getValue(TariffXmlTag.AVAILABLE.getValue());

        if (isAvailable != null) {
            currentTariff.setAvailable(Boolean.parseBoolean(isAvailable));
        }
    }
}
