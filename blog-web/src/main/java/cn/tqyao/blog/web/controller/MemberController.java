package cn.tqyao.blog.web.controller;


import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.security.JwtAuthenticationToken;
import cn.tqyao.blog.web.dto.MemberRegisterDTO;
import cn.tqyao.blog.web.service.IMemberService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


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
    @GetMapping("/member/current")
    public Result getCurrentMember(){
        return Result.success(memberService.getCurrentMember());
    }

    //TODO:
    // 1.了解经过Security过滤器放权和不经过其放权
    // 2.了解Redis缓存应用
    // 5.logout接口
    @ApiOperation(value = "登出")
    @GetMapping("/member/logout/{refresh_token}")
    @ApiImplicitParam(name = "refresh_token", value = "refresh类型的token", required = true,
            paramType = "path", dataType = "String", dataTypeClass = String.class)
    public Result logout(@ApiIgnore @RequestHeader(value = "Authorization", required = false) String accessToken,
                         @PathVariable("refresh_token")String refreshToken){
        memberService.logout(accessToken, refreshToken);
        return Result.success();
    }

    @ApiOperation(value = "无感登录",notes = "刷新token")
    @GetMapping("/refresh-token/{access-token}/{refresh-token}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access-token", value = "access类型的token", required = true,
                    paramType = "path", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "refresh-token", value = "refresh类型的token", required = true,
                    paramType = "path", dataType = "String", dataTypeClass = String.class)
    })
    public Result<JwtAuthenticationToken> refreshToken(
            @PathVariable(value = "access-token") String accessToken,
            @PathVariable("refresh-token") String refreshToken){

        //TODO: 30分钟内刷新过，返回原token

        return Result.success("刷新成功！",memberService.refreshToken(accessToken, refreshToken));
    }

    @ApiOperation(value = "获取验证码")
    @GetMapping("/member/code")
    @ApiImplicitParam(name = "telephone", value = "手机号", required = true,
            paramType = "query", dataType = "String", dataTypeClass = String.class)
    public Result getAuthCode(@RequestParam("telephone") String telephone){
        return Result.success(memberService.sendSms(telephone));
    }


}
