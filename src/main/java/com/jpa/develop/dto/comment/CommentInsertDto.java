package com.jpa.develop.dto.comment;

import com.jpa.develop.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentInsertDto {

    private Long seq;

    private String contents;

    private String writer;

    public Comment toEntity(){
        return Comment.builder()
                .contents(contents)
                .writer(writer)
                .build();
    }

}
