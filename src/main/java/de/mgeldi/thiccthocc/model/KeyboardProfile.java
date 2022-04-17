package de.mgeldi.thiccthocc.model;

import javax.persistence.*;
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
    @JoinColumn(name = "ownerId")
    private User owner;


    @ElementCollection
    @MapKeyColumn(name = "key")
    @CollectionTable(name = "keymap",
            joinColumns = {@JoinColumn(name = "keyboardProfileId")})
    private Map<String, Byte[]> soundBytes;

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
}
