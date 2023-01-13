package Pojo;

public class GetCourses
{
private String url;
private String services;
private String expertise;
private Courses courses;
private String instructor;
private String linkedIn;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getServices() {
        return services;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setCourses(Pojo.Courses courses) {
        this.courses = courses;
    }

    public Courses getCourses() {
        return courses;
    }
}

