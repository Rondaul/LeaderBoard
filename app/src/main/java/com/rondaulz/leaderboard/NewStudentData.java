package com.rondaulz.leaderboard;

/**
 * Created by Ron on 10/10/2017.
 */

public class NewStudentData {
    private String name;
    private String regno;
    private int id;
    private String imageId;
    private FirstSemester firstSemester;
    private SecondSemester secondSemester;
    private ThirdSemester thirdSemester;
    private FourthSemester fourthSemester;
    private int total;

    public NewStudentData() {

    }

    public NewStudentData(String name, String regno, int id, FirstSemester firstSemester,
                          SecondSemester secondSemester, ThirdSemester thirdSemester, FourthSemester fourthSemester) {
        this.name = name;
        this.regno = regno;
        this.id = id;
        this.firstSemester = firstSemester;
        this.secondSemester = secondSemester;
        this.thirdSemester = thirdSemester;
        this.fourthSemester = fourthSemester;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public void setFirstSemester(FirstSemester firstSemester) {
        this.firstSemester = firstSemester;
    }

    public void setSecondSemester(SecondSemester secondSemester) {
        this.secondSemester = secondSemester;
    }

    public ThirdSemester getThirdSemester() {
        return thirdSemester;
    }

    public void setThirdSemester(ThirdSemester thirdSemester) {
        this.thirdSemester = thirdSemester;
    }

    public FourthSemester getFourthSemester() {
        return fourthSemester;
    }

    public void setFourthSemester(FourthSemester fourthSemester) {
        this.fourthSemester = fourthSemester;
    }

    public FirstSemester getFirstSemester() {
        return firstSemester;
    }

    public SecondSemester getSecondSemester() {
        return secondSemester;
    }

}
