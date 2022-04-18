package de.mgeldi.thiccthocc.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Entity(name = "keyboard_profile")
public class KeyboardProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private UUID keyboardProfileId;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @ElementCollection
    @MapKeyColumn(name = "key")
    @CollectionTable(name = "keymap",
            joinColumns = {@JoinColumn(name = "keyboardProfileId")})
    private Map<String, Byte[]> soundBytes;

    @Length(max = 24, message = "Profile name can only be 24 characters long!")
    @NotBlank(message = "Please enter a keyboard profile name!")
    private String profileName;


    public KeyboardProfile(UUID keyboardProfileId, User owner, Map<String, Byte[]> soundBytes, String profileName) {
        this.keyboardProfileId = keyboardProfileId;
        this.owner = owner;
        this.soundBytes = soundBytes;
        this.profileName = profileName;
    }

    public KeyboardProfile() {

    }

    public User getOwner() {
        return owner;
    }

    public UUID getKeyboardProfileId() {
        return keyboardProfileId;
    }

    public void setKeyboardProfileId(UUID id) {
        this.keyboardProfileId = id;
    }

    public Map<String, Byte[]> getSoundBytes() {
        return soundBytes;
    }

    public void setSoundBytes(Map<String, Byte[]> keymap) {
        this.soundBytes = keymap;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
