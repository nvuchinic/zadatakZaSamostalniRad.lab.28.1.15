package ba.bitcamp.nerminVucinic.homework.xml;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * class that reads rss feed(xml tags) from web address(klix.ba), and returns values for  particular tags("title" and "clanak"), 
 * which in turn are used for instantiating objects of Article type. 
 * It creates standard DOM representation of XML file using two classes:DocumentBuilder i DocumentBuilderFactory.
 * @author nermin
 *
 */
public class ArticleReader {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilder docReader = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();

		URL url = new URL("http://www.klix.ba/rss/svevijesti");
		URLConnection connection = url.openConnection();
		Document xmldoc = docReader.parse(connection.getInputStream());
		NodeList xmlItems = xmldoc.getElementsByTagName("item");
		LinkedList articles = new LinkedList<Article>();
		String childTitle = null;
		String childClanak = null;
		Article a = null;
		for (int i = 0; i < xmlItems.getLength(); i++) {
			Node currentItem = xmlItems.item(i);
			if (currentItem instanceof Element) {
				NodeList xmlChildren = currentItem.getChildNodes();
				for (int j = 0; j < xmlChildren.getLength(); j++) {
					Node currentChild = xmlChildren.item(j);
					if (currentChild instanceof Element) {
						Element currentChildElement = (Element) currentChild;
						if (currentChildElement.getTagName().equals("title")) {
							childTitle = currentChildElement.getTextContent();
						}
						if (currentChildElement.getTagName().equals("clanak")) {
							childClanak = currentChildElement.getTextContent();
						}
						a = new Article(childTitle, childClanak);
					}
				}
				articles.add(a);
			}
		}

		Scanner in = new Scanner(System.in);
		System.out
				.println("Unesite redni broj clanka koji zelite procitati(od 1 do 20 ):");
		int brojClanka = in.nextInt();
		int counter = 0;
		Iterator<Article> it = articles.iterator();
		while (it.hasNext()) {
			counter++;

			Article art = it.next();
			if (counter == brojClanka) {
				System.out.println("CLANAK " + counter + "\n" + art.toString()
						+ "\n\n");
				break;
			}
		}
	}
}