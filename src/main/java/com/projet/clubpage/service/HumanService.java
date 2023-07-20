package com.projet.clubpage.service;

import com.projet.clubpage.entity.Human;
import com.projet.clubpage.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor //final 이나 @NonNull인 필드 값만 파라미터로 받는 생성 자동 생성
public class HumanService {

    private final HumanRepository humanRepository;

    //등록 (엔티티(=테이블)를 인스턴스, 레포에 엔티티 인스턴스 저장)
    @Transactional //insert, delete, update
    public void saveHuman(String name){ //name = @RequestBody String name
        Human human = new Human();  //Human: 클래스 Human 엔티티, 인스턴스
        human.setName(name);    //엔티티 인스턴스 human 의 이름 = 엔티티 클래스의 이름
        humanRepository.save(human); //레포에 엔티티 인스턴스 human 저장.
    }

    //조회
    public List<Human> getHumanList() {
        List<Human> humans = humanRepository.findAll(); //리스트 humans<엔티티 휴먼 클래스 타입>는 레포에서 findAll()를 통해 가져온 값이다.
        return humans;
    }

    //수정


}
