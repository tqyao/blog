package cn.tqyao.blog.web.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.tqyao.blog.common.exception.CommonException;
import cn.tqyao.blog.common.result.ResultCode;
import cn.tqyao.blog.common.util.sms.AliyunSmsSendProperties;
import cn.tqyao.blog.entity.Member;
import cn.tqyao.blog.security.domain.JwtAuthenticationToken;
import cn.tqyao.blog.security.exception.TokenAuthenticationException;
import cn.tqyao.blog.security.util.RedisSecurityUtil;
import cn.tqyao.blog.security.util.SecurityUtil;
import cn.tqyao.blog.web.config.MemberDetails;
import cn.tqyao.blog.web.dto.MemberRegisterDTO;
import cn.tqyao.blog.web.mapper.MemberMapper;
import cn.tqyao.blog.web.service.IMemberCacheService;
import cn.tqyao.blog.web.service.IMemberService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-11-09
 */
@Slf4j
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    private static final Integer CODE_LEN = 6;

    /**
     * Bearer
     */
    @Value("${jwt.token-head}")
    private String tokenHead;
    @Autowired
    private AliyunSmsSendProperties smsSendProperties;
    @Autowired
    private RedisSecurityUtil redisSecurityUtil;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IMemberCacheService memberCacheService;


    @Override
    public MemberDetails loadUserByUsername(String username) {
        Member member = Optional.ofNullable(getByUsername(username, true))
                .orElseThrow(() -> new UsernameNotFoundException("用户名或密码错误"));
        return new MemberDetails(member, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public Member getByUsername(String username, Boolean cache) {
        Member cacheMember = memberCacheService.getMember(username);
        if (!ObjectUtils.isEmpty(cacheMember)) {
            log.info("cache user username:{}", cacheMember.getUsername());
        }
        return Optional.ofNullable(cacheMember).orElseGet(() -> {
            Member one = getOne(Wrappers.<Member>lambdaQuery().eq(Member::getUsername, username));
            if (!ObjectUtils.isEmpty(one) && cache) {
                memberCacheService.setMember(one);
            }
            return one;
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(MemberRegisterDTO dto) {
        //TODO 验证验证码是否正确

        //查询数据库用户是否已存在用户
        if (null != getByUsername(dto.getUsername(), false)) {
            throw new CommonException("用户名存在");
        }

        if (null != getOne(Wrappers.<Member>lambdaQuery()
                .select(Member::getUsername)
                .eq(Member::getPhone, dto.getPhone()))) {
            throw new CommonException("手机号已经被绑定");
        }

        Member member = new Member();
        BeanUtils.copyProperties(dto, member);

        //密码加密
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        //TODO 设置默认头像，生成默认用户名
        save(member);
    }

    @Override
    public Member getCurrentMember() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(auth -> Optional.ofNullable(auth.getPrincipal())
                        .filter(obj -> obj instanceof MemberDetails)
                        .map(obj -> (MemberDetails) obj)
                        .orElse(new MemberDetails()).getMember())
                .orElseThrow(() -> new CommonException(ResultCode.UNAUTHORIZED));
    }

    @Override
    public void logout(String accessToken, String refreshToken) {
        Optional.ofNullable(getCurrentMember()).ifPresent(member -> memberCacheService.delMember(member.getId()));
        accessToken = getTokenBody(accessToken);
        refreshToken = getTokenBody(refreshToken);
        redisSecurityUtil.addBlacklistSet(accessToken, refreshToken);
    }

    @Override
    public JwtAuthenticationToken refreshToken(String accessToken, String refreshToken) {
        JwtAuthenticationToken token = null;
        try {
            //先认证刷新
            accessToken = getTokenBody(accessToken);
            refreshToken = getTokenBody(refreshToken);
            token = securityUtil.getRefreshToken(refreshToken);
            //旧token加入黑名单
            redisSecurityUtil.addBlacklistSet(accessToken, refreshToken);
        } catch (TokenAuthenticationException e) {
            throw new CommonException (e.getResultCode ());
        }
        return token;
    }

    @Override
    public String sendSms(String telephone) {
        return null;
    }

    /**
     * 获取token体
     *
     * @param token
     * @return
     */
    private String getTokenBody(String token) {
        return Optional.ofNullable(token.indexOf(" "))
                .filter(index -> index >= 0)
                .map(i -> token.substring(i + 1)).orElse(token);
    }

    /**
     * 获得随机的code
     *
     * @return
     */
    private String getCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LEN; i++) {
            code.append(RandomUtil.randomInt(0, 9));
        }
        return code.toString();
    }


}
