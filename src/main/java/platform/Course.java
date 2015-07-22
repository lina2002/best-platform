package platform;

public class Course {
    private int id;
    private String name;
    private String description;

    public static Course getNoSuchCourse() {
        Course noSuchCourse = new Course();
        noSuchCourse.setName("No such course");
        noSuchCourse.setDescription("");
        noSuchCourse.setId(-1);
        return noSuchCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "domain.Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}