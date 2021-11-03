package com.jpa.develop.board;

import com.jpa.develop.domain.board.Board;
import com.jpa.develop.domain.board.BoardRepository;
import com.jpa.develop.dto.board.exception.BoardIsNotExistException;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    BoardRepository boardRepository;

    static EnhancedRandom boardCreator;

    @BeforeAll
    static void setUp(){
        boardCreator = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .randomize(f -> f.getName().equals("boardNo"), () -> Long.parseLong("1"))
                .randomize(f -> f.getName().equals("title"),() -> "테스트" )
                .randomize(f -> f.getName().equals("contents"),() -> "컨텐츠 테스트")
                .randomize(f -> f.getName().equals("writer"),() -> "grayson")
                .excludeField(f -> f.getName().equals("regDate"))
                .excludeType(f -> f.getName().equals("modifiedDate"))
                .build();
    }

    @Test
    @DisplayName("게시글 저장 및 조회 하기")
    void insertAndSelectBoard() {

        // given
        Board board = boardCreator.nextObject(Board.class);

        // when
        boardRepository.save(board);
        testEntityManager.flush();

        Board findBoard = boardRepository.findById(board.getBoardNo())
                .orElseThrow(EntityNotFoundException::new);

        // then
        assertThat(findBoard.getBoardNo()).isEqualTo(board.getBoardNo());
        assertThat(findBoard.getTitle()).isEqualTo(board.getTitle());
        assertThat(findBoard.getWriter()).isEqualTo(board.getWriter());

    }

    @Test
    @DisplayName("게시글 수정 하기")
    void updateBoard() {

        // given
        Board board = boardCreator.nextObject(Board.class);

        String title = "수정 제목";
        String contents = "수정 내용";

        // when
        board.updateBoard(title, contents);
        boardRepository.save(board);
        testEntityManager.flush();

        Board findBoard = boardRepository.findById(board.getBoardNo())
                .orElseThrow(EntityNotFoundException::new);

        // then
        assertThat(findBoard.getTitle()).isEqualTo(title);
        assertThat(findBoard.getContents()).isEqualTo(contents);
    }

    @Test
    @DisplayName("게시글 저장 후 삭제하기")
    void insertAndDeleteBoard() {

        // given
        Board board = boardCreator.nextObject(Board.class);

        // when
        boardRepository.save(board);
        testEntityManager.flush();

        boardRepository.deleteById(board.getBoardNo());

        assertThat(boardRepository.findById(board.getBoardNo())).isEmpty();
    }

    

}
