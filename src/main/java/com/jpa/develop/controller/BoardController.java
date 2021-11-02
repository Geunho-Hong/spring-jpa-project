package com.jpa.develop.controller;

import com.jpa.develop.common.ApiResponse;
import com.jpa.develop.domain.board.Board;
import com.jpa.develop.domain.board.BoardService;
import com.jpa.develop.domain.comment.Comment;
import com.jpa.develop.domain.comment.CommentService;
import com.jpa.develop.dto.board.BoardInsertDto;
import com.jpa.develop.dto.board.BoardResponseDto;
import com.jpa.develop.dto.board.BoardUpdateDto;
import com.jpa.develop.dto.comment.CommentInsertDto;
import com.jpa.develop.dto.comment.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/{boardId}")
    public ResponseEntity<ApiResponse> selectBoard(@PathVariable final Long boardId) {

        Board board = boardService.selectBoard(boardId);

        ApiResponse response = ApiResponse.builder()
                .message("게시글이 조회 되었습니다")
                .data(BoardResponseDto.toBoardResponse(board))
                .status(201)
                .build();

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> insertBoard(@Valid @RequestBody BoardInsertDto boardInsertDto) {

        Board board = boardService.insertBoard(boardInsertDto.toEntity());

        ApiResponse response = ApiResponse.builder()
                .message("글 등록이 완료 되었습니다")
                .data(BoardResponseDto.toBoardResponse(board))
                .status(201)
                .build();

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<ApiResponse> updateBoard(
            @PathVariable final Long boardId, @Valid @RequestBody BoardUpdateDto boardUpdateDto) {

        Long id = boardService.updateBoard(boardId, boardUpdateDto.toEntity());

        ApiResponse response = ApiResponse.builder()
                .message("글 수정이 완료 되었습니다")
                .data(id)
                .status(201)
                .build();

        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<ApiResponse> deleteBoard(@PathVariable Long boardId) {

        boardService.deleteBoard(boardId);

        ApiResponse response = ApiResponse.builder()
                .message(boardId + " 번 글 삭제가 완료 되었습니다")
                .data(boardId)
                .status(201)
                .build();

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/{boardId}/comment")
    public ResponseEntity<ApiResponse> insertComment(@Valid @RequestBody CommentInsertDto commentInsertDto,
                                                     @PathVariable Long boardId) {

        Comment comment = commentService.insertComment(boardId, commentInsertDto.toEntity());

        ApiResponse response = ApiResponse.builder()
                .message("댓글 등록이 완료 되었습니다")
                .data(CommentResponseDto.toCommentResponseDto(comment))
                .status(201)
                .build();

        return ResponseEntity.status(201).body(response);
    }

}
