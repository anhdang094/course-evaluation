package thanhtuu.springmvc.Domain;

public class Chapters extends ChaptersKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chapters.name
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column chapters.teacherId
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    private Integer teacherid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chapters.name
     *
     * @return the value of chapters.name
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chapters.name
     *
     * @param name the value for chapters.name
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column chapters.teacherId
     *
     * @return the value of chapters.teacherId
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    public Integer getTeacherid() {
        return teacherid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column chapters.teacherId
     *
     * @param teacherid the value for chapters.teacherId
     *
     * @mbggenerated Thu Sep 01 21:22:11 ICT 2016
     */
    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }
}