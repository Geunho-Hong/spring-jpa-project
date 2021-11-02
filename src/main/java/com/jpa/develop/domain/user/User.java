package com.jpa.develop.domain.user;

import com.jpa.develop.domain.support.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "tbl_member")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "usr_no" , nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(name = "usr_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "usr_pw", nullable = false)
    private String userPw;

    @Column(name = "usr_nm")
    private String userName;

    @Column(name = "usr_tel")
    private String userPhoneNumber;

    @Column(name = "usr_bt_dt")
    private LocalDate userBirthDate;

}
