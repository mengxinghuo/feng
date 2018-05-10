package com.truck.service;

import com.github.pagehelper.PageInfo;
import com.truck.common.ServerResponse;
import com.truck.pojo.Contact;
import com.truck.vo.ContactVo;

public interface IContactService {

    ServerResponse add(Integer adminId,Contact contact);

    ServerResponse del(Integer contactId);

    ServerResponse update(Contact contact);

    ServerResponse<PageInfo> list(Integer adminId, int pageNum, int pageSize);

    ContactVo assembleContactVo(Contact contact);
}
