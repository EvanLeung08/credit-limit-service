package com.eshare.tunnel.database;

import com.eshare.tunnel.database.dataobject.CustomerDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerTunnel {

  public CustomerDO getById(String customerId);
}
