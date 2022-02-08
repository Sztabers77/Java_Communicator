package project.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class XmlParser {

	private static final String FILENAME = "example_xml/conversation.xml";

	public static void parse() {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));

			doc.getDocumentElement().normalize();

			System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
			System.out.println("------");

			NodeList msgs = doc.getElementsByTagName("msg");

			for (int i = 0; i < msgs.getLength(); i++) {

				Node node = msgs.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					// get text
					String nick = element.getElementsByTagName("nick").item(0).getTextContent();
					String text = element.getElementsByTagName("text").item(0).getTextContent();

					System.out.println("Nickname" + nick);
					System.out.println("Text:" + text);
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
