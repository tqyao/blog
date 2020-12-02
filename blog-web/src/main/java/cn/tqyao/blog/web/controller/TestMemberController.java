package cn.tqyao.blog.web.controller;


import cn.tqyao.blog.common.validated.group.UpdateValidated;
import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.entity.Member;
import cn.tqyao.blog.web.service.IMemberCacheService;
import cn.tqyao.blog.web.service.IMemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import io.swagger.annotations.Api;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Optional;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author -Tanqy
 * @since 2020-11-09
 */
//swagger隐藏controller
@ApiIgnore
@Validated
@RestController
@RequestMapping("/member")
public class TestMemberController {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IMemberCacheService memberCacheService;

    @RequestMapping(value = "/from", method = RequestMethod.POST)
    public Result addFrom(@Validated Member member){
        member.setId(null);
        return Result.status(memberService.save(member));
    }

    @PostMapping
    public Result add(@Validated @RequestBody Member member){
        member.setId(null);
        return Result.status(memberService.save(member));
    }

    @GetMapping("/{id}")
    public Result getMember(@NotBlank(message = "ID 不能为空")@PathVariable("id") String id){
        return Result.success(memberService.getById(id));
    }

    @PutMapping("/{id}")
    public Result updateMember(@PathVariable String id,
                               @Validated(value = {UpdateValidated.class, Default.class}) @RequestBody Member member){
        member.setId(id);
        boolean update = memberService.update(member, new LambdaUpdateWrapper<Member>()
                .eq(Member::getId,member.getId()));
        return Result.status(update);
    }

    @PutMapping("/param/{id}")
    public Result updateMember(@PathVariable String id,
                               @Length(min = 4, message = "用户名至少4位") @NotBlank(message = "username 不能为空") String username,
                               @Length(min = 6, message = "密码至少6位") @NotBlank(message = "password 不能为空") String password){
        Member member = new Member()
                .setUsername(username)
                .setPassword(password);
        member.setId(id);
        return Result.status(memberService.updateById(member));
    }

    @DeleteMapping("/{id}")
    public Result deletedMember(@PathVariable @NotBlank(message = "id不能为空") String id){
        return Result.status(memberService.removeById(id));
    }

    @PostMapping("/cache")
    public Result cacheTest(){
        Member member = Optional.ofNullable(memberService.getBaseMapper().selectList(new LambdaQueryWrapper<>()))
                .flatMap(list -> list.stream().findFirst()).orElseGet(() -> {
                    Member m = new Member();
                    m.setId("none");
                    return m;
                });
        memberCacheService.setMember(member);
        return Result.success(member.getUsername());
    }

    @GetMapping("/cache")
    public Result getCacheTest(@RequestParam String username){
        Member member = memberCacheService.getMember(username);
        System.out.println(member);
        return Result.success(member);
    }
}
