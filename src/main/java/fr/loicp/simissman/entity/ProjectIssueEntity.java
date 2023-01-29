package fr.loicp.simissman.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The Project issue entity.
 */
@Entity
@Table(name = "project_issue")
public class ProjectIssueEntity implements UniqueEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person")
    @SequenceGenerator(name = "seq_person", sequenceName = "seq_person", allocationSize = 1, initialValue = 1000)
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id", updatable = false, insertable = false, referencedColumnName = "id")
    private ProjectEntity project;

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
     * Gets project.
     *
     * @return the project
     */
    public ProjectEntity getProject() {
        return project;
    }

    /**
     * Sets project.
     *
     * @param project the project
     */
    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectIssueEntity that = (ProjectIssueEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(projectId, that.projectId)
                && Objects.equals(title, that.title) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, title, content);
    }
}
