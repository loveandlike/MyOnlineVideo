package com.example.administrator.myonlinevideo.bombapi.result;


import java.util.List;

/**
 * 所有视频新闻查询结果
 *
 * 作者：yuanchao on 2016/8/16 0016 11:48
 * 邮箱：yuanchao@feicuiedu.com
 */
public class QueryResult<T> {

    private List<T> results;

    public List<T> getResults() {
        return results;
    }

//     {
//        "results": [{...},{...}]
//    }
}
