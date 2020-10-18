package cn.ekgc.itrip.pojo.vo;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

public class Page<E> implements Serializable {
    private static final long serialVersionUID = 4748771161475774951L;
    private Integer curPage;            //当前页
    private Long total;                 //总条数
    private Integer pageSize;           //每页显示数量
    private Integer pageCount;          //页面总数
    private Integer beginPos;           //当前页起始条数  第多少条
    private List<E> rows;               //集合

    public Page() {
    }
    public Page(Integer curPage,Integer pageSize){
        if (curPage!=null&&curPage>0){
            this.curPage=curPage;
        }else {
            this.curPage=1;
        }
        if (pageSize!=null&&pageSize>0){
            this.pageSize=pageSize;
        }else {
            this.curPage=5;
        }
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(Integer beginPos) {
        this.beginPos = beginPos;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
    public void pageToPage(PageInfo pageInfo){
        this.rows=pageInfo.getList();
        this.total=(Long)pageInfo.getTotal();
        this.pageCount=pageInfo.getPages();
    }
}
