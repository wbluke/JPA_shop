package study.jpashop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.jpashop.dto.MemberRequest;
import study.jpashop.dto.MemberResponse;
import study.jpashop.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody MemberRequest memberRequest) {
        memberService.save(memberRequest);

        return new ResponseEntity<>("Member 저장 성공", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAll() {
        List<MemberResponse> memberResponses = memberService.getAll();

        return new ResponseEntity<>(memberResponses, HttpStatus.OK);
    }
}
