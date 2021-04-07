package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WeatherManager {
	private static Document convertStringToXMLDocument(final String xmlString) {
		final DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		try {
			builder = docBuildFactory.newDocumentBuilder();
			final Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void getWeather(final WeatherBean weatherBean) throws IOException {
		final String apiKey = "1a8d262349bdd91b476ce4c256dddf9c";
		final String dataURL = "https://api.openweathermap.org/data/2.5/weather?q=" + weatherBean.getCityName() + ","
				+ weatherBean.getCountryName() + "&appid=" + apiKey + "&mode=xml";

		final URL weatherApiUrl = new URL(dataURL);
		final HttpURLConnection weatherUrlConnection = (HttpURLConnection) weatherApiUrl.openConnection();
		weatherUrlConnection.setDoInput(true);
		weatherUrlConnection.setDoOutput(true);
		weatherUrlConnection.setRequestMethod("GET");
		final BufferedReader brIn = new BufferedReader(
				new InputStreamReader(weatherApiUrl.openConnection().getInputStream()));

		String inputLine;
		String apiResponse = "";

		while ((inputLine = brIn.readLine()) != null) {
			apiResponse += inputLine;
		}

		brIn.close();

		// System.out.println(apiResponse);

		final Document doc = convertStringToXMLDocument(apiResponse);
		doc.getDocumentElement().normalize();

		// System.out.println("Root element: " +
		// doc.getDocumentElement().getNodeName());

		final NodeList nList = doc.getElementsByTagName("clouds");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			final Node node = nList.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				final Element eElement = (Element) node;
				final String XMLClouds = eElement.getAttribute("name");
				// System.out.println(XMLClouds);
				weatherBean.setCloudsStr(XMLClouds);
			}
		}

		final NodeList nList2 = doc.getElementsByTagName("temperature");

		for (int temp = 0; temp < nList2.getLength(); temp++) {
			final Node node = nList2.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				final Element eElement = (Element) node;
				final String XMLTemperature = eElement.getAttribute("value");
				// System.out.println(XMLTemperature);
				weatherBean.setTempKelvin(XMLTemperature);
			}
		}
	}
}
