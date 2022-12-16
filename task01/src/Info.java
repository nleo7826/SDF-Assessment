public class Info {
    
    private String firstName;
    private String lastName;
    private String address;
    private Integer years;

    public Info(String firstName, String lastName, String address, Integer years) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.years = years;
    }


    public String getFirstName() {
        return firstName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getAddress() {
        return address;
    }



    public void setAddress(String address) {
        this.address = address;
    }



    public Integer getYears() {
        return years;
    }



    public void setYears(Integer years) {
        this.years = years;
    }



}