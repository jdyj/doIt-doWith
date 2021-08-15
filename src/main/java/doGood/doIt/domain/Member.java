package doGood.doIt.domain;

import lombok.Builder;
import lombok.Getter;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String email;

    private String name;

    private String code;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Friend> friends = new HashSet<>();

    private String profileUrl;

    @Builder
    public Member(@NonNull String email, String name, String code, String profileUrl, LocalDateTime createdAt) {
        this.email = email;
        this.name = name;
        this.code = code;
        this.profileUrl = profileUrl;
        this.createdAt = createdAt;
    }

    protected Member() { }
}
