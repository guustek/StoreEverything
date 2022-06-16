package com.example.storeeverything.Information;

import com.example.storeeverything.User.User;
import com.example.storeeverything.Category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
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
    private String title;
    @Basic
    @Column(name = "content", nullable = false)
    @Size(min = 5, max = 500, message = "Size must be between 5 and 500")
    private String content;
    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "information_category_fk"))
    private Category category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "information_user_fk"))
    private User user;

    @Basic
    @Column(name = "shared", nullable = false)
    private boolean shared = false;

    @Basic
    @Column(name = "link", nullable = true)
    private String link;
    @Basic
    @Column(name = "added_date", nullable = false)
    private Date addedDate;
    @Basic
    @Column(name = "remind_date", nullable = true)
    private Date remindDate;

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
