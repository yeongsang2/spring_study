package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // spring 제공 annotation
@RequiredArgsConstructor
public class MemberService {

//    @Autowired
    private final MemberRepository memberRepository; // 변경할 일이 없기 떄문에 final keywrod

//    @Autowired // 생성자 주입
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }  --> RequiredArgsConstructor final이 가지고 있는 field 만드러웆ㅁ

    //회원 가입
    @Transactional // reaOnly dafault = false
    public Long join(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {

        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }


    //회원 전체 조회
//    @Transactional(readOnly = true) // 조회하는곳에서 성능 최적화
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

//    @Transactional(readOnly = true) // 조회하는곳에서 성능 최적화
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
