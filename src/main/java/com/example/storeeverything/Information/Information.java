package com.example.storeeverything.Information;

import com.example.storeeverything.Category.Category;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "information")
public class Information {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = false)
    private String title;
    @Basic
    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "information_category_pk"))
    private Category category;

    @Basic
    @Column(name = "link", nullable = true)
    private String link;
    @Basic
    @Column(name = "added_date", nullable = false)
    private Date addedDate;
    @Basic
    @Column(name = "remind_date", nullable = true)
    private Date remindDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

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
