package cn.superfree.visualizationsimulation.controller;

import java.util.HashMap;
import java.util.Map;

import cn.superfree.visualizationsimulation.datastructure.list.MyListResponse;
import cn.superfree.visualizationsimulation.service.InstanceStatusEnum;
import cn.superfree.visualizationsimulation.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.superfree.visualizationsimulation.constant.Response;

/**
 * ListController
 */
@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    ListService listService;

    @GetMapping("/")
    public Response list() {
        InstanceStatusEnum status = listService.status();
        switch (status) {
            case EMPTY:
                return putRel(Response.ok("List not exist, able to create"), new String[]{"create"});
            case ALIVE:
                return putRel(Response.ok("List exist, able to operation"), new String[]{"insert", "delete", "clear"});
            case BORKEN:
                return putRel(Response.ok("List broken, clear and recreate again"), new String[]{"clear"});
        }
        return putRel(Response.error("server error, request again or later"), new String[]{"list"});
    }

    @GetMapping("/rels")
    public Response rels() {
        return putRel(Response.ok("avalible rels"), new String[]{"create", "insert", "clear"});
    }

    @PostMapping("/create")
    public Response create(@RequestParam String type) {
        MyListResponse myListResponse = listService.create(type);
        if(myListResponse == null) {
            return Response.error("create fail");
        } else {
            return Response.ok("create success", myListResponse);
        }
    }

    @PostMapping("/insert")
    public Response insert() {
        return null;
    }

    @PostMapping("/clear")
    public Response clear() {
        return null;
    }

    private static Map<String, String> rels = new HashMap<>();

    private static final String ip = "http://localhost:8999";

    static {
        rels.put("list", ip + "/list");
        rels.put("create", ip + "/list/create");
        rels.put("insert", ip + "/list/insert");
        rels.put("clear" , ip + "/list/clear");
    }

    private Response putRel(Response response, String[] rels) {
        for(String rel : rels) {
            String path = ListController.rels.get(rel);
            if(path != null) {
                response.putRel(rel, path);
            }
        }
        return response;
    }
}