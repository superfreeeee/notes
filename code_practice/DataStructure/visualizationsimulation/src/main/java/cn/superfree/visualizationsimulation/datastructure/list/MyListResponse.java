package cn.superfree.visualizationsimulation.datastructure.list;

import cn.superfree.visualizationsimulation.service.InstanceStatusEnum;
import lombok.Data;

@Data
public class MyListResponse {

    private InstanceStatusEnum status;

    private String type;

    private int length;

    private int[] data;
}
