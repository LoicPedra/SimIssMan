package fr.loicp.simissman.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * The Project entity for repository.
 */
@Entity
@Table(name = "project")
public class ProjectEntity implements UniqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_project")
    @SequenceGenerator(name = "seq_project", sequenceName = "seq_project", allocationSize = 1, initialValue = 1000)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(name = "project_member",
            joinColumns = @JoinColumn( name = "project_id" ),
            inverseJoinColumns = @JoinColumn( name = "person_id" ) )
    private Set<PersonEntity> members;

    @OneToMany(mappedBy="project", fetch = FetchType.LAZY)
    private Set<ProjectIssueEntity> issues;

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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets members.
     *
     * @return the members
     */
    public Set<PersonEntity> getMembers() {
        return members;
    }

    /**
     * Sets members.
     *
     * @param members the members
     */
    public void setMembers(Set<PersonEntity> members) {
        this.members = members;
    }

    /**
     * Gets issues.
     *
     * @return the issues
     */
    public Set<ProjectIssueEntity> getIssues() {
        return issues;
    }

    /**
     * Sets issues.
     *
     * @param issues the issues
     */
    public void setIssues(Set<ProjectIssueEntity> issues) {
        this.issues = issues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
