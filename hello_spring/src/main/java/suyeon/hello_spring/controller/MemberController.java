package suyeon.hello_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import suyeon.hello_spring.domain.Member;
import suyeon.hello_spring.service.MemberService;

import java.text.Normalizer;
import java.util.List;

@Controller
// 어플리케이션이 실행될 때 Controller가 있으면 자동으로 클래스를 생성
public class MemberController {
    private final MemberService memberService;

    @Autowired
    // 스프링컨테이너에서 아래 클래스를 가져옴
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/create")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/create")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        System.out.println(members);
        return "members/memberList";
    }
}
