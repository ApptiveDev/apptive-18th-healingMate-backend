package apptive.backend.post.entity;

import apptive.backend.config.StringListConverter;
import apptive.backend.login.domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String postTitle;

    private String category;

    private String postContent;

    @Convert(converter = StringListConverter.class)
    private List<String> postPhotos;

    private Long isLike;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //member N:1
    @ManyToOne
    @JoinColumn(name = "member_memberId")
    @ToString.Exclude
    @JsonIgnore
    private Member member;

    //comment 1:N, post별 모든 comment조회
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Comment> commentList = new ArrayList<>();
}
