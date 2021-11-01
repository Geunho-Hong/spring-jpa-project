package com.jpa.develop.domain.comment;

import com.jpa.develop.domain.board.Board;
import com.jpa.develop.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    public Comment insertComment(Long boardId, Comment comment) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalAccessError("게시판이 존재하지 않습니다"));

        comment.insertComment(board);
        return commentRepository.save(comment);
    }

}


