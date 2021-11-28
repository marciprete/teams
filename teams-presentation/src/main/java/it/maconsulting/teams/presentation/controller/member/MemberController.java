package it.maconsulting.teams.presentation.controller.member;

import io.swagger.annotations.Api;
import it.maconsulting.microkernel.annotations.WebAdapter;
import it.maconsulting.teams.application.member.port.in.CreateMemberUseCase;
import it.maconsulting.teams.application.member.port.in.ReadMemberUseCase;
import it.maconsulting.teams.application.member.port.in.request.CreateMemberCommand;
import it.maconsulting.teams.domain.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Michele Arciprete
 * @since 0.0.1-SNAPSHOT
 */

@Slf4j
@WebAdapter
@RestController
@Api(tags = {"Member"})
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final CreateMemberUseCase createMemberUseCase;
    private final ReadMemberUseCase readMemberUseCase;
    private final MemberDtoMapper memberDtoMapper;

    @PostMapping()
    public ResponseEntity<Void> createMember(CreateMemberCommand command) {
        createMemberUseCase.createMember(command);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> findMember(@PathVariable UUID id) {
        return ResponseEntity.ok(readMemberUseCase.findMemberById(new Member.MemberId(id)).map(memberDtoMapper).orElse(null));
    }

    @GetMapping()
    public ResponseEntity<Page<MemberDto>> list(@RequestParam(required = false, defaultValue = "0") int page,
                                                @RequestParam(required = false, defaultValue = "25") @Max(100) int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Member> memberPage = readMemberUseCase.listMembers(pageRequest);
        Page<MemberDto> memberDtos = new PageImpl<>(
                memberPage.getContent().stream().map(memberDtoMapper).collect(Collectors.toList()),
                pageRequest,
                memberPage.getTotalElements()
        );
        return ResponseEntity.ok(memberDtos);
    }
}
