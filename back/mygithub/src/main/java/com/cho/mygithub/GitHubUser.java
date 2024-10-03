package com.cho.mygithub;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "GIT_HUB_USER")
public class GitHubUser {
    @Id
    @Column(name = "GIT_HUB_USER_PK")
    private Long uniqueId;

    private String id;
}
