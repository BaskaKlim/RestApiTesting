package tests;

public class House {

    String id;
    String name;
    String mascot;
    String headOfHouse;
    String houseGhost;
    String founder;
    String school;
    String[] members;
    String[] values;
    String[] colors;

    public House(String id, String name, String mascot, String headOfHouse, String houseGhost, String founder, String school, String[] members, String[] values, String[] colors) {
        this.id = id;
        this.name = name;
        this.mascot = mascot;
        this.headOfHouse = headOfHouse;
        this.houseGhost = houseGhost;
        this.founder = founder;
        this.school = school;
        this.members = members;
        this.values = values;
        this.colors = colors;
    }

    public House() {
    }

    public String getId() {
        return id;
    }

    public void setId(String newValue) {
        id = newValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String newValue) {
        name = newValue;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String newValue) {
        mascot = newValue;
    }

    public String getHeadOfHouse() {
        return headOfHouse;
    }

    public void setHeadOfHouse(String newValue) {
        headOfHouse = newValue;
    }

    public String getHouseGhost() {
        return houseGhost;
    }

    public void setHouseGhost(String newValue) {
        houseGhost = newValue;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String newValue) {
        founder = newValue;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String newValue) {
        school = newValue;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] newValue) {
        members = newValue;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] newValue) {
        values = newValue;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] newValue) {
        colors = newValue;
    }
}
