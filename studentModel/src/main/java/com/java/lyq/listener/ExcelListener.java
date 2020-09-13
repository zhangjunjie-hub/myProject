package com.java.lyq.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.java.lyq.dto.Student;
import com.java.lyq.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<Student> {
    private List<Student> datas = new ArrayList<>();
    private static final int BATCH_COUNT = 2;
    private StudentServiceImpl studentService;

    public ExcelListener(StudentServiceImpl studentService){
        this.studentService = studentService;
    }

    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        //数据存储到datas，供批量处理，或后续自己业务逻辑处理。
        datas.add(student);
        //达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if(datas.size() >= BATCH_COUNT){
            saveData();
            // 存储完成清理datas
            datas.clear();
        }
    }

    private void saveData() {
        //这个是新增操作
        studentService.insertStudent(datas);
        //根据学生姓名更新学生信息
        //studentService.updateStudent(datas);
    }

    public List<Student> getDatas() {
        return datas;
    }

    public void setDatas(List<Student> datas) {
        this.datas = datas;
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();//确保所有数据都能入库
    }
}
