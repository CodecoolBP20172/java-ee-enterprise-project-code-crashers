package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public Member getMemberById(int id) {
        return memberRepository.findOne(id);
    }

    public Member getMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    public void saveMember(String name, String password) {
        Member member = new Member(name, password);
        memberRepository.save(member);
    }
}
