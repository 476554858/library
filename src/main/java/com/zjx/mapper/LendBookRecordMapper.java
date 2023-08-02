package com.zjx.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjx.pojo.LendBookRecord;
import com.zjx.vo.LendRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LendBookRecordMapper extends BaseMapper<LendBookRecord>{

    @Select("select * from (select r.id,r.user_id,r.lend_date ,r.revert_date ,r.book_id,b.book_name,b.author,b.press,b.category,b.`status`\n" +
            "from t_lend_book_record r LEFT JOIN t_book b on b.id = r.book_id ) as t ${ew.customSqlSegment}")
    Page<LendRecordVO> findLendRocord(Page<LendRecordVO> page, @Param(Constants.WRAPPER) Wrapper<LendRecordVO> wrapper);

    @Select("select * from (select r.id,r.lend_date ,r.revert_date ,r.book_id,b.book_name,b.author,b.press,b.category,b.`status`,u.nick_name\n" +
            "            from t_lend_book_record r LEFT JOIN t_book b on b.id = r.book_id\n" +
            "LEFT JOIN t_user u on r.user_id = u.id) as t ${ew.customSqlSegment}")
    Page<LendRecordVO> findLendRocordWithUser(Page<LendRecordVO> page, @Param(Constants.WRAPPER) Wrapper<LendRecordVO> wrapper);
}
