package cn.superfree.visualizationsimulation.service.imp;

import cn.superfree.visualizationsimulation.datastructure.list.MyList;
import cn.superfree.visualizationsimulation.datastructure.list.MyListResponse;
import cn.superfree.visualizationsimulation.service.InstanceStatusEnum;
import cn.superfree.visualizationsimulation.service.ListService;
import org.springframework.stereotype.Service;

@Service
public class ListServiceImp implements ListService {

    private MyList myList;

    private InstanceStatusEnum status = InstanceStatusEnum.EMPTY;

    @Override
    public InstanceStatusEnum status() {
        return status;
    }

    @Override
    public MyListResponse create(String type) {
        myList = MyList.create(type);
        if(myList == null) {
            return null;
        } else {
            return getInstance();
        }
    }

    private MyListResponse getInstance() {
        MyListResponse response = myList.formData();
        response.setStatus(status);
        return response;
    }
}
