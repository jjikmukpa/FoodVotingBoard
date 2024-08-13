package com.jjikmukpa.project.reply.service;

import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.member.repository.MemberRepository;
import com.jjikmukpa.project.reply.model.entity.Reply;
import com.jjikmukpa.project.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    public Reply saveReply(Reply reply) {

        Member member = reply.getMember();
        if (member != null && member.getMemberId() == null){
            memberRepository.save(member);  // Member가 아직 저장되지 않은 경우 저장

        }
        return replyRepository.save(reply);
    }

    public List<Reply> getReplyByPostNo(long postNo) {
        return replyRepository.findByPost_PostNo(postNo);
    }

}
