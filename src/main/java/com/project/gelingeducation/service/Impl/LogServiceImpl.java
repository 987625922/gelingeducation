package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.utils.HttpUtil;
import com.project.gelingeducation.dao.ILogDao;
import com.project.gelingeducation.domain.Log;
import com.project.gelingeducation.service.ILogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LogServiceImpl implements ILogService {

    @Autowired
    private ILogDao logDao;

    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return logDao.queryAll(currentPage, pageSize);
        }else {
            return logDao.queryAll();
        }
    }

    @Override
    @Transactional
    public void save(String username, String browser, String ip,
                     ProceedingJoinPoint joinPoint, Log log) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.project.gelingeducation.common.annotation.Log aopLog
                = method.getAnnotation(com.project.gelingeducation.common.annotation.Log.class);

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        // 描述
        if (log != null) {
            log.setDescription(aopLog.value());
        }
        assert log != null;
        log.setRequestIp(ip);
        log.setAddress(HttpUtil.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        log.setBrowser(browser);
        logDao.save(log);
    }

    @Override
    public Log findByErrDetail(Long id) {
        return logDao.findByErrDetail(id);
    }

    @Override
    public void download(List<Log> logs, HttpServletResponse response) throws IOException {
        logDao.download(logs, response);
    }

    @Transactional
    @Override
    public void delAllByError() {
        logDao.delAllByError();
    }

    @Transactional
    @Override
    public void delAllByInfo() {
        logDao.delAllByInfo();
    }

}
