package com.rondaulz.leaderboard;

public class NewStudentData {
    private String name;
    private String regno;
    private int id;
    private String imageId;
    private FirstSemester firstSemester;
    private SecondSemester secondSemester;
    private ThirdSemester thirdSemester;
    private FourthSemester fourthSemester;

    public NewStudentData() { }

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

    public ThirdSemester getThirdSemester() {
        return thirdSemester;
    }


    public FourthSemester getFourthSemester() {
        return fourthSemester;
    }

    public FirstSemester getFirstSemester() {
        return firstSemester;
    }

    public SecondSemester getSecondSemester() {
        return secondSemester;
    }

}
