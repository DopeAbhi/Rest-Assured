package Pojo;

public class Api {
    public String getCourseTitle;
    private String courseTitle;
    private String Price;

    public void setPrice(String price) {
        Price = price;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getPrice() {
        return Price;
    }


}
