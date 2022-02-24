package jpabook.jpashop.api;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
//    엔티티를 직접노출 했기에 필요없는 Member의 오더정보도 가져간다 그럴경우 @JsonIgnore를 두어 json형식으로 날라가지않도록 막는방법도있다.
//    또한 이방식은 어레이를 그대로 보내서 data: []에 정보가 담기지않아서 다른정보를 합쳐저 보내는 확장성이 불가능하다.
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result memberV2() {
        List<Member> findmembers = memberService.findMembers();
//        단축키 iter + tap 아래와같아 루프를 돌리면됨
//        for (Member findmember : findmembers) {
//            
//        }
        
        List<MemberDto> collect = findmembers.stream()
//                멤버 엔티티에서 이름을 가져와서 dto로 넣기 이후 리스트로 변환
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {

//      api로 개발시 꼭 dto를 통해서 값을 전달받고 전달하자. 엔티티의 스펙이 view 의존적으로 변하지않도록 주의
//       entity로 받게되면 api문서를 보고도 어떤 정보가 넘어오는지 각 사용 되는 서비스마다 달라서 클라이언트에서 어떤정보가 넘어오는지 알기힘들다
//        따라서 dto를 보고 한눈에 알수있는 것이 좋다.
        Long id = memberService.join(member);

        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member();

        member.setName(request.name);

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);

    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());
//        위에 업데이트 서비스는 업데이트만 아래 조회 서비스는 조회로 각 서비스를 각 기능에 맞게 나누는 것이 유지보수성이 좋다.
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }



    @Data
    static class CreateMemberRequest {
        private String name;
    }


    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
// no-argrument 오류 발생시 innerclass 기때문에 static사용여부를 확인하자
    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
//    data 필드에 data[] 형식으로 보내기위함
    static class Result<T> {
//        private int count; collect.siz() 이런식으로 필드 추가하면 count,data 식으로 전송할수있다.
        private T data;

    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

}
