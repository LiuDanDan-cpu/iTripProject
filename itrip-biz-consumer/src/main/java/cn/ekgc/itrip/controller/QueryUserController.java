package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.BaseController;
import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.LinkUser;
import cn.ekgc.itrip.pojo.vo.LinkUserVO;
import cn.ekgc.itrip.transport.biz.LinkUserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("queryUser")
@RequestMapping("/biz/api/userinfo")
public class QueryUserController extends BaseController {
    @Autowired
    private LinkUserTransport transport;

    @PostMapping("/queryuserlinkuser")
    public ResultVO queryUserLinkUser(@RequestBody LinkUser linkUser)throws Exception{
        String token=request.getHeader("token");
        linkUser.setToken(token);
        List<LinkUser> list= transport.getListByQuery(linkUser);
        if (list!=null){
            return ResultVO.success(list);
        }
        return ResultVO.failure("该用户没有常用联系人");
    }

    /**
     * <b>根据所给对象添加常用联系人</b>
     * @param linkUser
     * @return
     * @throws Exception
     */
    @PostMapping("/adduserlinkuser")
    public ResultVO addLinkUser(@RequestBody LinkUser linkUser)throws Exception{
        String token= request.getHeader("token");
        linkUser.setToken(token);
        boolean query=transport.addLinkUser(linkUser);
        if (query){
            return ResultVO.success("新增成功");
        }
        return ResultVO.failure("添加常用联系人失败");
    }

    /**
     * <b>根据所给数组删除常用联系人</b>
     * @param ids
     * @return
     * @throws Exception
     */
    @GetMapping("/deluserlinkuser")
    public ResultVO deleteLinkUser(String[] ids)throws Exception{
        String token=request.getHeader("token");
        ArrayList<String> arrayList=new ArrayList<>();
        for (int i=0;i<ids.length;i++){
            arrayList.add(ids[i]);
        }
        arrayList.add(ids.length,token);
        return transport.deleteLinkUser(arrayList);
    }
    @PostMapping("/modifyuserlinkuser")
    public ResultVO updateLinkUser(@RequestBody LinkUserVO linkUserVO)throws Exception{
        linkUserVO.setToken(request.getHeader("token"));
        return transport.updateLinkUser(linkUserVO);
    }
}
