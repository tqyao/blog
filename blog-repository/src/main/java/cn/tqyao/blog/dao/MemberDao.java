package cn.tqyao.blog.dao;

import cn.tqyao.blog.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author -Tanqy
 * @since 2020-11-09
 */
@Repository
public interface MemberDao extends BaseMapper<Member> {

}
