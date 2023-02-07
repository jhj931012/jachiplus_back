package com.example.jachiplus_back.entity;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "comment")

public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "comment_title", nullable = false, length = 40)
    private String commentTitle;
    @Column(name = "comment_content", nullable = false)
    private String commentContent;
    @Column(name = "comment_author", nullable = false, length = 30)
    private String commentAuthor;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "nickname") //뭐로 매칭할지 생각해보기
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "title")
    private PostEntity post;


}
