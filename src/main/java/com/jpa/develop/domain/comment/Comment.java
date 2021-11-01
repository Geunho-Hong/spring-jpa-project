package com.jpa.develop.domain.comment;

import com.jpa.develop.domain.board.Board;
import com.jpa.develop.domain.support.BaseTimeEntity;
import com.jpa.develop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.naming.ldap.PagedResultsControl;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name ="tbl_comment")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "par_seq")
    private Board board;

    /*@ManyToOne
    @JoinColumn(name = "reg_id")
    private User user;*/

    @Column(name = "reg_id")
    private String writer;

    private String upd_id;

    public void insertComment(Board board) {
        this.board = board;
    }

}
