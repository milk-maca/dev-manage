package com.practice.dManage.entity;


import com.practice.dManage.type.DeveloperLevel;
import com.practice.dManage.type.DeveloperSkillType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected  Long id;

    @Enumerated(EnumType.STRING)
    private DeveloperLevel developerLevel;

    @Enumerated(EnumType.STRING)
    private DeveloperSkillType developerSkillType;


    private String memberId;
    private String name;
    private Integer experienceYears;
    private Integer age;

    /*
        Main 메서드에 @EnableJpaAuditing 추가
        생성 및 마지막 수정 시점을 저장
    */
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
