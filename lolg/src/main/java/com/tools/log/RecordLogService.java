package com.tools.log;


import org.springframework.stereotype.Service;

@Service
public class RecordLogService {

    public void recordLog(String log){
        System.out.println(log);
    }

}
