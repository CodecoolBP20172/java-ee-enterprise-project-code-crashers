package com.codecool.crashbooks.service;

import com.codecool.crashbooks.model.Member;
import com.codecool.crashbooks.model.memberProperty.Membership;
import com.codecool.crashbooks.repository.MemberRepository;
import com.codecool.crashbooks.utility.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    @Transactional
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

    public void editUsername(String userName, int id){
            Member member = getMemberById(id);
            member.setName(userName);
            memberRepository.save(member);
    }

    public void editPassword(String psw, int id){
            Member member = getMemberById(id);
            member.setPassword(Password.hashPassword(psw));
            memberRepository.save(member);
    }


    public boolean checkIsMemberNameFree(String name) {
        return (getMemberByName(name)== null);
    }

    public void setMembershipById(int id, String membership_type) {
        Member member = getMemberById(id);
        System.out.println(membership_type);
        member.setMembership(Membership.valueOf(membership_type));
        memberRepository.save(member);
    }
}
