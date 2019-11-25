package cn.superfree.visualizationsimulation.service;

import cn.superfree.visualizationsimulation.datastructure.list.MyListResponse;

public interface ListService {

    InstanceStatusEnum status();

    MyListResponse create(String type);


}
