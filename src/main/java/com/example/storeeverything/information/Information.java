package com.example.storeeverything.information;

import com.example.storeeverything.category.Category;
import com.example.storeeverything.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "information")
public class Information {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = false)
    @Size(min = 3, max = 20, message = "Size must be between 3 and 20")
    @NotBlank(message = "Must not be empty")
    private String title;
    @Basic
    @Column(name = "content", nullable = false)
    @Size(min = 5, max = 500, message = "Size must be between 5 and 500")
    @NotBlank(message = "Must not be empty")
    private String content;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "information_category_fk"))
    @Valid
    private Category category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "information_user_fk"))
    private User user;

    @Basic
    @Column(name = "link")
    private String link;
    @Basic
    @CreationTimestamp
    @Column(name = "added_date", nullable = false)
    private LocalDate addedDate;
    @Basic
    @Column(name = "remind_date")
    private LocalDate remindDate;

    @Basic
    @Column(name = "shared_for_all", nullable = false, columnDefinition = "boolean default false")
    private boolean sharedForAll = false;

    @Basic
    @Column(name = "share_link")
    private String shareLink;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Information that = (Information) o;
        return Objects.equals(title, that.title)
                && Objects.equals(content, that.content)
                && Objects.equals(addedDate, that.addedDate)
                && Objects.equals(remindDate, that.remindDate)
                && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, addedDate, remindDate, category);
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", link='" + link + '\'' +
                ", addedDate=" + addedDate +
                ", remindDate=" + remindDate +
                '}';
    }
}
