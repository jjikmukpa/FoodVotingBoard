package com.jjikmukpa.project.reply.service;

import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.member.repository.MemberRepository;
import com.jjikmukpa.project.reply.model.dto.ReplyDTO;
import com.jjikmukpa.project.reply.model.entity.Reply;
import com.jjikmukpa.project.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void updateReply(Long replyNo, String replyContent) {
        Reply reply = replyRepository.findById(replyNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reply No:" + replyNo));

        reply.setReplyContent(replyContent);

        replyRepository.save(reply);
    }

    public ReplyDTO findReplyByNo(Long replyNo) {
        Reply reply = replyRepository.findById(replyNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reply No: " + replyNo));

        ReplyDTO replyDto = new ReplyDTO();
        replyDto.setReplyNo(reply.getReplyNo());
        replyDto.setReplyContent(reply.getReplyContent());
        replyDto.setReplyDate(LocalDateTime.parse(reply.getReplyDate().toString()));
        replyDto.setMember(reply.getMember());
        replyDto.setPost(reply.getPost());
        replyDto.setEditMode(true);

        return replyDto;
    }

    public List<Reply> findRepliesByPostNo(Long postNo) {
        return replyRepository.findByPost_PostNo(postNo);
    }

    public Long findPostNoByReplyNo(Long replyNo) {
        return replyRepository.findPostNoByReplyNo(replyNo);
    }
}
