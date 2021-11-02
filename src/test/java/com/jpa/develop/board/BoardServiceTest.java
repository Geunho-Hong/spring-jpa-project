package com.jpa.develop.board;

import com.jpa.develop.domain.board.Board;
import com.jpa.develop.domain.board.BoardRepository;
import com.jpa.develop.domain.board.BoardService;
import com.jpa.develop.dto.board.exception.BoardIsNotExistException;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @InjectMocks
    BoardService boardService;

    @Mock
    BoardRepository boardRepository;

    static EnhancedRandom boardRandomObject;

    @BeforeAll
    static void setUp(){
        boardRandomObject = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .randomize(f -> f.getName().equals("title"), () -> "test")
                .build();
    }

    @Test
    @DisplayName("게시글 등록")
    void insertBoard() {

        // given
        Board board  = boardRandomObject.nextObject(Board.class);

        boardService.insertBoard(board);
    }

    @Test
    @DisplayName("게시글 조회")
    void selectBoard() {

        // given
        Board board = boardRandomObject.nextObject(Board.class);

        assertThatThrownBy(() -> boardService.selectBoard(board.getBoardNo()))
                .isInstanceOf(BoardIsNotExistException.class);

    }



}
