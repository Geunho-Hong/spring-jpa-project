package com.jpa.develop.dto.board;

import com.jpa.develop.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardInsertDto {

    private Long seq;

    @NotEmpty(message = "제목을 입력해 주세요")
    @Size(min = 2 ,max = 20)
    private String title;

    @NotEmpty(message = "내용을 입력해 주세요")
    private String contents;

    @NotEmpty(message = "작성자를 입력해 주세요")
    private String writer;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .contents(contents)
                .writer(writer)
                .build();
    }

}
