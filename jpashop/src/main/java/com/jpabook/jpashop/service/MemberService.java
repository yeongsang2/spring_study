package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //회원 가입
    public Long join(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {


    }

    //회원 전체 조회

}
