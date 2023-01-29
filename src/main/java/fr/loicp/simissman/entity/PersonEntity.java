package fr.loicp.simissman.entity;

import jakarta.persistence.*;

/**
 * The Person entity.
 */
@Entity
@Table(name = "person")
public class PersonEntity implements UniqueEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person")
    @SequenceGenerator(name = "seq_person", sequenceName = "seq_person", allocationSize = 1, initialValue = 1000)
    private Long id;

    @Column
    private String username;

    /**
     * Gets unique id.
     *
     * @return the unique id
     */
    @Override
    public Long getUniqueId() {
        return id;
    }

    /**
     * Gets unique id.
     *
     * @param uniqueId the unique id
     */
    @Override
    public void getUniqueId(Long uniqueId) {
        this.id = uniqueId;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
