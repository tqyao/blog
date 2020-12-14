package cn.tqyao.blog.web.controller;


import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.common.result.ResultCode;
import cn.tqyao.blog.web.dto.MemberRegisterDTO;
import cn.tqyao.blog.web.service.IMemberService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author -Tanqy
 * @since 2020-11-09
 */
@Validated
@RestController
@RequestMapping("/members")
@Api(tags = "用户-控制层")
public class MemberController {

    //TODO：1.过滤链异常处理（a.登录不传参数 b.传参格式错误...）

    @Autowired
    private IMemberService memberService;

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    @ApiImplicitParam(name = "acToken", value = "access_token", required = true, paramType = "body",
            dataTypeClass = MemberRegisterDTO.class)
    public Result register(@RequestBody MemberRegisterDTO dto){
        memberService.register(dto);
        return Result.success("注册成功！");
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/current")
    public Result getCurrentMember(){
        return Result.success(memberService.getCurrentMember());
    }

    //TODO:
    // 1.了解经过Security过滤器放权和不经过其放权
    // 2.了解Redis缓存应用
    // 5.logout接口
    @ApiOperation(value = "登出")
    @GetMapping("/logout/{access_token}/{refresh_token}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "acToken", value = "access_token", required = true, paramType = "path",
//                    dataTypeClass = String.class),
//            @ApiImplicitParam(name = "refToken", value = "refresh_token", required = true, paramType = "path",
//                    dataTypeClass = String.class)
//    })
    public Result logout(@PathVariable("access_token") String acToken,
                         @PathVariable("refresh_token")String refToken){
        memberService.logout(acToken, refToken);
        return Result.success();
    }

}
