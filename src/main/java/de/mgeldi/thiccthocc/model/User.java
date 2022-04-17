package de.mgeldi.thiccthocc.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
public class User implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    @Length(min=3, max=20, message = "Name must be between 3 and 20 characters long!")
    private String username;

    public User(String username, String email, List<KeyboardProfile> keyboardProfile, String profilePictureUrl) {
        this.username = username;
        this.email = email;
        this.keyboardProfile = keyboardProfile;
        this.profilePictureUrl = profilePictureUrl;
    }

    @Email(message = "Not a valid email address!")
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner")
    private List<KeyboardProfile> keyboardProfile;

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    private String profilePictureUrl;

}
