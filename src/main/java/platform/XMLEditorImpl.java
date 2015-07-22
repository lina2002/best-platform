package platform;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLEditorImpl implements XMLEditor {
    private File file;

    public XMLEditorImpl(String fileName) {
        this.file = new File(fileName);
    }

    @Override
    public void update(Course course) {
        try {
            tryToUpdate(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryToUpdate(Course course) throws Exception {
        Document document = getDocument();
        updateCourseInDocument(course, document);
        save(document);
    }

    private Document getDocument() throws Exception {
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(file);
    }

    private void updateCourseInDocument(Course course, Document document) {
        Node coursesNode = document.getFirstChild();
        NodeList courses = coursesNode.getChildNodes();
        Node courseToUpdate = courses.item(2 * course.getId() - 1);
        NodeList properties = courseToUpdate.getChildNodes();
        properties.item(1).setTextContent(course.getName());
        properties.item(3).setTextContent(course.getDescription());
    }

    private void save(Document document) throws Exception {
        TransformerFactory transformerFactory =
                TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);
        transformer.transform(source, streamResult);
    }

}
