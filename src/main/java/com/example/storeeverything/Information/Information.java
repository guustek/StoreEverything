package com.example.storeeverything.Information;

import com.example.storeeverything.Category.Category;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
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
    @Basic
    @Column(name = "added_date", nullable = false)
    private Date addedDate;
    @Basic
    @Column(name = "remind_date", nullable = false)
    private Date remindDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

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
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(addedDate, that.addedDate) && Objects.equals(remindDate, that.remindDate) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, addedDate, remindDate, category);
    }
}
