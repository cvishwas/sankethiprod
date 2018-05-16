package com.support.common.util;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {

	private String xmlFileName;

	public XmlParser() {

	}

	public XmlParser(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Document getDocument(File xmlFile) throws Exception {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		this.xmlFileName = xmlFile.getName();
		Document doc = docBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		return doc;
	}

	public Document getDocument() throws Exception {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(new File(xmlFileName));
		doc.getDocumentElement().normalize();
		return doc;
	}

	public Map<String, Object> parse() throws Exception {
		Document doc = getDocument();

		Map<String, Object> parsedData = parse(doc.getDocumentElement(), null);
		System.out.println(parsedData);
		return parsedData;
	}

	public Map<String, Object> parse(Document document, String xpathStr) throws Exception {
		NodeList nodeList = getNodeList(document, xpathStr);

		Map<String, Object> parsedData = null;
		if (nodeList != null) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				parsedData = parse(nodeList.item(i), null);
				System.out.println(parsedData);
			}
		}
		return parsedData;
	}

	public NodeList getNodeList(Document document, String xpathStr) throws Exception, XPathExpressionException {
		if (document == null)
			document = getDocument();
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xPath.compile(xpathStr).evaluate(document, XPathConstants.NODESET);
		return nodeList;
	}

	public Map<String, Object> parse(Node node, Map<String, Object> parsedData) {
		if (parsedData == null)
			parsedData = new HashMap<>();

		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			String nodeName = element.getTagName();
			String paretNodeName = element.getParentNode().getNodeName();
			String nodeValue = getElementValue(element, nodeName);

			System.out.println("current Node:" + nodeName + " parent:" + paretNodeName + ",val:" + nodeValue);

			Map<String, Object> currentNodeData = new HashMap<>();

			if (node.hasAttributes()) {
				NamedNodeMap attributes = node.getAttributes();
				for (int j = 0; j < attributes.getLength(); j++) {
					Node item = attributes.item(j);
					System.out.println("attribute:" + item.getNodeName() + "==" + item.getNodeValue());
					currentNodeData.put(item.getNodeName(), item.getNodeValue());
				}
			}

			if (parsedData.containsKey(nodeName)) {
				System.out.println("=====================nodename exists already in parsedDta:" + nodeName);
				Object existingData = parsedData.get(nodeName);
				System.out.println("==============existingData:" + nodeName + "==" + existingData);

				LinkedList<Map<String, Object>> listData = new LinkedList<>();
				if (existingData instanceof Map) {
					listData.add((Map) existingData);
					listData.add(currentNodeData);
				}
				else if (existingData instanceof List) {
					listData = (LinkedList<Map<String, Object>>) existingData;
					listData.add(currentNodeData);
				}
				parsedData.put(nodeName, listData);
			}
			else {
				parsedData.put(nodeName, currentNodeData);
			}

			if (node.hasChildNodes()) {
				for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
					if (child.getNodeType() == Node.ELEMENT_NODE) {
						System.out.println("child node:" + child.getNodeName() + ", parrent:"
								+ child.getParentNode().getNodeName());
						parse(child, currentNodeData);
					}
					else if (child.getNodeType() == Node.TEXT_NODE) {
						if (StringUtils.isNotBlank(child.getTextContent()))
							parsedData.put(nodeName, child.getTextContent());
					}

				}
			}

		}
		return parsedData;
	}

	protected String getElementValue(Element element, String tagName) {
		NodeList list = element.getElementsByTagName(tagName);
		if (list != null && list.getLength() > 0) {
			NodeList subList = list.item(0).getChildNodes();

			if (subList != null && subList.getLength() > 0) {
				return subList.item(0).getNodeValue();
			}
		}

		if (((Node) element).hasChildNodes())
			return ((Node) element).getFirstChild().getTextContent();

		return null;
	}

	public static void main(String[] args) throws Exception {
		String landingZone = "C:/work/SmartDocs/";
		String kofaxFile = landingZone + "K416583B.xml";

		// String kofaxFile = landingZone + "tuorial.xml";

		XmlParser xmlParser = new XmlParser(kofaxFile);
		// xmlParser.parse();

		String xpath = "/XMLRELEASE/KOFAXXML/BATCHCLASS/DOCUMENTS";
		xmlParser.parse(null, xpath);
	}

}
