package bsuir.wt.lab3.entity.xml;

import bsuir.wt.lab3.entity.User;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlUsers implements Serializable {

    @XmlElements({
            @XmlElement(name = "user", type = User.class)
    })
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
