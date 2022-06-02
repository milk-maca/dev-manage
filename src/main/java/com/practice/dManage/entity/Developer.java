package com.practice.dManage.entity;


import com.practice.dManage.type.DeveloperLevel;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected  Long id;

    @Enumerated(EnumType.STRING)
    private DeveloperLevel developerLevel;
}
