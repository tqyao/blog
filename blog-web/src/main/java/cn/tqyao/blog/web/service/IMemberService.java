package cn.tqyao.blog.web.service;


import cn.tqyao.blog.entity.Member;
import cn.tqyao.blog.security.domain.JwtAuthenticationToken;
import cn.tqyao.blog.web.config.MemberDetails;
import cn.tqyao.blog.web.dto.MemberRegisterDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-11-09
 */
public interface IMemberService extends IService<Member> {


//    Member loadUserByUsername(String username);

    MemberDetails loadUserByUsername(String username);

    /**
     * 通过用户名查询用户,先查询缓存是否存在
     * @param username
     * @param cache 是否缓存
     * @return
     */
    Member getByUsername(String username, Boolean cache);

    /**
     * 注册
     * @param dto
     */
    void register(MemberRegisterDTO dto);

    /**
     * 获取其当前登录用户
     * @return
     */
    Member getCurrentMember();

    void logout(String accessToken,String refreshToken);

    JwtAuthenticationToken refreshToken(String accessToken, String refreshToken);

    String sendSms(String telephone);
}
