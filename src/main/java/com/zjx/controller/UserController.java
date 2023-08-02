package com.zjx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjx.common.PageResult;
import com.zjx.common.Result;
import com.zjx.common.UserContext;
import com.zjx.enums.RoleEnum;
import com.zjx.mapper.UserMapper;
import com.zjx.pojo.User;
import com.zjx.util.MD5Util;
import com.zjx.vo.PageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/add")
    public Result addUser(User user, HttpSession session){
        try {
            //校验用户名
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("user_name", user.getUserName());
            Long count = userMapper.selectCount(wrapper);
            if(count > 0){
                return Result.failed("用户名已占用");
            }
            wrapper.clear();
            //校验昵称
            wrapper.eq("nick_name", user.getNickName());
            count = userMapper.selectCount(wrapper);
            if(count > 0){
                return Result.failed("昵称已占用");
            }
            //注册的默认都是读者角色
            user.setRole(RoleEnum.READER.getCode());
            user.setPassword(MD5Util.getMD5(user.getPassword()));
            user.setRegistDate(new Date());
            userMapper.insert(user);
            //密码置空
            user.setPassword("");
            session.setAttribute("user", user);
        } catch (Exception e) {
            return Result.failed("注册失败");
        }
        return Result.success("注册成功");
    }

    /**
     * 用户登陆
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/login")
    public Result login(User user, HttpSession session){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", user.getUserName());
        User tmpUser = userMapper.selectOne(wrapper);
        if(tmpUser == null){
            return Result.failed("用户不存在");
        }
        //获取加密过的密码进行比较
        String password = tmpUser.getPassword();
        if(!MD5Util.getMD5(user.getPassword()).equals(password)){
            return Result.failed("密码错误");
        }
        //密码置空
        tmpUser.setPassword("");
        session.setAttribute("user", tmpUser);
        return Result.success("登陆成功");
    }

    /**
     * 查看用户
     * @param pageVO
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/list")
    public PageResult getUserList(PageVO pageVO, User user){
        Page<User> page = new Page<>(pageVO.getPage(), pageVO.getLimit());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //只查询读者
        wrapper.eq("role", RoleEnum.READER.getCode());
        if(StringUtils.isNotBlank(user.getUserName())){
            wrapper.like("user_name", user.getUserName());
        }
        if(StringUtils.isNotBlank(user.getNickName())){
            wrapper.like("nick_name", user.getNickName());
        }

        Page<User> userPage = userMapper.selectPage(page, wrapper);
        PageResult<User> pageResult = PageResult.success(userPage.getTotal(), userPage.getRecords());
        return pageResult;
    }

    /**
     * 首页
     * @param session
     * @return
     */
    @RequestMapping("/")
    public String main(HttpSession session){
        return "main";
    }
    @RequestMapping("/regist")
    public String regist(){
        return "regist";
    }


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

    /**
     * 是否管理员角色,用于控制页面按钮显示
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/isAdmin")
    public Boolean isAdmin(){
        User user = UserContext.getUser();
        return RoleEnum.ADMIN.getCode() == user.getRole();
    }


}
