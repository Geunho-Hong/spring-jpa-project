package com.jpa.develop.domain.board;

import com.jpa.develop.dto.board.exception.BoardIsNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board insertBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board selectBoard(final Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardIsNotExistException("해당 게시판은 존재하지 않습니다"));
    }

    @Transactional
    public Long updateBoard(final Long boardId , Board updateBoard) {
        Board board = selectBoard(boardId);
        board.updateBoard(updateBoard.getTitle(), updateBoard.getContents());
        return board.getBoardNo();
    }

    @Transactional
    public void deleteBoard(final Long boardId) {
        boardRepository.deleteById(boardId);
    }

}
