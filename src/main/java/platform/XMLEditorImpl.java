package platform;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLEditorImpl implements XMLEditor {
    private File file;
    private int lastId;

    public XMLEditorImpl(final String fileName) {
        this.file = new File(fileName);
        try {
            Document document = getDocument();
            Node coursesNode = document.getFirstChild();
            NodeList courses = coursesNode.getChildNodes();
            lastId = courses.getLength() / 2;
        } catch (Exception e) {
            System.out.println("XMLEditor was not initialized!");
        }
    }

    @Override
    public void update(final Course course) {
        try {
            tryToUpdate(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(final int courseId) {
        try {
            tryToRemove(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(final Course course) {
        try {
            tryToAdd(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tryToAdd(final Course course) throws Exception {
        Document document = getDocument();
        addCourseToDocument(course, document);
        save(document);
    }

    private void addCourseToDocument(final Course course, final Document document) {
        Node coursesNode = document.getFirstChild();
        Element newCourseNode = createNewElementRepresentingGivenCourse(document, course);
        coursesNode.appendChild(newCourseNode);
    }

    private Element createNewElementRepresentingGivenCourse(final Document document, final Course course) {
        Element newCourseElement = document.createElement("course");
        newCourseElement.setAttribute("id", getNextId().toString());
        Element newCourseNameNode = document.createElement("name");
        newCourseNameNode.setTextContent(course.getName());
        newCourseElement.appendChild(newCourseNameNode);
        Element newCourseDescriptionNode = document.createElement("description");
        newCourseDescriptionNode.setTextContent(course.getDescription());
        newCourseElement.appendChild(newCourseDescriptionNode);
        return newCourseElement;
    }

    private Integer getNextId() {
        return ++lastId;
    }

    private void tryToUpdate(final Course course) throws Exception {
        Document document = getDocument();
        updateCourseInDocument(course, document);
        save(document);
    }

    private void tryToRemove(final int courseId) throws Exception {
        Document document = getDocument();
        removeCourseFromDocument(courseId, document);
        save(document);
    }

    private void removeCourseFromDocument(final int courseId, final Document document) {
        Node coursesNode = document.getFirstChild();
        Node courseToRemove = getCourseById(coursesNode, courseId);
        coursesNode.removeChild(courseToRemove);
    }

    private Node getCourseById(final Node coursesNode, final int courseId) {
        NodeList courses = coursesNode.getChildNodes();
        for (int i = 1; i < courses.getLength(); i += 2) {
            Node currentCourse = courses.item(i);
            String currentCourseIdString = currentCourse.getAttributes().item(0).getTextContent();
            int currentCourseId = Integer.parseInt(currentCourseIdString);
            if (currentCourseId == courseId) {
                return currentCourse;
            }
        }
        return null;
    }

    private Document getDocument() throws Exception {
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(file);
    }

    private void updateCourseInDocument(final Course course, final Document document) {
        Node coursesNode = document.getFirstChild();
        Node courseToUpdate = getCourseById(coursesNode, course.getId());
        NodeList properties = courseToUpdate.getChildNodes();
        properties.item(1).setTextContent(course.getName());
        properties.item(3).setTextContent(course.getDescription());
    }

    private void save(final Document document) throws Exception {
        TransformerFactory transformerFactory =
                TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        DOMSource source = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);
        transformer.transform(source, streamResult);
    }
}
