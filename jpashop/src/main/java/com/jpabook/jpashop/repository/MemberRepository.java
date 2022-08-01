package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository // -> component scan으로 자동으로 spring bean으로 등록됨
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findMember(Long memberId){
        return em.find(Member.class, memberId);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class) // 두붠째 반환타임
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
