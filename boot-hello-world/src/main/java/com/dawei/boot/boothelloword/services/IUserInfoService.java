package com.dawei.boot.boothelloword.services;

import com.dawei.boot.boothelloword.pojo.UserInfo;

/**
 * @author by Dawei on 2019/4/24.
 */
public interface IUserInfoService {

    UserInfo getUserInfoById(String loginName);
}
