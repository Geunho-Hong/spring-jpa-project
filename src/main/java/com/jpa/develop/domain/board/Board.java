package com.jpa.develop.domain.board;

import com.jpa.develop.domain.support.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tbl_board")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;

    private String contents;

    private String writer;

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.modifiedDate = LocalDateTime.now();
    }

    /*public void update2(String title, String contents,String writer) {
        this.title = title;
        this.contents = contents;
        this.writer = writer
        this.modifiedDate = LocalDateTime.now();
    }*/

    /*public void update2(Board updateBoard) {
        this.title = updateBoard.getTitle();
        this.contents = updateBoard.getContents();
        this.modifiedDate = LocalDateTime.now();
    }*/

}
