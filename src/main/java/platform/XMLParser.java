package platform;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private String inputFileName;
    private NodeList nodes;
    private List<Course> courses;

    public XMLParser(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public List<Course> parseXML() {
        try {
            courses = tryToParseXML();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    List<Course> tryToParseXML() throws Exception {
        courses = new ArrayList<Course>();
        initializeNodeList();

        for (int i = 0; i < nodes.getLength(); i++) {
            Course course = new Course();
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                course.setId(Integer.parseInt(element.getAttribute("id")));
                course.setName(getValueByName(element, "name"));
                course.setDescription(getValueByName(element, "description"));
            }
            courses.add(course);
        }
        return courses;
    }

    private void initializeNodeList() throws Exception {
        DocumentBuilderFactory dbFactory
                = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder
                = dbFactory.newDocumentBuilder();

        File inputFile = new File(inputFileName);
        Document document = dBuilder.parse(inputFile);
        document.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/courses/course";
        nodes = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
    }

    private String getValueByName(Element element, String name) {
        return element
                .getElementsByTagName(name)
                .item(0)
                .getTextContent();
    }
}