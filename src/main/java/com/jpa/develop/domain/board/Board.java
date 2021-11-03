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
    @Column(name = "board_no" , nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    private String title;

    private String contents;

    private String writer;

    public void updateBoard(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.modifiedDate = LocalDateTime.now();
    }

}
