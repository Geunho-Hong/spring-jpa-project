package com.jpa.develop.dto.board;

import com.jpa.develop.domain.board.Board;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BoardResponseDto {

    private final Long seq;
    private String title;
    private String contents;
    private String writer;

    public static BoardResponseDto toBoardResponse(Board board) {
        return BoardResponseDto.builder()
                .seq(board.getSeq())
                .title(board.getTitle())
                .contents(board.getContents())
                .writer(board.getWriter())
                .build();
    }

}
