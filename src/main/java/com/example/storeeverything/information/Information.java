package com.example.storeeverything.information;

import com.example.storeeverything.category.Category;
import com.example.storeeverything.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
    @ManyToOne(cascade = CascadeType.PERSIST)
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
    @Column(name = "added_date", nullable = false, columnDefinition = "date default current_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate addedDate;
    @Basic
    @Column(name = "remind_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

        if (! title.equals(that.title))
            return false;
        if (! content.equals(that.content))
            return false;
        if (! category.equals(that.category))
            return false;
        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", user=" + user +
                ", link='" + link + '\'' +
                ", addedDate=" + addedDate +
                ", remindDate=" + remindDate +
                ", sharedForAll=" + sharedForAll +
                ", shareLink='" + shareLink + '\'' +
                '}';
    }
}
