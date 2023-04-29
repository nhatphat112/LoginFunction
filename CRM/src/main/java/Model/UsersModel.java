package Model;

public class UsersModel {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private String avatar;
    private RolesModel role;
    // getter and setter


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public RolesModel getRole() {
        return role;
    }

    public void setRole(RolesModel role) {
        this.role = role;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }



    public UsersModel() {
    }

    public UsersModel(int id, String email, String password, String fullName, String avatar, RolesModel role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.role = role;
    }
}
