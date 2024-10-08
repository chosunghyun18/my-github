package com.cho.system.member.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.util.Assert;
import com.cho.system.global.common.domain.basetime.BaseTimeEntity;

@Getter
@Entity
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    final private static Long NICK_NAME_MAX_LENGTH = 30L;
    final private static Long EMAIL_MAX_LENGTH = 320L;
    private final static Integer USER_MAX_EXP = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_nickname")
    private String nickName;

    @Column(name = "member_email", unique = true)
    private String email;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_level")
    private int level;

    @Column(name = "member_exp")
    private int exp;
    @ColumnDefault("'/base/baseLogo.png'")
    @Column(name = "member_image_url")
    private String imageUrl;

    @Column(name = "member_delete")
    private boolean delete;

    @Column(name = "member_block")
    private boolean block;

    @Column(name = "member_atk")
    private String accessToken;

    @Column(name = "member_rtk")
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<FeedLike> feedLikes = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Scrap> scraps = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Review> reviews = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Feed> feeds = new ArrayList<>();

    @Builder(builderClassName = "ByAccountBuilder", builderMethodName = "ByAccountBuilder")
    public Member(String nickName, String email) {
        validateNickname(nickName);
        validateEmail(email);
        this.role = Role.GUEST;
        this.nickName = nickName;
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.password = email;
    }

    // 일반 로그인용
    public Member(String nickName, String email, String password) {
        validateNickname(nickName);
        validateEmail(email);
        this.role = Role.USER;
        this.nickName = nickName;
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.password = password;
        this.imageUrl = "/base/baseLogo.png";
    }

    public void register(String atk, String rtk) {
        this.level = 1;
        this.accessToken = atk;
        this.refreshToken = rtk;
        this.role = Role.USER;
    }

    public void updateExpAndLevel(int addingExp) {
        int changedExp = this.exp + addingExp;
        if (changedExp / USER_MAX_EXP == 0) {
            this.exp = changedExp;
        } else {
            this.exp = changedExp % USER_MAX_EXP;
            this.level = this.level + 1;
        }
    }

    public void updateMemberNickName(String to) {
        Objects.requireNonNull(to);
        validateNickname(to);
        this.nickName = to;
    }

    public Member updateMemberNickNameAndImage(String nickName, String imageUrl) {
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        return this;
    }

    public void blockMember() {
        this.block = true;
    }

    public void unblockMember() {
        this.block = false;
    }

    public void deleteMember() {
        this.delete = true;
    }

    public void setAdminRole() {
        this.role = Role.ADMIN;
    }

    public void setWriterId(Long id) {
        this.id = id;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void updateMemberImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private void validateNickname(String nickname) {
        Assert.isTrue(nickname.length() <= NICK_NAME_MAX_LENGTH, "닉네임 최대 길이를 초과했습니다.");
    }

    private void validateEmail(String email) {
        Assert.isTrue(email.length() <= EMAIL_MAX_LENGTH, "이메일 최대 길이를 초과했습니다.");
    }

    public void updateToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public boolean isNotGuest() {
        return getRole() != Role.GUEST;
    }

    public boolean isGuest() {
        return getRole() == Role.GUEST;
    }

    public void checkPassword(String passWord) {
        Assert.isTrue(this.password.equals(passWord), "비밀번호가 틀렸습니다.");
    }
}
