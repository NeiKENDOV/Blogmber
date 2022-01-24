package ru.neikendov.blogmber.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotBlank
    private AppUser author;

    @Column(updatable = false)
    @NotBlank
    private LocalDateTime creationTime;

    @Size(max = 100000)
    @NotBlank
    private String message;

    @Size(max = 100)
    private String label;

    @ManyToMany(mappedBy = "likedPosts")
    private Set<AppUser> likes;


}
