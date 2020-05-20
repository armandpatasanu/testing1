package org.loose.oose.fis.project.model;

public class User {

    private String back_color;
    private String description;
    private String phone;
    private String country;
    private String city;
    private String pic_path;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    public User(){ }

    public User(String fn, String ln, String user, String email, String pass)
    {
        this.firstName=fn;
        this.lastName=ln;
        this.username=user;
        this.email=email;
        this.password=pass;
        this.pic_path= "images/profile_photo.png";
        this.back_color="#b22222";
    }

    public String getBack_color() {
        return back_color;
    }

    public void setBack_color(String back_color) {
        this.back_color = back_color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            User user = (User)o;
            if (!this.username.equals(user.username)) {
                return false;
            }
            if (!this.email.equals(user.email)) {
                return false;

            }else {
                return !this.password.equals(user.password);
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.username.hashCode();
        result = 31 * result + this.password.hashCode();
        result = 31 * result + this.firstName.hashCode();
        result = 31 * result + this.lastName.hashCode();
        result = 31 * result + this.email.hashCode();
        result = 31 * result + this.description.hashCode();
        result = 31 * result + this.city.hashCode();
        result = 31 * result + this.country.hashCode();
        result = 31 * result + this.phone.hashCode();
        return result;
    }

    public String toString() {
        return "UserDTO{first name='" + this.firstName + '\'' +
                ", last name=" + this.lastName + '\'' +
                ", username=" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", email='" + this.email + '\'' +
                ", description=" + this.city + '\'' +
                ", phone=" + this.country + '\'' +
                ", country=" + this.phone + '\'' +
                ", city=" + this.description + '\'' +
                ", image=" + this.pic_path + '\'' +
                ", profile background color=" + this.back_color + '\'' +
                '}';
    }

}
