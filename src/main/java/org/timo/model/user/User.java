package org.timo.model.user;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.timo.model.BaseEntity;

import java.util.UUID;

@Table
public class User extends BaseEntity {

    private String email;
    private String password;
    private String username;
    private String name;


    public User(UUID id, String email, String password, String username, String name) {
        super(id);
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
    }

    public String getEmail() { return this.email; }
    public String getPassword() { return this.password; }
    public String getUsername() { return this.username; }
    public String getName() { return this.name; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setUsername(String username) { this.username = username; }
    public void setName(String name) { this.name = name; }


    @Override
    public String toString() {
        return "User [username=" + this.username + ", password=" + this.password + ", name=" + this.name + ", email=" + this.email + "]";
    }

}
