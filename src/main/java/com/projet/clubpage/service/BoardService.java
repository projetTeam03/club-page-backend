package com.projet.clubpage.service;

import com.projet.clubpage.domain.entity.BoardEntity;
import com.projet.clubpage.domain.repository.BoardRepository;
import com.projet.clubpage.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 비즈니스 로직을 구현
// 데이터 처리(모델)를 담당하는 repository에서 데이터를 가져와서 controller에 넘겨주거나, 비즈니스 로직을 처리
@AllArgsConstructor // Repository를 주입하기 위해 사용
@Service // 서비스 계층임을 명시해주는 어노테이션
public class BoardService {
    private BoardRepository boardRepository;

    @Transactional
    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();

        return boardDTO;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    public List<BoardDto> getBoardlist() {
        return null;
    }
}