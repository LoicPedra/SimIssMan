package fr.loicp.simissman.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The type Project member entity.
 */
@Entity
@Table(name = "project_member")
public class ProjectMemberEntity implements UniqueEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_project_member")
    @SequenceGenerator(name = "seq_project_member", sequenceName = "seq_project_member", allocationSize = 1, initialValue = 1000)
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "person_id")
    private Long personId;

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
     * Gets project id.
     *
     * @return the project id
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * Sets project id.
     *
     * @param projectId the project id
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets person id.
     *
     * @return the person id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * Sets person id.
     *
     * @param personId the person id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMemberEntity that = (ProjectMemberEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(projectId, that.projectId)
                && Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, personId);
    }
}
