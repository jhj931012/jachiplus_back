package com.example.jachiplus_back.entity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
@Entity
@ToString
@Table(name="post")

public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @Column(length = 500, nullable = false,name="title")
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @Column(nullable = false,length = 30)
    private String author;
    @Column(name="modified_user",length = 30)
    private String modifiedUser;

    @Column(columnDefinition = "integer default 0")
    private Integer hit;

    private Boolean isActive;
    private Boolean isDelete;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
