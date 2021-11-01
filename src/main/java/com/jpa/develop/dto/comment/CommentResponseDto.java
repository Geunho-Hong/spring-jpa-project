package com.jpa.develop.dto.comment;

import com.jpa.develop.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentResponseDto {

    private final Long seq;
    private String contents;
    private String writer;

    public static CommentResponseDto toCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .seq(comment.getSeq())
                .contents(comment.getContents())
                .writer(comment.getWriter())
                .build();
    }
}
