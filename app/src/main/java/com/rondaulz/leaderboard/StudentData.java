package com.rondaulz.leaderboard;

import java.util.Comparator;

/**
 * Created by Ron on 06-08-2017.
 */

public class StudentData {
    private String name;
    private String regno;
    private int id;
    private String imageId;
    private FirstSemester firstSemester;
    private SecondSemester secondSemester;
    private ThirdSemester thirdSemester;
    private FourthSemester fourthSemester;
    private int total;

    public StudentData() {
    }

    public StudentData(String name) {
        this.name = name;
    }

    public StudentData(String name, String regno, int id, String imageId, FirstSemester firstSemester, SecondSemester secondSemester, ThirdSemester thirdSemester, FourthSemester fourthSemester) {
        this.name = name;
        this.regno = regno;
        this.id = id;
        this.imageId = imageId;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        StudentData other = (StudentData) obj;
        if (this.hashCode() != other.hashCode()) {
            return false;
        }
        return true;
    }

    public FirstSemester getFirstSemester() {
        return firstSemester;
    }

    public SecondSemester getSecondSemester() {
        return secondSemester;
    }

}

class MarksComparator implements Comparator<StudentData> {
    @Override
    public int compare(StudentData studentData1, StudentData studentData2) {
        int Marks1Total = studentData1.getTotal();
        int Marks2Total = studentData2.getTotal();

        if (Marks2Total < Marks1Total) {
            return -1;
        } else if (Marks2Total > Marks1Total) {
            return 1;
        } else {
            return 0;
        }
    }

}

 class FirstSemester {
    int digitalElectronics;
     int english;

    public FirstSemester() {
    }

     public FirstSemester(int digitalElectronics, int english) {
         this.digitalElectronics = digitalElectronics;
         this.english = english;
     }

     public int getDigitalElectronics() {
         return digitalElectronics;
     }

     public void setDigitalElectronics(int digitalElectronics) {
         this.digitalElectronics = digitalElectronics;
     }

     public int getEnglish() {
         return english;
     }

     public void setEnglish(int english) {
         this.english = english;
     }
 }

 class SecondSemester {
    int dataStructures;
     int dbms;

    public SecondSemester() {
    }

     public SecondSemester(int dataStructures, int dbms) {
         this.dataStructures = dataStructures;
         this.dbms = dbms;
     }

     public int getDataStructures() {
         return dataStructures;
     }

     public void setDataStructures(int dataStructures) {
         this.dataStructures = dataStructures;
     }

     public int getDbms() {
         return dbms;
     }

     public void setDbms(int dbms) {
         this.dbms = dbms;
     }
 }

 class ThirdSemester {
     int cPlus;
     int operatingSystem;

     public ThirdSemester() {
     }

     public ThirdSemester(int cPlus, int operatingSystem) {
         this.cPlus = cPlus;
         this.operatingSystem = operatingSystem;
     }

     public int getcPlus() {
         return cPlus;
     }

     public void setcPlus(int cPlus) {
         this.cPlus = cPlus;
     }

     public int getOperatingSystem() {
         return operatingSystem;
     }

     public void setOperatingSystem(int operatingSystem) {
         this.operatingSystem = operatingSystem;
     }
 }

 class FourthSemester {
     int unix;
     int vb;

     public FourthSemester() {
     }

     public FourthSemester(int unix, int vb) {
         this.unix = unix;
         this.vb = vb;
     }

     public int getUnix() {
         return unix;
     }

     public void setUnix(int unix) {
         this.unix = unix;
     }

     public int getVb() {
         return vb;
     }

     public void setVb(int vb) {
         this.vb = vb;
     }
 }

