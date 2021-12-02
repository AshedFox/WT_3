package bsuir.wt.lab3.entity;

import java.util.StringJoiner;

public class User {
    private int id;
    private String email;
    private String password;
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!email.equals(user.email)) return false;
        if (!password.equals(user.password)) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("email='" + email + "'")
                .add("password='" + password + "'")
                .add("role=" + role)
                .toString();
    }

    public static User parse(String str) {
        var user = new User();

        var paramsString = str.substring("User".length(), str.length() - 1);
        var params = paramsString.split(", ");

        user.setId(Integer.parseInt(params[0].substring(params[0].indexOf("=") + 1)));
        user.setEmail(params[1].substring(params[1].indexOf("=") + 2, params[1].length() - 1));
        user.setPassword(params[2].substring(params[2].indexOf("=")  + 2, params[2].length() - 1));
        user.setRole(Role.valueOf(params[3].substring(params[3].indexOf("=") + 1)));

        return user;
    }
}
